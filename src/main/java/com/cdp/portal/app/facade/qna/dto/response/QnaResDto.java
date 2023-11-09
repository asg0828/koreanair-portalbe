package com.cdp.portal.app.facade.qna.dto.response;

import java.util.List;

import com.cdp.portal.app.facade.faq.dto.request.FaqReqDto;
import com.cdp.portal.app.facade.faq.dto.response.FaqResDto;
import com.cdp.portal.app.facade.notice.dto.request.NoticeReqDto;
import com.cdp.portal.app.facade.notice.dto.response.NoticeResDto;
import com.cdp.portal.app.facade.qna.dto.request.QnaReqDto;
import com.cdp.portal.app.facade.qna.model.QnaModel;
import com.cdp.portal.common.dto.ApiResDto;

import com.cdp.portal.common.dto.PagingDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Schema(description = "QNA 관리 응답")
public class QnaResDto extends QnaModel {
    public static class QnaResDtoResult extends ApiResDto<QnaResDto.QnasResult> {}

    @JsonProperty("comments")
    @Schema(description = "댓글 목록", nullable = true)
    private List<QnaResDto> comments;

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @Schema(description = "Q&A 목록 결과")
    public static class QnasResult {

        @Schema(description = "컨텐츠 정보", nullable = false)
        private List<QnaResDto> contents;
        @Schema(description = "검색 정보", nullable = false)
        private QnaReqDto.SearchQna search;
        @Schema(description = "페이지 정보", nullable = false)
        private PagingDto page;

    }
}
