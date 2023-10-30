package com.cdp.portal.app.bo.code.conroller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.bo.code.dto.request.CodeReqDto;
import com.cdp.portal.app.bo.code.dto.response.CodeResDto.CodeResDtoResult;
import com.cdp.portal.app.bo.code.service.CodeService;
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
@RequestMapping(value = "/bo/system")
@Tag(name = "code", description = "코드 관리 API")
public class CodeApiController {
    
    private final CodeService codeService;
    
    /**
     * 코드 그룹 ID 전체 목록
     * @return
     */
    @Operation(summary = "코드 그룹 ID 전체 목록 조회 API", description = "코드 그룹 ID 전체 목록을 조회한다.", tags = { "code" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CodeResDtoResult.class)))
        }
    )
    @GetMapping(value = "/code-groups/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCodeGroups() {
        return ResponseEntity.ok(ApiResDto.success(codeService.getGroupIdAllList("GROUP_ID")));
    }
    
    /**
     * 코드 그룹 저장
     * @param dto
     * @return
     */
    @Operation(summary = "코드 그룹 저장 API", description = "코드 그룹을 신규 등록하거나 그룹아이디가 있으면 수정한다.", tags = { "code" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @PostMapping(value = "/code-groups", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCodeGroup(@Valid @RequestBody CodeReqDto.CreateCodeReq dto) {
        
        codeService.saveGroupId(dto);
        
        return ResponseEntity.ok(ApiResDto.success());
    }

}
