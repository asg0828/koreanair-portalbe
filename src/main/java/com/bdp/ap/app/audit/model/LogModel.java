package com.bdp.ap.app.audit.model;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LogModel {

    private int rownum;             // 화면 순번
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime logDt;    // 로그일시
    private String userId;          // 사번
    private String userNm;          // 성명
    private String pstnCode;        // 직책코드
    private String pstnNm;          // 직책명
    private String deptCode;        // 부서코드
    private String deptNm;          // 부서명
    private String authId;          // 권한코드
    private String authNm;          // 권한명
    private String clientIp;        // 접속IP
    private String serverIp;       // 서버 IP
    private String rqstMethod;      // HTTP Method
    private String rqstUri;         // 요청 URI
    private String programNm;       // 프로그램 명
    private String controllerNm;    // 컨트롤러명
    private String methodNm;        // 메소드명
    private String msg;             // 파라미터

    private String sysSe;

    private String reportId;
    private String ver;
    private String tableauWorkbookId;
    private JSONObject tableauParam;
    private String tableauUserId;
}
