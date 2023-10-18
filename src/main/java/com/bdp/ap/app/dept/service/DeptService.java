package com.bdp.ap.app.dept.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdp.ap.app.dept.mapper.DeptMapper;
import com.bdp.ap.app.dept.model.DeptClModel;
import com.bdp.ap.app.menu.model.MgrMenuModel;
import com.bdp.ap.common.IdUtil;

@Service
public class DeptService {
	
	@Resource
    private IdUtil idUtil;
	
	@Resource
	private DeptMapper deptMapper;

	public DeptClModel selectDeptCl(String deptCode) {
		return deptMapper.selectDeptCl(deptCode);
	}

	public List<DeptClModel> selectDeptClList() {
		return deptMapper.selectDeptClList();
	}

	public List<DeptClModel> selectDeptClSearchList(DeptClModel model) {
		return deptMapper.selectDeptClSearchList(model);
	}
	
	public List<DeptClModel>selectDeptList(DeptClModel model){
		return deptMapper.selectDeptList(model);
	}
	
	public List<DeptClModel>selectList(DeptClModel model){
		return deptMapper.selectList(model);
	}
	
	@Transactional
    public long delete(DeptClModel model) {
		deptMapper.insertHst(model);
		return deptMapper.delete(model);
	}
	
	@Transactional
    public long save(DeptClModel model) {
    	
        if(model.getDeptCode() != null && model.getDeptCode().length()>1&& !model.getDeptCode().contains("new")) {
            return deptMapper.update(model);
        } else {
        	model.setDeptCode(idUtil.getDeptCd());
        	if (model.getUpDeptCode() == null || model.getUpDeptCode().length()<1) {
        		model.setUpDeptCode(null);
        	}
        	        	
            return deptMapper.insert(model);
        }
    }
	
	@Transactional
    public long saveAll(ArrayList<DeptClModel> arrModel) {
    	long lRet=0;
    	
    	if (arrModel !=null) {
	    	deptMapper.insertHist(arrModel.get(0).getCompanyCode());
	    	deptMapper.deleteAll(arrModel.get(0).getCompanyCode());
    	}
    	
    	for(DeptClModel model : arrModel) {    		
    		lRet++;
    		
    		if(model.getDeptCode()== null || model.getDeptCode().contains("new")) { 
    			model.setDeptCode(idUtil.getDeptCd());
    		}
    		
        	if (model.getUpDeptCode() == null || model.getUpDeptCode().length()<1) {
        		model.setUpDeptCode(null);
        	}
        	deptMapper.insert(model);       
    	     
    	}
    	    	
    	return lRet;
    }
	
}
