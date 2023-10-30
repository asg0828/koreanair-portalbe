package com.cdp.portal.app.bo.notice.controller;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cdp.portal.app.facade.notice.dto.request.NoticeReqDto;
import com.cdp.portal.app.facade.notice.dto.response.NoticeResDto;

import com.cdp.portal.app.facade.notice.service.NoticeService;
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
@RequestMapping(value = "/board")
@Tag(name = "board", description = "게시물 관리 API")
public class NoticeRestController {
    private final NoticeService noticeService;

    @Operation(summary = "공지사항 전체 목록 조회 API", description = "공지사항 전체 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = NoticeResDto.NoticeResDtoResult.class)))
    }
    )

    @GetMapping(value = "/notice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNotices() {
        return ResponseEntity.ok(ApiResDto.success(noticeService.getNoticeAllList()));
    }

    @PostMapping(value = "/notice/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNotice(@Valid @RequestBody NoticeReqDto.CreateNoticeReq dto) {

        noticeService.createNotice(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @PostMapping(value = "notice/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteNotice(@Valid @RequestBody String noticeId) {

        noticeService.deleteNotice(noticeId);

        return ResponseEntity.ok(ApiResDto.success());
    }

    /**
     * 공지사항 수정
     * @param noticeId
     * @param dto
     * @return
     */
    @Operation(summary = "공지사항 수정", description = "공지사항을 수정한다.", tags = { "notice" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @Parameter(name ="noticeId", required = true, description = "공지사항 ID", example = "1")
    @PutMapping(value = "/notice/{noticeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateNotice(@PathVariable String noticeId, @Valid @RequestBody NoticeReqDto.CreateNoticeReq dto) {
//        NoticeService.updateNotice(noticeId, dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

}

