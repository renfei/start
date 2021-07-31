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
import net.renfei.web.api.start.ao.SysPermissionAO;
import org.springframework.web.bind.annotation.*;

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
    @OperationLog(operation = OperationTypeEnum.RETRIEVE, module = ModuleEnum.PERMISSION, desc = "获取系统资源配置")
    @ApiOperation(value = "获取系统资源配置", notes = "获取系统资源配置", tags = "系统资源配置接口")
    public APIResult<ListData<PermissionDTO>> getAllPermissionList(@RequestParam(value = "page", required = false) String page,
                                                                   @RequestParam(value = "rows", required = false) String rows) {
        if (VUETIFYJS_ALL_ROWS.equals(rows)) {
            rows = Integer.MAX_VALUE + "";
        }
        return new APIResult<>(permissionService.getAllPermissionList(NumberUtils.parseInt(page, 1), NumberUtils.parseInt(rows, 10)));
    }

    @PostMapping("sys/permission")
    @OperationLog(operation = OperationTypeEnum.UPDATE, module = ModuleEnum.PERMISSION, desc = "编辑系统资源配置")
    @ApiOperation(value = "编辑系统资源配置", notes = "编辑系统资源配置", tags = "系统资源配置接口")
    public APIResult editPermission(@RequestBody SysPermissionAO permissionAO) {
        permissionService.editPermission(permissionAO);
        return APIResult.success();
    }

    @DeleteMapping("sys/permission")
    @OperationLog(operation = OperationTypeEnum.DELETE, module = ModuleEnum.PERMISSION, desc = "删除系统资源配置")
    @ApiOperation(value = "删除系统资源配置", notes = "删除系统资源配置", tags = "系统资源配置接口")
    public APIResult deletePermissionById(Long id) {
        permissionService.deletePermissionById(id);
        return APIResult.success();
    }
}
