package com.cdp.portal.app.facade.log.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Schema(description = "관리자 로그")
public class LogMgrModel {
    
    @Schema(description = "로그일시", nullable = false)
    private LocalDateTime logDt;
    
    @Schema(description = "사용자 ID")
    private String userId;
    
    @Schema(description = "클라이언트 IP")
    private String clientIp;
    
    @Schema(description = "요청 URI")
    private String rqstUri;
    
    @Schema(description = "요청 메소드")
    private String rqstMethod;
    
    @Schema(description = "요청 쿼리")
    private String rqstQuery;
    
    @Schema(description = "요청 바디")
    private String rqstBody;

    @Schema(description = "응답바디")
    private String rsptBody;

}
