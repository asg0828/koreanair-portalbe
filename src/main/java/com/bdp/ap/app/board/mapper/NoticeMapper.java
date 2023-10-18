package com.bdp.ap.app.board.mapper;

import java.util.List;

import com.bdp.ap.app.board.model.NoticeModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

@ConnMapperFirst
public interface NoticeMapper {

	List<NoticeModel> selectNoticeList(Criteria criteria);
	int selectNoticeListCount(Criteria criteria);
	NoticeModel selectNotice(NoticeModel model);
	long deleteNotice(NoticeModel model);
	long insertNotice(NoticeModel model);
	long updateNotice(NoticeModel model);
	long addViewCntNotice(NoticeModel model);

}
