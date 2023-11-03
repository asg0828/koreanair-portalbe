package com.cdp.portal.app.facade.dataroom.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class DataRoomReqDto {

    @Getter
    @Setter
    public static class CreateDataRoomReq {

        @Schema(description = "자료실 ID", example = "1", nullable = false)
        private String dataId;
        @Schema(description = "제목", example = "1", nullable = false)
        private String sj;
        @Schema(description = "내용", example = "1", nullable = false)
        private String cn;
        @Schema(description = "사용여부", example = "Y|N", nullable = false)
        private String useYn;
        @Schema(description = "수정자 ID", example = "admin", nullable = false)
        private String modiId;
        

    }

    @Getter
    @Setter
    public static class UpdateDataRoomReq {

        @Schema(description = "제목", example = "제목", nullable = false)
        @NotBlank(message = "제목은 필수 항목입니다.")
        private String sj;

        @Schema(description = "내용", example = "내용", nullable = false)
        @NotBlank(message = "내용은 필수 항목입니다.")
        private String cn;

        @Schema(description = "사용여부", example = "Y|N", nullable = false)
        @NotBlank(message = "사용여부는 필수 항목입니다.")
        private String useYn;


    }

    @Getter
    @Setter
    public static class DeleteDataRoomReq {
        @Schema(description = "자료실 ID", example = "", nullable = false)
        @NotBlank(message = "자료실 ID는 필수 항목입니다.")
        private String dataId;

        @Schema(description = "수정자 ID", example = "제목", nullable = false)
        private String modiId;
    }
}
