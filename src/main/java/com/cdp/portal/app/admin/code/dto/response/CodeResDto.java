package com.cdp.portal.app.admin.code.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.cdp.portal.common.dto.ApiResDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "코드 관리 응답")
public class CodeResDto {
    
    public static class CodeResDtoResult extends ApiResDto<List<CodeResDto>> {}
    
    @Schema(description = "rownum", example = "1")
    private String rownum;
    
    @Schema(description = "코드그룹ID", example = "GROUP_ID")
    private String groupId;
    
    @Schema(description = "코드ID", example = "APPROVE_YN")
    private String codeId;
    
    @Schema(description = "코드이름ID", example = "승인 여부")
    private String codeNm;
    
    @Schema(description = "코드설명", example = "")
    private String codeDsc;
    
    @Schema(description = "사용여부", example = "Y")
    private String useYn;
    
    @Schema(description = "등록자ID", example = "SYSTEM")
    private String rgstId;
    
    @Schema(description = "등록일시", example = "2021-04-13 09:04:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    private LocalDateTime rgstDt;
    
    @Schema(description = "수정자ID", example = "admin")
    private String modiId;
    
    @Schema(description = "수정일시", example = "2021-04-13 09:04:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    private LocalDateTime modiDt;

}
