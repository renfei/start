package net.renfei.service.start.dto;

import lombok.Data;

import java.util.Date;

/**
 * 角色数据传输对象
 *
 * @author renfei
 */
@Data
public class RoleDTO {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDeleted;

    private String uuid;

    private String roleName;

    private String parentUuid;
}
