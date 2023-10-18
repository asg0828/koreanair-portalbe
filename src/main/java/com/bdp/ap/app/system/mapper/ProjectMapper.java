package com.bdp.ap.app.system.mapper;

import java.util.List;

import com.bdp.ap.app.system.model.ProjectModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface ProjectMapper {

    List<ProjectModel> selectProjectList(ProjectModel model);
    int selectProjectListCount(ProjectModel model);
    ProjectModel selectProject(ProjectModel model);
}