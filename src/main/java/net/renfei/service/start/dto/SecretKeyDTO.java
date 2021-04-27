package net.renfei.service.start.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author renfei
 */
@Data
public class SecretKeyDTO {
    private Long id;
    private Date createTime;
    private Date updateTime;
    private String uuid;
    private String publicKey;
    private String privateKey;
}
