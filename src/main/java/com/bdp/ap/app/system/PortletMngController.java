
package com.bdp.ap.app.system;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.role.service.RoleService;
import com.bdp.ap.app.system.model.PortletItemModel;
import com.bdp.ap.app.system.model.PortletMngModel;
import com.bdp.ap.app.system.model.PortletReqModel;
import com.bdp.ap.app.system.service.PortletItemService;
import com.bdp.ap.app.system.service.PortletMngService;
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
public class PortletMngController {
	@Resource
	private PortletService portletService;
	@Resource
	private PortletMngService portletMngService;
	@Resource
	private RoleService roleService;
	@Resource
	private CodeService codeService;
	@Resource
	private IdUtil idUtil;
	@Resource
	private PortletItemService portletItemService;

	/**
	 * 포틀릿 관리 페이지로 이동한다.
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/portlet/detail")
	public String report(@ModelAttribute PortletReqModel criteria, Model model, @AuthenticationPrincipal AuthUser authUser) {
		PortletMngModel portletMngModel = portletMngService.selectPortletMng();
    	String jsonViewStr = portletMngModel.getPortletView();
    	JSONArray jsonItems = new JSONArray();
    	
    	if (jsonViewStr != null) {
    		JSONArray jsonArray = new JSONArray(jsonViewStr);

    		for (int i = 0; i < jsonArray.length(); i++) {
    			JSONObject obj = new JSONObject();
    			String codeId = (String) jsonArray.get(i);
    			String codeNm = codeService.selectCodeNm(codeId);
    			List<PortletItemModel> list = portletItemService.selectItemList(codeId, authUser);
    			
    			obj.put("codeId", codeId);
    			obj.put("codeNm", codeNm);
    			obj.put("list", new JSONArray(list));
    			
    			jsonItems.put(obj);
    		}
    	}
    	
    	model.addAttribute("jsonItems", jsonItems);
		model.addAttribute("detail", portletMngModel);
		model.addAttribute("codes", codeService.selectGroupTreeList(Constant.Code.PORTLET_TYPE));

		return "system/portletMng";
	}

	/**
	 * 포틀릿 관리 페이지로 이동한다.
	 *
	 * @param model
	 * @return
	 */
	@PostMapping("/detail")
	public String report(@ModelAttribute Criteria criteria, RedirectAttributes attributes) {
		attributes.addFlashAttribute("criteria", criteria);
		return "redirect:/system/portlet/detail";
	}

	@PostMapping("/portletMng/insert/complete")
	public String insert(@ModelAttribute PortletMngModel req, @AuthenticationPrincipal AuthUser authUser,
			Model model) {
		req.setRgstId(authUser.getMemberModel().getUserId());
		req.setModiId(authUser.getMemberModel().getUserId());
		req.setPortletMngId(idUtil.getPortletMngId());

		log.debug("{}", req);
		portletMngService.insertPortletMng(req);
		return "redirect:/system/portlet/detail";
	}

	@PostMapping("/portletMng/update/complete")
	public String update(@ModelAttribute PortletMngModel req, @AuthenticationPrincipal AuthUser authUser,
			Model model) {
		req.setModiId(authUser.getMemberModel().getUserId());

		portletMngService.updatePortletMng(req);

		return "redirect:/system/portlet/detail";
	}

}
