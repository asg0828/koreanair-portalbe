package com.cdp.portal.app.facade.session.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "사용자 HR 정보")
@NoArgsConstructor
public class UserHrInfoDto {
//	public static class ApiResHrInfos extends ApiResDto<UserHrInfoDto.HrInfoResult> {}

	@Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class HrInfo {

		@Schema(description = "재직/휴직 상태", example = "")
		private String Status;

		@Schema(description = "부서명 (한글)", example = "")
		private String Department_Korean;

		@Schema(description = "팀명 (영문)", example = "")
		private String Team_English;

		@Schema(description = "부서 코드", example = "")
		private String Department_Code;

		@Schema(description = "부서명 (영문)", example = "")
		private String Department_English;

		@Schema(description = "팀명 (한글)", example = "")
		private String Team_Korean;

		@Schema(description = "사번", example = "")
		private String EEID;

		@Schema(description = "직원 이름 (영문명)", example = "")
		private String Eng_Name;

		@Schema(description = "팀 코드", example = "")
		private String Team_Code;

		@Schema(description = "직원 이름 (한글)", example = "")
		private String Kor_Name;

	}

	@Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown=true)
	public static class HrInfoResult {

		@Schema(description = "HR 정보 결과", example = "")
		private List<HrInfo> Report_Entry = new ArrayList<>();

	}
}
