package com.cdp.portal.app.bo.oneid.controller;

import org.apache.commons.codec.language.DoubleMetaphone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdp.portal.app.facade.oneid.dto.common.BaseSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.common.GridData;
import com.cdp.portal.app.facade.oneid.dto.common.GridResponseVO;
import com.cdp.portal.app.facade.oneid.dto.common.Pagination;
import com.cdp.portal.app.facade.oneid.dto.response.AgtContactDTO;
import com.cdp.portal.app.facade.oneid.dto.response.AgtContactSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.response.MasterDTO;
import com.cdp.portal.app.facade.oneid.dto.response.MasterHistoryDTO;
import com.cdp.portal.app.facade.oneid.dto.response.MasterHistorySearchDTO;
import com.cdp.portal.app.facade.oneid.dto.response.MasterSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.response.MergeHistoryDTO;
import com.cdp.portal.app.facade.oneid.dto.response.MergeHistorySearchDTO;
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
            @RequestParam int perSize,
            @RequestParam(defaultValue = "1", name = "page") int page,
            @ModelAttribute MasterSearchDTO inDTO) {//Test

        Pagination paging = Pagination.builder()
                .page(page)
                .perSize(perSize)
                .offset((page - 1) * perSize)
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
            @RequestParam int perSize,
            @RequestParam(defaultValue = "1", name = "page") int page,
            @ModelAttribute MasterSearchDTO inDTO) {

        Pagination paging = Pagination.builder()
                .page(page)
                .perSize(perSize)
                .offset((page - 1) * perSize)
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

    @Operation(summary = "OneID 마스터 History 조회", description = "OneID 마스터 History 조회")
    @GetMapping(value = "/v1/master-history")
    public ResponseEntity<GridResponseVO<GridData>> getMasterHistory(
            @RequestParam int perSize,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @ModelAttribute MasterHistorySearchDTO inDTO) {

        Pagination paging = Pagination.builder()
                .page(page)
                .perSize(perSize)
                .offset((page - 1) * perSize)
                .build();

        inDTO.setBfChgMblfonNoInfoHashVlu(cryptoProvider.toHashEncryptedText(inDTO.getBfChgMobilePhoneNoInfo()));
        inDTO.setBfChgEmailAdrsHashValue(cryptoProvider.toHashEncryptedText(inDTO.getBfChgEmailAdrs()));

        BaseSearchDTO<MasterHistorySearchDTO> baseSearchDTO = BaseSearchDTO.<MasterHistorySearchDTO>builder().paging(paging).search(inDTO).build();
        paging.setTotalCount(oneIdMainService.getCountMasterHistory(baseSearchDTO));

        return new GridResponseVO().data(GridData.<MasterHistoryDTO>builder()
                .contents(oneIdMainService.getMasterHistory(baseSearchDTO))
                .pagination(paging).build()).successResponse(OneidConstants.SUCCESS);
    }

    @Operation(summary = "OneID-PAX 매핑 조회", description = "OneID-PAX 매핑 조회")
    @GetMapping(value = "/v1/pax-mapping")
    public ResponseEntity<GridResponseVO<GridData>> getPaxMapping(
            @RequestParam int perSize,
            @RequestParam(defaultValue = "1", name = "page") int page,
            @ModelAttribute PaxMappingSearchDTO inDTO) {

        Pagination paging = Pagination.builder()
                .page(page)
                .perSize(perSize)
                .offset((page - 1) * perSize)
                .build();

        BaseSearchDTO<PaxMappingSearchDTO> baseSearchDTO = BaseSearchDTO.<PaxMappingSearchDTO>builder().paging(paging).search(inDTO).build();
        paging.setTotalCount(oneIdMainService.getCountPaxMapping(baseSearchDTO));

        return new GridResponseVO().data(GridData.<PaxMappingDTO>builder()
                .contents(oneIdMainService.getPaxMapping(baseSearchDTO))
                .pagination(paging).build()).successResponse(OneidConstants.SUCCESS);
    }

    @Operation(summary = "대리점 추정 모바일 번호 조회", description = "대리점 추정 모바일 번호 조회")
    @GetMapping(value = "/v1/agt-contact")
    public ResponseEntity<GridResponseVO<GridData>> getAgtContact(
            @RequestParam int perSize,
            @RequestParam(defaultValue = "1", name = "page") int page,
            @ModelAttribute AgtContactSearchDTO inDTO) {

        Pagination paging = Pagination.builder()
                .page(page)
                .perSize(perSize)
                .offset((page - 1) * perSize)
                .build();

        inDTO.setConvertMblfonNoInfoToHshVlu(cryptoProvider.toHashEncryptedText(inDTO.getAgtEstimatedMblfonNoInfo()));
        BaseSearchDTO<AgtContactSearchDTO> baseSearchDTO = BaseSearchDTO.<AgtContactSearchDTO>builder().paging(paging).search(inDTO).build();
        paging.setTotalCount(oneIdMainService.getCountAgtContact(baseSearchDTO));

        return new GridResponseVO().data(GridData.<AgtContactDTO>builder()
                .contents(oneIdMainService.getAgtContact(baseSearchDTO))
                .pagination(paging).build()).successResponse(OneidConstants.SUCCESS);
    }

    @Operation(summary = "OneID관계이력테이블 조회", description = "OneID관계이력테이블 조회")
    @GetMapping(value = "/v1/merge-history")
    public ResponseEntity<GridResponseVO<GridData>> getMergeHistory(
            @RequestParam int perSize,
            @RequestParam(defaultValue = "1", name = "page") int page,
            @ModelAttribute MergeHistorySearchDTO inDTO) {

        Pagination paging = Pagination.builder()
                .page(page)
                .perSize(perSize)
                .offset((page - 1) * perSize)
                .build();

        BaseSearchDTO<MergeHistorySearchDTO> baseSearchDTO = BaseSearchDTO.<MergeHistorySearchDTO>builder().paging(paging).search(inDTO).build();
        paging.setTotalCount(oneIdMainService.getCountMergeHistory(baseSearchDTO));

        return new GridResponseVO().data(GridData.<MergeHistoryDTO>builder()
                .contents(oneIdMainService.getMergeHistory(baseSearchDTO))
                .pagination(paging).build()).successResponse(OneidConstants.SUCCESS);
    }

}