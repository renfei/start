package net.renfei.service;

import net.renfei.config.RenFeiConfig;

/**
 * @author renfei
 */
public abstract class BaseService {
    protected final RenFeiConfig renFeiConfig;

    protected BaseService(RenFeiConfig renFeiConfig) {
        this.renFeiConfig = renFeiConfig;
    }
}
