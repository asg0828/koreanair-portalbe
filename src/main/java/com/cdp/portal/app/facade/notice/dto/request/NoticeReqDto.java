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

        @Schema(description = "수정 ID", example = "pj.shkwak", nullable = false)
        private String modiId;

        @Schema(description = "파일 ID 목록", example = "[fl23000000360]", nullable = false)
        private String[] fileIds;
        
        @Schema(description = "파일 링크 URL 목록", example = "[https://example.com]", nullable = false)
        private String[] fileLinks;
    }

    @Getter
    @Setter
    public static class DeleteNoticeReq {
        @Schema(description = "공지사항 ID", example = "nt23000000003", nullable = false)
        @NotBlank(message = "공지사항 ID는 필수 항목입니다.")
        private String noticeId;

        @Schema(description = "수정자 ID", example = "pj.shkwak", nullable = false)
        @NotBlank(message = "수정자 ID는 필수 항목입니다.")
        private String modiId;
        
    }

    @Getter
    @Schema(description = "공지사항 검색")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class SearchNotice {

        @Schema(description = "검색 테이블", example = "")
        private String searchTable;

        @Schema(description = "검색 조건", example = "")
        private String[] searchConditions;
        
        @Schema(description = "공개여부", example = "")
        private String useYn;
    }
}
