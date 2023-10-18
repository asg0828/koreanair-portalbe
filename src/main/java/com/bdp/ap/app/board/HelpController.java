package com.bdp.ap.app.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdp.ap.app.board.model.HelpModel;
import com.bdp.ap.app.board.service.HelpService;
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

@RequestMapping("/board")
@Controller
@Slf4j
public class HelpController {
	
	@Resource(name = "fileProps")
	private FileProps fileProps;

	@Resource
    private IdUtil idUtil;

	@Resource
	private CodeService codeService;

	@Resource
	private HelpService helpService;

	@Resource
	private FileService fileService;


	/**
	 * Search All Info
	 * @param criteria
	 * @param model
	 * @return
	 */
    @GetMapping("/help")
    public String help(@ModelAttribute Criteria criteria, Model model) {
    	model.addAttribute("helpList", helpService.selectHelpList(criteria));
    	criteria.setTotalCount(helpService.selectHelpListCount(criteria));
    	model.addAttribute("pages", criteria);
    	model.addAttribute("categoryCd", codeService.selectGroupIdAllList("CATEGORY_CD"));
    	model.addAttribute("groupCd", codeService.selectGroupIdAllList("COMPANY_CODE"));
        return "board/help/help";
    }

    /**
     * Search Specific Info
     * @param criteria
     * @param attributes
     * @return
     */
    @PostMapping("/help")
    public String help(@ModelAttribute Criteria criteria, RedirectAttributes attributes) {
        attributes.addFlashAttribute("criteria", criteria);
        return "redirect:/board/help";
    }

    /**
     * Get New Info
     * @param model
     * @return
     */
    @GetMapping("/help/regist")
    public String select(@ModelAttribute Criteria criteria, Model model) {
        //공지사항에서 사용하는 공통코드 모두 set
    	helpService.selectAllHelpCodeList(model);

		model.addAttribute("pages", criteria);

        return "board/help/helpRegist";
    }

    /**
     * Get Old Info
     * @param helpId
     * @param model
     * @return
     */
    @GetMapping("/help/modify/{helpId}")
    public String select(@ModelAttribute Criteria criteria, @PathVariable String helpId, Model model) {
        HelpModel helpModel = new HelpModel();
        helpModel.setHelpId(helpId);
        model.addAttribute("helpInfo", helpService.selectHelp(helpModel));

        //공지사항에서 사용하는 공통코드 모두 set
    	helpService.selectAllHelpCodeList(model);

    	//파일 조회
    	FileModel f = new FileModel();
    	f.setRefId(helpId);
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
    		obj.put("fileUrl", fm.getFileUrl());
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

        return "board/help/helpRegist";
    }

	/**
     * Get Old Info
     * @param helpId
     * @param model
     * @return
     */
    @GetMapping("/help/detail/{helpId}")
    public String detail(@ModelAttribute Criteria criteria, @PathVariable String helpId, Model model) {
        HelpModel helpModel = new HelpModel();
        helpModel.setHelpId(helpId);
		helpService.addViewCntHelp(helpModel);
        model.addAttribute("helpInfo", helpService.selectHelp(helpModel));

        //공지사항에서 사용하는 공통코드 모두 set
    	helpService.selectAllHelpCodeList(model);

    	//파일 조회
    	FileModel f = new FileModel();
    	f.setRefId(helpId);
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

        return "board/help/helpDetail";
    }


    /**
     * Insert New Info
     * @param helpInfo
     * @param authUser
     * @param model
     * @return
     */
    @PostMapping("/help/insert")
    public String insert(@ModelAttribute HelpModel helpInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
    	helpInfo.setRgstId(authUser.getMemberModel().getUserId());
    	helpInfo.setModiId(authUser.getMemberModel().getUserId());
    	helpInfo.setHelpId(idUtil.getHelpId());
    	model.addAttribute("helpInsertInfo", helpService.insertHelp(helpInfo));

    	if(helpInfo.getFileIds() != null) {
	    	for(String fileId : helpInfo.getFileIds()) {
	    		FileModel file = new FileModel();
	    		file.setFileId(fileId);
	    		file.setRefId(helpInfo.getHelpId());
	    		file.setModiId(authUser.getMemberModel().getUserId());
	    		fileService.updateFile(file);
	    	}
    	}
    	log.debug("HELP UPSERT MODEL: {}", model);

    	return "redirect:/board/help";
    }

    /**
     * Update Old Info
     * @param helpInfo
     * @param authUser
     * @param model
     * @return
     */
    @PostMapping("/help/update")
    public String update(@ModelAttribute HelpModel helpInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
    	helpInfo.setModiId(authUser.getMemberModel().getUserId());

		System.out.println(helpInfo.toString());
    	model.addAttribute("helpUpdateInfo", helpService.updateHelp(helpInfo));

    	if(helpInfo.getFileIds() != null) {
    		for(String fileId : helpInfo.getFileIds()) {
    			FileModel file = new FileModel();
    			file.setFileId(fileId);
    			file.setRefId(helpInfo.getHelpId());
    			file.setModiId(authUser.getMemberModel().getUserId());
    			fileService.updateFile(file);
    		}
    	}
    	log.debug("HELP UPSERT MODEL: {}", model);

    	return "redirect:/board/help";
    }

    /**
     * Delete Old Info
     * @param helpId
     * @param model
     * @return
     */
    @PostMapping("/help/delete")
    public String delete(@ModelAttribute HelpModel helpInfo,@ModelAttribute FileModel fileModel,@AuthenticationPrincipal AuthUser authUser, Model model) {
    	helpInfo.setModiId(authUser.getMemberModel().getUserId());
    	model.addAttribute("helpDeleteInfo", helpService.deleteHelp(helpInfo));
    	/*
    	fileModel.setRefId(helpInfo.getHelpId());
    	fileModel.setModiId(authUser.getMemberModel().getUserId());
    	fileService.deleteFileParent(fileModel);
    	*/
    	log.debug("HELP UPSERT MODEL: {}", model);

    	return "redirect:/board/help";
    }

    /**
	 * Search All Info
	 * @param criteria
	 * @param model
	 * @return
	 */
    @NoLogging
    @Profile({Constant.Profile.LOCAL})
    @GetMapping("/help/detail/upload/{helpId}")
    public String helpUpload(@PathVariable String helpId, Model model) {
    	HelpModel helpModel = new HelpModel();
        helpModel.setHelpId(helpId);
        model.addAttribute("helpInfo", helpService.selectHelp(helpModel));
    	model.addAttribute("categoryCd", codeService.selectGroupIdAllList("CATEGORY_CD"));
    	model.addAttribute("groupCd", codeService.selectGroupIdAllList("COMPANY_CODE"));

    	FileModel f = new FileModel();
    	f.setRefId(helpId);
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
        return "board/help/helpUpload";
    }

}
