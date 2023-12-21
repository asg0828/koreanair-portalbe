package com.cdp.portal.app.facade.oneid.mapper;

import com.cdp.portal.app.facade.oneid.dto.common.BaseSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.response.*;
import com.cdp.portal.config.OneidMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@OneidMapper
public interface OneIdReportMapper {

    List<DailyReportDTO> getDailyReport(BaseSearchDTO<DailyReportSearchDTO> baseSearchDTO);
    int getCountDailyReport(BaseSearchDTO<DailyReportSearchDTO> baseSearchDTO);
    List<CtiVocReportDTO> getCtiVocReport(BaseSearchDTO<CtiVocReportSearchDTO> baseSearchDTO);
    int getCountCtiVocReport(BaseSearchDTO<CtiVocReportSearchDTO> baseSearchDTO);

    List<SamePnrReportDTO> getSamePnrReport(BaseSearchDTO<SamePnrReportDTO> baseSearchDTO);
    int getCountSamePnrReport(BaseSearchDTO<SamePnrReportDTO> baseSearchDTO);

}
