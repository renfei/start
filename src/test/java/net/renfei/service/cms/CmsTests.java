package net.renfei.service.cms;

import net.renfei.ApplicationTests;
import net.renfei.service.cms.dto.CategoryDTO;
import net.renfei.service.cms.dto.PostDTO;
import net.renfei.service.cms.dto.TagDTO;
import net.renfei.web.api.cms.ao.CategoryAO;
import net.renfei.web.api.cms.ao.PostAO;
import net.renfei.web.api.cms.ao.TagAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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

    public void addCategory() {
        CategoryAO category = new CategoryAO();
        category.setEnName("test");
        category.setZhName("测试分类");
        categoryService.addCategory(category);
    }

    public void addTag() {
        TagAO tag = new TagAO();
        tag.setEnName("test");
        tag.setZhName("测试");
        tagService.addTag(tag);
    }

    @Test
    public void addPost() {
        addCategory();
        addTag();
        CategoryDTO categoryDTO = categoryService.getCategoryByEnName("test");
        TagDTO tag = tagService.getTagByEnName("test");
        PostAO post = new PostAO();
        post.setTitle("测试文章");
        post.setContent("测试文章内容");
        post.setSourceName("Tester");
        post.setIsOriginal(true);
        post.setCategoryId(categoryDTO.getId());
        post.setTags(new ArrayList<Long>() {{
            this.add(tag.getId());
        }});
        postService.addPost(post);
        postService.updateView(1);
        post.setId(1L);
        post.setTitle("2222222");
        postService.updatePost(post);
        postService.getAllPost("1", "10");
        PostDTO postDTO = postService.getPostByPostId(1);
        postService.getPostListByCategoryId(1, "1", "10");
        postService.getPostListByTagId(tag.getId(), "1", "10");
        postService.getRelatedPostList(postDTO, "1", "10");
        postService.getHotPostTop10();
        postService.updateView(1);
        postService.deletePost(1);
        tagService.deleteTag(tag.getId());
        categoryService.deleteCategory(categoryDTO.getId());
    }
}
