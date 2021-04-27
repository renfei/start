package net.renfei.service.start;

import net.renfei.config.RenFeiConfig;
import net.renfei.service.start.impl.OperationLogServiceForDataBaseImpl;
import org.springframework.stereotype.Service;

/**
 * 操作日志工厂类
 * 日志可以输出到不同的地方，记录到库、记录到文件等
 *
 * @author renfei
 */
@Service
public class OperationLogServiceFactory {
    private final RenFeiConfig renFeiConfig;
    private final OperationLogServiceForDataBaseImpl operationLogServiceForDataBase;

    public OperationLogServiceFactory(RenFeiConfig renFeiConfig,
                                      OperationLogServiceForDataBaseImpl operationLogServiceForDataBase) {
        this.renFeiConfig = renFeiConfig;
        this.operationLogServiceForDataBase = operationLogServiceForDataBase;
    }

    public OperationLogService getOperationLogService() {
        // 根据 renFeiConfig 配置生产不同的记录服务
        return operationLogServiceForDataBase;
    }
}
