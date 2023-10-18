package com.bdp.ap.app.dept;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.dept.model.DeptClModel;
import com.bdp.ap.app.dept.service.DeptService;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.config.security.AuthUser;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/system")
@Controller
@Slf4j
public class DeptController {
	@Resource
	private IdUtil idUtil;

	@Resource
	private DeptService deptService;
	
	@Resource
	private CodeService codeService;
	
	@Value("${company-props.code}")
	private String companyCode;
	
	@GetMapping("/dept")
    public String deptReg(Model model, @ModelAttribute DeptClModel deptClModel,
            							@AuthenticationPrincipal AuthUser authUser) throws JsonProcessingException {
		if (deptClModel.getCompanyCode() ==null) {
			deptClModel.setCompanyCode(companyCode);
		}
		List<DeptClModel>list = deptService.selectList(deptClModel);
				 
        Map<String, DeptClModel> resMap = new HashMap<>();
        
        for (DeptClModel deptModel : list) {
        	resMap.put(deptModel.getDeptCode(), deptModel);
        }
        model.addAttribute("treeJson", new org.json.JSONObject(resMap));
        model.addAttribute("companyCodeList", codeService.selectGroupIdAllList("COMPANY_CODE")); 
		return "system/deptReg";
	}
	
	@SuppressWarnings("finally")
	@PostMapping("/dept/insert")
    public String save(@ModelAttribute DeptClModel deptModel, @AuthenticationPrincipal AuthUser authUser) {
		 try {
				 deptModel.setRgstId(authUser.getMemberModel().getUserId());
				 deptModel.setModiId(authUser.getMemberModel().getUserId());

				 deptService.save(deptModel);            
	        } catch (Exception e) {
	            log.error(e.getMessage(), e);
	        }finally {
	        	return "redirect:/system/deptReg";
			}
	}
	
	@SuppressWarnings("finally")
    @PostMapping("/dept/delete")
    public String delete(@ModelAttribute DeptClModel deptModel, @AuthenticationPrincipal AuthUser authUser, Authentication auth) {

        try {
        	deptModel.setModiId(authUser.getMemberModel().getUserId());
            deptService.delete(deptModel);      
        
        } catch (Exception e) {
                log.error(e.getMessage(), e);    	
        }finally {
        	return "redirect:/system/deptReg";
        }
    }
	
	@PostMapping(value = "/treeDeptSave/ajax",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    headers = {"Accept=application/json"})
    public ResponseEntity  treeDeptSave(@RequestBody String param, @AuthenticationPrincipal AuthUser authUser) {
    	Map<String,Object> result = new HashMap<String, Object>();
    	try {
		    		
	    		JSONParser parser = new JSONParser();
	    		JSONArray jsonArr = (JSONArray)parser.parse(param);
		    		
	    		ArrayList<DeptClModel> arrList = new ArrayList<DeptClModel>();
	    		for(Object obj : jsonArr) {    			
	    			JSONObject jsonObj = (JSONObject)obj;    			
	    			getJSONObject(jsonObj, authUser.getMemberModel().getUserId(),arrList); 
	    		}
 
	    		deptService.saveAll(arrList);
		    		
	    		result.put("ok", "success");
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		result.put("ok", "failed");
    	}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	 private void getJSONObject(JSONObject obj, String sUserId,ArrayList<DeptClModel> arr) {
	    	JSONObject jsonObj = (JSONObject)obj; 
	    	DeptClModel model = new DeptClModel();
	    	
	    	model = getTreeModel(jsonObj,sUserId);
	    	arr.add( model);
	    	JSONArray jsonChildren = (JSONArray)jsonObj.get("children");
			if (!jsonChildren.isEmpty()) {
				for( Object obj1 : jsonChildren) {
					((JSONObject) obj1).put("parent", obj.get("id"));
					getJSONObject((JSONObject) obj1,sUserId,arr);
				}
			}
	    	
	    }
	    	    
	    private DeptClModel getTreeModel(JSONObject obj, String sUserId) {
	    	DeptClModel model = new DeptClModel();
	    	model.setDeptCode(obj.get("id").toString());    	
	    	model.setUpDeptCode((String)obj.get("parent"));    	
	    	model.setDeptNm(obj.get("text").toString());	    	
	    	model.setRgstId(sUserId);
	    	model.setModiId(sUserId);
	    	JSONObject jsonObj = (JSONObject)obj.get("li_attr");
	    	model.setUseYn(jsonObj.get("useYn").toString());
	    	model.setOrdSeq(Integer.parseInt(jsonObj.get("ordSeq").toString()) );
	    	model.setCompanyCode((String)jsonObj.get("companyCode"));
	    	model.setModiSe((String)jsonObj.get("modiSe"));
	    	 
	    	
	    	return model;
	    }
}
