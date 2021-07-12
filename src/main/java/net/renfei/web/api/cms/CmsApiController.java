package net.renfei.web.api.cms;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.renfei.annotation.OperationLog;
import net.renfei.config.SystemConfig;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import net.renfei.sdk.entity.ListData;
import net.renfei.security.ConfidentialRankEnum;
import net.renfei.service.cms.CmsPostService;
import net.renfei.service.cms.dto.PostDTO;
import net.renfei.service.start.type.ModuleEnum;
import net.renfei.service.start.type.OperationTypeEnum;
import net.renfei.web.BaseController;
import net.renfei.web.api.cms.ao.PostAO;
import net.renfei.web.api.cms.vo.PostVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 后台CMS接口控制器
 *
 * @author renfei
 */
@RestController
@RequestMapping("/api/cms")
@Api(value = "后台CMS内容管理接口", tags = "CMS内容管理接口")
public class CmsApiController extends BaseController {
    private final CmsPostService cmsPostService;

    protected CmsApiController(SystemConfig systemConfig,
                               CmsPostService cmsPostService) {
        super(systemConfig);
        this.cmsPostService = cmsPostService;
    }

    @GetMapping("post")
    @OperationLog(operation = OperationTypeEnum.RETRIEVE, module = ModuleEnum.CMS, desc = "后台获取CMS内容列表")
    @ApiOperation(value = "根据查询条件获取内容列表", notes = "根据查询条件获取内容列表", tags = "CMS内容管理接口")
    public APIResult<ListData<PostVO>>
    getPostList(@RequestParam(value = "category", required = false) Long category,
                @RequestParam(value = "confidentialRank", required = false) ConfidentialRankEnum confidentialRank,
                @RequestParam(value = "startDate", required = false) Date startDate,
                @RequestParam(value = "endDate", required = false) Date endDate,
                @RequestParam(value = "pages", required = false) String pages,
                @RequestParam(value = "rows", required = false) String rows) {
        ListData<PostDTO> postDTOListData =
                cmsPostService.getPostList(getSignedUser(), category, confidentialRank, startDate, endDate, pages, rows);
        ListData<PostVO> postVOListData = new ListData<>();
        postVOListData.setTotalPages(postDTOListData.getTotalPages());
        postVOListData.setTotal(postDTOListData.getTotal());
        postVOListData.setShowRows(postDTOListData.getShowRows());
        postVOListData.setCurrentPage(postDTOListData.getCurrentPage());
        if (postDTOListData.getData() != null && !postDTOListData.getData().isEmpty()) {
            List<PostVO> postVOS = new ArrayList<>();
            for (PostDTO postDTO : postDTOListData.getData()
            ) {
                PostVO postVO = new PostVO();
                BeanUtils.copyProperties(postDTO, postVO);
                postVOS.add(postVO);
            }
            postVOListData.setData(postVOS);
        }
        return new APIResult<>(postVOListData);
    }

    @PostMapping("post")
    @OperationLog(operation = OperationTypeEnum.CREATE, module = ModuleEnum.CMS, desc = "添加CMS内容")
    @ApiOperation(value = "添加CMS内容", notes = "添加CMS内容", tags = "CMS内容管理接口")
    public APIResult addPost(PostAO post) {
        cmsPostService.addPost(post, getSignedUser());
        return APIResult.success();
    }

    @GetMapping("post/{postId}")
    @OperationLog(operation = OperationTypeEnum.RETRIEVE, module = ModuleEnum.CMS, desc = "后台获取CMS内容详情")
    @ApiOperation(value = "根据ID获取内容详情", notes = "根据ID获取内容详情", tags = "CMS内容管理接口")
    public APIResult<PostVO> getPostById(@PathVariable("postId") Long postId) {
        PostDTO postDTO = cmsPostService.getPostByPostId(getSignedUser(), postId);
        if (postDTO == null) {
            return APIResult.builder()
                    .code(StateCode.Failure)
                    .message("根据ID未找到此内容，或您无权限查看此内容。")
                    .build();
        }
        PostVO postVO = new PostVO();
        BeanUtils.copyProperties(postDTO, postVO);
        return new APIResult<>(postVO);
    }

    @PutMapping("post/{postId}")
    @OperationLog(operation = OperationTypeEnum.UPDATE, module = ModuleEnum.CMS, desc = "修改CMS内容")
    @ApiOperation(value = "修改CMS内容", notes = "修改CMS内容", tags = "CMS内容管理接口")
    public APIResult updatePost(@PathVariable("postId") Long postId, PostAO post) {
        post.setId(postId);
        cmsPostService.updatePost(post, getSignedUser());
        return APIResult.success();
    }

    @DeleteMapping("post/{postId}")
    @OperationLog(operation = OperationTypeEnum.DELETE, module = ModuleEnum.CMS, desc = "删除CMS内容")
    @ApiOperation(value = "删除CMS内容", notes = "删除CMS内容", tags = "CMS内容管理接口")
    public APIResult deletePost(@PathVariable("postId") Long postId) {
        cmsPostService.deletePost(postId, getSignedUser());
        return APIResult.success();
    }
}
