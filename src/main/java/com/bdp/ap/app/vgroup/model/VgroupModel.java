package com.bdp.ap.app.vgroup.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.bdp.ap.common.paging.Criteria;

import lombok.Data;

/**
 * 권한 데이터 모델
 */
@Data
public class VgroupModel extends Criteria{

	private String rownum;              // 순번
    private String groupId;              // 그룹ID
    private String groupNm;              // 그룹명
    private String groupDsc;            // 그룹설명
    private String useYn;               // 사용여부
    private String delYn;               // 삭제여부
    private String rgstId;              // 등록자 ID
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDt;     // 등록일시
    private String modiId;              // 수정자 ID
    private LocalDateTime modiDt;     // 수정일시

    private String userId;
    private String refId;
    private String refTy;
}
