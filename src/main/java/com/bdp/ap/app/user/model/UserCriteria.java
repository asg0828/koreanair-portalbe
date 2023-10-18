package com.bdp.ap.app.user.model;

import com.bdp.ap.common.paging.Criteria;

import lombok.Data;

@Data
public class UserCriteria extends Criteria {	
    private String userId;             //사용자ID
    private String userNm;          //사용자명 
    private String deptCode;        //부서코드
    private String deptNm;          //부서명
    private String pstnCode;         // 직위코드
    private String pstnNm;           // 직위명
    private String rankCode;        //직급코드
    private String rankNm;          //직급명 
    private String dutyCode;        //직책코드
    private String dutyNm;          //직책명
    private String authId;          // 권한ID
    private String authNm;          // 권한명
    private String companyCode;  //회사코드
}
