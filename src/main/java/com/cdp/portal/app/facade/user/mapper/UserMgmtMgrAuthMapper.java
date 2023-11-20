package com.cdp.portal.app.facade.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cdp.portal.app.facade.user.dto.response.UserMgmtMgrAuthResDto;

@Mapper
public interface UserMgmtMgrAuthMapper {
	List<UserMgmtMgrAuthResDto> selectAll();
}
