package com.bdp.ap.app.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bdp.ap.app.dept.model.DeptClModel;
import com.bdp.ap.app.dept.service.DeptService;
import com.bdp.ap.app.rank.model.RankModel;
import com.bdp.ap.app.rank.service.RankService;
import com.bdp.ap.app.role.model.MgrRoleModel;
import com.bdp.ap.app.role.service.MgrRoleService;
import com.bdp.ap.app.user.model.UserCriteria;
import com.bdp.ap.app.user.model.UserModel;
import com.bdp.ap.app.user.service.UserService;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/system")
@Controller
public class UserController {
	@Resource
	 private UserService userService;
	
	@Resource
	private DeptService deptService;
 
	@Resource
	private RankService rankService;
	
	@Resource
	private MgrRoleService mgrRoleService;
	
	/**
	 * 사용자조회
	 * @param criteria
	 * @param authUser
	 * @return
	 */
	@PostMapping("/selectUserList/ajax")
    public ResponseEntity  selectUserList(@ModelAttribute UserCriteria criteria, @AuthenticationPrincipal AuthUser authUser) {
				
		try {
			List<UserModel> result = userService.selectUserList(criteria);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		 
	}
	
	/**
	 * 부서조회
	 * @param criteria
	 * @param authUser
	 * @return
	 */
	@PostMapping("/selectDeptUser/ajax")
    public ResponseEntity  selectDeptUser(@ModelAttribute UserCriteria criteria, @AuthenticationPrincipal AuthUser authUser) {
				
		try {
			int iCnt = userService.selectDeptUserCount(criteria);
			List<UserModel> list = userService.selectDeptUser(criteria);
			Map<Object,Object> result = new HashMap<>();
			result.put("deptUserTotalCount",iCnt);
			result.put("deptUserList", list);
			
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		 
	}
	
	/**
	 * 직위조회
	 * @param criteria
	 * @param authUser
	 * @return
	 */
	@PostMapping("/selectPstnUser/ajax")
    public ResponseEntity  selectPstnUser(@ModelAttribute UserCriteria criteria, @AuthenticationPrincipal AuthUser authUser) {
				
		try {
			int iCnt = userService.selectPstnUserCount(criteria);
			List<UserModel> list = userService.selectPstnUser(criteria);
			Map<Object,Object> result = new HashMap<>();
			result.put("pstnUserTotalCount",iCnt);
			result.put("pstnUserList", list);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		 
	}
	
	/**
	 * 직책조회
	 * @param criteria
	 * @param authUser
	 * @return
	 */
	@PostMapping("/selectRankUser/ajax")
    public ResponseEntity  selectRankUser(@ModelAttribute UserCriteria criteria, @AuthenticationPrincipal AuthUser authUser) {
				
		try {
			int iCnt = userService.selectRankUserCount(criteria);
			List<UserModel> list = userService.selectRankUser(criteria);
			Map<Object,Object> result = new HashMap<>();
			result.put("rankUserTotalCount",iCnt);
			result.put("rankUserList", list);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		 
	}
	
	/**
	 * 직급조회
	 * @param criteria
	 * @param authUser
	 * @return
	 */
	@PostMapping("/selectDutyUser/ajax")
    public ResponseEntity  selectDutyUser(@ModelAttribute UserCriteria criteria, @AuthenticationPrincipal AuthUser authUser) {
				
		try {
			int iCnt = userService.selectDutyUserCount(criteria);
			List<UserModel> list = userService.selectDutyUser(criteria);
			Map<Object,Object> result = new HashMap<>();
			result.put("dutyUserTotalCount",iCnt);
			result.put("dutyUserList", list);
			
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
	@PostMapping("/selectAuthUser/ajax")
    public ResponseEntity  selectAuthUser(@ModelAttribute UserCriteria criteria, @AuthenticationPrincipal AuthUser authUser) {
				
		try {
			int iCnt = userService.selectAuthUserCount(criteria);
			List<UserModel> list = userService.selectAuthUser(criteria);
			Map<Object,Object> result = new HashMap<>();
			result.put("authUserTotalCount",iCnt);
			result.put("authUserList", list);
			
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		 
	}
	
	/**
	 * 부서조회
	 * @param deptClModel
	 * @param authUser
	 * @return
	 */
	@PostMapping("/selectDept/ajax")
    public ResponseEntity selectDept(@ModelAttribute DeptClModel deptClModel, @AuthenticationPrincipal AuthUser authUser) {
		try {
			
			List<DeptClModel>list = deptService.selectList(deptClModel);
			 
	        Map<String, DeptClModel> result = new HashMap<>();
	        
	        for (DeptClModel model : list) {
	        	result.put(model.getDeptCode(), model);
	            
	        }
	        
			return new ResponseEntity<>(new org.json.JSONObject(result).toString(), HttpStatus.OK);	
	        
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
	@PostMapping("/selectAuth/ajax")
    public ResponseEntity selectAuth(@ModelAttribute Criteria criteria, @AuthenticationPrincipal AuthUser authUser) {
		try {
           
			List<MgrRoleModel>list =mgrRoleService.selectList(criteria);
			Map<String, MgrRoleModel> result = new HashMap<>();
	        
	        for (MgrRoleModel model : list) {
	        	result.put(model.getAuthId(), model);
	        }
	        
	        return new ResponseEntity<>(new org.json.JSONObject(result).toString(), HttpStatus.OK);	
	        
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}		
	}
	
	/**
	 * 직책조회
	 * @param rankModel
	 * @param authUser
	 * @return
	 */
	@PostMapping("/selectRank/ajax")
    public ResponseEntity selectRank(@ModelAttribute RankModel rankModel, @AuthenticationPrincipal AuthUser authUser) {
		try {
			
			List<RankModel> list = rankService.select(rankModel);
			Map<String, RankModel> result = new HashMap<>();
	        
	        for (RankModel model : list) {
	        	result.put(model.getRankCode(), model);
	        }
			 	         			
	        return new ResponseEntity<>(new org.json.JSONObject(result).toString(), HttpStatus.OK);	
	        
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}		
	}
	
}
