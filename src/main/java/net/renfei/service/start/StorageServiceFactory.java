package net.renfei.service.start;

import lombok.extern.slf4j.Slf4j;
import net.renfei.config.SystemConfig;
import net.renfei.service.start.impl.SessionStorageServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 存储工厂类
 * 根据配置生产基于 Session 或 Redis 的存储服务
 *
 * @author renfei
 */
@Slf4j
@Service
public class StorageServiceFactory {
    private final SystemConfig systemConfig;

    public StorageServiceFactory(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    public StorageService getStorageService(HttpServletRequest request) {
        // 根据 renFeiConfig 配置生产不同的服务
        return new SessionStorageServiceImpl(request);
    }
}
