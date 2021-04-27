package net.renfei.repository.dao.start;

import net.renfei.repository.dao.start.model.TStartUserRole;
import net.renfei.repository.dao.start.model.TStartUserRoleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TStartUserRoleMapper {
    long countByExample(TStartUserRoleExample example);

    int deleteByExample(TStartUserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TStartUserRole record);

    int insertSelective(TStartUserRole record);

    List<TStartUserRole> selectByExample(TStartUserRoleExample example);

    TStartUserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TStartUserRole record, @Param("example") TStartUserRoleExample example);

    int updateByExample(@Param("record") TStartUserRole record, @Param("example") TStartUserRoleExample example);

    int updateByPrimaryKeySelective(TStartUserRole record);

    int updateByPrimaryKey(TStartUserRole record);
}