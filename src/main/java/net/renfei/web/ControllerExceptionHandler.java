package net.renfei.web;

import lombok.extern.slf4j.Slf4j;
import net.renfei.exception.BusinessException;
import net.renfei.exception.NeedU2FException;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常捕获处理
 *
 * @author renfei
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public APIResult handler(BusinessException e) {
        log.error("BusinessException", e);
        return APIResult.builder()
                .code(StateCode.Failure)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(NeedU2FException.class)
    public APIResult handler(NeedU2FException e) {
        log.error("NeedU2FException", e);
        return APIResult.builder()
                .code(StateCode.NeedTOTP)
                .message(StateCode.NeedTOTP.getDescribe())
                .build();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public APIResult handler(NoHandlerFoundException e) {
        log.error("NoHandlerFoundException", e);
        return APIResult.builder()
                .code(StateCode.Failure)
                .message("路径不存在，请检查路径是否正确")
                .build();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public APIResult handler(DuplicateKeyException e) {
        log.error("DuplicateKeyException", e);
        return APIResult.builder()
                .code(StateCode.Error)
                .message("数据库中已存在该记录")
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public APIResult handler(AccessDeniedException e) {
        log.error("DuplicateKeyException", e);
        return APIResult.builder()
                .code(StateCode.Error)
                .message("数据库中已存在该记录")
                .build();
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public APIResult handler(RuntimeException e) {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        log.error("运行时异常:", e);
        return APIResult.builder()
                .code(StateCode.Error)
                .message(StateCode.Error.getDescribe())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public APIResult handler(Exception e) {
        log.error("GlobalRestExceptionHandler :", e);
        return APIResult.builder()
                .code(StateCode.Error)
                .message(StateCode.Error.getDescribe())
                .build();
    }
}
