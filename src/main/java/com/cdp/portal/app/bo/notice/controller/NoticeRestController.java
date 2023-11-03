package com.cdp.portal.app.bo.notice.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.constants.CommonConstants;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cdp.portal.app.facade.notice.dto.request.NoticeReqDto;
import com.cdp.portal.app.facade.notice.dto.response.NoticeResDto;
import com.cdp.portal.app.facade.notice.service.NoticeService;
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
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/board")
@Tag(name = "board", description = "게시물 관리 API")
public class NoticeRestController {

    @Resource
    private IdUtil idUtil;
    private final NoticeService noticeService;

    @Operation(summary = "공지사항 전체 목록 조회 API", description = "공지사항 전체 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = NoticeResDto.NoticeResDtoResult.class)))
    }
    )

    @GetMapping(value = "/v1/notice/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNotices() {
        return ResponseEntity.ok(ApiResDto.success(noticeService.getNoticeAllList()));
    }

    @Operation(summary = "공지사항 상세 조회 API", description = "공지사항 상세를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = NoticeResDto.NoticeResDtoResult.class)))
    }
    )
    @GetMapping(value = "/v1/notice/{noticeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> selectNotice(@PathVariable String noticeId){
        noticeService.addViewCntNotice(noticeId);

        return ResponseEntity.ok(ApiResDto.success(noticeService.getNotice(noticeId)));
    }

    @Operation(summary = "공지사항 등록 API", description = "공지사항을 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = NoticeResDto.NoticeResDtoResult.class)))
    }
    )
    @PostMapping(value = "/v1/notice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNotice(@Valid @RequestBody NoticeReqDto.CreateNoticeReq dto) {
        String noticeId = idUtil.getNoticeId();
        dto.setNoticeId(noticeId);
        dto.setModiId("admin");
        noticeService.createNotice(dto);

        return ResponseEntity.ok(ApiResDto.success(noticeId));
    }

    @Operation(summary = "공지사항 수정", description = "공지사항을 수정한다.", tags = { "notice" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @Parameter(name ="noticeId", required = true, description = "공지사항 ID", example = "1")
    @PutMapping(value = "/v1/notice/{noticeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateNotice(@PathVariable String noticeId, @Valid @RequestBody NoticeReqDto.UpdateNoticeReq dto) {
        noticeService.updateNotice(noticeId, dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "공지사항 삭제 API", description = "공지사항을 SOFT 삭제한다.(del_yn)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = NoticeResDto.NoticeResDtoResult.class)))
    }
    )
    @PostMapping(value = "/v1/notice/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteNotice(@Valid @RequestBody NoticeReqDto.DeleteNoticeReq dto) {
        dto.setModiId("admin"); // 사용자 정보 받아오기 전까지 일단 하드코딩
        noticeService.deleteNotice(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "공지사항 삭제 API", description = "공지사항을 완전 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = NoticeResDto.NoticeResDtoResult.class)))
    }
    )
    @DeleteMapping(value = "/v1/notice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteNotice1(@Valid @RequestBody NoticeReqDto.DeleteNoticeReq dto) {
        noticeService.deleteNotice2(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

}

