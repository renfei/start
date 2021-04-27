package net.renfei.repository.dao.start;

import net.renfei.repository.dao.start.model.TStartUser;
import net.renfei.repository.dao.start.model.TStartUserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TStartUserMapper {
    long countByExample(TStartUserExample example);

    int deleteByExample(TStartUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TStartUser record);

    int insertSelective(TStartUser record);

    List<TStartUser> selectByExample(TStartUserExample example);

    TStartUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TStartUser record, @Param("example") TStartUserExample example);

    int updateByExample(@Param("record") TStartUser record, @Param("example") TStartUserExample example);

    int updateByPrimaryKeySelective(TStartUser record);

    int updateByPrimaryKey(TStartUser record);
}