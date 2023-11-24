package com.cdp.portal.app.facade.menu.dto.response;

import java.util.List;

import com.cdp.portal.app.facade.menu.dto.request.MenuMgmtReqDto;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "메뉴관리 응답")
public class MenuMgmtResDto {
	public static class ApiResMenus extends ApiResDto<MenuMgmtResDto.MenuMgmtResult> {}

	@Getter
    @Setter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "메뉴관리 상세")
	public static class MenuMgmt {

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
    @Schema(description = "메뉴관리 목록 결과")
    public static class MenuMgmtResult {

        @Schema(description = "컨텐츠 정보", nullable = false)
        private List<MenuMgmt> contents;

        @Schema(description = "검색 정보", nullable = false)
        private MenuMgmtReqDto.SearchMenu search;

    }

}
