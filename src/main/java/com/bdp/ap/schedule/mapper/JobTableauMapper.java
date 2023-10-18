package com.bdp.ap.schedule.mapper;

import java.util.List;
import java.util.Map;

import com.bdp.ap.app.file.model.FileModel;
//import com.bdp.ap.app.license.model.LicenseModel;
import com.bdp.ap.app.member.model.MemberModel;
import com.bdp.ap.app.system.model.ProjectModel;
import com.bdp.ap.app.system.model.ReportModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.tableauapi.model.ProjectType;
import com.bdp.ap.common.tableauapi.model.UserType;
import com.bdp.ap.common.tableauapi.model.ViewType;
import com.bdp.ap.common.tableauapi.model.WorkbookType;
import com.bdp.ap.schedule.model.WorkbookModel;

@ConnMapperFirst
public interface JobTableauMapper {

	// TABLEAU PROJECT
	int deleteIfProject() throws Exception;
	int insertIfProject(Map<String, List<ProjectType>> map) throws Exception;
	int syncDeleteProject() throws Exception;
	int syncUpdateProject() throws Exception;
	int syncInsertProject() throws Exception;
	int updateModiSeProject() throws Exception;

	//포털 프로젝트
	int deleteProject() throws Exception;
	int deleteProjectHist() throws Exception;
	List<String> selectModiProject() throws Exception;
	int updateModiProject() throws Exception;
	int insertProjectHist(String projectId) throws Exception;
	List<ProjectModel> selectNewProject() throws Exception;
	int insertNewProject(ProjectModel model) throws Exception;
	
	
	
	// TABLEAU WORKBOOK
	int deleteIfWorkbook() throws Exception;
	int insertIfWorkbook(Map<String, List<WorkbookType>> map) throws Exception;
	int syncDeleteWorkbook() throws Exception;
	int syncUpdateWorkbook() throws Exception;
	int syncInsertWorkbook() throws Exception;
	int updateModiSeWorkbook() throws Exception;

	//포털 보고서
	int deleteReport() throws Exception;
	int deleteReportHist() throws Exception;
	List<String> selectModiReport() throws Exception;
	int updateModiReport() throws Exception;
	int insertReportHist(String reportId) throws Exception;
	List<ReportModel> selectNewReport() throws Exception;
	int insertNewReport(ReportModel model) throws Exception;
	
	
	
	// TABLEAU VIEW
	List<WorkbookModel> selectWorkbook() throws Exception;
	int updateWorkbookDefaultView(WorkbookModel model) throws Exception;
	int updateReportDefaultView(WorkbookModel model) throws Exception;
	int deleteIfView() throws Exception;
	int insertIfView(Map<String, List<ViewType>> map) throws Exception;
	int syncDeleteView(String workbookId) throws Exception;
	int syncUpdateView() throws Exception;
	int syncInsertView() throws Exception;
	int updateModiSeView() throws Exception;

	
	
	// TABLEAU USER
	int deleteIfUser() throws Exception;
	int insertIfUser(Map<String, List<UserType>> map) throws Exception;
	int syncDeleteUser() throws Exception;
	int syncUpdateUser() throws Exception;
	int syncInsertUser() throws Exception;
	int updateModiSeUser() throws Exception;

	// TABLEAU CREATE USER
	List<MemberModel> selectUserList();
	//int insertLicense(LicenseModel model);

	
	
	// TABLEAU PREVIEW IMAGE
	int updateWorkbookPreview(FileModel model) throws Exception;
	int updateReportPreview(FileModel model) throws Exception;
	int insertFile(FileModel model) throws Exception;
	int updateFile(FileModel model) throws Exception;
	FileModel selectFile(String fileId) throws Exception;
	
	// TABLEAU VIEW IMAGE
	List<WorkbookModel> selectWorkbookViewFile() throws Exception;
}
