package com.cdp.portal.app.facade.report.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportModel {
    @Schema(description = "oneID", example = "S199604132974321")
    private String oneId;
    @Schema(description = "정렬 기준", example = "0 year")
    private String criteria;
    @Schema(description = "정형보고서 분류", example = "vip")
    private String category;
    @Schema(description = "oneID", example = "1")
    private int rank;
    @Schema(description = "이름", example = "홍길동")
    private String userNm;
    @Schema(description = "회원번호", example = "112917446366")
    private String skypassNm;
    @Schema(description = "vip 회원분류", example = "SSSA")
    private String vipType;
    @Schema(description = "확약된 pnrCount", example = "1")
    private String confirmedPnrCount;
    @Schema(description = "국제선 탑승 예정일", example = "2023-12-06")
    private String scheduledIntlFlightDate;
    @Schema(description = "국제선 최근 탑승일", example = "2023-12-06")
    private String lastIntlFlightDate;

    // 구매기여도 TOP 100
    @Schema(description = "구매 금액", example = "111000000")
    private int purchaseAmount;
    @Schema(description = "구매 금액", example = "111000000")
    private int purchaseCount;
    @Schema(description = "국내선 구매 금액", example = "111000000")
    private int domesticPurchaseAmount;
    @Schema(description = "국제선 구매 금액", example = "111000000")
    private int intlPurchaseAmount;
    @Schema(description = "FR 구매횟수", example = "1")
    private int frCount ;
    @Schema(description = "PR 구매횟수", example = "1")
    private int prCount ;


}
