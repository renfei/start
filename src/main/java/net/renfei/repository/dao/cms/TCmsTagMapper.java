package net.renfei.repository.dao.cms;

import net.renfei.repository.dao.cms.model.TCmsTag;
import net.renfei.repository.dao.cms.model.TCmsTagExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TCmsTagMapper {
    long countByExample(TCmsTagExample example);

    int deleteByExample(TCmsTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TCmsTag record);

    int insertSelective(TCmsTag record);

    List<TCmsTag> selectByExampleWithBLOBs(TCmsTagExample example);

    List<TCmsTag> selectByExample(TCmsTagExample example);

    TCmsTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TCmsTag record, @Param("example") TCmsTagExample example);

    int updateByExampleWithBLOBs(@Param("record") TCmsTag record, @Param("example") TCmsTagExample example);

    int updateByExample(@Param("record") TCmsTag record, @Param("example") TCmsTagExample example);

    int updateByPrimaryKeySelective(TCmsTag record);

    int updateByPrimaryKeyWithBLOBs(TCmsTag record);
}