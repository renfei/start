package net.renfei.web.api.start.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author renfei
 */
@Data
public class RoleVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String uuid;
    private String roleEnName;
    private String roleName;
    private String parentUuid;
    private Boolean isBuiltIn;
    private List<RoleDataVO.Permission> myMenuPermission;
    private List<RoleDataVO.Permission> myApiPermission;
}
