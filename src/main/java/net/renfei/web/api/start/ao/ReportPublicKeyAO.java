package net.renfei.web.api.start.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 客户端上报公钥模型
 *
 * @author renfei
 */
@Data
@ApiModel(value = "客户端上报公钥模型",
        description = "前后端交换加密秘钥对时，客户端上报公钥模型",
        reference = "https://www.renfei.net/posts/1003346")
public class ReportPublicKeyAO {
    @ApiModelProperty(value = "加密使用的秘钥对ID")
    private String keyId;
    @ApiModelProperty(value = "经过服务器公钥加密后的客户端公钥")
    private String publicKey;
}
