package net.renfei.web.view.cms;

import net.renfei.config.SystemConfig;
import net.renfei.service.cms.CmsPostService;
import net.renfei.web.view.ViewController;
import org.springframework.stereotype.Controller;

/**
 * 新闻类CMS页面
 *
 * @author renfei
 */
@Controller
public class CmsController extends ViewController {
    private final CmsPostService cmsPostService;

    public CmsController(SystemConfig systemConfig,
                         CmsPostService cmsPostService) {
        super(systemConfig);
        this.cmsPostService = cmsPostService;
    }
}
