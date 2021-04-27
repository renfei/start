package net.renfei.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.renfei.sdk.comm.StateCode;

/**
 * 业务逻辑异常
 *
 * @author renfei
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NeedU2FException extends RuntimeException {
    private StateCode stateCode;

    public NeedU2FException(String msg) {
        super(msg);
        this.stateCode = StateCode.NeedTOTP;
    }

    public NeedU2FException(String msg, Throwable t) {
        super(msg);
        this.stateCode = StateCode.NeedTOTP;
    }
}
