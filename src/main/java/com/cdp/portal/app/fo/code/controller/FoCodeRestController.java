package com.cdp.portal.app.fo.code.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.code.dto.response.CodeResDto.ApiResCodeResDtos;
import com.cdp.portal.app.facade.code.service.CodeService;
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
@RequestMapping(value = CommonConstants.API_FO_PREFIX + "/system")
@Tag(name = "code", description = "코드 관리 API")
public class FoCodeRestController {
    
    private final CodeService codeService;
    
    /**
     * 코드 그룹 전체 목록
     * @return
     */
    @Operation(summary = "코드 그룹 전체 목록 조회", description = "코드 그룹 전체 목록을 조회한다.", tags = { "code" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResCodeResDtos.class)))
        }
    )
    @GetMapping(value = "/v1/code-groups/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCodeGroupsAll() {
        return ResponseEntity.ok(ApiResDto.success(codeService.getCodes("GROUP_ID")));
    }
    
    /**
     * 코드 목록 조회
     * @param codeGroupId
     * @return
     */
    @Operation(summary = "코드 목록 조회", description = "코드 목록을 조회한다.", tags = { "code" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResCodeResDtos.class)))
        }
    )
    @Parameter(name ="groupId", required = true, description = "코드 그룹 ID", example = "USE_YN")
    @GetMapping(value = "/v1/code-groups/{groupId}/codes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCodes(@PathVariable String groupId) {
        return ResponseEntity.ok(ApiResDto.success(codeService.getCodes(groupId)));
    }

}
