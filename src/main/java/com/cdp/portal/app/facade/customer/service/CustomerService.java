package com.cdp.portal.app.facade.customer.service;

import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Service;

import com.cdp.portal.app.facade.customer.dto.response.CustomerResDto;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.external.api.client.CdpApiClient;
import com.cdp.portal.external.api.dto.response.CdpApiResDto;
import com.cdp.portal.external.api.dto.response.CustomerProfileResDto;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    
    private final CdpApiClient cdpApiClient;
    
    public CustomerResDto.Profile getCustomerProfile(String searchType, String oneidNo, String skypassMemberNumber) {
        CustomerProfileResDto dto = null;
        
        try {
            CdpApiResDto<CustomerProfileResDto> customerProfileResDto = cdpApiClient.getCustomerProfile(searchType, oneidNo, skypassMemberNumber);
            if (Objects.isNull(customerProfileResDto) || Objects.isNull(customerProfileResDto.getData()) || !StringUtils.equals(customerProfileResDto.getStatus(), "success")) {
                throw CdpPortalError.CUSTOMER_PROFILE_NOT_FOUND.exception();
            }
            
            dto = customerProfileResDto.getData();
        } catch (FeignException feignException) {
            log.error("getCustomerProfile error: {}", feignException.getMessage(), feignException);
            throw CdpPortalError.CUSTOMER_PROFILE_NOT_FOUND.exception();
        }
        
        return CustomerResDto.Profile.builder()
                .oneidNo(dto.getOneidNo())
                .korFname(dto.getKorFname())
                .korLname(dto.getKorLname())
                .engFname(dto.getEngFname())
                .engLname(dto.getEngLname())
                .birthDatev(dto.getBirthDatev())
                .age(dto.getAge())
                .sexCode(dto.getSexCode())
                .mobilePhoneNumber(dto.getMobilePhoneNumber())
                .emailAddress(dto.getEmailAddress())
                .skypassInfos(dto.getSkypassInfos().stream()
                        .map(m -> {
                            return CustomerResDto.SkypassInfo.builder()
                                    .oneidNo(m.getOneidNo())
                                    .skypassMemberNumber(m.getSkypassMemberNumber())
                                    .build();
                        })
                        .collect(Collectors.toList()))
                .build();
    }

}
