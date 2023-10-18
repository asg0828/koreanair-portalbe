package com.bdp.ap.app.system;

import javax.annotation.Resource;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bdp.ap.app.system.service.ProjectService;
import com.bdp.ap.config.security.AuthUser;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.app.system.model.ProjectModel;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/system")
@Controller
@Slf4j
public class ProjectController {
	
	@Resource
	ProjectService projectService;
	
	@Resource
	CodeService codeService;

    @GetMapping("/project")
    public String project(@ModelAttribute ProjectModel projectModel, Model model, @AuthenticationPrincipal AuthUser authUser) {

    	projectModel.setUserId(authUser.getMemberModel().getUserId());
    	
    	model.addAttribute("projectList", projectService.selectProjectList(projectModel));
    	projectModel.setTotalCount(projectService.selectProjectListCount(projectModel));
    	model.addAttribute("pages", projectModel);

        model.addAttribute("codePrjTypeList", codeService.selectGroupIdAllList("PRJ_TYPE_CODE"));
    	//projectService.selectAllProjectCodeList(model);
        return "system/projectList";
    }

    @GetMapping("/project/detail/{projectId}")
    public String selectDetail(@PathVariable String projectId, Model model) {
		
        ProjectModel projectModel = new ProjectModel();
        projectModel.setProjectId(projectId);
        model.addAttribute("projectDetail", projectService.selectProject(projectModel));
        //projectService.selectAllProjectCodeList(model);

       	return "system/projectDetail";
    }

}
    