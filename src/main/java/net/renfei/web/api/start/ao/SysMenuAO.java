package net.renfei.web.api.start.ao;

import lombok.Data;

/**
 * @author renfei
 */
@Data
public class SysMenuAO {
    private Long id;

    private String menuName;

    private String menuLink;

    private Integer orderNum;

    private String menuIcon;

    private Boolean isNewWindow;

    private Long parentId;
}
