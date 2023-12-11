package com.cdp.portal.app.facade.log.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cdp.portal.app.facade.log.model.LogMgrModel;

@Mapper
public interface LogMgrMapper {
    
    Long insert(LogMgrModel logMgrModel);

}
