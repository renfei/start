package net.renfei.service.cms.impl;

import net.renfei.config.SystemConfig;
import net.renfei.exception.BusinessException;
import net.renfei.repository.dao.cms.TCmsPostTagMapper;
import net.renfei.repository.dao.cms.TCmsTagMapper;
import net.renfei.repository.dao.cms.model.TCmsPostTag;
import net.renfei.repository.dao.cms.model.TCmsPostTagExample;
import net.renfei.repository.dao.cms.model.TCmsTag;
import net.renfei.repository.dao.cms.model.TCmsTagExample;
import net.renfei.sdk.utils.ListUtils;
import net.renfei.service.BaseService;
import net.renfei.service.cms.CmsTagService;
import net.renfei.service.cms.dto.TagDTO;
import net.renfei.web.api.cms.ao.TagAO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CMS标签服务
 *
 * @author renfei
 */
@Service
public class CmsTagServiceImpl extends BaseService implements CmsTagService {
    private final TCmsTagMapper tagMapper;
    private final TCmsPostTagMapper postTagMapper;

    protected CmsTagServiceImpl(SystemConfig systemConfig,
                                TCmsTagMapper tagMapper,
                                TCmsPostTagMapper postTagMapper) {
        super(systemConfig);
        this.tagMapper = tagMapper;
        this.postTagMapper = postTagMapper;
    }

    /**
     * 添加标签
     *
     * @param tag 标签
     */
    @Override
    public void addTag(TagAO tag) {
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(tag.getEnName())) {
            throw new BusinessException("[EnName]不能为空");
        }
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(tag.getZhName())) {
            throw new BusinessException("[ZhName]不能为空");
        }
        TCmsTagExample example = new TCmsTagExample();
        // 检查重名
        example.createCriteria()
                .andIsDeletedEqualTo(false)
                .andEnNameEqualTo(tag.getEnName());
        TCmsTag tagDo = ListUtils.getOne(tagMapper.selectByExampleWithBLOBs(example));
        if (tagDo != null) {
            throw new BusinessException("[EnName]已存在，请换一个");
        }
        tagDo = new TCmsTag();
        tagDo.setIsDeleted(false);
        tagDo.setCreateTime(new Date());
        tagDo.setEnName(tag.getEnName());
        tagDo.setZhName(tag.getZhName());
        tagMapper.insertSelective(tagDo);
    }

    /**
     * 更新标签
     *
     * @param tag 标签
     */
    @Override
    public void updateTag(TagAO tag) {
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(tag.getEnName())) {
            throw new BusinessException("[EnName]不能为空");
        }
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(tag.getZhName())) {
            throw new BusinessException("[ZhName]不能为空");
        }
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(tag.getId())) {
            throw new BusinessException("[id]不能为空");
        }
        TCmsTag tagDo = tagMapper.selectByPrimaryKey(tag.getId());
        if (tagDo == null) {
            throw new BusinessException("根据[id]未查询到数据，请新增");
        }
        if (!tagDo.getEnName().equals(tag.getEnName())) {
            // EnName发生了变更，检查重名
            TCmsTagExample example = new TCmsTagExample();
            example.createCriteria()
                    .andIsDeletedEqualTo(false)
                    .andEnNameEqualTo(tag.getEnName());
            if (ListUtils.getOne(tagMapper.selectByExample(example)) != null) {
                throw new BusinessException("[EnName]已存在，请换一个");
            }
        }
        tagDo.setUpdateTime(new Date());
        tagDo.setEnName(tag.getEnName());
        tagDo.setZhName(tag.getZhName());
        tagMapper.updateByPrimaryKey(tagDo);
    }

    /**
     * 删除标签
     *
     * @param id 标签ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTag(long id) {
        TCmsTag tagDo = tagMapper.selectByPrimaryKey(id);
        if (tagDo == null) {
            throw new BusinessException("根据[id]未查询到数据，无需删除");
        }
        tagDo.setIsDeleted(true);
        tagMapper.updateByPrimaryKey(tagDo);
        TCmsPostTagExample example = new TCmsPostTagExample();
        example.createCriteria()
                .andTagIdEqualTo(id);
        TCmsPostTag postTag = new TCmsPostTag();
        postTag.setIsDeleted(true);
        postTag.setUpdateTime(new Date());
        postTagMapper.updateByExampleSelective(postTag, example);
    }

    /**
     * 获取全部标签
     *
     * @return 标签列表
     */
    @Override
    public List<TagDTO> getTagList() {
        TCmsTagExample example = new TCmsTagExample();
        example.createCriteria()
                .andIsDeletedEqualTo(false);
        List<TCmsTag> tagDoList = tagMapper.selectByExampleWithBLOBs(example);
        if (tagDoList == null || tagDoList.isEmpty()) {
            return null;
        }
        List<TagDTO> tagList = new CopyOnWriteArrayList<>();
        for (TCmsTag tagDo : tagDoList
        ) {
            TagDTO tagDTO = new TagDTO();
            BeanUtils.copyProperties(tagDo, tagDTO);
            tagList.add(tagDTO);
        }
        return tagList;
    }

    /**
     * 根据ID获取标签
     *
     * @param id 标签ID
     * @return 标签
     */
    @Override
    public TagDTO getTagById(long id) {
        TCmsTagExample example = new TCmsTagExample();
        example.createCriteria()
                .andIsDeletedEqualTo(false)
                .andIdEqualTo(id);
        TCmsTag tagDo = ListUtils.getOne(tagMapper.selectByExampleWithBLOBs(example));
        if (tagDo == null) {
            return null;
        }
        TagDTO tagDTO = new TagDTO();
        BeanUtils.copyProperties(tagDo, tagDTO);
        return tagDTO;
    }

    /**
     * 根据EnName获取标签
     *
     * @param enName 英文名
     * @return 标签
     */
    @Override
    public TagDTO getTagByEnName(String enName) {
        if (net.renfei.sdk.utils.BeanUtils.isEmpty(enName)) {
            return null;
        }
        TCmsTagExample example = new TCmsTagExample();
        example.createCriteria()
                .andIsDeletedEqualTo(false)
                .andEnNameEqualTo(enName);
        TCmsTag tagDo = ListUtils.getOne(tagMapper.selectByExampleWithBLOBs(example));
        if (tagDo == null) {
            return null;
        }
        TagDTO tagDTO = new TagDTO();
        BeanUtils.copyProperties(tagDo, tagDTO);
        return tagDTO;
    }
}
