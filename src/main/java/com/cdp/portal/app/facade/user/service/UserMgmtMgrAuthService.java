package com.cdp.portal.app.facade.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdp.portal.app.facade.user.dto.response.UserMgmtMgrAuthResDto;
import com.cdp.portal.app.facade.user.mapper.UserMgmtMgrAuthMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMgmtMgrAuthService {

	private final UserMgmtMgrAuthMapper userMgmtMgrAuthMapper;
	
	public List<UserMgmtMgrAuthResDto> getUserMgmtMgrAuths(){
		return userMgmtMgrAuthMapper.selectAll();
	}
}
