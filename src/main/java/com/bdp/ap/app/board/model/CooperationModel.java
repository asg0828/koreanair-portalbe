package com.bdp.ap.app.board.model;

import java.io.Serializable;

import java.util.ArrayList;

import lombok.Data;

@Data
public class CooperationModel implements Serializable {
	private String collabSpcId;                           //협업공간ID	
	private String grpCd;                               //그룹사코드 
	private String grpNm;                             //그룹사명
	private String collabNm;                         //협업명
	private String collabInitor;                     //협업개시자
	private String collabInitorNm;                //협업개시자명
	private String crtDt;								  //생성일자
	private String collabTgt;                         //협업목적및주제
	private String collabSpcDtlDesc;          //협업공간상세설명
	private String stCd;                              //상태코드
	private String stNm;                             //상태명
	private String delYn;                              //삭제여부
	private String rgstDt;                            //등록일자
	private String rgstId;                            //등록자ID
	private String modiDt;                          //수정일자
	private String modiId;                          //수정자ID
	private String rgstNm;                            //등록자명
	
    private String pcptId;								//참여자ID
    private String pcptNm;								//참여자명
    private ArrayList<String> userIdList;
    
    private String collabSpcTskId;				//협업공간TASKID
    private String collabSpcTskNm;			//협업공간TASK명
    private String sj;									//제목
    private String wrkKndCd;                    //작업유형코드
    private String wrkKndNm;                   //작업유형명
    private String picrId;							//참여자ID
    private String picrNm;							//참여자명
    private String deptCd;							//부서코드
    private String deptNm;						//부서명
    private String wrkTrmDt;						//작업기한일
    private String cntn;								//내용
    private String antate;							// 부연설명
    
    private String companyCd;                  //회사코드
    
    private String comtId;							//Comment ID
    private String comtNm;						//Comment명
    private String upComtId;						// 상위Comment ID
    
    private int seq;									//순번
        
    private String[] fileIds;                        //파일Id배열
    private String[] picrIds;                      //참여자ID배열
    private String fileId;
    private String fileNm;
    private String fileSize;
    private int lv;
    
}
