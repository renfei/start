package net.renfei.repository.dao.start;

import net.renfei.repository.dao.start.model.TStartRegion;
import net.renfei.repository.dao.start.model.TStartRegionExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TStartRegionMapper {
    long countByExample(TStartRegionExample example);

    int deleteByExample(TStartRegionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TStartRegion record);

    int insertSelective(TStartRegion record);

    List<TStartRegion> selectByExample(TStartRegionExample example);

    TStartRegion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TStartRegion record, @Param("example") TStartRegionExample example);

    int updateByExample(@Param("record") TStartRegion record, @Param("example") TStartRegionExample example);

    int updateByPrimaryKeySelective(TStartRegion record);

    int updateByPrimaryKey(TStartRegion record);
}