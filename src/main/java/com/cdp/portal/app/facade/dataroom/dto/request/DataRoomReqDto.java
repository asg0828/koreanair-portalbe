package com.cdp.portal.app.facade.dataroom.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class DataRoomReqDto {

    @Getter
    @Setter
    public static class CreateDataRoomReq {

        @Schema(description = "자료실 ID", example = "1", nullable = false)
        private String dataId;
        @Schema(description = "제목", example = "1", nullable = false)
        private String sj;
        @Schema(description = "내용", example = "1", nullable = false)
        private String cn;
        @Schema(description = "사용여부", example = "Y|N", nullable = false)
        private String useYn;
        @Schema(description = "등록자 ID", example = "pj.shkwak", nullable = false)
        private String rgstId;
        @Schema(description = "수정자 ID", example = "pj.shkwak", nullable = false)
        private String modiId;
        @Schema(description = "파일 ID 목록", example = "pj.shkwak", nullable = false)
        private String[] fileIds;
        @Schema(description = "파일 링크 URL 목록", example = "[https://example.com]", nullable = false)
        private String[] fileLinks;
    }

    @Getter
    @Setter
    public static class UpdateDataRoomReq {

        @Schema(description = "제목", example = "제목", nullable = false)
        @NotBlank(message = "제목은 필수 항목입니다.")
        private String sj;

        @Schema(description = "내용", example = "내용", nullable = false)
        @NotBlank(message = "내용은 필수 항목입니다.")
        private String cn;

        @Schema(description = "사용여부", example = "Y|N", nullable = false)
        @NotBlank(message = "사용여부는 필수 항목입니다.")
        private String useYn;

        @Schema(description = "수정자 ID", example = "pj.shkwak", nullable = false)
        private String modiId;
        @Schema(description = "파일 ID 목록", example = "[fl23000000360]", nullable = false)
        private String[] fileIds;

        @Schema(description = "파일 링크 URL 목록", example = "[https://example.com]", nullable = false)
        private String[] fileLinks;
    }

    @Getter
    @Setter
    public static class DeleteDataRoomReq {
        @Schema(description = "자료실 ID", example = "", nullable = false)
        @NotBlank(message = "자료실 ID는 필수 항목입니다.")
        private String dataId;

        @Schema(description = "수정자 ID", example = "제목", nullable = false)
        private String modiId;
    }

    @Getter
    @Schema(description = "자료실 검색")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class SearchDataRoom {

        @Schema(description = "검색 테이블", example = "")
        private String searchTable;

        @Schema(description = "검색 조건", example = "")
        private String[] searchConditions;

        @Schema(description = "공개여부", example = "")
        private String useYn;
    }
}
