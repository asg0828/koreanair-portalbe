package com.cdp.portal.app.facade.feature.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cdp.portal.app.facade.feature.dto.response.FeatureResDto;
import com.cdp.portal.app.facade.feature.model.FeatureModel;

@Mapper
public interface FeatureMapper {
    
    Long insert(FeatureModel featureModel);
    
    Boolean isExistsByFeatureKoNm(String featureKoNm);
    
    Boolean isExistsByFeatureEnNm(String featureEnNm);
    
    FeatureResDto.Feature selectById(String featureId);

}
