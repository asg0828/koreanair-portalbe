package com.cdp.portal.app.bo.user.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.user.dto.request.UserQuickMenuReqDto;
import com.cdp.portal.app.facade.user.dto.response.UserQuickMenuResDto.ApiResUserQuickMenus;
import com.cdp.portal.app.facade.user.service.UserQuickMenuMgrService;
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
@Tag(name = "user-quick-menus", description = "사용자 퀵메뉴 관리 API")
public class BoUserQuickMenuRestController {

    private final UserQuickMenuMgrService userQuickMenuMgrService;

    @Operation(summary = "사용자 퀵메뉴 등록", description = "사용자 퀵메뉴를 등록한다.", tags = { "user-quick-menus" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name ="userId", required = true, description = "사용자ID", example = "")
    @PostMapping(value = "/v1/users/{userId}/quick-menus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUserQuickMenu(@PathVariable String userId, @Valid @RequestBody UserQuickMenuReqDto.CreateQuickMenu dto) {
    	userQuickMenuMgrService.createUserQuickMenu(userId, dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "사용자 퀵메뉴 삭제", description = "사용자 퀵메뉴를 삭제한다.", tags = { "user-quick-menus" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name ="userId", required = true, description = "사용자ID", example = "")
    @Parameter(name ="menuId", required = true, description = "메뉴ID", example = "")
    @DeleteMapping(value = "/v1/users/{userId}/quick-menus/{menuId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUserQuickMenu(@PathVariable String userId, @PathVariable String menuId) {
    	userQuickMenuMgrService.deleteUserQuickMenu(userId, menuId);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "사용자 퀵메뉴 목록 조회", description = "사용자 퀵메뉴 목록을 조회한다.", tags = { "user-quick-menus" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResUserQuickMenus.class)))
        }
    )
    @Parameter(name ="userId", required = true, description = "사용자ID", example = "")
    @GetMapping(value = "/v1/users/{userId}/quick-menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserQuickMenu(
            @PathVariable String userId) {

        return ResponseEntity.ok(ApiResDto.success(userQuickMenuMgrService.getUserQuickMenus(userId)));
    }

}
