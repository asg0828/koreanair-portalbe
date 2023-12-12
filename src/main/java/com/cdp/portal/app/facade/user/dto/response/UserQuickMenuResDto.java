package com.cdp.portal.app.facade.user.dto.response;

import java.util.List;

import com.cdp.portal.app.facade.user.dto.request.UserQuickMenuReqDto;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class UserQuickMenuResDto {

	public static class ApiResUserQuickMenus extends ApiResDto<UserQuickMenuResDto.QuickMenuByUser> {}

	@Getter
    @Setter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "퀵메뉴")
	public static class QuickMenu {

		@Schema(description = "메뉴 ID", example = "", nullable = false)
		private String menuId;

		@Schema(description = "상위 메뉴 ID", example = "")
		private String upMenuId;

		@Schema(description = "메뉴 명", example = "", nullable = false)
		private String menuNm;

		@Schema(description = "메뉴 URL", example = "")
		private String menuUrl;

		@Schema(description = "메뉴 설명", example = "")
		private String menuDsc;

		@Schema(description = "정렬 순서", example = "")
		private int ordSeq;

		@Schema(description = "사용 여부", example = "")
		private String useYn;

	}

	@Getter
	@ToString
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
	@Schema(description = "퀵 메뉴 목록")
	public static class QuickMenuByUser {

		@Schema(description = "권한 ID", nullable = false)
		private UserQuickMenuReqDto.SearchQuickMenuByAuthUser search;

		@Schema(description = "퀵메뉴 목록", nullable = false)
		private List<QuickMenu> menus;

	}
}
