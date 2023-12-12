package com.cdp.portal.app.facade.menu.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "메뉴관리 요청")
public class MenuMgmtReqDto {

	@Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "메뉴 저장(C, U, D)")
	public static class SaveMenu {

		@Schema(description = "메뉴 ID - 생성(insert)시 무시됨", example = "")
		private String menuId;

		@Schema(description = "상위 메뉴 ID", example = "")
		private String upMenuId;

		@Schema(description = "메뉴 명", example = "")
		private String menuNm;

		@Schema(description = "메뉴 URL", example = "")
		private String menuUrl;

		@Schema(description = "메뉴 설명", example = "")
		private String menuDsc;

		@Schema(description = "정렬 순서", example = "")
		private int ordSeq;

		@Schema(description = "사용 여부", example = "")
		private String useYn;

		@Schema(description = "오퍼레이터 구분[C:생성, U:수정, D:삭제]", example = "")
        private String oprtrSe;

	}

	@Getter
    @Schema(description = "메뉴 검색")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class SearchMenu {

        @Schema(description = "메뉴 명", example = "")
        private String menuNm;

    }

	@Getter
	@Schema(description = "메뉴 권한 조건 검색")
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
	@Builder
	public static class SearchMenuByAuth {

		@Schema(description = "권한 ID", nullable = false)
		private String authId;

		@Schema(description = "권한 명", example = "")
		private String authNm;

	}

}
