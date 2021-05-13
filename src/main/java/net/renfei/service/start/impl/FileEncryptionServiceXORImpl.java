package net.renfei.service.start.impl;

import net.renfei.config.SystemConfig;
import net.renfei.service.BaseService;
import net.renfei.service.start.FileEncryptionService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 基于异或的文件加密实现
 *
 * @author renfei
 */
@Service
public class FileEncryptionServiceXORImpl extends BaseService implements FileEncryptionService {
    protected FileEncryptionServiceXORImpl(SystemConfig systemConfig) {
        super(systemConfig);
    }

    @Override
    public void encryption(InputStream inputStream, OutputStream outputStream) throws IOException {
        int i;
        while ((i = inputStream.read()) > -1) {
            outputStream.write(i ^ systemConfig.getFileEncryptionXor());
        }
        outputStream.flush();
    }

    @Override
    public void decrypt(InputStream inputStream, OutputStream outputStream) throws IOException {
        int i;
        while ((i = inputStream.read()) > -1) {
            outputStream.write(i ^ systemConfig.getFileEncryptionXor());
        }
        outputStream.flush();
    }
}
