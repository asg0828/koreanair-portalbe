package com.cdp.portal.app.bo.file.controller;

import com.cdp.portal.app.facade.file.dto.request.FileReqDto;
import com.cdp.portal.app.facade.file.service.FileService;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.dto.ApiResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/file")
@Tag(name = "file", description = "파일 관리 API")
public class FileRestController {

    private final FileService fileService;

    @Operation(summary = "파일 업로드", description = "파일을 업로드합니다.")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResDto<?>> uploadFile(FileReqDto.InsertFileReq file) {
        fileService.insertFile(file);
        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "파일 다운로드", description = "파일을 다운로드합니다.")
    @GetMapping(value = "/download/{fileId}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileId) {
//        fileService.downloadFile(fileId);
        return ResponseEntity.ok(ApiResDto.success());
    }

    @Operation(summary = "파일 삭제", description = "파일을 삭제합니다.")
    @DeleteMapping(value = "/delete/{fileId}")
    public ResponseEntity<ApiResDto<?>> deleteFile(@PathVariable String fileId) {
//        fileService.deleteFile(fileId);

        return ResponseEntity.ok(ApiResDto.success());
    }
    // 기타 필요한 파일 관련 API 엔드포인트 추가 가능
}
