package net.renfei.web.view;

import net.renfei.config.SystemConfig;
import net.renfei.web.BaseController;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

/**
 * 网页控制器基类
 *
 * @author renfei
 */
public abstract class ViewController extends BaseController {
    public ViewController(SystemConfig systemConfig) {
        super(systemConfig);
    }

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder WebDataBinder
     */
    @Override
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        super.initBinder(binder);
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     *
     * @param mv ModelAndView
     */
    @Override
    @ModelAttribute
    public void modelAttribute(ModelAndView mv) {
        super.modelAttribute(mv);
    }
}
