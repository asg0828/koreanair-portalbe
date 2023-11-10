package com.cdp.portal.app.fo.table.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.table.dto.request.TableSpecReqDto;
import com.cdp.portal.app.facade.table.dto.response.TableSpecResDto.ApiResTableSpec;
import com.cdp.portal.app.facade.table.dto.response.TableSpecResDto.ApiResTableSpecs;
import com.cdp.portal.app.facade.table.service.TableSpecService;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.dto.ApiResDto;
import com.cdp.portal.common.dto.PagingDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_FO_PREFIX + "/biz-meta")
@Tag(name = "table-spec", description = "테이블 정의서 관리 API")
public class FoTableSpecRestController {
    
    private final TableSpecService tableSpecService;
    
    @Operation(summary = "테이블 정의서 목록 조회", description = "테이블 정의서 목록을 조회한다.", tags = { "table-spec" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResTableSpecs.class)))
        }
    )
    @Parameter(name ="page", required = false, description = "페이지", example = "1")
    @Parameter(name ="pageSize", required = false, description = "페이지 사이즈", example = "10")
    @Parameter(name ="searchTable", required = false, description = "검색 테이블", example = "")
    @Parameter(name ="dataSetConditions", required = false, description = "테이터셋 조건(mtsEnNm: 테이블정의영문명, mtsKoNm: 테이블정의한글명, mtsDef: 테이블정의, srcTbNm: 원천테이블명)", example = "")
    @Parameter(name ="srcDbCd", required = false, description = "DB코드(코드 그룹 ID: DBMS)", example = "POSTGRESQL")
    @GetMapping(value = "/v1/table-specs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTableSpecs(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "searchTable", required = false, defaultValue = "") String searchTable,
            @RequestParam(value = "dataSetConditions", required = false, defaultValue = "") String[] dataSetConditions,
            @RequestParam(value = "srcDbCd", required = false, defaultValue = "") String srcDbCd) {
        
        PagingDto pagingDto = PagingDto.builder()
                .page(page)
                .pageSize(pageSize)
                .build();
        
        TableSpecReqDto.SearchTableSpec searchDto = TableSpecReqDto.SearchTableSpec.builder()
                .searchTable(searchTable)
                .dataSetConditions(dataSetConditions)
                .srcDbCd(srcDbCd)
                .build();
        
        return ResponseEntity.ok(ApiResDto.success(tableSpecService.getTableSpecs(pagingDto, searchDto)));
    }
    
    @Operation(summary = "테이블 정의서 조회", description = "테이블 정의서를 조회한다.", tags = { "table-spec" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResTableSpec.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name ="mtsId", required = true, description = "테이블정의ID", example = "mt23000000005")
    @GetMapping(value = "/v1/table-specs/{mtsId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTableSpec(@PathVariable String mtsId) {
        return ResponseEntity.ok(ApiResDto.success(tableSpecService.getTableSpec(mtsId)));
    }

}
