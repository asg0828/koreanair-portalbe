package com.bdp.ap.app.bizmeta;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
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

import com.bdp.ap.app.bizmeta.model.BizmetaCriteria;
import com.bdp.ap.app.bizmeta.model.ReportInfoModel;
import com.bdp.ap.app.bizmeta.service.BizmetaComService;
import com.bdp.ap.app.bizmeta.service.ReportInfoService;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.dept.service.DeptService;
import com.bdp.ap.app.file.service.FileService;
import com.bdp.ap.app.member.model.MemberCriteria;
import com.bdp.ap.app.member.service.MemberService;
import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.config.props.FileProps;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/bizmeta")
@Controller
public class ReportInfoController {
	
	@Resource(name = "fileProps")
	private FileProps fileProps;
	
	@Resource(name="commonUtil")
	private CommonUtil commonUtil;	
	
	@Resource
    private IdUtil idUtil;
	
	@Resource
	private CodeService codeService;
	
	@Resource
	private FileService fileService;
    
    @Resource
    private DeptService deptService;
	
    @Resource
    private ReportInfoService reportInfoService;
    
    @Resource
    private MemberService memberService;
    
    @Resource
    private BizmetaComService bizmetaComService;
    
    @GetMapping("/reportInfo")
    public String reportInfoList(@AuthenticationPrincipal AuthUser authUser,@ModelAttribute BizmetaCriteria criteria, Model model) {
    
    	model.addAttribute("ReportClassCdList", codeService.selectGroupIdAllList("REPORT_CLASS_CD"));   //보고서구분 
    	model.addAttribute("OpenYnlist", codeService.selectGroupIdAllList("OPEN_YN"));   //공개여부
    	model.addAttribute("companyCodeList", codeService.selectGroupIdAllList("COMPANY_CODE"));
    	model.addAttribute("reportInfoList",reportInfoService.selectReportInfoList(criteria));
    	criteria.setTotalCount(reportInfoService.selectReportInfoCount(criteria));
    	 
    	model.addAttribute("pages", criteria);
    	
        return "bizmeta/reportInfo/reportInfo";
    }
    
    @PostMapping("/reportInfo")
    public String reportInfo(@AuthenticationPrincipal AuthUser authUser,@ModelAttribute BizmetaCriteria criteria, RedirectAttributes attributes, Model model) {
    	model.addAttribute("ReportClassCdList", codeService.selectGroupIdAllList("REPORT_CLASS_CD"));   //보고서구분 
    	model.addAttribute("OpenYnlist", codeService.selectGroupIdAllList("OPEN_YN"));   //공개여부
    	
    	model.addAttribute("reportInfoList",reportInfoService.selectReportInfoList(criteria));
    	criteria.setTotalCount(reportInfoService.selectReportInfoCount(criteria));
    	 
    	
    	model.addAttribute("pages", criteria);
    	
        return "bizmeta/reportInfo/reportInfo";
    }
    
    @GetMapping("/reportInfo/regist")
    public String reportInfoRegist(@ModelAttribute ReportInfoModel reportInfoModel, @AuthenticationPrincipal AuthUser authUser, @ModelAttribute MemberCriteria criteria, Model model) {
    	model.addAttribute("ReportClassCdList", codeService.selectGroupIdAllList("REPORT_CLASS_CD"));   //보고서구분
    	model.addAttribute("TopicAreaCdList", codeService.selectGroupIdAllList("TOPIC_AREA_CD"));  //주제영역
    	model.addAttribute("UseCycleCdList", codeService.selectGroupIdAllList("USE_CYCLE_CD"));		//활용주기
    	model.addAttribute("OpenYnlist", codeService.selectGroupIdAllList("OPEN_YN"));   //공개여부
    	
    	model.addAttribute("mngUserNm",authUser.getMemberModel().getUserNm());
    	model.addAttribute("regId",authUser.getMemberModel().getUserId());
    	model.addAttribute("companyCodeList", codeService.selectGroupIdAllList("COMPANY_CODE")); 

		model.addAttribute("pages", criteria);
    	
    	return "bizmeta/reportInfo/reportInfoRegist";
    }
    
    @PostMapping("/reportInfo/insert")
    public String reportInfoInsert(@ModelAttribute ReportInfoModel reportInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
    	reportInfo.setUserId(authUser.getMemberModel().getUserId());
    	reportInfo.setRgstId(authUser.getMemberModel().getUserId());
    	reportInfo.setModiId(authUser.getMemberModel().getUserId());
    	reportInfo.setRptInfId(idUtil.getRptInfId());
    	
    	reportInfoService.insertReportInfo(reportInfo);
    	    	
    	return "redirect:/bizmeta/reportInfo";
    }
    
    @PostMapping("/reportInfo/update")
    public String reportInfoUpdate(@ModelAttribute ReportInfoModel reportInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
    	reportInfo.setUserId(authUser.getMemberModel().getUserId());
    	reportInfo.setRgstId(authUser.getMemberModel().getUserId());
    	reportInfo.setModiId(authUser.getMemberModel().getUserId());
    	
    	reportInfoService.updateReportInfo(reportInfo);
    	
    	return "redirect:/bizmeta/reportInfo";
    }
    
    @GetMapping("/reportInfo/modify/{rptInfId}")
    public String reportInfoModify(@PathVariable String rptInfId,@AuthenticationPrincipal AuthUser authUser,@ModelAttribute ReportInfoModel reportInfo, @ModelAttribute BizmetaCriteria criteria, Model model) {
    	
    	model.addAttribute("ReportClassCdList", codeService.selectGroupIdAllList("REPORT_CLASS_CD"));   //보고서구분
    	model.addAttribute("TopicAreaCdList", codeService.selectGroupIdAllList("TOPIC_AREA_CD"));  //주제영역
    	model.addAttribute("UseCycleCdList", codeService.selectGroupIdAllList("USE_CYCLE_CD"));		//활용주기
    	model.addAttribute("OpenYnlist", codeService.selectGroupIdAllList("OPEN_YN"));   //공개여부
    	model.addAttribute("companyCodeList", codeService.selectGroupIdAllList("COMPANY_CODE"));
//    	model.addAttribute("deptList", deptService.selectDeptClList());
//    	MemberCriteria memberCriteria = new MemberCriteria();
//    	
//    	memberCriteria.setSearchCompanyCode(authUser.getMemberModel().getCompanyCode());
//    	model.addAttribute("memberList", bizmetaComService.selectMemberList(memberCriteria));    	
    	criteria.setSeId(rptInfId);
    	
    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) ) {
    		model.addAttribute("dimList",bizmetaComService.selectDimensiont(criteria));						//주요조회조건=디멘젼
        	model.addAttribute("mesList",bizmetaComService.selectMeasure(criteria));							//주요항목=메전	
        	model.addAttribute("dataMart",bizmetaComService.selectDataMart(criteria));						//데이터원천    	
        	model.addAttribute("fndTagList",bizmetaComService.selectFindTag(criteria));						//검색태그
    	}
    	
    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId())) {
    		criteria.setDeptSe("01");
        	model.addAttribute("mngDeptList",bizmetaComService.selectManageDept(criteria));			//관리부서
        	criteria.setDeptSe("02");
        	model.addAttribute("avoidDeptList",bizmetaComService.selectManageDept(criteria));			//기피부서        	
    	}
    	   	
    	model.addAttribute("reportInfoDetail", reportInfoService.selectReportInfoDetail(criteria));	//보고서상세항목

		model.addAttribute("pages", criteria);
    	
    	return "bizmeta/reportInfo/reportInfoRegist";
    }
    
    @GetMapping("/reportInfo/detail/{rptInfId}")
    public String selectReportInfoDetail(@PathVariable String rptInfId,@ModelAttribute BizmetaCriteria criteria, Model model) {
    	
    	criteria.setSeId(rptInfId);
    	
    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) ) {
    		model.addAttribute("dimList",bizmetaComService.selectDimensiont(criteria));						//주요조회조건=디멘젼
        	model.addAttribute("mesList",bizmetaComService.selectMeasure(criteria));							//주요항목=메전	
        	model.addAttribute("dataMart",bizmetaComService.selectDataMart(criteria));						//데이터원천    	
        	model.addAttribute("fndTagList",bizmetaComService.selectFindTag(criteria));						//검색태그
    	}
    	
    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId())) {
    		criteria.setDeptSe("01");
        	model.addAttribute("mngDeptList",bizmetaComService.selectManageDept(criteria));			//관리부서
        	criteria.setDeptSe("02");
        	model.addAttribute("avoidDeptList",bizmetaComService.selectManageDept(criteria));			//기피부서        	
    	}
    	model.addAttribute("reportInfoDetail", reportInfoService.selectReportInfoDetail(criteria));

		model.addAttribute("pages", criteria);
    	
    	return "bizmeta/reportInfo/reportInfoDetail";
    }
    
    @PostMapping("/reportInfo/delete")
    public String reportInfoDelete(@ModelAttribute ReportInfoModel reportInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
    	reportInfo.setUserId(authUser.getMemberModel().getUserId());
    	reportInfo.setRgstId(authUser.getMemberModel().getUserId());
    	reportInfo.setModiId(authUser.getMemberModel().getUserId());
    	
    	reportInfoService.deleteReportInfo(reportInfo);
    	
    	return "redirect:/bizmeta/reportInfo";
    }
    
    /**
     * 보고서 정보 엑셀 다운로드
     * @param request
     * @param response
     * @param criteria
     * @param model
     * @return
     */
    @GetMapping("/reportInfo/excel/download")
    public ResponseEntity<ByteArrayResource> excelDownload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BizmetaCriteria criteria, Model model) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cl = Calendar.getInstance();
		
		String[] headers = {"NO","보고서구분","보고서명","주제영역","활용주기","등록자","등록일","공개여부"};
		String[] keys = {"","rptClNm","rptNm","topicAreaNm","usCycNm","regNm","rgstDt", "opnYn"};
		String fileNm = sdf.format(cl.getTime())+"_reportInfo.xlsx";

    	List<ReportInfoModel> reportInfoList = reportInfoService.selectReportInfoExcel(criteria);
		
		ResponseEntity<ByteArrayResource> result = fileService.downloadExcel(headers, keys, new JSONArray(reportInfoList), fileNm);
		return result;
	}

	/*보고서 정보 등록시 중복확인*/
	@RequestMapping(value="/reportInfo/rptCdCount/ajax", method=RequestMethod.POST)
    @ResponseBody
    public int rptCdCount(@AuthenticationPrincipal AuthUser authUser,@ModelAttribute BizmetaCriteria criteria, Model model){
    	
    	int rptCdCount = reportInfoService.selectReportInfoCount(criteria);
    	
    	return rptCdCount;
    }
    
}
