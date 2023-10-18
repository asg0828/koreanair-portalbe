package com.bdp.ap.app.schedule.mapper;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.bdp.ap.app.schedule.model.ScheduleLogModel;
import com.bdp.ap.app.schedule.model.ScheduleModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface ScheduleMapper {
	//스케줄 로그
	int insertScheduleLog(ScheduleLogModel model);

	//스케줄 LOCK
	List<ScheduleModel> selectScheduleLockList();
	int selectScheduleLockCount(ScheduleModel model);
	int insertScheduleLock(ScheduleModel model) throws DuplicateKeyException;
	int deleteScheduleLock(ScheduleModel model);

	//스케줄 조회
	ScheduleModel selectSchedule(String scheduleNm);
	List<ScheduleModel> selectScheduleListAtStart();
	List<ScheduleModel> selectScheduleList();
}
