package com.bdp.ap.app.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdp.ap.app.system.service.ReportService;
import com.bdp.ap.config.security.AuthUser;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.system.model.ReportModel;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/system")
@Controller
@Slf4j
public class ReportController {
	
	@Resource
	ReportService reportService;
	
	@Resource
	CodeService codeService;

	//보고서 조회 (목록형)
    @GetMapping("/report")
    public String reportListType(@ModelAttribute ReportModel reportModel, Model model, @AuthenticationPrincipal AuthUser authUser) {
    	reportModel.setUserId(authUser.getMemberModel().getUserId());
        model.addAttribute("reportList", reportService.selectReportListType(reportModel));
        reportModel.setTotalCount(reportService.selectReportListTypeCount(reportModel));
        model.addAttribute("pages", reportModel);

        model.addAttribute("reportTyList", codeService.selectGroupIdAllList("RPT_TY"));

        return "system/reportList";
    }

    //보고서 상세화면
    @GetMapping("/report/detail/{reportId}")
    public String detail(@PathVariable String reportId, Model model) {
        ReportModel reportModel = new ReportModel();
        reportModel.setReportId(reportId);
        model.addAttribute("reportInfo", reportService.selectReport(reportModel));
        
        return "system/reportDetail";
    }

    //보고서 조회 (팝업)
    @RequestMapping(value="/report/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectReportList(@ModelAttribute ReportModel reportModel, Model model, @AuthenticationPrincipal AuthUser authUser){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false; 
    	List<ReportModel> modelList=reportService.selectReportListType(reportModel);
    	
    	if(modelList !=null && modelList.size()>0) {
    		res = true;
    		result.put("reportInfoList", modelList); 
    	}
    	result.put("result", res);
    	
    	return result;
    }
    
}