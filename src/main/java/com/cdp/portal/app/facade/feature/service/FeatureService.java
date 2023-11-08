package com.cdp.portal.app.facade.feature.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.cdp.portal.app.facade.feature.dto.request.FeatureReqDto;
import com.cdp.portal.app.facade.feature.dto.response.FeatureResDto;
import com.cdp.portal.app.facade.feature.dto.response.FeatureSeparateResDto;
import com.cdp.portal.app.facade.feature.mapper.FeatureMapper;
import com.cdp.portal.app.facade.feature.mapper.FeatureSeparateMapper;
import com.cdp.portal.app.facade.feature.model.FeatureModel;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.enumeration.CdpPortalError;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeatureService {
    
    private final FeatureMapper featureMapper;
    private final FeatureSeparateMapper featureSeparateMapper;
    private final IdUtil idUtil;
    
    public void createFeature(FeatureReqDto.CreateFeature dto) {
        Boolean isExists = featureMapper.isExistsByFeatureKoNm(dto.getFeatureKoNm());
        if (isExists) {
            throw CdpPortalError.FEATURE_NM_DUPLICATED.exception(dto.getFeatureKoNm());
        }
        
        isExists = featureMapper.isExistsByFeatureEnNm(dto.getFeatureEnNm());
        if (isExists) {
            throw CdpPortalError.FEATURE_NM_DUPLICATED.exception(dto.getFeatureEnNm());
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
                .featureRelTb(dto.getFeatureRelTb())
                .featureDsc(dto.getFeatureDsc())
                .rgstId("admin")    // TODO: 로그인한 사용자 세팅
                .modiId("admin")    // TODO: 로그인한 사용자 세팅
                .build();
        
        featureMapper.insert(featureModel);
    }
    
    public FeatureResDto.Feature getFeature(String featureId) {
        /* get feature */
        FeatureResDto.Feature feature = featureMapper.selectById(featureId);
        if (Objects.isNull(feature)) {
            throw CdpPortalError.FEATURE_NOT_FOUND.exception(featureId);
        }
        
        /* get feature separate */
        FeatureSeparateResDto featureSeparate = featureSeparateMapper.selectBySeGrpIdAndSeId("SE_GRP_ID", feature.getFeatureSeGrp());
        
        feature.setFeatureSeGrpNm(featureSeparate.getSeNm());
        
        return feature;
    }

}
