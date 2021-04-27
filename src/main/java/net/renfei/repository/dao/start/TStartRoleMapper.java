package net.renfei.repository.dao.start;

import net.renfei.repository.dao.start.model.TStartRole;
import net.renfei.repository.dao.start.model.TStartRoleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TStartRoleMapper {
    long countByExample(TStartRoleExample example);

    int deleteByExample(TStartRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TStartRole record);

    int insertSelective(TStartRole record);

    List<TStartRole> selectByExample(TStartRoleExample example);

    TStartRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TStartRole record, @Param("example") TStartRoleExample example);

    int updateByExample(@Param("record") TStartRole record, @Param("example") TStartRoleExample example);

    int updateByPrimaryKeySelective(TStartRole record);

    int updateByPrimaryKey(TStartRole record);
}