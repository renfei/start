package net.renfei.service.cms;

import net.renfei.service.cms.dto.CategoryDTO;
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
     */
    void addCategory(CategoryAO category);

    /**
     * 修改分类
     *
     * @param category 分类请求数据
     */
    void updateCategory(CategoryAO category);

    /**
     * 删除分类
     *
     * @param id 分类ID
     */
    void deleteCategory(long id);

    /**
     * 获取所有分类列表
     *
     * @return CMS分类
     */
    List<CategoryDTO> getCategoryList();

    /**
     * 根据分类ID获取分类
     *
     * @param id 分类ID
     * @return CMS分类
     */
    CategoryDTO getCategoryById(long id);

    /**
     * 根据英文名获取分类
     *
     * @param enName 分类英文名
     * @return CMS分类
     */
    CategoryDTO getCategoryByEnName(String enName);
}
