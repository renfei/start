package net.renfei.service.start;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 文件上传服务
 *
 * @author renfei
 */
public interface FileUploadService {
    /**
     * 上传文件
     *
     * @param multipartFile
     * @param savePath
     * @param newPath
     * @return
     */
    String uploadFile(MultipartFile multipartFile, String savePath, String newPath);

    /**
     * 上传文件
     *
     * @param file
     * @param savePath
     * @param newPath
     * @return
     */
    String uploadFile(File file, String savePath, String newPath);
}
