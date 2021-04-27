package net.renfei.security.interceptor;

import net.renfei.service.start.impl.UserServiceImpl;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 自定义权限决策管理器
 * 有了权限资源({@link FilterInvocationSecurityMetadataSourceImpl})，
 * 知道了当前访问的url需要的具体权限，接下来就是决策当前的访问是否能通过权限验证了
 *
 * @author renfei
 */
@Component
public class AccessDecisionManagerImpl implements AccessDecisionManager {
    /**
     * 取当前用户的权限与这次请求的这个url需要的权限作对比，决定是否放行
     * auth 包含了当前的用户信息，包括拥有的权限,即之前{@link UserServiceImpl}登录时候存储的用户对象
     * object 就是{@link FilterInvocation}对象，可以得到request等web资源。
     * configAttributes 是本次访问需要的权限。即上一步的 {@link FilterInvocationSecurityMetadataSourceImpl} 中查询核对得到的权限列表
     *
     * @param authentication
     * @param o
     * @param collection
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o,
                       Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : collection) {
            if (authentication == null) {
                throw new AccessDeniedException("Access Denied! 当前访问没有权限！");
            }
            //当前请求需要的权限
            String needRole = configAttribute.getAttribute();
            //当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("Access Denied! 权限不足！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
