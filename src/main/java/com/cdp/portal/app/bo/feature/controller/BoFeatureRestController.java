package com.cdp.portal.app.bo.feature.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.feature.dto.request.FeatureReqDto;
import com.cdp.portal.app.facade.feature.dto.response.FeatureResDto.ApiResFeature;
import com.cdp.portal.app.facade.feature.dto.response.FeatureResDto.ApiResFeatures;
import com.cdp.portal.app.facade.feature.dto.response.FeatureResDto.ApiResFeaturesResult;
import com.cdp.portal.app.facade.feature.dto.response.FeatureSeparateResDto.ApiResFeatureSeparateResDtos;
import com.cdp.portal.app.facade.feature.service.FeatureSeparateService;
import com.cdp.portal.app.facade.feature.service.FeatureService;
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
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/biz-meta")
@Tag(name = "feature", description = "Feature 관리 API")
public class BoFeatureRestController {
    
    private final FeatureSeparateService featureSeparateService;
    private final FeatureService featureService;
    
    @Operation(summary = "Feature 대구분 목록 조회", description = "Feature 대구분 목록을 조회한다.", tags = { "feature" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResFeatureSeparateResDtos.class)))
        }
    )
    @GetMapping(value = "/v1/feature-separates/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFeatureSeparatesAll() {
        return ResponseEntity.ok(ApiResDto.success(featureSeparateService.getFeatureSeparates("SE_GRP_ID")));
    }
    
    @Operation(summary = "Feature 중구분 목록 조회", description = "Feature 중구분 목록을 조회한다.", tags = { "feature" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResFeatureSeparateResDtos.class)))
        }
    )
    @Parameter(name ="seGrpId", required = true, description = "구분그룹ID", example = "CUSTOMER_SERVICE")
    @GetMapping(value = "/v1/feature-separates/{seGrpId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFeatureSeparates(@PathVariable String seGrpId) {
        return ResponseEntity.ok(ApiResDto.success(featureSeparateService.getFeatureSeparates(seGrpId)));
    }
    
    @Operation(summary = "Feature 등록", description = "Feature를 등록한다.", tags = { "feature" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @PostMapping(value = "/v1/features", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createFeature(@Valid @RequestBody FeatureReqDto.CreateFeature dto) {
        featureService.createFeature(dto);
        
        return ResponseEntity.ok(ApiResDto.success());
    }
    
    @Operation(summary = "Feature 전체 목록 조회(검색)", description = "Feature 전체 목록을 조회한다.", tags = { "feature" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResFeatures.class)))
        }
    )
    @Parameter(name ="featureKoNm", required = false, description = "피쳐한글명", example = "")
    @Parameter(name ="featureEnNm", required = false, description = "피쳐영문명", example = "")
    @GetMapping(value = "/v1/features/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFeaturesAll(
            @RequestParam(value = "featureKoNm", required = false, defaultValue = "") String featureKoNm,
            @RequestParam(value = "featureEnNm", required = false, defaultValue = "") String featureEnNm) {
        
        FeatureReqDto.SearchFeature searchDto = FeatureReqDto.SearchFeature.builder()
                .featureKoNm(featureKoNm)
                .featureEnNm(featureEnNm)
                .build();
        
        return ResponseEntity.ok(ApiResDto.success(featureService.getFeatures(searchDto)));
    }
    
    @Operation(summary = "Feature 목록 조회", description = "Feature 목록을 조회한다.", tags = { "feature" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResFeaturesResult.class)))
        }
    )
    @Parameter(name ="page", required = false, description = "페이지", example = "1")
    @Parameter(name ="pageSize", required = false, description = "페이지 사이즈", example = "10")
    @Parameter(name ="featureSeGrp", required = false, description = "피쳐대구분", example = "")
    @Parameter(name ="featureSe", required = false, description = "피쳐중구분", example = "")
    @Parameter(name ="searchFeature", required = false, description = "검색 Feature", example = "")
    @Parameter(name ="searchConditions", required = false, description = "검색 조건(featureKoNm: 피쳐한글명, featureEnNm: 피쳐영문명, featureDef: 피쳐정의)", example = "")
    @Parameter(name ="enrUserId", required = false, description = "신청자 ID", example = "")
    @Parameter(name ="enrDeptCode", required = false, description = "신청부서코드", example = "")
    @GetMapping(value = "/v1/features", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFeatures(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "featureSeGrp", required = false, defaultValue = "") String featureSeGrp,
            @RequestParam(value = "featureSe", required = false, defaultValue = "") String featureSe, 
            @RequestParam(value = "searchFeature", required = false, defaultValue = "") String searchFeature,
            @RequestParam(value = "searchConditions", required = false, defaultValue = "") String[] searchConditions,
            @RequestParam(value = "enrUserId", required = false, defaultValue = "") String enrUserId,
            @RequestParam(value = "enrDeptCode", required = false, defaultValue = "") String enrDeptCode) {
        
        PagingDto pagingDto = PagingDto.builder()
                .page(page)
                .pageSize(pageSize)
                .build();
        
        FeatureReqDto.SearchFeature searchDto = FeatureReqDto.SearchFeature.builder()
                .featureSeGrp(featureSeGrp)
                .featureSe(featureSe)
                .searchFeature(searchFeature)
                .searchConditions(searchConditions)
                .enrUserId(enrUserId)
                .enrDeptCode(enrDeptCode)
                .build();
        
        return ResponseEntity.ok(ApiResDto.success(featureService.getFeatures(pagingDto, searchDto)));
    }
    
    @Operation(summary = "Feature 조회", description = "Feature를 조회한다.", tags = { "feature" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResFeature.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name ="featureId", required = true, description = "피쳐ID", example = "")
    @GetMapping(value = "/v1/features/{featureId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFeature(@PathVariable String featureId) {
        return ResponseEntity.ok(ApiResDto.success(featureService.getFeature(featureId)));
    }
    
    @Operation(summary = "Feature 수정", description = "Feature를 수정한다.", tags = { "feature" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name ="featureId", required = true, description = "피쳐ID", example = "")
    @PutMapping(value = "/v1/features/{featureId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateFeature(@PathVariable String featureId, @Valid @RequestBody FeatureReqDto.updateFeature dto) {
        featureService.updateFeature(featureId, dto);
        
        return ResponseEntity.ok(ApiResDto.success());
    }
    
    @Operation(summary = "Feature 삭제", description = "Feature를 삭제한다.", tags = { "feature" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name ="featureId", required = true, description = "피쳐ID", example = "")
    @DeleteMapping(value = "/v1/features/{featureId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteFeature(@PathVariable String featureId) {
        featureService.deleteFeature(featureId);
        
        return ResponseEntity.ok(ApiResDto.success());
    }

}
