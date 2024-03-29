package com.cdp.portal.app.bo.oneid.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.menu.dto.response.MenuMgmtResDto.ApiResMenus;
import com.cdp.portal.app.facade.oneid.dto.common.BaseSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.common.GridData;
import com.cdp.portal.app.facade.oneid.dto.common.GridResponseVO;
import com.cdp.portal.app.facade.oneid.dto.common.Pagination;
import com.cdp.portal.app.facade.oneid.dto.request.ErrorLogSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.response.CtiVocReportDTO;
import com.cdp.portal.app.facade.oneid.dto.response.CtiVocReportSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.response.DailyReportDTO;
import com.cdp.portal.app.facade.oneid.dto.response.DailyReportSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.response.ErrorLogDTO;
import com.cdp.portal.app.facade.oneid.dto.response.SamePnrReportDTO;
import com.cdp.portal.app.facade.oneid.service.OneIdLogService;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.constants.OneidConstants;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/oneid")
@Tag(name = "oneid-log")
public class BoOneIdLogController {

	private final OneIdLogService oneIdLogService;

    @Operation(summary = "OneID 에러 이력 조회", description = "OneID 에러 이력 조회")
    @GetMapping(value = "/v1/error-log")
    public ResponseEntity<GridResponseVO<GridData>> getErrorLog(
            @RequestParam(defaultValue = "10", name = "pageSize") int pageSize,
            @RequestParam(defaultValue = "1", name = "page") int page,
            @ModelAttribute ErrorLogSearchDTO inDTO) {

        Pagination paging = Pagination.builder()
                .page(page)
                .pageSize(pageSize)
                .build();

        BaseSearchDTO<ErrorLogSearchDTO> baseSearchDTO = BaseSearchDTO.<ErrorLogSearchDTO>builder().paging(paging).search(inDTO).build();
        paging.setTotalCount(oneIdLogService.getCountErrorLog(baseSearchDTO));
        paging.setTotalPage((int)Math.ceil((float)paging.getTotalCount() / pageSize));

        return new GridResponseVO().data(GridData.<ErrorLogDTO>builder()
                .contents(oneIdLogService.getErrorLog(baseSearchDTO))
                .page(paging).build()).successResponse(OneidConstants.SUCCESS);
    }

    @Operation(summary = "One-ID Daily Report", description = "One-ID Daily Report를 조회한다", tags = { "oneid" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResMenus.class)))
    }
    )
    @GetMapping(value = "/v1/daily-report", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDailyReport(
            @RequestParam(defaultValue = "10", name = "pageSize") int pageSize,
            @RequestParam(defaultValue = "1", name = "page") int page,
            @ModelAttribute DailyReportSearchDTO inDTO) {

        Pagination paging = Pagination.builder()
                .page(page)
                .pageSize(pageSize)
                .build();

        BaseSearchDTO<DailyReportSearchDTO> baseSearchDTO = BaseSearchDTO.<DailyReportSearchDTO>builder().paging(paging).search(inDTO).build();
        paging.setTotalCount(oneIdLogService.getCountDailyReport(baseSearchDTO));
        paging.setTotalPage((int)Math.ceil((float)paging.getTotalCount() / pageSize));

        return new GridResponseVO().data(GridData.<DailyReportDTO>builder()
                .contents(oneIdLogService.getDailyReport(baseSearchDTO))
                .page(paging).build()).successResponse(OneidConstants.SUCCESS);
    }

    @Operation(summary = "OneID CTI/VOC Report 조회", description = "OneID CTI/VOC Report를 조회한다", tags = { "oneid" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResMenus.class)))
    }
    )
    @GetMapping(value = "/v1/cti-voc-report", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCtiVocReport(
            @RequestParam int pageSize,
            @RequestParam(defaultValue = "1", name = "page") int page,
            @ModelAttribute CtiVocReportSearchDTO inDTO) {

        Pagination paging = Pagination.builder()
                .page(page)
                .pageSize(pageSize)
                .build();

        BaseSearchDTO<CtiVocReportSearchDTO> baseSearchDTO = BaseSearchDTO.<CtiVocReportSearchDTO>builder().paging(paging).search(inDTO).build();
        paging.setTotalCount(oneIdLogService.getCountCtiVocReport(baseSearchDTO));
        paging.setTotalPage((int)Math.ceil((float)paging.getTotalCount() / pageSize));

        return new GridResponseVO().data(GridData.<CtiVocReportDTO>builder()
                .contents(oneIdLogService.getCtiVocReport(baseSearchDTO))
                .page(paging).build()).successResponse(OneidConstants.SUCCESS);
    }

    @Operation(summary = "Same Pnr Report 조회", description = "OneID Same Pnr Report를 조회한다", tags = { "oneid" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResMenus.class)))
    }
    )
    @GetMapping(value = "/v1/same-pnr", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSamePnrReport(
            @RequestParam int pageSize,
            @RequestParam(defaultValue = "1", name = "page") int page) {

        Pagination paging = Pagination.builder()
                .page(page)
                .pageSize(pageSize)
                .build();

        BaseSearchDTO<SamePnrReportDTO> baseSearchDTO = BaseSearchDTO.<SamePnrReportDTO>builder().paging(paging).build();
        paging.setTotalCount(oneIdLogService.getCountSamePnrReport(baseSearchDTO));
        paging.setTotalPage((int)Math.ceil((float)paging.getTotalCount() / pageSize));

        return new GridResponseVO().data(GridData.<SamePnrReportDTO>builder()
                .contents(oneIdLogService.getSamePnrReport(baseSearchDTO))
                .page(paging).build()).successResponse(OneidConstants.SUCCESS);
    }

}
