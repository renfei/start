package net.renfei.service.start.dto;

import lombok.Data;
import org.springframework.security.access.ConfigAttribute;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色数据传输对象
 *
 * @author renfei
 */
@Data
public class RoleDTO implements ConfigAttribute, Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDeleted;

    private String uuid;

    private String roleEnName;

    private String roleName;

    private String parentUuid;

    @Override
    public String getAttribute() {
        if (roleEnName == null) {
            return null;
        } else if (roleEnName.startsWith("ROLE_")) {
            return roleEnName;
        } else {
            return "ROLE_" + roleEnName;
        }
    }
}
