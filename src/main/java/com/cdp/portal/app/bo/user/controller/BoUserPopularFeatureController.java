package com.cdp.portal.app.bo.user.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.user.dto.response.UserFeatureResDto.ApiUserPopularFeatures;
import com.cdp.portal.app.facade.user.service.UserFeatureService;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/user-mgmt")
@Tag(name = "user-popular-feature", description = "사용자 인기 Feature 관리 API")
public class BoUserPopularFeatureController {
    
    private final UserFeatureService userFeatureService;
    
    @Operation(summary = "사용자 인기 Feature 조회", description = "사용자 인기 Feature를 조회한다.", tags = { "user-popular-feature" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiUserPopularFeatures.class)))
        }
    )
    @GetMapping(value = "/v1/user-popular-features", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserPopularFeatures() {
        return ResponseEntity.ok(ApiResDto.success(userFeatureService.getUserPopularFeatures()));
    }

}
