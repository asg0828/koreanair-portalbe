package com.cdp.portal.app.facade.log.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.log.dto.request.LogMgrReqDto;
import com.cdp.portal.app.facade.log.mapper.LogMgrMapper;
import com.cdp.portal.app.facade.log.model.LogMgrModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogMgrService {
    
    private final LogMgrMapper logMgrMapper;
    
    @Transactional
    public void createLogMgr(LogMgrReqDto.CreateLogMgr dto) {
        LogMgrModel logMgrModel = LogMgrModel.builder()
                .logDt(dto.getLogDt())
                .userId(dto.getUserId())
                .clientIp(dto.getClientIp())
                .rqstUri(dto.getRqstUri())
                .rqstMethod(dto.getRqstMethod())
                .rqstQuery(dto.getRqstQuery())
                .rqstBody(dto.getRqstBody())
                .rsptBody(dto.getRsptBody())
                .build();
        
        logMgrMapper.insert(logMgrModel);
    }

}
