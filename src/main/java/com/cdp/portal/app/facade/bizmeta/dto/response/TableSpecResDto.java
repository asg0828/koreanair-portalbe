package com.cdp.portal.app.facade.bizmeta.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.cdp.portal.app.facade.bizmeta.model.TableSpecModel;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "테이블 정의서 응답")
public class TableSpecResDto {
    
    public static class ApiResTableSpecs extends ApiResDto<List<TableSpecs>> {}
    public static class ApiResTableSpec extends ApiResDto<TableSpec> {}
    
    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "테이블 정의서 목록")
    public static class TableSpecs extends TableSpecModel {
        
    }
    
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "테이블 정의서 상세")
    public static class TableSpec extends TableSpecModel {
        
        private List<TableColumnSpecResDto> columnSpecs = new ArrayList<>();
    }

}
