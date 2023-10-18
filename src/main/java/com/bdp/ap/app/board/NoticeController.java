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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdp.ap.app.board.model.NoticeModel;
import com.bdp.ap.app.board.service.NoticeService;
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
public class NoticeController {
	
	@Resource(name = "fileProps")
	private FileProps fileProps;

	@Resource
    private IdUtil idUtil;

	@Resource
	private CodeService codeService;

	@Resource
	private NoticeService noticeService;

	@Resource
	private FileService fileService;

	/**
     * @param model
     * @return
     */
    @GetMapping("/n")
    public String list(Model model) {
        //공지사항에서 사용하는 공통코드 모두 set
		noticeService.selectAllNoticeCodeList(model);
        return "redirect:notice";
    }

	/**
	 * Search All Info
	 * 공지사항 목록 조회
	 * @param criteria
	 * @param model
	 * @return
	 */
    @GetMapping("/notice")
    public String notice(@ModelAttribute Criteria criteria, Model model) {
		model.addAttribute("noticeList", noticeService.selectNoticeList(criteria));
		criteria.setTotalCount(noticeService.selectNoticeListCount(criteria));
		model.addAttribute("pages", criteria);
		model.addAttribute("codeUseYnList", codeService.selectGroupIdAllList("USE_YN"));
		model.addAttribute("codeImportantYnList", codeService.selectGroupIdAllList("IMPORTANT_YN"));
        return "board/notice/notice";
    }

    /**
     * Search Specific Info
	 * 공지사항 목록조회
     * @param criteria
     * @param attributes
     * @return
     */
    @PostMapping("/notice")
    public String notice(@ModelAttribute Criteria criteria, RedirectAttributes attributes) {
        attributes.addFlashAttribute("criteria", criteria);
        return "redirect:/board/notice";
    }

    /**
     * 공지사항 등록
     * @param model
     * @return
     */
    @GetMapping("/notice/regist")
    public String select(@ModelAttribute Criteria criteria, Model model) {
        //공지사항에서 사용하는 공통코드 모두 set
		noticeService.selectAllNoticeCodeList(model);

		model.addAttribute("pages", criteria);

        return "board/notice/noticeRegist";
    }

    /**
     * 공지사항 수정 화면 이동
     * @param noticeId
     * @param model
     * @return
     */
    @GetMapping("/notice/modify/{noticeId}")
    public String modify(@ModelAttribute Criteria criteria, @AuthenticationPrincipal AuthUser authUser, @PathVariable String noticeId, Model model) {
        NoticeModel noticeModel = new NoticeModel();
        noticeModel.setNoticeId(noticeId);
		NoticeModel nModel = noticeService.selectNotice(noticeModel);
        model.addAttribute("noticeInfo", nModel);
        /*if(!nModel.getRgstId().equals(authUser.getMemberModel().getAuthId())){
			//본인글만 수정할거면
			return "error/alert";
		}*/
        //공지사항에서 사용하는 공통코드 모두 set
		noticeService.selectAllNoticeCodeList(model);
		//파일 조회
		FileModel f = new FileModel();
		f.setRefId(noticeId);
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
		
        return "board/notice/noticeRegist";
    }

	/**
     * 공지사항 상세 화면 이동
     * @param noticeId
     * @param model
     * @return
     */
    @GetMapping("/notice/detail/{noticeId}")
    public String detail(@ModelAttribute Criteria criteria, @PathVariable String noticeId, Model model) {
        NoticeModel noticeModel = new NoticeModel();
        noticeModel.setNoticeId(noticeId);
		noticeService.addViewCntNotice(noticeModel);
        model.addAttribute("noticeInfo", noticeService.selectNotice(noticeModel));

        //공지사항에서 사용하는 공통코드 모두 set
		noticeService.selectAllNoticeCodeList(model);

		//파일 조회
		FileModel f = new FileModel();
		f.setRefId(noticeId);
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

        return "board/notice/noticeDetail";
    }


    /**
     * 공지사항 등록
     * @param noticeInfo
     * @param authUser
     * @param model
     * @return
     */
    @PostMapping("/notice/insert")
    public String insert(@ModelAttribute NoticeModel noticeInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
		noticeInfo.setRgstId(authUser.getMemberModel().getUserId());
		noticeInfo.setModiId(authUser.getMemberModel().getUserId());
		noticeInfo.setNoticeId(idUtil.getNoticeId());
		model.addAttribute("noticeInsertInfo", noticeService.insertNotice(noticeInfo));

		if(noticeInfo.getFileIds() != null) {
			for(String fileId : noticeInfo.getFileIds()) {
				FileModel file = new FileModel();
				file.setFileId(fileId);
				file.setRefId(noticeInfo.getNoticeId());
				file.setModiId(authUser.getMemberModel().getUserId());
				fileService.updateFile(file);
			}
		}
		log.debug("NOTICE UPSERT MODEL: {}", model);

		return "redirect:/board/notice";
    }

    /**
     * 공지사항 수정
     * @param noticeInfo
     * @param authUser
     * @param model
     * @return
     */
    @PostMapping("/notice/update")
    public String update(@ModelAttribute NoticeModel noticeInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
		noticeInfo.setModiId(authUser.getMemberModel().getUserId());

		model.addAttribute("noticeUpdateInfo", noticeService.updateNotice(noticeInfo));

		if(noticeInfo.getFileIds() != null) {
			for(String fileId : noticeInfo.getFileIds()) {
				FileModel file = new FileModel();
				file.setFileId(fileId);
				file.setRefId(noticeInfo.getNoticeId());
				file.setModiId(authUser.getMemberModel().getUserId());
				fileService.updateFile(file);
			}
		}
		log.debug("NOTICE UPSERT MODEL: {}", model);

		return "redirect:/board/notice";
    }

    /**
     * 공지사항 삭제
     * @param noticeId
     * @param model
     * @return
     */
    @PostMapping("/notice/delete")
    public String delete(@ModelAttribute NoticeModel noticeInfo,@ModelAttribute FileModel fileModel,@AuthenticationPrincipal AuthUser authUser, Model model) {
		noticeInfo.setModiId(authUser.getMemberModel().getUserId());
		model.addAttribute("noticeDeleteInfo", noticeService.deleteNotice(noticeInfo));

		log.debug("NOTICE UPSERT MODEL: {}", model);

		return "redirect:/board/notice";
    }

    /**
	 * 파일업로드
	 * @param criteria
	 * @param model
	 * @return
	 */
    @NoLogging
    @Profile({Constant.Profile.LOCAL})
    @GetMapping("/notice/detail/upload/{noticeId}")
    public String noticeUpload(@PathVariable String noticeId, Model model) {
		NoticeModel noticeModel = new NoticeModel();
        noticeModel.setNoticeId(noticeId);
        model.addAttribute("noticeInfo", noticeService.selectNotice(noticeModel));
		model.addAttribute("codeUseYnList", codeService.selectGroupIdAllList("USE_YN"));
		model.addAttribute("codeImportantYnList", codeService.selectGroupIdAllList("IMPORTANT_YN"));

		FileModel f = new FileModel();
		f.setRefId(noticeId);
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
        return "board/notice/noticeUpload";
    }

}
