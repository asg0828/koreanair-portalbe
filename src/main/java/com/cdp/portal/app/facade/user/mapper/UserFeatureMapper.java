package com.cdp.portal.app.facade.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.user.model.UserFeatureModel;

@Mapper
public interface UserFeatureMapper {
    
    Long insert(UserFeatureModel userFeatureModel);
    
    Boolean isExistsByUserIdAndFeatureId(@Param("userId") String userId, @Param("featureId") String featureId);
    
    Long delete(@Param("userId") String userId, @Param("featureId") String featureId);

}
