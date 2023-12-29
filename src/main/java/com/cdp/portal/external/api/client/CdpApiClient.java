package com.cdp.portal.external.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdp.portal.external.api.config.CdpApiConfig;
import com.cdp.portal.external.api.dto.response.CdpApiResDto;
import com.cdp.portal.external.api.dto.response.CustomerProfileResDto;

@FeignClient(name = "cdpApiClient", url = "${external.cdp-api.url}", configuration = CdpApiConfig.class)
public interface CdpApiClient {
    
    /**
     * 고객 프로필 조회
     * @param searchType
     * @param oneidNo
     * @param skypassMemberNumber
     * @return
     */
    @GetMapping(value = "/v1/customer/profile")
    CdpApiResDto<CustomerProfileResDto> getCustomerProfile(
            @RequestParam(name = "searchType", required = true) String searchType,
            @RequestParam(name = "oneidNo", required = false) String oneidNo,
            @RequestParam(name = "skypassMemberNumber", required = false) String skypassMemberNumber);

}
