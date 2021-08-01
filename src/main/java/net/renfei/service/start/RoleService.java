package net.renfei.service.start;

import net.renfei.web.api.start.ao.RoleAO;
import net.renfei.web.api.start.vo.RoleDataVO;

/**
 * @author renfei
 */
public interface RoleService {
    RoleDataVO getAllRoleList(int pages, int rows);

    void editRoleById(RoleAO roleAO);

    void deleteRoleById(Long id);
}
