package com.bdp.ap.app.board.mapper;

import java.util.List;

import com.bdp.ap.app.board.model.FaqModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

@ConnMapperFirst
public interface FaqMapper {

	List<FaqModel> selectFaqList(Criteria criteria);
	int selectFaqListCount(Criteria criteria);
	FaqModel selectFaq(FaqModel model);
	long deleteFaq(FaqModel model);
	long insertFaq(FaqModel model);
	long updateFaq(FaqModel model);

}
