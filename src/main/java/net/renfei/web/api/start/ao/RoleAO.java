package net.renfei.web.api.start.ao;

import lombok.Data;

import java.util.List;

/**
 * @author renfei
 */
@Data
public class RoleAO {
    private Long id;
    private String uuid;
    private String roleEnName;
    private String roleName;
    private String parentUuid;
    private List<String> permissionUuid;
}
