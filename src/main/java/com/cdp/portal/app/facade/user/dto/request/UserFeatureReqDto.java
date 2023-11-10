package com.cdp.portal.app.facade.user.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "사용자 Feature 요청")
public class UserFeatureReqDto {
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "사용자 Feature 등록")
    public static class CreateUserFeature {
        
        @NotBlank
        @Schema(description = "피쳐ID", nullable = false)
        private String featureId;
        
    }

}
