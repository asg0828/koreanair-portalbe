package com.cdp.portal.app.facade.oneid.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgtContactDTO {
    @Schema(description = "No")
    private Integer no;

    @Schema(description = "대리점추정휴대전화번호정보")
    private String agtEstimatedMblfonNoInfo;

    @Schema(description = "대리점추정휴대전화번호정보Hash값")
    private String agtEstMblfonNoInfoHshVlu;

    @Schema(description = "사용여부")
    private String useYn;

    @Schema(description = "미사용전환일자")
    private String disuseConvsDt;

    @Schema(description = "최초생성일시")
    private String creationDate;

    @Schema(description = "최종갱신일시")
    private String lastUpdateDate;
}

