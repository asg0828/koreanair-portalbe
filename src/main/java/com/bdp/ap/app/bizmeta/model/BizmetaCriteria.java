package com.bdp.ap.app.bizmeta.model;

import com.bdp.ap.common.paging.Criteria;
import lombok.Data;

@Data
public class BizmetaCriteria extends Criteria {
	private String searchKey;                     /*검색대상*/ 
	private String searchNm;						/*검색어*/
	private String stdCoefftSe;					/*표준계수구분*/
	private String modiDt;           	   			/*최종수정일자*/
	private String stdCoefftNmChk;			/*표준계수명 선택여부*/
	private String stdCoefftEngNmChk;		/*표준계수영문명 선택여부*/
	private String calcObjChk;					/*산출목적 선택여부*/	
	private String stdCoefftFmChk;			/*표준계수수산식 선택여부*/
	private String basRptNmChk;				/*표준계수보고서 선택여부*/
	private String relRptNmChk;				/*관련보고서 선택여부*/
	private String deptCd;							/*관련부서 */
	private String deptNm;					    /*관련부서명 */
	private String userId;							/*담당자 */
	private String userNm;					 	/*담당자명 */ 
	
	
	private String martNmChk;					/*마트명 선택여부*/
	private String tblIdChk;						/*테이블ID 선택여부*/
	private String defChk;							/*정의 선택여부*/
	private String keyItmNmChk;				/*KEY 선택여부*/
	private String anlzAptChk;					/*분석관점 선택여부*/
	private String datSrcChk;					/*데이터원천 선택여부*/
	private String usDtlChk;						/*활용사례 선택여부*/
	private String topicArea;				/*주제영역*/
	private String topicAreaChk;				/*주제영역선택*/ 
	
	private String rptNmChk;					   /*보고서명 선택여부*/
	private String mainItmChk;				   /*주요항목 선택여부*/
	private String rptCl;	 				           /*보고서구분*/
	private String rptCd;						//레포트 코드 
	
	private String seId;							  /*구분ID(표준계수ID,보고서ID,분석마트ID)*/	
	private String anlzMrtId;					  /*분석마트ID*/
	private String deptSe;						 /*부서구분(01:관리부서,02:기피부서)*/	
	private String opnYn;						/*공개여부*/
	private String stdCoefftSeq;				/*표준계수이력일련번호*/
	private String rptInfId;					/*보고서정보ID*/
	private int refVer;                      /*참조버전*- 표준계수이력번호/
	
//	private String rptId;							  /*보고서ID*/
//	private String rptSeId;						  /*보고서구분ID*/	
//	
//	private String dimId;						  /*디멘젼ID*/
//	private String dimSeId;					  /*디멘젼구분ID*/
//	
//	private String modiId;						  /*메젼ID*/
//	private String mesSeId;					  /*메젼구분ID*/
//	
//	private String keyItmId;						  /*키항목ID*/
//	private String keyItmSeId;					  /*키항목구분ID*/
//	
//	private String dtMrtId;						  /*데이터마트ID*/
//	private String dtMrtSeId;					  /*데이터마트구분ID*/
//		
//	private String mngUserId;			/*관리사용자ID*/
//	private String mngUserSeId;		 /*관리담당자구분ID*/
//	
//	private String avoidDeptCd;			/*기피부서ID*/
//	private String avoidDeptSeId;		 /*기피부서구분ID*/
//	
//	private String mngDeptCd;			/*관리부서ID*/
//	private String mngDeptSeId;		 /*관리부서구분ID*/
//	
//	private String fndTagId;			/*검색태그ID*/
//	private String fndTagSeId;		 /*검색태그구분ID*/
//	
//	private String mngInfId;			/*관리정보ID*/
//	private String mngInfSeId;		 /*관리정보구분ID*/
	
}
