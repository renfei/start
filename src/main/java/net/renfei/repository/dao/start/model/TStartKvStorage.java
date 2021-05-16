package net.renfei.repository.dao.start.model;

import java.io.Serializable;
import java.util.Date;

public class TStartKvStorage implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String keys;
    private Date expiration;
    private String values;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", keys=").append(keys);
        sb.append(", expiration=").append(expiration);
        sb.append(", values=").append(values);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}