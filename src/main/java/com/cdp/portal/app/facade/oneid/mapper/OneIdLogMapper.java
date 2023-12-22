package com.cdp.portal.app.facade.oneid.mapper;

import java.util.List;

import com.cdp.portal.app.facade.oneid.dto.common.BaseSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.request.ErrorLogSearchDTO;
import com.cdp.portal.app.facade.oneid.dto.response.ErrorLogDTO;
import com.cdp.portal.config.OneidMapper;

@OneidMapper
public interface OneIdLogMapper {

    List<ErrorLogDTO> getErrorLog(BaseSearchDTO<ErrorLogSearchDTO> baseSearchDTO);

    int getCountErrorLog(BaseSearchDTO<ErrorLogSearchDTO> baseSearchDTO);

}
