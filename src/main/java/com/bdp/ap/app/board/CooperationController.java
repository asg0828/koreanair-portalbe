package com.bdp.ap.app.board;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bdp.ap.app.board.model.CooperationCriteria;
import com.bdp.ap.app.board.model.CooperationModel;
import com.bdp.ap.app.board.service.CooperationService;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.dept.service.DeptService;
import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.app.file.service.FileService;
import com.bdp.ap.app.member.service.MemberService;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.config.props.FileProps;
import com.bdp.ap.config.security.AuthUser;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.utils.StringUtils;

@RequestMapping("/board")
@Controller
@Slf4j
public class CooperationController {

	@Resource
	private CodeService codeService;
	
	@Resource(name = "fileProps")
	private FileProps fileProps;

	@Resource
	IdUtil idUtil;

	@Resource
	private DeptService deptService;

	@Resource
	private CooperationService cooperationService;

	@Resource
	private MemberService memberService;
	
	@Resource
	private FileService fileService;

	@GetMapping("/cooperation")
	public String cooperation(@ModelAttribute CooperationCriteria criteria, Model model) {

		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		model.addAttribute("cooperationList", cooperationService.selectCooperationList(criteria));
		criteria.setTotalCount(cooperationService.selectCooperationListCount(criteria));
		model.addAttribute("grpCdList", codeService.selectGroupIdAllList("GRP_CD"));
		model.addAttribute("stCdList", codeService.selectGroupIdAllList("ST_CD"));

//		criteria.setGrpCd("A");
//		criteria.setStCd("A");				

		model.addAttribute("pages", criteria);
		return "board/cooperation/cooperationList";
	}

	@GetMapping("/cooperation/regist")
	public String cooperationRegist(@ModelAttribute CooperationCriteria criteria, @AuthenticationPrincipal AuthUser authUser, Model model) {

		model.addAttribute("grpCdList", codeService.selectGroupIdAllList("GRP_CD"));
		model.addAttribute("stCdList", codeService.selectGroupIdAllList("ST_CD"));
		model.addAttribute("wrKndCdList", codeService.selectGroupIdAllList("WK_KND_CD"));
		model.addAttribute("userId", authUser.getMemberModel().getUserId());
		model.addAttribute("userNm", authUser.getMemberModel().getUserNm());

		model.addAttribute("cooperationInfo", criteria);

		return "board/cooperation/cooperationRegist";
	}

	/**
			 * 협업공간 마스터 저장
			 * 
			 * @param cooperationInfo
			 * @param authUser
			 * @param model
			 * @return
			 */
	
	/*
	 * @PostMapping("/insertMaster/ajax") public ResponseEntity
	 * insertMaster(@ModelAttribute CooperationModel
	 * cooperationInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
	 * try { Map<Object,Object> result = new HashMap<>();
	 * cooperationInfo.setRgstId(authUser.getMemberModel().getUserId());
	 * cooperationInfo.setModiId(authUser.getMemberModel().getUserId());
	 * cooperationInfo.setCollabSpcId(idUtil.getCollabSpcId()); // 협업공간ID
	 * 
	 * long icnt = cooperationService.insertCooperationMaster(cooperationInfo); //
	 * result.put("collabSpcId", cooperationInfo.getCollabSpcId()); return new
	 * ResponseEntity<>(result, HttpStatus.OK); } catch (Exception e) {
	 * log.error(e.getMessage(), e); return new ResponseEntity<>(e.getMessage(),
	 * HttpStatus.NOT_ACCEPTABLE); } }
	 */
	  
	 /**
		 * 협업공간 TASK 저장
		 * 
		 * @param cooperationInfo
		 * @param authUser
		 * @param model
		 * @return
		 */
		  @PostMapping("/insertTask/ajax") 
		  public ResponseEntity insertTask(@ModelAttribute CooperationModel cooperationInfo, @AuthenticationPrincipal AuthUser authUser, Model model) { 
			  try { 
				  Map<Object,Object> result = new HashMap<>();
				  
				  cooperationInfo.setRgstId(authUser.getMemberModel().getUserId());
				  cooperationInfo.setModiId(authUser.getMemberModel().getUserId()); 
				  String sCollabSpcTskId = idUtil.getCollabSpcTskId();
				  cooperationInfo.setCollabSpcTskId(sCollabSpcTskId); // 협업공간TASK ID
				  cooperationInfo.setComtId(idUtil.getComtId()); // Comment ID
				  
				  long icnt = cooperationService.insertCooperationTask(cooperationInfo); //
				  result.put("collabSpcTskId", sCollabSpcTskId);
				  
				  return new ResponseEntity<>(result, HttpStatus.OK); 
				  } catch (Exception e) {
				  log.error(e.getMessage(), e); 
				  return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE); 
				  } 
		  }
	/**
		 * 협업공간 Comment 저장
		 * 
		 * @param cooperationInfo
		 * @param authUser
		 * @param model
		 * @return
		 */
			  @PostMapping("/insertComment/ajax") 
			  public ResponseEntity insertComment(@ModelAttribute CooperationModel cooperationInfo, @AuthenticationPrincipal AuthUser authUser, Model model) { 
				  try { 
					  Map<Object,Object> result = new HashMap<>();
					  cooperationInfo.setRgstId(authUser.getMemberModel().getUserId());
					  cooperationInfo.setModiId(authUser.getMemberModel().getUserId());
					  cooperationInfo.setComtId(idUtil.getComtId()); // Comment ID
					  
					  long icnt = cooperationService.insertCooperationComt(cooperationInfo); 
					  //협업공간Comment
					  result.put("comtId", cooperationInfo.getComtId()); 
					  result.put("rgstDt", cooperationService.selectCooperationComtDt(cooperationInfo)); 
					  return new ResponseEntity<>(result, HttpStatus.OK); 
					  } catch (Exception e) {
						  log.error(e.getMessage(), e); 
						  return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE); 
						  } 
				  }
			 

	/**
	* 협업공간 마스터 수정
	* 
	* @param cooperationInfo
	* @param authUser
	* @param model
	* @return
	*/
	@PostMapping("/updateMaster/ajax")
	public ResponseEntity updateMaster(@ModelAttribute CooperationModel cooperationInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
		try {
			Map<Object,Object> result = new HashMap<>();
			cooperationInfo.setRgstId(authUser.getMemberModel().getUserId());
			cooperationInfo.setModiId(authUser.getMemberModel().getUserId());

			long icnt=cooperationService.updateCooperationMaster(cooperationInfo);                 // 협업공간
			result.put("cnt", icnt);	
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	/**
	 * 협업공간 마스터 삭제
	 */
	@PostMapping("/deleteMaster/ajax")
	public ResponseEntity cooperationDelete(@ModelAttribute CooperationModel cooperationInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
			try {
					Map<Object,Object> result = new HashMap<>();
					cooperationInfo.setModiId(authUser.getMemberModel().getUserId());
					
					long icnt=cooperationService.deleteCooperationMaster(cooperationInfo);					   //협업공간TASK 
		
					result.put("cnt", icnt);	
					return new ResponseEntity<>(result, HttpStatus.OK);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
			}
   }
	
	 /**
		 * 협업공간 TASK 수정
		 * 
		 * @param cooperationInfo
		 * @param authUser
		 * @param model
		 * @return
		 */
	
	
	@PostMapping("/updateTask/ajax")
	public ResponseEntity updateTask(@ModelAttribute CooperationModel cooperationInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
		try { 
			Map<Object,Object> result = new HashMap<>();
			cooperationInfo.setModiId(authUser.getMemberModel().getUserId());
	
			long icnt=cooperationService.updateCooperationTask(cooperationInfo);
			//협업공간TASK result.put("cnt", icnt);
			
			return new ResponseEntity<>(result, HttpStatus.OK); 
		} catch (Exception e) {
			log.error(e.getMessage(), e); return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE); 
		} }
	
	 
	 
	 /**
		 * 협업공간 Comment 수정
		 * 
		 * @param cooperationInfo
		 * @param authUser
		 * @param model
		 * @return
		*/
	
	  @PostMapping("/updateComment/ajax") 
	  public ResponseEntity updateComment(@ModelAttribute CooperationModel cooperationInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
	  try { 
		  Map<Object,Object> result = new HashMap<>();
		  cooperationInfo.setModiId(authUser.getMemberModel().getUserId());
		  cooperationInfo.setDeptCd(authUser.getMemberModel().getDeptCode()); 
		  long icnt=cooperationService.updateCooperationComt(cooperationInfo); //협업공간Comment
		  result.put("cnt", icnt);
	  
		  return new ResponseEntity<>(result, HttpStatus.OK); 
		  } catch (Exception e) {
			  log.error(e.getMessage(), e); 
			  return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE); 
			  } 
	  }
	  
		
	/**
	 * * 협업공간Comment 삭제
	 * 
	 * @param cooperationInfo
	 * 
	 * @param authUser
	 * 
	 * @param model
	 * 
	 * @return
	 */	 
	  @PostMapping("/deleteComment/ajax") 
	  public ResponseEntity deleteComment(@ModelAttribute CooperationModel cooperationInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
	  try {
		  	Map<Object,Object> result = new HashMap<>();
	  		cooperationInfo.setModiId(authUser.getMemberModel().getUserId());
	  
	  		long icnt=cooperationService.deleteCooperationComt(cooperationInfo);
	  		result.put("cnt", icnt);
	  
	  return new ResponseEntity<>(result, HttpStatus.OK); 
	  } catch (Exception e) {
		  log.error(e.getMessage(), e);
		  return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		  } 
	  }
			 

	/**
	 * 협업공간 상세조회
	 * 
	 * @param criteria
	 * @param authUser
	 * @return
	 */
	@PostMapping("/selectCooperation/detail/ajax")
	public ResponseEntity selectCooperationDetail(@ModelAttribute CooperationCriteria criteria,@AuthenticationPrincipal AuthUser authUser) {
		try {
			Map<Object, Object> result = new HashMap<>();
			CooperationModel model = cooperationService.selectCooperationDetail(criteria);
			result.put("cooperationDetail", model);

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}

		
	}

	/**
	 * 협업공간 Task 상세조회
	 * 
	 * @param criteria
	 * @param authUser
	 * @return
	 */
	@PostMapping("/selectCooperationTask/detail/ajax")
	public ResponseEntity selectCooperationTaskDetail(@ModelAttribute CooperationCriteria criteria, @AuthenticationPrincipal AuthUser authUser) {
		try {
			Map<Object, Object> result = new HashMap<>();

			List<CooperationModel> model = cooperationService.selectCooperationTaskDetail(criteria);
			result.put("taskDetail", model);
			
			//첨부파일
			for(CooperationModel copModel : model) {
				FileModel f = new FileModel();
				f.setRefId(copModel.getCollabSpcTskId());
				List<FileModel> fileList = fileService.selectFileList(f);
				JSONArray files = new JSONArray();
				for(FileModel fm : fileList) {
					JSONObject obj = new JSONObject();
					obj.put("fileId", fm.getFileId());
					obj.put("storageSe", fm.getStorageSe());
					obj.put("savePath", fm.getSavePath());
					obj.put("saveFileNm", fm.getSaveFileNm());
					obj.put("fileNm", fm.getFileNm());
					obj.put("fileExtsn", fm.getFileExtsn());
					obj.put("fileSize", fm.getFileSize());
					obj.put("useYn", fm.getUseYn());
					obj.put("rgstId", fm.getRgstId());
					obj.put("rgstDt", fm.getRgstDt());
					obj.put("modiId", fm.getModiId());
					obj.put("modiDt", fm.getModiDt());
					obj.put("bucketNm", fm.getBucketNm());
					obj.put("fileUrl", fileProps.getUploadServer()+fm.getSavePath()+fm.getSaveFileNm());
					obj.put("fileCl", fm.getFileCl());
					obj.put("saveFileVer", fm.getSaveFileVer());
					obj.put("inputStream", fm.getInputStream());
					obj.put("bytes", fm.getBytes());
					obj.put("modiByUserYn", fm.getModiByUserYn());
					obj.put("refId", fm.getRefId());
					obj.put("refVer", fm.getRefVer());
					files.put(obj);
				}
				result.put(copModel.getCollabSpcTskId()+"_fileList", fileList);
				result.put(copModel.getCollabSpcTskId()+"_fileJsonList", files);
			}
			

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
	 * 협업공간 Comment 상세조회
	 * 
	 * @param criteria
	 * @param authUser
	 * @return
	 */
	@PostMapping("/selectCooperationComment/detail/ajax")
	public ResponseEntity selectCooperationCommentDetail(@ModelAttribute CooperationCriteria criteria, @AuthenticationPrincipal AuthUser authUser) {
		try {
			Map<Object, Object> result = new HashMap<>();

			List<CooperationModel> list = cooperationService.selectCooperationCommentDetail(criteria);
			result.put("commentDetailList", list);

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
