package com.cdp.portal.app.facade.oneid.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MasterHistoryDTO {

    @Schema(description = "No")
    private Integer no;

    @Schema(description = "OneID번호")
    private String oneidNo;

    @Schema(description = "OneID변경이유코드")
    private String oneidChgRsnCd;

    @Schema(description = "변경전OneID유형코드")
    private String bfChgOneidTypeCd;

    @Schema(description = "변경전OneID상태코드")
    private String bfChgOneidSttsCd;

    @Schema(description = "변경전OneID최종변경Channel코드")
    private String bfChgOneidFinalChgChnlCd;

    @Schema(description = "변경전OneID최종변경관련번호")
    private String bfChgOneidFinalChgReltNo;

    @Schema(description = "변경전OneID최종변경UCIID")
    private String bfChgOneidFnlChgUciId;

    @Schema(description = "변경전영문FirstName")
    private String bfChgEngFname;

    @Schema(description = "변경전영문LastName")
    private String bfChgEngLname;

    @Schema(description = "변경전휴대전화번호정보")
    private String bfChgMobilePhoneNoInfo;

    @Schema(description = "변경전E-Mail주소")
    private String bfChgEmailAdrs;

    @Schema(description = "변경전출생일자V")
    private String bfChgBirthDtv;

    @Schema(description = "변경전한글FirstName")
    private String bfChgKorFname;

    @Schema(description = "변경전한글LastName")
    private String bfChgKorLname;

    @Schema(description = "변경전영문명Soundex값")
    private String bfChgEngNmSoundexValue;

    @Schema(description = "변경전휴대전화번호정보Hash값")
    private String bfChgMblfonNoInfoHashVlu;

    @Schema(description = "변경전E-Mail주소Hash값")
    private String bfChgEmailAdrsHashValue;

    @Schema(description = "변경전여권국적ISO3Letter국가코드")
    private String bfChgPpNtnltyIsoL3CtrCd;

    @Schema(description = "변경전여권만료일자V")
    private String bfChgPassportExpireDtv;

    @Schema(description = "변경후OneID유형코드")
    private String afChgOneidTypeCd;

    @Schema(description = "변경후OneID상태코드")
    private String afChgOneidSttsCd;

    @Schema(description = "변경후OneID최종변경Channel코드")
    private String afChgOneidFinalChgChnlCd;

    @Schema(description = "변경후OneID최종변경관련번호")
    private String afChgOneidFinalChgReltNo;

    @Schema(description = "변경후OneID최종변경UCIID")
    private String afChgOneidFnlChgUciId;

    @Schema(description = "변경후영문FirstName")
    private String afChgEngFname;

    @Schema(description = "변경후영문LastName")
    private String afChgEngLname;

    @Schema(description = "변경후휴대전화번호정보")
    private String afChgMobilePhoneNoInfo;

    @Schema(description = "변경후E-Mail주소")
    private String afChgEmailAdrs;

    @Schema(description = "변경후출생일자V")
    private String afChgBirthDtv;

    @Schema(description = "변경후한글FirstName")
    private String afChgKorFname;

    @Schema(description = "변경후한글LastName")
    private String afChgKorLname;

    @Schema(description = "변경후영문명Soundex값")
    private String afChgEngNmSoundexValue;

    @Schema(description = "변경후휴대전화번호정보Hash값")
    private String afChgMblfonNoInfoHashVlu;

    @Schema(description = "변경후E-Mail주소Hash값")
    private String afChgEmailAdrsHashValue;

    @Schema(description = "변경후여권국적ISO3Letter국가코드")
    private String afChgPpNtnltyIsoL3CtrCd;

    @Schema(description = "변경후여권만료일자V")
    private String afChgPassportExpireDtv;

    @Schema(description = "최초생성일시")
    private String creationDate;

    @Schema(description = "최종갱신일시")
    private String lastUpdateDate;
}