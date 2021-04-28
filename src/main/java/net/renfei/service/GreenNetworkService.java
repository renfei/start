package net.renfei.service;

/**
 * 绿网服务
 *
 * @author renfei
 */
public interface GreenNetworkService {
    /**
     * 文本合规安全扫描
     * 主要用于扫描暴恐、涉政、黄赌毒等违规信息
     *
     * @param text 待扫描文档
     * @return 是否合规
     */
    boolean textScan(String text);
}
