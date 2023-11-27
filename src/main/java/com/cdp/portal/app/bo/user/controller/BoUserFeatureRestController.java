package com.cdp.portal.app.bo.user.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.user.dto.request.UserFeatureReqDto;
import com.cdp.portal.app.facade.user.dto.response.UserFeatureResDto.ApiResUserFeatures;
import com.cdp.portal.app.facade.user.service.UserFeatureService;
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
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/user-mgmt")
@Tag(name = "user-feature", description = "사용자 관심 Feature 관리 API")
public class BoUserFeatureRestController {
    
    private final UserFeatureService userFeatureService;
    
    @Operation(summary = "사용자 관심 Feature 등록", description = "사용자 관심 Feature를 등록한다.", tags = { "user-feature" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name ="userId", required = true, description = "사용자ID", example = "")
    @PostMapping(value = "/v1/users/{userId}/features", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUserFeature(@PathVariable String userId, @Valid @RequestBody UserFeatureReqDto.CreateUserFeature dto) {
        userFeatureService.createUserFeature(userId, dto);
        
        return ResponseEntity.ok(ApiResDto.success());
    }
    
    @Operation(summary = "사용자 관심 Feature 목록 조회", description = "사용자 관심 Feature 목록을 조회한다.", tags = { "user-feature" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResUserFeatures.class)))
        }
    )
    @Parameter(name ="userId", required = true, description = "사용자ID", example = "")
    @Parameter(name ="page", required = false, description = "페이지", example = "1")
    @Parameter(name ="pageSize", required = false, description = "페이지 사이즈", example = "10")
    @GetMapping(value = "/v1/users/{userId}/features", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFeatures(
            @PathVariable String userId,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        
        PagingDto pagingDto = PagingDto.builder()
                .page(page)
                .pageSize(pageSize)
                .build();
        
        return ResponseEntity.ok(ApiResDto.success(userFeatureService.getUserFeatures(userId, pagingDto)));
    }
    
    @Operation(summary = "사용자 관심 Feature 삭제(단건)", description = "사용자 관심 Feature를 삭제한다.", tags = { "user-feature" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name ="userId", required = true, description = "사용자ID", example = "")
    @Parameter(name ="featureId", required = true, description = "피쳐ID", example = "")
    @DeleteMapping(value = "/v1/users/{userId}/features/{featureId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUserFeature(@PathVariable String userId, @PathVariable String featureId) {
        userFeatureService.deleteUserFeature(userId, featureId);
        
        return ResponseEntity.ok(ApiResDto.success());
    }
    
    @Operation(summary = "사용자 관심 Feature 삭제(다건)", description = "사용자 관심 Feature를 삭제한다.", tags = { "user-feature" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name ="userId", required = true, description = "사용자ID", example = "")
    @Parameter(name ="featureIds", required = true, description = "피쳐ID", example = "")
    @DeleteMapping(value = "/v1/users/{userId}/features", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUserFeatures(
            @PathVariable String userId, 
            @RequestParam(value = "featureIds", required = false, defaultValue = "") String[] featureIds) {
        userFeatureService.deleteUserFeatures(userId, featureIds);
        
        return ResponseEntity.ok(ApiResDto.success());
    }

}
