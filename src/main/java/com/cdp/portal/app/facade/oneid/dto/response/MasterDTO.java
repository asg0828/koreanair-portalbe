package com.cdp.portal.app.facade.oneid.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MasterDTO {

    @Schema(description = "No")
    private Integer no;

    @Schema(description = "OneID번호")
    private String oneidNo;

    @Schema(description = "OneID유형코드")
    private String oneidTypeCd;

    @Schema(description = "OneID상태코드")
    private String oneidSttsCd;

    @Schema(description = "OneID등록Channel코드")
    private String oneidRegisChnlCd;

    @Schema(description = "OneID최종변경Channel코드")
    private String oneidFinalChgChnlCd;

    @Schema(description = "OneID최종변경관련번호")
    private String oneidFinalChgRelateNo;

    @Schema(description = "OneID최종변경UCIID")
    private String oneidFinalChgUciId;

    @Schema(description = "영문FirstName")
    private String engFname;

    @Schema(description = "영문LastName")
    private String engLname;

    @Schema(description = "휴대전화번호정보")
    private String mobilePhoneNumberInfo;

    @Schema(description = "E-Mail주소")
    private String emailAddress;

    @Schema(description = "출생일자V")
    private String birthDatev;

    @Schema(description = "한글FirstName")
    private String korFname;

    @Schema(description = "한글LastName")
    private String korLname;

    @Schema(description = "성별코드")
    private String sexCode;

    @Schema(description = "성별등록Channel코드")
    private String sexRegisChnlCd;

    @Schema(description = "자택전화번호정보")
    private String homePhoneNumberInfo;

    @Schema(description = "사무실전화번호정보")
    private String officePhoneNumberInfo;

    @Schema(description = "자동전환영문FirstName")
    private String autoConvsEngFname;

    @Schema(description = "자동전환영문LastName")
    private String autoConvsEngLname;

    @Schema(description = "복수여권여부")
    private String multiplePassportYn;

    @Schema(description = "대리점추정코드")
    private String agtEstimatedContactTypeCd;

    @Schema(description = "영문명Soundex값")
    private String engNmSoundexValue;

    @Schema(description = "휴대전화번호정보Hash값")
    private String mobilePhoneNoInfoHashVlu;

    @Schema(description = "E-Mail주소Hash값")
    private String emailAdrsHashValue;

    @Schema(description = "자택전화번호정보Hash값")
    private String homePhoneNoInfoHashValue;

    @Schema(description = "사무실전화번호정보Hash값")
    private String officePhoneNoInfoHashVlu;

    @Schema(description = "개인정보사용동의여부")
    private String personalInfoUseAgreeYn;

    @Schema(description = "최종조회일시")
    private String finalInqDtim;

    @Schema(description = "최초생성일시")
    private String creationDate;

    @Schema(description = "최종갱신일시")
    private String lastUpdateDate;
}