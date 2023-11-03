package com.cdp.portal.app.facade.bizmeta.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.cdp.portal.app.facade.bizmeta.dto.request.TableSpecReqDto;
import com.cdp.portal.app.facade.bizmeta.model.TableSpecModel;
import com.cdp.portal.common.dto.ApiResDto;
import com.cdp.portal.common.dto.PagingDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "테이블 정의서 응답")
public class TableSpecResDto {
    
    @Schema(description = "API 응답(테이블 정의서 목록 결과)")
    public static class ApiResTableSpecs extends ApiResDto<TableSpecsResult> {}
    
    @Schema(description = "API 응답(테이블 정의서 상세)")
    public static class ApiResTableSpec extends ApiResDto<TableSpec> {}
    
    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "테이블 정의서 목록")
    public static class TableSpecs extends TableSpecModel {
        
        @Schema(description = "DB명", example = "")
        private String srcDbNm; 
        
    }
    
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "테이블 정의서 상세")
    public static class TableSpec extends TableSpecModel {
        
        private List<TableColumnSpecResDto> columnSpecs = new ArrayList<>();
    }
    
    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @Schema(description = "테이블 정의서 목록 결과")
    public static class TableSpecsResult {
        
        @Schema(description = "컨텐츠 정보", nullable = false)
        private List<TableSpecs> contents;
        
        @Schema(description = "검색 정보", nullable = false)
        private TableSpecReqDto.SearchTableColumnSpec search;
        
        @Schema(description = "페이지 정보", nullable = false)
        private PagingDto page;
        
    }

}
