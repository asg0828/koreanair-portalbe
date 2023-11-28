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
    
    List<FeatureResDto.Features> selectAll(@Param("search") FeatureReqDto.SearchFeature searchDto);
    
    List<FeatureResDto.Feature> selects(@Param("paging") PagingDto pagingDto, @Param("search") FeatureReqDto.SearchFeature searchDto, @Param("userId") String userId);
    
    int selectCount(@Param("search") FeatureReqDto.SearchFeature searchDto);
    
    FeatureResDto.Feature selectById(String featureId);
    
    FeatureResDto.Feature selectByFeatureIdAndUserId(@Param("featureId") String featureId, @Param("userId") String userId);
    
    Long update(FeatureModel featureModel);
    
    Long updateDelYnById(@Param("modiId") String modiId, @Param("featureId") String featureId);

}
