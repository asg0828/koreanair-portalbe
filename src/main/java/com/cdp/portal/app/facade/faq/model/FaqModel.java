package com.cdp.portal.app.facade.faq.model;

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
public class FaqModel {
    @Schema(description = "FAQ ID", example = "FAQ_ID")
    private String faqId;
    @Schema(description = "분류 코드[CODE GROUP_ID: FAQ_CL_CODE]")
    private String clCode;
    @Schema(description = "질문")
    private String qstn;
    @Schema(description = "답변")
    private String answ;
    @Schema(description = "정렬 순서")
    private int ordSeq;
    @Schema(description = "사용 여부")
    private String useYn;
    @Schema(description = "삭제 여부")
    private String delYn;
    @Schema(description = "등록 ID")
    private String rgstId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    @Schema(description = "등록 일시")
    private LocalDateTime rgstDt;
    @Schema(description = "수정 ID")
    private String modiId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    @Schema(description = "수정 일시")
    private LocalDateTime modiDt;
    @Schema(description = "뷰 건수")
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
