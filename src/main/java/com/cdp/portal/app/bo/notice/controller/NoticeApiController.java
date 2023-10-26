package com.cdp.portal.app.bo.notice.controller;

import com.cdp.portal.app.bo.notice.dto.response.NoticeResDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.bo.notice.service.NoticeService;
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
@RequestMapping(value = "/admin/board")
@Tag(name = "board", description = "게시물 관리 API")
public class NoticeApiController {
    private final NoticeService noticeService;

    @Operation(summary = "공지사항 전체 목록 조회 API", description = "공지사항 전체 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = NoticeResDto.NoticeResDtoResult.class)))
        }
    )

    @GetMapping(value = "/notice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNotices() {
        return ResponseEntity.ok(ApiResDto.createSuccess(noticeService.getNoticeAllList()));
    }
}

