package com.cdp.portal.app.facade.feature.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "Feature 요청")
public class FeatureReqDto {
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "Feature 등록")
    public static class CreateFeature {
        
        @NotBlank
        @Schema(description = "피쳐타입", example = "", nullable = false)
        private String featureTyp;
        
        @NotBlank
        @Schema(description = "피쳐구분(중구분)", example = "", nullable = false)
        private String featureSe;
        
        @NotBlank
        @Schema(description = "피쳐한글명", example = "", nullable = false)
        private String featureKoNm;
        
        @NotBlank
        @Schema(description = "피쳐영문명", example = "", nullable = false)
        private String featureEnNm;
        
        @Schema(description = "산출단위", example = "")
        private String calcUnt;
        
        @NotBlank
        @Schema(description = "피쳐정의", example = "", nullable = false)
        private String featureDef;
        
        @Schema(description = "피쳐산식(산출로직)", example = "")
        private String featureFm;

        @NotBlank
        @Schema(description = "신청자 ID", example = "", nullable = false)
        private String enrUserId;
        
        @Schema(description = "연관테이블", example = "")
        private String featureRelTb;
        
        @Schema(description = "비고", example = "")
        private String featureDsc;
        
    }

}
