package net.renfei.repository.dao.start.model;

import java.io.Serializable;
import java.util.Date;

public class TStartOperationLog implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Date operDate;
    private String operUserUuid;
    private String operUserName;
    private String operType;
    private String operModel;
    private String operIp;
    private String className;
    private String methodName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOperDate() {
        return operDate;
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    public String getOperUserUuid() {
        return operUserUuid;
    }

    public void setOperUserUuid(String operUserUuid) {
        this.operUserUuid = operUserUuid;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getOperModel() {
        return operModel;
    }

    public void setOperModel(String operModel) {
        this.operModel = operModel;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", operDate=").append(operDate);
        sb.append(", operUserUuid=").append(operUserUuid);
        sb.append(", operUserName=").append(operUserName);
        sb.append(", operType=").append(operType);
        sb.append(", operModel=").append(operModel);
        sb.append(", operIp=").append(operIp);
        sb.append(", className=").append(className);
        sb.append(", methodName=").append(methodName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}