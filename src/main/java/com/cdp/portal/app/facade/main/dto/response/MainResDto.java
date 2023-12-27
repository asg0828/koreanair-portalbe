package com.cdp.portal.app.facade.main.dto.response;

import java.util.List;

import com.cdp.portal.app.facade.main.model.MainModel;
import com.cdp.portal.app.facade.notice.dto.response.NoticeResDto;
import com.cdp.portal.common.dto.ApiResDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Schema(description = "메인 응답")
public class MainResDto extends MainModel {

    public static class MainResDtoResult extends ApiResDto<MainResDto.MainMyDeptResult> {}

    @Getter
    @Setter
    @ToString
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "메인 CDP totalCount")

    public static class MainCdpResult {
        @Schema(description = "카운트", nullable = false)
        private int featureCount;
        private int tableSpecCount;
        private int avgUserCount;
    }

    @Getter
    @Setter
    @ToString
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "메인 내 부서 totalCount")

    public static class MainMyDeptResult {
        @Schema(description = "카운트", nullable = false)
        private int featureDeptCount;
        private int featureInterestDeptCount;
    }
}
