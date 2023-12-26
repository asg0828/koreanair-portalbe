package com.cdp.portal.app.facade.oneid.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AgtContactSearchDTO {

    @Schema(description = "대리점추정휴대전화번호정보")
    private String agtEstimatedMblfonNoInfo;

    @Schema(description = "Hash값 전환 대리점추정휴대전화번호정보")
    private String convertMblfonNoInfoToHshVlu;

    @Schema(description = "대리점추정휴대전화번호정보Hash값")
    private String agtEstMblfonNoInfoHshVlu;
}