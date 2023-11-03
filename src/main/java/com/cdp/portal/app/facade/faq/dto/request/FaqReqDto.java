package com.cdp.portal.app.facade.faq.dto.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

public class FaqReqDto {

    @Getter
    @Setter
    public static class CreateFaqReq {

        @Schema(description = "FAQ ID", example = "1", nullable = false)
        private String faqId;
        @Schema(description = "분류 코드", example = "1", nullable = false)
        private String clCode;

        @Schema(description = "질문", example = "1", nullable = false)
        private String qstn;

        @Schema(description = "답변", example = "1", nullable = false)
        private String answ;
        @Schema(description = "게시 여부", example = "Y|N", nullable = false)
        private String useYn;
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

        @Schema(description = "수정 ID", example = "admin", nullable = false)
        private String modiId;
    }

    @Getter
    @Setter
    public static class DeleteFaqReq {
        @Schema(description = "Faq ID", example = "", nullable = false)
        @NotBlank(message = "Faq ID는 필수 항목입니다.")
        private String faqId;

        @Schema(description = "수정자 ID", example = "제목", nullable = false)
        private String modiId;
    }
}