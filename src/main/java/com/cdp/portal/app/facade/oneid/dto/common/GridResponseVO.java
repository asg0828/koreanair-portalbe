package com.cdp.portal.app.facade.oneid.dto.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.cdp.portal.common.constants.OneidConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Schema(description = "응답 VO")
@Getter
@NoArgsConstructor
public class GridResponseVO<T> {
	@Schema(description = "응답 결과 / 유-success, 무-failure", required = true)
	private String result;
	@Schema(description = "응답 코드", required = true)
	private int code;

	@Schema(description = "응답 메시지", required = true)
	private String message;

	@Schema(description = "응답 데이터 / result=failure -> data 필드 X")
	private GridData data;

	public GridResponseVO<T> data(GridData data) {
		this.data = data;
		return this;
	}

	public ResponseEntity<GridResponseVO<T>> successResponse(OneidConstants response) {
		return this.successResponse(response, (HttpHeaders)null);
	}

	public ResponseEntity<GridResponseVO<T>> successResponse(OneidConstants response, HttpHeaders headers) {
		this.result = response.getResult();
		this.code = response.getCode();
		this.message = response.getMessage();
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.headers(headers)
				.body(this);
	}
}
