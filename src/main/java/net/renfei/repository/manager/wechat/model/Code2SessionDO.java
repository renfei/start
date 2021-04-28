package net.renfei.repository.manager.wechat.model;

import lombok.Data;

/**
 * @author renfei
 */
@Data
public class Code2SessionDO extends BaseWeChatModel {
    private String openid;
    private String session_key;
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
