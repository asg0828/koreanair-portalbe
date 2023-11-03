package com.cdp.portal.app.facade.session.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "Session 생성")
@ToString
public class SessionRequestDto {
	@NotBlank
	@Schema(description = "google access token", example = "xxx1234")
	private String googleAccessToken;

	@NotBlank
	@Schema(description = "google id token", example = "xxx1234")
	private String googleIdToken;
}