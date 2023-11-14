package com.cdp.portal.app.facade.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.user.dto.response.UserFeatureResDto;
import com.cdp.portal.app.facade.user.model.UserFeatureModel;
import com.cdp.portal.common.dto.PagingDto;

@Mapper
public interface UserFeatureMapper {
    
    Long insert(UserFeatureModel userFeatureModel);
    
    Boolean isExistsByUserIdAndFeatureId(@Param("userId") String userId, @Param("featureId") String featureId);
    
    List<UserFeatureResDto.UserFeatures> selectAll(@Param("userId") String userId, @Param("paging") PagingDto pagingDto);
    
    int selectCount(@Param("userId") String userId);
    
    Long delete(@Param("userId") String userId, @Param("featureId") String featureId);

}
