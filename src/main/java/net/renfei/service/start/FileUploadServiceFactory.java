package net.renfei.service.start;

import net.renfei.config.SystemConfig;
import net.renfei.repository.manager.aliyun.AliyunOSS;
import net.renfei.service.start.impl.FileUploadLocationImpl;
import org.springframework.stereotype.Service;

/**
 * 文件上传服务工厂
 *
 * @author renfei
 */
@Service
public class FileUploadServiceFactory {
    private final SystemConfig systemConfig;
    private final AliyunOSS aliyunOSS;
    private final FileUploadLocationImpl fileUploadLocation;

    public FileUploadServiceFactory(SystemConfig systemConfig,
                                    AliyunOSS aliyunOSS,
                                    FileUploadLocationImpl fileUploadLocation) {
        this.systemConfig = systemConfig;
        this.aliyunOSS = aliyunOSS;
        this.fileUploadLocation = fileUploadLocation;
    }

    public FileUploadService getFileUploadService() {
        // 根据配置返回不同实现
        if ("ALIYUNOSS".equals(systemConfig.getFileUploadMode())) {
            return aliyunOSS;
        }
        return fileUploadLocation;
    }
}
