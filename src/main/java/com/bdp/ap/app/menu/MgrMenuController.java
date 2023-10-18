package com.bdp.ap.app.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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

import com.bdp.ap.app.board.service.BbsService;
import com.bdp.ap.app.menu.model.MgrMenuModel;
import com.bdp.ap.app.menu.model.TreeMenuModel;
import com.bdp.ap.app.menu.service.MgrMenuService;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.security.AuthUser;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/system")
@Controller
public class MgrMenuController {
 
    @Resource
    private MgrMenuService mgrMenuService;
    
    @Resource
	private BbsService bbsService;
    
    @GetMapping("/mgrMenu")
    public String sysMenu(Model model, String searchKeyword,
            @AuthenticationPrincipal AuthUser authUser) throws JsonProcessingException {
    	
    	List<MgrMenuModel> list = mgrMenuService.selectList(null);
    	
        Map<String, MgrMenuModel> resMenuMap = new HashMap<>();
        
        for (MgrMenuModel sysMenu : list) {
            resMenuMap.put(sysMenu.getMenuId(), sysMenu);
        }
    	        
        model.addAttribute("treeJson", new org.json.JSONObject(resMenuMap));
        model.addAttribute("searchKeyword", searchKeyword);

		Criteria criteria = new Criteria();
        criteria.setType("menu");
        model.addAttribute("bbsList", bbsService.selectBbsList(criteria));
        
        return "menu/mgrMenuReg";
    }
    
    @SuppressWarnings("finally")
	@PostMapping("/mgrMenu/insert")
    public String save(@ModelAttribute MgrMenuModel menuModel, @AuthenticationPrincipal AuthUser authUser,String searchKeyword) {
        try {
            menuModel.setRgstId(authUser.getMemberModel().getUserId());
            menuModel.setModiId(authUser.getMemberModel().getUserId());

            mgrMenuService.save(menuModel);            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }finally {
        	return "redirect:/system/mgrMenuReg?searchKeyword="+searchKeyword;
		}
        
    }
    
    @SuppressWarnings("finally")
    @PostMapping("/mgrMenu/delete")
    public String delete(@ModelAttribute MgrMenuModel menuModel, @AuthenticationPrincipal AuthUser authUser, Authentication auth) {

        try {
            menuModel.setModiId(authUser.getMemberModel().getUserId());
            mgrMenuService.delete(menuModel);      
        
        } catch (Exception e) {
                log.error(e.getMessage(), e);    	
        }finally {
        	return "redirect:/system/mgrMenuReg";
        }
    }
   
    @PostMapping(value = "/treeSave/ajax", consumes = MediaType.APPLICATION_JSON_VALUE, headers = {"Accept=application/json"})
    public ResponseEntity  treeSave(@RequestBody String param, @AuthenticationPrincipal AuthUser authUser) {
    	Map<String,Object> result = new HashMap<String, Object>();
 
    	try {
    		
    		JSONParser parser = new JSONParser();
    		JSONArray jsonArr = (JSONArray)parser.parse(param);
    		
    		ArrayList<MgrMenuModel> arrList = new ArrayList<MgrMenuModel>();
    		for(Object obj : jsonArr) {    			
    			JSONObject jsonObj = (JSONObject)obj;    			
    			getJSONObject(jsonObj, authUser.getMemberModel().getUserId(),arrList); 
    		}
    		 
    		mgrMenuService.saveAll(arrList);
    		
    		result.put("ok", "success");
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		result.put("ok", "failed");
    	}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    private void getJSONObject(JSONObject obj, String sUserId,ArrayList<MgrMenuModel> arr) {
    	JSONObject jsonObj = (JSONObject)obj; 
    	MgrMenuModel model = new MgrMenuModel();
    	
    	model = getMgrMenuModel(jsonObj,sUserId);
    	arr.add( model);
    	JSONArray jsonChildren = (JSONArray)jsonObj.get("children");
		if (!jsonChildren.isEmpty()) {
			for( Object obj1 : jsonChildren) {
				((JSONObject) obj1).put("parent", obj.get("id"));
				getJSONObject((JSONObject) obj1,sUserId,arr);
			}
		}
    	
    }
    
    
    @SuppressWarnings("unchecked")
	private MgrMenuModel getMgrMenuModel(JSONObject obj, String sUserId) {
    	MgrMenuModel model = new MgrMenuModel();
    	model.setMenuId(obj.get("id").toString());    	
    	model.setUpMenuId((String)obj.get("parent"));    	
    	model.setMenuNm(obj.get("text").toString());
    	
    	JSONObject jsonObj = (JSONObject)obj.get("li_attr");
    	model.setMenuDsc(jsonObj.get("menuDsc").toString());
    	model.setMenuUrl(jsonObj.get("menuUrl").toString());
    	model.setOrdSeq(Integer.parseInt(jsonObj.get("ordSeq").toString()) );
    	model.setUseYn(jsonObj.get("useYn").toString());
    	model.setMenuSe(jsonObj.get("menuSe").toString());
    	model.setMenuAttr(new org.json.JSONObject(jsonObj.getOrDefault("menuAttr", "").toString()));
    	model.setModiId(sUserId);
    	model.setRgstId(sUserId);
    	model.setMenuIcon(jsonObj.get("menuIcon").toString());
    	
    	return model;
    }
    
}
