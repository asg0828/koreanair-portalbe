package com.cdp.portal.app.facade.report.mapper;

import com.cdp.portal.app.facade.report.dto.response.ReportResDto;
import com.cdp.portal.common.dto.PagingDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ReportMapper {

    List<ReportResDto> selectVipReservationStatus(@Param("paging") PagingDto pagingDto, @Param("sortedColumn") String sortedColumn, @Param("sortedDirection") String sortedDirection);

    int selectCount();
    List<ReportResDto> selectPurchaseContribution(String criteria);
    List<ReportResDto> selectIntlBoardingTop100(String criteria);
    List<ReportResDto> selectDomesticBoardingTop100(String criteria);
    List<ReportResDto> selectTotalMileageTop100(String criteria);
    List<ReportResDto> selectAwardTicketBoardingTop100(String criteria);
    List<ReportResDto> selectIntlMileageUpgradeBoardingTop100(String criteria);
}
