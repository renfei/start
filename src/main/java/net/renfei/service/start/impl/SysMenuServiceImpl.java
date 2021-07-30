package net.renfei.service.start.impl;

import net.renfei.config.SystemConfig;
import net.renfei.repository.dao.start.TStartPermissionMapper;
import net.renfei.repository.dao.start.TStartRolePermissionMapper;
import net.renfei.repository.dao.start.TStartSysMenuMapper;
import net.renfei.repository.dao.start.TStartUserRoleMapper;
import net.renfei.repository.dao.start.model.*;
import net.renfei.service.BaseService;
import net.renfei.service.start.SysMenuService;
import net.renfei.service.start.dto.MenuDTO;
import net.renfei.service.start.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统后台菜单服务
 *
 * @author renfei
 */
@Service
public class SysMenuServiceImpl extends BaseService implements SysMenuService {
    private final TStartSysMenuMapper menuMapper;
    private final TStartUserRoleMapper userRoleMapper;
    private final TStartPermissionMapper permissionMapper;
    private final TStartRolePermissionMapper rolePermissionMapper;

    protected SysMenuServiceImpl(SystemConfig systemConfig,
                                 TStartSysMenuMapper menuMapper,
                                 TStartUserRoleMapper userRoleMapper,
                                 TStartPermissionMapper permissionMapper,
                                 TStartRolePermissionMapper rolePermissionMapper) {
        super(systemConfig);
        this.menuMapper = menuMapper;
        this.userRoleMapper = userRoleMapper;
        this.permissionMapper = permissionMapper;
        this.rolePermissionMapper = rolePermissionMapper;
    }

    /**
     * 根据登录的用户获取后台菜单
     *
     * @param userDTO 登录的用户
     * @return
     */
    @Override
    public List<MenuDTO> getMenuByUser(UserDTO userDTO) {
        List<Long> ids = getMenuIdByUser(userDTO);
        TStartSysMenuExample example = new TStartSysMenuExample();
        example.setOrderByClause("order_num ASC");
        example.createCriteria()
                .andIdIn(ids)
                .andParentIdIsNull();
        List<TStartSysMenu> menus = menuMapper.selectByExample(example);
        if (menus.isEmpty()) {
            return null;
        }
        List<MenuDTO> menuDTOS = new ArrayList<>();
        for (TStartSysMenu menu : menus
        ) {
            MenuDTO menuDTO = new MenuDTO();
            BeanUtils.copyProperties(menu, menuDTO);
            setChildMenu(menuDTO, ids);
            menuDTOS.add(menuDTO);
        }
        return menuDTOS;
    }

    /**
     * 递归查询子菜单
     *
     * @param menuDTO 菜单
     * @param ids     拥有的菜单权限ID们
     */
    @Override
    public void setChildMenu(MenuDTO menuDTO, List<Long> ids) {
        TStartSysMenuExample example = new TStartSysMenuExample();
        example.setOrderByClause("order_num ASC");
        example.createCriteria()
                .andIdIn(ids)
                .andParentIdEqualTo(menuDTO.getId());
        List<TStartSysMenu> menus = menuMapper.selectByExample(example);
        if (menus.isEmpty()) {
            return;
        }
        List<MenuDTO> menuDTOS = new ArrayList<>();
        for (TStartSysMenu menu : menus
        ) {
            MenuDTO menuChildDTO = new MenuDTO();
            BeanUtils.copyProperties(menu, menuChildDTO);
            setChildMenu(menuChildDTO, ids);
            menuDTOS.add(menuChildDTO);
        }
        menuDTO.setChild(menuDTOS);
    }

    private List<Long> getMenuIdByUser(UserDTO userDTO) {
        TStartUserRoleExample userRoleExample = new TStartUserRoleExample();
        userRoleExample.createCriteria()
                .andIsDeletedEqualTo(false)
                .andUserUuidEqualTo(userDTO.getUuid());
        List<TStartUserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        if (userRoles.isEmpty()) {
            return null;
        }
        List<String> roleUuids = new ArrayList<>();
        userRoles.forEach(userRole -> roleUuids.add(userRole.getRoleUuid()));
        TStartRolePermissionExample rolePermissionExample = new TStartRolePermissionExample();
        rolePermissionExample.createCriteria()
                .andIsDeletedEqualTo(false)
                .andRoleUuidIn(roleUuids);
        List<TStartRolePermission> rolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);
        if (rolePermissions.isEmpty()) {
            return null;
        }
        List<String> permissionUuid = new ArrayList<>();
        rolePermissions.forEach(rolePermission -> permissionUuid.add(rolePermission.getPermissionUuid()));
        TStartPermissionExample permissionExample = new TStartPermissionExample();
        permissionExample.createCriteria()
                .andIsDeletedEqualTo(false)
                .andResourceTypeEqualTo("MENU")
                .andUuidIn(permissionUuid);
        List<TStartPermission> permissions = permissionMapper.selectByExample(permissionExample);
        if (permissions.isEmpty()) {
            return null;
        }
        List<Long> ids = new ArrayList<>();
        permissions.forEach(permission -> ids.add(Long.parseLong(permission.getResourceUrl())));
        return ids;
    }
}
