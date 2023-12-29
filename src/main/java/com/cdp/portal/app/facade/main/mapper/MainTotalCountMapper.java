package com.cdp.portal.app.facade.main.mapper;

import com.cdp.portal.app.facade.main.dto.response.MainResDto;
import com.cdp.portal.app.facade.main.model.MainModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface MainTotalCountMapper {
    int selectDeptCount(@Param("userId") String userId);

    int selectInterestDeptCount(@Param("userId") String userId);

    int selectFeatureCount();

    int selectTableSpecCount();

    int selectAvgUserCount();

    Timestamp selectLoginInfoLogDt(@Param("userId") String userId);
    String selectLoginInfoClientIp(@Param("userId") String userId);

    List<MainResDto> selectLoginUserByDept();
}
