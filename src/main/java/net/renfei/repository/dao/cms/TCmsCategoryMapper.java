package net.renfei.repository.dao.cms;

import net.renfei.repository.dao.cms.model.TCmsCategory;
import net.renfei.repository.dao.cms.model.TCmsCategoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TCmsCategoryMapper {
    long countByExample(TCmsCategoryExample example);

    int deleteByExample(TCmsCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TCmsCategory record);

    int insertSelective(TCmsCategory record);

    List<TCmsCategory> selectByExampleWithBLOBs(TCmsCategoryExample example);

    List<TCmsCategory> selectByExample(TCmsCategoryExample example);

    TCmsCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TCmsCategory record, @Param("example") TCmsCategoryExample example);

    int updateByExampleWithBLOBs(@Param("record") TCmsCategory record, @Param("example") TCmsCategoryExample example);

    int updateByExample(@Param("record") TCmsCategory record, @Param("example") TCmsCategoryExample example);

    int updateByPrimaryKeySelective(TCmsCategory record);

    int updateByPrimaryKeyWithBLOBs(TCmsCategory record);

    int updateByPrimaryKey(TCmsCategory record);
}