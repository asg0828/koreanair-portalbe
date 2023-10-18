package com.bdp.ap.app.menu;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdp.ap.app.menu.model.QuickMenuModel;
import com.bdp.ap.app.menu.service.MenuService;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/quickmenu")
@Controller
public class QuickMenuController {
	 @Resource
	 private MenuService menuService;
	
	 /**
	 * 퀵메뉴조회
	 * @param authUser
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/menuList/ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> quickMenuList(@AuthenticationPrincipal AuthUser authUser, @ModelAttribute QuickMenuModel quickMenuModel, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean res = false;	
						 
		List<QuickMenuModel> quickMenuList = menuService.selectBookMarkMenuListWithAuth(quickMenuModel);
		
		if (quickMenuList != null) {
			res = true;
			result.put("quickMenuList", quickMenuList);	 
		}
		result.put("result", res);
							
		return result;	
	}
	
	/**
	 * 쿽메뉴 저장
	 * @param quickMenuModel
	 * @param authUser
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/insert/ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertQuickMenu(@ModelAttribute QuickMenuModel quickMenuModel, @AuthenticationPrincipal AuthUser authUser, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean res = false;
		quickMenuModel.setRgstId(authUser.getMemberModel().getUserId());
		quickMenuModel.setModiId(authUser.getMemberModel().getUserId());
		quickMenuModel.setQuickUserId(authUser.getMemberModel().getUserId());
		
		long cnt = menuService.insertQuickMenu(quickMenuModel);
		//if(cnt > 0) {
			res = true;
			result.put("cnt", cnt);
			//즐겨찾기 메뉴
			quickMenuModel.setQuickUserId(authUser.getMemberModel().getUserId());
			quickMenuModel.setDelYn("N");
			List<QuickMenuModel> quickList = menuService.selectQuickMenuList(quickMenuModel);
			result.put("quickList", quickList);
		//}
		result.put("result", res);
		
		return result;
	}
	
	/**
	 * 쿽메뉴 수정
	 * @param quickMenuModel
	 * @param authUser
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateQuickMenu(@ModelAttribute QuickMenuModel quickMenuModel, @AuthenticationPrincipal AuthUser authUser, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean res = false;
		quickMenuModel.setRgstId(authUser.getMemberModel().getUserId());
		quickMenuModel.setModiId(authUser.getMemberModel().getUserId());
		quickMenuModel.setQuickUserId(authUser.getMemberModel().getUserId());
		
		long cnt = menuService.updateQuickMenu(quickMenuModel);
		
		//if(cnt > 0) {
			res = true;
			result.put("cnt", cnt);
			//즐겨찾기 메뉴
			quickMenuModel.setQuickUserId(authUser.getMemberModel().getUserId());
			quickMenuModel.setDelYn("N");
			List<QuickMenuModel> quickList = menuService.selectQuickMenuList(quickMenuModel);
			result.put("quickList", quickList);
		//}
		result.put("result", res);
		
		return result;
	}
	
	/**
	 * 쿽메뉴 삭제
	 * @param quickMenuModel
	 * @param authUser
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete/ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteQuickMenu(@ModelAttribute QuickMenuModel quickMenuModel, @AuthenticationPrincipal AuthUser authUser, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean res = false;
		quickMenuModel.setRgstId(authUser.getMemberModel().getUserId());
		quickMenuModel.setModiId(authUser.getMemberModel().getUserId());
		quickMenuModel.setQuickUserId(authUser.getMemberModel().getUserId());
		
		long cnt = menuService.deleteQuickMenu(quickMenuModel);

		//if(cnt > 0) {
			res = true;
			result.put("cnt", cnt);
			//즐겨찾기 메뉴
			quickMenuModel.setQuickUserId(authUser.getMemberModel().getUserId());
			quickMenuModel.setDelYn("N");
			List<QuickMenuModel> quickList = menuService.selectQuickMenuList(quickMenuModel);
			result.put("quickList", quickList);
		//}
		result.put("result", res);
		
		return result;	
	}
}
