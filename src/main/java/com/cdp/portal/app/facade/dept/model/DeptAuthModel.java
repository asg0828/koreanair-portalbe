package com.cdp.portal.app.facade.dept.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Schema(description = "부서-권한 링크 모델")
public class DeptAuthModel {
	@Schema(description = "부서 코드", example = "", nullable = false)
    private String deptCode;
	
	@Schema(description = "권한 ID", example = "", nullable = false)
	private String authId;
	
	@Schema(description = "등록자ID", example = "admin")
    private String rgstId;
    
    @Schema(description = "수정자ID", example = "admin")
    private String modiId;
}
