package net.renfei.web.view.cms;

import net.renfei.config.RenFeiConfig;
import net.renfei.web.view.ViewController;
import org.springframework.stereotype.Controller;

/**
 * 新闻类CMS页面
 *
 * @author renfei
 */
@Controller
public class CmsController extends ViewController {
    public CmsController(RenFeiConfig renFeiConfig) {
        super(renFeiConfig);
    }
}
