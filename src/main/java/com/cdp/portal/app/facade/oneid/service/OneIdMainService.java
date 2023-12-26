package com.cdp.portal.app.facade.oneid.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.oneid.dto.common.BaseSearchDTO;
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
import com.cdp.portal.app.facade.oneid.mapper.OneIdMainMapper;
import com.cdp.portal.common.encryption.CryptoProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OneIdMainService {

    private final OneIdMainMapper oneIdMainMapper;
    private final CryptoProvider cryptoProvider;

    @Transactional(readOnly = true)
    public List<MasterDTO> getMaster(BaseSearchDTO<MasterSearchDTO> baseSearchDTO) {
        List<MasterDTO> result = oneIdMainMapper.getMaster(baseSearchDTO);
        for (MasterDTO dto : result) {
            dto.setMobilePhoneNumberInfo(cryptoProvider.toAesDecryptedText(dto.getMobilePhoneNumberInfo()));
            dto.setHomePhoneNumberInfo(cryptoProvider.toAesDecryptedText(dto.getHomePhoneNumberInfo()));
            dto.setOfficePhoneNumberInfo(cryptoProvider.toAesDecryptedText(dto.getOfficePhoneNumberInfo()));
            dto.setEmailAddress(cryptoProvider.toAesDecryptedText(dto.getEmailAddress()));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public int getCountMaster(BaseSearchDTO<MasterSearchDTO> baseSearchDTO) {
        return oneIdMainMapper.getCountMaster(baseSearchDTO);
    }
    public List<MasterDTO> getMasterForHistory(BaseSearchDTO<MasterSearchDTO> baseSearchDTO) {
        List<MasterDTO> result = oneIdMainMapper.getMasterForHistory(baseSearchDTO);
        for (MasterDTO dto : result) {
            dto.setMobilePhoneNumberInfo(cryptoProvider.toAesDecryptedText(dto.getMobilePhoneNumberInfo()));
            dto.setHomePhoneNumberInfo(cryptoProvider.toAesDecryptedText(dto.getHomePhoneNumberInfo()));
            dto.setOfficePhoneNumberInfo(cryptoProvider.toAesDecryptedText(dto.getOfficePhoneNumberInfo()));
            dto.setEmailAddress(cryptoProvider.toAesDecryptedText(dto.getEmailAddress()));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public int getCountMasterForHistory(BaseSearchDTO<MasterSearchDTO> baseSearchDTO) {
        return oneIdMainMapper.getCountMasterForHistory(baseSearchDTO);
    }

    @Transactional(readOnly = true)
    public List<MasterHistoryDTO> getMasterHistory(BaseSearchDTO<MasterHistorySearchDTO> baseSearchDTO) {
        List<MasterHistoryDTO> result = oneIdMainMapper.getMasterHistory(baseSearchDTO);
        for (MasterHistoryDTO dto : result) {
            dto.setBfChgMobilePhoneNoInfo(cryptoProvider.toAesDecryptedText(dto.getBfChgMobilePhoneNoInfo()));
            dto.setBfChgEmailAdrs(cryptoProvider.toAesDecryptedText(dto.getBfChgEmailAdrs()));
            dto.setAfChgMobilePhoneNoInfo(cryptoProvider.toAesDecryptedText(dto.getAfChgMobilePhoneNoInfo()));
            dto.setAfChgEmailAdrs(cryptoProvider.toAesDecryptedText(dto.getAfChgEmailAdrs()));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public int getCountMasterHistory(BaseSearchDTO<MasterHistorySearchDTO> baseSearchDTO) {
        return oneIdMainMapper.getCountMasterHistory(baseSearchDTO);
    }

//    @Transactional(readOnly = true)
//    public List<PsptInfoDTO> getPsptInfo(BaseSearchDTO<PsptInfoSearchDTO> baseSearchDTO) {
//        return oneIdMainMapper.getPsptInfo(baseSearchDTO);
//    }
//
//    @Transactional(readOnly = true)
//    public int getCountPsptInfo(BaseSearchDTO<PsptInfoSearchDTO> baseSearchDTO) {
//        return oneIdMainMapper.getCountPsptInfo(baseSearchDTO);
//    }
//
//    @Transactional(readOnly = true)
//    public List<SkypassMappingDTO> getSkypassMapping(BaseSearchDTO<SkypassMappingSearchDTO> baseSearchDTO) {
//        return oneIdMainMapper.getSkypassMapping(baseSearchDTO);
//    }
//
//    @Transactional(readOnly = true)
//    public int getCountSkypassMapping(BaseSearchDTO<SkypassMappingSearchDTO> baseSearchDTO) {
//        return oneIdMainMapper.getCountSkypassMapping(baseSearchDTO);
//    }
//
//    @Transactional(readOnly = true)
//    public List<SkypassInfoDTO> getSkypassInfo(BaseSearchDTO<SkypassInfoSearchDTO> baseSearchDTO) {
//        List<SkypassInfoDTO> result = oneIdMainMapper.getSkypassInfo(baseSearchDTO);
//        for (SkypassInfoDTO dto : result) {
//            dto.setMobilePhoneNumberInfo(cryptoProvider.toAesDecryptedText(dto.getMobilePhoneNumberInfo()));
//            dto.setHomePhoneNumberInfo(cryptoProvider.toAesDecryptedText(dto.getHomePhoneNumberInfo()));
//            dto.setOfficePhoneNumberInfo(cryptoProvider.toAesDecryptedText(dto.getOfficePhoneNumberInfo()));
//            dto.setEmailAddress(cryptoProvider.toAesDecryptedText(dto.getEmailAddress()));
//        }
//
//        return result;
//    }
//
//    @Transactional(readOnly = true)
//    public int getCountSkypassInfo(BaseSearchDTO<SkypassInfoSearchDTO> baseSearchDTO) {
//        return oneIdMainMapper.getCountSkypassInfo(baseSearchDTO);
//    }

    @Transactional(readOnly = true)
    public List<PaxMappingDTO> getPaxMapping(BaseSearchDTO<PaxMappingSearchDTO> baseSearchDTO) {
        return oneIdMainMapper.getPaxMapping(baseSearchDTO);
    }

    @Transactional(readOnly = true)
    public int getCountPaxMapping(BaseSearchDTO<PaxMappingSearchDTO> baseSearchDTO) {
        return oneIdMainMapper.getCountPaxMapping(baseSearchDTO);
    }

    @Transactional(readOnly = true)
    public List<AgtContactDTO> getAgtContact(BaseSearchDTO<AgtContactSearchDTO> baseSearchDTO) {
        List<AgtContactDTO> result = oneIdMainMapper.getAgtContact(baseSearchDTO);
        for (AgtContactDTO dto : result) {
            dto.setAgtEstimatedMblfonNoInfo(cryptoProvider.toAesDecryptedText(dto.getAgtEstimatedMblfonNoInfo()));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public int getCountAgtContact(BaseSearchDTO<AgtContactSearchDTO> baseSearchDTO) {
        return oneIdMainMapper.getCountAgtContact(baseSearchDTO);
    }

    @Transactional(readOnly = true)
    public List<MergeHistoryDTO> getMergeHistory(BaseSearchDTO<MergeHistorySearchDTO> baseSearchDTO) {
        List<MergeHistoryDTO> result = oneIdMainMapper.getMergeHistory(baseSearchDTO);
        for (MergeHistoryDTO dto : result) {
            dto.setEmailAddress(cryptoProvider.toAesDecryptedText(dto.getEmailAddress()));
            dto.setMobilePhoneNumberInfo(cryptoProvider.toAesDecryptedText(dto.getMobilePhoneNumberInfo()));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public int getCountMergeHistory(BaseSearchDTO<MergeHistorySearchDTO> baseSearchDTO) {
        return oneIdMainMapper.getCountMergeHistory(baseSearchDTO);
    }

//    @Transactional(readOnly = true)
//    public List<SkypassMergeDTO> getSkypassMerge(BaseSearchDTO<SkypassMergeSearchDTO> baseSearchDTO) {
//        return oneIdMainMapper.getSkypassMerge(baseSearchDTO);
//    }
//
//    @Transactional(readOnly = true)
//    public int getCountSkypassMerge(BaseSearchDTO<SkypassMergeSearchDTO> baseSearchDTO) {
//        return oneIdMainMapper.getCountSkypassMerge(baseSearchDTO);
//    }
}
