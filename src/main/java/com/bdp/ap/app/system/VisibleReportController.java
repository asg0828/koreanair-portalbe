package com.bdp.ap.app.system;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdp.ap.app.system.model.VisibleReportModel;
import com.bdp.ap.app.system.service.VisibleReportService;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템관리/시각화 보고서관리 컨트롤러
 */
@RequestMapping("/system")
@Controller
@Slf4j
public class VisibleReportController {

	@Autowired
	VisibleReportService visibleReportService;

	@Resource
	private IdUtil idUtil;

	/**
	 * 보고서관리 페이지로 이동한다.
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/visibleReport")
	public String report(@ModelAttribute Criteria criteria, Model model) {
		model.addAttribute("list", visibleReportService.selectVisibleReportList(criteria));
		criteria.setTotalCount(visibleReportService.selectVisibleReportListCount(criteria));
		model.addAttribute("pages", criteria);

		return "system/visibleReport";
	}

	/**
	 * 보고서관리 페이지로 이동한다.
	 *
	 * @param model
	 * @return
	 */
	@PostMapping("/visibleReport")
	public String report(@ModelAttribute Criteria criteria, RedirectAttributes attributes) {
		attributes.addFlashAttribute("criteria", criteria);
		return "redirect:/system/visibleReport";
	}

	@PostMapping("/visibleReport/insert")
	public String insert(@ModelAttribute VisibleReportModel visibleReportModel, @AuthenticationPrincipal AuthUser authUser,
			Model model) {
		visibleReportModel.setRgstId(authUser.getMemberModel().getUserId());
		visibleReportModel.setModiId(authUser.getMemberModel().getUserId());
		visibleReportModel.setVisibleReportId(idUtil.getVisibleReportId());

		visibleReportService.insertVisibleReport(visibleReportModel);

		return "redirect:/system/visibleReport";
	}

	@PostMapping("/visibleReport/update")
	public String update(@ModelAttribute VisibleReportModel visibleReportModel, @AuthenticationPrincipal AuthUser authUser,
			Model model) {
		visibleReportModel.setModiId(authUser.getMemberModel().getUserId());

		visibleReportService.updateVisibleReport(visibleReportModel);

		return "redirect:/system/visibleReport";
	}

	@GetMapping("/visibleReport/detail/ajax")
	@ResponseBody
	public VisibleReportModel select(@ModelAttribute VisibleReportModel visibleReportModel, Model model) {

		return visibleReportService.selectVisibleReport(visibleReportModel);
	}

	/**
	 * 보고서 등록페이지로 이동한다.
	 *
	 * @param model
	 * @return
	 */
	// @GetMapping("/report/regist")
	// public String reportRegist(@AuthenticationPrincipal AuthUser authUser, Model model) {
	// 	MemberModel user = authUser.getMemberModel();
	// 	reportService.getVWProjectList(user, model);
	// 	reportService.selectAllReportCodeList(model);

	// 	model.addAttribute("user", user);

	// 	//태블로 워크북 목록
	// 	model.addAttribute("tableauWorkbookList", reportService.selectTableauWorkbookList());

	// 	return "report/reportRegist";
	// }


}
