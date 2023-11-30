package com.cdp.portal.app.ext.feature.dto.response;

import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Schema(description = "피쳐 구분 응답")
public class ExtFeatureSeparateResDto {
    
    public static class ApiResExtFeatureSeparateResDtos extends ApiResDto<ExtFeatureSeparateResDto> {}
    
    @Schema(description = "구분ID", example = "CUSTOMER_SERVICE")
    private String seId;
    
    @Schema(description = "구분명", example = "고객서비스")
    private String seNm;
    
    @Schema(description = "구분설명", example = "고객서비스")
    private String seDsc;
    
    @Schema(description = "정렬순서", example = "1")
    private Integer ordSeq;

}
