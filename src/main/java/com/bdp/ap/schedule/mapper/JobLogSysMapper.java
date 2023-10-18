package com.bdp.ap.schedule.mapper;

import java.util.List;
import java.util.Map;

import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface JobLogSysMapper {

	//로그 - 사용자 접속 로그 : 접속 건수
	int selectUserSysRqstCntCount();
	List<Map<String,Object>> selectUserSysRqstCnt(int pageNo);

}
