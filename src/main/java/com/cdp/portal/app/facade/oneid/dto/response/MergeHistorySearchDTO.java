package com.cdp.portal.app.facade.oneid.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MergeHistorySearchDTO {

    @Schema(description = "OneID번호")
    private String oneidNo;

    @Schema(description = "최초생성시작일시")
    private String creationStartDate;

    @Schema(description = "최초생성종료일시")
    private String creationEndDate;
}