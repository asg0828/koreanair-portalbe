package com.cdp.portal.app.fo.dept.controller;

import com.cdp.portal.app.facade.dept.dto.request.DeptMgmtReqDto;
import com.cdp.portal.app.facade.dept.dto.response.DeptMgmtResDto;
import com.cdp.portal.app.facade.dept.service.DeptMgmtService;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_FO_PREFIX + "/dept-mgmt")
@Tag(name = "dept-mgmt", description = "부서 관리 API")
public class FoDeptMgmtController {
    private final DeptMgmtService deptMgmtService;

    @Operation(summary = "부서 저장", description = "부서 정보를 저장 한다.", tags = { "dept-mgmt" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @PostMapping(value = "/v1/depts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDepts(@Valid @RequestBody List<DeptMgmtReqDto.SaveDept> dto) {
        deptMgmtService.saveDepts(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "부서 목록 조회", description = "부서 목록을 조회한다.", tags = { "dept-mgmt" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = DeptMgmtResDto.ApiResDepts.class)))
    }
    )
    @Parameter(name ="deptNm", required = false, description = "부서(팀)명", example = "")
    @GetMapping(value = "/v1/depts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDepts(
            @RequestParam(value ="deptNm", required = false, defaultValue = "" ) String deptNm) {

        DeptMgmtReqDto.SearchDept searchDto = DeptMgmtReqDto.SearchDept.builder()
                .deptNm(deptNm)
                .build();

        return ResponseEntity.ok(ApiResDto.success(deptMgmtService.getDepts(searchDto)));
    }
}
