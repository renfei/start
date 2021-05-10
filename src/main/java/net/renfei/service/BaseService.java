package net.renfei.service;

import net.renfei.config.SystemConfig;

/**
 * @author renfei
 */
public abstract class BaseService {
    protected final SystemConfig systemConfig;

    protected BaseService(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }
}
