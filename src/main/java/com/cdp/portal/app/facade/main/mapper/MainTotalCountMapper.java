package com.cdp.portal.app.facade.main.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface MainTotalCountMapper {
    int selectDeptCount(@Param("userId") String userId);
    int selectInterestDeptCount(@Param("userId") String userId);
    int selectFeatureCount();
    int selectTableSpecCount();
    int selectAvgUserCount();
}
