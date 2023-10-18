package com.bdp.ap.app.board.mapper;

import java.util.List;

import com.bdp.ap.app.board.model.QnaModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

@ConnMapperFirst
public interface QnaMapper {

	List<QnaModel> selectQnaList(Criteria criteria);
	int selectQnaListCount(Criteria criteria);
	QnaModel selectQna(QnaModel model);
	long deleteQna(QnaModel model);
	long insertQna(QnaModel model);
	long updateQna(QnaModel model);
	long addViewCntQna(QnaModel model);
	List<QnaModel>selectQnaReplyList(QnaModel model);	
	long qnaStat(QnaModel model);

}
