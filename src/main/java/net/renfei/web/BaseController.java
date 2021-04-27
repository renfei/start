package net.renfei.web;

import net.renfei.config.RenFeiConfig;
import net.renfei.sdk.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局控制器基类
 *
 * @author renfei
 */
public abstract class BaseController {
    public static final String SESSION_KEY = "signedUserSession";
    protected final RenFeiConfig renFeiConfig;
    @Autowired
    protected HttpServletRequest request;

    protected BaseController(RenFeiConfig renFeiConfig) {
        this.renFeiConfig = renFeiConfig;
    }

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder WebDataBinder
     */
    protected void initBinder(WebDataBinder binder) {
    }

    protected void modelAttribute(ModelAndView mv) {

    }

    protected String getIp() {
        return IpUtils.getIpAddress(this.request);
    }
}
