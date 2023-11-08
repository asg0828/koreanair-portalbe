package com.cdp.portal.app.bo.feature.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.feature.dto.request.FeatureReqDto;
import com.cdp.portal.app.facade.feature.dto.response.FeatureResDto.ApiResFeature;
import com.cdp.portal.app.facade.feature.dto.response.FeatureSeparateResDto.ApiResFeatureSeparateResDtos;
import com.cdp.portal.app.facade.feature.service.FeatureSeparateService;
import com.cdp.portal.app.facade.feature.service.FeatureService;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.dto.ApiResDto;

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
@Tag(name = "feature", description = "Biz 메타 관리 > Feature 관리 API")
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

}
