package com.cdp.portal.app.facade.dataroom.dto.response;

import java.util.List;

import com.cdp.portal.app.facade.dataroom.dto.request.DataRoomReqDto;
import com.cdp.portal.app.facade.dataroom.model.DataRoomModel;
import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.common.dto.ApiResDto;

import com.cdp.portal.common.dto.PagingDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Schema(description = "자료실 관리 응답")
public class DataRoomResDto extends DataRoomModel {
    public static class DataRoomResDtoResult extends ApiResDto<List<DataRoomResDto>> {}

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @Schema(description = "자료실 목록 결과")
    public static class DataRoomsResult {

        @Schema(description = "컨텐츠 정보", nullable = false)
        private List<DataRoomResDto> contents;
        @Schema(description = "검색 정보", nullable = false)
        private DataRoomReqDto.SearchDataRoom search;
        @Schema(description = "페이지 정보", nullable = false)
        private PagingDto page;
        @Schema(description = "파일 목록", nullable = false)
        private List<FileModel> fileList;

    }
    @Schema(description = "파일 목록", nullable = false)
    private List<FileModel> fileList;
    public void setFileList(List<FileModel> fileList) {
        this.fileList = fileList;
    }
    public List<FileModel> getFileList() {
        return this.fileList;
    }
}
