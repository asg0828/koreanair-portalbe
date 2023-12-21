package com.cdp.portal.app.facade.oneid.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class CleansingHashResultsDTO {
    @Schema(description = "휴대전화번호 클렌징 결과")
    private String phoneCleansingResult;

    @Schema(description = "E-mail 주소 클렌징 결과")
    private String emailCleansingResult;

    @Schema(description = "휴대전화번호 Hash값")
    private String phoneHashValue;

    @Schema(description = "E-mail주소 Hash값")
    private String emailHashValue;
}
