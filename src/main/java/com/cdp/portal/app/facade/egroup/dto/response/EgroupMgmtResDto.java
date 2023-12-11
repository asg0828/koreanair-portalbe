package com.cdp.portal.app.facade.egroup.dto.response;

import java.util.List;

import com.cdp.portal.app.facade.egroup.dto.request.EgroupMgmtReqDto;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "사용자 예외그룹 관리 응답")
public class EgroupMgmtResDto {
	public static class ApiResEgroups extends ApiResDto<EgroupMgmtResDto.EgroupsResult> {}
	public static class ApiResEgroupUsers extends ApiResDto<EgroupMgmtResDto.EgroupUser> {}

	@Getter
    @Setter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "예외그룹 상세")
    public static class Egroup {

        @Schema(description = "그룹 코드", nullable = false)
        private String groupCode;

        @Schema(description = "그룹 명", example = "", nullable = false)
        private String groupNm;

        @Schema(description = "상위 그룹코드", example = "")
        private String upGroupCode;

        @Schema(description = "상위 그룹명", example = "")
        private String upGroupNm;

        @Schema(description = "순서", example = "")
        private int ordSeq;

        @Schema(description = "사용자권한 ID", example = "")
        private String userAuthId;

        @Schema(description = "사용자권한명", example = "")
        private String userAuthNm;

        @Schema(description = "관리자권한 ID", example = "")
        private String mgrAuthId;

        @Schema(description = "관리자권한명", example = "")
        private String mgrAuthNm;
    }

	@Getter
	@Setter
	@ToString
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
	@Schema(description = "예외그룹별 사용자")
	public static class EgroupUser {

		@Schema(description = "사용자 ID", nullable = false)
		private String userId;

		@Schema(description = "사용자 명", nullable = false)
		private String userNm;

		@Schema(description = "그룹 코드", nullable = false)
		private String groupCode;

		@Schema(description = "부서 코드", nullable = false)
		private String deptCode;

		@Schema(description = "부서 명", nullable = false)
		private String deptNm;

	}

	@Getter
    @ToString
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Schema(description = "예외그룹 목록 결과")
    public static class EgroupsResult {

        @Schema(description = "컨텐츠 정보", nullable = false)
        private List<Egroup> contents;

        @Schema(description = "검색 정보", nullable = false)
        private EgroupMgmtReqDto.SearchEgroup search;

    }
}
