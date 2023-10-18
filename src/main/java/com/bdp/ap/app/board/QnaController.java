package com.bdp.ap.app.board;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdp.ap.app.board.model.HelpModel;
import com.bdp.ap.app.board.model.QnaModel;
import com.bdp.ap.app.board.service.QnaService;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.app.file.service.FileService;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.security.AuthUser;

/**
 * QnA 컨트롤러
 */
@RequestMapping("/board")
@Controller
public class QnaController {

	@Resource
	private CodeService codeService;
	
	@Resource
	private QnaService qnaService;
	
	@Resource
    private IdUtil idUtil;	

	@Resource
	private FileService fileService;	

    /**
     * QnA 게시판 페이지로 이동한다.
     *
     * @param model
     * @return
     */
    @GetMapping("/qna")
    public String qna(@ModelAttribute Criteria criteria, Model model) {

    	model.addAttribute("qnaList", qnaService.selectQnaList(criteria));
    	criteria.setTotalCount(qnaService.selectQnaListCount(criteria));
    	model.addAttribute("pages", criteria);
    	qnaService.selectAllQnaCodeList(model);	

    	return "board/qna/qna";
    }

    /**
     * QnA 게시판 페이지로 이동한다.
     *
     * @param model
     * @return
     */
    @PostMapping("/qna")
    public String qnaPost(@ModelAttribute Criteria criteria, Model model, RedirectAttributes attributes) {

    	attributes.addFlashAttribute("criteria", criteria);

    	return "redirect:/board/qna";
    }
 
	//상세
    @GetMapping("/qna/detail/{qnaId}")
    public String select(@PathVariable String qnaId, Model model, @ModelAttribute Criteria criteria, @AuthenticationPrincipal AuthUser authUser) {
        QnaModel qnaModel = new QnaModel();
        qnaModel.setQnaId(qnaId);
		//view카운트++
    	qnaService.addViewCntQna(qnaModel);
        model.addAttribute("qnaInfo", qnaService.selectQna(qnaModel));
    	qnaService.selectAllQnaCodeList(model);
    	
    	//파일 조회
    	FileModel f = new FileModel();
    	f.setRefId(qnaId);
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
    	
    	//get 저장
    	model.addAttribute("pages", criteria);
    	
    	//권한 체크
    	model.addAttribute("userId", authUser.getMemberModel().getUserId());
    	
    	//답글리스트
    	model.addAttribute("qnaReplyInfo", qnaService.selectQnaReplyList(qnaModel));
    	
        return "board/qna/qnaDetail";
    }

	//글쓰기
    @GetMapping("/qna/regist")
    public String regist(@ModelAttribute Criteria criteria, Model model) {
    	qnaService.selectAllQnaCodeList(model);

		model.addAttribute("pages", criteria);
        return "board/qna/qnaRegist";
    }    

    //insert
    @PostMapping("/qna/insert")
    public String insert(@ModelAttribute QnaModel qnaInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
    	qnaInfo.setRgstId(authUser.getMemberModel().getUserId());
    	qnaInfo.setModiId(authUser.getMemberModel().getUserId());
   		qnaInfo.setBfQnaId(qnaInfo.getQnaId());
    	qnaInfo.setQnaId(idUtil.getQnaId());
    	qnaInfo.setQnaStat("UNREAD");
    	qnaInfo.setUseYn("Y");
    	model.addAttribute("qnaInsertInfo", qnaService.insertQna(qnaInfo));
    	
    	if(qnaInfo.getFileIds() != null) {
    		for(String fileId : qnaInfo.getFileIds()) {
    			FileModel file = new FileModel();
    			file.setFileId(fileId);
    			file.setRefId(qnaInfo.getQnaId());
    			file.setModiId(authUser.getMemberModel().getUserId());
    			fileService.updateFile(file);
    		}
    	}
    	
    	return "redirect:/board/qna";
    }
    
    //글수정
    @GetMapping("/qna/modify/{qnaId}")
    public String modify(@PathVariable String qnaId, Model model, @ModelAttribute Criteria criteria, @AuthenticationPrincipal AuthUser authUser) {
        QnaModel qnaModel = new QnaModel();
        qnaModel.setQnaId(qnaId);
        qnaModel.setDeptCd(authUser.getMemberModel().getDeptCode());
        model.addAttribute("qnaInfo", qnaService.selectQna(qnaModel));
    	qnaService.selectAllQnaCodeList(model);
    	
    	//파일 조회
    	FileModel f = new FileModel();
    	f.setRefId(qnaId);
    	f.setFileCl(Constant.File.BOARDQ);
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
    	
    	//get 저장
    	model.addAttribute("pages", criteria);
    	//권한 체크
    	model.addAttribute("userId", authUser.getMemberModel().getUserId());
        return "board/qna/qnaRegist";
    }
    
    //update
    @PostMapping("/qna/update")
    public String update(@ModelAttribute QnaModel qnaInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
    	qnaInfo.setModiId(authUser.getMemberModel().getUserId());
    	model.addAttribute("qnaUpdateInfo", qnaService.updateQna(qnaInfo));
    	
    	if(qnaInfo.getFileIds() != null) {
    		for(String fileId : qnaInfo.getFileIds()) {
    			FileModel file = new FileModel();
    			file.setFileId(fileId);
    			file.setRefId(qnaInfo.getQnaId());
    			file.setModiId(authUser.getMemberModel().getUserId());
    			fileService.updateFile(file);
    		}
    	}
    	
    	return "redirect:/board/qna";
    }
    
    //delete
    @PostMapping("/qna/delete")
    public String delete(@ModelAttribute QnaModel qnaInfo, Model model) {
    	model.addAttribute("qnaDeleteInfo", qnaService.deleteQna(qnaInfo));
    	return "redirect:/board/qna";
    }  
       
    
    @PostMapping("/selectQnaReplayInfo/ajax")
    public ResponseEntity  selectQnaReplayInfo(@ModelAttribute QnaModel qnaModel, @AuthenticationPrincipal AuthUser authUser) {
				
		try {
			
			Map<Object,Object> result = new HashMap<>();
			result.put("qnaReplyInfo", qnaService.selectQnaReplyList(qnaModel));
			
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) { 
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}		 
	}
    
    @PostMapping("/qnaStatUpdate/ajax")
    public ResponseEntity qnaStatUpdate(@ModelAttribute QnaModel qnaModel, @AuthenticationPrincipal AuthUser authUser) {
    	try {
			
			Map<Object,Object> result = new HashMap<>();
			result.put("qnaStatUpdate", qnaService.qnaStat(qnaModel));
			
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) { 
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}		
    }
}
