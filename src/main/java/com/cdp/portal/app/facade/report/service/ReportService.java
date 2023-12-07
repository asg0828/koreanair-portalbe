package com.cdp.portal.app.facade.report.service;

import com.cdp.portal.app.facade.notice.mapper.NoticeMapper;
import com.cdp.portal.app.facade.report.mapper.ReportMapper;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.app.facade.report.dto.response.ReportResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportMapper reportMapper;

    /**
     * 정형 보고서 목록 조회
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public ReportResDto.ReportsResult getVipReservationStatus () {

        return ReportResDto.ReportsResult.builder()
                .contents(reportMapper.selectVipReservationStatus())
                .build();
    }
}
