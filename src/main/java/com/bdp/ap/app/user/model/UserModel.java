package com.bdp.ap.app.user.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserModel {
	private String rownum;          // 순번
    private String userId;        // 사번
    private String userNm;        // 사용자명
    private String pstnCode;           // 직위코드
    private String pstnNm;           // 직위명
    private String deptCode;           // 부서코드
    private String deptNm;           // 부서명
    private String hdeptCode;           // 상위부서코드
    private String rankCode;        //직급코드
    private String rankNm;          //직급명 
    private String dutyCode;        //직책코드
    private String dutyNm;          //직책명
    private String companyCode;       // 회사코드
    private String companyNm;       // 회사명
    private String adofDeptCode; //겸직 부서
    private String adofDeptNm;   //겸직 부서명 
    private String authId;          // 권한ID
    private String authUseYn;       // 권한 사용 여부
    private String authNm;          // 권한명
    private String useYn;           // 사용여부
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLogDt;  // 마지막로그일시
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startDt;    // 시작일시
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endDt;      // 종료일시
    private String rgstId;              // 등록자ID
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDt;     // 등록일시
    private String modiId;              // 수정자ID
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modiDt;     // 수정일시
    private String expiredYn;// 계정 만료 :  - 접속일이 start_dt와 end_dt 사이에 있는지    
    private String fileUrl; // 사진 파일 URL
    
}
