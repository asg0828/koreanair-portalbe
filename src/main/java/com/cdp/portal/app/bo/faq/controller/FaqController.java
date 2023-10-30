package com.cdp.portal.app.bo.faq.controller;

import com.cdp.portal.app.bo.faq.dto.response.FaqResDto;
import com.cdp.portal.app.bo.faq.service.FaqService;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/board")
@Tag(name = "faq", description = "FAQ 관리 API")
public class FaqController {

    private final FaqService faqService;

    @Operation(summary = "FAQ 전체 목록 조회 API", description = "FAQ 전체 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = FaqResDto.FaqResDtoResult.class)))
        }
    )
    @GetMapping(value = "/faq", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFaqs() {
        return ResponseEntity.ok(ApiResDto.success(faqService.getFaqAllList()));
    }
}
