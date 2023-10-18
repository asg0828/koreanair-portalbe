package com.bdp.ap.app.board.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class QnaModel implements Serializable {
/*
CREATE TABLE ptl.t_bbs_qna (
	qna_id varchar(32) NOT NULL, -- QNA ID
	cl_code varchar(16) NULL, -- 분류 코드
	sj varchar(100) NULL, -- 제목
	cn varchar(1000) NULL, -- 내용
	answ varchar(1000) NULL, -- 답변
	file_id varchar(32) NULL, -- 파일 ID
	answ_file_id varchar(32) NULL, -- 답변 파일 ID
	answ_rgst_id varchar(32) NULL, -- 답변 등록 ID
	answ_rgst_dt timestamp NULL, -- 답변 등록 일시
	bf_qna_id varchar(32) NULL, -- 이전 QNA ID
	use_yn varchar(1) NULL, -- 사용 여부
	rgst_id varchar(32) NULL, -- 등록 ID
	rgst_dt timestamp NULL, -- 등록 일시
	modi_id varchar(32) NULL, -- 수정 ID
	modi_dt timestamp NULL, -- 수정 일시
	qna_stat varchar(8) NULL, -- QNA 상태
	open_yn varchar(1) NULL, -- 공개 여부
	view_cnt numeric(9) NULL, -- 뷰 건수
	CONSTRAINT t_bbs_qna_pk PRIMARY KEY (qna_id)
);
COMMENT ON TABLE ptl.t_bbs_qna IS '게시판 QNA[QNA 관리]';

-- Column comments
COMMENT ON COLUMN ptl.t_bbs_qna.qna_id IS 'QNA ID';
COMMENT ON COLUMN ptl.t_bbs_qna.cl_code IS '분류 코드';
COMMENT ON COLUMN ptl.t_bbs_qna.sj IS '제목';
COMMENT ON COLUMN ptl.t_bbs_qna.cn IS '내용';
COMMENT ON COLUMN ptl.t_bbs_qna.answ IS '답변';
COMMENT ON COLUMN ptl.t_bbs_qna.file_id IS '파일 ID';
COMMENT ON COLUMN ptl.t_bbs_qna.answ_file_id IS '답변 파일 ID';
COMMENT ON COLUMN ptl.t_bbs_qna.answ_rgst_id IS '답변 등록 ID';
COMMENT ON COLUMN ptl.t_bbs_qna.answ_rgst_dt IS '답변 등록 일시';
COMMENT ON COLUMN ptl.t_bbs_qna.bf_qna_id IS '이전 QNA ID';
COMMENT ON COLUMN ptl.t_bbs_qna.use_yn IS '사용 여부';
COMMENT ON COLUMN ptl.t_bbs_qna.rgst_id IS '등록 ID';
COMMENT ON COLUMN ptl.t_bbs_qna.rgst_dt IS '등록 일시';
COMMENT ON COLUMN ptl.t_bbs_qna.modi_id IS '수정 ID';
COMMENT ON COLUMN ptl.t_bbs_qna.modi_dt IS '수정 일시';
COMMENT ON COLUMN ptl.t_bbs_qna.qna_stat IS 'QNA 상태';
COMMENT ON COLUMN ptl.t_bbs_qna.open_yn IS '공개 여부';
COMMENT ON COLUMN ptl.t_bbs_qna.view_cnt IS '뷰 건수';
 */
	private String qnaId;
	private String clCode;
	private String sj;	
	private String preQnaId;
	private String preSj;	
	private String nextQnaId;
	private String nextSj;	
	private String cn;
	private String answ;
	private String answRgstId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	private LocalDateTime answRgstDt;
	private String bfQnaId;
	private String useYn;
	private String useYnNm;
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
	
	private String qnaCateNm;
	private String qnaStatNm;
	private String openYnNm;
	
	private int rownum;             // 화면 순번
    private String rgstNm;
    private String rgstDeptNm;
    private String rgstPstnNm;
    
    private String answRgstNm;
    private String answDeptNm;
    private String answPstnNm;
    
    private String rgstPhotoUrl;	//등록자 사진
    private String answPhotoUrl;	//답변자 사진
    
    private String[] fileIds;
    
    private int lv; // 레벨
    private String deptCd;
	private String companyNm;
}
