package com.cdp.portal.app.facade.faq.dto.response;

import java.util.List;

import com.cdp.portal.app.facade.faq.dto.request.FaqReqDto;
import com.cdp.portal.app.facade.faq.model.FaqModel;
import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.common.dto.ApiResDto;
import com.cdp.portal.common.dto.PagingDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Schema(description = "FAQ 관리 응답")
public class FaqResDto extends FaqModel {

    public static class FaqResDtoResult extends ApiResDto<FaqResDto.FaqsResult> {}
    
    @Schema(description = "파일 목록", nullable = false)
    private List<FileModel> fileList;
    @Schema(description = "파일 링크 URL 목록", nullable = false)
    private List<String> fileLinks;

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @Schema(description = "공지사항 목록 결과")
    public static class FaqsResult {

        @Schema(description = "컨텐츠 정보", nullable = false)
        private List<FaqResDto> contents;
        @Schema(description = "검색 정보", nullable = false)
        private FaqReqDto.SearchFaq search;
        @Schema(description = "페이지 정보", nullable = false)
        private PagingDto page;
        @Schema(description = "파일 목록", nullable = false)
        private List<FileModel> fileList;
    }
}
