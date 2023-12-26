package com.cdp.portal.app.facade.oneid.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PaxMappingDTO {

    @Schema(description = "No")
    private Integer no;

    @Schema(description = "OneID번호")
    private String oneidNo;

    @Schema(description = "PNR번호")
    private String pnrNumber;

    @Schema(description = "UCIID")
    private String uciId;

    @Schema(description = "임시OneID번호")
    private String temporaryOneidNo;

    @Schema(description = "사용여부")
    private String useYn;

    @Schema(description = "Skypass회원번호")
    private String skypassMemberNumber;

    @Schema(description = "최초생성일시")
    private String creationDate;

    @Schema(description = "최종갱신일시")
    private String lastUpdateDate;
}