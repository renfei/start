package net.renfei.web.api.start;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.renfei.config.SystemConfig;
import net.renfei.exception.BusinessException;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.service.start.SecretKeyService;
import net.renfei.service.start.UserService;
import net.renfei.service.start.dto.UserDTO;
import net.renfei.util.JwtTokenUtil;
import net.renfei.web.BaseController;
import net.renfei.web.api.start.ao.ReportPublicKeyAO;
import net.renfei.web.api.start.ao.SignInAO;
import net.renfei.web.api.start.vo.SecretKeyVO;
import net.renfei.web.api.start.vo.SignInVO;
import net.renfei.web.api.start.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 认证接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/api/auth")
@Api(value = "认证接口", tags = "认证接口")
public class AuthApiController extends BaseController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final SecretKeyService secretKeyService;

    public AuthApiController(SystemConfig systemConfig,
                             UserService userService,
                             JwtTokenUtil jwtTokenUtil,
                             SecretKeyService secretKeyService) {
        super(systemConfig);
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.secretKeyService = secretKeyService;
    }

    /**
     * 申请获取服务器公钥
     *
     * @return
     */
    @GetMapping("secretKey")
    @ApiOperation(value = "申请获取服务器公钥", tags = "认证接口")
    public APIResult<SecretKeyVO> getSecretKey() {
        Map<Integer, String> map = secretKeyService.generateServerSecretKey();
        if (BeanUtils.isEmpty(map)) {
            return APIResult.builder()
                    .code(StateCode.Error)
                    .build();
        }
        SecretKeyVO secretKey = new SecretKeyVO();
        secretKey.setKeyId(map.get(1));
        secretKey.setPublicKey(map.get(0));
        return new APIResult<>(secretKey);
    }

    /**
     * 上报客户端公钥，并下发AES秘钥
     */
    @PostMapping("secretKey")
    @ApiOperation(value = "上报客户端公钥，并下发AES秘钥", tags = "认证接口")
    public APIResult<SecretKeyVO> setSecretKey(@RequestBody ReportPublicKeyAO reportPublicKeyAo) {
        try {
            String clientKey = secretKeyService.decryptByRsaKeyId(reportPublicKeyAo.getPublicKey(), reportPublicKeyAo.getKeyId());
            Map<String, String> map = secretKeyService.generateAesKey();
            String aesKey = map.get("aesKey");
            aesKey = secretKeyService.encryptByRsaKey(aesKey, clientKey);
            SecretKeyVO secretKey = new SecretKeyVO();
            secretKey.setKeyId(map.get("keyId"));
            secretKey.setPrivateKey(aesKey);
            secretKey.setPublicKey(aesKey);
            return new APIResult<>(secretKey);
        } catch (BusinessException businessException) {
            return APIResult.builder().code(StateCode.Failure).message(businessException.getMessage()).build();
        }
    }

    @PostMapping("signIn")
    @ApiOperation(value = "用户登录接口", tags = "认证接口")
    public APIResult<SignInVO> signIn(@RequestBody SignInAO signInAo) {
        UserDTO userDTO;
        SignInVO signInVO = new SignInVO();
        try {
            // 对密码解密
            signInAo.setPassword(secretKeyService.decryptByAesKeyId(signInAo.getPassword(), signInAo.getKeyId()));
            // 登录本站
            userDTO = userService.signIn(signInAo);
            // TODO 登录Discuz
        } catch (BusinessException businessException) {
            return APIResult.builder().code(StateCode.Failure).message(businessException.getMessage()).build();
        }
        if ("SESSION".equals(systemConfig.getAuthMode())) {
            // 放入 Session
            request.getSession().setAttribute(SESSION_KEY, userDTO);
        } else {
            // 签发 TOKEN
            String token = jwtTokenUtil.createJwt(userDTO.getUsername());
            signInVO.setToken(token);
        }
        return new APIResult<>(signInVO);
    }

    @GetMapping("myInfo")
    @ApiOperation(value = "获取当前登录的用户信息", tags = "认证接口")
    public APIResult<UserVO> getMyInfo() {
        UserDTO userDTO = getSignedUser();
        if (userDTO == null) {
            return APIResult.builder()
                    .code(StateCode.Unauthorized)
                    .message(StateCode.Unauthorized.getDescribe())
                    .build();
        }
        UserVO userVO = new UserVO();
        org.springframework.beans.BeanUtils.copyProperties(userDTO, userVO);
        return new APIResult<>(userVO);
    }
}
