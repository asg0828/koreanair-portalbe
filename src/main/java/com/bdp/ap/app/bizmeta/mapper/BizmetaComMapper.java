package com.bdp.ap.app.bizmeta.mapper;

import java.util.List;

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
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

/**
 * Mybatis Bizmeta 매핑 Interface
 */
@ConnMapperFirst
public interface BizmetaComMapper {
	public List<RptModel>selectRptList(BizmetaCriteria criteria);
	public long insertRpt(RptModel model);
	public List<DimensionModel>selectDimensiont(BizmetaCriteria criteria);
	public long insertDimension(DimensionModel model);
	public List<MeasureModel>selectMeasure(BizmetaCriteria criteria);
	public long insertMeasure(MeasureModel model);
	public List<KeyItemModel>selectKeyItem(BizmetaCriteria criteria);
	public long insertKeyItem(KeyItemModel model);
	public List<DataMartModel>selectDataMart(BizmetaCriteria criteria);
	public long insertDataMart(DataMartModel model);
	public List<ManageUserModel>selectManageUser(BizmetaCriteria criteria);
	public long insertManageUser(ManageUserModel model);
	public List<ManageDeptModel>selectManageDept(BizmetaCriteria criteria);
	public long insertManageDept(ManageDeptModel model);
	public List<FindTagModel>selectFindTag(BizmetaCriteria criteria);
	public long insertFindTag(FindTagModel model);
	public List<ManageInfoModel>selectManageInfo(BizmetaCriteria criteria);
	public long insertManageInfo(ManageInfoModel model);

	public int selectDataMartCount(BizmetaCriteria criteria);
	public int selectTblListCount(MetaModel model);
	public int selectColListCount(MetaModel model);

	public long deleteRpt(String rptSeId);
	public long deleteDimension(String dimSeId);
	public long deleteMeasure(String mesSeId);
	public long deleteKeyItem(String keyItmSeId);
	public long deleteDataMart(String dtMrtSeId);
	public long deleteManageUser(String mngUserSeId);
	public long deleteManageDept(ComDeptModel model);
	public long deleteFindTag(String fndTagSeId);
	public long deleteManageInfo(String mngInfSeId);

	public List<MetaModel>selectTblList(MetaModel model);
	public List<MetaModel>selectColList(MetaModel model);
	public List<ReportInfoModel>selectReportInfoList(BizmetaCriteria criteria);
	
	public long insertBizMetaFile(BizMetaFileModel model);
	
	public List<FileModel>selectBizMetaFileList(BizMetaFileModel model);
	
	public List<MemberModel> selectMemberList(Criteria criteria);
	public  int selectMemberListCount(Criteria criteria);
	
}
