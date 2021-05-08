package net.renfei.web.api.cms;

import net.renfei.config.RenFeiConfig;
import net.renfei.service.cms.CmsPostService;
import net.renfei.web.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CMS接口控制器
 *
 * @author renfei
 */
@RestController
@RequestMapping("/api/cms")
public class CmsApiController extends BaseController {
    private final CmsPostService cmsPostService;

    protected CmsApiController(RenFeiConfig renFeiConfig,
                               CmsPostService cmsPostService) {
        super(renFeiConfig);
        this.cmsPostService = cmsPostService;
    }
}
