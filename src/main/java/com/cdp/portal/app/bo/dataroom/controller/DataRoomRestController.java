package com.cdp.portal.app.bo.dataroom.controller;

import com.cdp.portal.app.facade.dataroom.service.DataRoomService;
import com.cdp.portal.app.facade.dataroom.dto.response.DataRoomResDto;
import com.cdp.portal.app.facade.dataroom.dto.request.DataRoomReqDto;

import com.cdp.portal.app.facade.notice.dto.request.NoticeReqDto;
import com.cdp.portal.app.facade.notice.dto.response.NoticeResDto;
import com.cdp.portal.common.IdUtil;
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

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/board")
@Tag(name = "board", description = "자료실 관리 API")
public class DataRoomRestController {

    @Resource
    private IdUtil idUtil;
    private final DataRoomService dataRoomService;

    @Operation(summary = "자료실 전체 목록 조회 API", description = "자료실 전체 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = DataRoomResDto.DataRoomResDtoResult.class)))
    }
    )

    @GetMapping(value = "/v1/dataroom/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDataRooms() {
        return ResponseEntity.ok(ApiResDto.success(dataRoomService.getDataRoomAllList()));
    }

    @Operation(summary = "자료실 상세 조회 API", description = "자료실 상세를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = DataRoomResDto.DataRoomResDtoResult.class)))
    }
    )
    @GetMapping(value = "/v1/dataroom/{dataId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> selectData(@PathVariable String dataId){
        dataRoomService.addViewCntData(dataId);

        return ResponseEntity.ok(ApiResDto.success(dataRoomService.getData(dataId)));
    }

    @Operation(summary = "자료실 등록 API", description = "자료를 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = DataRoomResDto.DataRoomResDtoResult.class)))
    }
    )
    @PostMapping(value = "/v1/dataroom", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createData(@Valid @RequestBody DataRoomReqDto.CreateDataRoomReq dto) {
        String dataId = idUtil.getDataId();
        dto.setDataId(dataId);
        dto.setModiId("admin");
        dataRoomService.createData(dto);

        return ResponseEntity.ok(ApiResDto.success(dataId));
    }

    @Operation(summary = "자료실 수정", description = "자료를 수정한다.", tags = { "dataroom" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @Parameter(name ="dataId", required = true, description = "자료실 ID", example = "1")
    @PutMapping(value = "/v1/dataroom/{dataId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDataRoom(@PathVariable String dataId, @Valid @RequestBody DataRoomReqDto.UpdateDataRoomReq dto) {
        dataRoomService.updateDataRoom(dataId, dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "자료실 SOFT 삭제 API", description = "공지사항을 SOFT 삭제한다.(del_yn)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = NoticeResDto.NoticeResDtoResult.class)))
    }
    )
    @PostMapping(value = "/v1/dataroom/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteData(@Valid @RequestBody DataRoomReqDto.DeleteDataRoomReq dto) {
        dto.setModiId("admin"); // 사용자 정보 받아오기 전까지 일단 하드코딩
        dataRoomService.deleteDataRoom(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "자료실 삭제 API", description = "자료를 완전 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = NoticeResDto.NoticeResDtoResult.class)))
    }
    )
    @DeleteMapping(value = "/v1/dataroom", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDataRoom2(@Valid @RequestBody DataRoomReqDto.DeleteDataRoomReq dto) {
        dataRoomService.deleteDataRoom2(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }
}
