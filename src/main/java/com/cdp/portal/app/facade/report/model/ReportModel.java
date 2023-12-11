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
    @Schema(description = "순위", example = "1")
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
    @Schema(description = "구매 횟수", example = "1")
    private int purchaseCount;
    @Schema(description = "국내선 구매 금액", example = "111000000")
    private int domesticPurchaseAmount;
    @Schema(description = "국제선 구매 금액", example = "111000000")
    private int intlPurchaseAmount;
    @Schema(description = "FR 구매횟수", example = "1")
    private int frCount ;
    @Schema(description = "PR 구매횟수", example = "1")
    private int prCount ;

    // 국제선 탑승 횟수 TOP 100
    @Schema(description = "국제선 수입 금액", example = "111000000")
    private int intlIncomeAmount;
    @Schema(description = "국제선 보너스 항공권 탑승 횟수", example = "1")
    private int intlBonusBoardingCount;
    @Schema(description = "국제선 FR 탑승 횟수", example = "2")
    private int intlFrCount;
    @Schema(description = "국제선 PR 탑승 횟수", example = "2")
    private int intlPrCount;
    @Schema(description = "국제선 평균 탑승 주기", example = "2")
    private int avgBoardingIntervalForIntl;
    @Schema(description = "국제선 최다 탑승 O&D", example = "2")
    private int mostFrequentedOnDForIntl;

    // 국내선 탑승 횟수 TOP 100
    @Schema(description = "국내선 수입 금액", example = "111000000")
    private int domesticIncomeAmount;
    @Schema(description = "국내선 탑승 횟수", example = "10")
    private int domesticBoardingCount;
    @Schema(description = "국내선 보너스 항공권 탑승 횟수", example = "111000000")
    private int domesticBounusBoardingCount;
    @Schema(description = "국내선 PR 탑승 횟수", example = "10")
    private int domesticPrBoardingCount;
    @Schema(description = "국내선 평균 탑승 주기", example = "30")
    private int domesticAvgBoardingCycle;

    // 마일리지 적립 TOP 100
    @Schema(description = "총 적립 마일리지", example = "1100000")
    private int totalMileage;
    @Schema(description = "총 적립 마일리지 (항공 탑승)", example = "1100000")
    private int totalFlightMileage;
    @Schema(description = "총 적립 마일리지(항공 이외)", example = "1100000")
    private int totalEtcMileage;
    @Schema(description = "잔여 마일리지", example = "1100000")
    private int availableMileage;
    @Schema(description = "잔여 마일리지(가족 합산)", example = "1100000")
    private int familyAvailableMileage;
    @Schema(description = "마일리지 제휴카드(PLCC 보유여부)", example = "1100000")
    private int mileagePartnerCardYn;
}
