package com.bdp.ap.app.schedule.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ScheduleModel {

	private String scheduleNm;
	private String scheduleCl;
	private String executCycle;
	private String timeZone;
	private String scheduleDsc;
	private String objectNm;
	private String triggerNm;
	private String lockYn;
	private String lockCycle;
	private String useYn;
	private String rgstId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDt;
	private String modiId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modiDt;
	private String serverIp; // schedule lock 처리 시 사용
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lockRgstDt; // schedule lock 처리 시 사용
	private String lockStopYn; // schedule lock 처리 시 사용
}
