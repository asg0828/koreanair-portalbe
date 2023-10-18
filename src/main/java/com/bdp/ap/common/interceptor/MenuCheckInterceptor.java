package com.bdp.ap.common.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.binding.BindingException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bdp.ap.app.menu.model.MenuModel;
import com.bdp.ap.app.menu.model.QuickMenuModel;
import com.bdp.ap.app.menu.service.MenuService;
import com.bdp.ap.common.Constant;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 호출되는 MenuURL을 기반으로 Submenu
 */
@Slf4j
public class MenuCheckInterceptor implements HandlerInterceptor {

	@Resource
	private MenuService menuService;

	/**
	 * 컨트롤러 호출후 호출된 URL을 기반으로 레프트메뉴에 대한 목록을 조회해서 페이지로 전달한다.
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (modelAndView != null) {
			if (SecurityContextHolder.getContext().getAuthentication() != null) {
				log.info("##########Start MenuCheckInterceptor##########");
				if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AuthUser) {
					AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

					// detail, regist, modify : 화면
					// insert, update, delete : 동작
					String uri = request.getRequestURI();
					Matcher matcher = Constant.UrlPattern.MENU_CHECK_PATTERN.matcher(uri);
					if (matcher.find()) {
						uri = uri.substring(0, matcher.start());
					}

					try {
						
						MenuModel currentMenu = menuService.selectUpperMenuIdForMenuUrlDepth(uri);
						
						Map<String, MenuModel> resMenuMap = new HashMap<>();
						if (currentMenu != null) {						
							String upperMenuId =  menuService.selectUpperMenuIdForMenuUrl(uri);
							modelAndView.addObject("upperMenuId", upperMenuId);
							
							List<MenuModel> subMenuList = menuService.selectLeftMenuListWithAuth(upperMenuId,authUser.getMemberModel().getAuthId());
							modelAndView.addObject("subMenuList", subMenuList);
							
					        for (MenuModel sysMenu : subMenuList) {
					            resMenuMap.put(sysMenu.getMenuId(), sysMenu);
					        }
					        modelAndView.addObject("myMenuId", currentMenu.getMenuId());
						}
//						modelAndView.addObject("subMenuList", new org.json.JSONObject(resMenuMap));
						modelAndView.addObject("myUri", request.getRequestURI());
						modelAndView.addObject("currentMenu", currentMenu);
						
						//즐겨찾기 메뉴
						QuickMenuModel quickMenuModel = new QuickMenuModel();
						quickMenuModel.setQuickUserId(authUser.getMemberModel().getUserId());
						quickMenuModel.setDelYn("N");
						List<QuickMenuModel> quickList = menuService.selectQuickMenuList(quickMenuModel);
						modelAndView.addObject("quickList", quickList);
						
						//즐겨찾기 class on
						boolean quickStar = false;
						if (currentMenu != null) {
							for(QuickMenuModel quick : quickList) {
								if(currentMenu.getMenuId().equals(quick.getMenuId())) {
									quickStar = true;
								}
							}
						}
						modelAndView.addObject("quickStar", quickStar);
						
						//전체 메뉴
						/*
						quickMenuModel.setAuthId(authUser.getMemberModel().getAuthId());
						quickMenuModel.setCompanyCd(authUser.getCompanyCd());
						List<QuickMenuModel> totalList = menuService.selectQuickMenuListWithAuth(quickMenuModel);
						modelAndView.addObject("totalList", totalList);
						*/
						
					} catch (BindingException e) {
						log.warn("urlCheck Error {}", e.getMessage());
					}
				}
				log.info("##########End MenuCheckInterceptor##########");
			}
		}
		
	}
}
