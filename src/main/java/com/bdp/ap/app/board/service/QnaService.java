package com.bdp.ap.app.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bdp.ap.app.board.mapper.QnaMapper;
import com.bdp.ap.app.board.model.QnaModel;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.common.paging.Criteria;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author 713034
 * Notice Service
 */
@Slf4j
@Service
public class QnaService {

	@Resource
	private CodeService codeService;
	
	@Resource
	private QnaMapper qnaMapper;
	
	public List<QnaModel> selectQnaList(Criteria criteria) {
		return qnaMapper.selectQnaList(criteria);
	}
	public int selectQnaListCount(Criteria criteria) {
		return qnaMapper.selectQnaListCount(criteria);
	}
	public QnaModel selectQna(QnaModel model) {
		return qnaMapper.selectQna(model);
	}
	public long deleteQna(QnaModel model) {
		return qnaMapper.deleteQna(model);
	}
	public long insertQna(QnaModel model) {
		return qnaMapper.insertQna(model);
	}
	public long updateQna(QnaModel model) {
		return qnaMapper.updateQna(model);
	}
	public long addViewCntQna(QnaModel model) {
		return qnaMapper.addViewCntQna(model);
	}	
	
	public List<QnaModel>selectQnaReplyList(QnaModel model){
		return qnaMapper.selectQnaReplyList(model);
	}
	
	public long qnaStat(QnaModel model) {
		return qnaMapper.qnaStat(model);
	}
	
    /**
     * 모든 QnA 코드 가져오기
     * @param model
     */
	public void selectAllQnaCodeList(Model model) {
    	model.addAttribute("codeUseYnList", codeService.selectGroupIdAllList("USE_YN"));
    	model.addAttribute("codeOpenYnList", codeService.selectGroupIdAllList("OPEN_YN"));
    	model.addAttribute("codeQnaCateCdList", codeService.selectGroupIdAllList("QNA_CAT"));
    	model.addAttribute("codeQnaStatCdList", codeService.selectGroupIdAllList("QNA_STAT_CODE"));
    }	

}
