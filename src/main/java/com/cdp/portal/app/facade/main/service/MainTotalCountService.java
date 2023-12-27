package com.cdp.portal.app.facade.main.service;

import com.cdp.portal.app.facade.main.dto.response.MainResDto;
import com.cdp.portal.app.facade.main.mapper.MainTotalCountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public MainResDto.MainCdpResult getBizMetaCount() {
        int featureCount = mainTotalCountMapper.selectFeatureCount();
        int avgUserCount =  mainTotalCountMapper.selectAvgUserCount();
        int tableSpecCount = mainTotalCountMapper.selectTableSpecCount();
        return MainResDto.MainCdpResult.builder()
                .featureCount(featureCount)
                .tableSpecCount(tableSpecCount)
                .avgUserCount(avgUserCount)
                .build();
    }
}
