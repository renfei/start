package net.renfei.service.start.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.config.SystemConfig;
import net.renfei.repository.dao.start.TStartPermissionMapper;
import net.renfei.repository.dao.start.TStartRoleMapper;
import net.renfei.repository.dao.start.TStartRolePermissionMapper;
import net.renfei.repository.dao.start.TStartUserRoleMapper;
import net.renfei.repository.dao.start.model.*;
import net.renfei.sdk.entity.ListData;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.service.BaseService;
import net.renfei.service.start.PermissionService;
import net.renfei.service.start.dto.PermissionDTO;
import net.renfei.service.start.dto.RoleDTO;
import net.renfei.service.start.dto.UserDTO;
import net.renfei.service.start.type.ResourceTypeEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 权限服务
 *
 * @author renfei
 */
@Service
public class PermissionServiceImpl extends BaseService implements PermissionService, Serializable {
    private static final long serialVersionUID = -8366929034564774130L;
    private final TStartRoleMapper roleMapper;
    private final TStartUserRoleMapper userRoleMapper;
    private final TStartPermissionMapper permissionMapper;
    private final TStartRolePermissionMapper rolePermissionMapper;

    protected PermissionServiceImpl(SystemConfig systemConfig,
                                    TStartRoleMapper roleMapper,
                                    TStartUserRoleMapper userRoleMapper,
                                    TStartPermissionMapper permissionMapper,
                                    TStartRolePermissionMapper rolePermissionMapper) {
        super(systemConfig);
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
        this.permissionMapper = permissionMapper;
        this.rolePermissionMapper = rolePermissionMapper;
    }


    /**
     * 获取用户拥有的权限
     *
     * @param user {@link UserDTO}
     * @return {@link GrantedAuthority}
     */
    @Override
    public List<GrantedAuthority> getAuthoritiesByUser(UserDTO user) {
        if (user == null) {
            return null;
        }
        // 获取用户拥有的角色
        List<RoleDTO> roleList = this.getRoleListByUser(user);
        if (roleList == null || roleList.isEmpty()) {
            return null;
        }
        List<GrantedAuthority> authorities = new CopyOnWriteArrayList<>();
        roleList.forEach(roleDTO -> authorities.add(new GrantedAuthorityImpl(roleDTO.getRoleEnName())));
        return authorities;
    }

    /**
     * 获取用户拥有的角色列表
     *
     * @param user {@link UserDTO}
     * @return {@link RoleDTO}
     */
    @Override
    public List<RoleDTO> getRoleListByUser(UserDTO user) {
        if (user == null || BeanUtils.isEmpty(user.getUuid())) {
            return null;
        }
        TStartUserRoleExample userRoleExample = new TStartUserRoleExample();
        userRoleExample.createCriteria()
                .andIsDeletedEqualTo(false)
                .andUserUuidEqualTo(user.getUuid());
        List<TStartUserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);
        if (userRoleList == null || userRoleList.isEmpty()) {
            return null;
        }
        List<String> roleUuid = new CopyOnWriteArrayList<>();
        userRoleList.forEach(userRole -> roleUuid.add(userRole.getRoleUuid()));
        TStartRoleExample roleExample = new TStartRoleExample();
        roleExample.createCriteria()
                .andIsDeletedEqualTo(false)
                .andUuidIn(roleUuid);
        List<TStartRole> roles = roleMapper.selectByExample(roleExample);
        this.getChildRole(roles);
        List<RoleDTO> roleDTOS = new CopyOnWriteArrayList<>();
        if (!BeanUtils.isEmpty(roles)) {
            for (TStartRole role : roles
            ) {
                RoleDTO roleDTO = new RoleDTO();
                org.springframework.beans.BeanUtils.copyProperties(role, roleDTO);
                roleDTOS.add(roleDTO);
            }
        }
        return roleDTOS;
    }

    /**
     * 根据角色列表获取权限列表
     *
     * @param roleList {@link RoleDTO}
     * @return {@link PermissionDTO}
     */
    @Override
    public List<PermissionDTO> getPermissionListByRoleList(List<RoleDTO> roleList, ResourceTypeEnum resourceTypeEnum) {
        if (roleList == null || roleList.isEmpty()) {
            return null;
        }
        List<String> roleUuid = new CopyOnWriteArrayList<>();
        roleList.forEach(role -> roleUuid.add(role.getUuid()));
        TStartRolePermissionExample rolePermissionExample = new TStartRolePermissionExample();
        rolePermissionExample.createCriteria()
                .andIsDeletedEqualTo(false)
                .andRoleUuidIn(roleUuid);
        List<TStartRolePermission> rolePermissionList = rolePermissionMapper.selectByExample(rolePermissionExample);
        if (rolePermissionList == null || rolePermissionList.isEmpty()) {
            return null;
        }
        List<String> permissionUuid = new CopyOnWriteArrayList<>();
        rolePermissionList.forEach(rolePermission -> permissionUuid.add(rolePermission.getPermissionUuid()));
        TStartPermissionExample permissionExample = new TStartPermissionExample();
        permissionExample.createCriteria()
                .andIsDeletedEqualTo(false)
                .andResourceTypeEqualTo(resourceTypeEnum.toString())
                .andUuidIn(permissionUuid);
        List<TStartPermission> permissionList = permissionMapper.selectByExample(permissionExample);
        List<PermissionDTO> permissions = new CopyOnWriteArrayList<>();
        if (!BeanUtils.isEmpty(permissionList)) {
            for (TStartPermission permission : permissionList
            ) {
                PermissionDTO permissionDTO = new PermissionDTO();
                org.springframework.beans.BeanUtils.copyProperties(permission, permissionDTO);
                permissions.add(permissionDTO);
            }
        }
        org.springframework.beans.BeanUtils.copyProperties(permissionList, permissions);
        return permissions;
    }

    @Override
    public List<PermissionDTO> getAllPermissionList(ResourceTypeEnum resourceTypeEnum) {
        TStartPermissionExample permissionExample = new TStartPermissionExample();
        permissionExample.createCriteria()
                .andIsDeletedEqualTo(false)
                .andResourceTypeEqualTo(resourceTypeEnum.toString());
        List<TStartPermission> permissionList = permissionMapper.selectByExample(permissionExample);
        List<PermissionDTO> permissions = new CopyOnWriteArrayList<>();
        if (!BeanUtils.isEmpty(permissionList)) {
            for (TStartPermission permission : permissionList
            ) {
                PermissionDTO permissionDTO = new PermissionDTO();
                org.springframework.beans.BeanUtils.copyProperties(permission, permissionDTO);
                permissions.add(permissionDTO);
            }
        }
        return permissions;
    }

    @Override
    public ListData<PermissionDTO> getAllPermissionList(int pages, int rows) {
        TStartPermissionExample permissionExample = new TStartPermissionExample();
        permissionExample.createCriteria()
                .andIsDeletedEqualTo(false);
        Page<TStartPermission> page = PageHelper.startPage(pages, rows);
        permissionMapper.selectByExample(permissionExample);
        List<PermissionDTO> permissions = new CopyOnWriteArrayList<>();
        if (!BeanUtils.isEmpty(page.getResult())) {
            for (TStartPermission permission : page.getResult()
            ) {
                PermissionDTO permissionDTO = new PermissionDTO();
                org.springframework.beans.BeanUtils.copyProperties(permission, permissionDTO);
                permissions.add(permissionDTO);
            }
        }
        return new ListData<>(permissions, page.getTotal(), page.getPageNum(), page.getPageSize(), page.getPages());
    }

    @Override
    public List<RoleDTO> getRoleListByPermission(PermissionDTO permission) {
        TStartRolePermissionExample rolePermissionExample = new TStartRolePermissionExample();
        rolePermissionExample.createCriteria()
                .andIsDeletedEqualTo(false)
                .andPermissionUuidEqualTo(permission.getUuid());
        List<TStartRolePermission> rolePermissionList = rolePermissionMapper.selectByExample(rolePermissionExample);
        if (rolePermissionList == null || rolePermissionList.isEmpty()) {
            return null;
        }
        List<String> roleUuid = new CopyOnWriteArrayList<>();
        rolePermissionList.forEach(rolePermission -> roleUuid.add(rolePermission.getRoleUuid()));
        TStartRoleExample roleExample = new TStartRoleExample();
        roleExample.createCriteria()
                .andIsDeletedEqualTo(false)
                .andUuidIn(roleUuid);
        List<TStartRole> roles = roleMapper.selectByExample(roleExample);
        this.getChildRole(roles);
        List<RoleDTO> roleList = new CopyOnWriteArrayList<>();
        if (!BeanUtils.isEmpty(roles)) {
            for (TStartRole role : roles
            ) {
                RoleDTO roleDTO = new RoleDTO();
                org.springframework.beans.BeanUtils.copyProperties(role, roleDTO);
                roleList.add(roleDTO);
            }
        }
        return roleList;
    }

    /**
     * 递归获取子级角色列表
     *
     * @param roles 角色列表
     */
    private void getChildRole(List<TStartRole> roles) {
        if (roles != null && !roles.isEmpty()) {
            for (TStartRole role : roles
            ) {
                TStartRoleExample example = new TStartRoleExample();
                example.createCriteria()
                        .andIsDeletedEqualTo(false)
                        .andParentUuidEqualTo(role.getUuid());
                List<TStartRole> childRoles = roleMapper.selectByExample(example);
                if (childRoles != null && !childRoles.isEmpty()) {
                    getChildRole(childRoles);
                    roles.addAll(childRoles);
                }
            }
        }
    }

    public static class GrantedAuthorityImpl implements GrantedAuthority {
        private final String authority;

        public GrantedAuthorityImpl(String authority) {
            this.authority = authority;
        }

        @Override
        public String getAuthority() {
            return this.authority;
        }
    }
}
