package com.bdp.ap.app.board.mapper;

import java.util.ArrayList;
import java.util.List;

import com.bdp.ap.app.board.model.AnlzSourceCmtModel;
import com.bdp.ap.app.board.model.AnlzSourceModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

@ConnMapperFirst
public interface AnlzSourceMapper {

	List<AnlzSourceModel> selectAnlzSourceList(Criteria criteria);
	int selectAnlzSourceListCount(Criteria criteria);
	AnlzSourceModel selectAnlzSource(AnlzSourceModel model);
	List<AnlzSourceModel> selectAnlzSourceAuth(AnlzSourceModel model);
	ArrayList<String> selectAnlzSourceAuthList(AnlzSourceModel model);
	List<AnlzSourceCmtModel> selectAnlzSourceCommentList(AnlzSourceModel model);
	long deleteAnlzSource(AnlzSourceModel model);
	long insertAnlzSource(AnlzSourceModel model);
	long insertAnlzSourceAuth(AnlzSourceModel model);
	long insertAnlzSourceCmt(AnlzSourceCmtModel model);
	long updateAnlzSourceCmt(AnlzSourceCmtModel model);
	long deleteAnlzSourceCmt(AnlzSourceCmtModel model);
	long updateAnlzSource(AnlzSourceModel model);
	long deleteAnlzSourceAuth(AnlzSourceModel model);
	long updateAnlzSourceReadCnt(AnlzSourceModel model);
	long deleteRealAnlzSourceCmt(AnlzSourceCmtModel model);
}
