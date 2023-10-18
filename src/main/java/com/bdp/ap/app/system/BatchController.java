package com.bdp.ap.app.system;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.system.model.BatchModel;
import com.bdp.ap.app.system.model.BatchReqModel;
import com.bdp.ap.app.system.service.BatchService;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 배치 관리 컨트롤러
 */
@RequestMapping("/system")
@Controller
@Slf4j
public class BatchController {

	@Autowired
	BatchService batchService;

	@Resource
	private IdUtil idUtil;
	
	@Resource
	private CodeService codeService;

	/**
	 * 보고서관리 페이지로 이동한다.
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/batch")
	public String report(@ModelAttribute BatchReqModel criteria, Model model) {
		model.addAttribute("batchList", batchService.selectBatchList(criteria));
		criteria.setTotalCount(batchService.selectBatchListCount(criteria));
		model.addAttribute("pages", criteria);

		return "system/batchList";
	}

	/**
	 * 보고서관리 페이지로 이동한다.
	 *
	 * @param model
	 * @return
	 */
	@PostMapping("/batch")
	public String report(@ModelAttribute Criteria criteria, RedirectAttributes attributes) {
		attributes.addFlashAttribute("criteria", criteria);
		return "redirect:/system/batch";
	}

	/**
	 * 배치 상세 조회
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/batch/detail/{scheduleNm}")
	public String detail(@ModelAttribute Criteria criteria, Model model, @PathVariable String scheduleNm, @AuthenticationPrincipal AuthUser authUser) {
		BatchModel batchModel = batchService.selectBatch(scheduleNm);
		
		model.addAttribute("batchInfo", batchModel);

		model.addAttribute("pages", criteria);
		
		return "system/batchDetail";
	}
	
	@GetMapping("/batch/modify/{scheduleNm}")
	public String modify(@ModelAttribute Criteria criteria, Model model, @PathVariable String scheduleNm, @AuthenticationPrincipal AuthUser authUser) {
		BatchModel batchModel = batchService.selectBatch(scheduleNm);
		
		model.addAttribute("batchInfo", batchModel);
		model.addAttribute("scheduleClList", codeService.selectGroupIdAllList("SCHEDULE_CL"));

		model.addAttribute("pages", criteria);
		
		return "system/batchRegist";
	}

	@GetMapping("/batch/regist")
	public String regist(@ModelAttribute Criteria criteria, Model model, @ModelAttribute BatchModel batchModel) {
		
		model.addAttribute("scheduleClList", codeService.selectGroupIdAllList("SCHEDULE_CL"));

		model.addAttribute("pages", criteria);
		
		return "system/batchRegist";
	}
	
	@PostMapping("/batch/insert")
	public String insert(@ModelAttribute BatchModel batchInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
		batchInfo.setRgstId(authUser.getMemberModel().getUserId());
		batchInfo.setModiId(authUser.getMemberModel().getUserId());

		batchService.insertBatch(batchInfo);

		return "redirect:/system/batch";
	}

	@PostMapping("/batch/update")
	public String update(@ModelAttribute BatchModel batchInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
		batchInfo.setModiId(authUser.getMemberModel().getUserId());

		batchService.updateBatch(batchInfo);

		return "redirect:/system/batch";
	}
	
	@PostMapping("/batch/delete")
	public String delete(@ModelAttribute BatchModel batchInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
		batchInfo.setModiId(authUser.getMemberModel().getUserId());

		batchService.deleteBatch(batchInfo);

		return "redirect:/system/batch";
	}
	
	/**
     * 스케줄 이름 중복확인 
     * @param batchInfo
     * @param model
     * @return
     */
    @RequestMapping(value="/batch/batchNmCheck/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object>batchNmCheck(@ModelAttribute BatchReqModel criteria, Model model) {
    	Map<String,Object> result = new HashMap<String, Object>();
    	
    	result.put("sameCount",batchService.selectSameBatchNmCount(criteria));
    	result.put("result", true);
    	
    	return result;
    };

}
