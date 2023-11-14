package com.cdp.portal.app.fo.session.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.session.dto.SessionDto;
import com.cdp.portal.app.facade.session.dto.SessionRequestDto;
import com.cdp.portal.app.facade.session.service.SessionService;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.dto.ApiResDto;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.common.error.exception.CdpPortalException;
import com.cdp.portal.common.util.SessionScopeUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_FO_PREFIX + "/session")
@Tag(name = "Session", description = "Session 컨트롤러")
public class FoSessionRestController {
	private final SessionService sessionService;
	private class ApiResSessionSpec extends ApiResDto<SessionDto> {
		@Schema(description = "상태", example = "success", nullable = true)
		private String status;
	}
	private class ApiResSessionSpecSuccess extends ApiResDto<Void> {
		@Schema(description = "상태", example = "success", nullable = true)
		private String status;
	}
	private class ApiResSessionSpecFailTokenRequired extends ApiResDto<Void> {
		@Schema(description = "상태", example = "fail", nullable = true)
		private String status;
		
		@Schema(description = "메시지", example = "GOOGLE_ACCESS_TOKEN_REQUIRED|GOOGLE_ID_TOKEN_REQUIRED", nullable = true)
	    private String message;
	}
	private class ApiResSessionSpecFailSessionExpried extends ApiResDto<Void> {
		@Schema(description = "상태", example = "fail", nullable = true)
		private String status;
		
		@Schema(description = "메시지", example = "SESSION_EXPIRE", nullable = true)
		private String message;
	}
	
	@Operation(summary = "login", description = "Google SSO 로그인 후 id token 과 access token 을 받아서 stpc application session 을 생성한다.", tags = "Session", responses = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResSessionSpec.class))),
			@ApiResponse(responseCode = "401", description = "구글 Access/Id Tokken 누락", content = @Content(schema = @Schema(implementation = ApiResSessionSpecFailTokenRequired.class))),
			@ApiResponse(responseCode = "403", description = "SESSION_EXPIRE", content = @Content(schema = @Schema(implementation = ApiResSessionSpecFailSessionExpried.class)))
			})
	@PostMapping(path = "/v1/login", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<ApiResDto<SessionDto>> login(@RequestBody SessionRequestDto sessionRequest) {

		if (ObjectUtils.isEmpty(sessionRequest.getGoogleIdToken())) {
			throw new CdpPortalException(CdpPortalError.GOOGLE_ID_TOKEN_REQUIRED);	//google id token is required
		}

		if (ObjectUtils.isEmpty(sessionRequest.getGoogleAccessToken())) {
			throw new CdpPortalException(CdpPortalError.GOOGLE_ACCESS_TOKEN_REQUIRED);	//google access token is required
		}

		return new ResponseEntity<>(sessionService.createSession(sessionRequest), HttpStatus.OK);
	}

	@Operation(summary = "logout", description = "SessionId 를 받아서 Session을 삭제한다.", tags = "Session", responses = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResSessionSpecSuccess.class))) })
	@DeleteMapping(path = "/v1/logout")
	public ResponseEntity<ApiResDto<Void>> logout() {
		if (sessionService.logout(SessionScopeUtil.getContextSession().getSessionId())) {
			return ResponseEntity.ok((ApiResDto<Void>) ApiResDto.success());
		} else {
			return ResponseEntity.ok((ApiResDto<Void>) ApiResDto.success());
		}
	}
}