package com.cdp.portal.app.facade.report.mapper;


import com.cdp.portal.app.facade.feature.dto.request.FeatureReqDto;
import com.cdp.portal.app.facade.report.dto.request.ReportReqDto;
import com.cdp.portal.app.facade.report.dto.response.ReportResDto;
import com.cdp.portal.common.dto.PagingDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ReportMapper {

    List<ReportResDto> selectVipReservationStatus(@Param("paging") PagingDto pagingDto);

    int selectCount(@Param("search") ReportReqDto.SearchReport searchDto);
    List<ReportResDto> selectPurchaseContribution(String criteria);
    List<ReportResDto> selectIntlBoardingTop100(String criteria);
    List<ReportResDto> selectDomesticBoardingTop100(String criteria);
    List<ReportResDto> selectTotalMileageTop100(String criteria);
}
