package com.cdp.portal.app.bo.notice.controller;

import javax.validation.Valid;

import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.dto.PagingDto;
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
    private final NoticeService noticeService;

    @Operation(summary = "공지사항 등록", description = "공지사항을 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @PostMapping(value = "/v1/notice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNotice(@Valid @RequestBody NoticeReqDto.CreateNoticeReq dto) {
        dto.setRgstId("admin");
        dto.setModiId("admin");
        noticeService.createNotice(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "공지사항 목록 조회", description = "공지사항 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @Parameter(name ="page", required = false, description = "페이지", example = "1")
    @Parameter(name ="pageSize", required = false, description = "페이지 사이즈", example = "10")
    @Parameter(name ="searchNotice", required = false, description = "검색 테이블", example = "")
    @GetMapping(value = "/v1/notice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNotices(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "searchNotice", required = false, defaultValue = "") String searchNotice) {

        PagingDto pagingDto = PagingDto.builder()
                .page(page)
                .pageSize(pageSize)
                .build();

        NoticeReqDto.SearchNotice searchDto = NoticeReqDto.SearchNotice.builder()
                .searchNotice(searchNotice)
                .build();

        return ResponseEntity.ok(ApiResDto.success(noticeService.getNotices(pagingDto,searchDto)));
    }

    @Operation(summary = "공지사항 조회", description = "공지사항을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @Parameter(name ="noticeId", required = true, description = "공지사항ID", example = "nt23000000005")
    @GetMapping(value = "/v1/notice/{noticeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNotice(@PathVariable String noticeId){
        noticeService.addViewCntNotice(noticeId);

        return ResponseEntity.ok(ApiResDto.success(noticeService.getNotice(noticeId)));
    }

    @Operation(summary = "공지사항 수정", description = "공지사항을 수정한다.", tags = { "notice" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @Parameter(name ="noticeId", required = true, description = "공지사항 ID", example = "nt23000000005")
    @PutMapping(value = "/v1/notice/{noticeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateNotice(@PathVariable String noticeId, @Valid @RequestBody NoticeReqDto.UpdateNoticeReq dto) {
        dto.setModiId("admin");
        noticeService.updateNotice(noticeId, dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "공지사항 삭제", description = "공지사항을 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @Parameter(name ="noticeId", required = true, description = "공지사항ID", example = "nt23000000005")
    @DeleteMapping(value = "/v1/notice/{noticeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteNotice(@PathVariable String noticeId) {
        noticeService.deleteNotice(noticeId);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "공지사항 SOFT 삭제", description = "공지사항을 SOFT 삭제한다.(del_yn)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = NoticeResDto.NoticeResDtoResult.class)))
    }
    )
    @PostMapping(value = "/v1/notice/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteNotice2(@Valid @RequestBody NoticeReqDto.DeleteNoticeReq dto) {
        dto.setModiId("admin"); // 사용자 정보 받아오기 전까지 일단 하드코딩
        noticeService.deleteNotice2(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

}

