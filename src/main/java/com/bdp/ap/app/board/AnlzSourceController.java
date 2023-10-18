package com.bdp.ap.app.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdp.ap.app.board.model.AnlzSourceCmtModel;
import com.bdp.ap.app.board.model.AnlzSourceModel;
import com.bdp.ap.app.board.model.QnaModel;
import com.bdp.ap.app.board.service.AnlzSourceService;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.dept.service.DeptService;
import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.app.file.service.FileService;
import com.bdp.ap.app.member.model.MemberCriteria;
import com.bdp.ap.app.member.service.MemberService;
import com.bdp.ap.app.role.service.RoleService;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.common.annotation.NoLogging;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.props.FileProps;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board")
@Controller
@Slf4j
public class AnlzSourceController {
	@Resource(name = "fileProps")
	private FileProps fileProps;

	@Resource
    private IdUtil idUtil;

	@Resource
	private CodeService codeService;

	@Resource
	private AnlzSourceService anlzSourceService;

	@Resource
	private FileService fileService;

	@Resource
    private RoleService roleService;

    @Resource
    private DeptService deptService;

	@Resource
    private MemberService memberService;

	/**
	 * 분석소스공유관리 목록조회
	 * @param authUser
	 * @param criteria
	 * @param model
	 * @return
	 */
    @GetMapping("/anlzSource")
    public String anlzSource(@AuthenticationPrincipal AuthUser authUser, @ModelAttribute Criteria criteria, Model model) {
		model.addAttribute("anlzSourceList", anlzSourceService.selectAnlzSourceList(criteria));
		criteria.setTotalCount(anlzSourceService.selectAnlzSourceListCount(criteria));
		model.addAttribute("anlzSourceType",codeService.selectGroupIdAllList("ANLZ_SOURCE_TYPE"));
		model.addAttribute("pages", criteria);
		model.addAttribute("userNm", authUser.getUsername());
		return "board/anlzSource/anlzSource";
    }

    /**
	 * 분석소스공유관리 목록조회
	 * @param criteria
	 * @param attributes
	 * @return
	 */
    @PostMapping("/anlzSource")
    public String anlzSource(@ModelAttribute Criteria criteria, RedirectAttributes attributes) {
        attributes.addFlashAttribute("criteria", criteria);
        return "redirect:/board/anlzSource";
    }

    /**
     * 분석소스공유관리 등록
	 * @param authUser
	 * @param criteria
	 * @param model
	 * @return
	 */
    @GetMapping("/anlzSource/regist")
    public String regist(@AuthenticationPrincipal AuthUser authUser, @ModelAttribute MemberCriteria criteria, Model model) {

        //공지사항에서 사용하는 공통코드 모두 set
		anlzSourceService.selectAllAnlzSourceCodeList(model);

		System.out.println("deptList : "+ deptService.selectDeptClList());

		// 부서 조회
        model.addAttribute("deptList", deptService.selectDeptClList());
		criteria.setSearchCompanyCode(authUser.getMemberModel().getCompanyCode());

		model.addAttribute("memberList", memberService.selectMemberList(criteria));
		model.addAttribute("userlist", null);

		model.addAttribute("pages", criteria);

		return "board/anlzSource/anlzSourceRegist";
    }


    /**
	 * 분석소스공유관리 수정
	 * @param anlzSourceId
	 * @param authUser
	 * @param criteria
	 * @param model
	 * @return
	 */
    @GetMapping("/anlzSource/modify/{anlzSourceId}")
    public String modify(@PathVariable String anlzSourceId, @AuthenticationPrincipal AuthUser authUser, @ModelAttribute MemberCriteria criteria, Model model) {
        AnlzSourceModel anlzSourceModel = new AnlzSourceModel();
        anlzSourceModel.setAnlzSourceId(anlzSourceId);
		AnlzSourceModel asModel = anlzSourceService.selectAnlzSource(anlzSourceModel);
        model.addAttribute("anlzSourceInfo", asModel);

		model.addAttribute("userIdList", anlzSourceService.selectAnlzSourceAuth(anlzSourceModel));
        //공지사항에서 사용하는 공통코드 모두 set
		anlzSourceService.selectAllAnlzSourceCodeList(model);

		//사용자선택팝업에서 사용하는 부서와 사용자 목록 조회
        model.addAttribute("deptList", deptService.selectDeptClList());
		criteria.setSearchCompanyCode(authUser.getMemberModel().getCompanyCode());
		model.addAttribute("memberList", memberService.selectMemberList(criteria));

		//파일 조회
		FileModel f = new FileModel();
		f.setRefId(anlzSourceId);
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

		model.addAttribute("fileList", fileList);
		model.addAttribute("fileJsonList", files);

		model.addAttribute("pages", criteria);
		
        return "board/anlzSource/anlzSourceRegist";
    }

	/**
     * 분석소스공유관리 상세
	 * @param anlzSourceId
	 * @param model
	 * @param authUser
	 * @return
	 */
    @GetMapping("/anlzSource/detail/{anlzSourceId}")
    public String detail(@PathVariable String anlzSourceId, Model model, @ModelAttribute MemberCriteria criteria, @AuthenticationPrincipal AuthUser authUser) {
        AnlzSourceModel anlzSourceModel = new AnlzSourceModel();
        AnlzSourceCmtModel anlzSourceCmtModel = new AnlzSourceCmtModel();
        anlzSourceModel.setAnlzSourceId(anlzSourceId);
        model.addAttribute("anlzSourceInfo", anlzSourceService.selectAnlzSource(anlzSourceModel));
        List<AnlzSourceCmtModel> cmtList = anlzSourceService.selectAnlzSourceCommentList(anlzSourceModel);
		model.addAttribute("commentList", cmtList);
		model.addAttribute("commentSize", cmtList.size());
		model.addAttribute("anlzSourceCmtInfo", anlzSourceCmtModel);
		model.addAttribute("userId", authUser.getMemberModel().getUserId());

		//조회수 올림
		anlzSourceService.updateAnlzSourceReadCnt(anlzSourceModel);

		//파일 조회
		FileModel f = new FileModel();
		f.setRefId(anlzSourceId);
		List<FileModel> fileList = fileService.selectFileList(f);
		JSONArray files = new JSONArray();
		for(FileModel fm : fileList) {
			//JSONObject obj = new JSONObject();
			Map obj = new HashMap();
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

		model.addAttribute("fileList", fileList);
		model.addAttribute("fileJsonList", files);

		model.addAttribute("pages", criteria);

        return "board/anlzSource/anlzSourceDetail";
    }


    /**
     * Insert New Info
     * @param anlzSourceInfo
     * @param authUser
     * @param model
     * @return
     */
    @PostMapping("/anlzSource/insert")
    public String insert(@ModelAttribute AnlzSourceModel anlzSourceInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
		anlzSourceInfo.setRgstId(authUser.getMemberModel().getUserId());
		anlzSourceInfo.setModiId(authUser.getMemberModel().getUserId());
		anlzSourceInfo.setAnlzSourceId(idUtil.getAnlzSourceId());
		model.addAttribute("anlzSourceInsertInfo", anlzSourceService.insertAnlzSource(anlzSourceInfo));
		if(!anlzSourceInfo.getUserIdList().isEmpty()){
			AnlzSourceModel userInfo = new AnlzSourceModel();
			userInfo.setAnlzSourceId(anlzSourceInfo.getAnlzSourceId());
			userInfo.setRgstId(authUser.getMemberModel().getUserId());
			for(String uId : anlzSourceInfo.getUserIdList()){
				userInfo.setUserId(uId);
				anlzSourceService.insertAnlzSourceAuth(userInfo);
			}
		}

		if(anlzSourceInfo.getFileIds() != null) {
			FileModel file = null;
			for(String fileId : anlzSourceInfo.getFileIds()) {
				file = new FileModel();
				file.setFileId(fileId);
				file.setRefId(anlzSourceInfo.getAnlzSourceId());
				file.setModiId(authUser.getMemberModel().getUserId());
				fileService.updateFile(file);
			}
		}
		log.debug("ANLZSOURCE UPSERT MODEL: {}", model);

		return "redirect:/board/anlzSource";
    }

    /**
     * 분석소스 공유관리 수정저장
	 * @param anlzSourceInfo
	 * @param authUser
	 * @param model
	 * @return
	 */
    @PostMapping("/anlzSource/update")
    public String update(@ModelAttribute AnlzSourceModel anlzSourceInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
		anlzSourceInfo.setModiId(authUser.getMemberModel().getUserId());
		anlzSourceInfo.setRgstId(authUser.getMemberModel().getUserId());
		model.addAttribute("anlzSourceUpdateInfo", anlzSourceService.updateAnlzSource(anlzSourceInfo));

		if(!anlzSourceInfo.getUserIdList().isEmpty()){
			AnlzSourceModel userInfo = new AnlzSourceModel();
			userInfo.setAnlzSourceId(anlzSourceInfo.getAnlzSourceId());
			userInfo.setRgstId(authUser.getMemberModel().getUserId());
			//삭제 후 새로 저장
			anlzSourceService.deleteAnlzSourceAuth(userInfo);
			for(String uId : anlzSourceInfo.getUserIdList()){
				userInfo.setUserId(uId);
				anlzSourceService.insertAnlzSourceAuth(userInfo);
			}
		}

		if(anlzSourceInfo.getFileIds() != null) {
			for(String fileId : anlzSourceInfo.getFileIds()) {
				FileModel file = new FileModel();
				file.setFileId(fileId);
				file.setRefId(anlzSourceInfo.getAnlzSourceId());
				file.setModiId(authUser.getMemberModel().getUserId());
				fileService.updateFile(file);
			}
		}
		log.debug("ANLZSOURCE UPSERT MODEL: {}", model);
		return "redirect:/board/anlzSource";
    }

    /**
     * 삭제
	 * @param anlzSourceInfo
	 * @param fileModel
	 * @param authUser
	 * @param model
	 * @return
	 */
    @PostMapping("/anlzSource/delete")
    public String delete(@ModelAttribute AnlzSourceModel anlzSourceInfo,@ModelAttribute FileModel fileModel,@AuthenticationPrincipal AuthUser authUser, Model model) {
		anlzSourceInfo.setModiId(authUser.getMemberModel().getUserId());
		model.addAttribute("anlzSourceDeleteInfo", anlzSourceService.deleteAnlzSource(anlzSourceInfo));
		log.debug("ANLZSOURCE UPSERT MODEL: {}", model);

		return "redirect:/board/anlzSource";
    }

	/**
	 * 분석소스 공유관리 댓글 달기
	 * @param anlzSourceCmtInfo
	 * @param authUser
	 * @param model
	 * @return
	 
    @PostMapping("/anlzSource/comment")
    public String comment(@ModelAttribute AnlzSourceCmtModel anlzSourceCmtInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
		anlzSourceCmtInfo.setRgstId(authUser.getMemberModel().getUserId());
		anlzSourceCmtInfo.setModiId(authUser.getMemberModel().getUserId());
		anlzSourceCmtInfo.setAnlzSourceId(idUtil.getAnlzSourceId());

		if(anlzSourceCmtInfo.getAnlzParentsCmtId() == 0){
			anlzSourceCmtInfo.setAnlzParentsCmtId(null);
		}
		model.addAttribute("anlzSourceCmtInsertInfo", anlzSourceService.insertAnlzSourceCmt(anlzSourceCmtInfo));

		log.debug("ANLZSOURCE ADD COMMENT MODEL: {}", model);

		return "redirect:/board/anlzSource";

    }*/

	 /**
     * Comment 의 대댓글 남기기
     * @param anlzSourceId
     * @param model
     * @return
     */
    @PostMapping("/anlzSource/addComment/ajax")
    public ResponseEntity addComment(@ModelAttribute AnlzSourceCmtModel anlzSourceCmtInfo
	, @AuthenticationPrincipal AuthUser authUser, Model model) {
		try {

			anlzSourceCmtInfo.setRgstId(authUser.getMemberModel().getUserId());
			anlzSourceCmtInfo.setModiId(authUser.getMemberModel().getUserId());
			log.debug("ANLZSOURCE ADD COMMENT MODEL: {}", model);

			long result = anlzSourceService.insertAnlzSourceCmt(anlzSourceCmtInfo);

			return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

	/**
	 * 댓글 수정
	 * @param authUser
	 * @param cmtId
	 * @param cn
	 * @return
	 */
	@PostMapping("/anlzSource/modComment/ajax")
    public ResponseEntity modComment(@AuthenticationPrincipal AuthUser authUser, @RequestParam int cmtId, @RequestParam String cn) {
		try {

			AnlzSourceCmtModel anlzSourceCmtInfo = new AnlzSourceCmtModel();
			anlzSourceCmtInfo.setRgstId(authUser.getMemberModel().getUserId());
			anlzSourceCmtInfo.setModiId(authUser.getMemberModel().getUserId());
			anlzSourceCmtInfo.setCn(cn);
			anlzSourceCmtInfo.setAnlzSourceCmtId(cmtId);

			log.debug("ANLZSOURCE ADD COMMENT MODEL: {}", anlzSourceCmtInfo);

			long result = 0;
            result = anlzSourceService.updateAnlzSourceCmt(anlzSourceCmtInfo);

			return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

	/**
	 * 대댓글 등록
	 * @param authUser
	 * @param anlzSourceId
	 * @param parentId
	 * @param cn
	 * @return
	 */
	@PostMapping("/anlzSource/addReComment/ajax")
    public ResponseEntity addReComment(@AuthenticationPrincipal AuthUser authUser, @RequestParam String anlzSourceId
			, @RequestParam int parentId, @RequestParam String cn) {
		try {

			AnlzSourceCmtModel anlzSourceCmtInfo = new AnlzSourceCmtModel();
			anlzSourceCmtInfo.setRgstId(authUser.getMemberModel().getUserId());
			anlzSourceCmtInfo.setModiId(authUser.getMemberModel().getUserId());
			anlzSourceCmtInfo.setCmt(cn);
			anlzSourceCmtInfo.setAnlzSourceId(anlzSourceId);
			anlzSourceCmtInfo.setAnlzParentsCmtId(parentId);

			log.debug("ANLZSOURCE ADD COMMENT MODEL: {}", anlzSourceCmtInfo);

			long result = 0;
            result = anlzSourceService.insertAnlzSourceCmt(anlzSourceCmtInfo);

			return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

	/**
	 * 댓글 삭제
	 * @param authUser
	 * @param cmtId
	 * @param level
	 * @return
	 */
	@PostMapping("/anlzSource/delComment/ajax")
    public ResponseEntity delComment(@AuthenticationPrincipal AuthUser authUser, @RequestParam int cmtId, @RequestParam int level) {
		try {

			AnlzSourceCmtModel anlzSourceCmtInfo = new AnlzSourceCmtModel();
			anlzSourceCmtInfo.setModiId(authUser.getMemberModel().getUserId());
			anlzSourceCmtInfo.setAnlzSourceCmtId(cmtId);

			log.debug("ANLZSOURCE ADD COMMENT MODEL: {}", anlzSourceCmtInfo);

			long result = 0;
			if(level > 0) {
				result = anlzSourceService.deleteRealAnlzSourceCmt(anlzSourceCmtInfo);
			}else {
				result = anlzSourceService.deleteAnlzSourceCmt(anlzSourceCmtInfo);
			}

			return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
	 * 파일 업로드
	 * @param anlzSourceId
	 * @param model
	 * @return
	 */
    @NoLogging
    @Profile({Constant.Profile.LOCAL})
    @GetMapping("/anlzSource/detail/upload/{anlzSourceId}")
    public String anlzSourceUpload(@PathVariable String anlzSourceId, Model model) {
		AnlzSourceModel anlzSourceModel = new AnlzSourceModel();
        anlzSourceModel.setAnlzSourceId(anlzSourceId);
        model.addAttribute("anlzSourceInfo", anlzSourceService.selectAnlzSource(anlzSourceModel));
		model.addAttribute("codeUseYnList", codeService.selectGroupIdAllList("USE_YN"));
		model.addAttribute("codeImportantYnList", codeService.selectGroupIdAllList("IMPORTANT_YN"));

		FileModel f = new FileModel();
		f.setRefId(anlzSourceId);
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

		model.addAttribute("fileList", fileList);
		model.addAttribute("fileJsonList", files);
        return "board/anlzSource/anlzSourceUpload";
    }

}
