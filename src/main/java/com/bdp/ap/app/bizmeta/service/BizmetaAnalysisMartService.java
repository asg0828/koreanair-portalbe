package com.bdp.ap.app.bizmeta.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdp.ap.app.bizmeta.mapper.BizmetaAnalysisMartMapper;
import com.bdp.ap.app.bizmeta.mapper.BizmetaComMapper;
import com.bdp.ap.app.bizmeta.model.AnalysisMartModel;
import com.bdp.ap.app.bizmeta.model.BizmetaCriteria;
import com.bdp.ap.app.bizmeta.model.ComDeptModel;
import com.bdp.ap.app.bizmeta.model.DataMartModel;
import com.bdp.ap.app.bizmeta.model.DimensionModel;
import com.bdp.ap.app.bizmeta.model.FindTagModel;
import com.bdp.ap.app.bizmeta.model.KeyItemModel;
import com.bdp.ap.app.bizmeta.model.ManageDeptModel;
import com.bdp.ap.app.bizmeta.model.ManageInfoModel;
import com.bdp.ap.app.bizmeta.model.MeasureModel;
import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.app.file.service.FileService;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.IdUtil;

@Service
public class BizmetaAnalysisMartService {
	@Resource
    private IdUtil idUtil;

	@Resource
    private BizmetaComMapper bizmetaComMapper;

    @Resource(name = "fileService")
	private FileService fileService;

    @Resource
    private BizmetaAnalysisMartMapper bizmetaAnalysisMartMapper;

    public List<AnalysisMartModel>selectAnalysisMartList(BizmetaCriteria criteria){
    	return bizmetaAnalysisMartMapper.selectAnalysisMartList(criteria);
    }

    public int selectAnalysisMartCount(BizmetaCriteria criteria) {
    	return bizmetaAnalysisMartMapper.selectAnalysisMartCount(criteria);
    }

    public List<AnalysisMartModel>selectAnalysisMartExcel(BizmetaCriteria criteria){
    	return bizmetaAnalysisMartMapper.selectAnalysisMartExcel(criteria);
    }

    @Transactional
    public String insertAnalysisMart(AnalysisMartModel model) {

    	//첨부파일저장
    	if(model.getFileIds() != null) {
			FileModel file = null;
			for(String fileId : model.getFileIds()) {
				file = new FileModel();
				file.setFileId(idUtil.getFileId());
				file.setRefId(model.getAnlzMrtId());
				file.setModiId(model.getUserId());
				fileService.updateFile(file);
			}
		}

    	//Key항목
    	if(model.getKeyList() !=null && !model.getKeyList().isEmpty()) {
    		for(String keyNm : model.getKeyList()) {
    			KeyItemModel keyItemModel = new KeyItemModel();
    			keyItemModel.setKeyItmId(idUtil.getKeyItmId());
    			keyItemModel.setKeyItmSeId(model.getAnlzMrtId());
    			keyItemModel.setKeyItmNm(keyNm);
    			keyItemModel.setRgstId(model.getUserId());
    			keyItemModel.setModiId(model.getUserId());
    			keyItemModel.setDelYn("N");
    			bizmetaComMapper.insertKeyItem(keyItemModel);
    		}
    	}

    	//디멘젼저장
    	if(model.getDimList() !=null && !model.getDimList().isEmpty()) {
    		for(String dimNm : model.getDimList()) {
    			DimensionModel dimensionModel = new DimensionModel();
    			dimensionModel.setDimId(idUtil.getDimId());
    			dimensionModel.setDimSeId(model.getAnlzMrtId());
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
    			measureModel.setMesSeId(model.getAnlzMrtId());
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
    			dataMartModel.setDtMrtSeId(model.getAnlzMrtId());
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
	        	findTagModel.setFndTagSeId(model.getAnlzMrtId());
	        	findTagModel.setFndTagNm(fndTagNm);
	        	findTagModel.setRgstId(model.getUserId());
	        	findTagModel.setModiId(model.getUserId());
	        	findTagModel.setDelYn("N");
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
    			manageDeptModel.setMngDeptSeId(model.getAnlzMrtId());
    			manageDeptModel.setRgstId(model.getUserId());
    			manageDeptModel.setModiId(model.getUserId());
    			manageDeptModel.setDelYn("N");
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
    			manageDeptModel.setMngDeptSeId(model.getAnlzMrtId());
    			manageDeptModel.setRgstId(model.getUserId());
    			manageDeptModel.setModiId(model.getUserId());
    			manageDeptModel.setDelYn("N");
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
    		manageInfoModel.setMngInfSeId(model.getAnlzMrtId());
    		manageInfoModel.setRegId(model.getRegId());
    		manageInfoModel.setOpnYn(model.getOpnYn());
    		manageInfoModel.setAppOpin(model.getAppOpin());
    		manageInfoModel.setRgstId(model.getUserId());
    		manageInfoModel.setModiId(model.getUserId());
    		manageInfoModel.setDelYn("N");
    		bizmetaComMapper.insertManageInfo(manageInfoModel);
    	}

    	long count= bizmetaAnalysisMartMapper.insertAnalysisMart(model);

    	if(count > 0) {
            return Constant.DB.INSERT;
        } else {
            return Constant.DB.FAIL;
        }
    }
    @Transactional
    public String updateAnalysisMart(AnalysisMartModel model) {

    	//첨부파일저장
    	if(model.getFileIds() != null) {
			FileModel file = null;
			for(String fileId : model.getFileIds()) {
				file = new FileModel();
				file.setFileId(fileId);
				file.setRefId(model.getAnlzMrtId());
				file.setModiId(model.getUserId());
				fileService.updateFile(file);
			}
		}

    	bizmetaComMapper.deleteKeyItem(model.getAnlzMrtId());
    	//Key항목
    	if(model.getKeyList() !=null && !model.getKeyList().isEmpty()) {
    		for(String keyNm : model.getKeyList()) {
    			KeyItemModel keyItemModel = new KeyItemModel();
    			keyItemModel.setKeyItmId(idUtil.getKeyItmId());
    			keyItemModel.setKeyItmSeId(model.getAnlzMrtId());
    			keyItemModel.setKeyItmNm(keyNm);
    			keyItemModel.setRgstId(model.getUserId());
    			keyItemModel.setModiId(model.getUserId());
    			keyItemModel.setDelYn("N");
    			bizmetaComMapper.insertKeyItem(keyItemModel);
    		}
    	}

    	bizmetaComMapper.deleteDimension(model.getAnlzMrtId());
    	//디멘젼저장
    	if(model.getDimList() !=null && !model.getDimList().isEmpty()) {
    		for(String dimNm : model.getDimList()) {
    			DimensionModel dimensionModel = new DimensionModel();
    			dimensionModel.setDimId(idUtil.getDimId());
    			dimensionModel.setDimSeId(model.getAnlzMrtId());
    			dimensionModel.setDimNm(dimNm);
    			dimensionModel.setRgstId(model.getUserId());
    			dimensionModel.setModiId(model.getUserId());
    			dimensionModel.setDelYn("N");
    			bizmetaComMapper.insertDimension(dimensionModel);
    		}
    	}

    	bizmetaComMapper.deleteMeasure(model.getAnlzMrtId());
    	//메젼저장
    	if(model.getMesList() !=null && !model.getMesList().isEmpty()) {
    		for(String mesNm : model.getMesList()) {
    			MeasureModel measureModel = new MeasureModel();
    			measureModel.setMesId(idUtil.getMesId());
    			measureModel.setMesSeId(model.getAnlzMrtId());
    			measureModel.setMesNm(mesNm);
    			measureModel.setRgstId(model.getUserId());
    			measureModel.setModiId(model.getUserId());
    			measureModel.setDelYn("N");
    			bizmetaComMapper.insertMeasure(measureModel);
    		}
    	}


    	bizmetaComMapper.deleteDataMart(model.getAnlzMrtId());
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
    			dataMartModel.setDtMrtSeId(model.getAnlzMrtId());
    			dataMartModel.setRgstId(model.getUserId());
    			dataMartModel.setModiId(model.getUserId());
    			dataMartModel.setDelYn("N");
    			bizmetaComMapper.insertDataMart(dataMartModel);
    		}
    	}

    	bizmetaComMapper.deleteFindTag(model.getAnlzMrtId());
    	//검색태그저장
    	if(model.getFndTagList() !=null && !model.getFndTagList().isEmpty()){
	    	for(String fndTagNm : model.getFndTagList()) {
	    		FindTagModel findTagModel = new FindTagModel();
	        	findTagModel.setFndTagId(idUtil.getFndTagId());
	        	findTagModel.setFndTagSeId(model.getAnlzMrtId());
	        	findTagModel.setFndTagNm(fndTagNm);
	        	findTagModel.setRgstId(model.getUserId());
	        	findTagModel.setModiId(model.getUserId());
	        	findTagModel.setDelYn("N");
	        	bizmetaComMapper.insertFindTag(findTagModel);    //검색태그저장
	    	}
    	}


    	ComDeptModel comDeptModel = new ComDeptModel();
    	comDeptModel.setMngDeptSeId(model.getAnlzMrtId());
    	comDeptModel.setDeptSe("01");
    	bizmetaComMapper.deleteManageDept(comDeptModel);
    	//관리부서저장
    	if(model.getMngDeptList() !=null && !model.getMngDeptList().isEmpty()){
    		for(String deptCd : model.getMngDeptList()) {
    			ManageDeptModel manageDeptModel = new ManageDeptModel();
    			manageDeptModel.setMngDeptCd(idUtil.getMngDeptCd());
    			manageDeptModel.setDeptCd(deptCd);
    			manageDeptModel.setDeptSe("01");  //관리부서
    			manageDeptModel.setMngDeptSeId(model.getAnlzMrtId());
    			manageDeptModel.setRgstId(model.getUserId());
    			manageDeptModel.setModiId(model.getUserId());
    			manageDeptModel.setDelYn("N");
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
    			manageDeptModel.setMngDeptSeId(model.getAnlzMrtId());
    			manageDeptModel.setRgstId(model.getUserId());
    			manageDeptModel.setModiId(model.getUserId());
    			manageDeptModel.setDelYn("N");
    			bizmetaComMapper.insertManageDept(manageDeptModel);
    		}
    	}

    	bizmetaComMapper.deleteManageInfo(model.getAnlzMrtId());
    	//관리정보저장
    	if((model.getAppOpin() !=null && !"".equals(model.getAppOpin()))
    	    	  || (model.getRegId() !=null && !"".equals(model.getRegId()))
    	    	  || (model.getOpnYn() !=null && !"".equals(model.getOpnYn()))
    	    	) {
    		ManageInfoModel manageInfoModel = new ManageInfoModel();
    		manageInfoModel.setMngInfId(idUtil.getMngInfId());
    		manageInfoModel.setMngInfSeId(model.getAnlzMrtId());
    		manageInfoModel.setRegId(model.getRegId());
    		manageInfoModel.setOpnYn(model.getOpnYn());
    		manageInfoModel.setAppOpin(model.getAppOpin());
    		manageInfoModel.setRgstId(model.getUserId());
    		manageInfoModel.setModiId(model.getUserId());
    		manageInfoModel.setDelYn("N");
    		bizmetaComMapper.insertManageInfo(manageInfoModel);
    	}

    	long count= bizmetaAnalysisMartMapper.updateAnalysisMart(model);
    	if(count > 0) {
            return Constant.DB.UPDATE;
        } else {
            return Constant.DB.FAIL;
        }


    }

    @Transactional
    public String deleteAnalysisMart(AnalysisMartModel model) {

    	FileModel fileDel =new FileModel();
    	fileDel.setRefId(model.getAnlzMrtId());
    	fileDel.setModiId(model.getUserId());
    	fileService.deleteFile(fileDel);

    	bizmetaComMapper.deleteKeyItem(model.getAnlzMrtId());
    	bizmetaComMapper.deleteDimension(model.getAnlzMrtId());
    	bizmetaComMapper.deleteMeasure(model.getAnlzMrtId());
    	bizmetaComMapper.deleteDataMart(model.getAnlzMrtId());
    	bizmetaComMapper.deleteFindTag(model.getAnlzMrtId());

    	ComDeptModel comDeptModel = new ComDeptModel();
    	comDeptModel.setMngDeptSeId(model.getAnlzMrtId());
    	comDeptModel.setDeptSe("01");
    	bizmetaComMapper.deleteManageDept(comDeptModel);

    	comDeptModel.setDeptSe("02");
    	bizmetaComMapper.deleteManageDept(comDeptModel);

    	bizmetaComMapper.deleteManageInfo(model.getAnlzMrtId());

    	long count= bizmetaAnalysisMartMapper.deleteAnalysisMart(model);
    	if(count > 0) {
            return Constant.DB.DELETE;
        } else {
            return Constant.DB.FAIL;
        }
    }

    public AnalysisMartModel selectAnalysisMartDetail(BizmetaCriteria criteria) {
    	return bizmetaAnalysisMartMapper.selectAnalysisMartDetail(criteria);
    }


}
