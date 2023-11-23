package com.cdp.portal.app.facade.notice.dto.response;

import java.util.List;

import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.app.facade.notice.dto.request.NoticeReqDto;
import com.cdp.portal.app.facade.notice.model.NoticeModel;
import com.cdp.portal.common.dto.ApiResDto;

import com.cdp.portal.common.dto.PagingDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "공지사항 관리 응답")
public class NoticeResDto extends NoticeModel {

    public static class NoticeResDtoResult extends ApiResDto<NoticeResDto.NoticesResult> {}


    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @Schema(description = "공지사항 목록 결과")
    public static class NoticesResult {

        @Schema(description = "컨텐츠 정보", nullable = false)
        private List<NoticeResDto> contents;
        @Schema(description = "검색 정보", nullable = false)
        private NoticeReqDto.SearchNotice search;
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
