package com.cdp.portal.app.facade.user.dto.response;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "사용자 Feature 응답")
public class UserFeatureResDto {
    
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "사용자 Feature 목록")
    public static class UserFeatures {
        
        @Schema(description = "사용자ID", nullable = false)
        private String userId;
        
        @Schema(description = "피쳐ID", nullable = false)
        private String featureId;
        
        @Schema(description = "등록자ID", example = "admin")
        private String rgstId;
        
        @Schema(description = "등록일시", example = "2021-04-13 09:04:40")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime rgstDt;
        
    }

}
