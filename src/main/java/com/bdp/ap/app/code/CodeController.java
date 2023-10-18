package com.bdp.ap.app.code;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdp.ap.app.code.model.CodeModel;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.common.paging.Criteria;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 시스템관리 / 코드관리 컨트롤러
 */
@Slf4j
@RequestMapping("/system")
@Controller
public class CodeController {

    @Resource
    private CodeService codeService;
    
        /**
     * 코드관리 페이지로 이동
     *
     * @param model
     * @return
     */
    @GetMapping("/c")
    public String codePage(@ModelAttribute Criteria criteria, Model model) {
        List<CodeModel> models = codeService.selectGroupIdList("GROUP_ID", criteria);
        criteria.setTotalCount(codeService.selectGroupIdListCount("GROUP_ID"));
        model.addAttribute("codes", models);
        model.addAttribute("pages", criteria);

        if(models.size() > 0) {
            model.addAttribute("firstCodeId", models.get(0).getCodeId());
            model.addAttribute("firstCodeNm", models.get(0).getCodeNm());
            model.addAttribute("firstCodeDsc", models.get(0).getCodeDsc());
            model.addAttribute("firstCodeUseYn", models.get(0).getUseYn());
        }

        return "redirect:/system/code";
    }
    /**
     * 코드관리 페이지로 이동
     *
     * @param model
     * @return
     */
    @GetMapping("/code")
    public String code(@ModelAttribute Criteria criteria, Model model, String old) {
        List<CodeModel> models = codeService.selectGroupIdList("GROUP_ID", criteria);
        criteria.setTotalCount(codeService.selectGroupIdListCount("GROUP_ID"));
        model.addAttribute("codes", models);
        model.addAttribute("pages", criteria);

        if(!models.isEmpty()) {
            model.addAttribute("firstCodeId", models.get(0).getCodeId());
            model.addAttribute("firstCodeNm", models.get(0).getCodeNm());
            model.addAttribute("firstCodeDsc", models.get(0).getCodeDsc());
            model.addAttribute("firstCodeUseYn", models.get(0).getUseYn());
        }

        if (old != null && old.equals("y")) {
            return "code/code";
        } else {
            return "code/code";
        }
    }

    /**
     * 코드관리 페이지로 이동
     *
     * @param criteria
     * @return
     */
    @PostMapping("/code")
    public String code(HttpServletRequest request, String old, @ModelAttribute Criteria criteria, Model model) {
        List<CodeModel> models = codeService.selectGroupIdList("GROUP_ID", criteria);
        criteria.setTotalCount(codeService.selectGroupIdListCount("GROUP_ID"));
        model.addAttribute("codes", models);
        model.addAttribute("pages", criteria);
        // 그룹 코드 수정 시 자동 선택하기 위해 코드 값 추가 전달
        String groupCodeId = request.getParameter("groupCodeId");
        model.addAttribute("groupCodeId", groupCodeId);

        // 그룹 코드 수정 시 자동 선택하기 위해 코드 값 추가 전달
        String codeId = request.getParameter("codeId");
        model.addAttribute("codeId", codeId);

        if (old != null && old.equals("y")) {
            return "code/code";
        } else {
            return "code/code";
        }
    }

    /**
     * 코드그룹을 저장한다. 그룹ID와 코드ID가 동일한 데이터가 존재하면 업데이트 없으면 신규 등록한다.
     *
     * @param request
     * @return
     */
    @PostMapping("/code/insert")
    public ResponseEntity<String> groupSave(HttpServletRequest request, @AuthenticationPrincipal AuthUser authUser) {
    	try {
            CodeModel codeModel = new CodeModel();
            for (String key : request.getParameterMap().keySet()) {
            	//log.debug("===== request.Parameter" + key + " :" + request.getParameter(key));
            }
            String codeId = request.getParameter("groupCodeId");
            String codeNm = request.getParameter("groupCodeNm");
            String codeDesc = request.getParameter("groupCodeDsc");
            String codeUseYn = request.getParameter("groupCodeUseYn");

            codeModel.setCodeId(codeId);
            codeModel.setCodeNm(codeNm);
            codeModel.setCodeDsc(codeDesc);
            codeModel.setUseYn(codeUseYn);
            codeModel.setGroupId("GROUP_ID");

            codeModel.setRgstId(authUser.getMemberModel().getUserId());
            codeModel.setModiId(authUser.getMemberModel().getUserId());

            String result = codeService.save(codeModel);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
    /**
     * 코드그룹을 삭제한다.
     *
     * @param request
     * @return
     */
    @PostMapping("/code/delete")
    public ResponseEntity<String> groupDelete(HttpServletRequest request, @AuthenticationPrincipal AuthUser authUser) {
        try {
            CodeModel codeModel = new CodeModel();

            String codeId = request.getParameter("groupCodeId");
            String codeNm = request.getParameter("groupCodeNm");
            String codeDesc = request.getParameter("groupCodeDsc");

            codeModel.setCodeId(codeId);
            codeModel.setCodeNm(codeNm);
            codeModel.setCodeDsc(codeDesc);
            codeModel.setGroupId("GROUP_ID");

            codeModel.setRgstId(authUser.getMemberModel().getUserId());
            codeModel.setModiId(authUser.getMemberModel().getUserId());

            String result = codeService.delete(codeModel);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }    
    
    
    
    
    /**
     * 그룹코드 목록을 조회한다.
     *
     * @param groupCd
     * @return
     */
    @GetMapping("/code/detail/{groupCd}")
    public ResponseEntity<List<CodeModel>> codesForGroupCd(@PathVariable("groupCd") String groupId) {
        List<CodeModel> codeModels = codeService.selectGroupIdAllList(groupId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        codeModels.stream().forEach(code -> code.setRgstDtStr(formatter.format(code.getRgstDt())));

        return new ResponseEntity<>(codeModels, HttpStatus.OK);
    }

    /**
     * 코드를 저장한다. 그룹ID와 코드ID가 동일한 데이터가 존재하면 업데이트 없으면 신규 등록한다.
     *
     * @param codeModel
     * @return
     */
    @PostMapping("/code/insert/code")
    public ResponseEntity<String> save(@ModelAttribute CodeModel codeModel, @AuthenticationPrincipal AuthUser authUser) {
    	try {
            codeModel.setRgstId(authUser.getMemberModel().getUserId());
            codeModel.setModiId(authUser.getMemberModel().getUserId());

            String result = codeService.save(codeModel);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }



    /**
     * 코드를 삭제한다.
     *
     * @param codeModel
     * @return
     */
    @PostMapping("/code/delete/code")
    public ResponseEntity<String> delete(@ModelAttribute CodeModel codeModel, @AuthenticationPrincipal AuthUser authUser) {
        try {
            codeModel.setRgstId(authUser.getMemberModel().getUserId());
            codeModel.setModiId(authUser.getMemberModel().getUserId());

            String result = codeService.delete(codeModel);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }


}
