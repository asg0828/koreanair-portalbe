package com.bdp.ap.app.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bdp.ap.app.board.mapper.HelpMapper;
import com.bdp.ap.app.board.model.HelpModel;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.common.paging.Criteria;

@Service
public class HelpService {

	@Resource
	private CodeService codeService;

	@Resource
	private HelpMapper helpMapper;

	public List<HelpModel> selectHelpList(Criteria criteria) {
		return helpMapper.selectHelpList(criteria);
	}
	public int selectHelpListCount(Criteria criteria) {
		return helpMapper.selectHelpListCount(criteria);
	}
	public HelpModel selectHelp(HelpModel model) {
		return helpMapper.selectHelp(model);
	}
	public long deleteHelp(HelpModel model) {
		return helpMapper.deleteHelp(model);
	}
	public long insertHelp(HelpModel model) {
		return helpMapper.insertHelp(model);
	}
	public long updateHelp(HelpModel model) {
		return helpMapper.updateHelp(model);
	}
	public long addViewCntHelp(HelpModel model) {
		return helpMapper.addViewCntHelp(model);
	}

    /**
     * 모든 공지사항 코드 가져오기
     * @param model
     */
	public void selectAllHelpCodeList(Model model) {
    	model.addAttribute("groupCd", codeService.selectGroupIdAllList("COMPANY_CODE"));
    	model.addAttribute("categoryCd", codeService.selectGroupIdAllList("CATEGORY_CD"));
    }

}
