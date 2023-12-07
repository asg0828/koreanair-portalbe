package com.cdp.portal.app.fo.report.controller;

import com.cdp.portal.app.facade.report.service.ReportService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.code.dto.response.CodeResDto.ApiResCodeResDtos;
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
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/report")
@Tag(name = "report", description = "정형 보고서 API")
public class FoReportRestController {

    private final ReportService reportService;

    /**
     * VIP 고객 국제선 예약 현황
     * @return
     */
    @Operation(summary = "정형 보고서 VIP 고객현황 조회", description = "정형 보고서 VIP 고객현황을 조회한다.", tags = { "report" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResCodeResDtos.class)))
    }
    )
    @GetMapping(value = "/v1/vip", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getVipReservationStatus() {
        return ResponseEntity.ok(ApiResDto.success(reportService.getVipReservationStatus()));
    }

    /**
     * 구매기여도 TOP 100
     * @return
     */
    @Operation(summary = "구매 기여도 TOP 100 조회", description = "구매 기여도 TOP 100 조회한다.", tags = { "report" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResCodeResDtos.class)))
    }
    )
    @Parameter(name ="criteria" , required = true, description = "조회기준", example = "1 year")
    @GetMapping(value = "/v1/purchase-contribution/{criteria}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPurchaseContribution(@PathVariable String criteria) {
        return ResponseEntity.ok(ApiResDto.success(reportService.getPurchaseContribution(criteria)));
    }
}
