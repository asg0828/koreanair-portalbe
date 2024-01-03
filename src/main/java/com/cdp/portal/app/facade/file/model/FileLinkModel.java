package com.cdp.portal.app.facade.file.model;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileLinkModel {

    @Schema(description = "파일 링크 ID", example = "fl23000000002", nullable = false)
    private String fileLinkId;
    @Schema(description = "파일 링크", example = "https://example.com", nullable = false)
    private String fileLinkUrl;
    @Schema(description = "참조 ID", example = "sc23000000040")
    private String refId;
}
