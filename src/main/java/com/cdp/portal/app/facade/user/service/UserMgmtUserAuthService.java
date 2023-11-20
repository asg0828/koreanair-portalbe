package com.cdp.portal.app.facade.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdp.portal.app.facade.user.dto.response.UserMgmtUserAuthResDto;
import com.cdp.portal.app.facade.user.mapper.UserMgmtUserAuthMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMgmtUserAuthService {

	private final UserMgmtUserAuthMapper userMgmtUserAuthMapper;
	
	public List<UserMgmtUserAuthResDto> getUserMgmtUserAuths(){
		return userMgmtUserAuthMapper.selectAll();
	}
}
