package com.cdp.portal.app.facade.oneid.dto.response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MasterSearchDTO {

    @Schema(description = "OneID번호")
    private String oneidNo;

    @Schema(description = "한글FirstName")
    private String korFname;

    @Schema(description = "한글LastName")
    private String korLname;

    @Schema(description = "영문FirstName")
    private String engFname;

    @Schema(description = "영문LastName")
    private String engLname;

    @Schema(description = "휴대전화번호정보")
    private String mobilePhoneNumberInfo;

    @Schema(description = "휴대전화번호정보Hash값")
    private String mobilePhoneNoInfoHashVlu;

    @Schema(description = "자택전화번호정보")
    private String homePhoneNumberInfo;

    @Schema(description = "자택전화번호정보Hash값")
    private String homePhoneNoInfoHashValue;

    @Schema(description = "자택전화번호정보")
    private String officePhoneNumberInfo;

    @Schema(description = "자택전화번호정보Hash값")
    private String officePhoneNoInfoHashVlu;

    @Schema(description = "E-Mail주소")
    private String emailAddress;

    @Schema(description = "E-Mail주소Hash값")
    private String emailAdrsHashValue;

    @Schema(description = "출생일자V")
    private String birthDatev;

    @Schema(description = "최초생성시작일시")
    private String creationStartDate;

    @Schema(description = "최초생성종료일시")
    private String creationEndDate;
}