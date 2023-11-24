package com.cdp.portal.app.facade.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "권한 관리 요청")
public class AuthMgmtReqDto {
	@Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "권한 생성")
	public static class CreateAuth{
		@Schema(description = "권한 명", example = "", nullable = false)
		private String authNm;

		@Schema(description = "권한 설명", example = "")
		private String authDsc;
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@Schema(description = "권한 수정")
	public static class UpdateAuth{
		@Schema(description = "권한 명", example = "", nullable = false)
		private String authNm;

		@Schema(description = "권한 설명", example = "")
		private String authDsc;
	}
}
