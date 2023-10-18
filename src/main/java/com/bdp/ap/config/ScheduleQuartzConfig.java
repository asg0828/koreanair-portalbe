package com.bdp.ap.config;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.bdp.ap.app.schedule.mapper.ScheduleMapper;
import com.bdp.ap.app.schedule.model.ScheduleModel;
import com.bdp.ap.app.schedule.service.ScheduleService;
import com.bdp.ap.common.Constant;

import lombok.extern.slf4j.Slf4j;

/**
 * QUARTZ Schedule 설정
 */
@Slf4j
@Configuration
//@Profile({Constant.Profile.PROD,Constant.Profile.DEV})
public class ScheduleQuartzConfig {

	@Resource(name="scheduleMapper")
	private ScheduleMapper mapper;

	@Resource(name="quartzScheduler")
	private StdScheduler scheduler;

	@Resource(name="scheduleService")
	private ScheduleService service;

	@PostConstruct
	public void scheduleQuartzConfig() throws SchedulerException {
		List<ScheduleModel> list = mapper.selectScheduleListAtStart();
		for (ScheduleModel model : list) {
			if (StringUtils.equals(Constant.YES, model.getUseYn())) {
				if (CronExpression.isValidExpression(model.getExecutCycle())) {
					scheduler.scheduleJob(service.getJobDetail(model), service.getTrigger(model));
					log.info("스케줄 {} 을 스케줄러에 등록",model.getScheduleNm());
				} else {
					log.warn("스케줄 {} 의 CronExpression '{}' 이 유효하지 않음",model.getScheduleNm(),model.getExecutCycle());
				}
			} else {
				log.info("스케줄 {} 는 사용 중지 상태로 스케줄러에 등록하지 않음",model.getScheduleNm());
			}
		}
		return;
	}

}
