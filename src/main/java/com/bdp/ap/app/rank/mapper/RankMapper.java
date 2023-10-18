package com.bdp.ap.app.rank.mapper;

import java.util.List;

import com.bdp.ap.app.rank.model.RankModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface RankMapper {
	List<RankModel> select(RankModel model);
	long insert(RankModel model);
	long update(RankModel model);
	long delete(RankModel model);
	long insertHist(String companyCode);
	long deleteAll(String companyCode);
}
