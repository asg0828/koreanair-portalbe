package com.bdp.ap.app.board.mapper;

import java.util.List;

import com.bdp.ap.app.board.model.CooperationCriteria;
import com.bdp.ap.app.board.model.CooperationModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface CooperationMapper {
	List<CooperationModel> selectCooperationList(CooperationCriteria criteria);
	int selectCooperationListCount(CooperationCriteria criteria);
	long insertCooperationMaster(CooperationModel model);
	long insertCooperationPcpt(CooperationModel model);
	long insertCooperationTask(CooperationModel model);
	long insertCooperationComt(CooperationModel model);
	
	long upsertCooperationPcpt(CooperationModel model);
	
	long updateCooperationMaster(CooperationModel model);
	long deleteCooperationPcpt(CooperationModel model);
	long updateCooperationTask(CooperationModel model);
	long updateCooperationComt(CooperationModel model);
	long deleteCooperationMaster(CooperationModel model);
	long deleteCooperationTask(CooperationModel model);
	long deleteCooperationComt(CooperationModel model);
	
	CooperationModel selectCooperationDetail(CooperationCriteria criteria);
	List<CooperationModel> selectCooperationTaskDetail(CooperationCriteria criteria);
	List<CooperationModel> selectCooperationCommentDetail(CooperationCriteria criteria);
	
	CooperationModel selectCooperationComtDt(CooperationModel model);
}
