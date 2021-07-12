package net.renfei.service.cms.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.renfei.config.SystemConfig;
import net.renfei.exception.BusinessException;
import net.renfei.repository.dao.cms.TCmsCategoryMapper;
import net.renfei.repository.dao.cms.TCmsPostTagMapper;
import net.renfei.repository.dao.cms.TCmsPostsMapper;
import net.renfei.repository.dao.cms.model.*;
import net.renfei.sdk.entity.ListData;
import net.renfei.sdk.utils.DateUtils;
import net.renfei.sdk.utils.ListUtils;
import net.renfei.sdk.utils.NumberUtils;
import net.renfei.security.ConfidentialRankEnum;
import net.renfei.service.BaseService;
import net.renfei.service.cms.CmsPostService;
import net.renfei.service.cms.dto.PostDTO;
import net.renfei.service.start.FileUploadService;
import net.renfei.service.start.FileUploadServiceFactory;
import net.renfei.service.start.dto.UserDTO;
import net.renfei.util.PageRankUtil;
import net.renfei.web.api.cms.ao.PostAO;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final TCmsCategoryMapper categoryMapper;
    private final FileUploadServiceFactory fileUploadServiceFactory;

    protected CmsPostServiceImpl(SystemConfig systemConfig,
                                 TCmsPostsMapper postsMapper,
                                 TCmsPostTagMapper postTagMapper,
                                 TCmsCategoryMapper categoryMapper,
                                 FileUploadServiceFactory fileUploadServiceFactory) {
        super(systemConfig);
        this.postsMapper = postsMapper;
        this.postTagMapper = postTagMapper;
        this.categoryMapper = categoryMapper;
        this.fileUploadServiceFactory = fileUploadServiceFactory;
    }

    /**
     * 添加文章
     *
     * @param post 文章对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPost(PostAO post, UserDTO user) {
        checkPost(post);
        checkMaxConfidentialRank(post.getConfidentialRank());
        checkConfidentialRank(user.getConfidentialRank(), post.getConfidentialRank());
        TCmsPostsWithBLOBs postDo = new TCmsPostsWithBLOBs();
        BeanUtils.copyProperties(post, postDo);
        postDo.setConfidentialRank(post.getConfidentialRank().getRank());
        // 检查发布的分类密级
        TCmsCategory category = categoryMapper.selectByPrimaryKey(post.getCategoryId());
        if (category == null) {
            throw new BusinessException("文章发布的目标目录不存在");
        } else if (category.getConfidentialRank() < post.getConfidentialRank().getRank()) {
            throw new BusinessException("文章密级高于发布的目标分类密级，操作被阻止");
        }
        if (post.getFeaturedImageFile() != null) {
            FileUploadService fileUploadService = fileUploadServiceFactory.getFileUploadService();
            // 上传特色图像
            postDo.setFeaturedImage(fileUploadService.uploadFile(post.getFeaturedImageFile(),
                    systemConfig.getFileUploadPath() + "upload/" + DateUtils.getYear() + "/", null));
        }
        // 将 ueditor 的代码格式替换成 highlightjs 的
        postDo.setContent(
                post.getContent()
                        .replace("<pre class=\"brush:bash;toolbar:false\">", "<pre><code class=\"bash\">")
                        .replace("<pre class=\"brush:java;toolbar:false\">", "<pre><code class=\"java\">")
                        .replace("<pre class=\"brush:scala;toolbar:false\">", "<pre><code class=\"scala\">")
                        .replace("<pre class=\"brush:xml;toolbar:false\">", "<pre><code class=\"xml\">")
                        .replace("<pre class=\"brush:sql;toolbar:false\">", "<pre><code class=\"sql\">")
                        .replace("<pre class=\"brush:js;toolbar:false\">", "<pre><code class=\"js\">")
                        .replace("<pre class=\"brush:php;toolbar:false\">", "<pre><code class=\"php\">")
                        .replace("<pre class=\"brush:html;toolbar:false\">", "<pre><code class=\"html\">")
                        .replace("<pre class=\"brush:css;toolbar:false\">", "<pre><code class=\"css\">")
                        .replace("<pre class=\"brush:c#;toolbar:false\">", "<pre><code class=\"csharp\">")
                        .replace("<pre class=\"brush:python;toolbar:false\">", "<pre><code class=\"python\">")
                        .replace("<pre class=\"brush:ruby;toolbar:false\">", "<pre><code class=\"ruby\">")
                        .replace("<pre class=\"brush:vb;toolbar:false\">", "<pre><code class=\"vb\">")
                        .replace("<pre class=\"brush:cpp;toolbar:false\">", "<pre><code class=\"cpp\">")
                        .replace("</pre>", "</code></pre>")
        );
        postDo.setAddTime(new Date());
        if (postDo.getReleaseTime() == null) {
            postDo.setReleaseTime(new Date());
        }
        postDo.setIsDelete(false);
        postDo.setViews(0L);
        postDo.setThumbsUp(0L);
        postDo.setThumbsDown(0L);
        postsMapper.insertSelective(postDo);
        if (post.getTags() != null && !post.getTags().isEmpty()) {
            for (Long tagId : post.getTags()
            ) {
                TCmsPostTag postTag = new TCmsPostTag();
                postTag.setIsDeleted(false);
                postTag.setTagId(tagId);
                postTag.setTargetId(postDo.getId());
                postTag.setCreateTime(new Date());
                postTagMapper.insertSelective(postTag);
            }
        }
    }

    /**
     * 更新文章
     *
     * @param post 文章对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePost(PostAO post, UserDTO user) {
        checkPost(post);
        if (post.getId() == null) {
            throw new BusinessException("文章ID不能为空");
        }
        checkMaxConfidentialRank(post.getConfidentialRank());
        TCmsPostsWithBLOBs oldPost = postsMapper.selectByPrimaryKey(post.getId());
        if (oldPost == null) {
            throw new BusinessException("根据文章ID未找到文章数据");
        }
        checkConfidentialRank(user.getConfidentialRank(), ConfidentialRankEnum.parse(oldPost.getConfidentialRank()));
        checkConfidentialRank(user.getConfidentialRank(), post.getConfidentialRank());
        // 检查发布的分类密级
        TCmsCategory category = categoryMapper.selectByPrimaryKey(post.getCategoryId());
        if (category == null) {
            throw new BusinessException("文章发布的目标目录不存在");
        } else if (category.getConfidentialRank() < post.getConfidentialRank().getRank()) {
            throw new BusinessException("文章密级高于发布的目标分类密级，操作被阻止");
        }
        if (post.getFeaturedImageFile() != null) {
            FileUploadService fileUploadService = fileUploadServiceFactory.getFileUploadService();
            // 上传特色图像
            oldPost.setFeaturedImage(fileUploadService.uploadFile(post.getFeaturedImageFile(),
                    systemConfig.getFileUploadPath() + "upload/" + DateUtils.getYear() + "/", null));
        }
        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());
        oldPost.setDescribes(post.getDescribes());
        oldPost.setKeyword(post.getKeyword());
        oldPost.setSourceName(post.getSourceName());
        oldPost.setSourceUrl(post.getSourceUrl());
        oldPost.setConfidentialRank(post.getConfidentialRank().getRank());
        // 将 ueditor 的代码格式替换成 highlightjs 的
        oldPost.setContent(
                post.getContent()
                        .replace("<pre class=\"brush:bash;toolbar:false\">", "<pre><code class=\"bash\">")
                        .replace("<pre class=\"brush:java;toolbar:false\">", "<pre><code class=\"java\">")
                        .replace("<pre class=\"brush:scala;toolbar:false\">", "<pre><code class=\"scala\">")
                        .replace("<pre class=\"brush:xml;toolbar:false\">", "<pre><code class=\"xml\">")
                        .replace("<pre class=\"brush:sql;toolbar:false\">", "<pre><code class=\"sql\">")
                        .replace("<pre class=\"brush:js;toolbar:false\">", "<pre><code class=\"js\">")
                        .replace("<pre class=\"brush:php;toolbar:false\">", "<pre><code class=\"php\">")
                        .replace("<pre class=\"brush:html;toolbar:false\">", "<pre><code class=\"html\">")
                        .replace("<pre class=\"brush:css;toolbar:false\">", "<pre><code class=\"css\">")
                        .replace("<pre class=\"brush:c#;toolbar:false\">", "<pre><code class=\"csharp\">")
                        .replace("<pre class=\"brush:python;toolbar:false\">", "<pre><code class=\"python\">")
                        .replace("<pre class=\"brush:ruby;toolbar:false\">", "<pre><code class=\"ruby\">")
                        .replace("<pre class=\"brush:vb;toolbar:false\">", "<pre><code class=\"vb\">")
                        .replace("<pre class=\"brush:cpp;toolbar:false\">", "<pre><code class=\"cpp\">")
                        .replace("</pre>", "</code></pre>")
        );
        postsMapper.updateByPrimaryKeyWithBLOBs(oldPost);
        // 先删除
        TCmsPostTagExample postTagExample = new TCmsPostTagExample();
        postTagExample.createCriteria().andTargetIdEqualTo(oldPost.getId());
        TCmsPostTag postTag = new TCmsPostTag();
        postTag.setIsDeleted(true);
        postTag.setUpdateTime(new Date());
        postTagMapper.updateByExampleSelective(postTag, postTagExample);
        // 再插入
        if (post.getTags() != null && !post.getTags().isEmpty()) {
            for (Long tagId : post.getTags()
            ) {
                TCmsPostTag tag = new TCmsPostTag();
                tag.setIsDeleted(false);
                tag.setTagId(tagId);
                tag.setTargetId(oldPost.getId());
                tag.setCreateTime(new Date());
                postTagMapper.insertSelective(tag);
            }
        }
    }

    @Override
    public void deletePost(long id, UserDTO user) {
        TCmsPostsWithBLOBs oldPost = postsMapper.selectByPrimaryKey(id);
        if (oldPost == null) {
            throw new BusinessException("根据文章ID未找到文章数据");
        }
        checkConfidentialRank(user.getConfidentialRank(), ConfidentialRankEnum.parse(oldPost.getConfidentialRank()));
        oldPost.setIsDelete(true);
        postsMapper.updateByPrimaryKeyWithBLOBs(oldPost);
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
        return getAllPost(null, pages, rows);
    }

    /**
     * 根据条件获取文章列表
     *
     * @param user             登陆的用户
     * @param category         分类
     * @param confidentialRank 密级
     * @param startDate        开始时间
     * @param endDate          结束时间
     * @param pages            页码
     * @param rows             每页行数
     * @return
     */
    @Override
    public ListData<PostDTO> getPostList(UserDTO user, Long category, ConfidentialRankEnum confidentialRank,
                                         Date startDate, Date endDate, String pages, String rows) {
        TCmsPostsExample example = new TCmsPostsExample();
        example.setOrderByClause("release_time DESC");
        TCmsPostsExample.Criteria criteria = example.createCriteria()
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andIsDeleteEqualTo(false);
        if (user != null) {
            criteria.andConfidentialRankLessThanOrEqualTo(user.getConfidentialRank().getRank());
        } else {
            criteria.andConfidentialRankEqualTo(0);
        }
        if (category != null) {
            criteria.andCategoryIdEqualTo(category);
        }
        if (confidentialRank != null) {
            assert user != null;
            checkConfidentialRank(user.getConfidentialRank(), confidentialRank);
            criteria.andConfidentialRankEqualTo(confidentialRank.getRank());
        }
        if (startDate != null) {
            criteria.andReleaseTimeGreaterThanOrEqualTo(startDate);
        }
        if (endDate != null) {
            criteria.andReleaseTimeLessThanOrEqualTo(endDate);
        }
        return selectByExample(example, pages, rows);
    }

    /**
     * 获取全部文章列表
     *
     * @param user  登陆用户
     * @param pages 页码
     * @param rows  每页条数
     * @return 文章列表
     */
    @Override
    public ListData<PostDTO> getAllPost(UserDTO user, String pages, String rows) {
        TCmsPostsExample example = new TCmsPostsExample();
        example.setOrderByClause("release_time DESC");
        TCmsPostsExample.Criteria criteria = example.createCriteria()
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andIsDeleteEqualTo(false);
        if (user != null) {
            criteria.andConfidentialRankLessThanOrEqualTo(user.getConfidentialRank().getRank());
        } else {
            criteria.andConfidentialRankEqualTo(0);
        }
        return getPostList(user, null, null, null, null, pages, rows);
    }

    /**
     * 根据ID获取文章详情
     *
     * @param postId 文章ID
     * @return 文章
     */
    @Override
    public PostDTO getPostByPostId(long postId) {
        return getPostByPostId(null, postId);
    }

    /**
     * 根据ID获取文章详情
     *
     * @param user   登陆用户
     * @param postId 文章ID
     * @return 文章
     */
    @Override
    public PostDTO getPostByPostId(UserDTO user, long postId) {
        TCmsPostsExample example = new TCmsPostsExample();
        TCmsPostsExample.Criteria criteria = example.createCriteria()
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andIsDeleteEqualTo(false)
                .andIdEqualTo(postId);
        if (user != null) {
            criteria.andConfidentialRankLessThanOrEqualTo(user.getConfidentialRank().getRank());
        } else {
            criteria.andConfidentialRankEqualTo(0);
        }
        TCmsPostsWithBLOBs post = ListUtils.getOne(postsMapper.selectByExampleWithBLOBs(example));
        if (post == null) {
            return null;
        }
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        postDTO.setConfidentialRank(ConfidentialRankEnum.parse(post.getConfidentialRank()));
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
        return getPostListByCategoryId(null, categoryId, pages, rows);
    }

    /**
     * 根据分类获取文章列表
     *
     * @param user       登陆的用户
     * @param categoryId 分类ID
     * @param pages      页码
     * @param rows       每页条数
     * @return 文章列表
     */
    @Override
    public ListData<PostDTO> getPostListByCategoryId(UserDTO user, long categoryId, String pages, String rows) {
        TCmsPostsExample example = new TCmsPostsExample();
        example.setOrderByClause("release_time DESC");
        TCmsPostsExample.Criteria criteria = example.createCriteria()
                .andCategoryIdEqualTo(categoryId)
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andIsDeleteEqualTo(false);
        if (user != null) {
            criteria.andConfidentialRankLessThanOrEqualTo(user.getConfidentialRank().getRank());
        } else {
            criteria.andConfidentialRankEqualTo(0);
        }
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
     * 根据标签获取文章列表
     *
     * @param user  登陆用户
     * @param tagId 分类ID
     * @param pages 页码
     * @param rows  每页条数
     * @return 文章列表
     */
    @Override
    public ListData<PostDTO> getPostListByTagId(UserDTO user, long tagId, String pages, String rows) {
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
        TCmsPostsExample.Criteria criteria = example.createCriteria()
                .andIdIn(postIds)
                .andReleaseTimeLessThanOrEqualTo(new Date())
                .andIsDeleteEqualTo(false);
        if (user != null) {
            criteria.andConfidentialRankLessThanOrEqualTo(user.getConfidentialRank().getRank());
        } else {
            criteria.andConfidentialRankEqualTo(0);
        }
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
        return getRelatedPostList(null, post, pages, rows);
    }

    /**
     * 获取相关推荐
     *
     * @param user  登陆用户
     * @param post  文章种子
     * @param pages 页码
     * @param rows  每页条数
     * @return 文章列表
     */
    @Override
    public ListData<PostDTO> getRelatedPostList(UserDTO user, PostDTO post, String pages, String rows) {
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
                TCmsPostsExample.Criteria criteria = example.createCriteria()
                        .andIdIn(postIds)
                        .andIsDeleteEqualTo(false)
                        .andIdNotEqualTo(post.getId())
                        .andReleaseTimeLessThanOrEqualTo(new Date());
                if (user != null) {
                    criteria.andConfidentialRankLessThanOrEqualTo(user.getConfidentialRank().getRank());
                } else {
                    criteria.andConfidentialRankEqualTo(0);
                }
                return selectByExample(example, pages, rows);
            }
        }
        // 兜底
        example.setDistinct(true);
        example.setOrderByClause("page_rank DESC,release_time DESC");
        TCmsPostsExample.Criteria criteria = example.createCriteria()
                .andIsDeleteEqualTo(false)
                .andIdNotEqualTo(post.getId())
                .andReleaseTimeLessThanOrEqualTo(new Date());
        if (user != null) {
            criteria.andConfidentialRankLessThanOrEqualTo(user.getConfidentialRank().getRank());
        } else {
            criteria.andConfidentialRankEqualTo(0);
        }
        return selectByExample(example, pages, rows);
    }

    /**
     * 获取最热文章Top10
     *
     * @return 文章列表
     */
    @Override
    public ListData<PostDTO> getHotPostTop10() {
        return getHotPostTop10(null);
    }

    /**
     * 获取最热文章Top10
     *
     * @return 文章列表
     */
    @Override
    public ListData<PostDTO> getHotPostTop10(UserDTO user) {
        TCmsPostsExample example = new TCmsPostsExample();
        example.setOrderByClause("avg_views DESC,release_time DESC");
        TCmsPostsExample.Criteria criteria = example.createCriteria()
                .andIsDeleteEqualTo(false)
                .andReleaseTimeLessThanOrEqualTo(new Date());
        if (user != null) {
            criteria.andConfidentialRankLessThanOrEqualTo(user.getConfidentialRank().getRank());
        } else {
            criteria.andConfidentialRankEqualTo(0);
        }
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
                postDTO.setConfidentialRank(ConfidentialRankEnum.parse(postsWithBlobs.getConfidentialRank()));
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

    private void checkPost(PostAO post) {
        if (post == null) {
            throw new BusinessException("文章数据不能为空");
        }
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(post.getTitle())) {
            throw new BusinessException("文章标题不能为空");
        }
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(post.getContent())) {
            throw new BusinessException("文章内容不能为空");
        }
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(post.getCategoryId())) {
            throw new BusinessException("文章分类不能为空");
        }
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(post.getIsOriginal())) {
            throw new BusinessException("文章是否原创不能为空");
        }
        if (post.getConfidentialRank() == null) {
            throw new BusinessException("文章密级不能为空");
        }
    }
}
