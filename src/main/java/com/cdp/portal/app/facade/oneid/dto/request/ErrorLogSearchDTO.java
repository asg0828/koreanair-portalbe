package com.cdp.portal.app.facade.oneid.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorLogSearchDTO {

    @Schema(description = "에러명")
    private String errorNm;

    @Schema(description = "상세에러코드")
    private String detailErrorNm;

    @Schema(description = "OneID등록Channel코드")
    private String oneidRegisChnlCd;

    @Schema(description = "채널 관리코드")
    private String oneidFinalChgRelateNo;

    @Schema(description = "UCIID")
    private String uciId;

    @Schema(description = "최초생성시작일시")
    private String creationStartDate;

    @Schema(description = "최초생성종료일시")
    private String creationEndDate;
}