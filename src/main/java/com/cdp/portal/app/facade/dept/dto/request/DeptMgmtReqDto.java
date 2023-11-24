package com.cdp.portal.app.facade.dept.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "부서관리 요청")
public class DeptMgmtReqDto {
	
	@Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "부서 저장(C, U, D)")
	public static class SaveDept {
		@NotBlank
	    @Schema(description = "부서 코드", example = "", nullable = false)
	    private String deptCode;

		@NotBlank
	    @Schema(description = "부서 명", example = "", nullable = false)
	    private String deptNm;

	    @Schema(description = "상위 부서 코드", example = "")
	    private String upDeptCode;

	    @Schema(description = "정렬 순서", example = "")
	    private int ordSeq;
	    
	    @Schema(description = "사용자권한 ID", example = "")
        private String userAuthId;
        
        @Schema(description = "관리자권한 ID", example = "")
        private String mgrAuthId;
        
        @Schema(description = "오퍼레이터 구분[C:생성, U:수정, D:삭제]", example = "")
        private String oprtrSe;
	}
	
    @Getter
    @Schema(description = "부서 검색")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class SearchDept {
        
        @Schema(description = "부서 명", example = "")
        private String deptNm;
        
    }

}
