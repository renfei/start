package net.renfei.web.api.start.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 重置密码请求模型
 *
 * @author renfei
 */
@Data
@ApiModel(value = "重置密码请求模型",
        description = "修改密码请求传递的数据对象，密码需要加密传输",
        reference = "https://www.renfei.net/posts/1003346")
public class ResetPasswordAO {
    @ApiModelProperty(value = "使用加密秘钥的ID", required = true)
    private String keyId;
    @ApiModelProperty(value = "旧密码", required = true)
    private String oldPassword;
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;
}
