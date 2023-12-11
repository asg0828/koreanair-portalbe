package com.cdp.portal.app.facade.log.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.log.dto.request.LogUserReqDto;
import com.cdp.portal.app.facade.log.mapper.LogUserMapper;
import com.cdp.portal.app.facade.log.model.LogUserModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogUserService {
    
    private final LogUserMapper logUserMapper;
    
    @Transactional
    public void createLogUser(LogUserReqDto.CreateLogUser dto) {
        LogUserModel logUserModel = LogUserModel.builder()
                .logDt(dto.getLogDt())
                .userId(dto.getUserId())
                .clientIp(dto.getClientIp())
                .rqstUri(dto.getRqstUri())
                .rqstMethod(dto.getRqstMethod())
                .rqstQuery(dto.getRqstQuery())
                .rqstBody(dto.getRqstBody())
                .rsptBody(dto.getRsptBody())
                .build();
        
        logUserMapper.insert(logUserModel);
    }

}
