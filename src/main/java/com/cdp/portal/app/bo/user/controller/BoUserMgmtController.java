package com.cdp.portal.app.bo.user.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.user.dto.request.UserMgmtReqDto;
import com.cdp.portal.app.facade.user.dto.response.UserMgmtResDto.ApiResUser;
import com.cdp.portal.app.facade.user.dto.response.UserMgmtResDto.ApiResUsers;
import com.cdp.portal.app.facade.user.service.UserMgmtService;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.dto.ApiResDto;
import com.cdp.portal.common.dto.PagingDto;

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
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/user-mgmt")
@Tag(name = "user-mgmt", description = "사용자 관리 API")
public class BoUserMgmtController {
	private final UserMgmtService userMgmtService;

	@Operation(summary = "사용자 목록 조회", description = "사용자 목록을 조회한다.", tags = { "user-mgmt" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResUsers.class)))
        }
    )
    @Parameter(name ="page", required = false, description = "페이지", example = "1")
    @Parameter(name ="pageSize", required = false, description = "페이지 사이즈", example = "10")
    @Parameter(name ="userNm", required = false, description = "사용자명", example = "")
    @Parameter(name ="deptNm", required = false, description = "부서(팀)명", example = "")
    @Parameter(name ="userAuthId", required = false, description = "사용자권한 ID", example = "")
    @Parameter(name ="mgrAuthId", required = false, description = "관리자권한 ID", example = "")
    @Parameter(name ="useYn", required = false, description = "재직구분", example = "")
    @GetMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsers(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value ="userNm", required = false, defaultValue = "") String userNm,
            @RequestParam(value ="deptNm", required = false, defaultValue = "" ) String deptNm,
            @RequestParam(value ="userAuthId", required = false, defaultValue = "") String userAuthId,
            @RequestParam(value ="mgrAuthId", required = false, defaultValue = "") String mgrAuthId,
            @RequestParam(value ="useYn", required = false, defaultValue = "") String useYn) {

        PagingDto pagingDto = PagingDto.builder()
                .page(page)
                .pageSize(pageSize)
                .build();

        UserMgmtReqDto.SearchUser searchDto = UserMgmtReqDto.SearchUser.builder()
        		.userNm(userNm)
        		.deptNm(deptNm)
        		.userAuthId(userAuthId)
        		.mgrAuthId(mgrAuthId)
        		.useYn(useYn)
                .build();

        return ResponseEntity.ok(ApiResDto.success(userMgmtService.getUsers(pagingDto, searchDto)));
    }

	@Operation(summary = "사용자 조회", description = "사용자 상세를 조회한다.", tags = { "user-mgmt" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResUser.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name ="userId", required = true, description = "사용자 ID", example = "")
    @GetMapping(value = "/v1/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        return ResponseEntity.ok(ApiResDto.success(userMgmtService.getUser(userId)));
    }

}
