package com.bdp.ap.app.board.model;



import com.bdp.ap.common.paging.Criteria;

import lombok.Data;

@Data
public class CooperationCriteria extends Criteria {
	private String regStDt;							//생성시작일자
	private String regEdDt;							//생성종료일자
	private String collabInitor;				   	//협업공간개시자
	private String grpCd;                           //그룹사코드
	private String stCd;                            //상태코드	
	private String collabNm;                        //협업명
	private String collabTgt;                       //협업목적및주제
	private String companyCd;                    	//회사코드
	private String collabSpcId;                    	//협업공간ID	
	private String pcptId;							//참여자ID
	private String collabSpcTskId;					//협업공간TASKID
	private String comtId;							//Comment ID
}
