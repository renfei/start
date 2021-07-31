package net.renfei.service.start;

import net.renfei.sdk.entity.ListData;
import net.renfei.service.start.dto.PermissionDTO;
import net.renfei.service.start.dto.RoleDTO;
import net.renfei.service.start.dto.UserDTO;
import net.renfei.service.start.type.ResourceTypeEnum;
import net.renfei.web.api.start.ao.SysPermissionAO;
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
     * @return 角色：{@link RoleDTO}
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

    /**
     * 获取全部资源列表
     *
     * @param resourceTypeEnum 资源类型：{@link ResourceTypeEnum}
     * @return 全部资源列表
     */
    List<PermissionDTO> getAllPermissionList(ResourceTypeEnum resourceTypeEnum);

    /**
     * 获取全部资源列表
     *
     * @param page 页码
     * @param rows 每页容量
     * @return 全部资源列表
     */
    ListData<PermissionDTO> getAllPermissionList(int page, int rows);

    /**
     * 根据资源反查角色
     *
     * @param permission 资源：{@link PermissionDTO}
     * @return 角色：{@link RoleDTO}
     */
    List<RoleDTO> getRoleListByPermission(PermissionDTO permission);

    void editPermission(SysPermissionAO permissionAO);

    void deletePermissionById(Long id);
}
