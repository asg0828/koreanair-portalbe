package com.bdp.ap.app.file;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.app.file.service.FileService;
import com.bdp.ap.common.annotation.NoLogging;
import com.bdp.ap.config.security.AuthUser;

/**
 * File Upload and Download Controller
 */
@Controller
public class FileController {

	@Resource(name="fileService")
	private FileService service;

	/**
	 * File Upload
	 * @param request
	 * @param multipart
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/file/insert", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> upload(HttpServletRequest request, MultipartRequest multipart, @AuthenticationPrincipal AuthUser authUser) {
		Map<String,Object> result = service.upload(request, multipart, authUser);
		return result;
	}

	/**
	 * File Download
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/file/detail/{fileId}", method=RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> download(HttpServletRequest request, @PathVariable String fileId) {
		ResponseEntity<ByteArrayResource> result = service.download(fileId);
		return result;
	}

	/**
	 * File delete
	 * @param request
	 * @param multipart
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/file/delete/{fileId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> delete(HttpServletRequest request, @AuthenticationPrincipal AuthUser authUser, @ModelAttribute FileModel fileModel, @PathVariable String fileId) {

		//Map<String,Object> result = null;
		Map<String,Object> result = new HashMap<>();
		fileModel.setFileUrl(fileId);
		fileModel.setUseYn("N");
		fileModel.setModiId(authUser.getMemberModel().getUserId());
		long cnt = service.deleteFile(fileModel);

		result.put("cnt", cnt);
		//파일 조회
		//FileModel file = service.selectFile(fileModel);

		//result = service.delete(request, file, authUser);
		return result;
	}

	/**
	 * Tableau preview Download
	 * @param request
	 * @return
	 */
	@NoLogging
	@RequestMapping(value="/file/preview/{fileId}", method=RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> previewUrl(HttpServletRequest request, @PathVariable String fileId) {
		ResponseEntity<ByteArrayResource> result = service.getPreview(request, fileId);
		return result;
	}

	/**
	 * Tableau view Download
	 * @param request
	 * @return
	 */
	@NoLogging
	@RequestMapping(value="/file/view/{fileId}", method=RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> viewUrl(HttpServletRequest request, @PathVariable String fileId) {
		ResponseEntity<ByteArrayResource> result = service.getView(request, fileId);
		return result;
	}

	/**
	 * 사원 사진 Download - 특정 사용자 사진
	 * @param request
	 * @return
	 */
	@NoLogging
	@RequestMapping(value="/file/photo/{fileId}", method=RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> userPhotoUrl(HttpServletRequest request, @PathVariable String fileId) {
		ResponseEntity<ByteArrayResource> result = service.getUserPhoto(request, fileId);
		return result;
	}

	/**
	 * 사원 사진 Download - 로그인 사용자 사진
	 * @param request
	 * @return
	 */
	@NoLogging
	@RequestMapping(value="/file/photo", method=RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> userPhoto(HttpServletRequest request, @AuthenticationPrincipal AuthUser authUser) {
		ResponseEntity<ByteArrayResource> result = service.getUserPhoto(request, authUser);
		return result;
	}

	/**
	 * editor 이미지 Download
	 * @param request
	 * @return
	 */
	@NoLogging
	@RequestMapping(value="/file/edimage/{fileId}", method=RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> editorImageUrl(HttpServletRequest request, @PathVariable String fileId) {
		ResponseEntity<ByteArrayResource> result = service.getEditorImage(request, fileId);
		return result;
	}
}
