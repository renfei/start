package net.renfei.web.api.start.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录请求模型
 *
 * @author renfei
 */
@Data
@ApiModel(value = "登录请求模型",
        description = "用户登录时请求的参数模型，注意密码需要进行加密传输",
        reference = "https://www.renfei.net/posts/1003346")
public class SignInAO {
    @ApiModelProperty(value = "用户名/手机号/邮箱", required = true)
    private String userName;
    @ApiModelProperty(value = "加密后的密码", required = true)
    private String password;
    @ApiModelProperty(value = "TOTP一次性密码")
    private String tOtp;
    @ApiModelProperty(value = "使用加密秘钥的ID", required = true)
    private String keyId;
    @ApiModelProperty(value = "是否使用验证码模式登录")
    private Boolean useVerCode;
}
