package com.bdp.ap.app.bizmeta.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bdp.ap.app.bizmeta.mapper.BizmetaComMapper;
import com.bdp.ap.app.bizmeta.model.BizMetaFileModel;
import com.bdp.ap.app.bizmeta.model.BizmetaCriteria;
import com.bdp.ap.app.bizmeta.model.ComDeptModel;
import com.bdp.ap.app.bizmeta.model.DataMartModel;
import com.bdp.ap.app.bizmeta.model.DimensionModel;
import com.bdp.ap.app.bizmeta.model.FindTagModel;
import com.bdp.ap.app.bizmeta.model.KeyItemModel;
import com.bdp.ap.app.bizmeta.model.ManageDeptModel;
import com.bdp.ap.app.bizmeta.model.ManageInfoModel;
import com.bdp.ap.app.bizmeta.model.ManageUserModel;
import com.bdp.ap.app.bizmeta.model.MeasureModel;
import com.bdp.ap.app.bizmeta.model.MetaModel;
import com.bdp.ap.app.bizmeta.model.ReportInfoModel;
import com.bdp.ap.app.bizmeta.model.RptModel;
import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.app.member.model.MemberModel;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.common.paging.Criteria;

@Service
public class BizmetaComService {
	@Resource
    private IdUtil idUtil;

    @Resource
    private BizmetaComMapper bizmetaComMapper;

    public List<RptModel>selectRptListBizmeta(BizmetaCriteria criteria){
    	List<RptModel>modelList = bizmetaComMapper.selectRptList(criteria);
    	return modelList;
    }

    public List<DimensionModel>selectDimensiont(BizmetaCriteria criteria){
    	List<DimensionModel>modelList = bizmetaComMapper.selectDimensiont(criteria);
    	return modelList;
    }
    public List<MeasureModel>selectMeasure(BizmetaCriteria criteria){
    	List<MeasureModel>modelList = bizmetaComMapper.selectMeasure(criteria);
    	return modelList;
    }

    public List<KeyItemModel>selectKeyItem(BizmetaCriteria criteria){
    	List<KeyItemModel>modelList = bizmetaComMapper.selectKeyItem(criteria);
    	return modelList;
    }

    public List<DataMartModel>selectDataMart(BizmetaCriteria criteria){
    	List<DataMartModel>modelList = bizmetaComMapper.selectDataMart(criteria);
    	return modelList;
    }

    public List<ManageUserModel>selectManageUser(BizmetaCriteria criteria){
    	List<ManageUserModel>modelList = bizmetaComMapper.selectManageUser(criteria);
    	return modelList;
    }

    public List<ManageDeptModel>selectManageDept(BizmetaCriteria criteria){
    	List<ManageDeptModel>modelList = bizmetaComMapper.selectManageDept(criteria);
    	return modelList;
    }

    public List<FindTagModel>selectFindTag(BizmetaCriteria criteria){
    	List<FindTagModel>modelList = bizmetaComMapper.selectFindTag(criteria);
    	return modelList;
    }

    public List<ManageInfoModel>selectManageInfo(BizmetaCriteria criteria){
    	List<ManageInfoModel>modelList = bizmetaComMapper.selectManageInfo(criteria);
    	return modelList;
    }

    public long insertRpt(RptModel model) {
    	return bizmetaComMapper.insertRpt(model);
    }

    public long insertDimension(DimensionModel model) {
    	return bizmetaComMapper.insertDimension(model);
    }

    public long insertMeasure(MeasureModel model) {
    	return bizmetaComMapper.insertMeasure(model);
    }

    public long insertKeyItem(KeyItemModel model) {
    	return bizmetaComMapper.insertKeyItem(model);
    }

    public long insertDataMart(DataMartModel model) {
    	return bizmetaComMapper.insertDataMart(model);
    }

    public long insertManageUser(ManageUserModel model) {
    	return bizmetaComMapper.insertManageUser(model);
    }

    public long insertManageDept(ManageDeptModel model) {
    	return bizmetaComMapper.insertManageDept(model);
    }

    public long insertFindTag(FindTagModel model) {
    	return bizmetaComMapper.insertFindTag(model);
    }

    public long insertManageInfo(ManageInfoModel model) {
    	return bizmetaComMapper.insertManageInfo(model);
    }

    public long deleteRpt(String rptSeId) {
    	return bizmetaComMapper.deleteRpt(rptSeId);
    }

    public long deleteDimension(String dimSeId) {
    	return bizmetaComMapper.deleteDimension(dimSeId);
    }

    public long deleteMeasure(String mesSeId) {
    	return bizmetaComMapper.deleteMeasure(mesSeId);
    }

    public long deleteKeyItem(String keyItmSeId) {
    	return bizmetaComMapper.deleteKeyItem(keyItmSeId);
    }

    public long deleteDataMart(String dtMrtSeId) {
    	return bizmetaComMapper.deleteDataMart(dtMrtSeId);
    }

    public long deleteManageUser(String mngUserSeId) {
    	return bizmetaComMapper.deleteManageUser(mngUserSeId);
    }

    public long deleteManageDept(ComDeptModel model) {
    	return bizmetaComMapper.deleteManageDept(model);
    }

    public long deleteFindTag(String fndTagSeId) {
    	return bizmetaComMapper.deleteFindTag(fndTagSeId);
    }
    public long deleteManageInfo(String mngInfSeId) {
    	return bizmetaComMapper.deleteManageInfo(mngInfSeId);
    }

    public List<MetaModel>selectTblList(MetaModel model){
    	return bizmetaComMapper.selectTblList(model);
    }
	public List<MetaModel>selectColList(MetaModel model){
		return bizmetaComMapper.selectColList(model);
	}

	public int selectDataMartCount(BizmetaCriteria criteria) {
		return bizmetaComMapper.selectDataMartCount(criteria);
	}
	public int selectTblListCount(MetaModel model) {
		return bizmetaComMapper.selectTblListCount(model);
	}

	public int selectColListCount(MetaModel model) {
		return bizmetaComMapper.selectColListCount(model);
	}
	
	public List<ReportInfoModel>selectReportInfoList(BizmetaCriteria criteria){
		return bizmetaComMapper.selectReportInfoList(criteria);
	}
	
	public long insertBizMetaFile(BizMetaFileModel model) {
		return bizmetaComMapper.insertBizMetaFile(model);
	}
	
	public List<FileModel>selectBizMetaFileList(BizMetaFileModel model){
		return bizmetaComMapper.selectBizMetaFileList(model);
	}
	
	public List<MemberModel> selectMemberList(Criteria criteria) {
        return bizmetaComMapper.selectMemberList(criteria);
    }
    public int selectMemberListCount(Criteria criteria) {
        return bizmetaComMapper.selectMemberListCount(criteria);
    }

}
