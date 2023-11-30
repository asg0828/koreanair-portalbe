package com.cdp.portal.app.facade.feature.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.feature.dto.response.FeatureSeparateResDto;
import com.cdp.portal.app.facade.feature.mapper.FeatureSeparateMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureSeparateService {
    
    private final FeatureSeparateMapper featureSeparateMapper;
    
    @Transactional(readOnly = true)
    public List<FeatureSeparateResDto> getFeatureSeparates(String seGrpId) {
        return featureSeparateMapper.selectBySeGrpId(seGrpId);
    }
    
    @Transactional(readOnly = true)
    public FeatureSeparateResDto getFeatureSeparate(String seGrpId, String seId) {
        return featureSeparateMapper.selectBySeGrpIdAndSeId(seGrpId, seId);
    }

}
