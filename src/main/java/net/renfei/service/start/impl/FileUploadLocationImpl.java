package net.renfei.service.start.impl;

import lombok.extern.slf4j.Slf4j;
import net.renfei.config.RenFeiConfig;
import net.renfei.exception.BusinessException;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.service.BaseService;
import net.renfei.service.start.FileUploadService;
import net.renfei.util.GeneralConvertor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * 文件上传到本地磁盘
 *
 * @author renfei
 */
@Slf4j
@Service
public class FileUploadLocationImpl extends BaseService implements FileUploadService {

    protected FileUploadLocationImpl(RenFeiConfig renFeiConfig, GeneralConvertor convertor) {
        super(renFeiConfig, convertor);
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
        Path copyLocation = Paths.get(path + File.separator + fileName);
        try {
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("inputParams:{} and errorMessage:{}", copyLocation, e.getMessage(), e);
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
        Path copyLocation = Paths.get(path + File.separator + fileName);
        try {
            Files.copy(new FileInputStream(file), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("inputParams:{} and errorMessage:{}", copyLocation, e.getMessage(), e);
            return null;
        }
        if (BeanUtils.isEmpty(newPath)) {
            return renFeiConfig.getStaticDomain() + "/" + path + fileName;
        } else {
            return newPath + "/" + path + fileName;
        }
    }
}
