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
    UNKNOWN("未知");
    private final String describe;

    ModuleEnum(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }
}
