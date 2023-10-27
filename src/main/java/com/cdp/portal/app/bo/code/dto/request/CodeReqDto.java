package com.cdp.portal.app.bo.code.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "코드 관리 요청")
public class CodeReqDto {
    
    @Getter
    @Schema(description = "코드 그룹 등록")
    public static class CreateGroupCodeReq {
        
        @Schema(description = "코드그룹ID", example = "USE_YN", nullable = false)
        @NotBlank(message = "코드그룹ID는 필수 항목입니다.")
        private String groupId;
        
        @Schema(description = "코드그룹명", example = "사용 여부", nullable = false)
        @NotBlank(message = "코드그룹명은 필수 항목입니다.")
        private String groupNm;
        
        @Schema(description = "코드그룹비고", example = "", nullable = true)
        private String groupDsc;
        
        @Schema(description = "사용여부", example = "Y|N", nullable = false)
        @NotBlank(message = "사용여부는 필수 항목입니다.")
        private String groupUseYn;
        
    }
    
    @Getter
    @Schema(description = "코드 등록")
    public static class CreateCodeReq {
        
        @Schema(description = "코드ID", example = "Y")
        @NotBlank(message = "코드ID는 필수 항목입니다.")
        private String codeId;
        
        @Schema(description = "코드이름", example = "사용")
        @NotBlank(message = "코드이름은 필수 항목입니다.")
        private String codeNm;
        
        @Schema(description = "코드설명", example = "")
        private String codeDsc;
        
        @Schema(description = "정렬순서", example = "1")
        private Integer ordSeq;
        
        @Schema(description = "사용여부", example = "Y")
        @NotBlank(message = "사용여부는 필수 항목입니다.")
        private String useYn;

    }

}
