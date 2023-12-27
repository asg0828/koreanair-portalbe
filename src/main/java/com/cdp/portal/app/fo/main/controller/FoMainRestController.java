package com.cdp.portal.app.fo.main.controller;

import com.cdp.portal.app.facade.main.service.MainTotalCountService;
import com.cdp.portal.app.facade.user.dto.response.UserFeatureResDto;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_FO_PREFIX + "/main")
@Tag(name = "main", description = "CDP 메인 totalCount API")
public class FoMainRestController {

    private final MainTotalCountService mainTotalCountService;

    @Operation(summary = "Feature 및 테이블정의서 총 건수 Count", description = "Feature 및 테이블정의서 총 건수 Count한다", tags = {"user-feature"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserFeatureResDto.ApiResUserFeatures.class)))
    }
    )
    @GetMapping(value = "/v1/biz-meta-count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBizMetaCount() {
        return ResponseEntity.ok(ApiResDto.success(mainTotalCountService.getBizMetaCount()));
    }

    @Operation(summary = "내 부서의 신청/등록Feature와 관심Feature 총 건수를 Count", description = "내 부서의 신청/등록Feature와 관심Feature 총 건수를 Count한다.", tags = {"user-feature"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserFeatureResDto.ApiResUserFeatures.class)))
    }
    )
    @Parameter(name = "userId", required = true, description = "사용자ID", example = "")
    @GetMapping(value = "/v1/users/{userId}/features-mydept", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFeatureDeptCount(
            @PathVariable String userId) {
        return ResponseEntity.ok(ApiResDto.success(mainTotalCountService.getFeaturesDeptCount(userId)));
    }
}
