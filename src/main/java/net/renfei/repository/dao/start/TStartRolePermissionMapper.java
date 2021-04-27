package net.renfei.repository.dao.start;

import net.renfei.repository.dao.start.model.TStartRolePermission;
import net.renfei.repository.dao.start.model.TStartRolePermissionExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TStartRolePermissionMapper {
    long countByExample(TStartRolePermissionExample example);

    int deleteByExample(TStartRolePermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TStartRolePermission record);

    int insertSelective(TStartRolePermission record);

    List<TStartRolePermission> selectByExample(TStartRolePermissionExample example);

    TStartRolePermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TStartRolePermission record, @Param("example") TStartRolePermissionExample example);

    int updateByExample(@Param("record") TStartRolePermission record, @Param("example") TStartRolePermissionExample example);

    int updateByPrimaryKeySelective(TStartRolePermission record);

    int updateByPrimaryKey(TStartRolePermission record);
}