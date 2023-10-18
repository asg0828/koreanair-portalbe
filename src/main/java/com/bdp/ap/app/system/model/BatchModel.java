package com.bdp.ap.app.system.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BatchModel implements Serializable {
    private String scheduleNm;
    private String scheduleCl;
    private String scheduleClNm;
    private String executCycle;
    private String timeZone;
    private String scheduleDsc;
    private String objectNm;
    private String triggerNm;
    private String lockYn;
    private String lockYnNm;
    private String lockCycle;
    private String useYn;
    private String useYnNm;
    private String rgstId;
    private String rgstNm;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDt;
    private String modiId;
    private String modiNm;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modiDt;
}
