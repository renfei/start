package net.renfei.service.cms;

import net.renfei.service.cms.dto.TagDTO;
import net.renfei.web.api.cms.ao.TagAO;

import java.util.List;

/**
 * CMS标签服务
 *
 * @author renfei
 */
public interface CmsTagService {
    /**
     * 添加标签
     *
     * @param tag 标签
     */
    void addTag(TagAO tag);

    /**
     * 更新标签
     *
     * @param tag 标签
     */
    void updateTag(TagAO tag);

    /**
     * 删除标签
     *
     * @param id 标签ID
     */
    void deleteTag(long id);

    /**
     * 获取全部标签
     *
     * @return 标签列表
     */
    List<TagDTO> getTagList();

    /**
     * 根据ID获取标签
     *
     * @param id 标签ID
     * @return 标签
     */
    TagDTO getTagById(long id);

    /**
     * 根据EnName获取标签
     *
     * @param enName 英文名
     * @return 标签
     */
    TagDTO getTagByEnName(String enName);
}
