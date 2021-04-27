package net.renfei.web.view;

import net.renfei.config.RenFeiConfig;
import net.renfei.util.GeneralConvertor;
import org.springframework.stereotype.Controller;

/**
 * 网站首页
 *
 * @author renfei
 */
@Controller
public class IndexController extends ViewController {
    protected IndexController(RenFeiConfig renFeiConfig,
                              GeneralConvertor convertor) {
        super(renFeiConfig, convertor);
    }
}
