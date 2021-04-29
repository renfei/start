package net.renfei.repository.manager.aliyun;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import lombok.extern.slf4j.Slf4j;
import net.renfei.config.RenFeiConfig;
import net.renfei.exception.BusinessException;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.service.start.FileSignedService;
import net.renfei.service.start.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * 阿里云 OSS 对象存储服务
 *
 * @author renfei
 */
@Slf4j
@Service
public class AliyunOSS extends AliyunService implements FileUploadService, FileSignedService {
    public AliyunOSS(RenFeiConfig renFeiConfig) {
        super(renFeiConfig, null);
    }

    @Override
    public String uploadFile(MultipartFile file, String path, String newPath) {
        if (file.isEmpty()) {
            throw new BusinessException("文件为空!");
        }
        // 文件名
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 新文件名
        fileName = UUID.randomUUID().toString().replace("-", "") + suffixName;
        try {
            uploadFile(file.getInputStream(), path + fileName);
        } catch (IOException e) {
            log.error("inputParams:{} and errorMessage:{}", path, e.getMessage(), e);
            return null;
        }
        if (BeanUtils.isEmpty(newPath)) {
            return renFeiConfig.getStaticDomain() + "/" + path + fileName;
        } else {
            return newPath + "/" + path + fileName;
        }
    }

    @Override
    public String uploadFile(File file, String path, String newPath) {
        if (file == null) {
            throw new BusinessException("文件为空!");
        }
        // 文件名
        String fileName = file.getName();
        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 新文件名
        fileName = UUID.randomUUID().toString().replace("-", "") + suffixName;
        try {
            uploadFile(new FileInputStream(file), path + fileName);
        } catch (IOException e) {
            log.error("inputParams:{} and errorMessage:{}", path, e.getMessage(), e);
            return null;
        }
        if (BeanUtils.isEmpty(newPath)) {
            return renFeiConfig.getStaticDomain() + "/" + path + fileName;
        } else {
            return newPath + "/" + path + fileName;
        }
    }

    @Override
    public String getSignedUrl(String objectName) {
        // 设置URL过期时间为24小时。1天(d)=86400000毫秒(ms)
        Date expiration = new Date(System.currentTimeMillis() + 86400000);
        return getSignedUrl(renFeiConfig.getAliyun().getOss().getDownloadBucketName(), objectName, expiration);
    }

    @Override
    public String getSignedUrl(String objectName, Date expiration) {
        return getSignedUrl(renFeiConfig.getAliyun().getOss().getDownloadBucketName(), objectName, expiration);
    }

    @Override
    public String getSignedUrl(String bucketName, String objectName, Date expiration) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder()
                .build(renFeiConfig.getAliyun().getOss().getEndpoint(), renFeiConfig.getAliyun().getAccessKeyId(), renFeiConfig.getAliyun().getAccessKeySecret());
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
        // 关闭OSSClient。
        ossClient.shutdown();
        return renFeiConfig.getAliyun().getOss().getDownloadHost() + url.getPath() + "?" + url.getQuery();
    }

    /**
     * 获取限速下载的签名URL
     *
     * @param bucketName 储存桶
     * @param objectName 对象地址
     * @param expiration 授权过期时间
     * @param speed      限速（单位KB/s）
     * @return 签名URL
     */
    public String getTrafficLimitUrl(String bucketName, String objectName, Date expiration, int speed) {
        // 限速 （X） KB/s。
        int limitSpeed = speed * 1024 * 8;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder()
                .build(renFeiConfig.getAliyun().getOss().getEndpoint(), renFeiConfig.getAliyun().getAccessKeyId(), renFeiConfig.getAliyun().getAccessKeySecret());
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        request.setExpiration(expiration);
        request.setTrafficLimit(limitSpeed);
        URL url = ossClient.generatePresignedUrl(request);
        ossClient.shutdown();
        return renFeiConfig.getAliyun().getOss().getDownloadHost() + url.getPath() + "?" + url.getQuery();
    }

    private void uploadFile(InputStream inputStream, String objectName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder()
                .build(renFeiConfig.getAliyun().getOss().getEndpoint(), renFeiConfig.getAliyun().getAccessKeyId(), renFeiConfig.getAliyun().getAccessKeySecret());
        ossClient.putObject(renFeiConfig.getAliyun().getOss().getBucketName(), objectName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
