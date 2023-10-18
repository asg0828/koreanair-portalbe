package com.bdp.ap.app.member;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdp.ap.app.code.model.CodeModel;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.dept.service.DeptService;
import com.bdp.ap.app.member.model.MemberCriteria;
import com.bdp.ap.app.member.model.MemberModel;
import com.bdp.ap.app.member.service.MemberService;
import com.bdp.ap.app.role.model.RoleModel;
import com.bdp.ap.app.role.service.RoleService;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 사용자관리/사용자관리 컨트롤러
 */
@Slf4j
@RequestMapping("/system")
@Controller
public class MemberController {

    @Resource
    private MemberService memberService;

    @Resource
    private CodeService codeService;

    @Resource
    private RoleService roleService;

    @Resource
    private DeptService deptService;
    
    @Value("${company-props.code}")
	private String companyCode;

    /**
     * 상단 메뉴 이동용
     *
     * @param model
     * @return
     */
    @GetMapping("/m")
    public String mainlist(@ModelAttribute MemberCriteria criteria, Model model) {
        return "redirect:/system/member";
    }


    /**
     * 사용자관리 페이지로 이동한다.(최초 메뉴 진입시만 사용)
     *
     * @param model
     * @return
     */
    @GetMapping("/member")
    public String list(@ModelAttribute MemberCriteria criteria, Model model) {

    	try {
	        // 화면 표시용 코드 셋팅
	        // 회사구분 코드
	        model.addAttribute("codeCompanyCdList", codeService.selectGroupIdAllList("COMPANY_CODE"));
	        // 사용여부 코드
	        model.addAttribute("codeUseYnList", codeService.selectGroupIdAllList("USE_YN"));
	        // 검색구분 코드
	        model.addAttribute("codeMemSearchCdList", codeService.selectGroupIdAllList("USER_SEARCH_CODE"));
	
	        // 권한코드 조회
	        model.addAttribute("roles", roleService.selectAllList());
	
	        // 관리자권한코드 조회
	        RoleModel role =  new RoleModel();
	        model.addAttribute("mgrRoles", roleService.selectMgrSysAuthAllList(role));
	
	        model.addAttribute("members", memberService.selectMemberList(criteria));
	        criteria.setTotalCount(memberService.selectMemberListCount(criteria));
	        model.addAttribute("pages", criteria);
    	} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

        return "member/member";
    }

    /**
     * 조건 검색시 사용
     * @param criteria
     * @param attributes
     * @param model
     * @return
     */
    @PostMapping("/member")
    public String list(@ModelAttribute MemberCriteria criteria, RedirectAttributes attributes, Model model) {

        attributes.addFlashAttribute("criteria", criteria);

        // 화면 표시용 코드 셋팅
        // 회사구분 코드
        model.addAttribute("codeCompanyCdList", codeService.selectGroupIdAllList("COMPANY_CODE"));
        // 사용여부 코드
        model.addAttribute("codeUseYnList", codeService.selectGroupIdAllList("USE_YN"));
        // 검색구분 코드
        model.addAttribute("codeMemSearchCdList", codeService.selectGroupIdAllList("USER_SEARCH_CODE"));
        // 권한코드 조회
        model.addAttribute("roles", roleService.selectAllList());
       // 관리자권한코드 조회
        RoleModel role =  new RoleModel();
        model.addAttribute("mgrRoles", roleService.selectMgrSysAuthAllList(role));
        model.addAttribute("members", memberService.selectMemberList(criteria));
        criteria.setTotalCount(memberService.selectMemberListCount(criteria));
        model.addAttribute("pages", criteria);

        return "member/member";
    }
    /**
     * 사용자관리 상세 페이지로 이동한다.(신규 상세 수정)
     *
     * @param model
     * @return
     */
    @GetMapping("/member/regist") 
    public String regedit(@ModelAttribute MemberCriteria criteria, Model model) {

        // 화면 표시용 코드 셋팅
        // 회사구분 코드
        model.addAttribute("codeCompanyCdList", codeService.selectGroupIdAllList("COMPANY_CODE"));
        // 사용여부 코드
        model.addAttribute("codeUseYnList", codeService.selectGroupIdAllList("USE_YN"));
        // 검색구분 코드
        model.addAttribute("codeMemSearchCdList", codeService.selectGroupIdAllList("USER_SEARCH_CODE"));
        // 직위코드 조회
        // model.addAttribute("pstns", codeService.pstnSelect(null));
        // 부서 조회
        //model.addAttribute("depts", deptService.selectDeptClList());
        // 권한코드 조회
        model.addAttribute("roles", roleService.selectAllList());
        // 관리자권한코드 조회
        RoleModel role =  new RoleModel();
        model.addAttribute("mgrRoles", roleService.selectMgrSysAuthAllList(role));

        if(StringUtils.isEmpty(criteria.getUserId())){
            criteria.setUserId("Y");
            model.addAttribute("member", new MemberModel());
        }else{
            MemberModel memberModel = new MemberModel();
            memberModel.setUserId(criteria.getUserId());
            model.addAttribute("member", memberService.selectMember(memberModel));
        }
        //기본검색정보?
        model.addAttribute("pages", criteria);

        return "member/memberRegist";
    }

    @GetMapping("/member/modify/{memberId}")
    public String modify(@PathVariable String memberId, @ModelAttribute MemberCriteria criteria, Model model){

        model.addAttribute("codeCompanyCdList", codeService.selectGroupIdAllList("COMPANY_CODE"));
        model.addAttribute("codeUseYnList", codeService.selectGroupIdAllList("USE_YN"));
        model.addAttribute("codeMemSearchCdList", codeService.selectGroupIdAllList("USER_SEARCH_CODE"));
        model.addAttribute("roles", roleService.selectAllList());
        RoleModel role =  new RoleModel();
        model.addAttribute("mgrRoles", roleService.selectMgrSysAuthAllList(role));

        MemberModel memberModel = new MemberModel();
        memberModel.setUserId(memberId);
        model.addAttribute("member", memberService.selectMember(memberModel));
        model.addAttribute("pages", criteria);

        return "member/memberRegist";
    }

    @PostMapping("/member/detail/{memberId}")
    @ResponseBody
    public MemberModel select(@PathVariable String memberId) {

        MemberModel memberModel = new MemberModel();
        memberModel.setUserId(memberId);

        return memberService.selectMember(memberModel);
    }

    /**
     * 회원 저장 및 수정 저장
     * @param startDate
     * @param endDate
     * @param newYn
     * @param memberModel
     * @param authUser
     * @return
     */
    @PostMapping("/member/insert")
    public ResponseEntity<String> insert(@RequestParam String startDate, @RequestParam String endDate,@RequestParam String newYn,
                                       @ModelAttribute MemberModel memberModel,
                                       @AuthenticationPrincipal AuthUser authUser) {

        try {
            if(!StringUtils.isEmpty(startDate)) {
                memberModel.setStartDt(LocalDateTime.parse(startDate + "T" + LocalTime.now().toString()));
            }
            if(!StringUtils.isEmpty(endDate)) {
                memberModel.setEndDt(LocalDateTime.parse(endDate+"T"+LocalTime.now().toString()));
            }

            memberModel.setRgstId(authUser.getMemberModel().getUserId());
            memberModel.setModiId(authUser.getMemberModel().getUserId());

            String result = "";
            if("Y".equals(newYn)){
                result = memberService.insert(memberModel);
            }else{
                result = memberService.save(memberModel);
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/member/popup")
    public String popupList(@ModelAttribute MemberCriteria criteria, Model model) {

        // 화면 표시용 코드 셋팅
        // 회사구분 코드
        model.addAttribute("codeCompanyCdList", codeService.selectGroupIdAllList("COMPANY_CODE"));
        // 사용여부 코드
        model.addAttribute("codeUseYnList", codeService.selectGroupIdAllList("USE_YN"));
        // 검색구분 코드
        model.addAttribute("codeMemSearchCdList", codeService.selectGroupIdAllList("USER_SEARCH_CODE"));

        // 권한코드 조회
        model.addAttribute("roles", roleService.selectAllList());
        // 부서 조회
        model.addAttribute("depts", deptService.selectDeptClList());

        model.addAttribute("members", memberService.selectMemberList(criteria));
        criteria.setTotalCount(memberService.selectMemberListCount(criteria));
        model.addAttribute("pages", criteria);

        return "popup/memberPopup";
    }

    @PostMapping("/member/popup")
    public String popupList(@ModelAttribute MemberCriteria criteria, RedirectAttributes attributes) {
        attributes.addFlashAttribute("criteria", criteria);
        return "redirect:/system/member/popup";
    }

    /**
     * 사용자 정보 조회(ajax)
     * @param criteria
     * @return
     */
    @RequestMapping(value="/member/list/popup", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> memberSelect(@ModelAttribute MemberCriteria criteria) {
    	Map<String,Object> result = new HashMap<>();
		boolean res = false;

		List<MemberModel> members = memberService.selectMemberAllList(criteria);

		if(members != null) {
			res = true;
			result.put("members", members);
		}
		result.put("result", res);

        return result;
    }

    //계정 잠김 해제
    @PostMapping("/member/update/unlock/{memberId}/ajax")
    @ResponseBody
    public long unlockAccount(@PathVariable String memberId) {

        MemberModel memberModel = new MemberModel();
        memberModel.setUserId(memberId);

        return memberService.unlockAccount(memberModel);
    }
    
    @PostMapping("/member/codeList/ajax")
    public ResponseEntity codeList(@RequestParam String companyCode, @RequestParam String codeId   ) {
    	try {
    		 Map<String,Object> result = new HashMap<>();
    		 List<CodeModel> lst=null;
    		 if ("dept".equals(codeId)) {  //부서
    			 lst=codeService.deptSelect(companyCode);
    		 }else if ("pstn".equals(codeId)) { //직위
    			 lst=codeService.pstnSelect(companyCode);
    		 }else if ("rank".equals(codeId)) { //직책
    			 lst=codeService.rankSelect(companyCode);
    		 }else if ("duty".equals(codeId)) { //직급
    			 lst=codeService.dutySelect(companyCode);	 
    		 }
    		 result.put("codeList", lst);
    	     return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
    
}