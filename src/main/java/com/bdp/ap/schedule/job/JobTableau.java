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
import com.bdp.ap.schedule.service.JobTableauService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobTableau extends QuartzJobBean {
	
	@Resource(name="jobTableauService")
	private JobTableauService service;
	
	@Resource(name="commonUtil")
	private CommonUtil util;
	
	@Resource(name="scheduleService")
	private ScheduleService scheduleService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		/*
		 * 태블로 정보 연계
		 * 이력 관리 없음
		 */
		//스케줄러 정보
		ScheduleModel model = scheduleService.selectSchedule("TABLEAU");
		model.setServerIp(util.getServerIP());
		int lock = 0;
		
		// 스케줄러 로그
		ScheduleLogModel logModel = new ScheduleLogModel();
		logModel.setStartDt(LocalDateTime.now());
		logModel.setIfTy(model.getScheduleNm());
		logModel.setServerIp(model.getServerIp());
		
		// 로그 메시지
		JSONObject msg = new JSONObject();
		
		//스케줄러 LOCK
		lock = scheduleService.insertScheduleLock(model);
		
		//스케줄러 LOCK 안 걸린 경우에 처리
		if (lock == 1) {
			try {
				// 태블로 연동 처리 시작
				
				// 프로젝트
				// 태블로 프로젝트 목록 중 수정된 것이 있으면 포털 프로젝트를 업데이트 : 제목, 설명
				// 태블로 프로젝트 목록 중에서 포털 프로젝트에 미등록인 프로젝트를 자동 생성
				service.syncProject(msg);
				
				//워크북
				// 태블로 워크북 목록 중 수정된 것이 있으면 포털 보고서를 업데이트 : 제목, 설명, URL
				// 태블로 워크북 목록 중에서 포털 보고서에 미등록인 워크북을 자동 생성
				service.syncWorkbook(msg);
				
				//뷰
				service.syncView(msg);
				
				// HR 사용자 태블로 계정 생성 : t_license 에 미등록 된 사용자를 태블로 addUser(over write 처리됨)하고 insert
				service.createUser(msg);
				//사용자
				service.syncUser(msg);
				
				//미리보기 이미지
				service.syncPreview(msg);
				
				//이미지
				service.syncViewImage(msg);
				
			} catch(Exception e) {
				log.warn("태블로 정보 연동 처리 중 오류 발생");
				log.warn(e.getMessage());
				logModel.setErrMsg(util.getExceptionTrace(e));
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
