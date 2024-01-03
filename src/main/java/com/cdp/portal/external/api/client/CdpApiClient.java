package com.cdp.portal.external.api.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdp.portal.external.api.config.CdpApiConfig;
import com.cdp.portal.external.api.dto.response.CdpApiResDto;
import com.cdp.portal.external.api.dto.response.CustomerMemberResDto;
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
    @GetMapping(value = "/v1/customer/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    CdpApiResDto<CustomerProfileResDto> getCustomerProfile(
            @RequestParam(name = "searchType", required = true) String searchType,
            @RequestParam(name = "oneidNo", required = false) String oneidNo,
            @RequestParam(name = "skypassMemberNumber", required = false) String skypassMemberNumber);
    
    /**
     * 고객 멤버 목록 조회
     * @param searchType
     * @param korFname
     * @param korLname
     * @param engFname
     * @param engLname
     * @param mobilePhoneNumber
     * @return
     */
    @GetMapping(value = "/v1/customer/members", produces = MediaType.APPLICATION_JSON_VALUE)
    CdpApiResDto<List<CustomerMemberResDto>> getCustomerMembers(
            @RequestParam(name = "searchType", required = true) String searchType,
            @RequestParam(name = "korFname", required = false) String korFname,
            @RequestParam(name = "korLname", required = false) String korLname,
            @RequestParam(name = "engFname", required = false) String engFname,
            @RequestParam(name = "engLname", required = false) String engLname,
            @RequestParam(name = "mobilePhoneNumber", required = false) String mobilePhoneNumber);

}
