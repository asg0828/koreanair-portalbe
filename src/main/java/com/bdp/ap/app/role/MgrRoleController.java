package com.bdp.ap.app.role;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdp.ap.app.role.model.MgrRoleModel;
import com.bdp.ap.app.role.model.RoleModel;
import com.bdp.ap.app.role.service.MgrRoleService;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 사용자관리 / 권한관리 컨트롤러
 */
@RequestMapping("/system")
@Controller
@Slf4j
public class MgrRoleController {

    @Resource
    private MgrRoleService mgrRoleService;

    /**
     * 권한 관리 페이지로 이동한다.
     *
     * @param criteria 페이징 처리 관련 모델
     * @param model
     * @return
     */
    @GetMapping("/mgrRole")
    public String list(@ModelAttribute Criteria criteria, Model model) {

        model.addAttribute("roles", mgrRoleService.selectList(criteria));
        criteria.setTotalCount(mgrRoleService.selectListCount(criteria));
        model.addAttribute("pages", criteria);

        //jsp
        return "role/mgrRole";
    }

    /**
     * 권한관리 페이지로 이동한다. 페이징시에 파라미터 노출을 하지 않기 위해서 redirect하기 위한 기능
     * @param criteria      // 페이징 모델
     * @param attributes    // 권한 페이지로 모델을 전달하기 위한 속성
     * @return
     */
    @PostMapping("/mgrRole")
    public String list(@ModelAttribute Criteria criteria, RedirectAttributes attributes) {

        attributes.addFlashAttribute("criteria", criteria);

        //controller
        return "redirect:/system/mgrRole";
    }

    /**
     * 권한 모델을 저장한다. 권한이 존재하면 업데이트 없으면 신규생성한다.
     *
     * @param roleModel
     * @return      Insert/Update/Fail을 페이지로 리턴하며 alert메시지 처리
     */
    @PostMapping("/mgrRole/insert")
    public ResponseEntity<String> insert(@ModelAttribute MgrRoleModel mgrRoleModel, @AuthenticationPrincipal AuthUser authUser) {

        try {
        	mgrRoleModel.setRgstId(authUser.getMemberModel().getUserId());
        	mgrRoleModel.setModiId(authUser.getMemberModel().getUserId());

            log.debug(authUser.getMemberModel().toString());

            if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AuthUser) {
                AuthUser customAuthUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                log.debug(customAuthUser.getMemberModel().toString());
            }

            String result = mgrRoleService.save(mgrRoleModel);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * 권한 모델을 삭제한다.
     *
     * @param roleModel
     * @return      Delete/Fail을 페이지로 리턴하며 alert메시지 처리
     */
    @PostMapping("/mgrRole/delete")
    public ResponseEntity<String> delete(@ModelAttribute MgrRoleModel mgrRoleModel, @AuthenticationPrincipal AuthUser authUser) {

        try {
        	mgrRoleModel.setRgstId(authUser.getMemberModel().getUserId());
        	mgrRoleModel.setModiId(authUser.getMemberModel().getUserId());

            String result = mgrRoleService.delete(mgrRoleModel);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/mgrRole/detail/{roleId}")
    @ResponseBody
    public MgrRoleModel select(@PathVariable String roleId) {

    	MgrRoleModel mgrRoleModel = new MgrRoleModel();
    	mgrRoleModel.setAuthId(roleId);

        return mgrRoleService.select(mgrRoleModel);
    }
}
