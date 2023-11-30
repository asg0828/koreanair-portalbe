package com.cdp.portal.app.bo.menu.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.menu.dto.response.MenuAuthMgmtResDto.ApiResMenuAuths;
import com.cdp.portal.app.facade.menu.service.MenuAuthMgmtMgrService;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/menu-auth-mgmt")
@Tag(name = "menu-auth-mgmt", description = "메뉴권한 관리 API")
public class BoMenuAuthMgmtMgrController {
	private final MenuAuthMgmtMgrService menuMgmtAuthMgrService;

	@Operation(summary = "메뉴권한 저장 - 관리자", description = "메뉴권한 관리 목록 [관리자] 정보를 저장 한다.", tags = { "menu-auth-mgmt" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
		}
	)
	@PostMapping(value = "/v1/mgr-menu/{authId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveMenuAuth(
		@PathVariable String authId,
		@RequestParam(value = "menuIds", required = false, defaultValue = "") String[] menuIds) {
		menuMgmtAuthMgrService.saveMenuAuth(authId, menuIds);

		return ResponseEntity.ok(ApiResDto.success());
	}

	@Operation(summary = "메뉴권한 목록 조회 - 관리자", description = "메뉴권한 관리 목록 [관리자] 메뉴를 조회 한다.", tags = { "menu-auth-mgmt" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResMenuAuths.class)))
		}
	)
	@Parameter(name ="authId", required = false, description = "권한 ID", example = "")
	@GetMapping(value = "/v1/mgr-menu/{authId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMenuAuths(
			@PathVariable String authId) {
		return ResponseEntity.ok(ApiResDto.success(menuMgmtAuthMgrService.getMenuAuths(authId)));
	}

}
