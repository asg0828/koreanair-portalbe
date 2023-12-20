package com.cdp.portal.app.facade.file.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class FileReqDto {

    @Getter
    @Setter
    public static class InsertFileReq {
        @Schema(description = "파일 ID", example = "fl23000000002", nullable = false)
        private String fileId;
        @Schema(description = "저장소 구분", example = "S3", nullable = false)
        private String storageSe;
        @Schema(description = "버킷 명", example = "", nullable = false)
        private String bucketNm;
        @Schema(description = "저장 경로", example = "", nullable = false)
        private String savePath;
        @Schema(description = "파일 이름", example = "", nullable = false)
        private String saveFileNm;
        @Schema(description = "사용 여부", example = "Y|N", nullable = false)
        private String useYn;
        @Schema(description = "등록 ID", example = "pj.shkwak", nullable = false)
        private String rgstId;
        @Schema(description = "수정 ID", example = "pj.shkwak", nullable = false)
        private String modiId;
        @Schema(description = "수정 일시", example = "2023-10-26 10:04:45.868000", nullable = false)
        private Timestamp modiDt;

    }
}
