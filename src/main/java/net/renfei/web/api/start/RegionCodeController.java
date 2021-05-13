package net.renfei.web.api.start;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.renfei.config.SystemConfig;
import net.renfei.sdk.entity.APIResult;
import net.renfei.service.start.RegionCodeService;
import net.renfei.web.BaseController;
import net.renfei.web.api.start.vo.RegionVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 行政区划代码接口
 *
 * @author renfei
 */
@RestController
@RequestMapping("/api")
@Api(value = "行政区划代码接口", tags = "行政区划代码接口")
public class RegionCodeController extends BaseController {
    private final RegionCodeService regionCodeService;

    protected RegionCodeController(SystemConfig systemConfig,
                                   RegionCodeService regionCodeService) {
        super(systemConfig);
        this.regionCodeService = regionCodeService;
    }

    @GetMapping("regionCode")
    @ApiOperation(value = "查询顶级行政区划", notes = "查询行政区划-省列表", tags = "行政区划代码接口")
    public APIResult<List<RegionVO>> getRegionByCode() {
        return new APIResult<>(regionCodeService.getRegion(""));
    }

    @GetMapping("regionCode/{regionCode}")
    @ApiOperation(value = "根据行政代码获取子级行政区划", notes = "查询行政区划-市、区县列表", tags = "行政区划代码接口")
    public APIResult<List<RegionVO>> getRegionByCode(@PathVariable("regionCode") String regionCode) {
        return new APIResult<>(regionCodeService.getRegion(regionCode));
    }
}
