package com.cdp.portal.app.facade.qna.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class QnaReqDto {

    @Getter
    @Setter
    public static class CreateQnaReq {

        @Schema(description = "Q&A ID", example = "1", nullable = false)
        private String qnaId;
        
        @Schema(description = "분류 코드", example = "1", nullable = false)
        private String clCode;

        @Schema(description = "제목", example = "제목", nullable = false)
        @NotBlank(message = "제목은 필수 항목입니다.")
        private String sj;

        @Schema(description = "내용", example = "내용", nullable = false)
        @NotBlank(message = "내용은 필수 항목입니다.")
        private String cn;

        @NotBlank(message = "사용여부는 필수 항목입니다.")
        @Schema(description = "사용여부", example = "Y|N", nullable = false)
        private String useYn;

        @NotBlank(message = "게시여부는 필수 항목입니다.")
        @Schema(description = "사용여부", example = "Y|N", nullable = false)
        private String openYn;

        @Schema(description = "답변상태", example = "UNREAD", nullable = false)
        private String qnaStat;

        @Schema(description = "수정 ID", example = "admin", nullable = false)
        private String modiId;

    }

    @Getter
    @Setter
    public static class UpdateQnaReq {

        @Schema(description = "분류 코드", example = "코드", nullable = false)
        @NotBlank(message = "분류 코드는 필수 항목입니다.")
        private String clCode;

        @Schema(description = "제목", example = "제목", nullable = false)
        @NotBlank(message = "제목은 필수 항목입니다.")
        private String sj;

        @Schema(description = "내용", example = "내용", nullable = false)
        private String cn;

        @Schema(description = "답변", example = "답변", nullable = true)
        private String answ;

        @NotBlank(message = "사용여부는 필수 항목입니다.")
        @Schema(description = "사용여부", example = "Y|N", nullable = false)
        private String useYn;

        @NotBlank(message = "게시여부는 필수 항목입니다.")
        @Schema(description = "게시여부", example = "Y|N", nullable = false)
        private String openYn;

    }

        @Getter
        @Setter
        public static class DeleteQnaReq {
            @Schema(description = "공지사항 ID", example = "", nullable = false)
            @NotBlank(message = "공지사항 ID는 필수 항목입니다.")
            private String qnaId;

            @Schema(description = "수정자 ID", example = "제목", nullable = false)
            private String modiId;
        }
    }
