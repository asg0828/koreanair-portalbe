package com.bdp.ap.app.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bdp.ap.app.board.mapper.CommonBbsMapper;
import com.bdp.ap.app.board.model.CommentModel;
import com.bdp.ap.app.board.model.CommonBbsItemModel;
import com.bdp.ap.app.board.model.CommonBbsMngModel;
import com.bdp.ap.app.board.model.CommonBbsModel;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.common.paging.Criteria;

@Service
public class CommonBbsService {

    @Resource
	private CodeService codeService;
	
	@Resource
	private CommonBbsMapper CommonBbsMapper;

    public List<CommonBbsModel> selectBbsList(CommonBbsModel model){
        return CommonBbsMapper.selectBbsList(model);
    }

    public List<CommonBbsModel> selectBbsReplyList(CommonBbsModel model){
        return CommonBbsMapper.selectBbsReplyList(model);
    }

    public int selectBbsListCount(CommonBbsModel model) {
		return CommonBbsMapper.selectBbsListCount(model);
	}

    public CommonBbsMngModel selectBbsMng(CommonBbsModel model){
        return CommonBbsMapper.selectBbsMng(model);
    }

    public CommonBbsModel selectBbs(CommonBbsModel model){
        return CommonBbsMapper.selectBbs(model);
    }

    public List<CommonBbsItemModel> selectBbsItemList(CommonBbsModel model){
        return CommonBbsMapper.selectBbsItemList(model);
    }

    public long insertBbs(CommonBbsModel model){ 
        return CommonBbsMapper.insertBbs(model);
    }

    public long updateBbs(CommonBbsModel model){
        return CommonBbsMapper.updateBbs(model);
    }
    
    public long deleteBbs(CommonBbsModel model){
        return CommonBbsMapper.deleteBbs(model);
    }
    
    public long addViewCntBbs(CommonBbsModel model){
        return CommonBbsMapper.addViewCntBbs(model);
    }

    public int authChecker(CommonBbsModel model){
        return CommonBbsMapper.authChecker(model);
    }

    public long insertBbsComment(CommentModel model){
        return CommonBbsMapper.insertBbsComment(model);
    }
    
    public long updateBbsComment(CommentModel model){
        return CommonBbsMapper.updateBbsComment(model);
    }

    public long deleteBbsComment(CommentModel model){
        return CommonBbsMapper.deleteBbsComment(model);
    }

    public List<CommentModel> selectBbsCommentList(CommonBbsModel model){
        return CommonBbsMapper.selectBbsCommentList(model);
    }
}