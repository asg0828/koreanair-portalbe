package com.cdp.portal.app.facade.menu.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.cdp.portal.app.facade.menu.dto.response.MenuAuthMgmtResDto;
import com.cdp.portal.app.facade.menu.mapper.MenuAuthMgmtUserMapper;
import com.cdp.portal.app.facade.menu.model.MenuAuthModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuAuthMgmtUserService {
	private final MenuAuthMgmtUserMapper menuAuthMgmtUserMapper;

    @Transactional
    public void saveMenuAuth(String authId, String[] menuIds) {
    	menuAuthMgmtUserMapper.delete(authId);

    	if(!ObjectUtils.isEmpty(menuIds)) {
    		Arrays.asList(menuIds).forEach(menuId ->
    			menuAuthMgmtUserMapper.insert(MenuAuthModel.builder()
	        		.authId(authId)
	        		.menuId(menuId)
	        		.rgstId("admin")    // TODO: 로그인한 사용자 세팅
	                .modiId("admin")    // TODO: 로그인한 사용자 세팅
	    			.build()));
    	}
    }

    public MenuAuthMgmtResDto.MenuAuthMgmtResult getMenuAuths(String authId) {
        return MenuAuthMgmtResDto.MenuAuthMgmtResult.builder()
                .authId(authId)
                .menuIds(menuAuthMgmtUserMapper.selectByAuthId(authId).stream().map(c -> c.getMenuId()).toArray(String[]::new))
                .build();
    }
}
