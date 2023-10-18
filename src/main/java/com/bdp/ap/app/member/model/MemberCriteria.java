package com.bdp.ap.app.member.model;

import java.util.ArrayList;
import java.util.Map;

import com.bdp.ap.common.paging.Criteria;

import lombok.Data;

/**
 * 페이징 처리 모델
 * 페이징 셋팅은 role.jsp파일 참고
 */
@Data
public class MemberCriteria extends Criteria {

    private String deptCode;
    private String fancytreeKey;
    private String deptNm;
    private String userNm;
    private String filterLockYn;
    private String userId;
    private String roles;
    private String mgrRoles;

}
