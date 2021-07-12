package net.renfei.security.interceptor;

import lombok.extern.slf4j.Slf4j;
import net.renfei.service.start.PermissionService;
import net.renfei.service.start.dto.PermissionDTO;
import net.renfei.service.start.type.ResourceTypeEnum;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
    private final PermissionService permissionService;
    private final List<PermissionDTO> permissionList;

    public FilterInvocationSecurityMetadataSourceImpl(PermissionService permissionService) {
        this.permissionService = permissionService;
        this.antPathMatcher = new AntPathMatcher();
        // 从数据库加载权限配置
        permissionList = this.permissionService.getAllPermissionList(ResourceTypeEnum.API);
    }

    /**
     * 返回本次访问需要的权限，可以有多个权限
     * 其实就是通过url地址获取 角色信息的方法
     *
     * @param object {@link FilterInvocation}
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        HttpServletRequest request = filterInvocation.getHttpRequest();
        String requestMethod = request.getMethod().toLowerCase();
        String requestUrl = filterInvocation.getRequest().getRequestURI();
        if (permissionList != null && !permissionList.isEmpty()) {
            List<ConfigAttribute> configAttributes = new CopyOnWriteArrayList<>();
            for (PermissionDTO permission : permissionList
            ) {
                // 遍历系统所有的资源进行匹配
                String method = permission.getRequestMethod().toLowerCase();
                String url = permission.getResourceUrl();
                if (requestMethod.equals(method) && antPathMatcher.match(url, requestUrl)) {
                    // 匹配命中了，将访问此资源需要的角色添加到 List<ConfigAttribute>
                    configAttributes.addAll(permissionService.getRoleListByPermission(permission));
                }
            }
            if (!configAttributes.isEmpty()) {
                return configAttributes;
            }
        }
        // 【警告】如果返回为null则说明此url地址不需要相应的角色就可以访问, 这样Security会放行
        Collection<ConfigAttribute> co = new CopyOnWriteArrayList<>();
        co.add(new SecurityConfig("null"));
        return co;
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
