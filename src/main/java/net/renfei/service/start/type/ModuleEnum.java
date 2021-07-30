package net.renfei.service.start.type;

/**
 * 功能模块
 *
 * @author renfei
 */
public enum ModuleEnum {
    /**
     * 未知模块
     */
    UNKNOWN("未知"),
    MENU("后台系统菜单模块"),
    PERMISSION("系统资源模块"),
    CMS("CMS内容管理模块");
    private final String describe;

    ModuleEnum(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }
}
