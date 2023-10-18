package com.bdp.ap.schedule.mapper;

import java.util.List;
import java.util.Map;

import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface JobDeptClMapper {

	int deleteIfDeptCl() throws Exception;

	int insertIfDeptCl(Map<String,List<Map<String,String>>> param) throws Exception;

	int syncDeleteDeptCl() throws Exception;

	int syncUpdateDeptCl() throws Exception;

	int syncDeleteRecoveryDeptCl() throws Exception;

	int syncInsertDeptCl() throws Exception;

	int updateModiSeDeptCl() throws Exception;
}
