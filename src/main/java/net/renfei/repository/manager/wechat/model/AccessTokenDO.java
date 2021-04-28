package net.renfei.repository.manager.wechat.model;

import lombok.Data;

/**
 * @author renfei
 */
@Data
public class AccessTokenDO extends BaseWeChatModel {
    private String access_token;
    private Integer expires_in;

    @Override
    public String toString() {
        return "AccessTokenDO{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                '}' + ',' + super.toString();
    }
}
