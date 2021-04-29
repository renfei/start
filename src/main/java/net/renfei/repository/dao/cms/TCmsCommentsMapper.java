package net.renfei.repository.dao.cms;

import net.renfei.repository.dao.cms.model.TCmsComments;
import net.renfei.repository.dao.cms.model.TCmsCommentsExample;
import net.renfei.repository.dao.cms.model.TCmsCommentsWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TCmsCommentsMapper {
    long countByExample(TCmsCommentsExample example);

    int deleteByExample(TCmsCommentsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TCmsCommentsWithBLOBs record);

    int insertSelective(TCmsCommentsWithBLOBs record);

    List<TCmsCommentsWithBLOBs> selectByExampleWithBLOBs(TCmsCommentsExample example);

    List<TCmsComments> selectByExample(TCmsCommentsExample example);

    TCmsCommentsWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TCmsCommentsWithBLOBs record, @Param("example") TCmsCommentsExample example);

    int updateByExampleWithBLOBs(@Param("record") TCmsCommentsWithBLOBs record, @Param("example") TCmsCommentsExample example);

    int updateByExample(@Param("record") TCmsComments record, @Param("example") TCmsCommentsExample example);

    int updateByPrimaryKeySelective(TCmsCommentsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TCmsCommentsWithBLOBs record);

    int updateByPrimaryKey(TCmsComments record);
}