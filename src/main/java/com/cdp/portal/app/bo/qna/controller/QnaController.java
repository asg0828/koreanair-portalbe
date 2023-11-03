package com.cdp.portal.app.bo.qna.controller;

import com.cdp.portal.app.facade.qna.dto.request.QnaReqDto;
import com.cdp.portal.app.facade.qna.dto.response.QnaResDto;

import com.cdp.portal.app.facade.qna.service.QnaService;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.constants.CommonConstants;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/board")
@Tag(name = "qna", description = "QNA 관리 API")
public class QnaController {
    @Resource
    private IdUtil idUtil;

    private final QnaService qnaService;

    @Operation(summary = "Q&A 전체 목록 조회 API", description = "Q&A 전체 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = QnaResDto.QnaResDtoResult.class)))
        }
    )
    @GetMapping(value = "/v1/qna/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getQnas() {
        return ResponseEntity.ok(ApiResDto.success(qnaService.getQnaAllList()));
    }

    @Operation(summary = "Q&A 상세 조회 API", description = "Q&A 상세를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = QnaResDto.QnaResDtoResult.class)))
    }
    )
    @GetMapping(value = "/v1/qna/{qnaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> selectQna(@PathVariable String qnaId){
        qnaService.addViewCntQna(qnaId);

        return ResponseEntity.ok(ApiResDto.success(qnaService.getQna(qnaId)));
    }

    @Operation(summary = "Q&A 등록 API", description = "Q&A를 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = QnaResDto.QnaResDtoResult.class)))
    }
    )
    @PostMapping(value = "/v1/qna", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createQna(@Valid @RequestBody QnaReqDto.CreateQnaReq dto) {
        String qnaId = idUtil.getQnaId();
        dto.setQnaId(qnaId);
        dto.setQnaStat("UNREAD");
        dto.setModiId("admin");
        qnaService.createQna(dto);

        return ResponseEntity.ok(ApiResDto.success(qnaId));
    }

    @Operation(summary = "Q&A 수정", description = "Q&A를 수정한다.", tags = { "qna" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @Parameter(name ="qnaId", required = true, description = "Q&A ID", example = "1")
    @PutMapping(value = "/v1/qna/{qnaId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateQna(@PathVariable String qnaId, @Valid @RequestBody QnaReqDto.UpdateQnaReq dto) {
        qnaService.updateQna(qnaId, dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "Q&A 삭제 API", description = "Q&A을 SOFT 삭제한다.(del_yn)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = QnaResDto.QnaResDtoResult.class)))
    }
    )
    @PostMapping(value = "/v1/qna/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteQna(@Valid @RequestBody QnaReqDto.DeleteQnaReq dto) {
        dto.setModiId("admin"); // 사용자 정보 받아오기 전까지 일단 하드코딩
        qnaService.deleteQna(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "Q&A 삭제 API", description = "Q&A을 완전 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = QnaResDto.QnaResDtoResult.class)))
    }
    )
    @DeleteMapping(value = "/v1/qna", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteQna1(@Valid @RequestBody QnaReqDto.DeleteQnaReq dto) {
        qnaService.deleteQna2(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }
}
