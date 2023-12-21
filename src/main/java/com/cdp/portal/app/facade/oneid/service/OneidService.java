package com.cdp.portal.app.facade.oneid.service;

import com.cdp.portal.app.facade.oneid.dto.common.BaseSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.response.*;
import com.cdp.portal.app.facade.oneid.mapper.OneIdReportMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.oneid.mapper.OneIdLogMapper;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OneidService {

    private final OneIdLogMapper oneIdLogMapper;
    private final OneIdReportMapper oneIdReportMapper;

    @Transactional
    public int getErrorLog() {

        return oneIdLogMapper.selectAll();
    }
    @Transactional
    public int getCountDailyReport(BaseSearchDTO<DailyReportSearchDTO> baseSearchDTO) {

        return oneIdReportMapper.getCountDailyReport(baseSearchDTO);
    }

    @Transactional
    public List<DailyReportDTO> getDailyReport(BaseSearchDTO<DailyReportSearchDTO> baseSearchDTO) {

        return oneIdReportMapper.getDailyReport(baseSearchDTO);
    }

    @Transactional
    public int getCountCtiVocReport(BaseSearchDTO<CtiVocReportSearchDTO> baseSearchDTO) {

        return oneIdReportMapper.getCountCtiVocReport(baseSearchDTO);
    }

    @Transactional
    public List<CtiVocReportDTO> getCtiVocReport(BaseSearchDTO<CtiVocReportSearchDTO> baseSearchDTO) {

        return oneIdReportMapper.getCtiVocReport(baseSearchDTO);
    }

    @Transactional
    public int getCountSamePnrReport(BaseSearchDTO<SamePnrReportDTO> baseSearchDTO) {

        return oneIdReportMapper.getCountSamePnrReport(baseSearchDTO);
    }

    @Transactional
    public List<SamePnrReportDTO> getSamePnrReport(BaseSearchDTO<SamePnrReportDTO> baseSearchDTO) {

        return oneIdReportMapper.getSamePnrReport(baseSearchDTO);
    }

}
