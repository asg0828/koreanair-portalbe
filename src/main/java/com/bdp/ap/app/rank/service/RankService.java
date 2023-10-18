package com.bdp.ap.app.rank.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdp.ap.app.rank.mapper.RankMapper;
import com.bdp.ap.app.rank.model.RankModel;
import com.bdp.ap.common.IdUtil;

@Service
public class RankService {
	@Resource
    private IdUtil idUtil;
	
    @Resource RankMapper rankMapper;
    
	public List<RankModel> select(RankModel model){
		return rankMapper.select(model);
	}
	
	@Transactional
    public long save(RankModel model) {
    	
        if(model.getRankCode() != null && model.getRankCode().length()>1&& !model.getRankCode().contains("new")) {
            return rankMapper.update(model);
        } else {
        	model.setRankCode(idUtil.getRankCd());
        	if (model.getUpRankCode() == null || model.getUpRankCode().length()<1) {
        		model.setUpRankCode(null);
        	}
            return rankMapper.insert(model);
        }
    }
	
	@Transactional
	public long delete(RankModel model) {
		return rankMapper.delete(model);
	}
	
	@Transactional
    public long saveAll(ArrayList<RankModel> arrModel) {
    	long lRet=0;
    	
    	if (arrModel !=null) {
	    	rankMapper.insertHist(arrModel.get(0).getCompanyCode());
	    	rankMapper.deleteAll(arrModel.get(0).getCompanyCode());
    	}
    	
    	for(RankModel model : arrModel) {    		
    		lRet++;
    		
    		if(model.getRankCode()== null || model.getRankCode().contains("new")) { 
    			model.setRankCode(idUtil.getPstnCd());
    		}
    		
        	if (model.getUpRankCode() == null || model.getUpRankCode().length()<1) {
        		model.setUpRankCode(null);
        	}
        	rankMapper.insert(model);       
    	     
    	}
    	    	
    	return lRet;
    }
}
