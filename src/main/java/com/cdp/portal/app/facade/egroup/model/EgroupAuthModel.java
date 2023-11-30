package com.cdp.portal.app.facade.egroup.model;

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
@Schema(description = "사용자 예외그룹-권한 링크 모델")
public class EgroupAuthModel {
    @Schema(description = "그룹 코드", nullable = false)
    private String groupCode;

	@Schema(description = "권한 ID", example = "", nullable = false)
	private String authId;

	@Schema(description = "등록자ID", example = "admin")
    private String rgstId;

    @Schema(description = "수정자ID", example = "admin")
    private String modiId;
}
