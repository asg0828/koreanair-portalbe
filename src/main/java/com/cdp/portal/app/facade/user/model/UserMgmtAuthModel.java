package com.cdp.portal.app.facade.user.model;

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
@Schema(description = "사용자(or관리자) 권한 모델")
public class UserMgmtAuthModel {
	
	@Schema(description = "권한 ID", nullable = false)
	private String authId;

	@Schema(description = "권한 분류", example = "")
	private String authCl;

	@Schema(description = "권한 명", example = "")
	private String authNm;

	@Schema(description = "권한 설명", example = "")
	private String authDsc;

//	@Schema(description = "사용 여부", example = "")
//	private String useYn;

//	@Schema(description = "등록 ID", example = "")
//	private String rgstId;
//
//	@Schema(description = "등록일시", example = "2021-04-13 09:04:40")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	private LocalDateTime rgstDt;
//
//	@Schema(description = "수정 ID", example = "")
//	private String modiId;
//
//	@Schema(description = "수정 일시", example = "2021-04-13 09:04:40")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	private LocalDateTime modiDt;
}
