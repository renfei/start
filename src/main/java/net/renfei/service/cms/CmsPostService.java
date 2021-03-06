package net.renfei.service.cms;

import net.renfei.sdk.entity.ListData;
import net.renfei.security.ConfidentialRankEnum;
import net.renfei.service.cms.dto.PostDTO;
import net.renfei.service.start.dto.UserDTO;
import net.renfei.web.api.cms.ao.PostAO;

import java.util.Date;

/**
 * 文章服务
 *
 * @author renfei
 */
public interface CmsPostService {
    /**
     * 添加文章
     *
     * @param post 文章对象
     */
    void addPost(PostAO post, UserDTO user);

    /**
     * 更新文章
     *
     * @param post 文章对象
     */
    void updatePost(PostAO post, UserDTO user);

    /**
     * 删除文章
     *
     * @param id 文章ID
     */
    void deletePost(long id, UserDTO user);

    /**
     * 获取全部文章列表
     *
     * @param pages 页码
     * @param rows  每页条数
     * @return 文章列表
     */
    ListData<PostDTO> getAllPost(String pages, String rows);

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
    ListData<PostDTO> getPostList(UserDTO user, Long category, ConfidentialRankEnum confidentialRank,
                                  Date startDate, Date endDate, String pages, String rows);

    /**
     * 获取全部文章列表
     *
     * @param user  登陆用户
     * @param pages 页码
     * @param rows  每页条数
     * @return 文章列表
     */
    ListData<PostDTO> getAllPost(UserDTO user, String pages, String rows);

    /**
     * 根据ID获取文章详情
     *
     * @param postId 文章ID
     * @return 文章
     */
    PostDTO getPostByPostId(long postId);

    /**
     * 根据ID获取文章详情
     *
     * @param user   登陆用户
     * @param postId 文章ID
     * @return 文章
     */
    PostDTO getPostByPostId(UserDTO user, long postId);

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
     * 根据分类获取文章列表
     *
     * @param user       登陆的用户
     * @param categoryId 分类ID
     * @param pages      页码
     * @param rows       每页条数
     * @return 文章列表
     */
    ListData<PostDTO> getPostListByCategoryId(UserDTO user, long categoryId, String pages, String rows);

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
     * 根据标签获取文章列表
     *
     * @param user  登陆用户
     * @param tagId 分类ID
     * @param pages 页码
     * @param rows  每页条数
     * @return 文章列表
     */
    ListData<PostDTO> getPostListByTagId(UserDTO user, long tagId, String pages, String rows);

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
     * 获取相关推荐
     *
     * @param user  登陆用户
     * @param post  文章种子
     * @param pages 页码
     * @param rows  每页条数
     * @return 文章列表
     */
    ListData<PostDTO> getRelatedPostList(UserDTO user, PostDTO post, String pages, String rows);

    /**
     * 获取最热文章Top10
     *
     * @return 文章列表
     */
    ListData<PostDTO> getHotPostTop10();

    /**
     * 获取最热文章Top10
     *
     * @param user 登陆用户
     * @return 文章列表
     */
    ListData<PostDTO> getHotPostTop10(UserDTO user);

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
