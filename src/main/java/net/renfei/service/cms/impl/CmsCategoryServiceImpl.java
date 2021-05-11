package net.renfei.service.cms.impl;

import net.renfei.config.SystemConfig;
import net.renfei.exception.BusinessException;
import net.renfei.repository.dao.cms.TCmsCategoryMapper;
import net.renfei.repository.dao.cms.TCmsPostsMapper;
import net.renfei.repository.dao.cms.model.TCmsCategory;
import net.renfei.repository.dao.cms.model.TCmsCategoryExample;
import net.renfei.repository.dao.cms.model.TCmsPosts;
import net.renfei.repository.dao.cms.model.TCmsPostsExample;
import net.renfei.sdk.utils.ListUtils;
import net.renfei.service.BaseService;
import net.renfei.service.cms.CmsCategoryService;
import net.renfei.service.cms.dto.CategoryDTO;
import net.renfei.web.api.cms.ao.CategoryAO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CMS分类服务
 *
 * @author renfei
 */
@Service
public class CmsCategoryServiceImpl extends BaseService implements CmsCategoryService {
    private final TCmsPostsMapper postsMapper;
    private final TCmsCategoryMapper categoryMapper;

    protected CmsCategoryServiceImpl(SystemConfig systemConfig,
                                     TCmsPostsMapper postsMapper,
                                     TCmsCategoryMapper categoryMapper) {
        super(systemConfig);
        this.postsMapper = postsMapper;
        this.categoryMapper = categoryMapper;
    }

    /**
     * 添加分类
     *
     * @param category 分类请求数据
     */
    @Override
    public void addCategory(CategoryAO category) {
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(category.getEnName())) {
            throw new BusinessException("[EnName]不能为空");
        }
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(category.getZhName())) {
            throw new BusinessException("[ZhName]不能为空");
        }
        TCmsCategoryExample example = new TCmsCategoryExample();
        // 检查重名
        example.createCriteria()
                .andIsDeletedEqualTo(false)
                .andEnNameEqualTo(category.getEnName());
        TCmsCategory cmsCategory = ListUtils.getOne(categoryMapper.selectByExample(example));
        if (cmsCategory != null) {
            throw new BusinessException("[EnName]已存在，请换一个");
        }
        cmsCategory = new TCmsCategory();
        cmsCategory.setIsDeleted(false);
        cmsCategory.setCreateTime(new Date());
        cmsCategory.setEnName(category.getEnName());
        cmsCategory.setZhName(category.getZhName());
        categoryMapper.insertSelective(cmsCategory);
    }

    /**
     * 修改分类
     *
     * @param category 分类请求数据
     */
    @Override
    public void updateCategory(CategoryAO category) {
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(category.getEnName())) {
            throw new BusinessException("[EnName]不能为空");
        }
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(category.getZhName())) {
            throw new BusinessException("[ZhName]不能为空");
        }
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(category.getId())) {
            throw new BusinessException("[id]不能为空");
        }
        TCmsCategory cmsCategory = categoryMapper.selectByPrimaryKey(category.getId());
        if (cmsCategory == null) {
            throw new BusinessException("根据[id]未查询到数据，请新增");
        }
        if (!cmsCategory.getEnName().equals(category.getEnName())) {
            // EnName发生了变更，检查重名
            TCmsCategoryExample example = new TCmsCategoryExample();
            example.createCriteria()
                    .andIsDeletedEqualTo(false)
                    .andEnNameEqualTo(category.getEnName());
            if (ListUtils.getOne(categoryMapper.selectByExample(example)) != null) {
                throw new BusinessException("[EnName]已存在，请换一个");
            }
        }
        cmsCategory.setUpdateTime(new Date());
        cmsCategory.setEnName(category.getEnName());
        cmsCategory.setZhName(category.getZhName());
        categoryMapper.updateByPrimaryKey(cmsCategory);
    }

    /**
     * 删除分类
     *
     * @param id 分类ID
     */
    @Override
    public void deleteCategory(long id) {
        TCmsCategory cmsCategory = categoryMapper.selectByPrimaryKey(id);
        if (cmsCategory == null) {
            throw new BusinessException("根据[id]未查询到数据，无需删除");
        }
        // 检查分类下是否有文章
        TCmsPostsExample example = new TCmsPostsExample();
        example.createCriteria()
                .andIsDeleteEqualTo(false)
                .andCategoryIdEqualTo(id);
        List<TCmsPosts> posts = postsMapper.selectByExample(example);
        if (posts != null && !posts.isEmpty()) {
            throw new BusinessException("该分类下还有文章没有删除，请先删除文章！");
        }
        cmsCategory.setIsDeleted(true);
        categoryMapper.updateByPrimaryKey(cmsCategory);
    }

    /**
     * 获取所有分类列表
     *
     * @return CMS分类
     */
    @Override
    public List<CategoryDTO> getCategoryList() {
        TCmsCategoryExample example = new TCmsCategoryExample();
        example.createCriteria();
        List<TCmsCategory> cmsCategories = categoryMapper.selectByExample(example);
        List<CategoryDTO> categoryList = new CopyOnWriteArrayList<>();
        if (cmsCategories == null || cmsCategories.isEmpty()) {
            return null;
        }
        for (TCmsCategory cmsCategory : cmsCategories
        ) {
            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtils.copyProperties(cmsCategory, categoryDTO);
            categoryList.add(categoryDTO);
        }
        return categoryList;
    }

    /**
     * 根据分类ID获取分类
     *
     * @param id 分类ID
     * @return CMS分类
     */
    @Override
    public CategoryDTO getCategoryById(long id) {
        TCmsCategoryExample example = new TCmsCategoryExample();
        example.createCriteria()
                .andIsDeletedEqualTo(false)
                .andIdEqualTo(id);
        TCmsCategory cmsCategory = ListUtils.getOne(categoryMapper.selectByExampleWithBLOBs(example));
        if (cmsCategory == null) {
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(cmsCategory, categoryDTO);
        return categoryDTO;
    }

    /**
     * 根据英文名获取分类
     *
     * @param enName 分类英文名
     * @return CMS分类
     */
    @Override
    public CategoryDTO getCategoryByEnName(String enName) {
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(enName)) {
            return null;
        }
        TCmsCategoryExample example = new TCmsCategoryExample();
        example.createCriteria()
                .andEnNameEqualTo(enName);
        TCmsCategory cmsCategory = ListUtils.getOne(categoryMapper.selectByExample(example));
        if (cmsCategory == null) {
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(cmsCategory, categoryDTO);
        return categoryDTO;
    }
}
