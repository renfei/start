package net.renfei.web;

import net.renfei.config.SystemConfig;
import net.renfei.sdk.utils.IpUtils;
import net.renfei.service.start.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    protected final SystemConfig systemConfig;
    @Autowired
    protected HttpServletRequest request;

    protected BaseController(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
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

    protected UserDTO getSignedUser() {
        Object object = null;
        if ("SESSION".equals(systemConfig.getAuthMode())) {
            object = request.getSession().getAttribute(SESSION_KEY);
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            object = authentication.getPrincipal();
        }
        if (object instanceof UserDTO) {
            return (UserDTO) object;
        }
        return null;
    }
}
