package com.cdp.portal.app.facade.user.dto.response;

import java.util.List;

import com.cdp.portal.app.facade.user.model.UserMgmtAuthModel;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "사용자 관리 [관리자 권한] 응답")
public class UserMgmtMgrAuthResDto extends UserMgmtAuthModel {
	public static class ApiResUserMgmtMgrAuthResDtos extends ApiResDto<List<UserMgmtMgrAuthResDto>> {}
}
