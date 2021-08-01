package net.renfei.repository.dao.start;

import net.renfei.repository.dao.start.model.TStartSysMenu;
import net.renfei.repository.dao.start.model.TStartSysMenuExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TStartSysMenuMapper {
    long countByExample(TStartSysMenuExample example);

    int deleteByExample(TStartSysMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TStartSysMenu record);

    int insertSelective(TStartSysMenu record);

    List<TStartSysMenu> selectByExample(TStartSysMenuExample example);

    TStartSysMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TStartSysMenu record, @Param("example") TStartSysMenuExample example);

    int updateByExample(@Param("record") TStartSysMenu record, @Param("example") TStartSysMenuExample example);

    int updateByPrimaryKeySelective(TStartSysMenu record);

    int updateByPrimaryKey(TStartSysMenu record);
}