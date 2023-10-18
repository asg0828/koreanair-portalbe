package com.bdp.ap.app.board.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bdp.ap.app.board.model.BbsModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

@ConnMapperFirst
public interface BbsMapper {
	List<BbsModel>selectBbsList(Criteria criteria);
	int selectBbsListCount(Criteria criteria);
	BbsModel selectBbsDetail(BbsModel model);
	long insertBbs(BbsModel model);
	long updateBbs(BbsModel model);
	long deleteBbs(BbsModel model);
	List<BbsModel>selectBbsItemList(BbsModel model);
	List<BbsModel>selectBbsItemMappList(BbsModel model);
	long upsertBbsItemMapping(BbsModel model);
	long deleteBbsItemMapping(BbsModel model);
	List<BbsModel>selectBbsKindList(BbsModel model);
	long upsertBbsKind(BbsModel model);
	long deleteBbsKind(BbsModel model);
	List<BbsModel>selectBbsAuthList(BbsModel model);
	List<BbsModel>selectBbsMngAuthList(BbsModel model);
	long upsertBbsAuth(BbsModel model);
	long deleteBbsAuth(BbsModel model);	
	long createNomalTable(Map map);
	long createReplyTable(Map map);
	long createCommentTable(Map map);
	List<String> selectItemFieldList(Map map);
	long dropTable(Map map);
}
