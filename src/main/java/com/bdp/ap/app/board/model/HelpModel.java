package com.bdp.ap.app.board.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class HelpModel implements Serializable {
/*
CREATE TABLE ptl.t_bbs_help (
	help_id varchar(32) NOT NULL, -- 공지사항 ID
	sj varchar(100) NULL, -- 제목
	cn varchar(1000) NULL, -- 내용
	important_yn varchar(1) NULL, -- 중요 여부
	ord_seq numeric(5) NULL, -- 정렬 순서
	file_id varchar(32) NULL, -- 파일 ID
	use_yn varchar(1) NULL, -- 사용 여부
	rgst_id varchar(32) NULL, -- 등록 ID
	rgst_dt timestamp NULL, -- 등록 일시
	modi_id varchar(32) NULL, -- 수정 ID
	modi_dt timestamp NULL, -- 수정 일시
	view_cnt numeric(9) NULL, -- 뷰 건수
	CONSTRAINT t_bbs_help_pk PRIMARY KEY (help_id)
);
COMMENT ON TABLE ptl.t_bbs_help IS '게시판 공지사항';

-- Column comments
COMMENT ON COLUMN ptl.t_bbs_help.help_id IS '공지사항 ID';
COMMENT ON COLUMN ptl.t_bbs_help.sj IS '제목';
COMMENT ON COLUMN ptl.t_bbs_help.cn IS '내용';
COMMENT ON COLUMN ptl.t_bbs_help.important_yn IS '중요 여부';
COMMENT ON COLUMN ptl.t_bbs_help.ord_seq IS '정렬 순서';
COMMENT ON COLUMN ptl.t_bbs_help.file_id IS '파일 ID';
COMMENT ON COLUMN ptl.t_bbs_help.use_yn IS '사용 여부';
COMMENT ON COLUMN ptl.t_bbs_help.rgst_id IS '등록 ID';
COMMENT ON COLUMN ptl.t_bbs_help.rgst_dt IS '등록 일시';
COMMENT ON COLUMN ptl.t_bbs_help.modi_id IS '수정 ID';
COMMENT ON COLUMN ptl.t_bbs_help.modi_dt IS '수정 일시';
COMMENT ON COLUMN ptl.t_bbs_help.view_cnt IS '뷰 건수';
 */
    private String helpId;
    private String sj;
    private String cn;
    private String importantYn;
    private int ordSeq;
    private String useYn;
    private String rgstId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDt;
    private String modiId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modiDt;
    private int viewCnt;

    private String popupYn;			//팝업사용여부
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDt;	//게시 시작일자
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDt;	//게시 종료일자

    private int rownum;             // 화면 순번
    private String rgstNm;
    private String rgstDeptNm;

    private String useYnNm;
    private String importantYnNm;

    private String[] fileIds;

    //, CASE WHEN DATE(NOW()) BETWEEN DATE(COALESCE(U.START_DT,NOW())) AND DATE(COALESCE(U.END_DT,NOW())) THEN 'Y' ELSE 'N' END AS PUBLISH_YN
    // use_yn이 'Y'라고 하더라도, 위 조건에서 publishYn가 'N'으로 나오면, 사용자단에 노출하지 않는다.
    private String publishYn;

    private String nextSj;
    private String nextId;
    private String preSj;
    private String preId;

    private String categoryCd;
    private String categoryCdNm;
    private String groupCd;
}

