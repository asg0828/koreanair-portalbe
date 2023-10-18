package com.bdp.ap.app.menu;

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

import com.bdp.ap.app.board.service.BbsService;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.menu.model.MenuModel;
import com.bdp.ap.app.menu.model.TreeMenuModel;
import com.bdp.ap.app.menu.service.MenuService;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.security.AuthUser;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템관리 / 메뉴관리 컨트롤러
 *
 */
@Slf4j
@RequestMapping("/system")
@Controller
public class MenuController {
	@Value("${company-props.code}")
	private String companyCode;
	
    @Resource
    private MenuService menuService;

    @Resource
	private CodeService codeService;
    
    @Resource
	private BbsService bbsService;

    /**
     * 메뉴관리 페이지로 이동한다.
     *
     * @param model
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/menu")
    public String menu(Model model, String old, String searchKeyword,String companyCd,
            @AuthenticationPrincipal AuthUser authUser) throws JsonProcessingException {
    	
    	if (companyCd ==null) {
    		companyCd=companyCode;
		}
    	
        List<MenuModel> list = menuService.selectList(searchKeyword,companyCd);
        String rootMenuId = null;
        // 응답 메뉴 목록 객체
        List<TreeMenuModel> resMenuList = new ArrayList<>();
        Map<String, MenuModel> resMenuMap = new HashMap<>();
        // 메뉴 리스트를 관리 하기 위한 참조 객체
        Map<String, TreeMenuModel> menuMap = new HashMap<>();
        for (MenuModel menu : list) {
            if (menu.getLv() == 0) {
                rootMenuId = menu.getMenuId();
            }
            String menuId = menu.getMenuId();
            String upMenuId = menu.getUpMenuId();
            resMenuMap.put(menuId, menu);
            TreeMenuModel treeMenuModel = new TreeMenuModel();
            treeMenuModel.setMenuModel(menu);
            menuMap.put(menuId, treeMenuModel);

            if (upMenuId == null || upMenuId.isEmpty()) {
                // ROOT 객체
                resMenuList.add(treeMenuModel);
            } else {
                if (menuMap.containsKey(upMenuId)) {
                    menuMap.get(upMenuId).getSubMenuModel().add(treeMenuModel);
                } else {
                    log.debug(" 부모 객체를 찾을 수 없습니다. {}", menu);
                }
            }
        }
     // 회사구분 코드
     	model.addAttribute("codeCompanyCdList", codeService.selectGroupIdAllList("COMPANY_CODE"));
        model.addAttribute("menus", list);
        model.addAttribute("rootMenuId", rootMenuId);
        model.addAttribute("menuJson", new org.json.JSONObject(resMenuMap));
        model.addAttribute("menuList", resMenuList);
        model.addAttribute("searchKeyword", searchKeyword);
        
        Criteria criteria = new Criteria();
        criteria.setType("menu");
        model.addAttribute("bbsList", bbsService.selectBbsList(criteria));
        if (old != null && old.equals("y")) {
            return "menu/menu";
        } else {
            return "menu/menuReg";
        }

    }
    
    /**
     * 메뉴관리 모델을 저장한다.
     *
     * @param menuModel 메뉴모델
     * @param authUser
     * @return
     */
    @PostMapping("/menu/insert")
    public String save(@ModelAttribute MenuModel menuModel, @AuthenticationPrincipal AuthUser authUser) {

        try {
            menuModel.setRgstId(authUser.getMemberModel().getUserId());
            menuModel.setModiId(authUser.getMemberModel().getUserId());

            menuService.save(menuModel);

            return "redirect:/system/menuReg";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "forward:/system/menuReg";
        }
    }

    /**
     * 메뉴모델을 삭제한다.
     *
     * @param menuModel
     * @param authUser
     * @return
     */
    @PostMapping("/menu/delete")
    public String delete(@ModelAttribute MenuModel menuModel, @AuthenticationPrincipal AuthUser authUser, Authentication auth) {

        try {
            menuModel.setModiId(authUser.getMemberModel().getUserId());

            menuService.delete(menuModel);

            return "redirect:/system/menuReg";
        } catch (Exception e) {
            return "forward:/system/menuReg";
        }
    }
    
    @PostMapping(value = "/treeUserSave/ajax", consumes = MediaType.APPLICATION_JSON_VALUE, headers = {"Accept=application/json"})
    public ResponseEntity  treeUserSave(@RequestBody String param, @AuthenticationPrincipal AuthUser authUser) {
    	Map<String,Object> result = new HashMap<String, Object>();
    	 
    	try {
    		
    		JSONParser parser = new JSONParser();
    		JSONArray jsonArr = (JSONArray)parser.parse(param);
    		
    		ArrayList<MenuModel> arrList = new ArrayList<MenuModel>();
    		for(Object obj : jsonArr) {    			
    			JSONObject jsonObj = (JSONObject)obj;    			
    			getJSONObject(jsonObj, authUser.getMemberModel().getUserId(),arrList); 
    		}
    		 
    		menuService.saveAll(arrList);
    		
    		result.put("ok", "success");
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		result.put("ok", "failed");
    	}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    
    private void getJSONObject(JSONObject obj, String sUserId,ArrayList<MenuModel> arr) {
    	JSONObject jsonObj = (JSONObject)obj; 
    	MenuModel model = new MenuModel();
    	
    	model = getMenuModel(jsonObj,sUserId);
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
	private MenuModel getMenuModel(JSONObject obj, String sUserId) {
    	MenuModel model = new MenuModel();
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
    	model.setCompanyCode((String)jsonObj.get("companyCode"));
    	model.setModiId(sUserId);
    	model.setRgstId(sUserId);
        model.setMenuIcon(jsonObj.get("menuIcon").toString());
    	
    	return model;
    }
    
    
    /**
     * 회사별 메뉴조회
     * @param searchKeyword
     * @param companyCd
     * @param authUser
     * @return
     */
    @PostMapping("/selectCompanyMenu/ajax")
    public ResponseEntity  selectCompanyMenu(String searchKeyword,String companyCd, @AuthenticationPrincipal AuthUser authUser) {
    	List<MenuModel> list = menuService.selectList(searchKeyword,companyCd);
    	Map<String, MenuModel> result = new HashMap<>();
    	  for (MenuModel menu : list) {
    		  result.put(menu.getMenuId(), menu);
          }    	  
    	  return new ResponseEntity<>(new org.json.JSONObject(result).toString(), HttpStatus.OK);	
    }
}
