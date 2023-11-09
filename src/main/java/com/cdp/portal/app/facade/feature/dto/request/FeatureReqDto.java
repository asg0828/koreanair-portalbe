package com.cdp.portal.app.facade.feature.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "Feature 요청")
public class FeatureReqDto {
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "Feature 등록")
    public static class CreateFeature {
        
        @NotBlank
        @Schema(description = "피쳐타입(코드 그룹 ID: FEATURE_TYPE)", example = "", nullable = false)
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
        
        @Schema(description = "신청부서코드", example = "")
        private String enrDeptCode;
        
        @Schema(description = "연관테이블", example = "")
        private String featureRelTb;
        
        @Schema(description = "비고", example = "")
        private String featureDsc;
        
    }
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "Feature 수정")
    public static class updateFeature {
        
        @NotBlank
        @Schema(description = "피쳐타입(코드 그룹 ID: FEATURE_TYPE)", example = "", nullable = false)
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
        
        @Schema(description = "신청부서코드", example = "")
        private String enrDeptCode;
        
        @Schema(description = "연관테이블", example = "")
        private String featureRelTb;
        
        @Schema(description = "비고", example = "")
        private String featureDsc;
        
    }
    
    @Getter
    @Schema(description = "Feature 검색")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class SearchFeature {
        
        @Schema(description = "피쳐대구분", example = "")
        private String featureSeGrp;
        
        @Schema(description = "피쳐중구분", example = "")
        private String featureSe;
        
        @Schema(description = "검색 Feature", example = "")
        private String searchFeature;
        
        @Schema(description = "검색 조건", example = "")
        private String[] searchConditions;
        
        @Schema(description = "신청자 ID", example = "")
        private String enrUserId;
        
        @Schema(description = "신청부서코드", example = "")
        private String enrDeptCode;
        
    }

}
