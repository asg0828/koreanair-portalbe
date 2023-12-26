package com.cdp.portal.app.facade.oneid.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

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
import com.cdp.portal.config.OneidMapper;

@Repository
@OneidMapper
public interface OneIdMainMapper {

    List<MasterDTO> getMaster(BaseSearchDTO<MasterSearchDTO> baseSearchDTO);

    int getCountMaster(BaseSearchDTO<MasterSearchDTO> baseSearchDTO);
    List<MasterDTO> getMasterForHistory(BaseSearchDTO<MasterSearchDTO> baseSearchDTO);

    int getCountMasterForHistory(BaseSearchDTO<MasterSearchDTO> baseSearchDTO);

    List<MasterHistoryDTO> getMasterHistory(BaseSearchDTO<MasterHistorySearchDTO> baseSearchDTO);

    int getCountMasterHistory(BaseSearchDTO<MasterHistorySearchDTO> baseSearchDTO);

//    List<PsptInfoDTO> getPsptInfo(BaseSearchDTO<PsptInfoSearchDTO> baseSearchDTO);
//
//    int getCountPsptInfo(BaseSearchDTO<PsptInfoSearchDTO> baseSearchDTO);
//
//    List<SkypassMappingDTO> getSkypassMapping(BaseSearchDTO<SkypassMappingSearchDTO> baseSearchDTO);
//
//    int getCountSkypassMapping(BaseSearchDTO<SkypassMappingSearchDTO> baseSearchDTO);
//
//    List<SkypassInfoDTO> getSkypassInfo(BaseSearchDTO<SkypassInfoSearchDTO> baseSearchDTO);
//
//    int getCountSkypassInfo(BaseSearchDTO<SkypassInfoSearchDTO> baseSearchDTO);

    List<PaxMappingDTO> getPaxMapping(BaseSearchDTO<PaxMappingSearchDTO> baseSearchDTO);

    int getCountPaxMapping(BaseSearchDTO<PaxMappingSearchDTO> baseSearchDTO);

    List<AgtContactDTO> getAgtContact(BaseSearchDTO<AgtContactSearchDTO> baseSearchDTO);

    int getCountAgtContact(BaseSearchDTO<AgtContactSearchDTO> baseSearchDTO);

    List<MergeHistoryDTO> getMergeHistory(BaseSearchDTO<MergeHistorySearchDTO> baseSearchDTO);

    int getCountMergeHistory(BaseSearchDTO<MergeHistorySearchDTO> baseSearchDTO);

//    void tempUpdateEncoding(MasterDTO dto);

//    List<SkypassMergeDTO> getSkypassMerge(BaseSearchDTO<SkypassMergeSearchDTO> baseSearchDTO);
//
//    int getCountSkypassMerge(BaseSearchDTO<SkypassMergeSearchDTO> baseSearchDTO);
}