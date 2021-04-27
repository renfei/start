package net.renfei.service.start.impl;

import net.renfei.service.start.StorageService;

/**
 * @author renfei
 */
public class RedisStorageServiceImpl implements StorageService {
    @Override
    public void set(String key, Object object, Long expiration) {

    }

    @Override
    public Object get(String key, Boolean remove) {
        return null;
    }
}
