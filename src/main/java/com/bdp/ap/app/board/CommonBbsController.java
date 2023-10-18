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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdp.ap.app.board.model.CommentModel;
import com.bdp.ap.app.board.model.CommonBbsItemModel;
import com.bdp.ap.app.board.model.CommonBbsMngModel;
import com.bdp.ap.app.board.model.CommonBbsModel;
import com.bdp.ap.app.board.model.QnaModel;
import com.bdp.ap.app.board.service.CommonBbsService;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.app.file.service.FileService;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.common.annotation.NoLogging;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.props.FileProps;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

// @RequestMapping("/info")
@Controller
@Slf4j

public class CommonBbsController {

    @Resource(name = "fileProps")
	private FileProps fileProps;

	@Resource
    private IdUtil idUtil;

	@Resource
	private CodeService codeService;

	@Resource
	private CommonBbsService CommonBbsService;

	@Resource
	private FileService fileService;

    @GetMapping("/bbs/{boardId}")
    public String bbsList(@PathVariable String boardId, @ModelAttribute CommonBbsModel bbsModel, Model model, @AuthenticationPrincipal AuthUser authUser){

    	bbsModel.setBoardId(boardId);
        bbsModel.setViewType("list");
    	List<CommonBbsItemModel> bbsItemList = CommonBbsService.selectBbsItemList(bbsModel);
        CommonBbsMngModel bbsMng = CommonBbsService.selectBbsMng(bbsModel);

    	for (CommonBbsItemModel bbsItem : bbsItemList) {
    		
    		String[] PhyNm = bbsItem.getItemPhyNm().split("_");
    		String a = PhyNm[0];
	    	for(int i=1;i < PhyNm.length;i++) {
	    		char[] arr = PhyNm[i].toCharArray();
	    	    arr[0] = Character.toUpperCase(arr[0]);
	    	    a += new String(arr);
	    	}
	    	bbsItem.setItemPhyNm(a);
        }

        if(bbsMng.getTypCd().equals("01")){
        	model.addAttribute("bbsList", CommonBbsService.selectBbsList(bbsModel));
        }else {
        	model.addAttribute("bbsList", CommonBbsService.selectBbsReplyList(bbsModel));
        }
		
    	model.addAttribute("bbsItemList", bbsItemList);
        bbsModel.setTotalCount(CommonBbsService.selectBbsListCount(bbsModel));
        model.addAttribute("pages", bbsModel);
        model.addAttribute("boardId", boardId);
        
        return "board/common/commonBbsList";
    }

    @GetMapping("/bbs/{boardId}/regist")
    public String bbsRegist(@PathVariable String boardId, @ModelAttribute CommonBbsModel bbsModel, Model model, @AuthenticationPrincipal AuthUser authUser){

        bbsModel.setBoardId(boardId);
        List<CommonBbsItemModel> bbsItemList = CommonBbsService.selectBbsItemList(bbsModel);
        CommonBbsMngModel bbsMngModel = CommonBbsService.selectBbsMng(bbsModel);

        if(bbsModel.getId() != "" && bbsModel.getId() != null) {
        	model.addAttribute("bfId", bbsModel.getId());
        }
        
		//권한검사
        bbsModel.setAuthId(authUser.getMemberModel().getAuthId());
        bbsModel.setMgrAuthId(authUser.getMemberModel().getMgrAuthId());
        if(bbsMngModel.getWkAuthYn().equals("Y") || CommonBbsService.authChecker(bbsModel) > 0) {
        	model.addAttribute("authChecker", "Y");
        }
        
        // 아이템 개별적용시 ItemUseCheck,JSONList활성화 후 사용
        // JSONObject ItemUseCheck = new JSONObject();
        for (CommonBbsItemModel bbsItem : bbsItemList) {
    		String[] PhyNm = bbsItem.getItemPhyNm().split("_");
    		String a = PhyNm[0];
	    	for(int i=1;i < PhyNm.length;i++) {
	    		char[] arr = PhyNm[i].toCharArray();
	    	    arr[0] = Character.toUpperCase(arr[0]);
	    	    a += new String(arr);
	    	}
	    	bbsItem.setItemPhyNm(a);
	    	// ItemUseCheck.put(bbsItem.getItemPhyNm(), bbsItem);
        }
        
        model.addAttribute("bbsItemList", bbsItemList);
        model.addAttribute("bbsMng", bbsMngModel);
        // model.addAttribute("bbsItemJSONList", ItemUseCheck);
        
        return "board/common/commonBbsRegist";
    }

    @GetMapping("/bbs/{boardId}/detail/{detailId}")
    public String bbsRegist(@PathVariable String detailId, @PathVariable String boardId, @ModelAttribute CommonBbsModel bbsModel, Model model, @AuthenticationPrincipal AuthUser authUser){

    	bbsModel.setId(detailId);
    	bbsModel.setBoardId(boardId);
        CommonBbsService.addViewCntBbs(bbsModel);
        List<CommonBbsItemModel> bbsItemList = CommonBbsService.selectBbsItemList(bbsModel);

        for (CommonBbsItemModel bbsItem : bbsItemList) {
    		String[] PhyNm = bbsItem.getItemPhyNm().split("_");
    		String a = PhyNm[0];
	    	for(int i=1;i < PhyNm.length;i++) {
	    		char[] arr = PhyNm[i].toCharArray();
	    	    arr[0] = Character.toUpperCase(arr[0]);
	    	    a += new String(arr);
	    	}
	    	bbsItem.setItemPhyNm(a);
        }

		//파일 조회
    	FileModel f = new FileModel();
    	f.setRefId(detailId);
    	List<FileModel> fileList = fileService.selectFileList(f);
    	JSONArray files = new JSONArray();
    	for(FileModel fm : fileList) {
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
        
        model.addAttribute("bbsItemList", bbsItemList);
        model.addAttribute("bbsMng", CommonBbsService.selectBbsMng(bbsModel));
    	model.addAttribute("bbsDetail",CommonBbsService.selectBbs(bbsModel));
    	//댓글리스트
        model.addAttribute("bbsCommnetInfo", CommonBbsService.selectBbsCommentList(bbsModel));

        return "board/common/commonBbsDetail";
    }

    @GetMapping("/bbs/{boardId}/modify/{detailId}")
    public String bbsModify(@PathVariable String detailId, @PathVariable String boardId, @ModelAttribute CommonBbsModel bbsModel, Model model, @AuthenticationPrincipal AuthUser authUser){
        
    	bbsModel.setId(detailId);
    	bbsModel.setBoardId(boardId);
        CommonBbsService.addViewCntBbs(bbsModel);
        List<CommonBbsItemModel> bbsItemList = CommonBbsService.selectBbsItemList(bbsModel);
        for (CommonBbsItemModel bbsItem : bbsItemList) {
    		String[] PhyNm = bbsItem.getItemPhyNm().split("_");
    		String a = PhyNm[0];
	    	for(int i=1;i < PhyNm.length;i++) {
	    		char[] arr = PhyNm[i].toCharArray();
	    	    arr[0] = Character.toUpperCase(arr[0]);
	    	    a += new String(arr);
	    	}
	    	bbsItem.setItemPhyNm(a);
        }

		//권한검사
		CommonBbsMngModel bbsMngModel = CommonBbsService.selectBbsMng(bbsModel);
		bbsModel.setAuthId(authUser.getMemberModel().getAuthId());
        bbsModel.setMgrAuthId(authUser.getMemberModel().getMgrAuthId());
        if(bbsMngModel.getWkAuthYn().equals("Y") || CommonBbsService.authChecker(bbsModel) > 0) {
        	model.addAttribute("authChecker", "Y");
        }

		//파일 조회
    	FileModel f = new FileModel();
    	f.setRefId(detailId);
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
        
        model.addAttribute("bbsItemList", bbsItemList);
        model.addAttribute("bbsMng", bbsMngModel);
    	model.addAttribute("bbsDetail",CommonBbsService.selectBbs(bbsModel));

        return "board/common/commonBbsRegist";
    }

    @PostMapping("/bbs/{boardId}/insert")
    public String bbsInsert(@PathVariable String boardId, @ModelAttribute CommonBbsModel bbsModel, Model model, @AuthenticationPrincipal AuthUser authUser){
    	
    	bbsModel.setRgstId(authUser.getMemberModel().getUserId());
    	bbsModel.setModiId(authUser.getMemberModel().getUserId());
        bbsModel.setDelYn("N");
    	//bbsModel.setId(idUtil.getDataId()); 시퀀스 사용중
        CommonBbsService.insertBbs(bbsModel);

    	if(bbsModel.getFileIds() != null) {
	    	for(String fileId : bbsModel.getFileIds()) {
	    		FileModel file = new FileModel();
	    		file.setFileId(fileId);
	    		file.setRefId(bbsModel.getId());
	    		file.setModiId(authUser.getMemberModel().getUserId());
	    		fileService.updateFile(file);
	    	}
    	}

        return "redirect:/bbs/{boardId}"; 
    }

    @PostMapping("/bbs/{boardId}/update")
    public String bbsUpdate(@PathVariable String boardId, @ModelAttribute CommonBbsModel bbsModel, Model model, @AuthenticationPrincipal AuthUser authUser){
    	bbsModel.setModiId(authUser.getMemberModel().getUserId());

    	CommonBbsService.updateBbs(bbsModel);

    	if(bbsModel.getFileIds() != null) {
	    	for(String fileId : bbsModel.getFileIds()) {
	    		FileModel file = new FileModel();
	    		file.setFileId(fileId);
	    		file.setRefId(bbsModel.getId());
	    		file.setModiId(authUser.getMemberModel().getUserId());
	    		fileService.updateFile(file);
	    	}
    	}

        return "redirect:/bbs/{boardId}";
    }

    @PostMapping("/bbs/{boardId}/delete")
    public String bbsDelete(@PathVariable String boardId, @ModelAttribute CommonBbsModel bbsModel, Model model, @AuthenticationPrincipal AuthUser authUser){
        
    	bbsModel.setModiId(authUser.getMemberModel().getUserId());
        bbsModel.setBoardId(boardId);
        bbsModel.setDelYn("Y");
    	CommonBbsService.deleteBbs(bbsModel);

        return "redirect:/bbs/{boardId}";
    }

    @PostMapping("/bbs/{boardId}/detail/{detailId}/insertComment")
    public String bbsCommentInsert(@PathVariable String detailId, @PathVariable String boardId, @ModelAttribute CommentModel bbsModel, Model model, @AuthenticationPrincipal AuthUser authUser){
		
    	bbsModel.setBoardId(boardId);
		bbsModel.setBfCommentId(bbsModel.getCommentId());
    	bbsModel.setModiId(authUser.getMemberModel().getUserId());
        bbsModel.setRefId(detailId);
		bbsModel.setDelYn("N");
		
    	CommonBbsService.insertBbsComment(bbsModel);

        return "board/common/commonBbsDetail";
    }

	@PostMapping("/bbs/{boardId}/detail/{detailId}/updateComment")
    public String bbsCommentUpdate(@PathVariable String detailId, @PathVariable String boardId, @ModelAttribute CommentModel bbsModel, Model model, @AuthenticationPrincipal AuthUser authUser){

    	CommonBbsService.updateBbsComment(bbsModel);
    	
        return "board/common/commonBbsDetail";
    }
	
	@PostMapping("/bbs/{boardId}/detail/{detailId}/deleteComment")
    public String bbsCommentDelete(@PathVariable String detailId, @PathVariable String boardId, @ModelAttribute CommentModel bbsModel, Model model, @AuthenticationPrincipal AuthUser authUser){
		
    	CommonBbsService.deleteBbsComment(bbsModel);
    	
        return "board/common/commonBbsDetail";
    }
	

}