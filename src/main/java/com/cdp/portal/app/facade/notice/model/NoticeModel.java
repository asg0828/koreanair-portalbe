package com.cdp.portal.app.facade.notice.model;

import java.sql.Timestamp;
import java.util.List;

import com.cdp.portal.app.facade.file.model.FileModel;
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
    @Schema(description = "뷰 건수")
    private int viewCnt;
    @Schema(description = "팝업 여부")
    private String popupYn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "시작 일시[게시 및 팝업 노출 시작 일시]")
    private Timestamp startDt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "종료 일시[게시 및 팝업 노출 종료 일시]")
    private Timestamp endDt;
    @Schema(description = "사용 여부")
    private String useYn;
    @Schema(description = "등록 ID")
    private String rgstId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+9")
    @Schema(description = "등록 일시")
    private Timestamp rgstDt;
    @Schema(description = "수정 ID")
    private String modiId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    @Schema(description = "수정 일시")
    private Timestamp modiDt;
    @Schema(description = "삭제 여부")
    private String delYn;
    @Schema(description = "화면 순번")
    private int rownum;
    @Schema(description = "등록자 이름")
    private String rgstNm;
    @Schema(description = "부서 이름")
    private String rgstDeptNm;
    @Schema(description = "파일 아이디 목록")
    private String[] fileIds;
    @Schema(description = "파일 정보")
    private List<FileModel> fileList;
    @Schema(description = "다음 글 제목")
    private String nextSj;
    @Schema(description = "다음 글 id")
    private String nextId;
    @Schema(description = "이전 글 제목")
    private String preSj;
    @Schema(description = "이전 글 아이디")
    private String preId;

}
