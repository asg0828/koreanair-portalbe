package com.cdp.portal.app.bo.faq.controller;

import com.cdp.portal.app.facade.faq.dto.request.FaqReqDto;
import com.cdp.portal.app.facade.faq.dto.response.FaqResDto;
import com.cdp.portal.app.facade.faq.service.FaqService;

import com.cdp.portal.app.facade.faq.dto.request.FaqReqDto;
import com.cdp.portal.app.facade.faq.dto.response.FaqResDto;
import com.cdp.portal.common.IdUtil;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/board")
@Tag(name = "faq", description = "FAQ 관리 API")
public class FaqController {

    @Resource
    private IdUtil idUtil;
    private final FaqService faqService;

    @Operation(summary = "FAQ 전체 목록 조회 API", description = "FAQ 전체 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = FaqResDto.FaqResDtoResult.class)))
        }
    )
    @GetMapping(value = "/v1/faq/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFaqs() {
        return ResponseEntity.ok(ApiResDto.success(faqService.getFaqAllList()));
    }

    @Operation(summary = "FAQ 상세 조회 API", description = "FAQ 상세를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = FaqResDto.FaqResDtoResult.class)))
    }
    )
    @GetMapping(value = "/v1/faq/{faqId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> selectFaq(@PathVariable String faqId){
        faqService.addViewCntFaq(faqId);

        return ResponseEntity.ok(ApiResDto.success(faqService.getFaq(faqId)));
    }

    @Operation(summary = "FAQ 등록 API", description = "FAQ를 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = FaqResDto.FaqResDtoResult.class)))
    }
    )
    @PostMapping(value = "/v1/faq", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createFaq(@Valid @RequestBody FaqReqDto.CreateFaqReq dto) {
        String faqId = idUtil.getFaqId();
        dto.setFaqId(faqId);
        faqService.createFaq(dto);

        return ResponseEntity.ok(ApiResDto.success(faqId));
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

    @Operation(summary = "FAQ 삭제 API", description = "FAQ를 SOFT 삭제한다.(del_yn)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = FaqResDto.FaqResDtoResult.class)))
    }
    )
    @PostMapping(value = "/v1/faq/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteFaq(@Valid @RequestBody FaqReqDto.DeleteFaqReq dto) {
        dto.setModiId("admin"); // 사용자 정보 받아오기 전까지 일단 하드코딩
        faqService.deleteFaq(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "FAQ 삭제 API", description = "FAQ를 완전 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = FaqResDto.FaqResDtoResult.class)))
    }
    )
    @DeleteMapping(value = "/v1/faq", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteFaq1(@Valid @RequestBody FaqReqDto.DeleteFaqReq dto) {
        faqService.deleteFaq2(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

}
