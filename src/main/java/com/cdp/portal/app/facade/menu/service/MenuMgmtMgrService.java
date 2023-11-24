package com.cdp.portal.app.facade.menu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.auth.mapper.AuthMgmtMgrMapper;
import com.cdp.portal.app.facade.menu.dto.request.MenuMgmtReqDto;
import com.cdp.portal.app.facade.menu.dto.response.MenuMgmtResDto;
import com.cdp.portal.app.facade.menu.mapper.MenuMgmtMgrMapper;
import com.cdp.portal.app.facade.menu.model.MenuModel;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.enumeration.CdpPortalError;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuMgmtMgrService {
	private final IdUtil idUtil;
	private final MenuMgmtMgrMapper menuMgmtMgrMapper;
	private final AuthMgmtMgrMapper authMgmtMgrMapper;

    @Transactional
    public void saveMenus(List<MenuMgmtReqDto.SaveMenu> dtos) {
    	final String menuId = idUtil.getMenuId();

    	List<MenuMgmtReqDto.SaveMenu> createMenus = dtos.stream()
    			.filter(c -> CommonConstants.CUD_OPERATOR_CREATE.equals(c.getOprtrSe()))
    			.collect(Collectors.toList());

    	List<MenuMgmtReqDto.SaveMenu> updateMenus = dtos.stream()
    			.filter(c -> CommonConstants.CUD_OPERATOR_UPDATE.equals(c.getOprtrSe()))
    			.collect(Collectors.toList());

    	List<MenuMgmtReqDto.SaveMenu> deleteMenus = dtos.stream()
    			.filter(c -> CommonConstants.CUD_OPERATOR_DELETE.equals(c.getOprtrSe()))
    			.collect(Collectors.toList());

    	createMenus.forEach(c -> {
    		menuMgmtMgrMapper.insert(MenuModel.builder()
    				.menuId(menuId)
    				.upMenuId(c.getUpMenuId())
    				.menuNm(c.getMenuNm())
    				.menuDsc(c.getMenuDsc())
    				.menuUrl(c.getMenuUrl())
    				.ordSeq(c.getOrdSeq())
    				.useYn(c.getUseYn())
    				.rgstId("admin")    // TODO: 로그인한 사용자 세팅
    	            .modiId("admin")    // TODO: 로그인한 사용자 세팅
    				.build());
    	});

    	updateMenus.forEach(c -> {
    		Boolean isExists = menuMgmtMgrMapper.isExistsByMenuId(c.getMenuId());
			if (!isExists) {
			      throw CdpPortalError.MENU_ID_NOT_FOUND.exception(c.getMenuId());
			}

    		menuMgmtMgrMapper.update(MenuModel.builder()
	    			.menuId(c.getMenuId())
	    			.upMenuId(c.getUpMenuId())
    				.menuNm(c.getMenuNm())
    				.menuDsc(c.getMenuDsc())
    				.menuUrl(c.getMenuUrl())
    				.ordSeq(c.getOrdSeq())
    				.useYn(c.getUseYn())
	    			.modiId("admin")    // TODO: 로그인한 사용자 세팅
	    			.build());
    	});

    	deleteMenus.forEach(c -> {
			Boolean isExists = menuMgmtMgrMapper.isExistsByMenuId(c.getMenuId());
			if (!isExists) {
			      throw CdpPortalError.MENU_ID_NOT_FOUND.exception(c.getMenuId());
			}

    		menuMgmtMgrMapper.delete(c.getMenuId());
    		authMgmtMgrMapper.deleteMgrMenuByMenuId(c.getMenuId());
    	});

    }

    public MenuMgmtResDto.MenuMgmtResult getDepts(MenuMgmtReqDto.SearchMenu searchDto) {
        return MenuMgmtResDto.MenuMgmtResult.builder()
                .contents(menuMgmtMgrMapper.selectAll(searchDto))
                .search(searchDto)
                .build();
    }
}
