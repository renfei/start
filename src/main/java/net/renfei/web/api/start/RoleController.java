package net.renfei.web.api.start;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.renfei.annotation.OperationLog;
import net.renfei.config.SystemConfig;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.utils.NumberUtils;
import net.renfei.service.start.RoleService;
import net.renfei.service.start.type.ModuleEnum;
import net.renfei.service.start.type.OperationTypeEnum;
import net.renfei.web.BaseController;
import net.renfei.web.api.start.ao.RoleAO;
import net.renfei.web.api.start.vo.RoleDataVO;
import org.springframework.web.bind.annotation.*;

/**
 * 系统角色接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/api")
@Api(value = "系统角色接口", tags = "系统角色接口")
public class RoleController extends BaseController {
    private final RoleService roleService;

    protected RoleController(SystemConfig systemConfig,
                             RoleService roleService) {
        super(systemConfig);
        this.roleService = roleService;
    }

    @GetMapping("sys/role")
    @OperationLog(operation = OperationTypeEnum.RETRIEVE, module = ModuleEnum.ROLE, desc = "获取后台角色列表")
    @ApiOperation(value = "获取后台角色列表", notes = "获取后台角色列表", tags = "后台角色接口")
    public APIResult<RoleDataVO> getRoleList(@RequestParam(value = "page", required = false) String page,
                                             @RequestParam(value = "rows", required = false) String rows) {
        return new APIResult<>(roleService.getAllRoleList(NumberUtils.parseInt(page, 1), NumberUtils.parseInt(rows, 10)));
    }

    @PostMapping("sys/role")
    @OperationLog(operation = OperationTypeEnum.UPDATE, module = ModuleEnum.ROLE, desc = "编辑后台角色")
    @ApiOperation(value = "编辑后台角色", notes = "编辑后台角色", tags = "后台角色接口")
    public APIResult editRole(@RequestBody RoleAO roleAO) {
        roleService.editRoleById(roleAO);
        return APIResult.success();
    }

    @DeleteMapping("sys/role")
    @OperationLog(operation = OperationTypeEnum.DELETE, module = ModuleEnum.ROLE, desc = "删除后台角色")
    @ApiOperation(value = "删除后台角色", notes = "删除后台角色", tags = "后台角色接口")
    public APIResult deleteRoleById(Long id) {
        roleService.deleteRoleById(id);
        return APIResult.success();
    }
}
