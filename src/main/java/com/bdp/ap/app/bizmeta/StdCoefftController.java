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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdp.ap.app.bizmeta.model.AnalysisMartModel;
import com.bdp.ap.app.bizmeta.model.BizMetaFileModel;
import com.bdp.ap.app.bizmeta.model.BizmetaCriteria;
import com.bdp.ap.app.bizmeta.model.BizmetaModel;
import com.bdp.ap.app.bizmeta.model.RptModel;
import com.bdp.ap.app.bizmeta.service.BizmetaAnalysisMartService;
import com.bdp.ap.app.bizmeta.service.BizmetaComService;
import com.bdp.ap.app.bizmeta.service.StdCoefftService;
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

/**
 * Bizmeta / 컨트롤러
 */
@Slf4j
@RequestMapping("/bizmeta")
@Controller
public class StdCoefftController {
	
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
    private StdCoefftService stdCoefftService;
    
    @Resource
    private MemberService memberService;
    
    @Resource
    private BizmetaComService bizmetaComService;
    
    @Resource
    private BizmetaAnalysisMartService bizmetaAnalysisMartService;
    
    /**
     * 비즈메타 메인 메뉴
     * @param authUser
     * @param criteria
     * @param model
     * @return
     */
    @GetMapping("/m")
    public String mainlist(@AuthenticationPrincipal AuthUser authUser,@ModelAttribute BizmetaCriteria criteria, Model model) {
        return "redirect:/bizmeta/stdCoefft";
    }
    
    /**
     * 표준계수 조회
     * @param authUser
     * @param criteria
     * @param model
     * @return
     */
    @GetMapping("/stdCoefft")
    public String stdCoefftList(@AuthenticationPrincipal AuthUser authUser,@ModelAttribute BizmetaCriteria criteria, Model model) {
    	
    	model.addAttribute("stdCoeffSeCdList", codeService.selectGroupIdAllList("STD_COEFFT_SE_CD")); 
    	model.addAttribute("companyCodeList", codeService.selectGroupIdAllList("COMPANY_CODE"));
    	model.addAttribute("stdCoeffList",stdCoefftService.selectStdCoefftList(criteria));
    	criteria.setTotalCount(stdCoefftService.selectStdCoefftListCount(criteria));
    	
    	model.addAttribute("pages", criteria);
    	
        return "bizmeta/stdCoefft/stdcoefft";
    }
    
    /**
     * 표준계수 조회
     * @param authUser
     * @param criteria
     * @param attributes
     * @param model
     * @return
     */
    @PostMapping("/stdCoefft")
    public String stdCoefft(@AuthenticationPrincipal AuthUser authUser,@ModelAttribute BizmetaCriteria criteria, RedirectAttributes attributes, Model model) {
    	
    	model.addAttribute("stdCoeffSeCdList", codeService.selectGroupIdAllList("STD_COEFFT_SE_CD"));
    	model.addAttribute("companyCodeList", codeService.selectGroupIdAllList("COMPANY_CODE"));
    	model.addAttribute("stdCoeffList",stdCoefftService.selectStdCoefftList(criteria));
    	criteria.setTotalCount(stdCoefftService.selectStdCoefftListCount(criteria));
    	    	    	     	    	
    	model.addAttribute("pages", criteria);
    	
        return "bizmeta/stdCoefft/stdcoefft";
    }
    
    /**
     * 표준계수 등록 화면
     * @param stdCoefftModel
     * @param criteria
     * @param model
     * @return
     */
    @GetMapping("/stdCoefft/regist")
    public String stdCoefftRegist(@ModelAttribute BizmetaModel stdCoefftModel, @ModelAttribute MemberCriteria criteria, Model model) {
    	
    	model.addAttribute("stdCoeffSeCdList", codeService.selectGroupIdAllList("STD_COEFFT_SE_CD"));
    	model.addAttribute("stdCoeffCtgyCdList", codeService.selectGroupIdAllList("STD_COEFFT_CTGY_CD"));
    	model.addAttribute("mesCycCdList", codeService.selectGroupIdAllList("MES_CYC_CD"));
    	model.addAttribute("typeGbnCdList", codeService.selectGroupIdAllList("TYPE_GBN_CD"));
    	model.addAttribute("calcUntCdList", codeService.selectGroupIdAllList("CALC_UNT_CD"));
    	
    	model.addAttribute("initData","01");  //표준계수구분 초기값세팅
    	model.addAttribute("companyCodeList", codeService.selectGroupIdAllList("COMPANY_CODE"));    	

		model.addAttribute("pages", criteria);
    	return "bizmeta/stdCoefft/stdcoefftRegist";
    }
    
    /**
     * 표준계수 신규 등록
     * @param stdCoefftInfo
     * @param authUser
     * @param model
     * @return
     */
    @PostMapping("/stdCoefft/insert")
    public String stdCoefftInsert(@ModelAttribute BizmetaModel stdCoefftInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
    	stdCoefftInfo.setUserId(authUser.getMemberModel().getUserId());
    	stdCoefftInfo.setRgstId(authUser.getMemberModel().getUserId());
    	stdCoefftInfo.setModiId(authUser.getMemberModel().getUserId());
    	stdCoefftInfo.setStdCoefftId(idUtil.getStdCoefftId());
    	stdCoefftInfo.setSeq(0);
    	
    	stdCoefftService.insertStdCoefft(stdCoefftInfo);
    	    	    	
    	return "redirect:/bizmeta/stdCoefft";
    }
    
    /**
     * 표준계수 수정
     * @param stdCoefftInfo
     * @param authUser
     * @param model
     * @return
     */
    @PostMapping("/stdCoefft/update")
    public String stdCoefftUpdate(@ModelAttribute BizmetaModel stdCoefftInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
    	stdCoefftInfo.setUserId(authUser.getMemberModel().getUserId());
    	stdCoefftInfo.setRgstId(authUser.getMemberModel().getUserId());
    	stdCoefftInfo.setModiId(authUser.getMemberModel().getUserId());
    	
    	stdCoefftService.updateStdCoefft(stdCoefftInfo);
    	
    	return "redirect:/bizmeta/stdCoefft";
    }
    
    /**
     * 표준계수 수정 화면
     * @param stdCoefftId
     * @param refVer
     * @param authUser
     * @param stdCoefftModel
     * @param criteria
     * @param model
     * @return
     */
    @GetMapping("/stdCoefft/modify/{stdCoefftId}/{refVer}")
    public String stdCoefftModify(@PathVariable String stdCoefftId,@PathVariable int refVer,@AuthenticationPrincipal AuthUser authUser,@ModelAttribute BizmetaModel stdCoefftModel, @ModelAttribute BizmetaCriteria criteria, Model model) {
    	
    	model.addAttribute("stdCoeffSeCdList", codeService.selectGroupIdAllList("STD_COEFFT_SE_CD"));
    	model.addAttribute("stdCoeffCtgyCdList", codeService.selectGroupIdAllList("STD_COEFFT_CTGY_CD"));
    	model.addAttribute("mesCycCdList", codeService.selectGroupIdAllList("MES_CYC_CD"));
    	model.addAttribute("typeGbnCdList", codeService.selectGroupIdAllList("TYPE_GBN_CD"));
    	model.addAttribute("calcUntCdList", codeService.selectGroupIdAllList("CALC_UNT_CD"));
    	model.addAttribute("companyCodeList", codeService.selectGroupIdAllList("COMPANY_CODE"));
//    	model.addAttribute("deptList", deptService.selectDeptClList());
//    	MemberCriteria memberCriteria = new MemberCriteria();    	
//    	memberCriteria.setSearchCompanyCode(authUser.getMemberModel().getCompanyCode());
//    	model.addAttribute("memberList", bizmetaComService.selectMemberList(memberCriteria));
    	criteria.setSeId(stdCoefftId);
    	criteria.setRefVer(refVer);
    	criteria.setDeptSe("01");
    	    	
    	//파일 조회
    	BizMetaFileModel f = new BizMetaFileModel();
    	f.setFileSeId(criteria.getSeId());
    	f.setRefVer(criteria.getRefVer());
    	
		List<FileModel> fileList =  bizmetaComService.selectBizMetaFileList(f);                     
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
			obj.put("fileUrl", fileProps.getUploadServer() + fm.getSavePath() + fm.getSaveFileNm());
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
    	model.addAttribute("fileJsonList", files);
    	
    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) ) {
    		model.addAttribute("rptList",bizmetaComService.selectRptListBizmeta(criteria));	 	//보고서
    	}
    	
    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) ) {
    		model.addAttribute("mngUserList",bizmetaComService.selectManageUser(criteria));	 	//관리담당자
    	}
    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) 
        		&& criteria.getDeptSe() !=null && !"".equals(criteria.getDeptSe())) {
    		model.addAttribute("deptCdList",bizmetaComService.selectManageDept(criteria));	//관리부서
    	}
    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) ) {
    		model.addAttribute("fndTagList",bizmetaComService.selectFindTag(criteria));				//검색태그
    	}
    	model.addAttribute("stdcoefftDetail", stdCoefftService.selectStdCoefftDetail(criteria));   //표준계수상세항목

		model.addAttribute("pages", criteria);
    	
    	return "bizmeta/stdCoefft/stdcoefftRegist";
    }
    
    /**
     * 표준계수 상세 화면
     * @param stdCoefftId
     * @param refVer
     * @param criteria
     * @param model
     * @return
     */
    @GetMapping("/stdCoefft/detail/{stdCoefftId}/{refVer}")
    public String selectStdCoefftDetail(@PathVariable String stdCoefftId,@PathVariable int refVer,@ModelAttribute BizmetaCriteria criteria, Model model) {
    	
    	criteria.setSeId(stdCoefftId);
    	criteria.setRefVer(refVer);
    	model.addAttribute("stdcoefftDetail", stdCoefftService.selectStdCoefftDetail(criteria));
    	     	
    	//파일 조회 
    	BizMetaFileModel f = new BizMetaFileModel();
    	f.setFileSeId(criteria.getSeId());
    	f.setRefVer(criteria.getRefVer());    	
		List<FileModel> fileList =  bizmetaComService.selectBizMetaFileList(f);   
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
			obj.put("fileUrl", fileProps.getUploadServer() + fm.getSavePath() + fm.getSaveFileNm());
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
		model.addAttribute("fileJsonList", files);
    	
    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) ) {
    		model.addAttribute("rptList",bizmetaComService.selectRptListBizmeta(criteria));	 	//보고서
    	}
    	     	
    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) ) {
    		model.addAttribute("mngUserList",bizmetaComService.selectManageUser(criteria));	 	//관리담당자
    	}
    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) 
        		&& criteria.getDeptSe() !=null && !"".equals(criteria.getDeptSe())) {
    		model.addAttribute("deptCdList",bizmetaComService.selectManageDept(criteria));	//관리부서
    	}
    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) ) {
    		model.addAttribute("fndTagList",bizmetaComService.selectFindTag(criteria));				//검색태그
    	}
    	
		model.addAttribute("pages", criteria);

    	return "bizmeta/stdCoefft/stdcoefftDetail";
    }
    
    /**
     * 표준계수 삭제
     * @param stdCoefftInfo
     * @param authUser
     * @param model
     * @return
     */
    @PostMapping("/stdCoefft/delete")
    public String stdCoefftDelete(@ModelAttribute BizmetaModel stdCoefftInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
    	stdCoefftInfo.setUserId(authUser.getMemberModel().getUserId());
    	stdCoefftInfo.setModiId(authUser.getMemberModel().getUserId());
    	
    	stdCoefftService.deleteStdCoefft(stdCoefftInfo);
    	
    	return "redirect:/bizmeta/stdCoefft";
    }
    
    /**
     * 표준계수명 중복확인 
     * @param stdCoefftInfo
     * @param model
     * @return
     */
    @RequestMapping(value="/stdCoefft/stdCoefftNmOverLap/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object>selectStdCoefftNmOverLap(@ModelAttribute BizmetaCriteria stdCoefftInfo, Model model) {
    	Map<String,Object> result = new HashMap<String, Object>();
    	
    	 
    	result.put("stdCoefftNmOverLap",stdCoefftService.selectStdCoefftNmCount(stdCoefftInfo));
    	result.put("result", true);
    	
    	return result;
    };
    
    /**
     * 표준계수명 리스트
     * @param stdCoefftInfo
     * @param model
     * @return
     */
    @RequestMapping(value="/stdCoefft/stdCoefftNmList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object>selectStdCoefftNmOverLapList(@ModelAttribute BizmetaCriteria stdCoefftInfo, Model model) {
    	Map<String,Object> result = new HashMap<String, Object>();
    	
    	result.put("stdCoefftNmList",stdCoefftService.selectStdCoefftNmList(stdCoefftInfo));
    	result.put("stdCoefftNmCount",stdCoefftService.selectStdCoefftNmCount(stdCoefftInfo));
    	result.put("result", true);
    	
    	return result;
    };
    
    /**
     * 표준계수명 부서표준계수조회
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/stdCoefft/stdCoefftDeptList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectStdCoefftDeptList(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false;
    	
    	List<BizmetaModel>bizmetaModel= stdCoefftService.selectStdCoefftDeptList(criteria);
    	if(bizmetaModel != null && !bizmetaModel.isEmpty()) {
    		res = true;
    		result.put("stdCoefftDeptList", bizmetaModel);
    		result.put("stdCoefftDeptCount", stdCoefftService.selectStdCoefftDeptCount(criteria)); //표준계수명 부서표준계수조회 총개수
    	}
    	
    	result.put("result", res);
    	
    	return result;
    }
    
    /**
     * 보고서 선택 조회 
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/stdCoefft/rptList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectRptList(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false;
    	    	
    	List<RptModel>rptModel =stdCoefftService.selectRptList(criteria);
    	if(rptModel != null) {
    		res = true;
    		result.put("rptList", rptModel);
    	}
    	
    	result.put("result", res);
    	
    	return result;
    }
    
    /**
     * 분석마트 선택 조회 
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/stdCoefft/analysisMartList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectAnalysisMartList(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false;
    	
    	List<AnalysisMartModel>analysisMartMode =  stdCoefftService.selectAnalysisMartList(criteria);
    	if(analysisMartMode !=null && analysisMartMode.size() != 0) {
    		res = true;
    		result.put("analysisMartList", analysisMartMode);
    	}
    	result.put("result", res);
    	
    	return result;
    }
    
    /**
     * 분석마트 선택 조회 
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/stdCoefft/analysisMartDetail/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectAnalysisMarDetail(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false;
    	
    	if (criteria.getAnlzMrtId() == null || "".equals(criteria.getAnlzMrtId() )) {
    		result.put("result", res);
    		return result;
    	}
    	criteria.setSeId(criteria.getAnlzMrtId());
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
			obj.put("fileUrl", fileProps.getUploadServer() + fm.getSavePath() +fm.getSaveFileNm());
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
		model.addAttribute("fileJsonList", files);
    	
    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) ) {
    		result.put("keyItmList",bizmetaComService.selectKeyItem(criteria));						//Key항목
    		result.put("dimList",bizmetaComService.selectDimensiont(criteria));						//주요조회조건=디멘젼
    		result.put("mesList",bizmetaComService.selectMeasure(criteria));							//주요항목=메전
    		result.put("dataMart",bizmetaComService.selectDataMart(criteria));						//데이터원천
    		result.put("fndTagList",bizmetaComService.selectFindTag(criteria));						//검색태그
    	}

    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId())) {
    		criteria.setDeptSe("01");
    		result.put("mngDeptList",bizmetaComService.selectManageDept(criteria));			//관리부서
        	criteria.setDeptSe("02");
        	result.put("avoidDeptList",bizmetaComService.selectManageDept(criteria));			//기피부서
    	}
    	
    	AnalysisMartModel  analysisMartMode =  bizmetaAnalysisMartService.selectAnalysisMartDetail(criteria);	//분석마트상세항목
    	if(analysisMartMode !=null) {
    		res = true;
    		result.put("analysisMartDetail", analysisMartMode);
    	}
    	result.put("result", res);
    	
    	return result;
    }
    
    /**
     * 표준계수이력조회 
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/stdCoefft/stdCoefftHistList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectStdCoefftHistList(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false;
    	
    	List<BizmetaModel> bizmetaModel = stdCoefftService.selectStdCoefftHistList(criteria);
    	criteria.setTotalCount(stdCoefftService.selectStdCoefftHistListCount(criteria));
    	
    	if(bizmetaModel !=null && bizmetaModel.size()>0) {
    		res = true;
    		result.put("stdCoefftHistList", bizmetaModel);    		
    		criteria.setRefVer(bizmetaModel.get(0).getSeq());
    		result.put("stdCoefftHist", stdCoefftService.selectStdCoefftHist(criteria));
    	}
    	result.put("result", res);
    	result.put("pages", criteria);
    	return result;
    }
    
    /**
     * 표준계수이력상세
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/stdCoefft/stdCoefftHist/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectStdCoefftHist(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false;
    	
    	BizmetaModel bizmetaModel = stdCoefftService.selectStdCoefftHist(criteria);
    	if(bizmetaModel !=null) {
    		 	    	
	    	BizMetaFileModel f = new BizMetaFileModel();
	    	f.setFileSeId(criteria.getSeId());
	    	f.setRefVer(criteria.getRefVer());
	    	
			List<FileModel> fileList =  bizmetaComService.selectBizMetaFileList(f);           
	    	
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
				obj.put("fileUrl", fileProps.getUploadServer() + fm.getSavePath() + fm.getSaveFileNm());
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
	    	model.addAttribute("fileJsonList", files);
    		res = true;
    		result.put("stdCoefftHist", bizmetaModel);
    	}
    	result.put("result", res);
    	
    	return result;
    }
    
    /**
     * 표준계수 부서 상세조회
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/stdCoefft/stdCoefftDetailPop/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectStdCoefftDetailPop(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false;    	
    	
    	BizmetaModel  bizmetaModel = stdCoefftService.selectStdCoefftDetail(criteria);
    	if(bizmetaModel !=null) {
    		res = true;
    		result.put("stdcoefftDetail", bizmetaModel);
    		
    		FileModel fileModel = new FileModel();
	    	fileModel.setRefId(criteria.getSeId());
	    	fileModel.setDelYn("N");
	    	
	    		    	
	    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) ) {
	    		result.put("fileList",fileService.selectFileList(fileModel));									//첨부파일 
	    		result.put("mngUserList",bizmetaComService.selectManageUser(criteria));	 	//관리담당자
	    	}
	    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) 
	        		&& criteria.getDeptSe() !=null && !"".equals(criteria.getDeptSe())) {
	    		result.put("mngDeptList",bizmetaComService.selectManageDept(criteria));	//관리부서
	    	}
	    	if (criteria.getSeId()!=null && !"".equals(criteria.getSeId()) ) {
	    		result.put("fndTagList",bizmetaComService.selectFindTag(criteria));				//검색태그
	    	}
    		
    	}
    	result.put("result", res);
    	
    	return result;
    }
    
    /**
     * 표준계수 엑셀 다운로드
     * @param request
     * @param response
     * @param criteria
     * @param model
     * @return
     */
    @GetMapping("/stdCoefft/excel/download")
    public ResponseEntity<ByteArrayResource> excelDownload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BizmetaCriteria criteria, Model model) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cl = Calendar.getInstance();
		
		String[] headers = {"NO","표준계수구분","표준계수명","표준계수수산식","설명","산출주기","담당자","등록일"};
		String[] keys = {"","stdCoefftSeNm","stdCoefftNm","stdCoefftFm","stdCoefftDef","mesCycNm","mngUserNm", "rgstDt"};
		String fileNm = sdf.format(cl.getTime())+"_stdCoefft.xlsx";

    	List<BizmetaModel> selectStdCoefftList = stdCoefftService.selectStdCoefftExcel(criteria);
		
		ResponseEntity<ByteArrayResource> result = fileService.downloadExcel(headers, keys, new JSONArray(selectStdCoefftList), fileNm);
		return result;
	}
    
    
}
