package net.renfei.service.start.impl;

import net.renfei.config.SystemConfig;
import net.renfei.repository.dao.start.TStartRegionMapper;
import net.renfei.repository.dao.start.model.TStartRegion;
import net.renfei.repository.dao.start.model.TStartRegionExample;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.service.BaseService;
import net.renfei.service.start.RegionCodeService;
import net.renfei.web.api.start.vo.RegionVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author renfei
 */
@Service
public class RegionCodeServiceImpl extends BaseService implements RegionCodeService {
    private final TStartRegionMapper regionMapper;

    protected RegionCodeServiceImpl(SystemConfig systemConfig,
                                    TStartRegionMapper regionMapper) {
        super(systemConfig);
        this.regionMapper = regionMapper;
    }

    /**
     * 根据行政区划代码获取行政区划数据
     *
     * @param regionCode 行政区划代码
     * @return 行政区划数据
     */
    @Override
    public List<RegionVO> getRegion(String regionCode) {
        if (regionCode.length() != 6) {
            regionCode = null;
        }
        TStartRegionExample example = new TStartRegionExample();
        example.setOrderByClause("region_code");
        if (BeanUtils.isEmpty(regionCode)) {
            example.createCriteria().andRegionCodeLike("__0000");
        } else if (regionCode.endsWith("0000")) {
            // 直辖市 北京、天津、上海、重庆、香港、澳门单独处理
            if (regionCode.startsWith("110") || regionCode.startsWith("120")
                    || regionCode.startsWith("310") || regionCode.startsWith("500")
                    || regionCode.startsWith("810") || regionCode.startsWith("820")) {
                example.createCriteria().andRegionCodeLike(regionCode.substring(0, 2) + "____");
            } else {
                example.createCriteria().andRegionCodeLike(regionCode.substring(0, 2) + "__00");
            }
        } else if (regionCode.endsWith("00")) {
            example.createCriteria().andRegionCodeLike(regionCode.substring(0, 4) + "__");
        } else {
            example.createCriteria().andRegionCodeEqualTo(regionCode);
        }
        List<TStartRegion> regionList = regionMapper.selectByExample(example);
        if (BeanUtils.isEmpty(regionList)) {
            return new ArrayList<>(0);
        }
        List<RegionVO> regionVoList = new CopyOnWriteArrayList<>();
        for (TStartRegion region : regionList
        ) {
            if (region.getRegionCode().equals(regionCode)) {
                continue;
            }
            RegionVO regionVO = new RegionVO();
            org.springframework.beans.BeanUtils.copyProperties(region, regionVO);
            regionVoList.add(regionVO);
        }
        return regionVoList;
    }
}
