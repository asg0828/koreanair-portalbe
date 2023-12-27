package com.cdp.portal.app.bo.oneid.controller;

import javax.validation.Valid;

import com.cdp.portal.app.facade.oneid.dto.common.*;
import com.cdp.portal.app.facade.oneid.dto.response.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.menu.dto.response.MenuMgmtResDto.ApiResMenus;
import com.cdp.portal.app.facade.oneid.dto.request.ErrorLogSearchDTO;
import com.cdp.portal.app.facade.oneid.service.OneIdLogService;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.constants.OneidConstants;
import com.cdp.portal.common.encryption.CryptoProvider;

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
    private final CryptoProvider cryptoProvider;
//    private final CleansingRule cleansingRule;

    @Operation(summary = "OneID 에러 이력 조회", description = "OneID 에러 이력 조회")
    @GetMapping(value = "/v1/error-log")
    public ResponseEntity<GridResponseVO<GridData>> getErrorLog(
            @RequestParam(defaultValue = "10", name = "perPage") int perPage,
            @RequestParam(defaultValue = "1", name = "page") int page,
            @ModelAttribute ErrorLogSearchDTO inDTO) {

        Pagination paging = Pagination.builder()
                .page(page)
                .perPage(perPage)
                .offset((page - 1) * perPage)
                .build();

        BaseSearchDTO<ErrorLogSearchDTO> baseSearchDTO = BaseSearchDTO.<ErrorLogSearchDTO>builder().paging(paging).search(inDTO).build();
        paging.setTotalCount(oneIdLogService.getCountErrorLog(baseSearchDTO));

        return new GridResponseVO().data(GridData.<ErrorLogDTO>builder()
                .contents(oneIdLogService.getErrorLog(baseSearchDTO))
                .pagination(paging).build()).successResponse(OneidConstants.SUCCESS);
    }

    @Operation(summary = "One-ID Daily Report", description = "One-ID Daily Report를 조회한다", tags = { "oneid" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResMenus.class)))
    }
    )
    @GetMapping(value = "/v1/daily-report", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDailyReport(
            @RequestParam(defaultValue = "10", name = "perPage") int perPage,
            @RequestParam(defaultValue = "1", name = "page") int page,
            @ModelAttribute DailyReportSearchDTO inDTO) {

        Pagination paging = Pagination.builder()
                .page(page)
                .perPage(perPage)
                .offset((page - 1) * perPage)
                .build();

        BaseSearchDTO<DailyReportSearchDTO> baseSearchDTO = BaseSearchDTO.<DailyReportSearchDTO>builder().paging(paging).search(inDTO).build();
        paging.setTotalCount(oneIdLogService.getCountDailyReport(baseSearchDTO));

        return new GridResponseVO().data(GridData.<DailyReportDTO>builder()
                .contents(oneIdLogService.getDailyReport(baseSearchDTO))
                .pagination(paging).build()).successResponse(OneidConstants.SUCCESS);
    }

    @Operation(summary = "OneID CTI/VOC Report 조회", description = "OneID CTI/VOC Report를 조회한다", tags = { "oneid" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResMenus.class)))
    }
    )
    @GetMapping(value = "/v1/cti-voc-report", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCtiVocReport(
            @RequestParam int perPage,
            @RequestParam(defaultValue = "1", name = "page") int page,
            @ModelAttribute CtiVocReportSearchDTO inDTO) {

        Pagination paging = Pagination.builder()
                .page(page)
                .perPage(perPage)
                .offset((page - 1) * perPage)
                .build();

        BaseSearchDTO<CtiVocReportSearchDTO> baseSearchDTO = BaseSearchDTO.<CtiVocReportSearchDTO>builder().paging(paging).search(inDTO).build();
        paging.setTotalCount(oneIdLogService.getCountCtiVocReport(baseSearchDTO));

        return new GridResponseVO().data(GridData.<CtiVocReportDTO>builder()
                .contents(oneIdLogService.getCtiVocReport(baseSearchDTO))
                .pagination(paging).build()).successResponse(OneidConstants.SUCCESS);
    }

    @Operation(summary = "Same Pnr Report 조회", description = "OneID Same Pnr Report를 조회한다", tags = { "oneid" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResMenus.class)))
    }
    )
    @GetMapping(value = "/v1/same-pnr", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSamePnrReport(
            @RequestParam int perPage,
            @RequestParam(defaultValue = "1", name = "page") int page) {

        Pagination paging = Pagination.builder()
                .page(page)
                .perPage(perPage)
                .offset((page - 1) * perPage)
                .build();

        BaseSearchDTO<SamePnrReportDTO> baseSearchDTO = BaseSearchDTO.<SamePnrReportDTO>builder().paging(paging).build();
        paging.setTotalCount(oneIdLogService.getCountSamePnrReport(baseSearchDTO));

        return new GridResponseVO().data(GridData.<SamePnrReportDTO>builder()
                .contents(oneIdLogService.getSamePnrReport(baseSearchDTO))
                .pagination(paging).build()).successResponse(OneidConstants.SUCCESS);
    }

    @Operation(summary = "CleansingRule / Hash 변환 결과 조회", description = "CleansingRule / Hash 변환 결과를 조회한다", tags = { "oneid" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResMenus.class)))
    }
    )
    @GetMapping(value = "/v1/cleansing-hash-results", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVO> getCleansingHashResults(
            @RequestParam String inptPhone,
            @RequestParam String inptEmail) {

        return new ResponseVO().data(CleansingHashResultsDTO.builder()
//                .phoneCleansingResult(cleansingRule.cleanseMobile(inptPhone))
//                .emailCleansingResult(cleansingRule.cleanseEmail(inptEmail))
                .phoneHashValue(cryptoProvider.toHashEncryptedText(inptPhone))
                .emailHashValue(cryptoProvider.toHashEncryptedText(inptEmail)).build()).successResponse(OneidConstants.SUCCESS);
    }
}
