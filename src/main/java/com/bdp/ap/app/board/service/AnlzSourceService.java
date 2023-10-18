package com.bdp.ap.app.board.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bdp.ap.app.board.mapper.AnlzSourceMapper;
import com.bdp.ap.app.board.model.AnlzSourceCmtModel;
import com.bdp.ap.app.board.model.AnlzSourceModel;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.security.AuthUser;

@Service
public class AnlzSourceService {

	@Resource
	private CodeService codeService;

	@Resource
	private AnlzSourceMapper anlzsourceMapper;

	public List<AnlzSourceModel> selectAnlzSourceList(Criteria criteria) {
		return anlzsourceMapper.selectAnlzSourceList(criteria);
	}
	public int selectAnlzSourceListCount(Criteria criteria) {
		return anlzsourceMapper.selectAnlzSourceListCount(criteria);
	}
	public AnlzSourceModel selectAnlzSource(AnlzSourceModel model) {
		return anlzsourceMapper.selectAnlzSource(model);
	}
	public List<AnlzSourceModel> selectAnlzSourceAuth(AnlzSourceModel model) {
		return anlzsourceMapper.selectAnlzSourceAuth(model);
	}
	public List<AnlzSourceCmtModel> selectAnlzSourceCommentList(AnlzSourceModel model) {
		return anlzsourceMapper.selectAnlzSourceCommentList(model);
	}
	public long deleteAnlzSource(AnlzSourceModel model) {
		return anlzsourceMapper.deleteAnlzSource(model);
	}
	public long insertAnlzSource(AnlzSourceModel model) {
		return anlzsourceMapper.insertAnlzSource(model);
	}
	public long insertAnlzSourceAuth(AnlzSourceModel model) {
		return anlzsourceMapper.insertAnlzSourceAuth(model);
	}
	public long insertAnlzSourceCmt(AnlzSourceCmtModel model) {
		return anlzsourceMapper.insertAnlzSourceCmt(model);
	}
	public long updateAnlzSourceCmt(AnlzSourceCmtModel model) {
		return anlzsourceMapper.updateAnlzSourceCmt(model);
	}
	public long deleteAnlzSourceCmt(AnlzSourceCmtModel model) {
		return anlzsourceMapper.deleteAnlzSourceCmt(model);
	}
	public long updateAnlzSource(AnlzSourceModel model) {
		return anlzsourceMapper.updateAnlzSource(model);
	}
	public long deleteAnlzSourceAuth(AnlzSourceModel model) {
		return anlzsourceMapper.deleteAnlzSourceAuth(model);
	}
	public long updateAnlzSourceReadCnt(AnlzSourceModel model) {
		return anlzsourceMapper.updateAnlzSourceReadCnt(model);
	}
	public long deleteRealAnlzSourceCmt(AnlzSourceCmtModel model) {
		return anlzsourceMapper.deleteRealAnlzSourceCmt(model);
	}
    /**
     * 모든 공지사항 코드 가져오기
     * @param model
     */
	public void selectAllAnlzSourceCodeList(Model model) {
		// 회사구분 코드
        model.addAttribute("codeCompanyCdList", codeService.selectGroupIdAllList("COMPANY_CODE"));

    }

}
