package net.renfei.repository.manager.wechat.model;

import lombok.Data;

/**
 * @author renfei
 */
@Data
public class PaidUnionIdDO extends BaseWeChatModel {
    private String unionid;

    @Override
    public String toString() {
        return "PaidUnionIdDO{" +
                "unionid='" + unionid + '\'' +
                '}' + ',' + super.toString();
    }
}
