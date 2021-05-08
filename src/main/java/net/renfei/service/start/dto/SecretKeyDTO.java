package net.renfei.service.start.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author renfei
 */
@Data
public class SecretKeyDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Date createTime;
    private Date updateTime;
    private String uuid;
    private String publicKey;
    private String privateKey;
}
