package net.renfei.service.start;

import lombok.extern.slf4j.Slf4j;
import net.renfei.config.SystemConfig;
import net.renfei.repository.dao.start.TStartKvStorageMapper;
import net.renfei.service.start.impl.RedisStorageServiceImpl;
import net.renfei.service.start.impl.StorageServiceDataBaseImpl;
import net.renfei.service.start.impl.StorageServiceSessionImpl;
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
    private final TStartKvStorageMapper kvStorageMapper;

    public StorageServiceFactory(SystemConfig systemConfig,
                                 TStartKvStorageMapper kvStorageMapper) {
        this.systemConfig = systemConfig;
        this.kvStorageMapper = kvStorageMapper;
    }

    public StorageService getStorageService(HttpServletRequest request) {
        switch (systemConfig.getKvStorage()) {
            case "Redis":
                return new RedisStorageServiceImpl();
            case "Session":
                return new StorageServiceSessionImpl(request);
            case "":
            case "DataBase":
            default:
                return new StorageServiceDataBaseImpl(kvStorageMapper);
        }
    }
}
