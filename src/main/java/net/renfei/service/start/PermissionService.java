package net.renfei.service.start;

import net.renfei.service.start.dto.PermissionDTO;
import net.renfei.service.start.dto.RoleDTO;
import net.renfei.service.start.dto.UserDTO;
import net.renfei.service.start.type.ResourceTypeEnum;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * 权限服务
 *
 * @author renfei
 */
public interface PermissionService {
    /**
     * 获取用户拥有的权限
     *
     * @param user {@link UserDTO}
     * @return {@link GrantedAuthority}
     */
    List<GrantedAuthority> getAuthoritiesByUser(UserDTO user);

    /**
     * 获取用户拥有的角色列表
     *
     * @param user {@link UserDTO}
     * @return {@link RoleDTO}
     */
    List<RoleDTO> getRoleListByUser(UserDTO user);

    /**
     * 根据角色列表获取权限列表
     *
     * @param roleList         {@link RoleDTO}
     * @param resourceTypeEnum 资源类型：{@link ResourceTypeEnum}
     * @return {@link PermissionDTO}
     */
    List<PermissionDTO> getPermissionListByRoleList(List<RoleDTO> roleList, ResourceTypeEnum resourceTypeEnum);
}
