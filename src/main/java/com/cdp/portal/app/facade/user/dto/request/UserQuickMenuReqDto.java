package com.cdp.portal.app.facade.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserQuickMenuReqDto {
	@Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "퀵메뉴 생성")
	public static class CreateQuickMenu {

		@Schema(description = "메뉴 ID", nullable = false)
		private String menuId;

	}

	@Getter
	@Schema(description = "퀵메뉴 사용자별 권한 조건 검색")
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
	@Builder
	public static class SearchQuickMenuByAuthUser {

		@Schema(description = "권한 ID", nullable = false)
		private String authId;

		@Schema(description = "사용자 ID", nullable = false)
		private String userId;

	}
}
