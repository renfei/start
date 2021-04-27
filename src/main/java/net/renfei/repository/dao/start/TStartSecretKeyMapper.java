package net.renfei.repository.dao.start;

import net.renfei.repository.dao.start.model.TStartSecretKey;
import net.renfei.repository.dao.start.model.TStartSecretKeyExample;
import net.renfei.repository.dao.start.model.TStartSecretKeyWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TStartSecretKeyMapper {
    long countByExample(TStartSecretKeyExample example);

    int deleteByExample(TStartSecretKeyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TStartSecretKeyWithBLOBs record);

    int insertSelective(TStartSecretKeyWithBLOBs record);

    List<TStartSecretKeyWithBLOBs> selectByExampleWithBLOBs(TStartSecretKeyExample example);

    List<TStartSecretKey> selectByExample(TStartSecretKeyExample example);

    TStartSecretKeyWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TStartSecretKeyWithBLOBs record, @Param("example") TStartSecretKeyExample example);

    int updateByExampleWithBLOBs(@Param("record") TStartSecretKeyWithBLOBs record, @Param("example") TStartSecretKeyExample example);

    int updateByExample(@Param("record") TStartSecretKey record, @Param("example") TStartSecretKeyExample example);

    int updateByPrimaryKeySelective(TStartSecretKeyWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TStartSecretKeyWithBLOBs record);

    int updateByPrimaryKey(TStartSecretKey record);
}