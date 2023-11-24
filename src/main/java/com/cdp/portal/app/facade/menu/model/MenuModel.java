package com.cdp.portal.app.facade.menu.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Schema(description = "메뉴 모델")
public class MenuModel {
	@Schema(description = "메뉴 ID", example = "", nullable = false)
	private String menuId;

	@Schema(description = "상위 메뉴 ID", example = "")
	private String upMenuId;

	@Schema(description = "메뉴 명", example = "", nullable = false)
	private String menuNm;

	@Schema(description = "메뉴 URL", example = "")
	private String menuUrl;

	@Schema(description = "메뉴 설명", example = "")
	private String menuDsc;

	@Schema(description = "정렬 순서", example = "")
	private int ordSeq;

	@Schema(description = "메뉴 구분[CODE GROUP_ID: MENU_SE]", example = "")
	private String menuSe;

	@Schema(description = "메뉴 속성", example = "")
	private String menuAttr;

	@Schema(description = "사용 여부", example = "")
	private String useYn;

	@Schema(description = "삭제여부", example = "")
	private String delYn;

	@Schema(description = "메뉴아이콘", example = "")
	private String menuIcon;

	@Schema(description = "등록자ID", example = "admin")
    private String rgstId;

    @Schema(description = "등록일시", example = "2021-04-13 09:04:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDt;

    @Schema(description = "수정자ID", example = "admin")
    private String modiId;

    @Schema(description = "수정일시", example = "2021-04-13 09:04:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modiDt;

}
