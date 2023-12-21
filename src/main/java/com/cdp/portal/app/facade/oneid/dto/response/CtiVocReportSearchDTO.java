package com.cdp.portal.app.facade.oneid.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CtiVocReportSearchDTO {
    @Schema(description = "조회기준")
    private String criteria;

    @Schema(description = "채널")
    private String channel;

    @Schema(description = "집계시작일시")
    private String aggrStartDate;

    @Schema(description = "집계종료일시")
    private String aggrEndDate;
}
