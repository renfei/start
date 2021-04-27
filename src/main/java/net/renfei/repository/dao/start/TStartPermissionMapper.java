package net.renfei.repository.dao.start;

import net.renfei.repository.dao.start.model.TStartPermission;
import net.renfei.repository.dao.start.model.TStartPermissionExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TStartPermissionMapper {
    long countByExample(TStartPermissionExample example);

    int deleteByExample(TStartPermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TStartPermission record);

    int insertSelective(TStartPermission record);

    List<TStartPermission> selectByExample(TStartPermissionExample example);

    TStartPermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TStartPermission record, @Param("example") TStartPermissionExample example);

    int updateByExample(@Param("record") TStartPermission record, @Param("example") TStartPermissionExample example);

    int updateByPrimaryKeySelective(TStartPermission record);

    int updateByPrimaryKey(TStartPermission record);
}