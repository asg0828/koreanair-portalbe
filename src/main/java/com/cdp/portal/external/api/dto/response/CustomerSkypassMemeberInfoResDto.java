package com.cdp.portal.external.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "스카이패스 회원 정보")
public class CustomerSkypassMemeberInfoResDto {
    
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
