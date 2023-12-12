package com.cdp.portal.app.fo.qna.controller;

import com.cdp.portal.app.facade.qna.dto.request.QnaReqDto;
import com.cdp.portal.app.facade.qna.dto.response.QnaResDto;
import com.cdp.portal.app.facade.qna.service.QnaService;
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
@RequestMapping(value = CommonConstants.API_FO_PREFIX + "/board")
@Tag(name = "qna", description = "QNA 관리 API")
public class FoQnaRestController {
    private final QnaService qnaService;

    @Operation(summary = "Q&A 목록 조회", description = "Q&A 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )

    @Parameter(name ="page", required = false, description = "페이지", example = "1")
    @Parameter(name ="pageSize", required = false, description = "페이지 사이즈", example = "10")
    @Parameter(name ="searchTable", required = false, description = "검색 테이블", example = "")
    @Parameter(name ="searchConditions", required = false, description = "검색 조건", example = "")
    @GetMapping(value = "/v1/qna", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getQnas(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "searchTable", required = false, defaultValue = "") String searchTable,
            @RequestParam(value = "searchConditions", required = false, defaultValue = "") String[] searchConditions) {

        PagingDto pagingDto = PagingDto.builder()
                .page(page)
                .pageSize(pageSize)
                .build();

        QnaReqDto.SearchQna searchDto = QnaReqDto.SearchQna.builder()
                .searchTable(searchTable)
                .searchConditions(searchConditions)
                .build();

        return ResponseEntity.ok(ApiResDto.success(qnaService.getQnas(pagingDto, searchDto)));
    }

    @Operation(summary = "Q&A 조회", description = "Q&A를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @GetMapping(value = "/v1/qna/{qnaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> selectQna(@PathVariable String qnaId){
        qnaService.addViewCntQna(qnaId);
//        qnaService.updateQnaStat(qnaId);
        QnaResDto qnaResDto = qnaService.getQna(qnaId);
        qnaResDto.setComments(qnaService.selectQnaReplyList(qnaId));

        return ResponseEntity.ok(ApiResDto.success(qnaResDto));
    }

}
