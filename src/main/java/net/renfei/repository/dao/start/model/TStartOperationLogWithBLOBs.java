package net.renfei.repository.dao.start.model;

import java.io.Serializable;

public class TStartOperationLogWithBLOBs extends TStartOperationLog implements Serializable {
    private String operDescribe;

    private String params;

    private String returning;

    private static final long serialVersionUID = 1L;

    public String getOperDescribe() {
        return operDescribe;
    }

    public void setOperDescribe(String operDescribe) {
        this.operDescribe = operDescribe;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getReturning() {
        return returning;
    }

    public void setReturning(String returning) {
        this.returning = returning;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", operDescribe=").append(operDescribe);
        sb.append(", params=").append(params);
        sb.append(", returning=").append(returning);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}