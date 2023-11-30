package com.cdp.portal.app.facade.menu.dto.response;

import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "메뉴권한관리 응답")
public class MenuAuthMgmtResDto {
	public static class ApiResMenuAuths extends ApiResDto<MenuAuthMgmtResDto.MenuAuthMgmtResult> {}

	@Getter
    @Setter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "메뉴권한관리 상세")
	public static class MenuAuthMgmt {

		@Schema(description = "권한 ID", example = "", nullable = false)
		private String authId;

		@Schema(description = "메뉴 ID", example = "", nullable = false)
		private String menuId;

	}

	@Getter
    @ToString
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Schema(description = "메뉴권한관리 목록 결과")
    public static class MenuAuthMgmtResult {

		@Schema(description = "권한 ID", nullable = false)
		private String authId;

        @Schema(description = "메뉴 ID", nullable = false)
        private String[] menuIds;
    }

}
