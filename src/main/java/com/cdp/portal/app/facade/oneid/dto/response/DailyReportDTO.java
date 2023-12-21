package com.cdp.portal.app.facade.oneid.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

    @Data
    @AllArgsConstructor
    @Builder
    public class DailyReportDTO {

        @Schema(description = "No")
        private Integer no;

        @Schema(description = "일자")
        private String aggrDate;

        @Schema(description = "총 PNR 수")
        private Integer pnrTotal;

        @Schema(description = "총 UCI ID 수")
        private Integer pnrUci;

        @Schema(description = "신규 OneID")
        private Integer pnrNewOneid;

        @Schema(description = "SKYPASS OneID 재사용")
        private Integer pnrReuseSkypass;

        @Schema(description = "기존 비회원 OneID 재사용")
        private Integer pnrReuseNonSkypass;

        @Schema(description = "신규 Potential OneID")
        private Integer pnrNewPotentialid;

        @Schema(description = "OneID 미생성")
        private Integer pnrSkipped;

        @Schema(description = "당일 PNR")
        private Integer newOneidTodayPnr;

        @Schema(description = "이전 PNR")
        private Integer newOneidPastPnr;

        @Schema(description = "이전 Potential 전환")
        private Integer newOneidPotentialConv;

        @Schema(description = "SKYPASS")
        private Integer newOneidSkypass;

        @Schema(description = "Total")
        private Integer newOneidTotal;

        @Schema(description = "대리점 추정 Mobile OneID")
        private Integer newOneidAgentMobile;

        @Schema(description = "총 OneID 개수(Master/Active)")
        private Integer oneidTotal;

        @Schema(description = "총 단독 OneID 개수")
        private Integer oneidAlone;

        @Schema(description = "총 Target OneID 개수")
        private Integer oneidTarget;

        @Schema(description = "총 Source OneID 개수")
        private Integer oneidSource;

        @Schema(description = "회원+회원")
        private Integer mergedSkypassToSkypass;

        @Schema(description = "회원+비회원")
        private Integer mergedNonSkypassToSkypass;

        @Schema(description = "비회원+비회원")
        private Integer mergedNonSkypassToNonSkypass;

        @Schema(description = "SKYPASS 요청")
        private Integer mergedSkypassRequest;
    }