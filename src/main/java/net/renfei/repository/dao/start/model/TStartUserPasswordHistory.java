package net.renfei.repository.dao.start.model;

import java.io.Serializable;
import java.util.Date;

public class TStartUserPasswordHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String password;
    private Date setTime;
    private Long setUserId;
    private String setUserName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }

    public Long getSetUserId() {
        return setUserId;
    }

    public void setSetUserId(Long setUserId) {
        this.setUserId = setUserId;
    }

    public String getSetUserName() {
        return setUserName;
    }

    public void setSetUserName(String setUserName) {
        this.setUserName = setUserName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", password=").append(password);
        sb.append(", setTime=").append(setTime);
        sb.append(", setUserId=").append(setUserId);
        sb.append(", setUserName=").append(setUserName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}