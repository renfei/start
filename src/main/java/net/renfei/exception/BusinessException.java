package net.renfei.exception;

/**
 * <p>Title: BusinessException</p>
 * <p>Description: 业务逻辑异常</p>
 *
 * @author renfei
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable t) {
        super(msg);
    }
}
