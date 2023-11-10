package com.cdp.portal.app.facade.file.dto.response;

import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.common.dto.ApiResDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
@Schema(description = "파일 관리 응답")
public class FileResDto extends FileModel {
    public static class FileResDtoResult extends ApiResDto<com.cdp.portal.app.facade.file.dto.response.FileResDto.FilesResult> {}

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @Schema(description = "공지사항 목록 결과")
    public static class FilesResult {

        @Schema(description = "컨텐츠 정보", nullable = false)
        private List<com.cdp.portal.app.facade.file.dto.response.FileResDto> contents;
    }
}
