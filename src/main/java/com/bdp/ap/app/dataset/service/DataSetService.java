package com.bdp.ap.app.dataset.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.dataset.mapper.DataSetMapper;
import com.bdp.ap.app.dataset.model.DataSetCriteria;
import com.bdp.ap.app.dataset.model.DataSetModel;
import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.IdUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataSetService {
	@Resource
    private IdUtil idUtil;	
	
	@Resource(name="commonUtil")
	private CommonUtil commonUtil;	
	
	@Resource
	private CodeService codeService;	
	
	@Resource
	private DataSetMapper dataSetMapper;
	
	public List<DataSetModel> selectStructTableInfoList(DataSetCriteria criteria) {
		return dataSetMapper.selectStructTableInfoList(criteria);
	}
	
	public int selectStructTableInfoListCount(DataSetCriteria criteria) {
		return dataSetMapper.selectStructTableInfoListCount(criteria);
	}
	public DataSetModel selectStructTableInfoDetail(DataSetCriteria criteria) {
		return dataSetMapper.selectStructTableInfoDetail(criteria);
	}
	public List<DataSetModel>selectStructColumnInfo(DataSetCriteria criteria){
		return dataSetMapper.selectStructColumnInfo(criteria);
	}
	public List<DataSetModel>selectExtrnlTableInfoList(DataSetCriteria criteria){
		return dataSetMapper.selectExtrnlTableInfoList(criteria);
	}
	public int selectExtrnlTableInfoListCount(DataSetCriteria criteria) {
		return dataSetMapper.selectExtrnlTableInfoListCount(criteria);
	}
	public DataSetModel selectExtrnlTableInfoDetail(DataSetCriteria criteria) {
		return dataSetMapper.selectExtrnlTableInfoDetail(criteria);
	}
	public List<DataSetModel>selectExtrnlColumnInfoDetail(DataSetCriteria criteria){
		return dataSetMapper.selectExtrnlColumnInfoDetail(criteria);
	}
	public List<DataSetModel> selectDataComCodeList(DataSetCriteria criteria){
		return dataSetMapper.selectDataComCodeList(criteria);
	}
	public int selectDataComCodeListCount(DataSetCriteria criteria) {
		return dataSetMapper.selectDataComCodeListCount(criteria);
	}
	public List<DataSetModel>selectDataComCodeListExcel(DataSetCriteria criteria){
		return dataSetMapper.selectDataComCodeListExcel(criteria);
	}
	public List<DataSetModel>selectStandardTermsProposalPopList(DataSetCriteria criteria){
		return dataSetMapper.selectStandardTermsProposalPopList(criteria);
	}
	public int selectStandardTermsProposalPopListCount(DataSetCriteria criteria) {
		return dataSetMapper.selectStandardTermsProposalPopListCount(criteria);
	}
	public DataSetModel selectStandardTermsProposalPopListExcel(DataSetCriteria criteria) {
		return dataSetMapper.selectStandardTermsProposalPopListExcel(criteria);
	}
	public List<DataSetModel>selectStandardTermsProposalList(DataSetCriteria criteria){
		return dataSetMapper.selectStandardTermsProposalList(criteria);
	}
	public int selectStandardTermsProposalListCount(DataSetCriteria criteria) {
		return dataSetMapper.selectStandardTermsProposalListCount(criteria);
	}
	public DataSetModel selectStandardTermsProposalDetail(DataSetCriteria criteria) {
		return dataSetMapper.selectStandardTermsProposalDetail(criteria);
	}
	public DataSetModel selectTblUseProposalDetail(DataSetCriteria criteria) {
		return dataSetMapper.selectTblUseProposalDetail(criteria);
	}

	public long insertHashTag(DataSetModel model) {
		return dataSetMapper.insertHashTag(model);
	}
	public long updateHashTag(DataSetModel model) {
		return dataSetMapper.updateHashTag(model);
	}
	public long deleteHashTag(DataSetModel model) {
		return dataSetMapper.deleteHashTag(model);
	}
	
	public long insertStandardTermsProposal(DataSetModel model) {
		return dataSetMapper.insertStandardTermsProposal(model);
	}
	public long updateStandardTermsProposal(DataSetModel model) {
		return dataSetMapper.updateStandardTermsProposal(model);
	}
	public long deleteStandardTermsProposal(DataSetModel model) {
		return dataSetMapper.deleteStandardTermsProposal(model);
	}
	
	public long addViewCntDataSet(String tblInfoId) {
		return dataSetMapper.addViewCntDataSet(tblInfoId);
	}
	
	public List<DataSetModel> selectHashTagList(DataSetCriteria criteria) {
		return dataSetMapper.selectHashTagList(criteria);
	}

	
	
}
