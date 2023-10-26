package com.cdp.portal.app.bo.code.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class CodeReqDto {
    
    @Getter
    @Setter
    public static class CreateCodeReq {
        
        @NotBlank(message = "코드그룹ID는 필수 항목입니다.")
        @Schema(description = "코드그룹ID", example = "USE_YN", nullable = false)
        private String groupCodeId;
        
        @Schema(description = "코드그룹명", example = "사용 여부", nullable = false)
        @NotBlank(message = "코드그룹명은 필수 항목입니다.")
        private String groupCodeNm;
        
        @Schema(description = "코드그룹비고", example = "", nullable = true)
        private String groupCodeDsc;
        
        @NotBlank(message = "사용여부는 필수 항목입니다.")
        @Schema(description = "사용여부", example = "Y|N", nullable = false)
        private String groupCodeUseYn;
        
    }

}
