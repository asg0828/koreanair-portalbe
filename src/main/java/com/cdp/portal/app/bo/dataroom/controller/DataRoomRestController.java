package com.cdp.portal.app.bo.dataroom.controller;

import com.cdp.portal.app.facade.dataroom.service.DataRoomService;
import com.cdp.portal.app.facade.dataroom.dto.response.DataRoomResDto;
import com.cdp.portal.app.facade.dataroom.dto.request.DataRoomReqDto;

import com.cdp.portal.app.facade.notice.dto.response.NoticeResDto;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.dto.ApiResDto;
import com.cdp.portal.common.dto.PagingDto;
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
    private final DataRoomService dataRoomService;

    @Operation(summary = "자료실 등록 API", description = "자료를 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = DataRoomResDto.DataRoomResDtoResult.class)))
    }
    )
    @PostMapping(value = "/v1/dataroom", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createData(@Valid @RequestBody DataRoomReqDto.CreateDataRoomReq dto) {
        dto.setRgstId("admin");
        dto.setModiId("admin");
        dataRoomService.createData(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "자료실 목록 조회", description = "자료실 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @Parameter(name ="page", required = false, description = "페이지", example = "1")
    @Parameter(name ="pageSize", required = false, description = "페이지 사이즈", example = "10")
    @Parameter(name ="searchTable", required = false, description = "검색 테이블", example = "")
    @Parameter(name ="searchConditions", required = false, description = "검색 조건", example = "")
    @GetMapping(value = "/v1/dataroom", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDataRooms(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "searchTable", required = false, defaultValue = "") String searchTable,
            @RequestParam(value = "searchConditions", required = false, defaultValue = "") String[] searchConditions) {
        PagingDto pagingDto = PagingDto.builder()
                .page(page)
                .pageSize(pageSize)
                .build();

        DataRoomReqDto.SearchDataRoom searchDto = DataRoomReqDto.SearchDataRoom.builder()
                .searchTable(searchTable)
                .searchConditions(searchConditions)
                .build();

        return ResponseEntity.ok(ApiResDto.success(dataRoomService.getDataRooms(pagingDto,searchDto)));
    }

    @Operation(summary = "자료실 조회", description = "자료실을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = DataRoomResDto.DataRoomResDtoResult.class)))
    }
    )
    @GetMapping(value = "/v1/dataroom/{dataId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> selectData(@PathVariable String dataId){
        dataRoomService.addViewCntData(dataId);

        return ResponseEntity.ok(ApiResDto.success(dataRoomService.getData(dataId)));
    }

    @Operation(summary = "자료실 수정", description = "자료를 수정한다.", tags = { "dataroom" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @Parameter(name ="dataId", required = true, description = "자료실 ID", example = "1")
    @PutMapping(value = "/v1/dataroom/{dataId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDataRoom(@PathVariable String dataId, @Valid @RequestBody DataRoomReqDto.UpdateDataRoomReq dto) {
        dto.setModiId("admin");
        dataRoomService.updateDataRoom(dataId, dto);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "자료실 삭제 API", description = "자료를 완전 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = NoticeResDto.NoticeResDtoResult.class)))
    }
    )
    @DeleteMapping(value = "/v1/dataroom/{dataId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDataRoom(@PathVariable String dataId) {
        dataRoomService.deleteDataRoom(dataId);

        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "자료실 SOFT 삭제 API", description = "공지사항을 SOFT 삭제한다.(del_yn)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = NoticeResDto.NoticeResDtoResult.class)))
    }
    )
    @PostMapping(value = "/v1/dataroom/delete/{dataId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteData(@Valid @RequestBody DataRoomReqDto.DeleteDataRoomReq dto) {
        dto.setModiId("admin");
        dataRoomService.deleteDataRoom2(dto);

        return ResponseEntity.ok(ApiResDto.success());
    }
}
