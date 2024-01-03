package com.cdp.portal.app.fo.customer.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.customer.dto.response.CustomerResDto.ApiResMembers;
import com.cdp.portal.app.facade.customer.dto.response.CustomerResDto.ApiResProfile;
import com.cdp.portal.app.facade.customer.service.CustomerService;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_FO_PREFIX + "/customer-info")
@Tag(name = "customer", description = "고객360 API")
public class CustomerController {
    
    private final CustomerService customerService;
    
    @Operation(summary = "고객 프로필 조회", description = "고객 프로필을 조회한다.", tags = { "customer" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResProfile.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name ="searchType", required = true, description = "검색유형(A: OneID번호, B: Skypass회원번호)", example = "A")
    @Parameter(name ="oneidNo", required = false, description = "OneID번호(검색유형이 'A'일 경우 필수)", example = "S200003049125482")
    @Parameter(name ="skypassMemberNumber", required = false, description = "Skypass회원번호(검색유형이 'B'일 경우 필수)", example = "714516883816")
    @GetMapping(value = "/v1/customer/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomerProfile(
            @RequestParam(name = "searchType", required = true) String searchType,
            @RequestParam(name = "oneidNo", required = false) String oneidNo,
            @RequestParam(name = "skypassMemberNumber", required = false) String skypassMemberNumber) {
        
        return ResponseEntity.ok(ApiResDto.success(customerService.getCustomerProfile(searchType, oneidNo, skypassMemberNumber)));
    }
    
    @Operation(summary = "고객 멤버 목록 조회", description = "고객 멤버 목록을 조회한다.", tags = { "customer" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ApiResMembers.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ApiResDto.class)))
        }
    )
    @Parameter(name ="searchType", required = true, description = "검색유형(A: 한글명, B: 영문명, C: 모바일번호)", example = "A")
    @Parameter(name ="korFname", required = false, description = "한글FirstName(검색유형이 'A'일 경우 필수)", example = "")
    @Parameter(name ="korLname", required = false, description = "한글LastName(검색유형이 'A'일 경우 필수)", example = "")
    @Parameter(name ="engFname", required = false, description = "영문FirstName(검색유형이 'B'일 경우 필수)", example = "")
    @Parameter(name ="engLname", required = false, description = "영문LastName(검색유형이 'B'일 경우 필수)", example = "")
    @Parameter(name ="mobilePhoneNumber", required = false, description = "휴대전화번호(검색유형이 'C'일 경우 필수)", example = "")
    @GetMapping(value = "/v1/customer/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomerMembers(
            @RequestParam(name = "searchType", required = true) String searchType,
            @RequestParam(name = "korFname", required = false) String korFname,
            @RequestParam(name = "korLname", required = false) String korLname,
            @RequestParam(name = "engFname", required = false) String engFname,
            @RequestParam(name = "engLname", required = false) String engLname,
            @RequestParam(name = "mobilePhoneNumber", required = false) String mobilePhoneNumber) {
        
        return ResponseEntity.ok(ApiResDto.success(customerService.getCustomerMembers(searchType, korFname, korLname, engFname, engLname, mobilePhoneNumber)));
    }

}
