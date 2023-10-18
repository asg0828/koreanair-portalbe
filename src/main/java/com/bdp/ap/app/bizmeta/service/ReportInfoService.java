package com.bdp.ap.app.bizmeta.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdp.ap.app.bizmeta.mapper.BizmetaComMapper;
import com.bdp.ap.app.bizmeta.mapper.BizmetaReportInfoMapper;
import com.bdp.ap.app.bizmeta.model.BizmetaCriteria;
import com.bdp.ap.app.bizmeta.model.ComDeptModel;
import com.bdp.ap.app.bizmeta.model.DataMartModel;
import com.bdp.ap.app.bizmeta.model.DimensionModel;
import com.bdp.ap.app.bizmeta.model.FindTagModel;
import com.bdp.ap.app.bizmeta.model.ManageDeptModel;
import com.bdp.ap.app.bizmeta.model.ManageInfoModel;
import com.bdp.ap.app.bizmeta.model.MeasureModel;
import com.bdp.ap.app.bizmeta.model.ReportInfoModel;
import com.bdp.ap.app.file.service.FileService;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.IdUtil;

@Service
public class ReportInfoService {
	@Resource
    private IdUtil idUtil;

    @Resource
    private BizmetaReportInfoMapper bizmetaReportInfoMapper;

    @Resource
    private BizmetaComMapper bizmetaComMapper;

    @Resource(name = "fileService")
	private FileService fileService;

    public List<ReportInfoModel> selectReportInfoList(BizmetaCriteria criteria){
    	List<ReportInfoModel>modelList = bizmetaReportInfoMapper.selectReportInfoList(criteria);
    	return modelList;
    }

    public int selectReportInfoCount(BizmetaCriteria criteria) {
    	return bizmetaReportInfoMapper.selectReportInfoCount(criteria);
    }

    public List<ReportInfoModel> selectReportInfoExcel(BizmetaCriteria criteria){
    	List<ReportInfoModel>modelList = bizmetaReportInfoMapper.selectReportInfoExcel(criteria);
    	return modelList;
    }


    @Transactional
    public String insertReportInfo(ReportInfoModel model) {

    	//디멘젼저장
    	if(model.getDimList() !=null && !model.getDimList().isEmpty()) {
    		for(String dimNm : model.getDimList()) {
    			DimensionModel dimensionModel = new DimensionModel();
    			dimensionModel.setDimId(idUtil.getDimId());
    			dimensionModel.setDimSeId(model.getRptInfId());
    			dimensionModel.setDimNm(dimNm);
    			dimensionModel.setRgstId(model.getUserId());
    			dimensionModel.setModiId(model.getUserId());
    			dimensionModel.setDelYn("N");
    			bizmetaComMapper.insertDimension(dimensionModel);
    		}
    	}

    	//메젼저장
    	if(model.getMesList() !=null && !model.getMesList().isEmpty()) {
    		for(String mesNm : model.getMesList()) {
    			MeasureModel measureModel = new MeasureModel();
    			measureModel.setMesId(idUtil.getMesId());
    			measureModel.setMesSeId(model.getRptInfId());
    			measureModel.setMesNm(mesNm);
    			measureModel.setRgstId(model.getUserId());
    			measureModel.setModiId(model.getUserId());
    			measureModel.setDelYn("N");
    			bizmetaComMapper.insertMeasure(measureModel);
    		}
    	}

    	//데이터원천 = dtMrtList=테이블ID:테이블명@테이블ID:테이블명@
    	if(model.getDtMrtList() !=null && !model.getDtMrtList().isEmpty()) {
    		for(String dtMrtNm : model.getDtMrtList()) {
    			DataMartModel dataMartModel = new DataMartModel();
    			if(dtMrtNm !=null && !dtMrtNm.isEmpty()) {
    	    		String[] arr = dtMrtNm.split("@");
    	    		for(String str : arr) {
    	    			if (str !=null && !str.isEmpty()) {
    	    				String[] arr1 = str.split(":");
    	    				int idx=0;
    	    				for(String str1 : arr1) {
    	    					idx++;
    	    					if(idx==1) {		//테이블ID
    	    						dataMartModel.setTblId(str1);
    	    					}else if(idx==2) { //테이블명
    	    						dataMartModel.setTblNm(str1);
    	    					}
    						}
    	    			}
    	    		}
    	    	}

    			dataMartModel.setDtMrtId(idUtil.getDtMrtId());
    			dataMartModel.setDtMrtSeId(model.getRptInfId());
    			dataMartModel.setRgstId(model.getUserId());
    			dataMartModel.setModiId(model.getUserId());
    			dataMartModel.setDelYn("N");
    			bizmetaComMapper.insertDataMart(dataMartModel);
    		}
    	}

    	//검색태그저장
    	if(model.getFndTagList() !=null && !model.getFndTagList().isEmpty()){
	    	for(String fndTagNm : model.getFndTagList()) {
	    		FindTagModel findTagModel = new FindTagModel();
	        	findTagModel.setFndTagId(idUtil.getFndTagId());
	        	findTagModel.setFndTagSeId(model.getRptInfId());
	        	findTagModel.setFndTagNm(fndTagNm);
	        	findTagModel.setRgstId(model.getUserId());
	        	findTagModel.setModiId(model.getUserId());
	        	findTagModel.setDelYn("N");
	        	findTagModel.setRefVer(0);
	        	bizmetaComMapper.insertFindTag(findTagModel);    //검색태그저장
	    	}
    	}

    	//관리부서저장
    	if(model.getMngDeptList() !=null && !model.getMngDeptList().isEmpty()){
    		for(String deptCd : model.getMngDeptList()) {
    			ManageDeptModel manageDeptModel = new ManageDeptModel();
    			manageDeptModel.setMngDeptCd(idUtil.getMngDeptCd());
    			manageDeptModel.setDeptCd(deptCd);
    			manageDeptModel.setDeptSe("01");  //관리부서
    			manageDeptModel.setMngDeptSeId(model.getRptInfId());
    			manageDeptModel.setRgstId(model.getUserId());
    			manageDeptModel.setModiId(model.getUserId());
    			manageDeptModel.setDelYn("N");
    			manageDeptModel.setRefVer(0);
    			bizmetaComMapper.insertManageDept(manageDeptModel);
    		}
    	}

    	//기피부서저장
    	if(model.getAvoidDeptList() !=null && !model.getAvoidDeptList().isEmpty()){
    		for(String deptCd : model.getAvoidDeptList()) {
    			ManageDeptModel manageDeptModel = new ManageDeptModel();
    			manageDeptModel.setMngDeptCd(idUtil.getMngDeptCd());
    			manageDeptModel.setDeptCd(deptCd);
    			manageDeptModel.setDeptSe("02");  //기피부서
    			manageDeptModel.setMngDeptSeId(model.getRptInfId());
    			manageDeptModel.setRgstId(model.getUserId());
    			manageDeptModel.setModiId(model.getUserId());
    			manageDeptModel.setDelYn("N");
    			manageDeptModel.setRefVer(0);
    			bizmetaComMapper.insertManageDept(manageDeptModel);
    		}
    	}

    	//관리정보저장
    	if((model.getAppOpin() !=null && !"".equals(model.getAppOpin()))
    	    	  || (model.getRegId() !=null && !"".equals(model.getRegId()))
    	    	  || (model.getOpnYn() !=null && !"".equals(model.getOpnYn()))
    	 ) {
    		ManageInfoModel manageInfoModel = new ManageInfoModel();
    		manageInfoModel.setMngInfId(idUtil.getMngInfId());
    		manageInfoModel.setMngInfSeId(model.getRptInfId());
    		manageInfoModel.setRegId(model.getRegId());
    		manageInfoModel.setOpnYn(model.getOpnYn());
    		manageInfoModel.setAppOpin(model.getAppOpin());
    		manageInfoModel.setRgstId(model.getUserId());
    		manageInfoModel.setModiId(model.getUserId());
    		manageInfoModel.setDelYn("N");
    		manageInfoModel.setRefVer(0);
    		bizmetaComMapper.insertManageInfo(manageInfoModel);
    	}


    	long count= bizmetaReportInfoMapper.insertReportInfo(model);

    	if(count > 0) {
            return Constant.DB.INSERT;
        } else {
            return Constant.DB.FAIL;
        }
    }

    @Transactional
    public String updateReportInfo(ReportInfoModel model) {


    	bizmetaComMapper.deleteDimension(model.getRptInfId());
    	//디멘젼저장
    	if(model.getDimList() !=null && !model.getDimList().isEmpty()) {
    		for(String dimNm : model.getDimList()) {
    			DimensionModel dimensionModel = new DimensionModel();
    			dimensionModel.setDimId(idUtil.getDimId());
    			dimensionModel.setDimSeId(model.getRptInfId());
    			dimensionModel.setDimNm(dimNm);
    			dimensionModel.setRgstId(model.getUserId());
    			dimensionModel.setModiId(model.getUserId());
    			dimensionModel.setDelYn("N");
    			bizmetaComMapper.insertDimension(dimensionModel);
    		}
    	}

    	bizmetaComMapper.deleteMeasure(model.getRptInfId());
    	//메젼저장
    	if(model.getMesList() !=null && !model.getMesList().isEmpty()) {
    		for(String mesNm : model.getMesList()) {
    			MeasureModel measureModel = new MeasureModel();
    			measureModel.setMesId(idUtil.getMesId());
    			measureModel.setMesSeId(model.getRptInfId());
    			measureModel.setMesNm(mesNm);
    			measureModel.setRgstId(model.getUserId());
    			measureModel.setModiId(model.getUserId());
    			measureModel.setDelYn("N");
    			bizmetaComMapper.insertMeasure(measureModel);
    		}
    	}


    	bizmetaComMapper.deleteDataMart(model.getRptInfId());
    	//데이터원천 = dtMrtList=테이블ID:테이블명@테이블ID:테이블명@
    	if(model.getDtMrtList() !=null && !model.getDtMrtList().isEmpty()) {
    		for(String dtMrtNm : model.getDtMrtList()) {
    			DataMartModel dataMartModel = new DataMartModel();
    			if(dtMrtNm !=null && !dtMrtNm.isEmpty()) {
    	    		String[] arr = dtMrtNm.split("@");
    	    		for(String str : arr) {
    	    			if (str !=null && !str.isEmpty()) {
    	    				String[] arr1 = str.split(":");
    	    				int idx=0;
    	    				for(String str1 : arr1) {
    	    					idx++;
    	    					if(idx==1) {		//테이블ID
    	    						dataMartModel.setTblId(str1);
    	    					}else if(idx==2) { //테이블명
    	    						dataMartModel.setTblNm(str1);
    	    					}
    						}
    	    			}
    	    		}
    	    	}

    			dataMartModel.setDtMrtId(idUtil.getDtMrtId());
    			dataMartModel.setDtMrtSeId(model.getRptInfId());
    			dataMartModel.setRgstId(model.getUserId());
    			dataMartModel.setModiId(model.getUserId());
    			dataMartModel.setDelYn("N");
    			bizmetaComMapper.insertDataMart(dataMartModel);
    		}
    	}


    	bizmetaComMapper.deleteFindTag(model.getRptInfId());
    	//검색태그저장
    	if(model.getFndTagList() !=null && !model.getFndTagList().isEmpty()){
	    	for(String fndTagNm : model.getFndTagList()) {
	    		FindTagModel findTagModel = new FindTagModel();
	        	findTagModel.setFndTagId(idUtil.getFndTagId());
	        	findTagModel.setFndTagSeId(model.getRptInfId());
	        	findTagModel.setFndTagNm(fndTagNm);
	        	findTagModel.setRgstId(model.getUserId());
	        	findTagModel.setModiId(model.getUserId());
	        	findTagModel.setDelYn("N");
	        	findTagModel.setRefVer(0);
	        	bizmetaComMapper.insertFindTag(findTagModel);    //검색태그저장
	    	}
    	}

    	ComDeptModel comDeptModel = new ComDeptModel();
    	comDeptModel.setMngDeptSeId(model.getRptInfId());
    	comDeptModel.setDeptSe("01");
    	bizmetaComMapper.deleteManageDept(comDeptModel);
    	//관리부서저장
    	if(model.getMngDeptList() !=null && !model.getMngDeptList().isEmpty()){
    		for(String deptCd : model.getMngDeptList()) {
    			ManageDeptModel manageDeptModel = new ManageDeptModel();
    			manageDeptModel.setMngDeptCd(idUtil.getMngDeptCd());
    			manageDeptModel.setDeptCd(deptCd);
    			manageDeptModel.setDeptSe("01");  //관리부서
    			manageDeptModel.setMngDeptSeId(model.getRptInfId());
    			manageDeptModel.setRgstId(model.getUserId());
    			manageDeptModel.setModiId(model.getUserId());
    			manageDeptModel.setDelYn("N");
    			manageDeptModel.setRefVer(0);
    			bizmetaComMapper.insertManageDept(manageDeptModel);
    		}
    	}

    	comDeptModel.setDeptSe("02");
    	bizmetaComMapper.deleteManageDept(comDeptModel);
    	//기피부서저장
    	if(model.getAvoidDeptList() !=null && !model.getAvoidDeptList().isEmpty()){
    		for(String deptCd : model.getAvoidDeptList()) {
    			ManageDeptModel manageDeptModel = new ManageDeptModel();
    			manageDeptModel.setMngDeptCd(idUtil.getMngDeptCd());
    			manageDeptModel.setDeptCd(deptCd);
    			manageDeptModel.setDeptSe("02");  //기피부서
    			manageDeptModel.setMngDeptSeId(model.getRptInfId());
    			manageDeptModel.setRgstId(model.getUserId());
    			manageDeptModel.setModiId(model.getUserId());
    			manageDeptModel.setDelYn("N");
    			manageDeptModel.setRefVer(0);
    			bizmetaComMapper.insertManageDept(manageDeptModel);
    		}
    	}


    	bizmetaComMapper.deleteManageInfo(model.getRptInfId());
    	//관리정보저장
    	if((model.getAppOpin() !=null && !"".equals(model.getAppOpin()))
    	    	  || (model.getRegId() !=null && !"".equals(model.getRegId()))
    	    	  || (model.getOpnYn() !=null && !"".equals(model.getOpnYn()))
    	 ) {
    		ManageInfoModel manageInfoModel = new ManageInfoModel();
    		manageInfoModel.setMngInfId(idUtil.getMngInfId());
    		manageInfoModel.setMngInfSeId(model.getRptInfId());
    		manageInfoModel.setRegId(model.getRegId());
    		manageInfoModel.setOpnYn(model.getOpnYn());
    		manageInfoModel.setAppOpin(model.getAppOpin());
    		manageInfoModel.setRgstId(model.getUserId());
    		manageInfoModel.setModiId(model.getUserId());
    		manageInfoModel.setDelYn("N");
    		manageInfoModel.setRefVer(0);
    		bizmetaComMapper.insertManageInfo(manageInfoModel);
    	} 
    	
    	long count= bizmetaReportInfoMapper.updateReportInfo(model); 
    	if(count > 0) {
            return Constant.DB.UPDATE;
        } else {
            return Constant.DB.FAIL;
        }
    }

  //보고서상세 삭제
    @Transactional
    public String deleteReportInfo(ReportInfoModel model) {

    	bizmetaComMapper.deleteDimension(model.getRptInfId());
    	bizmetaComMapper.deleteMeasure(model.getRptInfId());
    	bizmetaComMapper.deleteDataMart(model.getRptInfId());
    	bizmetaComMapper.deleteFindTag(model.getRptInfId());

    	ComDeptModel comDeptModel = new ComDeptModel();
    	comDeptModel.setMngDeptSeId(model.getRptInfId());
    	comDeptModel.setDeptSe("01");
    	bizmetaComMapper.deleteManageDept(comDeptModel);

    	comDeptModel.setDeptSe("02");
    	bizmetaComMapper.deleteManageDept(comDeptModel);

    	long count= bizmetaReportInfoMapper.deleteReportInfo(model);

    	if(count > 0) {
            return Constant.DB.DELETE;
        } else {
            return Constant.DB.FAIL;
        }
    }

    //보고서상세조회
    public ReportInfoModel selectReportInfoDetail(BizmetaCriteria criteria) {
    	return bizmetaReportInfoMapper.selectReportInfoDetail(criteria);
    }



}
