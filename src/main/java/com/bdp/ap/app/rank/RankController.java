package com.bdp.ap.app.rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
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
import com.bdp.ap.app.rank.model.RankModel;
import com.bdp.ap.app.rank.service.RankService;
import com.bdp.ap.config.security.AuthUser;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/system")
@Controller
public class RankController {
	@Value("${company-props.code}")
	private String companyCode;
	
	@Resource
	private CodeService codeService;
	
	@Resource
	private RankService rankService;
	
	
	@GetMapping("/rank")
	public String sysRank(Model model, @ModelAttribute RankModel rankmodel,
	            							@AuthenticationPrincipal AuthUser authUser) throws JsonProcessingException {
		if (rankmodel.getCompanyCode() ==null) {
			rankmodel.setCompanyCode(companyCode);
		}
		
	   List<RankModel> list = rankService.select(rankmodel);
		      
       Map<String, RankModel> resMap = new HashMap<>(); 
       for (RankModel sysRank : list) {
       	 resMap.put(sysRank.getRankCode(), sysRank);       	 
       }
       
       model.addAttribute("treeJson", new org.json.JSONObject(resMap)); 
       model.addAttribute("companyCodeList", codeService.selectGroupIdAllList("COMPANY_CODE"));
       
	 return "rank/rankReg";
		
	}
	
	@SuppressWarnings("finally")
	@PostMapping("/rank/insert")
    public String save(@ModelAttribute RankModel model, @AuthenticationPrincipal AuthUser authUser,String searchKeyword) {
		try {
			model.setRgstId(authUser.getMemberModel().getUserId());
			model.setModiId(authUser.getMemberModel().getUserId());

			rankService.save(model);            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }finally {
        	return "redirect:/system/rankReg?searchKeyword="+searchKeyword;
		}
	}
	
	@SuppressWarnings("finally")
    @PostMapping("/rank/delete")
    public String delete(@ModelAttribute RankModel model, @AuthenticationPrincipal AuthUser authUser, Authentication auth) {

        try {
        	model.setModiId(authUser.getMemberModel().getUserId());
        	rankService.delete(model);      
        
        } catch (Exception e) {
                log.error(e.getMessage(), e);    	
        }finally {
        	return "redirect:/system/rankReg";
        }
    }
	
	@PostMapping(value = "/rankSave/ajax", consumes = MediaType.APPLICATION_JSON_VALUE, headers = {"Accept=application/json"})
    public ResponseEntity  rankSave(@RequestBody String param, @AuthenticationPrincipal AuthUser authUser) {
    	Map<String,Object> result = new HashMap<String, Object>();

    	try {
    		
    		JSONParser parser = new JSONParser();
    		JSONArray jsonArr = (JSONArray)parser.parse(param);
    		
    		ArrayList<RankModel> arrList = new ArrayList<RankModel>();
    		for(Object obj : jsonArr) {    			
    			JSONObject jsonObj = (JSONObject)obj;    			
    			getJSONObject(jsonObj, authUser.getMemberModel().getUserId(),arrList); 
    		}
    		
    		rankService.saveAll(arrList);
    		
    		result.put("ok", "success");
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		result.put("ok", "failed");
    	}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	private void getJSONObject(JSONObject obj, String sUserId,ArrayList<RankModel> arr) {
    	JSONObject jsonObj = (JSONObject)obj; 
    	RankModel model = new RankModel();
    	
    	model = getRankModel(jsonObj,sUserId);
    	arr.add( model);
    	JSONArray jsonChildren = (JSONArray)jsonObj.get("children");
		if (!jsonChildren.isEmpty()) {
			for( Object obj1 : jsonChildren) {
				((JSONObject) obj1).put("parent", obj.get("id"));
				getJSONObject((JSONObject) obj1,sUserId,arr);
			}
		}    	
    }
	
	private RankModel getRankModel(JSONObject obj, String sUserId) {
		RankModel model = new RankModel();
    	model.setRankCode(obj.get("id").toString());    	
    	model.setUpRankCode((String)obj.get("parent"));    	
    	model.setRankNm((String)obj.get("text"));
    	
    	model.setRgstId(sUserId);
    	model.setModiId(sUserId);
    	JSONObject jsonObj = (JSONObject)obj.get("li_attr");
    	model.setUseYn((String)jsonObj.get("useYn"));
    	model.setOrdSeq(Integer.parseInt(jsonObj.get("ordSeq").toString()) );
    	model.setCompanyCode((String)jsonObj.get("companyCode"));
    	    	
    	return model;
    }
	
}
