package com.cdp.portal.app.facade.feature.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cdp.portal.app.facade.feature.model.FeatureHistModel;

@Mapper
public interface FeatureHistMapper {
    
    Long insert(FeatureHistModel featureHistModel);

}
