package com.cdp.portal.app.bo.oneid.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.oneid.dto.common.ResponseVO;
import com.cdp.portal.app.facade.oneid.dto.response.CleansingHashResultsDTO;
import com.cdp.portal.common.cleansing.CleansingRule;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.constants.OneidConstants;
import com.cdp.portal.common.encryption.CryptoProvider;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/oneid")
@Tag(name = "oneid-converter")
public class BoOneIdConverterController {

    private final CryptoProvider cryptoProvider;
    private final CleansingRule cleansingRule;

    @Operation(summary = "CleansingRule / Hash 변환 결과 조회", description = "CleansingRule / Hash 변환 결과 조회")
    @GetMapping(value = "/v1/cleansing-hash-results")
    public ResponseEntity<ResponseVO> getCleansingHashResults(
            @RequestParam String inptPhone,
            @RequestParam String inptEmail) {

        return new ResponseVO().data(CleansingHashResultsDTO.builder()
                .phoneCleansingResult(cleansingRule.cleanseMobile(inptPhone))
                .emailCleansingResult(cleansingRule.cleanseEmail(inptEmail))
                .phoneHashValue(cryptoProvider.toHashEncryptedText(inptPhone))
                .emailHashValue(cryptoProvider.toHashEncryptedText(inptEmail)).build()).successResponse(OneidConstants.SUCCESS);
    }
}