package net.renfei.repository.manager.wechat.model;

import lombok.Data;

/**
 * @author renfei
 */
@Data
public class Code2SessionDO extends BaseWeChatModel {
    /**
     * 用户唯一标识
     */
    private String openid;
    /**
     * 会话密钥
     */
    private String session_key;
    /**
     * 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回，详见 UnionID 机制说明。
     */
    private String unionid;

    @Override
    public String toString() {
        return "Code2SessionDO{" +
                "openid='" + openid + '\'' +
                ", session_key='" + session_key + '\'' +
                ", unionid='" + unionid + '\'' +
                '}' + ',' + super.toString();
    }
}
