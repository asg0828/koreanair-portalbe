package com.bdp.ap.app.system;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.role.service.RoleService;
import com.bdp.ap.app.system.model.PortletItemModel;
import com.bdp.ap.app.system.model.PortletModel;
import com.bdp.ap.app.system.model.PortletReqModel;
import com.bdp.ap.app.system.service.PortletItemService;
import com.bdp.ap.app.system.service.PortletService;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 포틀릿 관리 컨트롤러
 */
@RequestMapping("/system")
@Controller
@Slf4j
public class PortletController {
	@Resource
	PortletService portletService;
	@Resource
	PortletItemService portletItemService; 
	@Resource
	private RoleService roleService;
	@Resource
	private CodeService codeService;

	@Resource
	private IdUtil idUtil;

	/**
	 * 포틀릿 관리 페이지로 이동한다.
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/portlet")
	public String report(@ModelAttribute PortletReqModel criteria, Model model, @AuthenticationPrincipal AuthUser authUser) {
		criteria.setTotalCount(portletService.selectPortletListCnt(criteria));
		model.addAttribute("list", portletService.selectPortletList(criteria));
		model.addAttribute("pages", criteria);
		model.addAttribute("jsonCodes", new JSONArray(codeService.selectGroupTreeList(Constant.Code.PORTLET_TYPE)));

		return "system/portletList";
	}

	/**
	 * 포틀릿 관리 페이지로 이동한다.
	 *
	 * @param model
	 * @return
	 */
	@PostMapping("/portlet")
	public String portlet(@ModelAttribute Criteria criteria, RedirectAttributes attributes) {
		attributes.addFlashAttribute("criteria", criteria);
		
		return "redirect:/system/portlet";
	}
	
	/**
	 * 포틀릿 아이템을 가져온다.
	 * 
	 * @param criteria
	 * @param attributes
	 * @return
	 */
	@PostMapping("/portlet/ajax/{codeId}")
	@ResponseBody
	public List<PortletItemModel> portletItem(@PathVariable String codeId, @AuthenticationPrincipal AuthUser authUser) {
		return portletItemService.selectItemList(codeId, authUser);
	}

	/**
	 * 포틀릿 등록
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/portlet/regist")
	public String portletRegist(@ModelAttribute Criteria criteria, Model model, @AuthenticationPrincipal AuthUser authUser) {
		model.addAttribute("roles", roleService.selectAllList());
		model.addAttribute("jsonCodes", new JSONArray(codeService.selectGroupTreeList(Constant.Code.PORTLET_TYPE)));

		model.addAttribute("pages", criteria);
		
		return "system/portletDetail";
	}

	/**
	 * 포틀릿 등록
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/portlet/detail/{portletId}")
	public String portletDetail(@ModelAttribute Criteria criteria, Model model, @PathVariable String portletId, @AuthenticationPrincipal AuthUser authUser) {
		PortletModel portletModel = portletService.selectPortlet(portletId);
		model.addAttribute("detail", portletModel);
		model.addAttribute("roles", roleService.selectAllList());
		model.addAttribute("jsonCodes", new JSONArray(codeService.selectGroupTreeList(Constant.Code.PORTLET_TYPE)));

		model.addAttribute("pages", criteria);
		
		return "system/portletDetail";
	}

	@PostMapping("/portlet/insert")
	public String insert(@ModelAttribute PortletModel req, @AuthenticationPrincipal AuthUser authUser,
			Model model) {
		req.setRgstId(authUser.getMemberModel().getUserId());
		req.setModiId(authUser.getMemberModel().getUserId());
		req.setPortletId(idUtil.getPortletId());

		portletService.insertPortlet(req);

		return "redirect:/system/portlet";
	}

	@PostMapping("/portlet/update")
	public String update(@ModelAttribute PortletModel req, @AuthenticationPrincipal AuthUser authUser,
			Model model) {
		req.setModiId(authUser.getMemberModel().getUserId());

		portletService.updatePortlet(req);

		return "redirect:/system/portlet";
	}
}
