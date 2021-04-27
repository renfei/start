package net.renfei.web.api;

import lombok.extern.slf4j.Slf4j;
import net.renfei.config.RenFeiConfig;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.util.GeneralConvertor;
import net.renfei.web.BaseController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获处理
 *
 * @author renfei
 */
@Slf4j
@RestControllerAdvice(basePackages = "net.renfei.web.api")
public class GlobalRestExceptionHandler extends BaseController {

    protected GlobalRestExceptionHandler(RenFeiConfig renFeiConfig,
                                         GeneralConvertor convertor) {
        super(renFeiConfig, convertor);
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
