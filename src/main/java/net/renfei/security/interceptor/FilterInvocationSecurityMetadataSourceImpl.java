package net.renfei.security.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 自定义权限资源过滤器，实现动态的权限验证
 * {@link FilterInvocationSecurityMetadataSource}（权限资源过滤器接口）继承了 {@link SecurityMetadataSource}（权限资源接口）
 * Spring Security是通过SecurityMetadataSource来加载访问时所需要的具体权限
 * 它的主要责任就是当访问一个url时，返回这个url所需要的访问权限
 *
 * @author renfei
 */
@Slf4j
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {
    private final AntPathMatcher antPathMatcher;

    public FilterInvocationSecurityMetadataSourceImpl() {
        this.antPathMatcher = new AntPathMatcher();
    }

    /**
     * 返回本次访问需要的权限，可以有多个权限
     *
     * @param o {@link FilterInvocation}
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) o;
        HttpServletRequest request = filterInvocation.getHttpRequest();
        String requestUrl = request.getMethod().toLowerCase() + ":" + filterInvocation.getRequestUrl();
        // TODO 去数据库查询资源 antPathMatcher.match(getUrl(), requestUrl)
        return null;
    }

    /**
     * 此处方法如果做了实现，返回了定义的权限资源列表，
     * Spring Security会在启动时校验每个ConfigAttribute是否配置正确，
     * 如果不需要校验，这里实现方法，方法体直接返回null即可。
     *
     * @return {@link Collection}
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * 方法返回类对象是否支持校验，
     * web项目一般使用FilterInvocation来判断，或者直接返回true
     *
     * @param aClass
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
