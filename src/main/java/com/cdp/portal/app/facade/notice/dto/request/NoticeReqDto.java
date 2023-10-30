package com.cdp.portal.app.facade.notice.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class NoticeReqDto {

    @Getter
    @Setter
    public static class CreateNoticeReq {

        @Schema(description = "공지사항 ID", example = "1", nullable = false)
        @NotBlank(message = "공지사항 ID는 필수 항목입니다.")
        private String noticeId;

        @Schema(description = "제목", example = "제목", nullable = false)
        @NotBlank(message = "코드그룹명은 필수 항목입니다.")
        private String sj;

        @Schema(description = "내용", example = "내용", nullable = true)
        private String cn;

        @NotBlank(message = "중요여부는 필수 항목입니다.")
        @Schema(description = "중요여부", example = "Y|N", nullable = false)
        private String importantYn;
    }
}
