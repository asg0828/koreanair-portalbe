package com.cdp.portal.app.facade.log.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cdp.portal.app.facade.log.model.LogUserModel;

@Mapper
public interface LogUserMapper {
    
    Long insert(LogUserModel logUserModel);

}
