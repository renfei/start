package net.renfei.service.cms;

import net.renfei.ApplicationTests;
import net.renfei.web.api.cms.ao.CategoryAO;
import net.renfei.web.api.cms.ao.TagAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * CMS类测试
 *
 * @author renfei
 */
@Transactional
public class CmsTests extends ApplicationTests {
    @Autowired
    private CmsTagService tagService;
    @Autowired
    private CmsPostService postService;
    @Autowired
    private CmsCategoryService categoryService;
    @Autowired
    private CmsCommentsService cmsCommentsService;

    @Test
    public void addCategory() {
        CategoryAO category = new CategoryAO();
        category.setEnName("test");
        category.setZhName("测试分类");
        categoryService.addCategory(category);
    }

    @Test
    public void addTag() {
        TagAO tag = new TagAO();
        tag.setEnName("test");
        tag.setZhName("测试");
        tagService.addTag(tag);
    }
}
