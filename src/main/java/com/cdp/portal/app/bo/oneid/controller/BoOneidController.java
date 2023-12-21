package com.cdp.portal.app.bo.oneid.controller;

import com.cdp.portal.app.facade.oneid.dto.common.BaseSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.common.Pagination;
import com.cdp.portal.app.facade.oneid.dto.response.DailyReportSearchDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cdp.portal.app.facade.menu.dto.response.MenuMgmtResDto.ApiResMenus;
import com.cdp.portal.app.facade.oneid.service.OneidService;
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

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/oneid")
@Tag(name = "menu-mgmt", description = "메뉴 관리 API")
public class BoOneidController {
	private final OneidService oneidService;

	@Operation(summary = "메뉴 목록 조회 - 사용자", description = "메뉴 관리 목록 [사용자] 메뉴를 조회 한다.", tags = { "menu-mgmt" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResMenus.class)))
        }
    )
    @Parameter(name ="menuNm", required = false, description = "메뉴명", example = "")
    @GetMapping(value = "/v1/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMenus(
            @RequestParam(value ="menuNm", required = false, defaultValue = "" ) String menuNm) {

        return ResponseEntity.ok(ApiResDto.success(oneidService.getErrorLog()));
    }

    @Operation(summary = "One-ID Daily Report", description = "One-ID Daily Report 조회한다", tags = { "oneid" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResMenus.class)))
    }
    )
    @GetMapping(value = "/v1/oneid/daily-report", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDailyReport(
            @Valid @RequestBody DailyReportSearchDTO inDTO,
            @RequestParam int perPage,
            @RequestParam(defaultValue = "1", name = "page") int page) {

        Pagination paging = Pagination.builder()
                .page(page)
                .perPage(perPage)
                .offset((page - 1) * perPage)
                .build();

        BaseSearchDTO<DailyReportSearchDTO> baseSearchDTO = BaseSearchDTO.<DailyReportSearchDTO>builder().paging(paging).search(inDTO).build();
        paging.setTotalCount(oneidService.getCountDailyReport(baseSearchDTO));

        return ResponseEntity.ok(ApiResDto.success(oneidService.getDailyReport(baseSearchDTO)));
    }
}
