package com.cdp.portal.app.facade.code.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "코드 관리 요청")
public class CodeReqDto {
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "코드 그룹 등록")
    public static class CreateGroupCodeReq {
        
        @Schema(description = "코드그룹ID", example = "USE_YN", nullable = false)
        @NotBlank
        private String groupId;
        
        @Schema(description = "코드그룹명", example = "사용 여부", nullable = false)
        @NotBlank
        private String groupNm;
        
        @Schema(description = "코드그룹비고", example = "", nullable = true)
        private String groupDsc;
        
        @Schema(description = "사용여부", example = "Y|N", nullable = false)
        @NotBlank
        private String groupUseYn;
        
    }
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "코드 등록")
    public static class CreateCodeReq {
        
        @Schema(description = "코드ID", example = "Y")
        @NotBlank
        private String codeId;
        
        @Schema(description = "코드이름", example = "사용")
        @NotBlank
        private String codeNm;
        
        @Schema(description = "코드설명", example = "")
        private String codeDsc;
        
        @Schema(description = "정렬순서", example = "1")
        private Integer ordSeq;
        
        @Schema(description = "사용여부", example = "Y")
        @NotBlank
        private String useYn;

    }

}
