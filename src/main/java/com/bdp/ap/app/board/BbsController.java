package com.bdp.ap.app.board;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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

import com.bdp.ap.app.board.model.BbsModel;
import com.bdp.ap.app.board.service.BbsService;
import com.bdp.ap.app.code.model.CodeModel;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.file.service.FileService;
import com.bdp.ap.app.role.model.RoleModel;
import com.bdp.ap.app.role.service.RoleService;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board")
@Controller
public class BbsController {
	@Resource
    private CodeService codeService;

	@Resource
	private BbsService service;

	@Resource
	private FileService fileService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
    private IdUtil idUtil;
	
	@GetMapping("/bbs")
    public String bbs(@ModelAttribute Criteria criteria, Model model) {
		model.addAttribute("bbsList", service.selectBbsList(criteria));
    	criteria.setTotalCount(service.selectBbsListCount(criteria));
    	model.addAttribute("pages", criteria);

    	return "board/bbsList";
	}
	
	@GetMapping("/bbs/regist")
	public String regist(@ModelAttribute Criteria criteria, Model model) {
		
		model.addAttribute("bbsItemList", service.selectBbsItemList(null));
		model.addAttribute("boardKindList",codeService.selectGroupIdAllList("TYP_CD"));

		model.addAttribute("pages", criteria);
		
		return "board/bbsRegist";
	}
	
	@PostMapping("/bbs/insert")
    public String insert(@ModelAttribute BbsModel bbsInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
		bbsInfo.setRgstId(authUser.getMemberModel().getUserId());
		bbsInfo.setModiId(authUser.getMemberModel().getUserId());
		bbsInfo.setCompanyCode(authUser.getMemberModel().getCompanyCode());
		bbsInfo.setBoardId(idUtil.getBbsId());
		bbsInfo.setUrlAddr("/bbs/"+bbsInfo.getBoardId());
		service.insertBbs(bbsInfo);
		
		return "redirect:/board/bbs";
	}
	
	@GetMapping("/bbs/modify/{boardId}")
	public String modify(@ModelAttribute Criteria criteria, @AuthenticationPrincipal AuthUser authUser, @PathVariable String boardId, Model model) {
		BbsModel bbsModel = new BbsModel();
		bbsModel.setBoardId(boardId);
		
		model.addAttribute("bbsInfo", service.selectBbsDetail(bbsModel));
		model.addAttribute("bbsItemList", service.selectBbsItemMappList(bbsModel));  //사용_필수항목
		model.addAttribute("bbsKindList", service.selectBbsKindList(bbsModel));   //분류
		model.addAttribute("boardKindList",codeService.selectGroupIdAllList("TYP_CD"));
		bbsModel.setAuthDivCd("01");
		model.addAttribute("findAuthIds", service.selectBbsAuthList(bbsModel));   //조회권한
		bbsModel.setAuthDivCd("02");
		model.addAttribute("wkAuthIds", service.selectBbsAuthList(bbsModel));    //작성권한
		bbsModel.setAuthDivCd("03");
		model.addAttribute("mngAuthIds", service.selectBbsMngAuthList(bbsModel));  //관리권한

		model.addAttribute("pages", criteria);
		
		return "board/bbsRegist";
	 }
	
	@GetMapping("/bbs/detail/{boardId}")
	public String detail(@ModelAttribute Criteria criteria, @AuthenticationPrincipal AuthUser authUser, @PathVariable String boardId, Model model) {
		BbsModel bbsModel = new BbsModel();
		bbsModel.setBoardId(boardId);
		
		model.addAttribute("bbsInfo", service.selectBbsDetail(bbsModel));
		model.addAttribute("bbsItemList", service.selectBbsItemMappList(bbsModel));  //사용_필수항목
		model.addAttribute("bbsKindList", service.selectBbsKindList(bbsModel));   //분류
		bbsModel.setAuthDivCd("01");
		model.addAttribute("findAuthIds", service.selectBbsAuthList(bbsModel));   //조회권한
		bbsModel.setAuthDivCd("02");
		model.addAttribute("wkAuthIds", service.selectBbsAuthList(bbsModel));    //작성권한
		bbsModel.setAuthDivCd("03");
		model.addAttribute("mngAuthIds", service.selectBbsMngAuthList(bbsModel));  //관리권한

		model.addAttribute("pages", criteria);
		
		return "board/bbsDetail";
	 }
	
	@PostMapping("/bbs/update")
    public String update(@ModelAttribute BbsModel bbsInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
		bbsInfo.setModiId(authUser.getMemberModel().getUserId());
		
		service.updateBbs(bbsInfo);
		
		return "redirect:/board/bbs";
	}
	
	@PostMapping("/bbs/delete")
	public String delete(@ModelAttribute BbsModel bbsInfo, @AuthenticationPrincipal AuthUser authUser, Model model) {
		bbsInfo.setModiId(authUser.getMemberModel().getUserId());
		
		service.deleteBbs(bbsInfo);
		
		return "redirect:/board/bbs";
	}
	
	/**
	 * 분류명 팝업리스트 조회
	 * @param authUser
	 * @return
	 */
	@GetMapping("/kindList")
	public ResponseEntity  kindList(@AuthenticationPrincipal AuthUser authUser) {
		try {
			 
			List<CodeModel> result =codeService.selectGroupIdAllList("BOARD_KIND_ID");
			
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	/**
	 * 권한조회
	 * @param criteria
	 * @param authUser
	 * @return
	 */
	@PostMapping("/bbs/selectUserAuth/ajax")
    public ResponseEntity selectUserAuth(@ModelAttribute Criteria criteria, @AuthenticationPrincipal AuthUser authUser) {
		try {
			
			List<RoleModel>list =roleService.selectList(criteria);
			Map<String, RoleModel> result = new HashMap<>();
	        
	        for (RoleModel model : list) {
	        	result.put(model.getAuthId(), model);
	        }
	        
	        return new ResponseEntity<>(new org.json.JSONObject(result).toString(), HttpStatus.OK);	
	        
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}		
	}
	
	
	/**
	 * 테스트용
	 * @param authUser
	 * @return
	 */
	@GetMapping("/createTable")
    public ResponseEntity  createTable(@AuthenticationPrincipal AuthUser authUser) {
				
		try {
			List<String> result = new ArrayList();
			service.createTable();
			result.add("ok");
			
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		 
	}
	
}
