package net.renfei.repository.manager.wechat.model;

import lombok.Data;

/**
 * @author renfei
 */
@Data
public abstract class BaseWeChatModel {
    private Integer errcode;
    private String errmsg;

    @Override
    public String toString() {
        return "BaseWeChatModel{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
