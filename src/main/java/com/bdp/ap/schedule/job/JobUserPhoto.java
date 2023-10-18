package com.bdp.ap.schedule.job;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.bdp.ap.schedule.service.JobUserPhotoService;

import lombok.extern.slf4j.Slf4j;

/*
 * 사용자 사진 연계 Quartz Job
 */
@Slf4j
@Component
public class JobUserPhoto extends QuartzJobBean {

	@Resource(name="jobUserPhotoService")
	private JobUserPhotoService service;

	@Resource(name="commonUtil")
	private CommonUtil util;

	@Resource(name="scheduleService")
	private ScheduleService scheduleService;

	/*
	 * 사용자 사진 연계
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		/*
		 * 사용자 사진
		 * S3 등록
		 * 최초 전체 이후 변경 사진 연계
		 */
		//스케줄러 정보
		ScheduleModel model = scheduleService.selectSchedule("USER_PHOTO");
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

		// 사진 파일 가져오기
		List<Path> list = service.getPhotoFiles();

		// 사진 파일이 있는 경우 처리 시작
		if (list.size() > 0) {
			for (Path path : list) {
				log.debug("########## file : {}",path.getFileName().toString());
			}
			//스케줄러 LOCK
			lock = scheduleService.insertScheduleLock(model);

			if (lock == 1) {
				msg.put("fileCount",list.size());
				int successCount = 0;
				int failCount = 0;
				List<String> errList = new ArrayList<>();

				for (Path path : list) {
					JSONObject rslt = service.saveFile(path);
					if (rslt.optBoolean("rslt", false)) {
						successCount++;
					} else {
						failCount++;
						if (StringUtils.isNotBlank(rslt.optString("errMsg"))) {
							errList.add(rslt.getString("errMsg"));
						}
					}
				}
				msg.put("successCount", successCount);
				msg.put("failCount", failCount);

				if (errList.size() > 0) {
					logModel.setErrMsg(StringUtils.join(errList," / "));
				}

				logModel.setMsg(msg);
				logModel.setRsltCode(Constant.YES);
				logModel.setEndDt(LocalDateTime.now());
				log.debug("USER PHOTO SCHEDULE RESULT : {}",logModel.toString());
				//스케줄러 로그 기록
				scheduleService.insertScheduleLog(logModel);

				//스케줄러 LOCK 해제 - 서버간 시간 차이로 별도 스케줄러에서 락 해제
				//scheduleService.deleteScheduleLock(model);
			}
		}
	}

}
