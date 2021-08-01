package net.renfei.repository.dao.start.model;

import java.io.Serializable;

public class TStartSysMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String menuName;
    private String menuLink;
    private Integer orderNum;
    private String menuIcon;
    private Boolean isNewWindow;
    private Long parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuLink() {
        return menuLink;
    }

    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Boolean getIsNewWindow() {
        return isNewWindow;
    }

    public void setIsNewWindow(Boolean isNewWindow) {
        this.isNewWindow = isNewWindow;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuLink=").append(menuLink);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", menuIcon=").append(menuIcon);
        sb.append(", isNewWindow=").append(isNewWindow);
        sb.append(", parentId=").append(parentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}