package com.bdp.ap.app.board.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AnlzSourceCmtModel implements Serializable {
    private String anlzSourceId;
    private int anlzSourceCmtId;
    private Integer anlzParentsCmtId;

    private String cmt;
    private String cn;
    private int viewCnt;
    private String useYn;
    private String useYnNm;
    private String rgstId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDt;
    private String modiId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modiDt;
    private String userNm;
    private int rownum;             // 화면 순번
    private String rgstNm;
    private String rgstDeptNm;
    private int lv;
    private String cmtDept;
}

