package com.cdp.portal.app.facade.report.mapper;


import com.cdp.portal.app.facade.report.dto.response.ReportResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ReportMapper {

    List<ReportResDto> selectVipReservationStatus();
    List<ReportResDto> selectPurchaseContribution(String criteria);
    List<ReportResDto> selectIntlBoardingTop100(String criteria);
    List<ReportResDto> selectDomesticBoardingTop100(String criteria);
    List<ReportResDto> selectTotalMileageTop100(String criteria);
}