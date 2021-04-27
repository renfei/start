package net.renfei.service.start;

import net.renfei.service.start.dto.IpInfoDTO;

/**
 * IP服务
 *
 * @author renfei
 */
public interface IPService {
    IpInfoDTO query(String ip);
}
