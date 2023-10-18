package com.bdp.ap.app.audit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdp.ap.app.audit.model.UsingTableModel;
import com.bdp.ap.app.audit.service.AuditDataSetService;
import com.bdp.ap.app.audit.service.LogService;
import com.bdp.ap.common.paging.Criteria;

/**
 * 감사관리 컨트롤러
 */
@RequestMapping("/audit")
@Controller
public class AuditController {

    @Resource
    private LogService logService;

    @Resource
    private AuditDataSetService auditDataSetService;

    /**
     * 상단페이지 이동용
     * @param criteria
     * @param model
     * @return
     */
    @GetMapping("/a")
    public String list(@ModelAttribute Criteria criteria, Model model) {
        //jsp
        return "redirect:/audit/dataSet";
    }

    /**
     * 데이터 셋 관리 화면
     * @param criteria
     * @param startDate
     * @param endDate
     * @param model
     * @return
     */
    @GetMapping("/dataSet")
    public String dataSet(@ModelAttribute Criteria criteria, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate, Model model) {
        
        if(!StringUtils.isEmpty(startDate)) {
			criteria.setStartDt(startDate);
		}
		if(!StringUtils.isEmpty(endDate)) {
			criteria.setEndDt(endDate);
		}
        model.addAttribute("datasets", auditDataSetService.selectDataSetList(criteria));
        criteria.setTotalCount(auditDataSetService.selectDataSetCount(criteria));
        model.addAttribute("pages", criteria);
        return "audit/auditDataSet";
    }

    /**
     * 데이터 셋 상세 > 사용 테이블 목록 화면
     * @param criteria
     * @param startDate
     * @param endDate
     * @param model
     * @return
     
    @GetMapping("/dataSet/detail/{dataSetId}")
    public String dataSetDetail(@ModelAttribute Criteria criteria, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate, Model model) {
        if(!StringUtils.isEmpty(startDate)) {
			criteria.setStartDt(startDate);
		}
		if(!StringUtils.isEmpty(endDate)) {
			endDate = endDate+" 23:59:59";
			criteria.setEndDt(endDate);
		}
        model.addAttribute("logs", auditDataSetService.selectDataSetList(criteria));
        criteria.setTotalCount(auditDataSetService.selectDataSetCount(criteria));
        model.addAttribute("pages", criteria);
        return "audit/auditDataSet";
    }*/
    
	@ResponseBody
	@RequestMapping(value = "/dataSet/detail/{datasetId}/popup", method = RequestMethod.POST)
	public Map<String, Object> dataSetDetail(HttpServletRequest request, @PathVariable String datasetId) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean res = false;

		List<UsingTableModel> usingTables = auditDataSetService.selectUsingTableList(datasetId);

		if (usingTables != null) {
			res = true;
			result.put("tables", usingTables);
		}
		result.put("result", res);
		return result;
	}

    
    /**
     * 자원접근이력
     * @param criteria
     * @param startDate
     * @param endDate
     * @param model
     * @return
     */
    @GetMapping("/extrnlLog")
    public String extrnlLog(@ModelAttribute Criteria criteria, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate, Model model) {
//    	if(!StringUtils.isEmpty(startDate)) {
//			criteria.setStartDt(startDate);
//		}
//		if(!StringUtils.isEmpty(endDate)) {
//			endDate = endDate+" 23:59:59";
//			criteria.setEndDt(endDate);
//		}
//        model.addAttribute("logs", logService.selectList(criteria));
//        criteria.setTotalCount(logService.selectListCount(criteria));
//        model.addAttribute("pages", criteria);
        return "audit/extrnlLog";
    }

/**
 * 야간사용관리
 * @param criteria
 * @param startDate
 * @param endDate
 * @param model
 * @return
 */
    @GetMapping("/nightUsingAprv")
    public String nightUsingAprv(@ModelAttribute Criteria criteria, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate, Model model) {
//    	if(!StringUtils.isEmpty(startDate)) {
//			criteria.setStartDt(startDate);
//		}
//		if(!StringUtils.isEmpty(endDate)) {
//			endDate = endDate+" 23:59:59";
//			criteria.setEndDt(endDate);
//		}
//        model.addAttribute("logs", logService.selectList(criteria));
//        criteria.setTotalCount(logService.selectListCount(criteria));
//        model.addAttribute("pages", criteria);
        return "audit/nightUsingAprv";
    }

    //사용자 로그 관리
    @GetMapping("/userLog")
    public String userLog(@ModelAttribute Criteria criteria, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate, Model model) {
    	if(!StringUtils.isEmpty(startDate)) {
			criteria.setStartDt(startDate);
		}
		if(!StringUtils.isEmpty(endDate)) {
			endDate = endDate+" 23:59:59";
			criteria.setEndDt(endDate);
		}
        model.addAttribute("logs", logService.selectUserLogList(criteria));
        criteria.setTotalCount(logService.selectUserLogCount(criteria));
        model.addAttribute("pages", criteria);
        return "audit/userLog";
    }

/**
 * 관리자 로그 관리
 * @param criteria
 * @param startDate
 * @param endDate
 * @param model
 * @return
 */
    @GetMapping("/mgrLog")
    public String mgrLog(@ModelAttribute Criteria criteria, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate, Model model) {
    	if(!StringUtils.isEmpty(startDate)) {
			criteria.setStartDt(startDate);
		}
		if(!StringUtils.isEmpty(endDate)) {
			endDate = endDate+" 23:59:59";
			criteria.setEndDt(endDate);
		}
        model.addAttribute("logs", logService.selectMgrLogList(criteria));
        criteria.setTotalCount(logService.selectMgrLogCount(criteria));
        model.addAttribute("pages", criteria);
        return "audit/mgrLog";
    }

}
