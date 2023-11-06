package com.cdp.portal.app.facade.notice.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

public class NoticeReqDto {

    @Getter
    @Setter
    public static class CreateNoticeReq {

        @Schema(description = "공지사항 ID", example = "nt23000000002", nullable = false)
        private String noticeId;

        @NotBlank(message = "제목은 필수 항목입니다.")
        @Schema(description = "제목", example = "제목", nullable = false)
        private String sj;

        @NotBlank(message = "내용은 필수 항목입니다.")
        @Schema(description = "내용", example = "내용", nullable = false)
        private String cn;

        @NotBlank(message = "중요여부는 필수 항목입니다.")
        @Schema(description = "중요여부", example = "Y|N", nullable = false)
        private String importantYn;

        @NotBlank(message = "사용여부는 필수 항목입니다.")
        @Schema(description = "사용여부", example = "Y|N", nullable = false)
        private String useYn;

        @NotBlank(message = "팝업공지여부는 필수 항목입니다.")
        @Schema(description = "팝업공지여부", example = "Y|N", nullable = false)
        private String popupYn;

        @NotNull(message = "시작일시는 필수 항목입니다.")
        @Schema(description = "시작일시", example = "2023-10-26 10:04:45.868000", nullable = false)
        private Timestamp startDt;

        @NotNull(message = "종료일시는 필수 항목입니다.")
        @Schema(description = "종료일시", example = "2023-10-26 10:04:45.868000", nullable = false)
        private Timestamp endDt;

        @Schema(description = "등록 ID", example = "admin", nullable = false)
        private String rgstId;

        @Schema(description = "수정자 ID", example = "admin", nullable = false)
        private String modiId;
    }

    @Getter
    @Setter
    public static class UpdateNoticeReq {

        @Schema(description = "제목", example = "제목", nullable = false)
        @NotBlank(message = "제목은 필수 항목입니다.")
        private String sj;

        @Schema(description = "내용", example = "내용", nullable = true)
        @NotBlank(message = "내용은 필수 항목입니다.")
        private String cn;

        @NotBlank(message = "중요여부는 필수 항목입니다.")
        @Schema(description = "중요여부", example = "Y|N", nullable = false)
        private String importantYn;

        @NotBlank(message = "사용여부는 필수 항목입니다.")
        @Schema(description = "사용여부", example = "Y|N", nullable = false)
        private String useYn;

        @NotBlank(message = "팝업공지여부는 필수 항목입니다.")
        @Schema(description = "팝업공지여부", example = "Y|N", nullable = false)
        private String popupYn;


        @Schema(description = "수정 ID", example = "admin", nullable = false)
        private String modiId;

        @NotNull(message = "시작일시는 필수 항목입니다.")
        @Schema(description = "시작일시", example = "2023-10-26 10:04:45.868000", nullable = false)
        private Timestamp startDt;

        @NotNull(message = "종료일시는 필수 항목입니다.")
        @Schema(description = "종료일시", example = "2023-10-26 10:04:45.868000", nullable = false)
        private Timestamp endDt;
    }

    @Getter
    @Setter
    public static class DeleteNoticeReq {
        @Schema(description = "공지사항 ID", example = "nt23000000003", nullable = false)
        @NotBlank(message = "공지사항 ID는 필수 항목입니다.")
        private String noticeId;

        @Schema(description = "수정자 ID", example = "admin", nullable = false)
        @NotBlank(message = "수정자 ID는 필수 항목입니다.")
        private String modiId;
        
    }

    @Getter
    @Schema(description = "공지사항 검색")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class SearchNotice {

        @Schema(description = "검색", example = "")
        private String searchNotice;

    }
}
