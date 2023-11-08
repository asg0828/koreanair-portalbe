package com.cdp.portal.app.facade.feature.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdp.portal.app.facade.feature.dto.response.FeatureSeparateResDto;
import com.cdp.portal.app.facade.feature.mapper.FeatureSeparateMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureSeparateService {
    
    private final FeatureSeparateMapper featureSeparateMapper;
    
    public List<FeatureSeparateResDto> getFeatureSeparates(String seGrpId) {
        return featureSeparateMapper.selectBySeGrpId(seGrpId);
    }
    
    public FeatureSeparateResDto getFeatureSeparate(String seGrpId, String seId) {
        return featureSeparateMapper.selectBySeGrpIdAndSeId(seGrpId, seId);
    }

}
