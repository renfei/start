package net.renfei.service;

import net.renfei.config.SystemConfig;
import net.renfei.exception.BusinessException;
import net.renfei.security.ConfidentialRankEnum;

/**
 * @author renfei
 */
public abstract class BaseService {
    protected final SystemConfig systemConfig;

    protected BaseService(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    /**
     * 检查是否超过系统允许的最大密级
     *
     * @param confidentialRank 密级
     */
    protected void checkMaxConfidentialRank(ConfidentialRankEnum confidentialRank) {
        assert confidentialRank != null;
        assert systemConfig.getMaxConfidentialRank() != null;
        if (confidentialRank.getRank() > systemConfig.getMaxConfidentialRank().getRank()) {
            throw new BusinessException("当前操作设置的密级「"
                    + confidentialRank.getText()
                    + "」超过了系统允许的最高密级「"
                    + systemConfig.getMaxConfidentialRank().getText()
                    + "」，操作被阻止。");
        }
    }

    /**
     * 检查操作密级
     *
     * @param sourceRank 操作用户的密级
     * @param targetRank 操作目标的密级
     */
    protected void checkConfidentialRank(ConfidentialRankEnum sourceRank,
                                         ConfidentialRankEnum targetRank) {
        assert sourceRank != null;
        assert targetRank != null;
        if (targetRank.getRank() > sourceRank.getRank()) {
            throw new BusinessException("当前操作用户的密级「"
                    + sourceRank.getText()
                    + "」超过了操作目标的密级「"
                    + targetRank.getText()
                    + "」，操作被阻止。");
        }
    }
}
