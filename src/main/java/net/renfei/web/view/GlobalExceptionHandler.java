package net.renfei.web.view;

import lombok.extern.slf4j.Slf4j;
import net.renfei.sdk.comm.StateCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常捕获处理
 *
 * @author renfei
 */
@Slf4j
@ControllerAdvice(basePackages = "net.renfei.web.view")
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String globalExceptionHandler(Exception e) {
        log.error("GlobalRestExceptionHandler :", e);
        return StateCode.Error.getDescribe();
    }
}
