package com.cdp.portal.app.fo.file.controller;

import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.app.facade.file.service.FileService;
import com.cdp.portal.common.aws.AwsS3Util;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.dto.ApiResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_FO_PREFIX + "/file")
@Tag(name = "file", description = "파일 관리 API")
public class FoFileRestController {
    private final FileService fileService;

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
}
