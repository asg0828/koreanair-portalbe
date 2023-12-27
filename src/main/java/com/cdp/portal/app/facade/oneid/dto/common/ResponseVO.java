package com.cdp.portal.app.facade.oneid.dto.common;

import com.cdp.portal.common.constants.OneidConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Schema(title = "응답 VO")
@Getter
@NoArgsConstructor
public class ResponseVO<T> {

    @Schema(title = "응답 결과", description = "success : 정상 가동, failure : 비정상 가동", required = true)
    private String result;
    @Schema(title = "응답 코드", required = true)
    private int code;
    @Schema(title = "응답 메시지", required = true)
    private String message;
    @Schema(title = "응답 데이터", description = "응답 코드에 따라, 응답 data 필드가 없을 수 있습니다.")
    private T data;
    private String error;

    public ResponseVO<T> data(T data) {
        this.data = data;
        return this;
    }

    public ResponseEntity<ResponseVO<T>> successResponse(OneidConstants response) {
        return this.successResponse(response, null);
    }

    public ResponseEntity<ResponseVO<T>> successResponse(OneidConstants response, HttpHeaders headers) {
        this.result = response.getResult();
        this.code = response.getCode();
        this.message = response.getMessage();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .body(this);
    }

    public ResponseEntity<ResponseVO<T>> failResponse(OneidConstants response, String error) {
        this.result = response.getResult();
        this.code = response.getCode();
        this.message = response.getMessage();
        this.data = null;
        this.error = error;
        return ResponseEntity.internalServerError()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this);
    }
}