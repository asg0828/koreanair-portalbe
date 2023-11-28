package com.cdp.portal.app.bo.menu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.menu.dto.request.MenuMgmtReqDto;
import com.cdp.portal.app.facade.menu.dto.response.MenuMgmtResDto.ApiResMenus;
import com.cdp.portal.app.facade.menu.service.MenuMgmtMgrService;
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
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/menu-mgmt")
@Tag(name = "menu-mgmt", description = "메뉴 관리 API")
public class BoMenuMgmtMgrController {
	private final MenuMgmtMgrService menuMgmtMgrService;

	@Operation(summary = "메뉴 저장 - 관리자", description = "메뉴 관리 목록 [관리자] 정보를 저장 한다.", tags = { "menu-mgmt" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @PostMapping(value = "/v1/mgr-menus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveMenus(@Valid @RequestBody List<MenuMgmtReqDto.SaveMenu> dto) {
		menuMgmtMgrService.saveMenus(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

	@Operation(summary = "메뉴 목록 조회 - 관리자", description = "메뉴 관리 목록 [관리자] 메뉴를 조회 한다.", tags = { "menu-mgmt" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResMenus.class)))
        }
    )
    @Parameter(name ="menuNm", required = false, description = "메뉴명", example = "")
    @GetMapping(value = "/v1/mgr-menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMenus(
            @RequestParam(value ="menuNm", required = false, defaultValue = "" ) String menuNm) {

		MenuMgmtReqDto.SearchMenu searchDto = MenuMgmtReqDto.SearchMenu.builder()
        		.menuNm(menuNm)
                .build();

        return ResponseEntity.ok(ApiResDto.success(menuMgmtMgrService.getDepts(searchDto)));
    }
}
