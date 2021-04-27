package net.renfei.service;

import net.renfei.config.RenFeiConfig;
import net.renfei.util.GeneralConvertor;

/**
 * @author renfei
 */
public abstract class BaseService {
    protected final RenFeiConfig renFeiConfig;
    protected final GeneralConvertor convertor;

    protected BaseService(RenFeiConfig renFeiConfig,
                          GeneralConvertor convertor) {
        this.renFeiConfig = renFeiConfig;
        this.convertor = convertor;
    }
}
