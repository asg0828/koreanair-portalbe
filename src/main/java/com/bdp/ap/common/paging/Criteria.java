package com.bdp.ap.common.paging;

import java.io.Serializable;

import lombok.Data;

/**
 * 페이징 처리 모델
 * 페이징 셋팅은 role.jsp파일 참고
 */
@Data
public class Criteria implements Serializable {

    private int page;               // 현재페이지 번호
    private int pageSize;           // 페이지 사이즈
    private int pageOffset;         // 페이지 Offset : MySQL 서버 쿼리 오류 해결을 위해 추가
    
    private int totalPage;          // 전체페이지수
    private int totalCount;         // 전체데이터카운트
    private String searchKey;       // 검색키
    private String searchValue;     // 검색값
    private String groupId;         // 코드그룹 검색조건
    private String codeId;         // 코드 검색조건
    private String searchUseYn;           // 사용여부
    private String searchCompanyCode;       // 회사구분
    private String searchRoleCode;       // 권한구분
    private String searchQnaStat;       // Q&A 답변 상태
    private String searchRegistYn; // 등록여부
    private String searchSystemCode; // 시스템코드
    private String searchDeptCode;    // 관리부서코드
    private String searchTableName;     //테이블명
    private String searchJobName;       //job명
    private String sortType;                // 정렬조건 (사번, 최근로그인일시, 사용기간)
    private String sortDirection;           // 정렬방향 (오름차순, 내림차순)
    private String startDt;        // 조회 시작
    private String endDt;          // 조회 종료
    private String status;         // 상태
    private String useYn;
    private String authId;
    private String authNm;
    private String activeYn;
    private String type; //데이터 수집/파기 타입
    private String delYn;

    public Criteria() {
        this.page = 1;
        this.pageSize = 10;
        this.pageOffset = 0;
        this.searchKey = "ALL";
        this.searchValue = "";
    }

    // page 정보 가져오기
    public int getPage() {
        return page;
    }
    // page 정보 설정하기
    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;
            this.pageOffset = 0;
        } else {
            this.page = page;
            this.pageOffset = (page - 1) * this.pageSize;
        }
    }

    /**
     * 전체 데이터 카운트를 가지고 전체페이지 수를 계산한다. 페이징 공통함수 사용을 위한 처리
     *
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        int tempPage = totalCount / pageSize;

        if (totalCount % pageSize != 0) tempPage++;
        this.totalPage = tempPage;
    }

    public int getTotalCount() {
        return this.totalCount;
    }
}
