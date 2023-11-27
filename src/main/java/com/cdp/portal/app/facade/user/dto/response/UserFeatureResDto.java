package com.cdp.portal.app.facade.user.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.cdp.portal.common.dto.ApiResDto;
import com.cdp.portal.common.dto.PagingDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "사용자 Feature 응답")
public class UserFeatureResDto {
    
    public static class ApiResUserFeatures extends ApiResDto<UserFeatureResDto.UserFeaturesResult> {}
    public static class ApiUserPopularFeatures extends ApiResDto<List<UserFeatureResDto.UserPopularFeatures>> {}
    
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
        
        @Schema(description = "등록자ID(관심 피쳐 등록자ID)", example = "admin")
        private String rgstId;
        
        @Schema(description = "등록일시(관심 피쳐 등록일시)", example = "2021-04-13 09:04:40")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime rgstDt;
        
        @Schema(description = "피쳐타입", example = "", nullable = false)
        private String featureTyp;
        
        @Schema(description = "피쳐타입명", example = "")
        private String featureTypNm;
        
        @Schema(description = "피쳐대구분", example = "", nullable = false)
        private String featureSeGrp;
        
        @Schema(description = "피쳐대구분명", example = "")
        private String featureSeGrpNm;
        
        @Schema(description = "피쳐중구분", example = "", nullable = false)
        private String featureSe;
        
        @Schema(description = "피쳐중구분명", example = "")
        private String featureSeNm;
        
        @Schema(description = "피쳐한글명", example = "", nullable = false)
        private String featureKoNm;
        
        @Schema(description = "피쳐영문명", example = "", nullable = false)
        private String featureEnNm;
        
        @Schema(description = "산출단위", example = "")
        private String calcUnt;
        
        @Schema(description = "피쳐정의", example = "", nullable = false)
        private String featureDef;
        
        @Schema(description = "피쳐산식(산출로직)", example = "")
        private String featureFm;
        
        @Schema(description = "신청자 ID", example = "", nullable = false)
        private String enrUserId;
        
        @Schema(description = "신청자명", example = "", nullable = false)
        private String enrUserNm;
        
        @Schema(description = "신청부서코드", example = "")
        private String enrDeptCode;
        
        @Schema(description = "신청부서명", example = "")
        private String enrDeptNm;
        
        @Schema(description = "연관테이블", example = "")
        private String featureRelTb;
        
        @Schema(description = "비고", example = "")
        private String featureDsc;
        
        @Schema(description = "삭제여부", example = "")
        private String delYn;
        
    }
    
    @Getter
    @ToString
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Schema(description = "사용자 Feature 목록 결과")
    public static class UserFeaturesResult {
        
        @Schema(description = "컨텐츠 정보", nullable = false)
        private List<UserFeatures> contents;
        
        @Schema(description = "페이지 정보", nullable = false)
        private PagingDto page;
    }
    
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "사용자 인기 Feature 목록")
    public static class UserPopularFeatures {
        
        @Schema(description = "피쳐ID", example = "", nullable = false)
        private String featureId;
        
        @Schema(description = "피쳐타입", example = "", nullable = false)
        private String featureTyp;
        
        @Schema(description = "피쳐타입명", example = "")
        private String featureTypNm;
        
        @Schema(description = "피쳐대구분", example = "", nullable = false)
        private String featureSeGrp;
        
        @Schema(description = "피쳐대구분명", example = "")
        private String featureSeGrpNm;
        
        @Schema(description = "피쳐중구분", example = "", nullable = false)
        private String featureSe;
        
        @Schema(description = "피쳐중구분명", example = "")
        private String featureSeNm;
        
        @Schema(description = "피쳐한글명", example = "", nullable = false)
        private String featureKoNm;
        
        @Schema(description = "피쳐영문명", example = "", nullable = false)
        private String featureEnNm;
        
        @Schema(description = "산출단위", example = "")
        private String calcUnt;
        
        @Schema(description = "피쳐정의", example = "", nullable = false)
        private String featureDef;
        
        @Schema(description = "피쳐산식(산출로직)", example = "")
        private String featureFm;
        
        @Schema(description = "신청자 ID", example = "", nullable = false)
        private String enrUserId;
        
        @Schema(description = "신청자명", example = "", nullable = false)
        private String enrUserNm;
        
        @Schema(description = "신청부서코드", example = "")
        private String enrDeptCode;
        
        @Schema(description = "신청부서명", example = "")
        private String enrDeptNm;
        
        @Schema(description = "연관테이블", example = "")
        private String featureRelTb;
        
        @Schema(description = "비고", example = "")
        private String featureDsc;
        
        @Schema(description = "사용자 피쳐 여부(관심 피쳐)", example = "true|false")
        private Boolean isUserFeature;
        
        @Schema(description = "사용자 피쳐 개수", example = "")
        private int userFeatureCnt;
        
        @Schema(description = "삭제여부", example = "")
        private String delYn;
        
        @Schema(description = "등록자ID", example = "admin")
        private String rgstId;
        
        @Schema(description = "등록일시", example = "2021-04-13 09:04:40")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime rgstDt;
        
        @Schema(description = "수정자ID", example = "admin")
        private String modiId;
        
        @Schema(description = "수정일시", example = "2021-04-13 09:04:40")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime modiDt;
        
    }

}
