package com.cdp.portal.app.facade.user.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.cdp.portal.app.facade.feature.dto.request.FeatureReqDto;
import com.cdp.portal.app.facade.user.dto.request.UserMgmtReqDto;
import com.cdp.portal.app.facade.user.dto.response.UserFeatureResDto.UserFeatures;
import com.cdp.portal.common.dto.ApiResDto;
import com.cdp.portal.common.dto.PagingDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "사용자관리 응답")
public class UserMgmtResDto {

	public static class ApiResUsers extends ApiResDto<UserMgmtResDto.UsersResult> {}
	public static class ApiResUser extends ApiResDto<User> {}

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "사용자 상세")
    public static class User {

    	@Schema(description = "행번", nullable = false)
    	private int rownum;

        @Schema(description = "사용자 ID", nullable = false)
        private String userId;

        @Schema(description = "사용자 명", nullable = false)
        private String userNm;

        @Schema(description = "사용자 이메일", example = "")
        private String userEmail;

        @Schema(description = "부서(팀)코드", example = "")
        private String deptCode;

        @Schema(description = "부서(팀)명", example = "")
        private String deptNm;

        @Schema(description = "상위 부서코드", example = "")
        private String upDeptCode;

        @Schema(description = "상위 부서명", example = "")
        private String upDeptNm;

        @Schema(description = "그룹코드(예외)", example = "")
        private String groupCode;

        @Schema(description = "그룹명(예외)", example = "")
        private String groupNm;

        @Schema(description = "회사코드", example = "")
        private String companyCode;

        @Schema(description = "마지막 로그인 일시", example = "2021-04-13 09:04:40")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private String lastLogDt;

        @Schema(description = "이전 부서(팀)코드", example = "")
        private String bfDeptCode;

        @Schema(description = "부서(팀) 변경 일시", example = "2021-04-13 09:04:40")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private String deptUpdtDt;

        @Schema(description = "이전 상위 부서코드", example = "")
        private String bfUpDeptCode;

        @Schema(description = "상위 부서 변경 일시", example = "2021-04-13 09:04:40")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private String upDeptUpdtDt;

        @Schema(description = "이전 그룹코드(예외)", example = "")
        private String bfGroupCode;

        @Schema(description = "그룹코드(예외) 변경 일시", example = "2021-04-13 09:04:40")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private String groupUpdtDt;

        @Schema(description = "재직구분", example = "Y")
        private String useYn;

        @Schema(description = "수정구분", example = "")
        private String modiSe;

        @Schema(description = "적용 사용자권한 ID - 최종 적용된 부서(팀)/예외그룹 중 예외그룹 우선 권한", example = "")
        private String apldUserAuthId;

        @Schema(description = "적용 사용자권한명 - 최종 적용된 부서(팀)/예외그룹 중 예외그룹 우선 권한", example = "")
        private String apldUserAuthNm;

        @Schema(description = "적용 관리자권한 ID - 최종 적용된 부서(팀)/예외그룹 중 예외그룹 우선 권한", example = "")
        private String apldMgrAuthId;

        @Schema(description = "적용 관리자권한명 - 최종 적용된 부서(팀)/예외그룹 중 예외그룹 우선 권한", example = "")
        private String apldMgrAuthNm;

        @Schema(description = "이전 적용 사용자권한 ID - 최종 적용된 이전 부서(팀)/예외그룹 중 예외그룹 우선 권한", example = "")
        private String bfApldUserAuthId;

        @Schema(description = "이전 적용 사용자권한명 - 최종 적용된 이전 부서(팀)/예외그룹 중 예외그룹 우선 권한", example = "")
        private String bfApldUserAuthNm;

        @Schema(description = "이전 적용 관리자권한 ID - 최종 적용된 이전 부서(팀)/예외그룹 중 예외그룹 우선 권한", example = "")
        private String bfApldMgrAuthId;

        @Schema(description = "이전 적용 관리자권한명 - 최종 적용된 이전 부서(팀)/예외그룹 중 예외그룹 우선 권한", example = "")
        private String bfApldMgrAuthNm;

        @Schema(description = "사용자권한 ID", example = "")
        private String userAuthId;

        @Schema(description = "사용자권한명", example = "")
        private String userAuthNm;

        @Schema(description = "관리자권한 ID", example = "")
        private String mgrAuthId;

        @Schema(description = "관리자권한명", example = "")
        private String mgrAuthNm;

        @Schema(description = "예외그룹 사용자권한 ID", example = "")
        private String eUserAuthId;

        @Schema(description = "예외그룹 사용자권한명", example = "")
        private String eUserAuthNm;

        @Schema(description = "예외그룹 관리자권한 ID", example = "")
        private String eMgrAuthId;

        @Schema(description = "예외그룹 관리자권한명", example = "")
        private String eMgrAuthNm;

        @Schema(description = "이전 사용자권한 ID", example = "")
        private String bfUserAuthId;

        @Schema(description = "이전 사용자권한명", example = "")
        private String bfUserAuthNm;

        @Schema(description = "이전 관리자권한 ID", example = "")
        private String bfMgrAuthId;

        @Schema(description = "이전 관리자권한명", example = "")
        private String bfMgrAuthNm;

        @Schema(description = "이전 예외그룹 사용자권한 ID", example = "")
        private String bfEUserAuthId;

        @Schema(description = "이전 예외그룹 사용자권한명", example = "")
        private String bfEUserAuthNm;

        @Schema(description = "이전 예외그룹 관리자권한 ID", example = "")
        private String bfEMgrAuthId;

        @Schema(description = "이전 예외그룹 관리자권한명", example = "")
        private String bfEMgrAuthNm;

        @Schema(description = "등록자ID", example = "admin")
        private String rgstId;

        @Schema(description = "등록일시", example = "2021-04-13 09:04:40")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime rgstDt;

        @Schema(description = "수정자ID", example = "admin")
        private String modiId;

        @Schema(description = "수정일시", example = "2021-04-13 09:04:40")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime modiDt;
    }

    @Getter
    @ToString
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Schema(description = "사용자 목록 결과")
    public static class UsersResult {

        @Schema(description = "컨텐츠 정보", nullable = false)
        private List<User> contents;

        @Schema(description = "검색 정보", nullable = false)
        private UserMgmtReqDto.SearchUser search;

        @Schema(description = "페이지 정보", nullable = false)
        private PagingDto page;
    }
}
