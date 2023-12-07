package com.cdp.portal.app.facade.report.dto.response;

import com.cdp.portal.app.facade.report.model.ReportModel;
import com.cdp.portal.common.dto.ApiResDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(description = "정형 보고서 관리 응답")
public class ReportResDto extends ReportModel {

    public static class ReportResDtoResult extends ApiResDto<ReportResDto.ReportsResult> {}

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @Schema(description = "공지사항 목록 결과")
    public static class ReportsResult {
        @Schema(description = "컨텐츠 정보", nullable = false)
        private List<ReportResDto> contents;
    }
}
