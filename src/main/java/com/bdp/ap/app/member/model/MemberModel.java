package com.bdp.ap.app.member.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * 사용자 모델
 */
@Data
public class MemberModel implements Serializable {
    private static final long serialVersionUID = -2186024594472091641L;
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
    private String dutySe;  //승인권 구분 Y: 승인권자 / N: 비승인권자
    private String authId;          // 권한ID
    private String authCl;          // 권한 분류
    private String authUseYn;       // 권한 사용 여부
    private String authNm;          // 권한명
    private String useYn;           // 사용여부
    private String modiSe;          // 수정 구분
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLogDt;  // 마지막로그일시
//    private String lastLogYn;           // 마지막로그일시 3개월 유무 체크 -> lockYn로 변경
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
    private String lockYn;// 계정 잠금 :  - 최종 로그인 180일 경과
    private String mgrYn;// 관리자 여부
    private String fileId; // 사진 파일 ID
    private String fileUrl; // 사진 파일 URL
    private String clientIp; // 사용자 접속 IP
    private String mgrSysEnv; // 관리자 시스템 환경
    private String userSysEnv; // 사용자 시스템 환경
    private String bfDeptCode;// 이전 부서 코드
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deptUpdtDt;// 부서 변경 일시
    private String userSysHome;
    private String mgrAuthId;
    private String mgrAuthNm;
    private String userAuthChange;
    private String mgrAuthChange;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String beforeMgrAuthNm;
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String beforeAuthNm;

    //태블로 배치에서 사용
    private String tableauId;
    private String awsId;
    private String tableauUserId;	//태블로유저ID (efa22b06-3e21-430f-9a0d-1bf3378a3259 형식)
}
