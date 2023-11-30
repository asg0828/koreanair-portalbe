package com.cdp.portal.app.bo.egroup.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.egroup.dto.request.EgroupMgmtReqDto;
import com.cdp.portal.app.facade.egroup.dto.response.EgroupMgmtResDto.ApiResEgroupUsers;
import com.cdp.portal.app.facade.egroup.dto.response.EgroupMgmtResDto.ApiResEgroups;
import com.cdp.portal.app.facade.egroup.mapper.EgroupUserMapper;
import com.cdp.portal.app.facade.egroup.service.EgroupMgmtService;
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
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/egroup-mgmt")
@Tag(name = "egroup-mgmt", description = "사용자 예외그룹 관리 API")
public class BoEgroupMgmtController {
	private final EgroupMgmtService egroupMgmtService;
	private final EgroupUserMapper egroupUserMapper;

	@Operation(summary = "사용자 예외그룹 저장", description = "사용자 예외그룹 정보를 저장 한다.", tags = { "egroup-mgmt" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @PostMapping(value = "/v1/egroups", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveEgroups(@Valid @RequestBody EgroupMgmtReqDto.EgroupInfos dto) {
		egroupMgmtService.saveEgroupInfos(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

	@Operation(summary = "사용자 예외그룹 목록 조회", description = "사용자 예외그룹 목록을 조회한다.", tags = { "egroup-mgmt" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResEgroups.class)))
        }
    )
    @Parameter(name ="groupNm", required = false, description = "사용자 예외그룹명", example = "")
    @GetMapping(value = "/v1/egroups", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEgroups(
            @RequestParam(value ="groupNm", required = false, defaultValue = "" ) String groupNm) {

        EgroupMgmtReqDto.SearchEgroup searchDto = EgroupMgmtReqDto.SearchEgroup.builder()
        		.groupNm(groupNm)
                .build();

        return ResponseEntity.ok(ApiResDto.success(egroupMgmtService.getEgroups(searchDto)));
    }

	@Operation(summary = "사용자 예외그룹 사용자 목록 조회", description = "사용자 예외그룹 사용자 목록을 조회한다.", tags = { "egroup-mgmt" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResEgroupUsers.class)))
	}
			)
	@Parameter(name ="groupCode", required = false, description = "사용자 예외그룹 코드", example = "")
	@GetMapping(value = "/v1/egroup/{groupCode}/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEgroupUsers(
			@PathVariable String groupCode) {

		return ResponseEntity.ok(ApiResDto.success(egroupUserMapper.selectByGroupCode(groupCode)));
	}
}
