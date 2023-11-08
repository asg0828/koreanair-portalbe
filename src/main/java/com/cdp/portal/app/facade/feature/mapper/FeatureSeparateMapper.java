package com.cdp.portal.app.facade.feature.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.feature.dto.response.FeatureSeparateResDto;

@Mapper
public interface FeatureSeparateMapper {
    
    List<FeatureSeparateResDto> selectBySeGrpId(String seGrpId);
    
    FeatureSeparateResDto selectBySeGrpIdAndSeId(@Param("seGrpId") String seGrpId, @Param("seId") String seId); 

}
