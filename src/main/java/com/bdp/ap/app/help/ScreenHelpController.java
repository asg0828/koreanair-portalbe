package com.bdp.ap.app.help;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.dept.service.DeptService;
import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.app.file.service.FileService;
import com.bdp.ap.app.help.model.ScreenHelpModel;
import com.bdp.ap.app.help.service.ScreenHelpService;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.props.FileProps;
import com.bdp.ap.config.security.AuthUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/system")
@Controller
@Slf4j
public class ScreenHelpController {
	
	@Resource(name = "fileProps")
	private FileProps fileProps;

	@Resource
	private IdUtil idUtil;

	@Resource
	private CodeService codeService;

	@Resource
	private ScreenHelpService screenHelpService;

	@Resource
	private FileService fileService;

	@Resource
	private DeptService deptService;

	/**
	 * Search All Info
	 *
	 * @param criteria
	 * @param model
	 * @return
	 */
	@GetMapping("/screenHelp")
	public String help(@ModelAttribute Criteria criteria, Model model) {
		model.addAttribute("helpList", screenHelpService.selectScreenHelpList(criteria));
		criteria.setTotalCount(screenHelpService.selectScreenHelpListCount(criteria));
		model.addAttribute("pages", criteria);
		// 시스템 코드 조회
		model.addAttribute("codes", codeService.selectGroupIdAllList("SYSTEM_CD"));
		// 부서 조회
		model.addAttribute("depts", deptService.selectDeptClList());
		return "system/screenHelp";
	}

	/**
	 * Search Specific Info
	 *
	 * @param criteria
	 * @param attributes
	 * @return
	 */
	@PostMapping("/screenHelp")
	public String help(@ModelAttribute Criteria criteria, RedirectAttributes attributes) {
		attributes.addFlashAttribute("criteria", criteria);
		return "redirect:/system/screenHelp";
	}

	/**
	 * 화면 도움말 등록
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("screenHelp/regist")
	public String select(@ModelAttribute Criteria criteria, Model model) {
		// 시스템 코드 조회
		model.addAttribute("codes", codeService.selectGroupIdAllList("SYSTEM_CD"));
		// 부서 조회
		model.addAttribute("depts", deptService.selectDeptClList());

		model.addAttribute("pages", criteria);

		return "system/screenHelpRegist";
	}

	/**
	 * 화면 도움말 등록
	 *
	 * @param helpInfo
	 * @param authUser
	 * @param model
	 * @return
	 */
	@PostMapping("screenHelp/insert")
	public String insert(@ModelAttribute ScreenHelpModel screenHelpModel, @AuthenticationPrincipal AuthUser authUser,
			Model model) {
		screenHelpModel.setRgstId(authUser.getMemberModel().getUserId());
		screenHelpModel.setModiId(authUser.getMemberModel().getUserId());
		screenHelpModel.setScreenHelpId(idUtil.getScreenHelpId());

		model.addAttribute("helpInsertInfo", screenHelpService.insertScreenHelp(screenHelpModel));

		if (screenHelpModel.getFileIds() != null) {
			for (String fileId : screenHelpModel.getFileIds()) {
				FileModel file = new FileModel();
				file.setFileId(fileId);
				file.setRefId(screenHelpModel.getScreenHelpId());
				file.setModiId(authUser.getMemberModel().getUserId());
				fileService.updateFile(file);
			}
		}
		log.debug("HELP UPSERT MODEL: {}", model);

		return "redirect:/system/screenHelp";
	}

	/**
	 * 화면 도움말 등록
	 *
	 * @param helpInfo
	 * @param authUser
	 * @param model
	 * @return
	 */
	@PostMapping("screenHelp/update")
	public String update(@ModelAttribute ScreenHelpModel screenHelpModel, @AuthenticationPrincipal AuthUser authUser,
			Model model) {
		screenHelpModel.setModiId(authUser.getMemberModel().getUserId());

		model.addAttribute("helpInsertInfo", screenHelpService.updateScreenHelp(screenHelpModel));

		if (screenHelpModel.getFileIds() != null) {
			for (String fileId : screenHelpModel.getFileIds()) {
				FileModel file = new FileModel();
				file.setFileId(fileId);
				file.setRefId(screenHelpModel.getScreenHelpId());
				file.setModiId(authUser.getMemberModel().getUserId());
				fileService.updateFile(file);
			}
		}
		log.debug("HELP UPSERT MODEL: {}", model);

		return "redirect:/system/screenHelp";
	}

	/**
	 * Get Old Info
	 *
	 * @param helpId
	 * @param model
	 * @return
	 */
	@GetMapping("/screenHelp/detail/{screenHelpId}")
	public String detail(@ModelAttribute Criteria criteria, @PathVariable String screenHelpId, Model model) {
		ScreenHelpModel screenHelpModel = new ScreenHelpModel();
		screenHelpModel.setScreenHelpId(screenHelpId);
		screenHelpModel = screenHelpService.selectScreenHelp(screenHelpModel);
		model.addAttribute("helpInfo", screenHelpService.selectScreenHelp(screenHelpModel));

		// 시스템 코드 조회
		model.addAttribute("codes", codeService.selectGroupIdAllList("SYSTEM_CD"));
		// 부서 조회
		model.addAttribute("depts", deptService.selectDeptClList());

		model.addAttribute("pages", criteria);

		// 파일 조회
		FileModel f = new FileModel();
		f.setRefId(screenHelpId);
		List<FileModel> fileList = fileService.selectFileList(f);
		ObjectMapper om = new ObjectMapper();
		String fileJsonList = "";
		try {
			fileJsonList = om.writeValueAsString(fileList);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		model.addAttribute("fileList", fileList);
		model.addAttribute("fileJsonList", fileJsonList);
		
		return "system/screenHelpDetail";
	}

}
