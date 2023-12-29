package com.cdp.portal.app.facade.customer.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.cdp.portal.common.dto.ApiResDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "고객360 응답")
public class CustomerResDto {
    
    public static class ApiResProfile extends ApiResDto<CustomerResDto.Profile> {}
    public static class ApiResMembers extends ApiResDto<List<CustomerResDto.Member>> {}
    public static class ApiResSkypassMemeberInfo extends ApiResDto<CustomerResDto.SkypassMemeberInfo> {}
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @JsonInclude(Include.NON_NULL)
    @Schema(description = "프로필")
    public static class Profile {
        
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
        
        @Builder.Default
        @Schema(description = "스카이패스 정보 목록")
        private List<CustomerResDto.SkypassInfo> skypassInfos = new ArrayList<>();
    }
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @Schema(description = "스카이패스 정보")
    public static class SkypassInfo {
        
        @Schema(description = "OneID번호")
        private String oneidNo;

        @Schema(description = "Skypass회원번호")
        private String skypassMemberNumber;
        
    }
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @Schema(description = "멤버 정보")
    public static class Member {
        
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
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @Schema(description = "스카이패스 회원 정보")
    public static class SkypassMemeberInfo {
        
        @Schema(description = "Skypass회원번호")
        private String skypassMemberNumber;
        
        @Schema(description = "회원 Level")
        private String memberLevel;
        
        @Schema(description = "회원 상태명(휴면 여부)")
        private String memberStatusNm;
        
        @Schema(description = "현등급 최초 시작 일자")
        private String effectiveFrom;
        
        @Schema(description = "총 적립 마일리지")
        private String totalAccrued;
        
        @Schema(description = "잔여 마일리지")
        private String remainMileage;
        
        @Schema(description = "소멸 예정 마일리지")
        private String expiredMileage;
        
        @Schema(description = "PLCC 카드 보유")
        private Boolean hasPlccCard;
        
    }

}
