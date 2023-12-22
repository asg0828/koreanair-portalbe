package com.cdp.portal.app.facade.oneid.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.oneid.dto.common.BaseSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.request.ErrorLogSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.response.CtiVocReportDTO;
import com.cdp.portal.app.facade.oneid.dto.response.CtiVocReportSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.response.DailyReportDTO;
import com.cdp.portal.app.facade.oneid.dto.response.DailyReportSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.response.ErrorLogDTO;
import com.cdp.portal.app.facade.oneid.dto.response.SamePnrReportDTO;
import com.cdp.portal.app.facade.oneid.mapper.OneIdLogMapper;
import com.cdp.portal.app.facade.oneid.mapper.OneIdReportMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OneIdLogService {

    private final OneIdLogMapper oneIdLogMapper;
    private final OneIdReportMapper oneIdReportMapper;

    @Transactional(readOnly = true)
    public List<ErrorLogDTO> getErrorLog(BaseSearchDTO<ErrorLogSearchDTO> baseSearchDTO) {
        return oneIdLogMapper.getErrorLog(baseSearchDTO);
    }

    @Transactional(readOnly = true)
    public int getCountErrorLog(BaseSearchDTO<ErrorLogSearchDTO> baseSearchDTO) {
        return oneIdLogMapper.getCountErrorLog(baseSearchDTO);
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
