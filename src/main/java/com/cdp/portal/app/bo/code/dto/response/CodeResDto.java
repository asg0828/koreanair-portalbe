package com.cdp.portal.app.bo.code.dto.response;

import java.util.List;

import com.cdp.portal.app.bo.code.model.CodeModel;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "코드 관리 응답")
public class CodeResDto extends CodeModel {
    
    public static class ApiResCodeResDtos extends ApiResDto<List<CodeResDto>> {}
    
    public static class ApiResCodeResDto extends ApiResDto<CodeResDto> {}

}
