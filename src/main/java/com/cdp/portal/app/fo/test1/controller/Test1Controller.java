package com.cdp.portal.app.fo.test1.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/fo/test1")
@Tag(name = "test1", description = "테스트1 API")
public class Test1Controller {
    
    @Operation(summary = "test1 API", description = "test1 조회한다.", tags = { "test1" })
    @GetMapping(value = "/datas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDatas() {
        return ResponseEntity.ok(ApiResDto.success());
    }

}
