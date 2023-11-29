package com.cdp.portal.app.facade.dept.dto.response;

import java.util.List;

import com.cdp.portal.app.facade.dept.dto.request.DeptMgmtReqDto;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "부서관리 응답")
public class DeptMgmtResDto {
	public static class ApiResDepts extends ApiResDto<DeptMgmtResDto.DeptsResult> {}

	@Getter
    @Setter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "부서 상세")
    public static class Dept {

		@Schema(description = "행번", nullable = false)
    	private int rowmum;

        @Schema(description = "부서(팀)코드", nullable = false)
        private String deptCode;

        @Schema(description = "부서(팀)명", example = "")
        private String deptNm;

        @Schema(description = "상위 부서코드", example = "")
        private String upDeptCode;

        @Schema(description = "상위 부서명", example = "")
        private String upDeptNm;

        @Schema(description = "순서", example = "")
        private int ordSeq;

        @Schema(description = "회사코드", example = "")
        private String companyCode;

        @Schema(description = "사용여부", example = "Y")
        private String useYn;

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
    @ToString
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Schema(description = "부서 목록 결과")
    public static class DeptsResult {

        @Schema(description = "컨텐츠 정보", nullable = false)
        private List<Dept> contents;

        @Schema(description = "검색 정보", nullable = false)
        private DeptMgmtReqDto.SearchDept search;

    }
}
