package com.bdp.ap.schedule.job;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.bdp.ap.app.schedule.model.ScheduleLogModel;
import com.bdp.ap.app.schedule.model.ScheduleModel;
import com.bdp.ap.app.schedule.service.ScheduleService;
import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.Constant;
import com.bdp.ap.schedule.service.JobLogSysService;

import lombok.extern.slf4j.Slf4j;

/*
 * 시스템 수집 로그 파일 변환 Quartz Job
 */
@Slf4j
@Component
public class JobLogSys extends QuartzJobBean {

	@Resource(name = "jobLogSysService")
	private JobLogSysService service;

	@Resource(name = "commonUtil")
	private CommonUtil util;

	@Resource(name="scheduleService")
	private ScheduleService scheduleService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		// 스케줄러 정보
		ScheduleModel model = scheduleService.selectSchedule("LOG_SYS");
		model.setServerIp(util.getServerIP());
		int lock = 0;

		// 스케줄러 로그
		ScheduleLogModel logModel = new ScheduleLogModel();
		logModel.setStartDt(LocalDateTime.now());
		logModel.setIfTy(model.getScheduleNm());
		logModel.setIfTyKr(model.getScheduleDsc());
		logModel.setServerIp(model.getServerIp());

		// 로그 메시지
		JSONObject msg = new JSONObject();

		// 스케줄러 LOCK
		lock = scheduleService.insertScheduleLock(model);

		if (lock == 1) {
			try {
				//데이터 없으면 파일 생성하지 않음

				//일주일 전 파일 삭제
				service.deleteFile();

				// 공통 - 사용자
				service.userRqstCnt(msg);

			} catch (Exception e) {
				log.warn("시스템 수집 로그 처리 중 오류 발생");
				log.warn(e.getMessage());
				logModel.setErrMsg(util.getExceptionTrace(e));
			}

			logModel.setMsg(msg);
			if (StringUtils.isBlank(logModel.getRsltCode())) {
				logModel.setRsltCode(Constant.NO);
			}
			logModel.setEndDt(LocalDateTime.now());
			// 스케줄러 로그 기록 : 로그 기록 없음
			//scheduleService.insertScheduleLog(logModel);

			//스케줄러 LOCK 해제 - 서버간 시간 차이로 별도 스케줄러에서 락 해제
			//scheduleService.deleteScheduleLock(model);
		}
	}

}
