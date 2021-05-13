package net.renfei.service.start;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件加解密服务
 *
 * @author renfei
 */
public interface FileEncryptionService {
    /**
     * 加密文件
     *
     * @param inputStream  输入流
     * @param outputStream 输出流
     */
    void encryption(InputStream inputStream, OutputStream outputStream) throws IOException;

    /**
     * 解密文件
     *
     * @param inputStream  输入流
     * @param outputStream 输出流
     */
    void decrypt(InputStream inputStream, OutputStream outputStream) throws IOException;
}
