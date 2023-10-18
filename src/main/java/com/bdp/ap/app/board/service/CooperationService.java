package com.bdp.ap.app.board.service;

import java.util.List;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdp.ap.app.board.mapper.CooperationMapper;
import com.bdp.ap.app.board.model.CooperationCriteria;
import com.bdp.ap.app.board.model.CooperationModel;
import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.app.file.service.FileService;
import com.bdp.ap.common.IdUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CooperationService {

	@Resource
	IdUtil idUtil;	
	
	@Resource
	CooperationMapper cooperationMapper;
	
	@Resource(name = "fileService")
	private FileService fileService;
	
	public List selectCooperationList(com.bdp.ap.app.board.model.CooperationCriteria criteria){
		return cooperationMapper.selectCooperationList(criteria);
	}
	
	public int selectCooperationListCount(com.bdp.ap.app.board.model.CooperationCriteria criteria){
		return cooperationMapper.selectCooperationListCount(criteria);
	}
    
	/*
	 * @Transactional public long
	 * insertCooperationMaster(com.bdp.ap.app.board.model.CooperationModel
	 * cooperationInfo) {
	 * 
	 * if(cooperationInfo.getPicrIds() !=null) { CooperationModel picrIdModel =
	 * null; for(String picrId : cooperationInfo.getPicrIds()) { picrIdModel =new
	 * CooperationModel(); picrIdModel.setPcptId(picrId);
	 * picrIdModel.setCollabSpcId(cooperationInfo.getCollabSpcId());
	 * picrIdModel.setDelYn("N");
	 * picrIdModel.setRgstId(cooperationInfo.getRgstId());
	 * picrIdModel.setModiId(cooperationInfo.getModiId());
	 * cooperationMapper.insertCooperationPcpt(picrIdModel); //협업공간참여자 } }
	 * 
	 * return cooperationMapper.insertCooperationMaster(cooperationInfo); }
	 */
	
    public long insertCooperationPcpt(CooperationModel model) {
    	return cooperationMapper.insertCooperationPcpt(model);
    }
    
	
	  @Transactional 
	  public long insertCooperationTask(com.bdp.ap.app.board.model.CooperationModel model) { //첨부파일저장 
	  if(model.getFileIds() != null) {
	  FileModel file = null; for(String fileId : model.getFileIds()) {
	  file = new FileModel(); file.setFileId(fileId);
	  file.setRefId(model.getCollabSpcTskId());
	  file.setModiId(model.getRgstId()); 
	  fileService.updateFile(file); 
	   }
	 }
		  return cooperationMapper.insertCooperationTask(model);
	  
}
	 
    
   //협업공간 Comment 저장
    public long insertCooperationComt(com.bdp.ap.app.board.model.CooperationModel model) {
    	return cooperationMapper.insertCooperationComt(model);
    }
    
	
	@Transactional 
	public long updateCooperationMaster(CooperationModel model) {
		
		model.setDelYn("Y");
		cooperationMapper.deleteCooperationPcpt(model); 

		if(model.getPicrIds() !=null) {
			for(String picrId : model.getPicrIds()) { 
				CooperationModel picrIdModel = new CooperationModel(); 
				picrIdModel.setPcptId(picrId);
				picrIdModel.setCollabSpcId(model.getCollabSpcId());
				picrIdModel.setDelYn("N"); picrIdModel.setRgstId(model.getRgstId());
				picrIdModel.setModiId(model.getModiId());
				cooperationMapper.upsertCooperationPcpt(picrIdModel); //협업공간참여자 
			} 
		}
		
		return cooperationMapper.updateCooperationMaster(model); 
	} 

	public long deleteCooperationPcpt(CooperationModel model){ 
		return cooperationMapper.deleteCooperationPcpt(model); 
	}
	 
   @Transactional
   public long updateCooperationTask(CooperationModel model){
   	//첨부파일저장
   	if(model.getFileIds() != null) {
			FileModel file = null;
			for(String fileId : model.getFileIds()) {
				file = new FileModel();
				file.setFileId(fileId);
				file.setRefId(model.getCollabSpcTskId());
				file.setModiId(model.getRgstId());
				fileService.updateFile(file);
			}			
		} 
   	return cooperationMapper.updateCooperationTask(model);
   }
//    public long updateCooperationComt(CooperationModel model){
//    	return cooperationMapper.updateCooperationComt(model);
//    }
//    public long deleteCooperationMaster(CooperationModel model){
//    	return cooperationMapper.deleteCooperationMaster(model);
//    }
//    public long deleteCooperationTask(CooperationModel model){
//    	return cooperationMapper.deleteCooperationTask(model);
//    }
//    public long deleteCooperationComt(CooperationModel model){
//    	return cooperationMapper.deleteCooperationComt(model);
//    }
    
    public CooperationModel selectCooperationDetail(CooperationCriteria criteria) {
    	return cooperationMapper.selectCooperationDetail(criteria);
    }
    public List<CooperationModel> selectCooperationTaskDetail(CooperationCriteria criteria) {
    	return cooperationMapper.selectCooperationTaskDetail(criteria);
    }
    public List<CooperationModel> selectCooperationCommentDetail(CooperationCriteria criteria) {
    	return cooperationMapper.selectCooperationCommentDetail(criteria);
    }
    
    public CooperationModel selectCooperationComtDt(CooperationModel model) {
    	return cooperationMapper.selectCooperationComtDt(model);
    }

	//협업공간 Comment 삭제
	public long deleteCooperationComt(CooperationModel model) {
		return cooperationMapper.deleteCooperationComt(model);
	}
	//협업공간 Comment 수정
	public long updateCooperationComt(CooperationModel cooperationInfo) {
		return cooperationMapper.updateCooperationComt(cooperationInfo);
	}
	//협업공간 TASK 저장

	//협업공간 master 삭제
	public long deleteCooperationMaster(CooperationModel cooperationInfo) {
		return cooperationMapper.deleteCooperationMaster(cooperationInfo);
	}

		

}
