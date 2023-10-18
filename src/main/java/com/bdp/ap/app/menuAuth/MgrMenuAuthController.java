package com.bdp.ap.app.menuAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.dept.service.DeptService;
import com.bdp.ap.app.member.service.MemberService;
import com.bdp.ap.app.menu.model.MgrMenuModel;
import com.bdp.ap.app.menu.model.TreeMenuModel;
import com.bdp.ap.app.menu.service.MgrMenuService;
import com.bdp.ap.app.role.service.MgrRoleService;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템관리 / 메뉴관리 컨트롤러
 *
 */
@Slf4j
@RequestMapping("/system")
@Controller
public class MgrMenuAuthController {

	@Resource
	private MgrMenuService mgrMenuService;

	@Resource
	private MgrRoleService mgrRoleService;

	@Resource
	private CodeService codeService;

	@Resource
	private DeptService deptService;

	@Resource
	private MemberService memberService;

	 

	/**
	 * 메뉴권한관리 페이지로 이동한다.
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/mgrMenuAuth")
	public String sysMenu(@ModelAttribute Criteria criteria, Model model,
			String old, String menuNm, String authId) {
		List<MgrMenuModel> list = mgrMenuService.selectList(menuNm);
		String rootMenuId = null;
		// 응답 메뉴 목록 객체
		List<TreeMenuModel> resMenuList = new ArrayList<>();
		Map<String, MgrMenuModel> resMenuMap = new HashMap<>();
		// 메뉴 리스트를 관리 하기 위한 참조 객체
		Map<String, TreeMenuModel> menuMap = new HashMap<>();
		for (MgrMenuModel menu : list) {
			if (menu.getLv() == 0) {
				rootMenuId = menu.getMenuId();
			}
			String menuId = menu.getMenuId();
			String upMenuId = menu.getUpMenuId();
			resMenuMap.put(menuId, menu);
			TreeMenuModel treeMenuModel = new TreeMenuModel();
			treeMenuModel.setMgrMenuModel(menu);
			menuMap.put(menuId, treeMenuModel);

			if (upMenuId == null || upMenuId.isEmpty()) {
				resMenuList.add(treeMenuModel);
			} else {
				if (menuMap.containsKey(upMenuId)) {
					menuMap.get(upMenuId).getSubMenuModel().add(treeMenuModel);
				} else {
					log.debug(" 부모 객체를 찾을 수 없습니다. {}", menu);
				}
			}
		}
		model.addAttribute("roles", mgrRoleService.selectList(criteria));
		model.addAttribute("menus", list);
		model.addAttribute("rootMenuId", rootMenuId);
		model.addAttribute("treeJson", new JSONObject(resMenuMap));
		model.addAttribute("menuList", resMenuList);

		model.addAttribute("menuNm", menuNm);
		model.addAttribute("authNm", criteria.getAuthNm());
		model.addAttribute("authId", authId);
		 
		return "menu/mgrMenuAuth"; 

	}

	@PostMapping("/mgrMenuAuth")
	@ResponseBody
	public List<MgrMenuModel> AuthSearch(Model model, MgrMenuModel sysMenuModel, String authId, HttpServletRequest request) {
		List<MgrMenuModel> list = new ArrayList<>();
		sysMenuModel.setUseYn("Y");
		return list;
	}

	/**
	 * 권한별 메뉴 조회
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgrMenuAuth/{authId}/popup", method = RequestMethod.POST)
	public Map<String, Object> sysMenuAuth(HttpServletRequest request, @AuthenticationPrincipal AuthUser authUser,
			@PathVariable String authId) {
		Map<String, Object> result = new HashMap<>();
		boolean res = false;

		List<MgrMenuModel> menuAuths = mgrMenuService.selectMenuListWithAuth(authId);

		if (menuAuths != null) {
			res = true;
			result.put("menuAuths", menuAuths);
		}
		result.put("result", res);
		return result;
	}

	/**
	 * 권한별 메뉴 저장
	 *
	 * @param request
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mgrMenuAuth/update/{authId}/popup", method = RequestMethod.POST)
	public Map<String, Object> updateMenuAuth(HttpServletRequest request, @AuthenticationPrincipal AuthUser authUser,
			@PathVariable String authId, @ModelAttribute MgrMenuModel menuModel) {
		Map<String, Object> result = new HashMap<>();
		boolean res = false;

		JSONArray jsons = menuModel.getTreeJson();

		for (int i = 0; i < jsons.length(); i++) {
			JSONObject json = jsons.getJSONObject(i);
			MgrMenuModel model = new MgrMenuModel();
			model.setAuthId(authId);
			model.setMenuId(json.getString("menuId"));
			String authUseYn = "N";
			if ("true".equals(json.getString("checked"))) {
				authUseYn = "Y";
			}
			model.setAuthUseYn(authUseYn);
			model.setModiId(authUser.getUsername());
			long menuAuths = mgrMenuService.updateMenuListWithAuth(model);
			if (menuAuths > 0) {
				res = true;
			} else {
				res = false;
			}
		}
		result.put("result", res);
		return result;
	}
}
