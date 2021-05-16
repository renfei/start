package net.renfei.repository.dao.start;

import net.renfei.repository.dao.start.model.TStartKvStorage;
import net.renfei.repository.dao.start.model.TStartKvStorageExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TStartKvStorageMapper {
    long countByExample(TStartKvStorageExample example);

    int deleteByExample(TStartKvStorageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TStartKvStorage record);

    int insertSelective(TStartKvStorage record);

    List<TStartKvStorage> selectByExampleWithBLOBs(TStartKvStorageExample example);

    List<TStartKvStorage> selectByExample(TStartKvStorageExample example);

    TStartKvStorage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TStartKvStorage record, @Param("example") TStartKvStorageExample example);

    int updateByExampleWithBLOBs(@Param("record") TStartKvStorage record, @Param("example") TStartKvStorageExample example);

    int updateByExample(@Param("record") TStartKvStorage record, @Param("example") TStartKvStorageExample example);

    int updateByPrimaryKeySelective(TStartKvStorage record);

    int updateByPrimaryKeyWithBLOBs(TStartKvStorage record);

    int updateByPrimaryKey(TStartKvStorage record);
}