package com.bdp.ap.common.tiles;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.bdp.ap.app.menu.model.MenuModel;
import com.bdp.ap.app.menu.service.MenuService;
import com.bdp.ap.config.security.AuthUser;
// import com.bdp.ap.app.bizword.service.MetaService;

import lombok.extern.slf4j.Slf4j;

/**
 * 타일즈에서 상단 GNB 공통 로딩을 처리하기 위한 Custome View Preparer
 *
 * 메뉴에서 최상단 메뉴(upper_menu_id)가 0인 항목을 가지고 와서 layout/폴더의 gnb.jsp에서 TOP메뉴 목록을 출력하기
 * 위한 용도로 사용
 */
@Component
@Slf4j
public class MenuPreparer implements ViewPreparer {

	@Resource
	private MenuService menuService;

	// @Resource(name="metaService")
	// private MetaService metaService;
	
//	@Resource(name="aprvService")
//	private AprvService aprvService;

	@Override
	public void execute(Request request, AttributeContext attributeContext) {
		log.info("##########Start TopMenuListWithAuth##########");
		
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AuthUser) {
				AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				List<MenuModel> menuModelList = menuService.selectTopMenuListWithAuth(authUser.getMemberModel().getAuthId());
				if (menuModelList == null) {
					menuModelList = new ArrayList<>();
				}
				attributeContext.putAttribute("topMenuList", new Attribute(menuModelList), true);
			}
		}
		
		log.info("##########End TopMenuListWithAuth##########");
	}
}
