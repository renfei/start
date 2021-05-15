package net.renfei.web.api;

import lombok.extern.slf4j.Slf4j;
import net.renfei.config.SystemConfig;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.web.BaseController;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常捕获处理
 *
 * @author renfei
 */
@Slf4j
@RestControllerAdvice(basePackages = "net.renfei.web.api")
public class GlobalRestExceptionHandler extends BaseController {

    protected GlobalRestExceptionHandler(SystemConfig systemConfig) {
        super(systemConfig);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public APIResult handlerNoFoundException(Exception e) {
        log.error("NoHandlerFoundException", e);
        return APIResult.builder()
                .code(StateCode.Failure)
                .message("路径不存在，请检查路径是否正确")
                .build();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public APIResult handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("DuplicateKeyException", e);
        return APIResult.builder()
                .code(StateCode.Error)
                .message("数据库中已存在该记录")
                .build();
    }

    @ExceptionHandler(Exception.class)
    public APIResult globalExceptionHandler(Exception e) {
        log.error("GlobalRestExceptionHandler :", e);
        return APIResult.builder()
                .code(StateCode.Error)
                .message(StateCode.Error.getDescribe())
                .build();
    }
}
