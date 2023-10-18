package com.bdp.ap.schedule.mapper;

import java.util.List;
import java.util.Map;

import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface JobLogToFileMapper {

	//공통 - 사용자
	int selectUserCount();
	List<Map<String,Object>> selectUser(int pageNo);



	// 이력 - 사용자 이력
	int selectUserHistCount();
	List<Map<String,Object>> selectUserHist(int pageNo);



	// 로그 - 관리자 시스템 접속 로그
	int selectRqstMgrSysCount();
	List<Map<String,Object>> selectRqstMgrSys(int pageNo);
	// 로그 - 사용자 시스템 접속 로그
	int selectRqstUserSysCount();
	List<Map<String,Object>> selectRqstUserSys(int pageNo);

	// 로그 - 관리자 시스템 태블로 요청 로그
	int selectTableauMgrSysCount();
	List<Map<String,Object>> selectTableauMgrSys(int pageNo);
	// 로그 - 사용자 시스템 태블로 요청 로그
	int selectTableauUserSysCount();
	List<Map<String,Object>> selectTableauUserSys(int pageNo);

	// 로그 - 포털 관리자 시스템 비즈메타 접속 로그
	int selectBizmetaMgrSysCount();
	List<Map<String,Object>> selectBizmetaMgrSys(int pageNo);
	// 로그 - 포털 사용자 시스템 비즈메타 접속 로그
	int selectBizmetaUserSysCount();
	List<Map<String,Object>> selectBizmetaUserSys(int pageNo);

	// 로그 - 포털 관리자 시스템 AWS 접속 로그
	int selectAwsMgrSysCount();
	List<Map<String,Object>> selectAwsMgrSys(int pageNo);
	// 로그 - 포털 사용자 시스템 AWS 접속 로그
	int selectAwsUserSysCount();
	List<Map<String,Object>> selectAwsUserSys(int pageNo);



	// 업무 - 승인 요청 정보 중 승인 상태가 승인완료, 반려(회수) 인 승인 건
	int selectAprvRqstCount();
	List<Map<String,Object>> selectAprvRqst(int pageNo);
}
