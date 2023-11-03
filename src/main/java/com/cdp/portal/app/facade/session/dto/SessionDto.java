package com.cdp.portal.app.facade.session.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "Session")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class SessionDto {

	@Schema(description = "세션ID", example = "105273373997504583166:4300dd41-5a65-42eb-a856-ee48358c430f", nullable = false)
	private String sessionId;
	
	@Schema(description = "사번", example = "PJ.UHLEE", nullable = false)
	private String employeeNumber;
	
	@Schema(description = "이메일", example = "pj.uhlee@kalmate.net", nullable = false)
	private String email;
	
	@Schema(description = "이름", example = "홍길동", nullable = false)
	private String memberName;
	
	@Schema(description = "권한ID", example = "xxx", nullable = true, hidden = true)
	private String roleId;
	
	@Schema(description = "권한명", example = "xxx", nullable = true, hidden = true)
	private String roleName;
//	private List<ParentMenuDto> menus;
	
	@Schema(description = "googleAccessToken", example = "xxx", nullable = true, hidden = true)
	@Setter
	private String googleAccessToken;
	
	@Schema(description = "googleIdToken", example = "xxx", nullable = true, hidden = true)
	@Setter
	private String googleIdToken;

	@Builder
	public SessionDto(String sessionId, String email, String memberName, String employeeNumber, String roleId, String roleName,
//			List<ParentMenuDto> menus,
			String googleAccessToken, String googleIdToken) {
		this.sessionId = sessionId;
		this.email = email;
		this.memberName = memberName;
		this.employeeNumber = employeeNumber;
		this.roleId = roleId;
		this.roleName = roleName;
//		this.menus = menus;
		this.googleAccessToken = googleAccessToken;
		this.googleIdToken = googleIdToken;
	}
}