package com.cdp.portal.app.facade.main.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainModel {
    @Schema(description = "내 부서에서 신청/등록한 feature 총 건수", example = "")
    private int featureTotalCount;
    @Schema(description = "내 부서에서 등록한 관심feature 총 건수", example = "")
    private int featureInterestTotalCount;
}
