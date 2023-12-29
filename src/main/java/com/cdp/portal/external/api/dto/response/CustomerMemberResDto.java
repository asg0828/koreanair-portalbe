package com.cdp.portal.external.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "고객 멤버 정보")
public class CustomerMemberResDto {
    
    @Schema(description = "한글FirstName")
    private String korFname;
    
    @Schema(description = "한글LastName")
    private String korLname;
    
    @Schema(description = "영문FirstName")
    private String engFname;

    @Schema(description = "영문LastName")
    private String engLname;
    
    @Schema(description = "Skypass회원번호")
    private String skypassMemberNumber;
    
    @Schema(description = "회원등급")
    private String membershipLevel;
    
    @Schema(description = "성별코드")
    private String sexCode;
    
    @Schema(description = "OneID번호")
    private String oneidNo;

}
