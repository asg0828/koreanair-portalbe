package com.bdp.ap.app.board.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class DataRoomModel implements Serializable {

    private String dataId;
    private String sj;
    private String cn;
    private String useYn;
    private String useYnNm;
    private String delYn;
    private String companyNm;
    private String rgstId;
    private String rgstNm;
    private String rgstDeptNm;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDt;
    private String modiId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modiDt;
    private int viewCnt;
    private String nextSj;
    private String nextId;
    private String preSj;
    private String preId;

    private String[] fileIds;
}