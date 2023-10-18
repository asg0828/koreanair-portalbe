package com.bdp.ap.app.vgroup;

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

import com.bdp.ap.app.role.model.RoleModel;
import com.bdp.ap.app.vgroup.model.VgroupModel;
import com.bdp.ap.app.vgroup.service.VgroupService;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 사용자관리 / 가상그룹관리 컨트롤러
 */
@RequestMapping("/system")
@Controller
@Slf4j
public class VgroupController {

    @Resource
    private VgroupService vgroupService;

    /**
     * 가상그룹 관리 페이지로 이동한다.
     *
     * @param criteria 페이징 처리 관련 모델
     * @param model
     * @return
     */
    @GetMapping("/vgroup")
    public String list(@ModelAttribute VgroupModel vgroupModel, Model model) {
    	vgroupModel.setDelYn("N");
    	model.addAttribute("vgroups", vgroupService.selectVgroupList(vgroupModel));
        vgroupModel.setTotalCount(vgroupService.selectVgroupListCount(vgroupModel));
        model.addAttribute("pages", vgroupModel);

        //jsp
        return "vgroup/vgroup";
    }

    /**
     * 권한관리 페이지로 이동한다. 페이징시에 파라미터 노출을 하지 않기 위해서 redirect하기 위한 기능
     * @param criteria      // 페이징 모델
     * @param attributes    // 권한 페이지로 모델을 전달하기 위한 속성
     * @return
     */
    @PostMapping("/vgroup")
    public String list(@ModelAttribute VgroupModel vgroupModel, RedirectAttributes attributes) {

        attributes.addFlashAttribute("vgroupModel", vgroupModel);

        //controller
        return "redirect:/system/role";
    }

    /**
     * 권한 모델을 저장한다. 권한이 존재하면 업데이트 없으면 신규생성한다.
     *
     * @param roleModel
     * @return      Insert/Update/Fail을 페이지로 리턴하며 alert메시지 처리
     */
    @PostMapping("/vgroup/insert")
    public ResponseEntity<String> insert(@ModelAttribute VgroupModel vgroupModel, @AuthenticationPrincipal AuthUser authUser) {

        try {
        	vgroupModel.setRgstId(authUser.getMemberModel().getUserId());
        	vgroupModel.setModiId(authUser.getMemberModel().getUserId());

            if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AuthUser) {
                AuthUser customAuthUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                log.debug(customAuthUser.getMemberModel().toString());
            }

            String result = vgroupService.vgroupSave(vgroupModel);

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
    @PostMapping("/vgroup/delete")
    public ResponseEntity<String> delete(@ModelAttribute VgroupModel vgroupModel, @AuthenticationPrincipal AuthUser authUser) {

        try {
        	vgroupModel.setRgstId(authUser.getMemberModel().getUserId());
        	vgroupModel.setModiId(authUser.getMemberModel().getUserId());

            String result = vgroupService.deleteVgroup(vgroupModel);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/vgroup/detail/{groupId}")
    @ResponseBody
    public VgroupModel select(@PathVariable String groupId) {

    	VgroupModel vgroupModel = new VgroupModel();
    	vgroupModel.setGroupId(groupId);

        return vgroupService.selectVgroup(vgroupModel);
    }
}
