package com.cdp.portal.external.api.dto.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "고객 프로필")
public class CustomerProfileResDto {
    
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
    
    @Schema(description = "생년월일")
    private String birthDatev;
    
    @Schema(description = "만나이")
    private String age;
    
    @Schema(description = "성별코드")
    private String sexCode;
    
    @Schema(description = "휴대전화번호")
    private String mobilePhoneNumber;
    
    @Schema(description = "E-Mail주소")
    private String emailAddress;
    
    @Schema(description = "스카이패스 정보 목록")
    private List<CustomerProfileResDto.SkypassInfo> skypassInfos = new ArrayList<>();
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "스카이패스 정보")
    public static class SkypassInfo {
        
        @Schema(description = "OneID번호")
        private String oneidNo;

        @Schema(description = "Skypass회원번호")
        private String skypassMemberNumber;
        
    }

}
