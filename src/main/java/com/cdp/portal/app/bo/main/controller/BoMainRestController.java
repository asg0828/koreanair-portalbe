package com.cdp.portal.app.bo.main.controller;

import com.cdp.portal.app.facade.main.dto.response.MainResDto;
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
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/main")
@Tag(name = "main", description = "CDP 관리자 메인 totalCount API")
public class BoMainRestController {

    private final MainTotalCountService mainTotalCountService;

    @Operation(summary = "Feature 및 테이블정의서 총 건수 Count", description = "Feature 및 테이블정의서 총 건수 Count한다", tags = {"user-feature"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserFeatureResDto.ApiResUserFeatures.class)))
    }
    )
    @GetMapping(value = "/v1/biz-meta-count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBizMetaCount() {
        return ResponseEntity.ok(ApiResDto.success(mainTotalCountService.getUserBizMetaCount()));
    }

    @Operation(summary = "Admin 페이지 접속 정보", description = "Admin 페이지 접속 정보", tags = {"admin-main"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserFeatureResDto.ApiResUserFeatures.class)))
    }
    )
    @Parameter(name = "userId", required = true, description = "사용자ID", example = "")
    @GetMapping(value = "/v1/login-info/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAdminLoginInfo(@PathVariable String userId) {
        return ResponseEntity.ok(ApiResDto.success(mainTotalCountService.getAdminLoginInfo(userId)));
    }
}

//    @Operation(summary = "Admin 페이지 접속 정보", description = "Admin 페이지 접속 정보", tags = {"admin-main"})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserFeatureResDto.ApiResUserFeatures.class)))
//    }
//    )
//    @GetMapping(value = "/v1/login-user-dept", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> getLoginUserbyDept() {
//        return ResponseEntity.ok(ApiResDto.success(mainTotalCountService.getLoginUserbyDept()));
//    }
//}
