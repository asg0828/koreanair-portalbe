package com.bdp.ap.app.board.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.bdp.ap.app.file.model.FileModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FaqModel implements Serializable {
/*
CREATE TABLE ptl.t_bbs_faq (
	faq_id varchar(32) NOT NULL, -- FAQ ID
	cl_code varchar(16) NULL, -- 분류 코드
	qstn varchar(1000) NULL, -- 질문
	answ varchar(1000) NULL, -- 답변
	ord_seq numeric(5) NULL, -- 정렬 순서
	file_id varchar(32) NULL, -- 파일 ID
	use_yn varchar(1) NULL, -- 사용 여부
	rgst_id varchar(32) NULL, -- 등록 ID
	rgst_dt timestamp NULL, -- 등록 일시
	modi_id varchar(32) NULL, -- 수정 ID
	modi_dt timestamp NULL, -- 수정 일시
	view_cnt numeric(9) NULL, -- 뷰 건수
	CONSTRAINT t_bbs_faq_pk PRIMARY KEY (faq_id)
);
COMMENT ON TABLE ptl.t_bbs_faq IS '게시판 FAQ';

-- Column comments
COMMENT ON COLUMN ptl.t_bbs_faq.faq_id IS 'FAQ ID';
COMMENT ON COLUMN ptl.t_bbs_faq.cl_code IS '분류 코드';
COMMENT ON COLUMN ptl.t_bbs_faq.qstn IS '질문';
COMMENT ON COLUMN ptl.t_bbs_faq.answ IS '답변';
COMMENT ON COLUMN ptl.t_bbs_faq.ord_seq IS '정렬 순서';
COMMENT ON COLUMN ptl.t_bbs_faq.file_id IS '파일 ID';
COMMENT ON COLUMN ptl.t_bbs_faq.use_yn IS '사용 여부';
COMMENT ON COLUMN ptl.t_bbs_faq.rgst_id IS '등록 ID';
COMMENT ON COLUMN ptl.t_bbs_faq.rgst_dt IS '등록 일시';
COMMENT ON COLUMN ptl.t_bbs_faq.modi_id IS '수정 ID';
COMMENT ON COLUMN ptl.t_bbs_faq.modi_dt IS '수정 일시';
COMMENT ON COLUMN ptl.t_bbs_faq.view_cnt IS '뷰 건수';
 */
	private String faqId;
	private String clCode;
	private String qstn;
	private String answ;
    private int ordSeq;
    private String useYn;
	private String delYn;
    private String rgstId;
    private String rgstNm;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDt;
    private String modiId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modiDt;
    private int viewCnt;

    private String faqCateNm;

    private String[] fileIds;

    //faq특성상 목록에서 각 글의 파일목록도 같이 보여줘야 함
    private List<FileModel> fileList;

}
