package com.cdp.portal.external.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CdpApiResDto<T> {
    
    @Schema(description = "상태")
    private String status;
    
    @Schema(description = "데이터")
    private T data;
    
    @Schema(description = "메시지")
    private String message;
    
}
