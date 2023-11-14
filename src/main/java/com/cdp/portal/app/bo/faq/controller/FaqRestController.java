package com.cdp.portal.app.bo.faq.controller;

import com.cdp.portal.app.facade.faq.dto.request.FaqReqDto;
import com.cdp.portal.app.facade.faq.dto.response.FaqResDto;
import com.cdp.portal.app.facade.faq.service.FaqService;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/board")
@Tag(name = "faq", description = "FAQ 관리 API")
public class FaqRestController {
    private final FaqService faqService;

    @Operation(summary = "FAQ 등록", description = "FAQ를 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @PostMapping(value = "/v1/faq", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createFaq(@Valid @RequestBody FaqReqDto.CreateFaqReq dto) {
        dto.setRgstId("admin");
        dto.setModiId("admin");
        faqService.createFaq(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "FAQ 목록 조회", description = "FAQ 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = FaqResDto.FaqResDtoResult.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name = "page", required = false, description = "페이지", example = "1")
    @Parameter(name = "pageSize", required = false, description = "페이지 사이즈", example = "10")
    @Parameter(name ="searchTable", required = false, description = "검색 테이블", example = "")
    @Parameter(name ="searchConditions", required = false, description = "검색 조건", example = "")
    @GetMapping(value = "/v1/faq", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFaqs(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "searchTable", required = false, defaultValue = "") String searchTable,
            @RequestParam(value = "searchConditions", required = false, defaultValue = "") String[] searchConditions) {

        PagingDto pagingDto = PagingDto.builder()
                .page(page)
                .pageSize(pageSize)
                .build();

        FaqReqDto.SearchFaq searchDto = FaqReqDto.SearchFaq.builder()
                .searchTable(searchTable)
                .searchConditions(searchConditions)
                .build();

        return ResponseEntity.ok(ApiResDto.success(faqService.getFaqs(pagingDto,searchDto)));
    }

    @Operation(summary = "FAQ 조회", description = "FAQ를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @Parameter(name ="faqId", required = true, description = "FAQ ID", example = "fq23000000005")
    @GetMapping(value = "/v1/faq/{faqId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> selectFaq(@PathVariable String faqId){
        faqService.addViewCntFaq(faqId);

        return ResponseEntity.ok(ApiResDto.success(faqService.getFaq(faqId)));
    }

    @Operation(summary = "FAQ 수정", description = "FA를 수정한다.", tags = { "faq" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @Parameter(name ="faqId", required = true, description = "공지사항 ID", example = "1")
    @PutMapping(value = "/v1/faq/{faqId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateFaq(@PathVariable String faqId, @Valid @RequestBody FaqReqDto.UpdateFaqReq dto) {
        dto.setModiId("admin");
        faqService.updateFaq(faqId, dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "FAQ 삭제", description = "FAQ를 완전 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @Parameter(name ="faqId", required = true, description = "FAQ ID", example = "fq23000000005")
    @DeleteMapping(value = "/v1/faq/{faqId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteFaq(@PathVariable String faqId) {
        faqService.deleteFaq(faqId);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "FAQ SOFT 삭제", description = "FAQ를 SOFT 삭제한다.(del_yn)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = FaqResDto.FaqResDtoResult.class)))
    }
    )
    @PostMapping(value = "/v1/faq/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteFaq2(@Valid @RequestBody FaqReqDto.DeleteFaqReq dto) {
        dto.setModiId("admin"); // 사용자 정보 받아오기 전까지 일단 하드코딩
        faqService.deleteFaq2(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

}
