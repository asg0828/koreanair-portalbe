package com.cdp.portal.app.fo.test2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fo/test2")
@Tag(name = "test2", description = "테스트2 API")
public class Test2Controller {
    
    @Operation(summary = "test2 조회 API", description = "test2 조회한다.", tags = { "test2" })
    @GetMapping(value = "/datas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDatas() {
        return ResponseEntity.ok(ApiResDto.success());
    }
    
    @Operation(summary = "test2 등록 API", description = "test2 등록 한다.", tags = { "test2" })
    @PostMapping(value = "/datas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDatas() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
