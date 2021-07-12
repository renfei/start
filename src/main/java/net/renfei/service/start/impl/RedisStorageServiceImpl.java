package net.renfei.service.start.impl;

import net.renfei.service.start.StorageService;

/**
 * @author renfei
 */
public class RedisStorageServiceImpl implements StorageService {
    @Override
    public void set(String key, String object, Long expiration) {

    }

    @Override
    public String get(String key, Boolean remove) {
        return null;
    }
}
