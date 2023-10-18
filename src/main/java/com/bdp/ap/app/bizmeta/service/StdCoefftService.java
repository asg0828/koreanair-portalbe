package com.bdp.ap.app.bizmeta.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdp.ap.app.bizmeta.mapper.BizmetaComMapper;
import com.bdp.ap.app.bizmeta.mapper.StdCoefftMapper;
import com.bdp.ap.app.bizmeta.model.AnalysisMartModel;
import com.bdp.ap.app.bizmeta.model.BizMetaFileModel;
import com.bdp.ap.app.bizmeta.model.BizmetaCriteria;
import com.bdp.ap.app.bizmeta.model.BizmetaModel;
import com.bdp.ap.app.bizmeta.model.ComDeptModel;
import com.bdp.ap.app.bizmeta.model.FindTagModel;
import com.bdp.ap.app.bizmeta.model.ManageDeptModel;
import com.bdp.ap.app.bizmeta.model.ManageInfoModel;
import com.bdp.ap.app.bizmeta.model.ManageUserModel;
import com.bdp.ap.app.bizmeta.model.RptModel;
import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.app.file.service.FileService;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.IdUtil;

/**
 * Bizmeta 서비스 클래스
 */
@Service
public class StdCoefftService {

	@Resource
    private IdUtil idUtil;

    @Resource
    private StdCoefftMapper stdCoefftMapper;

    @Resource
    private BizmetaComMapper bizmetaComMapper;

    @Resource(name = "fileService")
	private FileService fileService;

    public List<BizmetaModel>selectStdCoefftList(BizmetaCriteria criteria){
    	List<BizmetaModel>modelList = stdCoefftMapper.selectStdCoefftList(criteria);
    	return modelList;
    }

    public int selectStdCoefftListCount(BizmetaCriteria criteria) {
    	return stdCoefftMapper.selectStdCoefftListCount(criteria);
    }

    public List<BizmetaModel>selectStdCoefftExcel(BizmetaCriteria criteria){
    	List<BizmetaModel>modelList = stdCoefftMapper.selectStdCoefftExcel(criteria);
    	return modelList;
    }
    
    public String selectStdCoefftHistSeq(BizmetaModel model) {
    	return selectStdCoefftHistSeq(model);
    }
    
    
    @Transactional
    public String insertStdCoefft(BizmetaModel model) {

    	//표준계수보고서 저장 rpt=보고서ID:보고서구분ID:보고서명
    	if(model.getRptList() !=null && !model.getRptList().isEmpty()) {
    		for(String rpt : model.getRptList()) {
    	    	RptModel rptModel = new RptModel();
    	    			if (rpt !=null && !rpt.isEmpty()) {
    	    				String[] arr1 = rpt.split(":");
    	    				int idx=0;
    	    				for(String str1 : arr1) {
    	    					idx++;
    	    					if(idx==1) {		//보고서ID
    	    						rptModel.setRefId(str1);
    	    					}else if(idx==2) { //보고서구분
    	    						rptModel.setRptSe(str1);
    	    					}else if(idx==3) { //보고서명
    	    						rptModel.setRptNm(str1);
    	    					}
    						}
    	    			}
    	    	rptModel.setRptId(idUtil.getRptId());
    	    	rptModel.setRptSeId(model.getStdCoefftId());
    	    	rptModel.setRgstId(model.getUserId());
    	    	rptModel.setModiId(model.getUserId());
    	    	rptModel.setRefVer(model.getSeq());
    	    	rptModel.setDelYn("N");


    	    	bizmetaComMapper.insertRpt(rptModel);
    		}
    	}
    	
    	
    	long count= stdCoefftMapper.insertStdCoefft(model);
    	
    	stdCoefftMapper.insertStdCoefftHist(model);   				//표준계수이력저장
    	model.setSeq(stdCoefftMapper.selectStdCoefftHistSeq(model.getStdCoefftId()));
    	
    	//첨부파일저장
    	if(model.getFileIds() != null) {
			FileModel file = null;
			BizMetaFileModel bizMetaFile = null;
			for(String fileId : model.getFileIds()) {
				file = new FileModel();
				file.setFileId(fileId);
				file.setRefId(model.getStdCoefftId());
				file.setModiId(model.getUserId());
				fileService.updateFile(file);
				
				bizMetaFile = new BizMetaFileModel();
				bizMetaFile.setBizFileId(idUtil.getBizFileId());
				bizMetaFile.setFileId(fileId);
				bizMetaFile.setFileSeId(model.getStdCoefftId());
				bizMetaFile.setRefVer(model.getSeq());
				bizMetaFile.setDelYn("N");
				bizMetaFile.setRgstId(model.getUserId());
				bizMetaFile.setModiId(model.getUserId());
				bizmetaComMapper.insertBizMetaFile(bizMetaFile);
				
			}
			
		} 
    	//관리담당자저장
    	if(model.getUserIdList()!=null &&  !model.getUserIdList().isEmpty()){
    		for(String userId : model.getUserIdList()) {
    			ManageUserModel manageUserModel = new ManageUserModel();
    			manageUserModel.setMngUserId(idUtil.getMngUserId());
    			manageUserModel.setUserId(userId);
    			manageUserModel.setMngUserSeId(model.getStdCoefftId());
    			manageUserModel.setRefVer(model.getSeq());
    			manageUserModel.setRgstId(model.getUserId());
    			manageUserModel.setModiId(model.getUserId());
    			manageUserModel.setDelYn("N");
    			bizmetaComMapper.insertManageUser(manageUserModel);
    		}
    	}

    	//관리부서저장
    	if(model.getDeptCdList() !=null && !model.getDeptCdList().isEmpty()){
    		for(String deptCd : model.getDeptCdList()) {
    			ManageDeptModel manageDeptModel = new ManageDeptModel();
    			manageDeptModel.setMngDeptCd(idUtil.getMngDeptCd());
    			manageDeptModel.setDeptCd(deptCd);
    			manageDeptModel.setDeptSe("01");  //관리부서
    			manageDeptModel.setMngDeptSeId(model.getStdCoefftId());
    			manageDeptModel.setRefVer(model.getSeq());
    			manageDeptModel.setRgstId(model.getUserId());
    			manageDeptModel.setModiId(model.getUserId());
    			manageDeptModel.setDelYn("N");
    			bizmetaComMapper.insertManageDept(manageDeptModel);
    		}
    	}

    	//검색태그저장
    	if(model.getFndTagList()!=null && !model.getFndTagList().isEmpty()){
	    	for(String fndTagNm : model.getFndTagList()) {
	    		FindTagModel findTagModel = new FindTagModel();
	        	findTagModel.setFndTagId(idUtil.getFndTagId());
	        	findTagModel.setFndTagSeId(model.getStdCoefftId());
	        	findTagModel.setRefVer(model.getSeq());
	        	findTagModel.setFndTagNm(fndTagNm);
	        	findTagModel.setRgstId(model.getUserId());
	        	findTagModel.setModiId(model.getUserId());
	        	findTagModel.setDelYn("N");
	        	bizmetaComMapper.insertFindTag(findTagModel);    //검색태그저장
	    	}
    	}

    	//관리정보저장
    	if(model.getAppOpin() !=null && !"".equals(model.getAppOpin())) {
    		ManageInfoModel manageInfoModel = new ManageInfoModel();
    		manageInfoModel.setMngInfId(idUtil.getMngInfId());
    		manageInfoModel.setMngInfSeId(model.getStdCoefftId());
    		manageInfoModel.setRefVer(model.getSeq());
    		manageInfoModel.setAppOpin(model.getAppOpin());
    		manageInfoModel.setRgstId(model.getUserId());
    		manageInfoModel.setModiId(model.getUserId());
    		manageInfoModel.setDelYn("N");
    		bizmetaComMapper.insertManageInfo(manageInfoModel);
    	}
    	 
    	
    	if(count > 0) {
            return Constant.DB.INSERT;
        } else {
            return Constant.DB.FAIL;
        }
    }

    @Transactional
    public String updateStdCoefft(BizmetaModel model) {
    	
    	long count= stdCoefftMapper.updateStdCoefft(model);
    	
    	stdCoefftMapper.insertStdCoefftHist(model);   				//표준계수이력저장
    	
    	model.setSeq(stdCoefftMapper.selectStdCoefftHistSeq(model.getStdCoefftId()));
    	
    	//bizmetaComMapper.deleteRpt(model.getStdCoefftId());

    	//표준계수보고서 저장 rpt=보고서ID:보고서구분ID:보고서명
    	if(model.getRptList() !=null && !model.getRptList().isEmpty()) {
    		for(String rpt : model.getRptList()) {
    	    	RptModel rptModel = new RptModel();
    			if (rpt !=null && !rpt.isEmpty()) {
    				String[] arr1 = rpt.split(":");
    				int idx=0;
    				for(String str1 : arr1) {
    					idx++;
    					if(idx==1) {		//보고서ID
    						rptModel.setRefId(str1);
    					}else if(idx==2) { //보고서구분
    						rptModel.setRptSe(str1);
    					}else if(idx==3) { //보고서명
    						rptModel.setRptNm(str1);
    					}
					}
    			}
    	    	rptModel.setRptId(idUtil.getRptId());
    	    	rptModel.setRptSeId(model.getStdCoefftId());
    	    	rptModel.setRefVer(model.getSeq());
    	    	rptModel.setRgstId(model.getUserId());
    	    	rptModel.setModiId(model.getUserId());
    	    	rptModel.setDelYn("N");

    	    	bizmetaComMapper.insertRpt(rptModel);
    		}
    	} 

    	//첨부파일저장
    	if(model.getFileIds() != null) {
			FileModel file = null;
			BizMetaFileModel bizMetaFile = null;
			for(String fileId : model.getFileIds()) {
				file = new FileModel(); 
				file.setFileId(fileId);
				file.setRefId(model.getStdCoefftId());
				file.setModiId(model.getUserId());
								
				fileService.updateFile(file);
				
				bizMetaFile = new BizMetaFileModel();
				bizMetaFile.setBizFileId(idUtil.getBizFileId());
				bizMetaFile.setFileId(fileId);
				bizMetaFile.setFileSeId(model.getStdCoefftId());
				bizMetaFile.setRefVer(model.getSeq());
				bizMetaFile.setDelYn("N");
				bizMetaFile.setRgstId(model.getUserId());
				bizMetaFile.setModiId(model.getUserId());
				bizmetaComMapper.insertBizMetaFile(bizMetaFile);
			} 
		}

    	//bizmetaComMapper.deleteManageUser(model.getStdCoefftId());
    	//관리담당자저장
    	if(model.getUserIdList() !=null && !model.getUserIdList().isEmpty()){
    		for(String userId : model.getUserIdList()) {
    			ManageUserModel manageUserModel = new ManageUserModel();
    			manageUserModel.setMngUserId(idUtil.getMngUserId());
    			manageUserModel.setUserId(userId);
    			manageUserModel.setMngUserSeId(model.getStdCoefftId());
    			manageUserModel.setRefVer(model.getSeq());
    			manageUserModel.setRgstId(model.getUserId());
    			manageUserModel.setModiId(model.getUserId());
    			manageUserModel.setDelYn("N");
    			bizmetaComMapper.insertManageUser(manageUserModel);
    		}
    	}

    	ComDeptModel comDeptModel = new ComDeptModel();
    	comDeptModel.setMngDeptSeId(model.getStdCoefftId());
    	comDeptModel.setDeptSe("01");
    	//bizmetaComMapper.deleteManageDept(comDeptModel);
    	//관리부서저장
    	if(model.getDeptCdList() !=null && !model.getDeptCdList().isEmpty()){
    		for(String deptCd : model.getDeptCdList()) {
    			ManageDeptModel manageDeptModel = new ManageDeptModel();
    			manageDeptModel.setMngDeptCd(idUtil.getMngDeptCd());
    			manageDeptModel.setDeptCd(deptCd);
    			manageDeptModel.setMngDeptSeId(model.getStdCoefftId());
    			manageDeptModel.setRefVer(model.getSeq());
    			manageDeptModel.setDeptSe("01");  //관리부서
    			manageDeptModel.setRgstId(model.getUserId());
    			manageDeptModel.setModiId(model.getUserId());
    			manageDeptModel.setDelYn("N");
    			bizmetaComMapper.insertManageDept(manageDeptModel);
    		}
    	}

    	//bizmetaComMapper.deleteFindTag(model.getStdCoefftId());
    	//검색태그저장
    	if(model.getFndTagList() !=null && !model.getFndTagList().isEmpty()){
	    	for(String fndTagNm : model.getFndTagList()) {
	    		FindTagModel findTagModel = new FindTagModel();
	        	findTagModel.setFndTagId(idUtil.getFndTagId());
	        	findTagModel.setFndTagSeId(model.getStdCoefftId());
	        	findTagModel.setRefVer(model.getSeq());
	        	findTagModel.setFndTagNm(fndTagNm);
	        	findTagModel.setRgstId(model.getUserId());
	        	findTagModel.setModiId(model.getUserId());
	        	findTagModel.setDelYn("N");
	        	bizmetaComMapper.insertFindTag(findTagModel);    //검색태그저장
	    	}
    	}

    	//bizmetaComMapper.deleteManageInfo(model.getStdCoefftId());
    	//관리정보저장
    	if(model.getAppOpin() !=null && !"".equals(model.getAppOpin())) {
    		ManageInfoModel manageInfoModel = new ManageInfoModel();
    		manageInfoModel.setMngInfId(idUtil.getMngInfId());
    		manageInfoModel.setMngInfSeId(model.getStdCoefftId());
    		manageInfoModel.setRefVer(model.getSeq());
    		manageInfoModel.setAppOpin(model.getAppOpin());
    		manageInfoModel.setRgstId(model.getUserId());
    		manageInfoModel.setModiId(model.getUserId());
    		manageInfoModel.setDelYn("N");
    		bizmetaComMapper.insertManageInfo(manageInfoModel);
    	}

    	
    	

    	if(count > 0) {
            return Constant.DB.UPDATE;
        } else {
            return Constant.DB.FAIL;
        }
    }

  //표준계수 삭제
    @Transactional
    public String deleteStdCoefft(BizmetaModel model) {
    	bizmetaComMapper.deleteRpt(model.getStdCoefftId());
    	FileModel fileDel =new FileModel();
    	fileDel.setRefId(model.getStdCoefftId());
    	fileDel.setModiId(model.getUserId());
    	fileService.deleteFile(fileDel);

    	bizmetaComMapper.deleteManageUser(model.getStdCoefftId());

    	ComDeptModel comDeptModel = new ComDeptModel();
    	comDeptModel.setMngDeptSeId(model.getStdCoefftId());
    	comDeptModel.setDeptSe("01");
    	bizmetaComMapper.deleteManageDept(comDeptModel);
    	bizmetaComMapper.deleteManageUser(model.getStdCoefftId());
    	bizmetaComMapper.deleteFindTag(model.getStdCoefftId());

    	bizmetaComMapper.deleteManageInfo(model.getStdCoefftId());

    	long count= stdCoefftMapper.deleteStdCoefft(model);

    	if(count > 0) {
            return Constant.DB.DELETE;
        } else {
            return Constant.DB.FAIL;
        }
    }



    //표준계수 상세조회
    public BizmetaModel selectStdCoefftDetail(BizmetaCriteria criteria) {
    	return stdCoefftMapper.selectStdCoefftDetail(criteria);
    }

    //표준계수명 중복확인
    public int selectStdCoefftNmCount(BizmetaCriteria model) {
    	return stdCoefftMapper.selectStdCoefftNmCount(model);
    }

    //표준계수명 부서표준계수조회
    public List<BizmetaModel>selectStdCoefftDeptList(BizmetaCriteria criteria){
    	return stdCoefftMapper.selectStdCoefftDeptList(criteria);
    }

    //표준계수명 부서표준계수조회 총개수
    public int selectStdCoefftDeptCount(BizmetaCriteria criteria) {
    	return stdCoefftMapper.selectStdCoefftDeptCount(criteria);
    }

    //보고서 선택 조회
	public List<RptModel>selectRptList(BizmetaCriteria criteria){
		return stdCoefftMapper.selectRptList(criteria);
	}

	//분석마트 선택 조회
	public List<AnalysisMartModel>selectAnalysisMartList(BizmetaCriteria criteria){
		return stdCoefftMapper.selectAnalysisMartList(criteria);
	}

	 //표준계수이력조회
	public List<BizmetaModel>selectStdCoefftHistList(BizmetaCriteria criteria){
		return stdCoefftMapper.selectStdCoefftHistList(criteria);
	}
	//표준계수이력상세
	public BizmetaModel selectStdCoefftHist(BizmetaCriteria criteria) {
		return stdCoefftMapper.selectStdCoefftHist(criteria);
	}

	public int selectStdCoefftHistListCount(BizmetaCriteria criteria) {
		return stdCoefftMapper.selectStdCoefftHistListCount(criteria);
	}
    //표준계수명 중복확인 리스트 건수조회
	public int selectStdCoefftNmListCount(BizmetaCriteria criteria) {
		return stdCoefftMapper.selectStdCoefftNmListCount(criteria);
	}

	//표준계수명 중복확인 리스트
	public List<BizmetaModel>selectStdCoefftNmList(BizmetaCriteria criteria){
		return stdCoefftMapper.selectStdCoefftNmList(criteria);
	}
}
