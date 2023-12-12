package com.cdp.portal.app.facade.qna.model;

import java.sql.Timestamp;
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
    private Timestamp answRgstDt;
    private String useYn;
    private String delYn;
    private String rgstId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    private Timestamp rgstDt;
    private String modiId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    private Timestamp modiDt;
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
    @Schema(description = "파일 아이디 목록")
    private String[] fileIds;
    @Schema(description = "다음 글 제목")
    private String nextSj;
    @Schema(description = "다음 글 id")
    private String nextId;
    @Schema(description = "이전 글 제목")
    private String preSj;
    @Schema(description = "이전 글 아이디")
    private String preId;
}
