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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    private LocalDateTime answRgstDt;
    private String useYn;
    private String delYn;
    private String rgstId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDt;
    private String modiId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    private LocalDateTime modiDt;
    private String qnaStat;
    private String openYn;
    private String bfQnaId;
    private int viewCnt;
    @Schema(description = "화면 순번")
    private int rownum;
    @Schema(description = "등록자 이름")
    private String rgstNm;
    @Schema(description = "부서 이름")
    private String rgstDeptNm;
}
