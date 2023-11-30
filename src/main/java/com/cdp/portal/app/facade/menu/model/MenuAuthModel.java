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
@Schema(description = "메뉴권한 모델")
public class MenuAuthModel {
	@Schema(description = "권한 ID", example = "", nullable = false)
	private String authId;

	@Schema(description = "메뉴 ID", example = "", nullable = false)
	private String menuId;

	@Schema(description = "메뉴속성", example = "")
	private String menuAttr;

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
