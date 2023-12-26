package com.cdp.portal.app.facade.oneid.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MasterHistorySearchDTO {

    @Schema(description = "OneID번호")
    private String oneidNo;

    @Schema(description = "조회기준")
    private String criteria;

    @Schema(description = "OneID변경이유코드")
    private String oneidChgRsnCd;

    @Schema(description = "변경전한글LastName")
    private String bfChgKorLname;

    @Schema(description = "변경전한글FirstName")
    private String bfChgKorFname;

    @Schema(description = "변경전영문LastName")
    private String bfChgEngLname;

    @Schema(description = "변경전영문FirstName")
    private String bfChgEngFname;

    @Schema(description = "변경전휴대전화번호정보")
    private String bfChgMobilePhoneNoInfo;

    @Schema(description = "변경전휴대전화번호정보Hash값")
    private String bfChgMblfonNoInfoHashVlu;

    @Schema(description = "변경전E-Mail주소")
    private String bfChgEmailAdrs;

    @Schema(description = "변경전E-Mail주소Hash값")
    private String bfChgEmailAdrsHashValue;

    @Schema(description = "변경전출생일자V")
    private String bfChgBirthDtv;

    @Schema(description = "최초생성시작일시")
    private String creationStartDate;

    @Schema(description = "최초생성종료일시")
    private String creationEndDate;
}