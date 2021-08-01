package net.renfei.web.api.start.ao;

import lombok.Data;
import lombok.NonNull;
import net.renfei.web.api.start.vo.RequestMethod;
import net.renfei.web.api.start.vo.SysPermissionType;

/**
 * @author renfei
 */
@Data
public class SysPermissionAO {
    private Long id;
    @NonNull
    private String resourceName;
    @NonNull
    private String resourceUrl;
    @NonNull
    private SysPermissionType resourceType;
    @NonNull
    private RequestMethod requestMethod;
}
