package com.bdp.ap.app.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
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

import com.bdp.ap.app.board.model.DataRoomModel;
import com.bdp.ap.app.board.service.DataRoomService;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.app.file.service.FileService;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.props.FileProps;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board")
@Controller
@Slf4j
public class DataRoomController {
	
	@Resource(name = "fileProps")
	private FileProps fileProps;

	@Resource
    private IdUtil idUtil;

	@Resource
	private CodeService codeService;

	@Resource
	private DataRoomService dataRoomService;

	@Resource
	private FileService fileService;

    @GetMapping("/dataRoom")
    public String dataRoom(@ModelAttribute Criteria criteria, Model model){
		model.addAttribute("dataRoomList", dataRoomService.selectDataRoomList(criteria));
		criteria.setTotalCount(dataRoomService.selectDataRoomListCount(criteria));
		model.addAttribute("pages", criteria);
        return "board/dataRoom/dataRoom";
    }

    @PostMapping("/dataRoom")
    public String dataRoom(@ModelAttribute Criteria criteria, RedirectAttributes attributes){
        attributes.addFlashAttribute("criteria", criteria);
        return "redirect:/board/dataRoom";
    }

    @GetMapping("/dataRoom/regist")
    public String select(@ModelAttribute Criteria criteria, Model model){

		model.addAttribute("pages", criteria);

        return "board/dataRoom/dataRoomRegist";
    }

    @PostMapping("/dataRoom/insert")
    public String insert(@ModelAttribute DataRoomModel dataInfo, @AuthenticationPrincipal AuthUser authUser, Model model){
        dataInfo.setRgstId(authUser.getMemberModel().getUserId());
    	dataInfo.setModiId(authUser.getMemberModel().getUserId());
    	dataInfo.setDataId(idUtil.getDataId());
    	model.addAttribute("dataInsertInfo", dataRoomService.insertDataRoom(dataInfo));

    	if(dataInfo.getFileIds() != null) {
	    	for(String fileId : dataInfo.getFileIds()) {
	    		FileModel file = new FileModel();
	    		file.setFileId(fileId);
	    		file.setRefId(dataInfo.getDataId());
	    		file.setModiId(authUser.getMemberModel().getUserId());
	    		fileService.updateFile(file);
	    	}
    	}

        return "redirect:/board/dataRoom";
    }

    @GetMapping("/dataRoom/detail/{dataId}")
    public String detail(@ModelAttribute Criteria criteria, @PathVariable String dataId, Model model) {
        DataRoomModel dataRoomModel = new DataRoomModel();
        dataRoomModel.setDataId(dataId);
		dataRoomService.addViewCntDataRoom(dataRoomModel);
        model.addAttribute("dataRoomInfo", dataRoomService.selectDataRoom(dataRoomModel));

    	//파일 조회
    	FileModel f = new FileModel();
    	f.setRefId(dataId);
    	List<FileModel> fileList = fileService.selectFileList(f);
		List<Map> files = new ArrayList();
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
    		files.add(obj);
    	}

    	model.addAttribute("fileList", fileList);
    	model.addAttribute("fileJsonList", files);

		model.addAttribute("pages", criteria);

        return "board/dataRoom/dataRoomDetail";
    }

    @GetMapping("/dataRoom/modify/{dataId}")
    public String modify(@ModelAttribute Criteria criteria, @PathVariable String dataId, Model model) {
        DataRoomModel dataRoomModel = new DataRoomModel();
        dataRoomModel.setDataId(dataId);
        model.addAttribute("dataRoomInfo", dataRoomService.selectDataRoom(dataRoomModel));

    	//파일 조회
    	FileModel f = new FileModel();
    	f.setRefId(dataId);
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

        return "board/dataRoom/dataRoomRegist";
    }

    @PostMapping("/dataRoom/update")
    public String update(@ModelAttribute DataRoomModel dataInfo, @AuthenticationPrincipal AuthUser authUser, Model model){
    	dataInfo.setModiId(authUser.getMemberModel().getUserId());

    	model.addAttribute("dataUpdateInfo", dataRoomService.updateDataRoom(dataInfo));

    	if(dataInfo.getFileIds() != null) {
	    	for(String fileId : dataInfo.getFileIds()) {
	    		FileModel file = new FileModel();
	    		file.setFileId(fileId);
	    		file.setRefId(dataInfo.getDataId());
	    		file.setModiId(authUser.getMemberModel().getUserId());
	    		fileService.updateFile(file);
	    	}
    	}

        return "redirect:/board/dataRoom";
    }

    @PostMapping("/dataRoom/delete")
    public String delete(@ModelAttribute DataRoomModel dataInfo,@AuthenticationPrincipal AuthUser authUser, Model model){
        
    	dataInfo.setModiId(authUser.getMemberModel().getUserId());
    	model.addAttribute("dataRoomDeleteInfo", dataRoomService.deleteDataRoom(dataInfo));

        return "redirect:/board/dataRoom";
    }
}
