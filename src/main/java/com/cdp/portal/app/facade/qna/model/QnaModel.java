package com.cdp.portal.app.facade.qna.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnaModel {

    private String qnaId;
    private String clCode;
    private String sj;
    private String cn;
    private String answ;
    private String answRgstId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime answRgstDt;
    private String bfQnaId;
    private String useYn;
    private String delYn;
    private String rgstId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDt;
    private String modiId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modiDt;
    private String qnaStat;
    private String openYn;
    private int viewCnt;
    private int rownum;             // 화면 순번

    private String rgstPhotoUrl;	//등록자 사진
    private String answPhotoUrl;	//답변자 사진

    private String[] fileIds;

    private int lv; // 레벨
    private String deptCd;
}
