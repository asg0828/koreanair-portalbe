package com.cdp.portal.app.facade.oneid.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CtiVocReportDTO {
    @Schema(description = "구분")
    private String channel;

    @Schema(description = "일자")
    private String reqDatev;

    @Schema(description = "전체 VOC건수(last_upd 기준)")
    private Integer totalCntLastUpd;

    @Schema(description = "전체 VOC건수(case_close기준)")
    private Integer totalCntCaseClose;

    @Schema(description = "OneID 연결 VOC 건수(회원)")
    private Integer oneidConnectSkypassCnt;

    @Schema(description = "OneID 연결 VOC 건수(비회원)")
    private Integer oneidConnectNonSkypassCnt;

    @Schema(description = "OneID 연결 VOC 건수(전체)")
    private Integer oneidConnectTotalCnt;

    @Schema(description = "OneID 연결 VOC 건수(연결%)")
    private Integer oneidConnectRate;

    @Schema(description = "(CTI)조회건수/전체건수")
    private Integer oneidCtiSearchTotalRate;

    @Schema(description = "(CTI)연결건수/Return건수")
    private Integer oneidCtiConnectReturnRate;

    @Schema(description = "조회 Key 별 OneID 조회건수(회원번호)")
    private Integer oneidSearchSkypassCnt;

    @Schema(description = "조회 Key 별 OneID 조회건수(회원번호 이외)")
    private Integer oneidSearchNonSkypassCnt;

    @Schema(description = "조회 Key 별 OneID 조회건수(전체)")
    private Integer oneidSearchTotalCnt;

    @Schema(description = "조회 Key Hit Ratio (추정, 전체)")
    private Integer oneidHitTotalRate;

    @Schema(description = "조회 Key Hit Ratio (추정, 회원번호 이외)")
    private Integer oneidHitNonSkypassRate;

    @Schema(description = "OneID Return 건수별 조회횟수(0건)")
    private Integer oneidNonHitCnt;

    @Schema(description = "OneID Return 건수별 조회횟수(1~10건)")
    private Integer oneidMultiHitCnt;

    @Schema(description = "OneID Return 건수별 조회횟수(11건 이상)")
    private Integer oneidOverHitCnt;

    @Schema(description = "최초생성시간")
    private String creationDate;

    @Schema(description = "최중수정시간")
    private String lastUpdateDate;
}
