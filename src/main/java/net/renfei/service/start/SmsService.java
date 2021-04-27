package net.renfei.service.start;

import net.renfei.service.start.dto.SmsDTO;

/**
 * @author renfei
 */
public interface SmsService {
    /**
     * 发送短信
     *
     * @param sms
     * @return
     */
    void send(SmsDTO sms);
}
