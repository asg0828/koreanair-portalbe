package com.cdp.portal.app.facade.report.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Report 요청")
public class ReportReqDto {

    @Getter
    @Schema(description = "Report 검색")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class SearchReport {
        @Schema(description = "Report 검색", example = "")
        private String searchReport;

        @Schema(description = "검색 조건", example = "")
        private String[] searchConditions;


    }

}
