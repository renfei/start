package net.renfei.service.start.dto;

import lombok.Data;

import java.util.Date;

/**
 * 权限数据传输对象
 *
 * @author renfei
 */
@Data
public class PermissionDTO {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDeleted;

    private String uuid;

    private String resourceName;

    private String requestMethod;

    private String resourceUrl;

    private String resourceType;
}
