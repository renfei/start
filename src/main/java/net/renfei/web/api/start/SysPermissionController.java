package net.renfei.web.api.start;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.renfei.annotation.OperationLog;
import net.renfei.config.SystemConfig;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.entity.ListData;
import net.renfei.sdk.utils.NumberUtils;
import net.renfei.service.start.PermissionService;
import net.renfei.service.start.dto.PermissionDTO;
import net.renfei.service.start.type.ModuleEnum;
import net.renfei.service.start.type.OperationTypeEnum;
import net.renfei.web.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author renfei
 */
@RestController
@RequestMapping("/api")
@Api(value = "系统资源配置接口", tags = "系统资源配置接口")
public class SysPermissionController extends BaseController {
    private final PermissionService permissionService;

    protected SysPermissionController(SystemConfig systemConfig,
                                      PermissionService permissionService) {
        super(systemConfig);
        this.permissionService = permissionService;
    }

    @GetMapping("sys/permission")
    @OperationLog(operation = OperationTypeEnum.RETRIEVE, module = ModuleEnum.MENU, desc = "获取系统资源配置")
    @ApiOperation(value = "获取系统资源配置", notes = "获取系统资源配置", tags = "系统资源配置接口")
    public APIResult<ListData<PermissionDTO>> getAllPermissionList(@RequestParam(value = "page", required = false) String page,
                                                                   @RequestParam(value = "rows", required = false) String rows) {
        if (VUETIFYJS_ALL_ROWS.equals(rows)) {
            rows = Integer.MAX_VALUE + "";
        }
        return new APIResult<>(permissionService.getAllPermissionList(NumberUtils.parseInt(page, 1), NumberUtils.parseInt(rows, 10)));
    }
}
