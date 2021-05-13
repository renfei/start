package net.renfei.service.cms;

import net.renfei.service.cms.dto.CategoryDTO;
import net.renfei.service.start.dto.UserDTO;
import net.renfei.web.api.cms.ao.CategoryAO;

import java.util.List;

/**
 * CMS分类服务
 *
 * @author renfei
 */
public interface CmsCategoryService {

    /**
     * 添加分类
     *
     * @param category 分类请求数据
     * @param user     登陆用户
     */
    void addCategory(CategoryAO category, UserDTO user);

    /**
     * 修改分类
     *
     * @param category 分类请求数据
     * @param user     登陆用户
     */
    void updateCategory(CategoryAO category, UserDTO user);

    /**
     * 删除分类
     *
     * @param id   分类ID
     * @param user 登陆用户
     */
    void deleteCategory(long id, UserDTO user);

    /**
     * 获取所有分类列表
     *
     * @return CMS分类
     */
    List<CategoryDTO> getCategoryList();

    /**
     * 获取所有分类列表
     *
     * @param user 登陆用户
     * @return CMS分类
     */
    List<CategoryDTO> getCategoryList(UserDTO user);

    /**
     * 根据分类ID获取分类
     *
     * @param id 分类ID
     * @return CMS分类
     */
    CategoryDTO getCategoryById(long id);

    /**
     * 根据分类ID获取分类
     *
     * @param id   分类ID
     * @param user 登陆用户
     * @return CMS分类
     */
    CategoryDTO getCategoryById(long id, UserDTO user);

    /**
     * 根据英文名获取分类
     *
     * @param enName 分类英文名
     * @return CMS分类
     */
    CategoryDTO getCategoryByEnName(String enName);

    /**
     * 根据英文名获取分类
     *
     * @param enName 分类英文名
     * @param user   登录用户
     * @return CMS分类
     */
    CategoryDTO getCategoryByEnName(String enName, UserDTO user);
}
