package com.cdp.portal.app.bo.file.controller;

import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.app.facade.file.service.FileService;
import com.cdp.portal.common.aws.AwsS3Util;
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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/file")
@Tag(name = "file", description = "파일 관리 API")
public class FileRestController {

    private final FileService fileService;

    private final AwsS3Util s3Util;

    @Operation(summary = "파일 업로드", description = "파일을 업로드합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
    }
    )
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResDto<?>> uploadFiles(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam String fileCl
    ) throws IOException {
        List<String> uploadedFileIds = fileService.insertFile(files, fileCl); // 업로드한 파일의 ID 목록을 가져옴
        return ResponseEntity.ok(ApiResDto.success(uploadedFileIds)); // 파일 ID 목록을 응답으로 반환
    }

    @Operation(summary = "파일 다운로드", description = "파일을 다운로드합니다.")
    @GetMapping(value = "/download/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId) {
        ByteArrayResource resource = fileService.downloadFile(fileId).getBody();
        FileModel file = fileService.selectFile(fileId);
        String fileNm = file.getFileNm();
        HttpHeaders headers = fileService.getHeaders(fileNm);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @Operation(summary = "파일 삭제", description = "파일을 삭제합니다.")
    @PostMapping(value = "/delete/{fileId}")
    public ResponseEntity<ApiResDto<List<String>>> deleteFile(@PathVariable String fileId) {
        List<String> deletedFileIds = fileService.deleteFile(fileId);
        return ResponseEntity.ok(ApiResDto.success(deletedFileIds));
    }
}