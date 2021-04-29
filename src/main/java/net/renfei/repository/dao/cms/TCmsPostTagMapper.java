package net.renfei.repository.dao.cms;

import net.renfei.repository.dao.cms.model.TCmsPostTag;
import net.renfei.repository.dao.cms.model.TCmsPostTagExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TCmsPostTagMapper {
    long countByExample(TCmsPostTagExample example);

    int deleteByExample(TCmsPostTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TCmsPostTag record);

    int insertSelective(TCmsPostTag record);

    List<TCmsPostTag> selectByExample(TCmsPostTagExample example);

    TCmsPostTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TCmsPostTag record, @Param("example") TCmsPostTagExample example);

    int updateByExample(@Param("record") TCmsPostTag record, @Param("example") TCmsPostTagExample example);

    int updateByPrimaryKeySelective(TCmsPostTag record);

    int updateByPrimaryKey(TCmsPostTag record);
}