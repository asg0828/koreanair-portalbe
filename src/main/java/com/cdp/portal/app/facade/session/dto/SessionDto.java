package com.cdp.portal.app.facade.session.dto;

import java.util.List;

import com.cdp.portal.app.facade.menu.dto.response.MenuMgmtResDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Schema(description = "Session")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class SessionDto {

	@Schema(description = "세션ID", example = "105273373997504583166:4300dd41-5a65-42eb-a856-ee48358c430f", nullable = false)
	private String sessionId;

	@Schema(description = "사용자 ID(사번)", example = "PJ.UHLEE", nullable = false)
	private String userId;

	@Schema(description = "사용자 명", example = "홍길동", nullable = false)
	private String userNm;

	@Schema(description = "사용자 이메일", example = "pj.uhlee@kalmate.net", nullable = false)
	private String userEmail;

    @Schema(description = "부서(팀)코드", example = "")
    private String deptCode;

    @Schema(description = "부서(팀)명", example = "")
    private String deptNm;

    @Schema(description = "상위 부서코드", example = "")
    private String upDeptCode;

    @Schema(description = "상위 부서명", example = "")
    private String upDeptNm;

    @Schema(description = "적용 사용자권한 ID - 최종 적용된 부서(팀)/예외그룹 중 예외그룹 우선 권한", example = "")
    private String apldUserAuthId;

    @Schema(description = "적용 사용자권한명 - 최종 적용된 부서(팀)/예외그룹 중 예외그룹 우선 권한", example = "")
    private String apldUserAuthNm;

    @Schema(description = "적용 관리자권한 ID - 최종 적용된 부서(팀)/예외그룹 중 예외그룹 우선 권한", example = "")
    private String apldMgrAuthId;

    @Schema(description = "적용 관리자권한명 - 최종 적용된 부서(팀)/예외그룹 중 예외그룹 우선 권한", example = "")
    private String apldMgrAuthNm;

    @Schema(description = "접근 가능 관리자 메뉴 목록", example = "")
    private MenuMgmtResDto.MenuByAuth menuByAuthMgr;

    @Schema(description = "접근 가능 사용자 메뉴 목록", example = "")
    private MenuMgmtResDto.MenuByAuth menuByAuthUser;

//    @Schema(description = "사용자 퀵 메뉴 목록", example = "")
//    private MenuMgmtResDto.QuickMenuByUser quickMenuUser;
//
//    @Schema(description = "관리자 퀵 메뉴 목록", example = "")
//    private MenuMgmtResDto.QuickMenuByUser quickMenuMgr;

	@Schema(description = "googleAccessToken", example = "", hidden = true)
	@Setter
	private String googleAccessToken;

	@Schema(description = "googleIdToken", example = "", hidden = true)
	@Setter
	private String googleIdToken;

}