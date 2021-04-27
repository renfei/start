package net.renfei.service.start;

import net.renfei.service.start.dto.OperationLogDTO;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 操作日志（审计日志）
 *
 * @author renfei
 */
public interface OperationLogService {
    /**
     * 记录日志
     *
     * @param operationLog {@link OperationLogDTO}
     */
    void log(OperationLogDTO operationLog);

    /**
     * 记录日志
     *
     * @param operationLog             {@link OperationLogDTO}
     * @param servletRequestAttributes {@link ServletRequestAttributes}
     */
    void log(OperationLogDTO operationLog, ServletRequestAttributes servletRequestAttributes);
}
