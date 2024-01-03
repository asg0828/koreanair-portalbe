package com.cdp.portal.app.facade.faq.dto.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

public class FaqReqDto {

    @Getter
    @Setter
    public static class CreateFaqReq {

        @Schema(description = "FAQ ID", example = "fq23000000002", nullable = false)
        private String faqId;
        @NotBlank(message = "분류 코드는 필수 항목입니다.")
        @Schema(description = "분류 코드", example = "1", nullable = false)
        private String clCode;

        @NotBlank(message = "질문은 필수 항목입니다.")
        @Schema(description = "질문", example = "질문", nullable = false)
        private String qstn;

        @NotBlank(message = "답변은 필수 항목입니다.")
        @Schema(description = "답변", example = "답변", nullable = false)
        private String answ;
        @NotBlank(message = "사용 여부는 필수 항목입니다.")
        @Schema(description = "사용 여부", example = "Y|N", nullable = false)
        private String useYn;
        @Schema(description = "등록 ID", example = "pj.shkwak", nullable = false)
        private String rgstId;

        @Schema(description = "수정자 ID", example = "pj.shkwak", nullable = false)
        private String modiId;
        @Schema(description = "파일 ID 목록", example = "[fl23000000360]", nullable = false)
        private String[] fileIds;
        
        @Schema(description = "파일 링크 URL 목록", example = "[https://example.com]", nullable = false)
        private String[] fileLinks;
    }

    @Getter
    @Setter
    public static class UpdateFaqReq {

        @NotBlank(message = "코드 분류는 필수 항목입니다.")
        @Schema(description = "코드 분류", example = "code", nullable = false)
        private String clCode;

        @NotBlank(message = "질문은 필수 항목입니다.")
        @Schema(description = "질문", example = "질문입니다", nullable = false)
        private String qstn;

        @NotBlank(message = "답변은 필수 항목입니다.")
        @Schema(description = "답변", example = "답변입니다", nullable = false)
        private String answ;

        @NotBlank(message = "사용여부는 필수 항목입니다.")
        @Schema(description = "사용여부", example = "Y|N", nullable = false)
        private String useYn;

        @Schema(description = "수정 ID", example = "pj.shkwak", nullable = false)
        private String modiId;
        @Schema(description = "파일 ID 목록", example = "[fl23000000360]", nullable = false)
        private String[] fileIds;
        
        @Schema(description = "파일 링크 URL 목록", example = "[https://example.com]", nullable = false)
        private String[] fileLinks;
    }

    @Getter
    @Setter
    public static class DeleteFaqReq {
        @Schema(description = "Faq ID", example = "", nullable = false)
        @NotBlank(message = "Faq ID는 필수 항목입니다.")
        private String faqId;

        @Schema(description = "수정자 ID", example = "pj.shkwak", nullable = false)
        @NotBlank(message = "수정자 ID는 필수 항목입니다.")
        private String modiId;
    }

    @Getter
    @Schema(description = "FAQ 검색")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class SearchFaq {

        @Schema(description = "검색 테이블", example = "")
        private String searchTable;

        @Schema(description = "검색 조건", example = "")
        private String[] searchConditions;
        
        @Schema(description = "공개여부", example = "")
        private String useYn;
    }
}
