package com.cdp.portal.app.ext.feature.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "Feature 요청")
public class ExtFeatureReqDto {
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "Feature 등록")
    public static class CreateFeature {
        
        @NotBlank
        @Schema(description = "피쳐타입\n\n"
                + "- FACT_INDEX: FACT 지수\n\n"
                + "- INFERENCT_INDEX: 추론 지수", example = "FACT_INDEX", nullable = false, allowableValues = {"FACT_INDEX", "INFERENCT_INDEX"})
        private String featureTyp;
        
        @NotBlank
        @Schema(description = "피쳐구분(중구분)", example = "FAMILY", nullable = false)
        private String featureSe;
        
        @NotBlank
        @Schema(description = "피쳐한글명", example = "등록가족수", nullable = false)
        private String featureKoNm;
        
        @NotBlank
        @Schema(description = "피쳐영문명", example = "family mebers count", nullable = false)
        private String featureEnNm;
        
        @Schema(description = "산출단위", example = "PSN")
        private String calcUnt;
        
        @NotBlank
        @Schema(description = "피쳐정의", example = "SKYPASS에 등록된 가족수", nullable = false)
        private String featureDef;
        
        @Schema(description = "피쳐산식(산출로직)", example = "SELECT SKYPASS 한글명일치며부 FROM OneID_마스터 WHERE 타입코드 = 'P' AND 상태코드 = 'A'")
        private String featureFm;

        @NotBlank
        @Schema(description = "신청자 ID", example = "admin", nullable = false)
        private String enrUserId;
        
        @Schema(description = "신청부서코드", example = "SELUF")
        private String enrDeptCode;
        
        @Schema(description = "연관테이블", example = "TB_TEST")
        private String featureRelTb;
        
        @Schema(description = "비고", example = "비고")
        private String featureDsc;
        
    }

}
