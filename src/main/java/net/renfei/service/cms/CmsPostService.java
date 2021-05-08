package net.renfei.service.cms;

import net.renfei.sdk.entity.ListData;
import net.renfei.service.cms.dto.PostDTO;

/**
 * 文章服务
 *
 * @author renfei
 */
public interface CmsPostService {
    /**
     * 获取全部文章列表
     *
     * @param pages 页码
     * @param rows  每页条数
     * @return 文章列表
     */
    ListData<PostDTO> getAllPost(String pages, String rows);

    /**
     * 根据ID获取文章详情
     *
     * @param postId 文章ID
     * @return 文章
     */
    PostDTO getPostByPostId(long postId);

    /**
     * 根据分类获取文章列表
     *
     * @param categoryId 分类ID
     * @param pages      页码
     * @param rows       每页条数
     * @return 文章列表
     */
    ListData<PostDTO> getPostListByCategoryId(long categoryId, String pages, String rows);

    /**
     * 根据标签获取文章列表
     *
     * @param tagId 分类ID
     * @param pages 页码
     * @param rows  每页条数
     * @return 文章列表
     */
    ListData<PostDTO> getPostListByTagId(long tagId, String pages, String rows);

    /**
     * 获取相关推荐
     *
     * @param post  文章种子
     * @param pages 页码
     * @param rows  每页条数
     * @return 文章列表
     */
    ListData<PostDTO> getRelatedPostList(PostDTO post, String pages, String rows);

    /**
     * 获取最热文章Top10
     *
     * @return 文章列表
     */
    ListData<PostDTO> getHotPostTop10();

    /**
     * 更新文章的权重
     */
    void updatePageRank();

    /**
     * 文章浏览量自增
     *
     * @param id 文章ID
     */
    void updateView(long id);
}
