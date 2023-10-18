package com.bdp.ap.app.dataset.mapper;

import java.util.List;

import com.bdp.ap.app.dataset.model.DataSetCriteria;
import com.bdp.ap.app.dataset.model.DataSetModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface DataSetMapper {
	List<DataSetModel> selectStructTableInfoList(DataSetCriteria criteria);
	int selectStructTableInfoListCount(DataSetCriteria criteria);
	DataSetModel selectStructTableInfoDetail(DataSetCriteria criteria);
	List<DataSetModel>selectStructColumnInfo(DataSetCriteria criteria);
	List<DataSetModel>selectExtrnlTableInfoList(DataSetCriteria criteria);
	int selectExtrnlTableInfoListCount(DataSetCriteria criteria);
	DataSetModel selectExtrnlTableInfoDetail(DataSetCriteria criteria);
	List<DataSetModel>selectExtrnlColumnInfoDetail(DataSetCriteria criteria);
	List<DataSetModel> selectDataComCodeList(DataSetCriteria criteria);
	int selectDataComCodeListCount(DataSetCriteria criteria);
	List<DataSetModel>selectDataComCodeListExcel(DataSetCriteria criteria);
	List<DataSetModel>selectStandardTermsProposalPopList(DataSetCriteria criteria);
	int selectStandardTermsProposalPopListCount(DataSetCriteria criteria);
	DataSetModel selectStandardTermsProposalPopListExcel(DataSetCriteria criteria);
	List<DataSetModel>selectStandardTermsProposalList(DataSetCriteria criteria);
	int selectStandardTermsProposalListCount(DataSetCriteria criteria);
	DataSetModel selectStandardTermsProposalDetail(DataSetCriteria criteria);
	DataSetModel selectTblUseProposalDetail(DataSetCriteria criteria);
	long insertTableInfo(DataSetModel model);
	long updateTableInfo(DataSetModel model);
	long deleteTableInfo(DataSetModel model);
	long insertColumnInfo(DataSetModel model);
	long updateColumnInfo(DataSetModel model);
	long deleteColumnInfo(DataSetModel model);
	long insertHashTag(DataSetModel model);
	long updateHashTag(DataSetModel model);
	long deleteHashTag(DataSetModel model);
	long insertDataComCode(DataSetModel model);
	long updateDataComCode(DataSetModel model);
	long deleteDataComCode(DataSetModel model);
	long insertStandardTermsProposal(DataSetModel model);
	long updateStandardTermsProposal(DataSetModel model);
	long deleteStandardTermsProposal(DataSetModel model);
	long insertTblUseProposal(DataSetModel model);
	long updateTblUseProposal(DataSetModel model);
	long deleteTblUseProposal(DataSetModel model);
	long insertTblUseObject(DataSetModel model);
	long updateTblUseObject(DataSetModel model);
	long deleteTblUseObject(DataSetModel model);
	
	long addViewCntDataSet(String tblInfoId);
	
	List<DataSetModel> selectHashTagList(DataSetCriteria criteria);
	
}
