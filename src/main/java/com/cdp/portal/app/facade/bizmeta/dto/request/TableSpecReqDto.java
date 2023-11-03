package com.cdp.portal.app.facade.bizmeta.dto.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "테이블 정의서 요청")
public class TableSpecReqDto {
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "테이블 정의서 등록")
    public static class CreateTableSpec {
        
        @NotBlank
        @Schema(description = "테이블정의한글명", example = "고객", nullable = false)
        private String mtsKoNm;
        
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z_]*$", message = "테이블 정의 영문명은 영문, '-'만 입력 가능합니다")
        @Schema(description = "테이블정의영문명", example = "OneID", nullable = false)
        private String mtsEnNm;
        
        @NotBlank
        @Schema(description = "정의", example = "고객 정의", nullable = false)
        private String mtsDef;
        
        @Schema(description = "원천시스템", example = "")
        private String srcSys;
        
        @Schema(description = "원천테이블명", example = "")
        private String srcTbNm;
        
        @Schema(description = "DB코드(코드 그룹 ID: DBMS)", example = "")
        private String srcDbCd;
        
        @Schema(description = "비고", example = "")
        private String mtsDsc;
        
        @Valid
        @Schema(description = "컬럼 정의 목록", example = "")
        private List<CreateTableColumnSpec> columnSpecs = new ArrayList<>();
        
    }
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "테이블 정의서 수정")
    public static class UpdateTableSpec {
        
        @NotBlank
        @Schema(description = "테이블정의한글명", example = "고객", nullable = false)
        private String mtsKoNm;
        
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z_]*$", message = "테이블 정의 영문명은 영문, '-'만 입력 가능합니다")
        @Schema(description = "테이블정의영문명", example = "OneID", nullable = false)
        private String mtsEnNm;
        
        @NotBlank
        @Schema(description = "정의", example = "고객 정의", nullable = false)
        private String mtsDef;
        
        @Schema(description = "원천시스템", example = "")
        private String srcSys;
        
        @Schema(description = "원천테이블명", example = "")
        private String srcTbNm;
        
        @Schema(description = "DB코드(코드 그룹 ID: DBMS)", example = "")
        private String srcDbCd;
        
        @Schema(description = "비고", example = "")
        private String mtsDsc;
        
        @Valid
        @Schema(description = "컬럼 정의 목록", example = "")
        private List<CreateTableColumnSpec> columnSpecs = new ArrayList<>();
        
    }
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "테이블 컬럼 정의서 등록")
    public static class CreateTableColumnSpec {
        
        @NotBlank
        @Schema(description = "컬럼정의한글명", example = "노선ID", nullable = false)
        private String mcsKoNm;
        
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z_]*$", message = "테이블 컬럼 정의 영문명은 영문, '-'만 입력 가능합니다")
        @Schema(description = "컬럼정의영문명", example = "route_id", nullable = false)
        private String mcsEnNm;
        
        @NotBlank
        @Schema(description = "정의", example = "노선ID", nullable = false)
        private String mcsDef;
        
        @Schema(description = "원천컬럼명", example = "")
        private String srcClNm;
        
        @Schema(description = "컬럼산출로직", example = "")
        private String clFm;
        
    }
    
    @Getter
    @Schema(description = "테이블 컬럼 정의서 검색")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class SearchTableColumnSpec {
        
        @Schema(description = "검색 테이블", example = "")
        private String searchTable;
        
        @Schema(description = "테이터셋 조건", example = "")
        private String[] dataSetConditions;
        
        @Schema(description = "DB코드(코드 그룹 ID: DBMS)", example = "")
        private String srcDbCd;
        
    }

}
