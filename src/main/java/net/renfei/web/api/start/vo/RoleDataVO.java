package net.renfei.web.api.start.vo;

import lombok.Data;
import net.renfei.sdk.entity.ListData;

import java.util.List;

/**
 * @author renfei
 */
@Data
public class RoleDataVO {
    ListData<RoleVO> listData;
    private List<Permission> allApiPermission;
    private List<Permission> allMenuPermission;

    @Data
    public static class Permission {
        private String uuid;
        private String resourceName;
    }
}
