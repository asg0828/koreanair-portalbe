package com.cdp.portal.app.ext.feature.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.ext.feature.dto.request.ExtFeatureReqDto;
import com.cdp.portal.app.facade.feature.mapper.FeatureMapper;
import com.cdp.portal.app.facade.feature.model.FeatureModel;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.enumeration.CdpPortalError;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExtFeatureService {
    
    private final FeatureMapper featureMapper;
    private final IdUtil idUtil;

    @Transactional
    public void createFeature(ExtFeatureReqDto.CreateFeature dto) {
        Boolean isExists = featureMapper.isExistsByFeatureKoNm(dto.getFeatureKoNm());
        if (isExists) {
            throw CdpPortalError.FEATURE_KO_NM_DUPLICATED.exception(dto.getFeatureKoNm());
        }
        
        isExists = featureMapper.isExistsByFeatureEnNm(dto.getFeatureEnNm());
        if (isExists) {
            throw CdpPortalError.FEATURE_EN_NM_DUPLICATED.exception(dto.getFeatureEnNm());
        }
        
        FeatureModel featureModel = FeatureModel.builder()
                .featureId(idUtil.getFeatureId())
                .featureTyp(dto.getFeatureTyp())
                .featureSe(dto.getFeatureSe())
                .featureKoNm(dto.getFeatureKoNm())
                .featureEnNm(dto.getFeatureEnNm())
                .calcUnt(dto.getCalcUnt())
                .featureDef(dto.getFeatureDef())
                .featureFm(dto.getFeatureFm())
                .enrUserId(dto.getEnrUserId())
                .enrDeptCode(dto.getEnrDeptCode())
                .featureRelTb(dto.getFeatureRelTb())
                .featureDsc(dto.getFeatureDsc())
                .rgstId(dto.getEnrUserId())
                .modiId(dto.getEnrUserId())
                .build();
        
        featureMapper.insert(featureModel);
    }

}
