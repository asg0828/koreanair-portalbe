package com.bdp.ap.app.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bdp.ap.app.system.mapper.ProjectMapper;
import com.bdp.ap.app.system.model.ProjectModel;
    
@Service
public class ProjectService {

    @Resource
	private ProjectMapper projectMapper;

    public List<ProjectModel> selectProjectList(ProjectModel projectModel) {
		return projectMapper.selectProjectList(projectModel);
	}
	
	public int selectProjectListCount(ProjectModel projectModel) {
		return projectMapper.selectProjectListCount(projectModel);
	}

    public ProjectModel selectProject(ProjectModel model) {
		return projectMapper.selectProject(model);
	}

}