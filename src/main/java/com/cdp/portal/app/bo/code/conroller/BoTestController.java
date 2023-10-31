package com.cdp.portal.app.bo.code.conroller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.code.dto.response.CodeResDto.ApiResCodeResDtos;
import com.cdp.portal.common.IdUtil;
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
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/test")
@Tag(name = "code", description = "샘플 코드 테스트 API")
public class BoTestController {
	
	@Resource
	private IdUtil idUtil;
	
    /**
     * @Method      : idGenerateSample
     * @Date        : 2023. 10. 30.
     * @author      : pj.uhlee
     * @description : 저장시 테이블별 ID 채번
     * @return
     */
    @Operation(summary = "저장시 테이블별 ID 채번", description = "테이블 insert시 테이블별 ID(varchar(32))를 생성한다. 시작 prefix는 CommonConstants.ID 참고", tags = { "sample" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResCodeResDtos.class)))
        }
    )
    @GetMapping(value = "/v1/id-gen-test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> idGenerateSample() {
    	String qnaId = idUtil.getQnaId();
    	return ResponseEntity.ok(ApiResDto.success(qnaId));
    }
}
