package com.cdp.portal.app.facade.oneid.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SamePnrReportDTO {
    @Schema(description = "No")
    private String no;

    @Schema(description = "OneID Target oneIdNo")
    private String oneIdTargetOneId;

    @Schema(description = "OneID Target SkypassMemberNO")
    private String oneIdTargetSkypassNo;

    @Schema(description = "OneID Target Skypass eng fname")
    private String oneIdTargetEngFname;

    @Schema(description = "OneID Target Skypass eng Lname")
    private String oneIdTargetEngLname;

    @Schema(description = "OneID source Skypass")
    private String oneIdSourceSkypassNo;

    @Schema(description = "OneID source Eng Fname")
    private String oneIdSourceEngFname;

    @Schema(description = "OneID source Eng Lname")
    private String oneIdSourceEngLname;

    @Schema(description = "Source OneID PaxMapping OneID NO")
    private String sourcePaxMappingOneIdNo;

    @Schema(description = "Source OneID PaxMapping OneID Pnr NO")
    private String sourcePaxMappingPnrNo;

    @Schema(description = "Source OneID PaxMapping OneID Uci NO")
    private String sourcePaxMappingUciIdNo;

    @Schema(description = "OneID pax Eng LName")
    private String paxEngLastName;

    @Schema(description = "OneID pax Eng FName")
    private String paxEngFirstName;

    @Schema(description = "OneID pax birthDatev")
    private String birthDatev;

    @Schema(description = "OneID pax mobile")
    private String mobile;

    @Schema(description = "OneID pax email")
    private String email;

    @Schema(description = "OneID pax pnrFqtv")
    private String pnrFqtv;

    @Schema(description = "OneID pax boardYn")
    private String boardYn;
}
