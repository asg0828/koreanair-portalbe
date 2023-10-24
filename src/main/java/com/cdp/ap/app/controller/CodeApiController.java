package com.cdp.ap.app.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.ap.app.dto.response.CodeResDto.CodeResDtoResult;
import com.cdp.ap.app.service.CodeService;
import com.cdp.ap.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/system")
@Tag(name = "code", description = "코드 관리 API")
public class CodeApiController {
    
    private final CodeService codeService;
    
    /**
     * 코드 그룹 ID 전체 목록
     * @return
     */
    @Operation(summary = "코드 그룹 ID 전체 목록 조회 API", description = "코드 그룹 ID 전체 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = CodeResDtoResult.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
            }
    )
    @GetMapping(value = "/code-groups/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCodeGroups() {
        return ResponseEntity.ok(ApiResDto.createSuccess(codeService.getGroupIdAllList("GROUP_ID")));
    }

}
