package com.cdp.portal.app.facade.file.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class FileLinkReqDto {

    @Getter
    @Setter
    public static class InsertFileReq {
        @Schema(description = "파일 링크 ID", example = "fl23000000002", nullable = false)
        private String fileLinkId;
        @Schema(description = "파일 링크", example = "https://example.com", nullable = false)
        private String fileLinkUrl;
        @Schema(description = "사용 여부", example = "Y|N")
        private String useYn;
        @Schema(description = "등록 ID", example = "pj.shkwak", nullable = false)
        private String rgstId;
        @Schema(description = "등록 일시", example = "2023-10-26 10:04:45.868000", nullable = false)
        private Timestamp rgstDt;
        @Schema(description = "수정 ID", example = "pj.shkwak", nullable = false)
        private String modiId;
        @Schema(description = "수정 일시", example = "2023-10-26 10:04:45.868000", nullable = false)
        private Timestamp modiDt;
    }
}
