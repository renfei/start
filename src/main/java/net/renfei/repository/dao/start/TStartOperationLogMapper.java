package net.renfei.repository.dao.start;

import net.renfei.repository.dao.start.model.TStartOperationLog;
import net.renfei.repository.dao.start.model.TStartOperationLogExample;
import net.renfei.repository.dao.start.model.TStartOperationLogWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TStartOperationLogMapper {
    long countByExample(TStartOperationLogExample example);

    int deleteByExample(TStartOperationLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TStartOperationLogWithBLOBs record);

    int insertSelective(TStartOperationLogWithBLOBs record);

    List<TStartOperationLogWithBLOBs> selectByExampleWithBLOBs(TStartOperationLogExample example);

    List<TStartOperationLog> selectByExample(TStartOperationLogExample example);

    TStartOperationLogWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TStartOperationLogWithBLOBs record, @Param("example") TStartOperationLogExample example);

    int updateByExampleWithBLOBs(@Param("record") TStartOperationLogWithBLOBs record, @Param("example") TStartOperationLogExample example);

    int updateByExample(@Param("record") TStartOperationLog record, @Param("example") TStartOperationLogExample example);

    int updateByPrimaryKeySelective(TStartOperationLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TStartOperationLogWithBLOBs record);

    int updateByPrimaryKey(TStartOperationLog record);
}