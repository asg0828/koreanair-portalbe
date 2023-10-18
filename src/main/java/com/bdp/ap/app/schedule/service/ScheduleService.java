package com.bdp.ap.app.schedule.service;

import java.util.List;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdScheduler;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdp.ap.app.schedule.mapper.ScheduleMapper;
import com.bdp.ap.app.schedule.model.ScheduleLogModel;
import com.bdp.ap.app.schedule.model.ScheduleModel;
import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.Constant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScheduleService {

	@Resource(name="commonUtil")
	private CommonUtil util;

	@Resource(name="scheduleMapper")
	private ScheduleMapper mapper;

	@Resource(name="quartzScheduler")
	private StdScheduler scheduler;

	/**
	 * 스케줄 JobDetail 생성
	 * @param model
	 * @return
	 */
	public JobDetail getJobDetail(ScheduleModel model) {
		JobBuilder jb = JobBuilder.newJob();
		jb.ofType(Constant.SCHEDULE_JOB.get(model.getScheduleNm()));
		jb.storeDurably();
		jb.withIdentity(model.getScheduleNm(), model.getScheduleCl());
		return jb.build();
	}

	/**
	 * 스케줄 Trigger 생성
	 * @param model
	 * @return
	 */
	public Trigger getTrigger(ScheduleModel model) {
		TriggerBuilder<Trigger> tb = TriggerBuilder.newTrigger();
		tb.forJob(model.getScheduleNm(), model.getScheduleCl());
		tb.withIdentity(model.getScheduleNm(), model.getScheduleCl());
		//tb.withSchedule(CronScheduleBuilder.cronSchedule(model.getExecutCycle()).inTimeZone(TimeZone.getTimeZone(model.getTimeZone())));
		tb.withSchedule(CronScheduleBuilder.cronSchedule(model.getExecutCycle()).inTimeZone(TimeZone.getTimeZone("Asia/Seoul")));
		return tb.build();
	}

	/**
	 * 스케줄 즉시 실행 - 나중에 화면 만들면 매개변수 타입을 ScheduleModel로 변경
	 * @param scheduleNm
	 */
	public String startJob(String scheduleNm) {
		String result = "run success";
		ScheduleModel model = mapper.selectSchedule(scheduleNm);
		try {
			if (model != null) {
				scheduler.triggerJob(JobKey.jobKey(scheduleNm, model.getScheduleCl()));
			} else {
				log.info("스케줄 {} 은 존재하지 않아 즉시 실행 불가",scheduleNm);
			}
		} catch (SchedulerException e) {
			result = "run failed : check log";
			log.warn("스케줄 {} 즉시 실행 오류",scheduleNm);
			log.warn(e.getMessage());
		}
		return result;
	}

	/**
	 * 스케줄 실행 상태
	 * 서버 여러대일 경우 처리 확인 불가 -> T_MGR_SYS_SCHEDULE_LOCK 조회로 대체해야 함
	 */
	public boolean isRunningJob(ScheduleModel model) {
		boolean result = false;
		List<JobExecutionContext> list = scheduler.getCurrentlyExecutingJobs();
		for (JobExecutionContext context : list) {
			String scheduleNm = context.getJobDetail().getKey().getName();
			String scheduleCl = context.getJobDetail().getKey().getGroup();
			if (StringUtils.equals(scheduleNm,model.getScheduleNm())
					&& StringUtils.equals(scheduleCl, model.getScheduleCl())
					&& context.getFireTime().equals(context.getFireTime())) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * 스케줄 중지 : 스케줄 삭제
	 * 서버 여러대일 경우 처리 불가 -> 별도 로직 필요
	 * @param model
	 */
	public void stopSchedule(ScheduleModel model) {
		try {
			scheduler.deleteJob(JobKey.jobKey(model.getScheduleNm(), model.getScheduleCl()));
		} catch (SchedulerException e) {
			log.warn("스케줄 {} 중지 오류",model.getScheduleNm());
			log.warn(e.getMessage());
		}
	}

	/**
	 * 스케줄 시작 : 스케줄 추가
	 * 서버 여러대일 경우 처리 불가 -> 별도 로직 필요
	 * @param model
	 */
	public void startSchedule(ScheduleModel model) {
		try {
			scheduler.scheduleJob(getJobDetail(model), getTrigger(model));
		} catch (SchedulerException e) {
			log.warn("스케줄 {} 시작 오류",model.getScheduleNm());
			log.warn(e.getMessage());
		}
	}

	/**
	 * 스케줄 정보 조회 : JOB에서 호출
	 * @param scheduleNm
	 * @return
	 */
	public ScheduleModel selectSchedule(String scheduleNm) {
		return mapper.selectSchedule(scheduleNm);
	}

	/**
	 * 스케줄 락 처리 : JOB에서 호출
	 * @param model
	 * @return
	 */
	@Transactional
	public int insertScheduleLock(ScheduleModel model) {
		int result = 0;
		try {
			if (StringUtils.equals(Constant.NO, model.getLockYn())) {
				// LOCK_YN = "N" 인 경우 모든 서버에서 실행
				result = 1;
			} else {
				// 락 테이블 조회 후 락 등록
				if (mapper.selectScheduleLockCount(model) == 0) {
					result = mapper.insertScheduleLock(model);
				}
			}
		} catch (DuplicateKeyException e) {
			result = 0;
			log.info("스케줄 {} : 작업 중인 스케줄 있음",model.getScheduleNm());
		}
		return result;
	}

	/**
	 * 스케줄 로그 기록 : JOB에서 호출
	 * @param model
	 * @return
	 */
	public int insertScheduleLog(ScheduleLogModel model) {
		return mapper.insertScheduleLog(model);
	}

	/**
	 * 스케줄 락 해제 : ScheduleTask에서 호출
	 * @param model
	 * @return
	 */
	public int deleteScheduleLock(ScheduleModel model) {
		return mapper.deleteScheduleLock(model);
	}

	/**
	 * 스케줄 락 목록 조회 : ScheduleTask에서 호출
	 * @return
	 */
	public List<ScheduleModel> selectScheduleLockList() {
		return mapper.selectScheduleLockList();
	}
}
