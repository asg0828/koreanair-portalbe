package com.cdp.portal.app.facade.feature.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.feature.dto.request.FeatureReqDto;
import com.cdp.portal.app.facade.feature.dto.response.FeatureResDto;
import com.cdp.portal.app.facade.feature.model.FeatureModel;
import com.cdp.portal.common.dto.PagingDto;

@Mapper
public interface FeatureMapper {
    
    Long insert(FeatureModel featureModel);
    
    Boolean isExistsByFeatureKoNm(String featureKoNm);
    
    Boolean isExistsByFeatureEnNm(String featureEnNm);
    
    List<FeatureResDto.Feature> selectAll(@Param("paging") PagingDto pagingDto, @Param("search") FeatureReqDto.SearchFeature searchDto);
    
    int selectCount(@Param("search") FeatureReqDto.SearchFeature searchDto);
    
    FeatureResDto.Feature selectById(String featureId);
    
    Long update(FeatureModel featureModel);
    
    Long updateDelYnById(@Param("modiId") String modiId, @Param("featureId") String featureId);

}
