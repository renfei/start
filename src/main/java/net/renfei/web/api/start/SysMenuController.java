package net.renfei.web.api.start;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.renfei.annotation.OperationLog;
import net.renfei.config.SystemConfig;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.entity.ListData;
import net.renfei.sdk.utils.NumberUtils;
import net.renfei.service.start.SysMenuService;
import net.renfei.service.start.dto.MenuDTO;
import net.renfei.service.start.type.ModuleEnum;
import net.renfei.service.start.type.OperationTypeEnum;
import net.renfei.web.BaseController;
import net.renfei.web.api.start.ao.SysMenuAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "后台菜单接口", tags = "后台菜单接口")
public class SysMenuController extends BaseController {
    private final SysMenuService menuService;

    protected SysMenuController(SystemConfig systemConfig,
                                SysMenuService menuService) {
        super(systemConfig);
        this.menuService = menuService;
    }

    @GetMapping("sys/menu")
    @OperationLog(operation = OperationTypeEnum.RETRIEVE, module = ModuleEnum.MENU, desc = "获取后台菜单列表")
    @ApiOperation(value = "获取后台菜单列表", notes = "获取后台菜单列表", tags = "后台菜单接口")
    public APIResult<List<MenuDTO>> getMenu() {
        return new APIResult<>(menuService.getMenuByUser(getSignedUser()));
    }

    @GetMapping("sys/menu/all")
    @OperationLog(operation = OperationTypeEnum.RETRIEVE, module = ModuleEnum.MENU, desc = "获取所有后台菜单列表")
    @ApiOperation(value = "获取所有后台菜单列表", notes = "获取所有后台菜单列表", tags = "后台菜单接口")
    public APIResult<ListData<MenuDTO>> getAllMenu(@RequestParam(value = "page", required = false) String page,
                                                   @RequestParam(value = "rows", required = false) String rows) {
        if (VUETIFYJS_ALL_ROWS.equals(rows)) {
            rows = Integer.MAX_VALUE + "";
        }
        return new APIResult<>(menuService.getAllMenu(NumberUtils.parseInt(page, 1), NumberUtils.parseInt(rows, 10)));
    }

    @PostMapping("sys/menu")
    @OperationLog(operation = OperationTypeEnum.UPDATE, module = ModuleEnum.MENU, desc = "编辑后台菜单")
    @ApiOperation(value = "编辑后台菜单", notes = "编辑后台菜单", tags = "后台菜单接口")
    public APIResult editMenu(@RequestBody SysMenuAO menuAO) {
        menuService.editMenu(menuAO);
        return APIResult.success();
    }

    @DeleteMapping("sys/menu")
    @OperationLog(operation = OperationTypeEnum.DELETE, module = ModuleEnum.MENU, desc = "删除后台菜单")
    @ApiOperation(value = "删除后台菜单", notes = "删除后台菜单", tags = "后台菜单接口")
    public APIResult deleteMenuById(Long id) {
        menuService.deleteMenuById(id);
        return APIResult.success();
    }
}
