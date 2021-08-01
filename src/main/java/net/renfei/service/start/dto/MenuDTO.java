package net.renfei.service.start.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author renfei
 */
@Data
public class MenuDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String menuName;

    private String menuLink;

    private Integer orderNum;

    private String menuIcon;

    private Boolean isNewWindow;

    private Long parentId;

    private List<MenuDTO> child;
}
