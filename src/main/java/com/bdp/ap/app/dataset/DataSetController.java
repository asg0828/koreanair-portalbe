package com.bdp.ap.app.dataset;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
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

import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.dataset.model.DataSetCriteria;
import com.bdp.ap.app.dataset.model.DataSetModel;
import com.bdp.ap.app.dataset.service.DataSetService;
import com.bdp.ap.app.member.model.MemberCriteria;
import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/dataSet")
@Slf4j
@Controller
public class DataSetController {
	@Resource
    private IdUtil idUtil;	
	
	@Resource(name="commonUtil")
	private CommonUtil commonUtil;	
	
	@Resource
	private CodeService codeService;	
	
	@Resource
	private DataSetService dataSetService;
	
	
	/*
	 * 정형데이터셋 조회  
	 * @param criteria
	 * @param model
	 * @return
	 */
	@GetMapping("/dataSet")
	public String dataSet(@ModelAttribute DataSetCriteria criteria, Model model) {
		
		model.addAttribute("dataSetList",dataSetService.selectStructTableInfoList(criteria));
		criteria.setTotalCount(dataSetService.selectStructTableInfoListCount(criteria));
		model.addAttribute("sptGrpCdList", codeService.selectGroupIdAllList("SPT_GRP_CD"));
		model.addAttribute("bizDivCdList", codeService.selectGroupIdAllList("BIZ_DIV_CD"));
		model.addAttribute("lvlDivCdList", codeService.selectGroupIdAllList("LVL_DIV_CD"));
		model.addAttribute("dataDivCdList", codeService.selectGroupIdAllList("DATA_DIV_CD"));
		
		
		model.addAttribute("pages", criteria);		
		
		return "dataSet/dataSetList";
	}

	@PostMapping("/dataSet")
	public String dataSet(@ModelAttribute DataSetCriteria criteria, RedirectAttributes attributes){
		
		attributes.addFlashAttribute("pages", criteria);
		
		return "redirect:/dataSet/dataSet";
	}
	
	/**
	 * 정형데이터셋 상세조회
	 * @param model
	 * @param tblInfoId
	 * @param criteria
	 * @return
	 */
	@GetMapping("/dataSet/detail/{tblInfoId}")
	public String dataStructDetail(Model model, @PathVariable String tblInfoId, @ModelAttribute DataSetCriteria criteria) {
		
		criteria.setTblInfoId(tblInfoId);
		model.addAttribute("structTblDetail",dataSetService.selectStructTableInfoDetail(criteria));
		model.addAttribute("structColDetail",dataSetService.selectStructColumnInfo(criteria));
		model.addAttribute("hashTagList",dataSetService.selectHashTagList(criteria));

		//view카운트
		dataSetService.addViewCntDataSet(tblInfoId);
		
		return "dataSet/dataSetDetail";
	}
	
	/**
	 * 해쉬태그 저장
	 * @param dataSetInfo
	 * @param authUser
	 * @param model
	 * @return
	 */
	@PostMapping("/insertHashTag/ajax")
	public ResponseEntity insertHashTag(@ModelAttribute DataSetModel dataSetInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
		try {
			Map<Object,Object> result = new HashMap<>();
			List<Map> hashTagList = new ArrayList();
			dataSetInfo.setRgstId(authUser.getMemberModel().getUserId());
			dataSetInfo.setModiId(authUser.getMemberModel().getUserId());
			
			for(String tagNm : dataSetInfo.getTagNm().split(",")) {
				dataSetInfo.setTagNm(tagNm);
				dataSetInfo.setHashTagId(idUtil.getFndTagId());
				dataSetService.insertHashTag(dataSetInfo);
				Map obj = new HashMap();
				obj.put("hashTagId", dataSetInfo.getHashTagId());
    			obj.put("tagNm", dataSetInfo.getTagNm());
    			hashTagList.add(obj);
			}
			result.put("hashTagList", hashTagList);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
	} catch (Exception e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
	}
	
	/**
	 * 해쉬태그 삭제
	 * @param dataSetInfo
	 * @param authUser
	 * @param model
	 * @return
	 */
	@PostMapping("/deleteHashTag/ajax")
	public ResponseEntity deleteHashTag(@ModelAttribute DataSetModel dataSetInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
				try {
					Map<Object,Object> result = new HashMap<>(); 
					dataSetInfo.setModiId(authUser.getMemberModel().getUserId());
					
					long icnt=dataSetService.deleteHashTag(dataSetInfo);       
					result.put("cnt", icnt);	
					return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	/**
	 * 외부데이터셋 조회
	 * @param criteria
	 * @param model
	 * @return
	 */
	@GetMapping("/extrnlDataSet")
	public String extrnlDataSet(@ModelAttribute DataSetCriteria criteria, Model model) {
		
		model.addAttribute("extrnlDataSetList",dataSetService.selectExtrnlTableInfoList(criteria));
		criteria.setTotalCount(dataSetService.selectExtrnlTableInfoListCount(criteria));
		model.addAttribute("sptCompCdList", codeService.selectGroupIdAllList("SPT_COMP_CD"));
		model.addAttribute("bizDivCdList", codeService.selectGroupIdAllList("BIZ_DIV_CD"));
		model.addAttribute("lvlDivCdList", codeService.selectGroupIdAllList("LVL_DIV_CD"));
		
		
		model.addAttribute("pages", criteria);		
		
		return "dataSet/extrnlDataSetList";
	}
	
	/**
	 * 외부데이터셋 상세조회
	 * @param model
	 * @param tblInfoId
	 * @param criteria
	 * @return
	 */
	@GetMapping("/extrnlDataSet/detail/{tblInfoId}")
	public String dataExtrnlDetail(Model model, @PathVariable String tblInfoId, @ModelAttribute DataSetCriteria criteria) {
		
		criteria.setTblInfoId(tblInfoId);
		model.addAttribute("extrnlTblDetail",dataSetService.selectExtrnlTableInfoDetail(criteria));
		model.addAttribute("extrnlColDetail",dataSetService.selectExtrnlColumnInfoDetail(criteria));
		model.addAttribute("hashTagList",dataSetService.selectHashTagList(criteria));
		return "dataSet/extrnlDataSetDetail";
	}
	
	/**
	 * 데이터 공통코드 조회
	 * @param criteria
	 * @param authUser
	 * @return
	 */
	@PostMapping("/selectDataComCdList")
	public ResponseEntity selectDataComCdList(@ModelAttribute DataSetCriteria criteria, @AuthenticationPrincipal AuthUser authUser) {
			try {
						Map<Object,Object> result = new HashMap<>(); 
						
						List<DataSetModel> list=dataSetService.selectDataComCodeList(criteria);	 
						criteria.setTotalCount(dataSetService.selectDataComCodeListCount(criteria));
						result.put("dataComCdList", list);	 
						result.put("pages", criteria);
							
						return new ResponseEntity<>(result, HttpStatus.OK);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
			}
	}
	
	/**
	 * 데이터 공통코드 엑셀다운로드
	 * @param response
	 * @param criteria
	 * @param model
	 * @throws IOException
	 */
	@GetMapping("/excel/DataComCdDownload")
	public void DataComCdDownload(HttpServletResponse response,@ModelAttribute DataSetCriteria criteria, Model model) throws IOException {
	    	
	    	List<DataSetModel>arrModel= dataSetService.selectDataComCodeListExcel(criteria);
	    	
	    	Workbook wb = new XSSFWorkbook();
	        Sheet sheet = wb.createSheet("공통코드");
	        Row row = null;
	        Cell cell = null;
	        int rowNum = 0;
	                
	        row = sheet.createRow(rowNum++);
	        cell = row.createCell(0);
	        cell.setCellValue("그룹사");
	        cell = row.createCell(1);
	        cell.setCellValue("테이블명");
	        cell = row.createCell(2);
	        cell.setCellValue("컬럼한글명");
	        cell = row.createCell(3);
	        cell.setCellValue("컬럼영문명");
	        cell = row.createCell(4);
	        cell.setCellValue("유효값");
	        cell = row.createCell(5);
	        cell.setCellValue("유효값명");
	        
	        for(DataSetModel sModel : arrModel) {
	        	row = sheet.createRow(rowNum++);
	        	cell = row.createCell(0);
	            cell.setCellValue(sModel.getSptGrpNm());
	            cell = row.createCell(1);
	            cell.setCellValue(sModel.getTblEngNm());
	            cell = row.createCell(2);
	            cell.setCellValue(sModel.getColKorNm());
	            cell = row.createCell(3);
	            cell.setCellValue(sModel.getColEngNm());
	            cell = row.createCell(4);
	            cell.setCellValue(sModel.getValidVal());
	            cell = row.createCell(5);
	            cell.setCellValue(sModel.getValidNm());
	        }
	        
	        response.setContentType("ms-vnd/excel");
	        response.setHeader("Content-Disposition", "attachment;filename="+commonUtil.getDateString(Constant.DATE_FORMAT.DEFAULT_DATETIME)+".xlsx");
	        wb.write(response.getOutputStream());
	        wb.close();
	        
	    	
	    }
	
	/**
	 * 표준용어 조회
	 * @param criteria
	 * @param authUser
	 * @return
	 */
	 @PostMapping("/selectStdTermsPropListPopUp")
	 public ResponseEntity selectStdTermsPropListPopUp(@ModelAttribute DataSetCriteria criteria, @AuthenticationPrincipal AuthUser authUser) {
			try {
						Map<Object,Object> result = new HashMap<>(); 
						
						List<DataSetModel> list=dataSetService.selectStandardTermsProposalList(criteria);	 
						criteria.setTotalCount(dataSetService.selectStandardTermsProposalListCount(criteria));
						result.put("stdTermsPropList", list);	 
						result.put("pages", criteria);
							
						return new ResponseEntity<>(result, HttpStatus.OK);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
			}
	 }
	
	 @GetMapping("/selectStdTermsPropList")
	 public String selectStdTermsPropList(@ModelAttribute DataSetCriteria criteria, Model model) {
			
			model.addAttribute("stdTermsPropList",dataSetService.selectStandardTermsProposalList(criteria));
			criteria.setTotalCount(dataSetService.selectStandardTermsProposalListCount(criteria));  
			model.addAttribute("pages", criteria);		
			
			return "dataSet/stdTermsPropList";
	 }
	 
	 /**
	  * 표준용어 등록
	  * @param dataSetInfo
	  * @param criteria
	  * @param model
	  * @return
	  */
	 @GetMapping("/stdTermsPropRegist")
	 public String stdTermsPropRegist(@ModelAttribute DataSetModel dataSetInfo, @ModelAttribute DataSetCriteria criteria, Model model) {
		 
		 return "dataSet/stdTermsPropRegist";
	 }
	 
	 
	 /**
	  * 표준용어 저장
	  * @param dataSetInfo
	  * @param criteria
	  * @param authUser
	  * @return
	  */
	 @PostMapping("/InsertstdTermsProp")
	 public String InsertstdTermsProp(@ModelAttribute DataSetModel dataSetInfo, @ModelAttribute DataSetCriteria criteria, @AuthenticationPrincipal AuthUser authUser) {
		    dataSetInfo.setRgstId(authUser.getMemberModel().getUserId());
			dataSetInfo.setModiId(authUser.getMemberModel().getUserId());
			dataSetInfo.setHashTagId(idUtil.getStandardTerms());
			
			dataSetService.insertStandardTermsProposal(dataSetInfo);
			
		 return "redirect:/dataSet/selectStdTermsPropList";
	 }
	 
	 /**
	  * 이용자신청 상세
	  * @param criteria
	  * @param model
	  * @return
	  */
	 @GetMapping("/selectTblUsePropDetail")
	 public String selectTblUsePropDetail(@ModelAttribute DataSetCriteria criteria, Model model) {
			
			model.addAttribute("tblUsePropDetail",dataSetService.selectTblUseProposalDetail(criteria));
			
			return "dataSet/tblUsePropDetail";
	 }
	 
	 
}
