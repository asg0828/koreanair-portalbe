package com.cdp.portal.app.facade.oneid.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorLogDTO {

    @Schema(description = "No")
    private Integer no;

    @Schema(description = "에러코드")
    private String errorNm;

    @Schema(description = "상세에러코드")
    private String detailErrorNm;

    @Schema(description = "OneID등록Channel코드")
    private String oneidRegisChnlCd;

    @Schema(description = "채널 관리코드")
    private String oneidFinalChgRelateNo;

    @Schema(description = "SKYPASS 액션")
    private String rqActionNm;

    @Schema(description = "ODS Header")
    private String odsHeader;

    @Schema(description = "ODS M_GID")
    private String odsMGid;

    @Schema(description = "UCIID")
    private String uciId;

    @Schema(description = "상세내역")
    private String errorContents;

    @Schema(description = "처리여부")
    private String errorActionYn;

    @Schema(description = "최초생성일시")
    private String creationDate;

    @Schema(description = "최종갱신일시")
    private String lastUpdateDate;
}