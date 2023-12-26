package com.cdp.portal.app.bo.oneid.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.language.DoubleMetaphone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.oneid.dto.common.BaseSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.common.GridData;
import com.cdp.portal.app.facade.oneid.dto.common.GridResponseVO;
import com.cdp.portal.app.facade.oneid.dto.common.Pagination;
import com.cdp.portal.app.facade.oneid.dto.response.MasterDTO;
import com.cdp.portal.app.facade.oneid.dto.response.MasterHistoryDTO;
import com.cdp.portal.app.facade.oneid.dto.response.MasterHistorySearchDTO;
import com.cdp.portal.app.facade.oneid.dto.response.MasterSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.response.PaxMappingDTO;
import com.cdp.portal.app.facade.oneid.dto.response.PaxMappingSearchDTO;
import com.cdp.portal.app.facade.oneid.service.OneIdMainService;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.constants.OneidConstants;
import com.cdp.portal.common.encryption.CryptoProvider;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.API_BO_PREFIX + "/oneid")
@Tag(name = "oneid-main")
public class BoOneIdMainController {

    private final OneIdMainService oneIdMainService;
    private final CryptoProvider cryptoProvider;
    private final DoubleMetaphone doubleMetaphone = new DoubleMetaphone();

    @Operation(summary = "OneID 마스터 조회", description = "OneID 마스터 조회")
    @GetMapping(value = "/v1/master")
    public ResponseEntity<GridResponseVO<GridData>> getMaster(
            @RequestParam int perPage,
            @RequestParam(defaultValue = "1", name = "page") int page,
            @ModelAttribute MasterSearchDTO inDTO) {//Test

        Pagination paging = Pagination.builder()
                .page(page)
                .perPage(perPage)
                .offset((page - 1) * perPage)
                .build();

        inDTO.setMobilePhoneNoInfoHashVlu(cryptoProvider.toHashEncryptedText(inDTO.getMobilePhoneNumberInfo()));
        inDTO.setHomePhoneNoInfoHashValue(cryptoProvider.toHashEncryptedText(inDTO.getHomePhoneNumberInfo()));
        inDTO.setOfficePhoneNoInfoHashVlu(cryptoProvider.toHashEncryptedText(inDTO.getOfficePhoneNumberInfo()));
        inDTO.setEmailAdrsHashValue(cryptoProvider.toHashEncryptedText(inDTO.getEmailAddress()));

        BaseSearchDTO<MasterSearchDTO> baseSearchDTO = BaseSearchDTO.<MasterSearchDTO>builder().paging(paging).search(inDTO).build();
        paging.setTotalCount(oneIdMainService.getCountMaster(baseSearchDTO));

        return new GridResponseVO().data(GridData.<MasterDTO>builder()
                .contents(oneIdMainService.getMaster(baseSearchDTO))
                .pagination(paging).build()).successResponse(OneidConstants.SUCCESS);
    }

    @Operation(summary = "OneID 마스터 조회", description = "OneID 마스터 조회")
    @GetMapping(value = "/v1/master-for-history")
    public ResponseEntity<GridResponseVO<GridData>> getMasterForHistory(
            @RequestParam int perPage,
            @RequestParam(defaultValue = "1", name = "page") int page,
            @ModelAttribute MasterSearchDTO inDTO) {

        Pagination paging = Pagination.builder()
                .page(page)
                .perPage(perPage)
                .offset((page - 1) * perPage)
                .build();

        inDTO.setMobilePhoneNoInfoHashVlu(cryptoProvider.toHashEncryptedText(inDTO.getMobilePhoneNumberInfo()));
        inDTO.setHomePhoneNoInfoHashValue(cryptoProvider.toHashEncryptedText(inDTO.getHomePhoneNumberInfo()));
        inDTO.setOfficePhoneNoInfoHashVlu(cryptoProvider.toHashEncryptedText(inDTO.getOfficePhoneNumberInfo()));
        inDTO.setEmailAdrsHashValue(cryptoProvider.toHashEncryptedText(inDTO.getEmailAddress()));

        BaseSearchDTO<MasterSearchDTO> baseSearchDTO = BaseSearchDTO.<MasterSearchDTO>builder().paging(paging).search(inDTO).build();
        paging.setTotalCount(oneIdMainService.getCountMasterForHistory(baseSearchDTO));

        return new GridResponseVO().data(GridData.<MasterDTO>builder()
                .contents(oneIdMainService.getMasterForHistory(baseSearchDTO))
                .pagination(paging).build()).successResponse(OneidConstants.SUCCESS);
    }

//    @Operation(summary = "Double Metaphone 변환값 조회", description = "Double Metaphone 변환값 조회")
//    @GetMapping(value = "/double-metaphone")
//    public ResponseEntity<ResponseVO> getDoubleMetaphone(
//            @RequestParam String bfConvertDoubleMetaphone) {
//        doubleMetaphone.setMaxCodeLen(12);
//        return new ResponseVO().data(doubleMetaphone.encode(bfConvertDoubleMetaphone)).successResponse(OneidConstants.SUCCESS);
//    }

    @Operation(summary = "OneID 마스터 History 조회", description = "OneID 마스터 History 조회")
    @GetMapping(value = "/v1/master-history")
    public ResponseEntity<GridResponseVO<GridData>> getMasterHistory(
            @RequestParam int perPage,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @ModelAttribute MasterHistorySearchDTO inDTO) {

        Pagination paging = Pagination.builder()
                .page(page)
                .perPage(perPage)
                .offset((page - 1) * perPage)
                .build();

        inDTO.setBfChgMblfonNoInfoHashVlu(cryptoProvider.toHashEncryptedText(inDTO.getBfChgMobilePhoneNoInfo()));
        inDTO.setBfChgEmailAdrsHashValue(cryptoProvider.toHashEncryptedText(inDTO.getBfChgEmailAdrs()));

        BaseSearchDTO<MasterHistorySearchDTO> baseSearchDTO = BaseSearchDTO.<MasterHistorySearchDTO>builder().paging(paging).search(inDTO).build();
        paging.setTotalCount(oneIdMainService.getCountMasterHistory(baseSearchDTO));

        return new GridResponseVO().data(GridData.<MasterHistoryDTO>builder()
                .contents(oneIdMainService.getMasterHistory(baseSearchDTO))
                .pagination(paging).build()).successResponse(OneidConstants.SUCCESS);
    }


//    @Operation(summary = "OneID-SKYPASS 매핑 조회", description = "OneID-SKYPASS 매핑 조회")
//    @GetMapping(value = "/skypass-mapping")
//    public ResponseEntity<GridResponseVO<GridData>> getSkypassMapping(
//            @RequestParam int perPage,
//            @RequestParam(defaultValue = "1", name = "page") int page,
//            @ModelAttribute SkypassMappingSearchDTO inDTO) {
//
//        Pagination paging = Pagination.builder()
//                .page(page)
//                .perPage(perPage)
//                .offset((page - 1) * perPage)
//                .build();
//
//        BaseSearchDTO<SkypassMappingSearchDTO> baseSearchDTO = BaseSearchDTO.<SkypassMappingSearchDTO>builder().paging(paging).search(inDTO).build();
//        paging.setTotalCount(oneIdMainService.getCountSkypassMapping(baseSearchDTO));
//
//        return new GridResponseVO().data(GridData.<SkypassMappingDTO>builder()
//                .contents(oneIdMainService.getSkypassMapping(baseSearchDTO))
//                .pagination(paging).build()).successResponse(OneidConstants.SUCCESS);
//    }


//    @Operation(summary = "OneID-SKYPASS 최신 정보 조회", description = "OneID-SKYPASS 최신 정보 조회")
//    @GetMapping(value = "/skypass-info")
//    public ResponseEntity<GridResponseVO<GridData>> getSkypassInfo(
//            @RequestParam int perPage,
//            @RequestParam(defaultValue = "1", name = "page") int page,
//            @ModelAttribute SkypassInfoSearchDTO inDTO) {
//
//        Pagination paging = Pagination.builder()
//                .page(page)
//                .perPage(perPage)
//                .offset((page - 1) * perPage)
//                .build();
//
//        inDTO.setMobilePhoneNoInfoHashVlu(cryptoProvider.toHashEncryptedText(inDTO.getMobilePhoneNumberInfo()));
//        inDTO.setEmailAdrsHashValue(cryptoProvider.toHashEncryptedText(inDTO.getEmailAddress()));
//
//        BaseSearchDTO<SkypassInfoSearchDTO> baseSearchDTO = BaseSearchDTO.<SkypassInfoSearchDTO>builder().paging(paging).search(inDTO).build();
//        paging.setTotalCount(oneIdMainService.getCountSkypassInfo(baseSearchDTO));
//
//        return new GridResponseVO().data(GridData.<SkypassInfoDTO>builder()
//                .contents(oneIdMainService.getSkypassInfo(baseSearchDTO))
//                .pagination(paging).build()).successResponse(OneidConstants.SUCCESS);
//    }

//    @Operation(summary = "OneID-SKYPASS 최신 정보 조회 엑셀 생성", description = "OneID-SKYPASS 최신 정보 조회 엑셀 생성")
//    @PostMapping(value = "/skypass-info-excel")
//    public void createSkypassInfoExcel(@RequestBody Map<String, Object> allParameters, HttpServletResponse response) {
//        allParameters.put("mobilePhoneNoInfoHashVlu", cryptoProvider.toHashEncryptedText((String) allParameters.get("mobilePhoneNumberInfo")));
//        allParameters.put("emailAdrsHashValue", cryptoProvider.toHashEncryptedText((String) allParameters.get("emailAddress")));
//        commonService.createExcelFile(allParameters, "OneID-SKYPASS 최신 정보_" + DateUtil.getDate() + ".xlsx", "com.ke.cdp.be.restapi.v1.oneid.mapper.OneIdMainMapper.createSkypassInfoExcel", response);
//    }

    @Operation(summary = "OneID-PAX 매핑 조회", description = "OneID-PAX 매핑 조회")
    @GetMapping(value = "/v1/pax-mapping")
    public ResponseEntity<GridResponseVO<GridData>> getPaxMapping(
            @RequestParam int perPage,
            @RequestParam(defaultValue = "1", name = "page") int page,
            @ModelAttribute PaxMappingSearchDTO inDTO) {

        Pagination paging = Pagination.builder()
                .page(page)
                .perPage(perPage)
                .offset((page - 1) * perPage)
                .build();

        BaseSearchDTO<PaxMappingSearchDTO> baseSearchDTO = BaseSearchDTO.<PaxMappingSearchDTO>builder().paging(paging).search(inDTO).build();
        paging.setTotalCount(oneIdMainService.getCountPaxMapping(baseSearchDTO));

        return new GridResponseVO().data(GridData.<PaxMappingDTO>builder()
                .contents(oneIdMainService.getPaxMapping(baseSearchDTO))
                .pagination(paging).build()).successResponse(OneidConstants.SUCCESS);
    }

//    @Operation(summary = "여권정보 조회", description = "여권정보 조회")
//    @GetMapping(value = "/pspt-info")
//    public ResponseEntity<GridResponseVO<GridData>> getPsptInfo(
//            @RequestParam int perPage,
//            @RequestParam(defaultValue = "1", name = "page") int page,
//            @ModelAttribute PsptInfoSearchDTO inDTO) {
//
//        Pagination paging = Pagination.builder()
//                .page(page)
//                .perPage(perPage)
//                .offset((page - 1) * perPage)
//                .build();
//
//        BaseSearchDTO<PsptInfoSearchDTO> baseSearchDTO = BaseSearchDTO.<PsptInfoSearchDTO>builder().paging(paging).search(inDTO).build();
//        paging.setTotalCount(oneIdMainService.getCountPsptInfo(baseSearchDTO));
//
//        return new GridResponseVO().data(GridData.<PsptInfoDTO>builder()
//                .contents(oneIdMainService.getPsptInfo(baseSearchDTO))
//                .pagination(paging).build()).successResponse(OneidConstants.SUCCESS);
//    }

}