package net.renfei.service.cms;

import net.renfei.ApplicationTests;
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
}
