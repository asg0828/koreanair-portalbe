package com.bdp.ap.app.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdp.ap.app.menu.mapper.MgrMenuMapper;
import com.bdp.ap.app.menu.model.MgrMenuModel;
import com.bdp.ap.common.IdUtil;

/**
 * 메뉴관리 서비스 클래스
 *
 */
@Service
public class MgrMenuService {
	
	@Resource
    private IdUtil idUtil;
	
    @Resource
    private MgrMenuMapper mgrMenuMapper;

    public List<MgrMenuModel> selectList(String searchKeyword) {
        return mgrMenuMapper.selectList(searchKeyword);
    }
    
    public MgrMenuModel select(MgrMenuModel model) {
        return mgrMenuMapper.select(model);
    }
    
    @Transactional
    public long delete(MgrMenuModel model) {
        return mgrMenuMapper.delete(model);
    }
    
    @Transactional
    public long save(MgrMenuModel model) {
    	
        if(model.getMenuId() != null && model.getMenuId().length()>1&& !model.getMenuId().contains("new")) {
            return mgrMenuMapper.update(model);
        } else {
        	model.setMenuId(idUtil.getMenuId());
        	if (model.getUpMenuId() == null || model.getUpMenuId().length()<1) {
        		model.setUpMenuId(null);
        	}
            return mgrMenuMapper.insert(model);
        }
    }
    
    public List<MgrMenuModel> selectTopMenuListWithAuth(String authId) {
    	return mgrMenuMapper.selectTopMenuListWithAuth(authId);
    }
    
    /**
     * 권한과 상위 메뉴 ID에 따른 메뉴 리스트를 조회
    * @param String
    * @param String
    */
    public List<MgrMenuModel> selectLeftMenuListWithAuth(String upMenuId, String authId) {
    	Map<String,String> param = new HashMap<>();
    	param.put("upMenuId", upMenuId);
    	param.put("authId", authId);
    	return mgrMenuMapper.selectLeftMenuListWithAuth(param);
    }
    
    /**
     * 권한으로 접근 가능한 첫번째 메뉴 URL 조회
     * @param String
     * @param String
     */
    public String selectFirstMenuUrl(String menuUrl, String authId) {
    	Map<String,String> param = new HashMap<>();
    	param.put("menuUrl", menuUrl);
    	param.put("authId", authId);
    	return mgrMenuMapper.selectFirstMenuUrl(param);
    }
    
    /**
     * 권한별 메뉴 리스트
     * @param String
     */
    public List<MgrMenuModel> selectMenuListWithAuth(String authId) {
    	return mgrMenuMapper.selectMenuListWithAuth(authId);
    }
    
    /**
     * 권한별 메뉴를 업데이트 한다.
     * @param model
     * @return
     */
    @Transactional
    public long updateMenuListWithAuth(MgrMenuModel model) {
    	return mgrMenuMapper.updateMenuListWithAuth(model);
    }
    
    @Transactional
    public long saveAll(ArrayList<MgrMenuModel> arrModel) {
    	long lRet=0;
    	
    	mgrMenuMapper.insertHst();
    	mgrMenuMapper.deleteAll();    	
    	for(MgrMenuModel model : arrModel) {    		
    		lRet++;
    		
    		if(model.getMenuId()== null || model.getMenuId().contains("new")) { 
    			model.setMenuId(idUtil.getMenuId());
    		}
    		
        	if (model.getUpMenuId() == null || model.getUpMenuId().length()<1) {
        		model.setUpMenuId(null);
        	}
        	mgrMenuMapper.insert(model);       
    	     
    	}
    	
    	
    	return lRet;
    }
}
