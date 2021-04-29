package net.renfei.repository.dao.cms;

import net.renfei.repository.dao.cms.model.TCmsPosts;
import net.renfei.repository.dao.cms.model.TCmsPostsExample;
import net.renfei.repository.dao.cms.model.TCmsPostsWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TCmsPostsMapper {
    long countByExample(TCmsPostsExample example);

    int deleteByExample(TCmsPostsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TCmsPostsWithBLOBs record);

    int insertSelective(TCmsPostsWithBLOBs record);

    List<TCmsPostsWithBLOBs> selectByExampleWithBLOBs(TCmsPostsExample example);

    List<TCmsPosts> selectByExample(TCmsPostsExample example);

    TCmsPostsWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TCmsPostsWithBLOBs record, @Param("example") TCmsPostsExample example);

    int updateByExampleWithBLOBs(@Param("record") TCmsPostsWithBLOBs record, @Param("example") TCmsPostsExample example);

    int updateByExample(@Param("record") TCmsPosts record, @Param("example") TCmsPostsExample example);

    int updateByPrimaryKeySelective(TCmsPostsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TCmsPostsWithBLOBs record);

    int updateByPrimaryKey(TCmsPosts record);
}