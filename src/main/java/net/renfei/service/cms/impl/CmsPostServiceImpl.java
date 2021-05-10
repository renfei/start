package net.renfei.service.cms.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.config.SystemConfig;
import net.renfei.repository.dao.cms.TCmsPostTagMapper;
import net.renfei.repository.dao.cms.TCmsPostsMapper;
import net.renfei.repository.dao.cms.model.TCmsPostTag;
import net.renfei.repository.dao.cms.model.TCmsPostTagExample;
import net.renfei.repository.dao.cms.model.TCmsPostsExample;
import net.renfei.repository.dao.cms.model.TCmsPostsWithBLOBs;
import net.renfei.sdk.entity.ListData;
import net.renfei.sdk.utils.ListUtils;
import net.renfei.sdk.utils.NumberUtils;
import net.renfei.service.BaseService;
import net.renfei.service.cms.CmsPostService;
import net.renfei.service.cms.dto.PostDTO;
import net.renfei.util.PageRankUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 文章服务
 *
 * @author renfei
 */
@Service
public class CmsPostServiceImpl extends BaseService implements CmsPostService {
    private static final Double DATE_WEIGHTED = 37.5D;
    private static final Double VIEW_WEIGHTED = 57.5D;
    private static final Double COMMENTHTED = 5D;
    private final TCmsPostsMapper postsMapper;
    private final TCmsPostTagMapper postTagMapper;

    protected CmsPostServiceImpl(SystemConfig systemConfig,
                                 TCmsPostsMapper postsMapper,
                                 TCmsPostTagMapper postTagMapper) {
        super(systemConfig);
        this.postsMapper = postsMapper;
        this.postTagMapper = postTagMapper;
    }

    /**
     * 获取全部文章列表
     *
     * @param pages 页码
     * @param rows  每页条数
     * @return 文章列表
     */
    @Override
    public ListData<PostDTO> getAllPost(String pages, String rows) {
        TCmsPostsExample example = new TCmsPostsExample();
        example.setOrderByClause("release_time DESC");
        example.createCriteria()
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andIsDeleteEqualTo(false);
        return selectByExample(example, pages, rows);
    }

    /**
     * 根据ID获取文章详情
     *
     * @param postId 文章ID
     * @return 文章
     */
    @Override
    public PostDTO getPostByPostId(long postId) {
        TCmsPostsExample example = new TCmsPostsExample();
        example.createCriteria()
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andIsDeleteEqualTo(false)
                .andIdEqualTo(postId);
        TCmsPostsWithBLOBs post = ListUtils.getOne(postsMapper.selectByExampleWithBLOBs(example));
        if (post == null) {
            return null;
        }
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        return postDTO;
    }

    /**
     * 根据分类获取文章列表
     *
     * @param categoryId 分类ID
     * @param pages      页码
     * @param rows       每页条数
     * @return 文章列表
     */
    @Override
    public ListData<PostDTO> getPostListByCategoryId(long categoryId, String pages, String rows) {
        TCmsPostsExample example = new TCmsPostsExample();
        example.setOrderByClause("release_time DESC");
        example.createCriteria()
                .andCategoryIdEqualTo(categoryId)
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andIsDeleteEqualTo(false);
        return selectByExample(example, pages, rows);
    }

    /**
     * 根据标签获取文章列表
     *
     * @param tagId 分类ID
     * @param pages 页码
     * @param rows  每页条数
     * @return 文章列表
     */
    @Override
    public ListData<PostDTO> getPostListByTagId(long tagId, String pages, String rows) {
        TCmsPostTagExample postTagExample = new TCmsPostTagExample();
        postTagExample.createCriteria()
                .andIsDeletedEqualTo(false)
                .andTagIdEqualTo(tagId);
        List<TCmsPostTag> postTags = postTagMapper.selectByExample(postTagExample);
        if (postTags == null || postTags.isEmpty()) {
            return null;
        }
        List<Long> postIds = new CopyOnWriteArrayList<>();
        postTags.forEach(postTag -> postIds.add(postTag.getTargetId()));
        TCmsPostsExample example = new TCmsPostsExample();
        example.setOrderByClause("release_time DESC");
        example.createCriteria()
                .andIdIn(postIds)
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andIsDeleteEqualTo(false);
        return selectByExample(example, pages, rows);
    }

    /**
     * 获取相关推荐
     *
     * @param post  文章种子
     * @param pages 页码
     * @param rows  每页条数
     * @return 文章列表
     */
    @Override
    public ListData<PostDTO> getRelatedPostList(PostDTO post, String pages, String rows) {
        // 1、先拿到文章的标签组
        TCmsPostTagExample postTagExample = new TCmsPostTagExample();
        postTagExample.createCriteria()
                .andIsDeletedEqualTo(false)
                .andTargetIdEqualTo(post.getId());
        List<TCmsPostTag> postTags = postTagMapper.selectByExample(postTagExample);
        TCmsPostsExample example = new TCmsPostsExample();
        if (postTags != null && !postTags.isEmpty()) {
            List<Long> ids = new CopyOnWriteArrayList<>();
            postTags.forEach(postTag -> ids.add(postTag.getTagId()));
            // 2、根据标签组拿到所有文章id
            postTagExample = new TCmsPostTagExample();
            postTagExample.createCriteria()
                    .andIsDeletedEqualTo(false)
                    .andTagIdIn(ids);
            postTags = postTagMapper.selectByExample(postTagExample);
            if (postTags != null && !postTags.isEmpty()) {
                List<Long> postIds = new CopyOnWriteArrayList<>();
                postTags.forEach(postTag -> postIds.add(postTag.getTargetId()));
                // 3、根据文章ID获得所有文章
                example.setDistinct(true);
                example.setOrderByClause("page_rank DESC,release_time DESC");
                example.createCriteria()
                        .andIdIn(postIds)
                        .andIsDeleteEqualTo(false)
                        .andIdNotEqualTo(post.getId())
                        .andReleaseTimeLessThanOrEqualTo(new Date());
                return selectByExample(example, pages, rows);
            }
        }
        // 兜底
        example.setDistinct(true);
        example.setOrderByClause("page_rank DESC,release_time DESC");
        example.createCriteria()
                .andIsDeleteEqualTo(false)
                .andIdNotEqualTo(post.getId())
                .andReleaseTimeLessThanOrEqualTo(new Date());
        return selectByExample(example, pages, rows);
    }

    /**
     * 获取最热文章Top10
     *
     * @return 文章列表
     */
    @Override
    public ListData<PostDTO> getHotPostTop10() {
        TCmsPostsExample example = new TCmsPostsExample();
        example.setOrderByClause("avg_views DESC,release_time DESC");
        example.createCriteria()
                .andIsDeleteEqualTo(false)
                .andReleaseTimeLessThanOrEqualTo(new Date());
        return selectByExample(example, "1", "10");
    }

    /**
     * 更新文章的权重
     */
    @Override
    public void updatePageRank() {
        TCmsPostsExample example = new TCmsPostsExample();
        example.createCriteria();
        List<TCmsPostsWithBLOBs> postsList = postsMapper.selectByExampleWithBLOBs(example);
        if (postsList == null || postsList.isEmpty()) {
            return;
        }
        for (TCmsPostsWithBLOBs post : postsList
        ) {
            setPageRank(post);
            postsMapper.updateByPrimaryKey(post);
        }
    }

    /**
     * 文章浏览量自增
     *
     * @param id 文章ID
     */
    @Async
    @Override
    public void updateView(long id) {
        TCmsPostsWithBLOBs post = postsMapper.selectByPrimaryKey(id);
        if (post != null) {
            post.setViews(post.getViews() + 1);
            postsMapper.updateByPrimaryKeySelective(post);
        }
    }

    private ListData<PostDTO> selectByExample(TCmsPostsExample example, String pages, String rows) {
        Page<TCmsPostsWithBLOBs> page =
                PageHelper.startPage(NumberUtils.parseInt(pages, 1), NumberUtils.parseInt(rows, 10));
        postsMapper.selectByExampleWithBLOBs(example);
        ListData<PostDTO> postListData = new ListData<>();
        postListData.setTotal(page.getTotal());
        postListData.setShowRows(page.getPageSize());
        postListData.setTotalPages(page.getPages());
        postListData.setCurrentPage(page.getPageNum());
        List<PostDTO> postList = new CopyOnWriteArrayList<>();
        if (page.getResult() != null && !page.getResult().isEmpty()) {
            for (TCmsPostsWithBLOBs postsWithBlobs : page.getResult()
            ) {
                PostDTO postDTO = new PostDTO();
                BeanUtils.copyProperties(postsWithBlobs, postDTO);
                postList.add(postDTO);
            }
        }
        postListData.setData(postList);
        return postListData;
    }

    private void setPageRank(TCmsPostsWithBLOBs postsDO) {
        PageRankUtil pageRankUtil = new PageRankUtil();
        postsDO.setPageRank(pageRankUtil.getPageRank(
                postsDO.getReleaseTime(),
                postsDO.getViews(),
                0L,
                DATE_WEIGHTED, VIEW_WEIGHTED, COMMENTHTED
        ));
        postsDO.setAvgViews(pageRankUtil.getAvgViews(
                postsDO.getReleaseTime(),
                postsDO.getViews()
        ));
        postsDO.setAvgComment(pageRankUtil.getAvgComments(
                postsDO.getReleaseTime(),
                0L
        ));
        postsDO.setPageRankUpdateTime(new Date());
    }
}
