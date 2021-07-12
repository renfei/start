package net.renfei.repository.dao.cms;

import net.renfei.repository.dao.cms.model.TCmsPostAttachments;
import net.renfei.repository.dao.cms.model.TCmsPostAttachmentsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TCmsPostAttachmentsMapper {
    long countByExample(TCmsPostAttachmentsExample example);

    int deleteByExample(TCmsPostAttachmentsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TCmsPostAttachments record);

    int insertSelective(TCmsPostAttachments record);

    List<TCmsPostAttachments> selectByExample(TCmsPostAttachmentsExample example);

    TCmsPostAttachments selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TCmsPostAttachments record, @Param("example") TCmsPostAttachmentsExample example);

    int updateByExample(@Param("record") TCmsPostAttachments record, @Param("example") TCmsPostAttachmentsExample example);

    int updateByPrimaryKeySelective(TCmsPostAttachments record);

    int updateByPrimaryKey(TCmsPostAttachments record);
}