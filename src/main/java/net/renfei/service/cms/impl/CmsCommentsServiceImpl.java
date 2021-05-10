package net.renfei.service.cms.impl;

import net.renfei.config.SystemConfig;
import net.renfei.service.BaseService;
import net.renfei.service.cms.CmsCommentsService;
import org.springframework.stereotype.Service;

/**
 * CMS评论服务
 *
 * @author renfei
 */
@Service
public class CmsCommentsServiceImpl extends BaseService implements CmsCommentsService {
    protected CmsCommentsServiceImpl(SystemConfig systemConfig) {
        super(systemConfig);
    }
}
