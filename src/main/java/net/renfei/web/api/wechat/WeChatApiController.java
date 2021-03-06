package net.renfei.web.api.wechat;

import io.swagger.annotations.Api;
import net.renfei.config.SystemConfig;
import net.renfei.sdk.entity.APIResult;
import net.renfei.web.BaseController;
import net.renfei.web.api.start.vo.SignInVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信服务接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/api/weChat")
@Api(value = "微信服务接口", tags = "微信服务接口")
public class WeChatApiController extends BaseController {
    protected WeChatApiController(SystemConfig systemConfig) {
        super(systemConfig);
    }

    @PostMapping("signIn")
    public APIResult<SignInVO> signIn(@RequestParam("code") String code) {
        // TODO 待实现
        return new APIResult(null);
    }
}
