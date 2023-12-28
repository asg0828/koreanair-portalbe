package com.cdp.portal.app.facade.main.service;

import com.cdp.portal.app.facade.main.dto.response.MainResDto;
import com.cdp.portal.app.facade.main.mapper.MainTotalCountMapper;
import com.cdp.portal.app.facade.notice.dto.request.NoticeReqDto;
import com.cdp.portal.app.facade.notice.dto.response.NoticeResDto;
import com.cdp.portal.common.dto.PagingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainTotalCountService {
    private final MainTotalCountMapper mainTotalCountMapper;

    @Transactional(readOnly = true)
    public MainResDto.MainMyDeptResult getFeaturesDeptCount(String userId) {
        int featureDeptCount = mainTotalCountMapper.selectDeptCount(userId);
        int featureInterestDeptCount =  mainTotalCountMapper.selectInterestDeptCount(userId);
        return MainResDto.MainMyDeptResult.builder()
                .featureDeptCount(featureDeptCount)
                .featureInterestDeptCount(featureInterestDeptCount)
                .build();
    }

    @Transactional(readOnly = true)
    public MainResDto.MainCdpResult getUserBizMetaCount() {
        int featureCount = mainTotalCountMapper.selectFeatureCount();
        int avgUserCount =  mainTotalCountMapper.selectAvgUserCount();
        int tableSpecCount = mainTotalCountMapper.selectTableSpecCount();
        return MainResDto.MainCdpResult.builder()
                .featureCount(featureCount)
                .tableSpecCount(tableSpecCount)
                .avgUserCount(avgUserCount)
                .build();
    }

    @Transactional(readOnly = true)
    public MainResDto.MainAdminLoginInfoCdpResult getAdminLoginInfo(String userId) {
        Timestamp logDt = mainTotalCountMapper.selectLoginInfoLogDt(userId);
        String clientIp = mainTotalCountMapper.selectLoginInfoClientIp(userId);
        String formattedLogDt = logDt.toInstant()
                .atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return MainResDto.MainAdminLoginInfoCdpResult.builder()
                .logDt(formattedLogDt)
                .clientIp(clientIp)
                .build();
    }
}
