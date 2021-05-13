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
     * @param multipartFile 文件
     * @param savePath      保存路径
     * @param newPath       返回路径
     * @return
     */
    String uploadFile(MultipartFile multipartFile, String savePath, String newPath);

    /**
     * 上传文件
     *
     * @param file     文件
     * @param savePath 保存路径
     * @param newPath  返回路径
     * @return
     */
    String uploadFile(File file, String savePath, String newPath);
}
