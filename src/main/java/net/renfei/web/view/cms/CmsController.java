package net.renfei.web.view.cms;

import net.renfei.config.RenFeiConfig;
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

    public CmsController(RenFeiConfig renFeiConfig,
                         CmsPostService cmsPostService) {
        super(renFeiConfig);
        this.cmsPostService = cmsPostService;
    }
}
