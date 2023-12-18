package com.cdp.portal.app.facade.oneid.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.log.dto.request.LogMgrReqDto;
import com.cdp.portal.app.facade.log.model.LogMgrModel;
import com.cdp.portal.app.facade.menu.dto.request.MenuMgmtReqDto;
import com.cdp.portal.app.facade.oneid.mapper.OneIdLogMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OneidService {

    private final OneIdLogMapper onneIdLogMapper;

    @Transactional
    public int getErrorLog() {

        return onneIdLogMapper.selectAll();
    }

}
