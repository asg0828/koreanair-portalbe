package com.bdp.ap.app.bizmeta;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdp.ap.app.bizmeta.model.AnalysisMartModel;
import com.bdp.ap.app.bizmeta.model.BizmetaCriteria;
import com.bdp.ap.app.bizmeta.service.BizmetaAnalysisMartService;
import com.bdp.ap.app.bizmeta.service.BizmetaComService;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.dept.service.DeptService;
import com.bdp.ap.app.file.model.FileModel;
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
public class AnalysisMartController {
	
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
    private MemberService memberService;

    @Resource
    private BizmetaComService bizmetaComService;

    @Resource
    private BizmetaAnalysisMartService bizmetaAnalysisMartService;

    
    @GetMapping("/analysisMart")
    public String AnalysisMartList(@AuthenticationPrincipal AuthUser authUser,@ModelAttribute BizmetaCriteria criteria, Model model) {

    	model.addAttribute("TopicAreaCdlist", codeService.selectGroupIdAllList("TOPIC_AREA_CD"));   //주제영역
    	model.addAttribute("OpenYnlist", codeService.selectGroupIdAllList("OPEN_YN"));   //공개여부
    	model.addAttribute("companyCodeList", codeService.selectGroupIdAllList("COMPANY_CODE"));

    	model.addAttribute("analysisMartList",bizmetaAnalysisMartService.selectAnalysisMartList(criteria));
    	criteria.setTotalCount(bizmetaAnalysisMartService.selectAnalysisMartCount(criteria));
 
    	model.addAttribute("pages", criteria);

        return "bizmeta/analysisMart/analysisMart";

    }

    @PostMapping("/analysisMart")
    public String analysisMart(@AuthenticationPrincipal AuthUser authUser,@ModelAttribute BizmetaCriteria criteria, RedirectAttributes attributes, Model model) {
    	model.addAttribute("TopicAreaCdlist", codeService.selectGroupIdAllList("TOPIC_AREA_CD"));   //주제영역
    	model.addAttribute("OpenYnlist", codeService.selectGroupIdAllList("OPEN_YN"));   //공개여부
    	model.addAttribute("companyCodeList", codeService.selectGroupIdAllList("COMPANY_CODE"));
    	model.addAttribute("analysisMartList",bizmetaAnalysisMartService.selectAnalysisMartList(criteria));
    	criteria.setTotalCount(bizmetaAnalysisMartService.selectAnalysisMartCount(criteria));
 
    	model.addAttribute("pages", criteria);

        return "bizmeta/analysisMart/analysisMart";
    }

    @GetMapping("/analysisMart/regist")
    public String analysisMartRegist(@ModelAttribute AnalysisMartModel analysisMartModel, @AuthenticationPrincipal AuthUser authUser, @ModelAttribute MemberCriteria criteria, Model model) {
    	model.addAttribute("TopicAreaCdlist", codeService.selectGroupIdAllList("TOPIC_AREA_CD"));   //주제영역
    	model.addAttribute("LoadCycCdlist", codeService.selectGroupIdAllList("LOAD_CYC_CD"));   //적재주기
    	model.addAttribute("OpenYnlist", codeService.selectGroupIdAllList("OPEN_YN"));   //공개여부
    	
    	model.addAttribute("mngUserNm",authUser.getMemberModel().getUserNm());
    	model.addAttribute("regId",authUser.getMemberModel().getUserId());
    	model.addAttribute("companyCodeList", codeService.selectGroupIdAllList("COMPANY_CODE")); 

		model.addAttribute("pages", criteria);
    	
    	return "bizmeta/analysisMart/analysisMartRegist";
    }

    @PostMapping("/analysisMart/insert")
    public String analysisMartRegistInsert(@ModelAttribute AnalysisMartModel analysisMart, @AuthenticationPrincipal AuthUser authUser, Model model) {
    	analysisMart.setUserId(authUser.getMemberModel().getUserId());
    	analysisMart.setRgstId(authUser.getMemberModel().getUserId());
    	analysisMart.setModiId(authUser.getMemberModel().getUserId());
    	analysisMart.setAnlzMrtId(idUtil.getAnlzmrtId());

    	bizmetaAnalysisMartService.insertAnalysisMart(analysisMart);

    	return "redirect:/bizmeta/analysisMart";
    }

    @PostMapping("/analysisMart/update")
    public String analysisMartRegistUpdate(@ModelAttribute AnalysisMartModel analysisMart, @AuthenticationPrincipal AuthUser authUser, Model model) {
    	analysisMart.setUserId(authUser.getMemberModel().getUserId());
    	analysisMart.setRgstId(authUser.getMemberModel().getUserId());
    	analysisMart.setModiId(authUser.getMemberModel().getUserId());

    	bizmetaAnalysisMartService.updateAnalysisMart(analysisMart);

    	return "redirect:/bizmeta/analysisMart";

    }

    @GetMapping("/analysisMart/modify/{anlzMrtId}")
    public String reportInfoModify(@PathVariable String anlzMrtId,@AuthenticationPrincipal AuthUser authUser,@ModelAttribute AnalysisMartModel analysisMart, @ModelAttribute BizmetaCriteria criteria, Model model) {

    	model.addAttribute("TopicAreaCdlist", codeService.selectGroupIdAllList("TOPIC_AREA_CD"));   //주제영역
    	model.addAttribute("LoadCycCdlist", codeService.selectGroupIdAllList("LOAD_CYC_CD"));   //적재주기
    	model.addAttribute("OpenYnlist", codeService.selectGroupIdAllList("OPEN_YN"));   //공개여부
    	model.addAttribute("companyCodeList", codeService.selectGroupIdAllList("COMPANY_CODE"));

		model.addAttribute("pages", criteria);
//    	model.addAttribute("deptList", deptService.selectDeptClList());
//    	MemberCriteria memberCriteria = new MemberCriteria();
//
//    	memberCriteria.setSearchCompanyCode(authUser.getMemberModel().getCompanyCode());
//    	model.addAttribute("memberList", bizmetaComService.selectMemberList(memberCriteria));

    	criteria.setSeId(anlzMrtId);
     
    	//파일 조회
		FileModel f = new FileModel();
		f.setRefId(criteria.getSeId());
		List<FileModel> fileList = fileService.selectFileList(f);
		JSONArray files = new JSONArray();
		for(FileModel fm : fileList) {
			Map obj = new HashMap();
			obj.put("fileId", fm.getFileId());
			obj.put("storageSe", fm.getStorageSe());
			obj.put("savePath", fm.getSavePath());
			obj.put("saveFileNm", fm.getSaveFileNm());
			obj.put("fileNm", fm.getFileNm());
			obj.put("fileExtsn", fm.getFileExtsn());
			obj.put("fileSize", fm.getFileSize());
			obj.put("useYn", fm.getUseYn());
			obj.put("rgstId", fm.getRgstId());
			obj.put("rgstDt", fm.getRgstDt());
			obj.put("modiId", fm.getModiId());
			obj.put("modiDt", fm.getModiDt());
			obj.put("bucketNm", fm.getBucketNm());
			obj.put("fileUrl", fileProps.getUploadServer()+fm.getSavePath()+fm.getSaveFileNm());
			obj.put("fileCl", fm.getFileCl());
			obj.put("saveFileVer", fm.getSaveFileVer());
			obj.put("inputStream", fm.getInputStream());
			obj.put("bytes", fm.getBytes());
			obj.put("modiByUserYn", fm.getModiByUserYn());
			obj.put("refId", fm.getRefId());
			obj.put("refVer", fm.getRefVer());
			files.put(obj);
		}   	
    	model.addAttribute("fileList",fileList);									//첨부파일
    	model.addAttribute("fileJsonList",files);

    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) ) {
    		model.addAttribute("keyItmList",bizmetaComService.selectKeyItem(criteria));						//Key항목
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

    	model.addAttribute("analysisMartDetail", bizmetaAnalysisMartService.selectAnalysisMartDetail(criteria));	//분석마트상세항목

    	return "bizmeta/analysisMart/analysisMartRegist";
    }

    @GetMapping("/analysisMart/detail/{anlzMrtId}")
    public String selectReportInfoDetail(@PathVariable String anlzMrtId,@ModelAttribute BizmetaCriteria criteria, Model model) {
    	criteria.setSeId(anlzMrtId);
    	//파일 조회
		FileModel f = new FileModel();
		f.setRefId(criteria.getSeId());
		List<FileModel> fileList = fileService.selectFileList(f);
		JSONArray files = new JSONArray();
		for(FileModel fm : fileList) {
			Map obj = new HashMap();
			obj.put("fileId", fm.getFileId());
			obj.put("storageSe", fm.getStorageSe());
			obj.put("savePath", fm.getSavePath());
			obj.put("saveFileNm", fm.getSaveFileNm());
			obj.put("fileNm", fm.getFileNm());
			obj.put("fileExtsn", fm.getFileExtsn());
			obj.put("fileSize", fm.getFileSize());
			obj.put("useYn", fm.getUseYn());
			obj.put("rgstId", fm.getRgstId());
			obj.put("rgstDt", fm.getRgstDt());
			obj.put("modiId", fm.getModiId());
			obj.put("modiDt", fm.getModiDt());
			obj.put("bucketNm", fm.getBucketNm());
			obj.put("fileUrl", fileProps.getUploadServer()+fm.getSavePath()+fm.getSaveFileNm());
			obj.put("fileCl", fm.getFileCl());
			obj.put("saveFileVer", fm.getSaveFileVer());
			obj.put("inputStream", fm.getInputStream());
			obj.put("bytes", fm.getBytes());
			obj.put("modiByUserYn", fm.getModiByUserYn());
			obj.put("refId", fm.getRefId());
			obj.put("refVer", fm.getRefVer());
			files.put(obj);
		}   	

    	model.addAttribute("fileList", fileList);
    	model.addAttribute("fileJsonList", files);								//첨부파일
    	
    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) ) {
    		model.addAttribute("keyItmList",bizmetaComService.selectKeyItem(criteria));						//Key항목
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

    	model.addAttribute("analysisMartDetail", bizmetaAnalysisMartService.selectAnalysisMartDetail(criteria));	//분석마트상세항목

		model.addAttribute("pages", criteria);

    	return "bizmeta/analysisMart/analysisMartDetail";
    }

    @PostMapping("/analysisMart/delete")
    public String reportInfoDelete(@ModelAttribute AnalysisMartModel analysisMart, @AuthenticationPrincipal AuthUser authUser, Model model) {
    	analysisMart.setUserId(authUser.getMemberModel().getUserId());
    	analysisMart.setRgstId(authUser.getMemberModel().getUserId());
    	analysisMart.setModiId(authUser.getMemberModel().getUserId());

    	bizmetaAnalysisMartService.deleteAnalysisMart(analysisMart);

    	
    	return "redirect:/bizmeta/analysisMart";
    }

    /**
     * 분석마트 엑셀 다운로드
     * @param request
     * @param response
     * @param criteria
     * @param model
     * @return
     */
    @GetMapping("/analysisMart/excel/download")
    public ResponseEntity<ByteArrayResource> excelDownload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BizmetaCriteria criteria, Model model) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cl = Calendar.getInstance();
		
		String[] headers = {"NO","주제영역","마트명","테이블ID","마트정의","관리부서","등록자","등록일","공개여부"};
		String[] keys = {"","topicAreaNm","martNm","tblId","def","deptNm","regNm", "rgstDt", "opnYn"};
		String fileNm = sdf.format(cl.getTime())+"_analysisMart.xlsx";

    	List<AnalysisMartModel> analysisMartList = bizmetaAnalysisMartService.selectAnalysisMartExcel(criteria);
 
		
		ResponseEntity<ByteArrayResource> result = fileService.downloadExcel(headers, keys, new JSONArray(analysisMartList), fileNm);
		return result;
	}
}
