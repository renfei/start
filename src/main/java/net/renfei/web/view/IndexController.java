package net.renfei.web.view;

import net.renfei.config.RenFeiConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 网站首页
 *
 * @author renfei
 */
@Controller
public class IndexController extends ViewController {
    protected IndexController(RenFeiConfig renFeiConfig) {
        super(renFeiConfig);
    }

    @RequestMapping("/")
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("index");
        return mv;
    }
}
