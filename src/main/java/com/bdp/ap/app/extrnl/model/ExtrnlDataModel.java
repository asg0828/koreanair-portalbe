package com.bdp.ap.app.extrnl.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 외부 데이터 모델
 */
@Data
public class ExtrnlDataModel {
	private String rownum;						//순번
	private String dataId;						//PK
	private String srcSysCode;					//소스 시스템 코드
	private String srcSysNm;					//※공통코드 코드네임
	private String dataTy;						//데이터 타입
	private String dataTyNm;					//※공통코드 코드네임
	private String dataNm;						//데이터 명
	private String dataDsc;						//데이터 설명
	private String dataItem;					//데이터 항목
	private String dataLc;						//데이터 위치
	private String rmk;							//비고
	private String purchsDeptCode;				//구매 부서 코드
	private String purchsDeptNm;				//※부서명
	private String purchsPicrId;				//구매 담당자 ID
	private String purchsPicrNm;				//※사용자명
	private String mngDeptCode;					//관리 부서 코드
	private String mngDeptNm;					//※부서명
	private String mngPicrId;					//관리 담당자 ID
	private String mngPicrNm;					//※사용자명
	private JSONArray deptInfo;					//부서 정보[사용 부서]
	private String deptInfoNm;					//부서 정보[사용 부서][이름]
	private String deptInfoCode;				//부서 정보[사용 부서][코드]
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDt;					//시작 일시
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDt;					//종료 일시
	private String srcSysUrl;					//소스 시스템 URL
	private String dataTrnsmisMthd;				//데이터 전송 방식
	private String dataTrnsmisMthdNm;			//※공통코드 코드네임
	private JSONArray dataTrnsmisCycle;		//데이터 전송 주기
	private String dataTrnsmisCycleNm;			//※공통코드 코드네임
	private String lang;						//언어
	private String langNm;						//※공통코드 코드네임
	private String rgstSe;						//등록 구분[엑셀 / 화면]
    private String useYn;               		//사용여부
    private String rgstId;              		//등록자ID
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDt;     			//등록일시
    private String modiId;              		//수정자ID
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modiDt;     			//수정일시
}
