package com.cdp.portal.app.facade.oneid.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MergeHistoryDTO {

    @Schema(description = "No")
    private Integer no;

    @Schema(description = "병합TargetOneID번호")
    private String mergeTargetOneidNo;

    @Schema(description = "병합SourceOneID번호")
    private String mergeSourceOneidNo;

    @Schema(description = "OneID병합상태코드")
    private String oneidMergeSttsCd;

    @Schema(description = "Skypass회원번호")
    private String skypassMemberNumber;

    @Schema(description = "Skypass회원번호(PAX)")
    private String paxSkypassMemberNumber;

    @Schema(description = "영문FirstName")
    private String engFname;

    @Schema(description = "영문LastName")
    private String engLname;

    @Schema(description = "한글FirstName")
    private String korFname;

    @Schema(description = "한글LastName")
    private String korLname;

    @Schema(description = "E-Mail주소")
    private String emailAddress;

    @Schema(description = "휴대전화번호정보")
    private String mobilePhoneNumberInfo;

    @Schema(description = "출생일자V")
    private String birthDatev;

    @Schema(description = "최초생성일시")
    private String creationDate;

    @Schema(description = "최종갱신일시")
    private String lastUpdateDate;
}

