package net.renfei.web.api.start;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.renfei.annotation.OperationLog;
import net.renfei.config.SystemConfig;
import net.renfei.sdk.entity.APIResult;
import net.renfei.service.start.SysMenuService;
import net.renfei.service.start.dto.MenuDTO;
import net.renfei.service.start.type.ModuleEnum;
import net.renfei.service.start.type.OperationTypeEnum;
import net.renfei.web.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
