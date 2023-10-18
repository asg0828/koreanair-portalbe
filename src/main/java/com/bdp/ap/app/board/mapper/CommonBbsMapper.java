package com.bdp.ap.app.board.mapper;

import java.util.ArrayList;
import java.util.List;
 
import com.bdp.ap.app.board.model.CommonBbsModel;
import com.bdp.ap.app.board.model.CommentModel;
import com.bdp.ap.app.board.model.CommonBbsItemModel;
import com.bdp.ap.app.board.model.CommonBbsMngModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

@ConnMapperFirst
public interface CommonBbsMapper {

    List<CommonBbsModel> selectBbsList(CommonBbsModel model);
    List<CommonBbsModel> selectBbsReplyList(CommonBbsModel model);
    int selectBbsListCount(CommonBbsModel model);
    CommonBbsMngModel selectBbsMng(CommonBbsModel model);
    CommonBbsModel selectBbs(CommonBbsModel model);
    List<CommonBbsItemModel> selectBbsItemList(CommonBbsModel model);
    long insertBbs(CommonBbsModel model);
    long updateBbs(CommonBbsModel model);
	long deleteBbs(CommonBbsModel model);
	long addViewCntBbs(CommonBbsModel model);
    int authChecker(CommonBbsModel model);
    long insertBbsComment(CommentModel model);
    long updateBbsComment(CommentModel model);
    long deleteBbsComment(CommentModel model);
    List<CommentModel> selectBbsCommentList(CommonBbsModel model);

}