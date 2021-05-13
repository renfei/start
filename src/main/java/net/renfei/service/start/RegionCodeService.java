package net.renfei.service.start;

import net.renfei.web.api.start.vo.RegionVO;

import java.util.List;

/**
 * 行政区划代码服务
 * 使用《中华人民共和国行政区划代码》国家标准(GB/T2260)
 *
 * @author renfei
 */
public interface RegionCodeService {
    /**
     * 根据行政区划代码获取行政区划数据
     *
     * @param regionCode 行政区划代码
     * @return 行政区划数据
     */
    List<RegionVO> getRegion(String regionCode);
}
