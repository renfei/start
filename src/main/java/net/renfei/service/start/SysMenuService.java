package net.renfei.service.start;

import net.renfei.sdk.entity.ListData;
import net.renfei.service.start.dto.MenuDTO;
import net.renfei.service.start.dto.UserDTO;
import net.renfei.web.api.start.ao.SysMenuAO;

import java.util.List;

/**
 * 系统后台菜单服务
 *
 * @author renfei
 */
public interface SysMenuService {
    /**
     * 根据登录的用户获取后台菜单
     *
     * @param userDTO 登录的用户
     * @return
     */
    List<MenuDTO> getMenuByUser(UserDTO userDTO);

    ListData<MenuDTO> getAllMenu(int pages, int rows);

    void editMenu(SysMenuAO menuAO);

    void deleteMenuById(Long id);

    /**
     * 递归查询子菜单
     *
     * @param menuDTO 菜单
     * @param ids     拥有的菜单权限ID们
     */
    void setChildMenu(MenuDTO menuDTO, List<Long> ids);
}
