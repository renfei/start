package net.renfei.web.api.start.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 秘钥对交换模型
 *
 * @author renfei
 */
@Data
@ApiModel(value = "秘钥对交换模型",
        description = "用于前后端秘钥交换的数据负载模型",
        reference = "https://www.renfei.net/posts/1003346")
public class SecretKeyVO {
    @ApiModelProperty(value = "秘钥对的ID")
    private String keyId;
    @ApiModelProperty(value = "秘钥对的公钥")
    private String publicKey;
    @ApiModelProperty(value = "秘钥的私钥")
    private String privateKey;
}
