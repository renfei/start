package net.renfei.web.api.start;

import io.swagger.annotations.Api;
import net.renfei.config.SystemConfig;
import net.renfei.service.start.UserService;
import net.renfei.web.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author renfei
 */
@RestController
@RequestMapping("/api/user")
@Api(value = "用户管理接口", tags = "用户管理接口")
public class UserApiController extends BaseController {
    private final UserService userService;

    protected UserApiController(SystemConfig systemConfig,
                                UserService userService) {
        super(systemConfig);
        this.userService = userService;
    }
}
