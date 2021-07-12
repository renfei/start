package net.renfei.repository.dao.start;

import net.renfei.repository.dao.start.model.TStartUserPasswordHistory;
import net.renfei.repository.dao.start.model.TStartUserPasswordHistoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TStartUserPasswordHistoryMapper {
    long countByExample(TStartUserPasswordHistoryExample example);

    int deleteByExample(TStartUserPasswordHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TStartUserPasswordHistory record);

    int insertSelective(TStartUserPasswordHistory record);

    List<TStartUserPasswordHistory> selectByExample(TStartUserPasswordHistoryExample example);

    TStartUserPasswordHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TStartUserPasswordHistory record, @Param("example") TStartUserPasswordHistoryExample example);

    int updateByExample(@Param("record") TStartUserPasswordHistory record, @Param("example") TStartUserPasswordHistoryExample example);

    int updateByPrimaryKeySelective(TStartUserPasswordHistory record);

    int updateByPrimaryKey(TStartUserPasswordHistory record);
}