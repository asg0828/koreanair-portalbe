package com.bdp.ap.schedule.job;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
import com.bdp.ap.schedule.service.JobDeptClService;

import lombok.extern.slf4j.Slf4j;

/*
 * 부서 분류 정보 연계 Quartz Job
 */
@Slf4j
@Component
public class JobDeptCl extends QuartzJobBean {

	@Resource(name="jobDeptClService")
	private JobDeptClService service;

	@Resource(name="commonUtil")
	private CommonUtil util;

	@Resource(name="scheduleService")
	private ScheduleService scheduleService;

	/*
	 * 부서 분류 정보 연계
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		/*
		 * 부서 조직도 연계
		 * 이력 관리 없음
		 */
		//스케줄러 정보
		ScheduleModel model = scheduleService.selectSchedule("DEPT_CL");
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

		// 파일 있는지 확인
		service.checkFiles(msg);

		// 파일이 있는 경우만 처리
		if (StringUtils.equals(Constant.YES, msg.getJSONObject(Constant.HR.CHECK_FILES).getString(Constant.RESULT))) {
			//스케줄러 LOCK
			lock = scheduleService.insertScheduleLock(model);

			//스케줄러 LOCK 안 걸린 경우에 처리
			if (lock == 1) {
				try {
					Map<String,List<Map<String,String>>> data = service.parsingFiles(msg);

					//파싱 완료 후 데이터 처리 시작
					if (StringUtils.equals(Constant.YES,msg.getJSONObject(Constant.HR.PARSING_FILES).getString(Constant.RESULT))) {
						// 인터페이스 테이블에 INSERT
						JSONObject insertData = service.insertData(data);
						msg.put("insertData", insertData);

						// HR 데이터 동기화
						JSONObject syncData = service.syncData();
						msg.put("syncData", syncData);

						logModel.setRsltCode(Constant.YES);
					} else {
						// 파싱 오류 인 경우
						logModel.setErrMsg(msg.getString(Constant.ERROR_MESSAGE));
						msg.remove(Constant.ERROR_MESSAGE);
					}
				} catch(Exception e) {
					log.warn("부서 분류 연동 처리 중 오류 발생");
					log.warn(e.getMessage());
					logModel.setErrMsg(util.getExceptionTrace(e));
				}

				try {
					service.backupFiles(msg);
				} catch(Exception e) {
					log.warn(e.getMessage());
				}

				logModel.setMsg(msg);
				if (StringUtils.isBlank(logModel.getRsltCode())) {
					logModel.setRsltCode(Constant.NO);
				}
				logModel.setEndDt(LocalDateTime.now());
				//스케줄러 로그 기록
				scheduleService.insertScheduleLog(logModel);

				//스케줄러 LOCK 해제 - 서버간 시간 차이로 별도 스케줄러에서 락 해제
				//scheduleService.deleteScheduleLock(model);
			}
		}
	}

}
