package com.cdp.portal.app.bo.code.conroller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.bo.code.dto.request.CodeReqDto;
import com.cdp.portal.app.bo.code.dto.response.CodeResDto.ApiResCodeResDto;
import com.cdp.portal.app.bo.code.dto.response.CodeResDto.ApiResCodeResDtos;
import com.cdp.portal.app.bo.code.service.CodeService;
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
@RequestMapping(value = "/bo/system")
@Tag(name = "code", description = "코드 관리 API")
public class CodeApiController {
    
    private final CodeService codeService;
    
    /**
     * 코드 그룹 전체 목록
     * @return
     */
    @Operation(summary = "코드 그룹 전체 목록 조회 API", description = "코드 그룹 전체 목록을 조회한다.", tags = { "code" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResCodeResDtos.class)))
        }
    )
    @GetMapping(value = "/code-groups/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCodeGroupsAll() {
        return ResponseEntity.ok(ApiResDto.success(codeService.getCodes("GROUP_ID")));
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
    public ResponseEntity<?> createCodeGroup(@Valid @RequestBody CodeReqDto.CreateGroupCodeReq dto) {
        codeService.saveCodeGroup(dto);
        
        return ResponseEntity.ok(ApiResDto.success());
    }
    
    /**
     * 코드 그룹 조회
     * @param groupCodeId
     * @return
     */
    @Operation(summary = "코드 그룹 조회 API", description = "코드 그룹을 조회한다.", tags = { "code" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResCodeResDto.class)))
        }
    )
    @Parameter(name ="groupId", required = true, description = "코드 그룹 ID", example = "USE_YN")
    @GetMapping(value = "/code-groups/{groupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCodeGroup(@PathVariable String groupId) {
        return ResponseEntity.ok(ApiResDto.success(codeService.getByGroupIdAndCodeId("GROUP_ID", groupId)));
    }
    
    /**
     * 코드 저장
     * @param codeGroupId
     * @param dto
     * @return
     */
    @Operation(summary = "코드 저장 API", description = "코드를 신규 등록하거나 등록된 코드가 있으면 수정한다.", tags = { "code" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name ="groupId", required = true, description = "코드 그룹 ID", example = "USE_YN")
    @PostMapping(value = "/code-groups/{groupId}/codes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCode(@PathVariable String groupId, @Valid @RequestBody CodeReqDto.CreateCodeReq dto) {
        codeService.saveCode(groupId, dto);
        
        return ResponseEntity.ok(ApiResDto.success());
    }
    
    /**
     * 코드 조회
     * @param groupId
     * @param codeId
     * @return
     */
    @Operation(summary = "코드 조회 API", description = "코드를 조회한다.", tags = { "code" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResCodeResDtos.class)))
        }
    )
    @Parameter(name ="groupId", required = true, description = "코드 그룹 ID", example = "USE_YN")
    @Parameter(name ="codeId", required = true, description = "코드 ID", example = "Y")
    @GetMapping(value = "/code-groups/{groupId}/codes/{codeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCode(@PathVariable String groupId, @PathVariable String codeId) {
        return ResponseEntity.ok(ApiResDto.success(codeService.getByGroupIdAndCodeId(groupId, codeId)));
    }
    
    /**
     * 코드 목록 조회
     * @param codeGroupId
     * @return
     */
    @Operation(summary = "코드 목록 조회 API", description = "코드 목록을 조회한다.", tags = { "code" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResCodeResDtos.class)))
        }
    )
    @Parameter(name ="groupId", required = true, description = "코드 그룹 ID", example = "USE_YN")
    @GetMapping(value = "/code-groups/{groupId}/codes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCodes(@PathVariable String groupId) {
        return ResponseEntity.ok(ApiResDto.success(codeService.getCodes(groupId)));
    }
    

}