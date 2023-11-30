package com.cdp.portal.app.facade.feature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.feature.dto.request.FeatureReqDto;
import com.cdp.portal.app.facade.feature.dto.response.FeatureResDto;
import com.cdp.portal.app.facade.feature.mapper.FeatureHistMapper;
import com.cdp.portal.app.facade.feature.mapper.FeatureMapper;
import com.cdp.portal.app.facade.feature.model.FeatureHistModel;
import com.cdp.portal.app.facade.feature.model.FeatureModel;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.dto.PagingDto;
import com.cdp.portal.common.enumeration.CdpPortalError;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureService {
    
    private final FeatureMapper featureMapper;
    private final FeatureHistMapper featureHistMapper;
    private final IdUtil idUtil;

    @Transactional
    public void createFeature(FeatureReqDto.CreateFeature dto) {
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
                .rgstId("admin")    // TODO: 로그인한 사용자 세팅
                .modiId("admin")    // TODO: 로그인한 사용자 세팅
                .build();
        
        featureMapper.insert(featureModel);
    }
    
    @Transactional(readOnly = true)
    public FeatureResDto.Feature getFeature(final String featureId) {
        FeatureResDto.Feature feature = featureMapper.selectByFeatureIdAndUserId(featureId, "admin");   // TODO: 로그인한 사용자 세팅
        if (Objects.isNull(feature)) {
            throw CdpPortalError.FEATURE_NOT_FOUND.exception(featureId);
        }
        
        return feature;
    }
    
    @Transactional(readOnly = true)
    public List<FeatureResDto.Features> getFeatures(FeatureReqDto.SearchFeature searchDto) {
        if (StringUtils.isEmpty(searchDto.getFeatureKoNm()) && StringUtils.isEmpty(searchDto.getFeatureEnNm())) {
            return new ArrayList<>();
        }
        
        return featureMapper.selectAll(searchDto);
    }
    
    @Transactional(readOnly = true)
    public FeatureResDto.FeaturesResult getFeatures(PagingDto pagingDto, FeatureReqDto.SearchFeature searchDto) {
        pagingDto.setPaging(featureMapper.selectCount(searchDto));
        
        return FeatureResDto.FeaturesResult.builder()
                .contents(featureMapper.selects(pagingDto, searchDto, "admin"))   // TODO: 로그인한 사용자 세팅
                .search(searchDto)
                .page(pagingDto)
                .build();
    }
    
    @Transactional
    public void updateFeature(final String featureId, FeatureReqDto.updateFeature dto) {
        FeatureResDto.Feature feature = featureMapper.selectByFeatureId(featureId);
        if (Objects.isNull(feature)) {
            throw CdpPortalError.FEATURE_NOT_FOUND.exception(featureId);
        }
        
        /* update feature */
        FeatureModel featureModel = FeatureModel.builder()
                .featureId(featureId)
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
                .modiId("admin")    // TODO: 로그인한 사용자 세팅
                .build();
        
        featureMapper.update(featureModel);
        
        /* insert feature history */
        if (this.isFeatureChanged(feature, dto)) {
            FeatureHistModel featureHistModel = FeatureHistModel.builder()
                    .featureId(featureId)
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
                    .rgstId("admin")    // TODO: 로그인한 사용자 세팅
                    .modiId("admin")    // TODO: 로그인한 사용자 세팅
                    .build();
            
            featureHistMapper.insert(featureHistModel);
        }
    }
    
    @Transactional
    public void deleteFeature(final String featureId) {
        FeatureResDto.Feature feature = featureMapper.selectByFeatureId(featureId);
        if (Objects.isNull(feature)) {
            throw CdpPortalError.FEATURE_NOT_FOUND.exception(featureId);
        }
        
        featureMapper.updateDelYnByFeatureId(featureId, "admin");  // TODO: 로그인한 사용자 세팅
    }
            
    
    private Boolean isFeatureChanged(FeatureResDto.Feature before, FeatureReqDto.updateFeature after) {
        if (!StringUtils.equals(before.getFeatureTyp(), after.getFeatureTyp())) {
            return true;
        }
        if (!StringUtils.equals(before.getFeatureSe(), after.getFeatureSe())) {
            return true;
        }
        if (!StringUtils.equals(before.getFeatureKoNm(), after.getFeatureKoNm())) {
            return true;
        }
        if (!StringUtils.equals(before.getFeatureEnNm(), after.getFeatureEnNm())) {
            return true;
        }
        if (!StringUtils.equals(before.getCalcUnt(), after.getCalcUnt())) {
            return true;
        }
        if (!StringUtils.equals(before.getFeatureDef(), after.getFeatureDef())) {
            return true;
        }
        if (!StringUtils.equals(before.getFeatureFm(), after.getFeatureFm())) {
            return true;
        }
        if (!StringUtils.equals(before.getEnrUserId(), after.getEnrUserId())) {
            return true;
        }
        if (!StringUtils.equals(before.getEnrDeptCode(), after.getEnrDeptCode())) {
            return true;
        }
        if (!StringUtils.equals(before.getFeatureRelTb(), after.getFeatureRelTb())) {
            return true;
        }
        if (!StringUtils.equals(before.getFeatureDsc(), after.getFeatureDsc())) {
            return true;
        }
        
        return false;
    }

}
