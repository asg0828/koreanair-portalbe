package com.bdp.ap.app.board.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AnlzSourceModel implements Serializable {
/*
CREATE TABLE ptl.t_bbs_anlzsource (
	anlzsource_id varchar(32) NOT NULL, -- 공지사항 ID
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
	CONSTRAINT t_bbs_anlzsource_pk PRIMARY KEY (anlzsource_id)
);
COMMENT ON TABLE ptl.t_bbs_anlzsource IS '게시판 공지사항';

-- Column comments
COMMENT ON COLUMN ptl.t_bbs_anlzsource.anlzsource_id IS '공유소스 ID';
COMMENT ON COLUMN ptl.t_bbs_anlzsource.sj IS '제목';
COMMENT ON COLUMN ptl.t_bbs_anlzsource.cn IS '내용';
COMMENT ON COLUMN ptl.t_bbs_anlzsource.use_yn IS '사용 여부';
COMMENT ON COLUMN ptl.t_bbs_anlzsource.rgst_id IS '등록 ID';
COMMENT ON COLUMN ptl.t_bbs_anlzsource.rgst_dt IS '등록 일시';
COMMENT ON COLUMN ptl.t_bbs_anlzsource.modi_id IS '수정 ID';
COMMENT ON COLUMN ptl.t_bbs_anlzsource.modi_dt IS '수정 일시';
 */
    private String anlzSourceId;
    private String sj;
    private String cn;
    private int viewCnt;
    private String sourceType;
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
    private String userNm;
    private int rownum;             // 화면 순번
    private String rgstNm;
    private String rgstDeptNm;

    private String deptCode;           // 부서코드
    private String deptNm;           // 부서명
    private String companyCode;       // 회사코드
    private String companyNm;       // 회사명

    private String[] fileIds;

    //, CASE WHEN DATE(NOW()) BETWEEN DATE(COALESCE(U.START_DT,NOW())) AND DATE(COALESCE(U.END_DT,NOW())) THEN 'Y' ELSE 'N' END AS PUBLISH_YN
    // use_yn이 'Y'라고 하더라도, 위 조건에서 publishYn가 'N'으로 나오면, 사용자단에 노출하지 않는다.
    private String publishYn;

    private String nextSj;
    private String nextId;
    private String preSj;
    private String preId;
    private String pstnNm;
    private String userId;
    private String readAuthYn;
    private ArrayList<String> userIdList;
}
