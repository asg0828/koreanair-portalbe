package com.bdp.ap.app.bizmeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdp.ap.app.bizmeta.model.AnalysisMartModel;
import com.bdp.ap.app.bizmeta.model.BizmetaCriteria;
import com.bdp.ap.app.bizmeta.model.DataMartModel;
import com.bdp.ap.app.bizmeta.model.DimensionModel;
import com.bdp.ap.app.bizmeta.model.FindTagModel;
import com.bdp.ap.app.bizmeta.model.KeyItemModel;
import com.bdp.ap.app.bizmeta.model.ManageDeptModel;
import com.bdp.ap.app.bizmeta.model.ManageUserModel;
import com.bdp.ap.app.bizmeta.model.MeasureModel;
import com.bdp.ap.app.bizmeta.model.MetaModel;
import com.bdp.ap.app.bizmeta.model.ReportInfoModel;
import com.bdp.ap.app.bizmeta.model.RptModel;
import com.bdp.ap.app.bizmeta.service.BizmetaComService;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.dept.model.DeptClModel;
import com.bdp.ap.app.dept.service.DeptService;
import com.bdp.ap.app.file.service.FileService;
import com.bdp.ap.app.member.model.MemberCriteria;
import com.bdp.ap.app.member.model.MemberModel;
import com.bdp.ap.app.member.service.MemberService;
import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.config.props.FileProps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/bizmeta")
@Controller
public class BizmetaComController {
	
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
    
    
    /**
     * Key항목 조회
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/bizmetaCom/keyItemList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectKeyItemList(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false;
    	List<KeyItemModel>modelList=bizmetaComService.selectKeyItem(criteria);
    	
    	if(modelList !=null && modelList.size()>0) {
    		res = true;
    		result.put("keyItemList", modelList);
    	}
    	result.put("result", res);
    	
    	return result;
    }
    
    /**
     * 디멘젼 조회
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/bizmetaCom/dimList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectDimension(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false;
    	List<DimensionModel>modelList=bizmetaComService.selectDimensiont(criteria);
    	
    	if(modelList !=null && modelList.size()>0) {
    		res = true;
    		result.put("dimList", modelList);
    	}
    	result.put("result", res);
    	
    	return result;
    }
    
    /**
     * 메져조회
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/bizmetaCom/mesList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectMeasure(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false;
    	List<MeasureModel>modelList=bizmetaComService.selectMeasure(criteria);
    	
    	if(modelList !=null && modelList.size()>0) {
    		res = true;
    		result.put("mesList", modelList);
    	}
    	result.put("result", res);
    	
    	return result;
    }
    
    /**
     * 데이터원천조회
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/bizmetaCom/dataMartList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectDataMart(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false;
    	List<DataMartModel>modelList=bizmetaComService.selectDataMart(criteria);
    	
    	if(modelList !=null && modelList.size()>0) {
    		res = true;
    		result.put("dataMartList", modelList);
    		result.put("dataMartListCount", bizmetaComService.selectDataMartCount(criteria));
    	}
    	result.put("result", res);
    	
    	return result;
    }
     
    
    /**
     * 검색태그
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/bizmetaCom/findTagList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectFindTag(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false;
    	List<FindTagModel>modelList=bizmetaComService.selectFindTag(criteria);
    	
    	if(modelList !=null && modelList.size()>0) {
    		res = true;
    		result.put("findTagList", modelList);
    	}
    	result.put("result", res);
    	
    	return result;
    }
    
   
   /**
    * 관리부서 조회 
    * @param criteria
    * @param model
    * @return
    */
    @RequestMapping(value="/bizmetaCom/deptClList", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectDeptCl(@ModelAttribute DeptClModel criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false; 
    	List<DeptClModel>modelList=deptService.selectDeptClSearchList(criteria);
    	
    	if(modelList !=null && modelList.size()>0) {
    		res = true;
    		result.put("deptClList", modelList);
    	}
    	result.put("result", res);
    	
    	return result;
    }
     
    
    /**
     * 관리부서 등록 조회
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/bizmetaCom/MngDeptList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectManageDept(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false; 
    	List<ManageDeptModel>modelList=bizmetaComService.selectManageDept(criteria);
    	
    	if(modelList !=null && modelList.size()>0) {
    		res = true;
    		result.put("mngDeptList", modelList);
    	}
    	result.put("result", res);
    	
    	return result;
    }
    
    /**
     * 사용자 조회
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/bizmetaCom/memberList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectMemberList(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false; 
    	
    	MemberCriteria memberCriteria =  new MemberCriteria();
    	memberCriteria.setDeptCode(criteria.getDeptCd());
    	memberCriteria.setDeptNm(criteria.getDeptNm());
    	memberCriteria.setUserId(criteria.getUserId());
    	memberCriteria.setUserNm(criteria.getUserNm());
    	
    	
    	memberCriteria.setSearchKey(criteria.getSearchKey()); 
    	memberCriteria.setSearchValue(criteria.getSearchNm());
    	
    	
    	List<DeptClModel>modelList=deptService.selectDeptClList();
    	
    	if(modelList !=null && modelList.size()>0) {
    		res = true;
    		result.put("memberList", bizmetaComService.selectMemberList(memberCriteria));
    		result.put("deptList", modelList);
    	}
    	result.put("result", res);
    	
    	return result;
    }
    

    @RequestMapping(value="/bizmetaCom/MngUserList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectManageUser(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false; 
    	List<ManageUserModel>modelList=bizmetaComService.selectManageUser(criteria);
    	
    	if(modelList !=null && modelList.size()>0) {
    		res = true;
    		result.put("mngUserList", modelList);
    	}
    	result.put("result", res);
    	
    	return result;
    }
    
    /**
     * 메타 테이블 조회
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/bizmetaCom/tblList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectTblList(@ModelAttribute MetaModel criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false; 
    	List<MetaModel>modelList=bizmetaComService.selectTblList(criteria);
    	
    	if(modelList !=null && modelList.size()>0) {
    		res = true;
    		result.put("tblList", modelList);
    		result.put("tblListCount", bizmetaComService.selectTblListCount(criteria));
    	}
    	result.put("result", res);
    	
    	return result;
    }
    
    /**
     * 메타 컬럼 조회
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/bizmetaCom/colList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectColList(@ModelAttribute MetaModel criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false; 
    	List<MetaModel>modelList=bizmetaComService.selectColList(criteria);
    	
    	if(modelList !=null && modelList.size()>0) {
    		res = true;
    		result.put("colList", modelList);
    		result.put("colListCount", bizmetaComService.selectColListCount(criteria));
    	}
    	result.put("result", res);
    	
    	return result;
    }
   
    /**
     * 보고서선택등록 조회
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/bizmetaCom/rptList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectRptListBizmeta(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false; 
    	List<RptModel>modelList=bizmetaComService.selectRptListBizmeta(criteria);
    	
    	if(modelList !=null && modelList.size()>0) {
    		res = true;
    		result.put("rptList", modelList); 
    	}
    	result.put("result", res);
    	
    	return result;
    }
    
    /**
     * 보고서선택 조회
     * @param criteria
     * @param model
     * @return
     */
    @RequestMapping(value="/bizmetaCom/reportInfoList/ajax", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectReportInfoList(@ModelAttribute BizmetaCriteria criteria, Model model){
    	Map<String,Object> result = new HashMap<String, Object>();
    	boolean res = false; 
    	List<ReportInfoModel>modelList=bizmetaComService.selectReportInfoList(criteria);
    	
    	if(modelList !=null && modelList.size()>0) {
    		res = true;
    		result.put("reportInfoList", modelList); 
    	}
    	result.put("result", res);
    	
    	return result;
    }
      
}
