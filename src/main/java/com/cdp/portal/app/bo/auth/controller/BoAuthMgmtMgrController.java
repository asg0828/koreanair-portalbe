package com.cdp.portal.app.bo.auth.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.auth.dto.request.AuthMgmtReqDto;
import com.cdp.portal.app.facade.auth.dto.response.AuthMgmtResDto.ApiResAuth;
import com.cdp.portal.app.facade.auth.service.AuthMgmtMgrService;
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
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/auth-mgmt")
@Tag(name = "auth-mgmt", description = "권한그룹 관리 API")
public class BoAuthMgmtMgrController {

	private final AuthMgmtMgrService authMgmtMgrService;

	@Operation(summary = "권한 등록 - 관리자", description = "권한그룹 관리 목록 [관리자] 권한을 등록한다.", tags = { "auth-mgmt" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
	@PostMapping(value = "/v1/mgr-auths", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAuth(@Valid @RequestBody AuthMgmtReqDto.CreateAuth dto) {
		authMgmtMgrService.createAuth(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

	@Operation(summary = "권한 목록 조회 - 관리자", description = "권한그룹 관리 목록 [관리자] 권한 목록을 조회한다.", tags = { "auth-mgmt" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResAuth.class)))
	}
			)
	@GetMapping(value = "/v1/mgr-auths/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAuthMgmtMgrAll() {
		return ResponseEntity.ok(ApiResDto.success(authMgmtMgrService.getAuthMgmtMgrs()));
	}

    @Operation(summary = "권한 수정 - 관리자", description = "권한그룹 관리 목록 [관리자] 권한을 수정한다.", tags = { "auth-mgmt" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name ="authId", required = true, description = "권한ID", example = "")
    @PutMapping(value = "/v1/mgr-auths/{authId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAuth(@PathVariable String authId, @Valid @RequestBody AuthMgmtReqDto.UpdateAuth dto) {
    	authMgmtMgrService.updateAuth(authId, dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "권한 삭제 - 관리자", description = "권한그룹 관리 목록 [관리자] 권한을 삭제한다.", tags = { "auth-mgmt" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name ="authId", required = true, description = "권한ID", example = "")
    @DeleteMapping(value = "/v1/mgr-auths/{authId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAuth(@PathVariable String authId) {
    	authMgmtMgrService.deleteAuth(authId);

        return ResponseEntity.ok(ApiResDto.success());
    }
}
