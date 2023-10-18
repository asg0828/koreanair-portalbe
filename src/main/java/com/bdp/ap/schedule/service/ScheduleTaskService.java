package com.bdp.ap.schedule.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.bdp.ap.app.schedule.model.ScheduleModel;
import com.bdp.ap.app.schedule.service.ScheduleService;
import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.Constant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduleTaskService {

	@Resource(name="commonUtil")
	private CommonUtil util;

	@Resource(name = "scheduleService")
    private ScheduleService scheduleService;

	/**
	 * Quartz 스케줄 락 확인
	 */
	public void checkLock() {
		List<ScheduleModel> list = scheduleService.selectScheduleLockList();
		if (list != null) {
			for (ScheduleModel model : list) {
				if (StringUtils.equals(model.getServerIp(), util.getServerIP()) && StringUtils.equals(Constant.YES, model.getLockStopYn())) {
					if (scheduleService.isRunningJob(model)) {
						//스케줄러 락 체크 시간 이상으로 실행 중인 스케줄러 경우 처리
					} else {
						scheduleService.deleteScheduleLock(model);
					}
				}
			}
		}
	}
}
