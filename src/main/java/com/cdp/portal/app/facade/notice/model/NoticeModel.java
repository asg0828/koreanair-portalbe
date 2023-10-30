package com.cdp.portal.app.facade.notice.model;

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
public class NoticeModel {
    @Schema(description = "공지사항ID", example = "NOTICE_ID")
    private String noticeId;
    @Schema(description = "제목")
    private String sj;
    @Schema(description = "내용")
    private String cn;
    @Schema(description = "중요여부")
    private String importantYn;
    @Schema(description = "정렬 순서")
    private int ordSeq;
    @Schema(description = "사용 여부")
    private String useYn;
    @Schema(description = "삭제 여부")
    private String delYn;
    @Schema(description = "등록 ID")
    private String rgstId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "등록 일시")
    private LocalDateTime rgstDt;
    @Schema(description = "수정 ID")
    private String modiId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "수정 일시")
    private LocalDateTime modiDt;
    @Schema(description = "뷰 건수")
    private int viewCnt;

    @Schema(description = "팝업 여부")
    private String popupYn;
//    private String popupYnNm;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "시작 일시[게시 및 팝업 노출 시작 일시]")
    private LocalDate startDt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "종료 일시[게시 및 팝업 노출 종료 일시]")
    private LocalDate endDt;	//게시 종료일자

//    private int rownum;             // 화면 순번
//    private String rgstNm;
//    private String rgstDeptNm;
//
//    private String useYnNm;
//    private String importantYnNm;
//
//    private String[] fileIds;
//
//    //, CASE WHEN DATE(NOW()) BETWEEN DATE(COALESCE(U.START_DT,NOW())) AND DATE(COALESCE(U.END_DT,NOW())) THEN 'Y' ELSE 'N' END AS PUBLISH_YN
//    // use_yn이 'Y'라고 하더라도, 위 조건에서 publishYn가 'N'으로 나오면, 사용자단에 노출하지 않는다.
//    private String publishYn;
//
//    private String nextSj;
//    private String nextId;
//    private String preSj;
//    private String preId;
//    private String companyNm;
}
