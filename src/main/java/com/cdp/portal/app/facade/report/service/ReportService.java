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
     * VIP 고객 현황 목록 조회
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public ReportResDto.ReportsResult getVipReservationStatus () {

        return ReportResDto.ReportsResult.builder()
                .contents(reportMapper.selectVipReservationStatus())
                .build();
    }

    /**
     * 구매 기여도 TOP 100 목록 조회
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public ReportResDto.ReportsResult getPurchaseContribution (String criteria) {

        return ReportResDto.ReportsResult.builder()
                .contents(reportMapper.selectPurchaseContribution(criteria))
                .build();
    }

    /**
     * 국제선 탑승 횟수 TOP 100 목록 조회
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public ReportResDto.ReportsResult getIntlBoardingTop100 (String criteria) {

        return ReportResDto.ReportsResult.builder()
                .contents(reportMapper.selectIntlBoardingTop100(criteria))
                .build();
    }

    /**
     * 국내선 탑승 횟수 TOP 100 목록 조회
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public ReportResDto.ReportsResult getDomesticBoardingTop100 (String criteria) {

        return ReportResDto.ReportsResult.builder()
                .contents(reportMapper.selectDomesticBoardingTop100(criteria))
                .build();
    }

    /**
     * 마일리지 적립 TOP 100 목록 조회
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public ReportResDto.ReportsResult getTotalMileageTop100 (String criteria) {

        return ReportResDto.ReportsResult.builder()
                .contents(reportMapper.selectTotalMileageTop100(criteria))
                .build();
    }
}
