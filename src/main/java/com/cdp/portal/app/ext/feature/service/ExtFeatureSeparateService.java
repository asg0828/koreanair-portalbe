package com.cdp.portal.app.ext.feature.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cdp.portal.app.ext.feature.dto.response.ExtFeatureSeparateResDto;
import com.cdp.portal.app.facade.feature.mapper.FeatureSeparateMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExtFeatureSeparateService {
    
    private final FeatureSeparateMapper featureSeparateMapper;
    
    public List<ExtFeatureSeparateResDto> getFeatureSeparates(String seGrpId) {
        return featureSeparateMapper.selectBySeGrpId(seGrpId).stream()
                .map(m -> {
                    return ExtFeatureSeparateResDto.builder()
                            .seId(m.getSeId())
                            .seNm(m.getSeNm())
                            .seDsc(m.getSeDsc())
                            .ordSeq(m.getOrdSeq())
                            .build();
                })
                .collect(Collectors.toList());
    }

}
