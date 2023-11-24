package com.cdp.portal.app.facade.auth.dto.response;

import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "권한 관리 응답")
public class AuthMgmtResDto {
	public static class ApiResAuth extends ApiResDto<AuthMgmtResDto.Auth> {}

	@Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
    @Schema(description = "권한 상세")
	public static class Auth {
		@Schema(description = "권한 ID", example = "", nullable = false)
		private String authId;
		
		@Schema(description = "권한 명", example = "", nullable = false)
		private String authNm;
		
		@Schema(description = "권한 설명", example = "")
		private String authDsc;
	}
}
