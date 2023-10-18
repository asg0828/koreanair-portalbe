package com.bdp.ap.schedule.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bdp.ap.app.file.model.FileModel;
//import com.bdp.ap.app.license.model.LicenseModel;
import com.bdp.ap.app.member.model.MemberModel;
import com.bdp.ap.app.system.model.ProjectModel;
import com.bdp.ap.app.system.model.ReportModel;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.common.aws.AwsS3Util;
import com.bdp.ap.common.tableauapi.model.ProjectListType;
import com.bdp.ap.common.tableauapi.model.ProjectType;
import com.bdp.ap.common.tableauapi.model.TableauCredentialsType;
import com.bdp.ap.common.tableauapi.model.UserListType;
import com.bdp.ap.common.tableauapi.model.UserType;
import com.bdp.ap.common.tableauapi.model.ViewListType;
import com.bdp.ap.common.tableauapi.model.ViewType;
import com.bdp.ap.common.tableauapi.model.WorkbookListType;
import com.bdp.ap.common.tableauapi.model.WorkbookType;
import com.bdp.ap.common.tableauapi.util.TableauApiUtils;
import com.bdp.ap.config.props.TableauProps;
import com.bdp.ap.schedule.mapper.JobTableauMapper;
import com.bdp.ap.schedule.model.WorkbookModel;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobTableauService {
	
	@Resource(name="jobTableauMapper")
	private JobTableauMapper mapper;
	
	@Resource(name="tableauApiUtils")
    private TableauApiUtils tableauApiUtils;
	
	@Resource(name="tableauProps")
	private TableauProps tableauProps;
	
	@Resource(name="idUtil")
	private IdUtil idUtil;
	
	@Resource(name="awsS3Util")
	private AwsS3Util s3Util;
	
	private TableauCredentialsType getCredential() {
		return tableauApiUtils.simpleSignIn();
	}
	
	/**
	 * 태블로 프로젝트
	 */
	@Transactional
	public void syncProject(JSONObject msg) {
		JSONObject result = new JSONObject();
		TableauCredentialsType credential = null;
		ProjectListType projects = null;
		try {
			//tableau sign in
			credential = getCredential();
			//tableau search all project
			projects = tableauApiUtils.invokeQueryProjectsAll(credential, credential.getSite().getId());
			
			if (projects != null && projects.getProject().size() > 0) {
					
				// 인터페이스 기존 데이터 삭제
				mapper.deleteIfProject();
				
				List<List<ProjectType>> partitionList = Lists.partition(projects.getProject(), 100);
				for (List<ProjectType> list : partitionList) {
					Map<String,List<ProjectType>> map = new HashMap<String,List<ProjectType>>();
					map.put("project", list);
					mapper.insertIfProject(map);
				}
				
				mapper.syncDeleteProject();
				mapper.syncUpdateProject();
				mapper.syncInsertProject();
				
				// 포탈 프로젝트 삭제
				mapper.deleteProject();
				mapper.deleteProjectHist();
				
				// 포탈 프로젝트 수정
				List<String> modiProject = mapper.selectModiProject();
				log.debug(modiProject.toString());
				if (modiProject != null && modiProject.size() > 0) {
					mapper.updateModiProject();
					for (String projectId : modiProject) {
						mapper.insertProjectHist(projectId);
					}
				}
				// 포탈 프로젝트 생성
				List<ProjectModel> newProject = mapper.selectNewProject();
				if (newProject != null && newProject.size() > 0) {
					for (ProjectModel model : newProject) {
						model.setProjectId(idUtil.getProjectId());
						mapper.insertNewProject(model);
						mapper.insertProjectHist(model.getProjectId());
					}
				}
				
				mapper.updateModiSeProject();
				
			}
			result.put("size", projects.getProject().size());
			result.put("result", Constant.YES);
		} catch (Exception e) {
			log.warn("TABLEAU PROJECT 연동 중 오류 발생");
			log.warn(e.getMessage());
			result.put("result", Constant.NO);
		}
		msg.put("project", result);
	}
	
	/**
	 * 태블로 워크북
	 */
	@Transactional
	public void syncWorkbook(JSONObject msg) {
		JSONObject result = new JSONObject();
		TableauCredentialsType credential = null;
		WorkbookListType workbooks = null;
		try {
			//tableau sign in
			credential = getCredential();
			//tableau search all workbook
			workbooks = tableauApiUtils.invokeQuerySitesWorkbooksAll(credential, credential.getSite().getId());
			
			if (workbooks != null && workbooks.getWorkbook().size() > 0) {
					
				// 인터페이스 기존 데이터 삭제
				mapper.deleteIfWorkbook();
				
				List<List<WorkbookType>> partitionList = Lists.partition(workbooks.getWorkbook(), 100);
				for (List<WorkbookType> list : partitionList) {
					Map<String,List<WorkbookType>> map = new HashMap<String,List<WorkbookType>>();
					map.put("workbook", list);
					mapper.insertIfWorkbook(map);
				}
				
				mapper.syncDeleteWorkbook();
				mapper.syncUpdateWorkbook();
				mapper.syncInsertWorkbook();
				
				// 포털 보고서 삭제
				mapper.deleteReport();
				mapper.deleteReportHist();
				
				// 포탈 보고서 수정
				List<String> modiReport = mapper.selectModiReport();
				if (modiReport != null && modiReport.size() > 0) {
					mapper.updateModiReport();
					for (String reportId : modiReport) {
						mapper.insertReportHist(reportId);
					}
				}
				// 포탈 보고서 생성
				List<ReportModel> newReport = mapper.selectNewReport();
				if (newReport != null && newReport.size() > 0) {
					for (ReportModel model : newReport) {
						model.setReportId(idUtil.getReportId());
						mapper.insertNewReport(model);
						mapper.insertReportHist(model.getReportId());
					}
				}
				
				mapper.updateModiSeWorkbook();
				
			}
			result.put("size", workbooks.getWorkbook().size());
			result.put("result", Constant.YES);
		} catch (Exception e) {
			log.warn("TABLEAU WORKBOOK 연동 중 오류 발생");
			log.warn(e.getMessage());
			result.put("result", Constant.NO);
		}
		msg.put("workbook", result);
	}
	
	/**
	 * 태블로 뷰
	 */
	@Transactional
	public void syncView(JSONObject msg) {
		JSONObject result = new JSONObject();
		TableauCredentialsType credential = null;
		ViewListType views = null;
		int viewCount = 0;
		try {
			List<WorkbookModel> workbooks = mapper.selectWorkbook();
			//tableau sign in
			credential = getCredential();
			for (WorkbookModel workbook : workbooks) {
				//tableau search views with workbookId
				views = tableauApiUtils.invokeQueryviews(credential, credential.getSite().getId(), workbook.getTableauWorkbookId());
				if (views != null && views.getView().size() > 0) {
					
					//url 정제
					for (ViewType view : views.getView()) {
						view.setContentUrl(view.getContentUrl().replaceAll("/sheets/", "/"));
						if (view.getWorkbook() == null) {
							WorkbookType w = new WorkbookType();
							view.setWorkbook(w);
						}
						view.getWorkbook().setId(workbook.getTableauWorkbookId());
					}
					
					//워크북 기본 뷰 업데이트
					ViewType defaultView = views.getView().get(0);
					workbook.setTableauWorkbookUrl(defaultView.getContentUrl());
					workbook.setTableauViewId(defaultView.getId());
					//T_TABLEAU_WORKBOOK.TABLEAU_WORKBOOK_URL
					mapper.updateWorkbookDefaultView(workbook);
					//T_REPORT.REPORT_URL
					mapper.updateReportDefaultView(workbook);
					
					viewCount += views.getView().size();
					// 인터페이스 기존 데이터 삭제
					mapper.deleteIfView();
					
					Map<String,List<ViewType>> map = new HashMap<String,List<ViewType>>();
					map.put("view", views.getView());
					mapper.insertIfView(map);
					
					mapper.syncDeleteView(workbook.getTableauWorkbookId());
					mapper.syncUpdateView();
					mapper.syncInsertView();
				}
			}
			mapper.updateModiSeView();
			result.put("size", viewCount);
			result.put("result", Constant.YES);
		} catch (Exception e) {
			log.warn("TABLEAU VIEW 연동 중 오류 발생");
			log.warn(e.getMessage());
			result.put("result", Constant.NO);
			e.printStackTrace();
		}
		msg.put("view", result);
	}
	
	/**
	 * 태블로 계정
	 */
	@Transactional
	public void syncUser(JSONObject msg) {
		JSONObject result = new JSONObject();
		TableauCredentialsType credential = null;
		UserListType users = null;
		int pageSize = 100;
		int pageNo = 1;
		int count = 0;
		boolean loop = true;
		
		try {
			
			// 인터페이스 기존 데이터 삭제
			mapper.deleteIfUser();
			
			//tableau sign in
			credential = getCredential();
			
			while(loop) {
				//tableau search all user
				users = tableauApiUtils.invokeQueryUsersPaging(credential, credential.getSite().getId(), pageSize, pageNo);
				
				if (users != null && users.getUser().size() > 0) {
					count += users.getUser().size();
					pageNo++;
					if (pageSize != users.getUser().size()) {
						loop = false;
					}
					List<List<UserType>> partitionList = Lists.partition(users.getUser(), 100);
					for (List<UserType> list : partitionList) {
						Map<String,List<UserType>> map = new HashMap<String,List<UserType>>();
						map.put("user", list);
						mapper.insertIfUser(map);
					}
				} else {
					loop = false;
				}
			}
			
			mapper.syncDeleteUser();
			mapper.syncUpdateUser();
			mapper.syncInsertUser();
			mapper.updateModiSeUser();
			
			result.put("size", count);
			result.put("result", Constant.YES);
		} catch (Exception e) {
			log.warn("TABLEAU USER 연동 중 오류 발생");
			log.warn(e.getMessage());
			result.put("result", Constant.NO);
		}
		msg.put("user", result);
	}
	

	
	/**
	 * HR 사용자별 태블로 계정 생성
	 */
	public void createUser(JSONObject msg) {
		JSONObject result = new JSONObject();
		TableauCredentialsType credential = null;
		UserListType users = null;
		UserType userType = null;
		int count = 0;
		try {
			/*
			 * 사용자 목록 조회 : T_LICENSE 등록 기준 조회
			 * userId 를 태블로 userName으로 조회
			 *     없으면 생성 후 T_LICENSE INSERT
			 */
			List<MemberModel> list = mapper.selectUserList();
			//tableau sign in
			if (list.size() > 0) {
				credential = getCredential();
				for (MemberModel user : list) {
					users = tableauApiUtils.invokeQueryUsersWithUserName(credential, credential.getSite().getId(), user.getUserId());
					// 조회된 사용자가 없으면 생성
					if (users.getUser() == null || users.getUser().size() == 0) {
						// 태블로 계정 생성
						userType = tableauApiUtils.invokeAddUser(credential, credential.getSite().getId(), user.getUserId(), user.getUserNm(), "8");
						if (userType != null) {
							//태블로 계정 DisplayName 업데이트 : tableau user id 반환 없음 주의
							UserType ut = tableauApiUtils.invokeUpdateUser(credential, credential.getSite().getId(), userType.getId(), user.getUserNm(), null, null);
							if (ut != null) {
//								LicenseModel model = new LicenseModel();
//								model.setLicenseId(idUtil.getLicenseId());
//								model.setUserId(user.getUserId());
//								model.setTableauId(userType.getId());
//								model.setLicenseDsc("TABLEAU 자동 생성 라이선스");
//								model.setLicenseTy("USER");
//								model.setLicenseSe("TABLEAU");
//								mapper.insertLicense(model);
								count++;
							}
						}
					} else {
						//조회된 사용자가 있으면 라이선스 등록
						for (UserType ut : users.getUser()) {
							//태블로 계정 DisplayName 업데이트 : tableau user id 반환 없음 주의
							userType = tableauApiUtils.invokeUpdateUser(credential, credential.getSite().getId(), ut.getId(), user.getUserNm(), null, null);
							if (userType != null) {
//								LicenseModel model = new LicenseModel();
//								model.setLicenseId(idUtil.getLicenseId());
//								model.setUserId(user.getUserId());
//								model.setTableauId(ut.getId());
//								model.setLicenseDsc("TABLEAU 자동 생성 라이선스");
//								model.setLicenseTy("USER");
//								model.setLicenseSe("TABLEAU");
//								mapper.insertLicense(model);
								count++;
							}
						}
					}
				}
			}
			result.put("createCount", count);
			result.put("result", Constant.YES);
		} catch (Exception e) {
			log.warn("TABLEAU USER 생성 중 오류 발생");
			log.warn(e.getMessage());
			result.put("result", Constant.NO);
		}
		msg.put("createUser", result);
	}
	
	
	/**
	 * 태블로 워크북 미리보기 이미지
	 * @param msg
	 */
	public void syncPreview(JSONObject msg) {
		JSONObject result = new JSONObject();
		TableauCredentialsType credential = null;
		try {
			List<WorkbookModel> workbooks = mapper.selectWorkbook();
			//tableau sign in
			credential = getCredential();
			for (WorkbookModel workbook : workbooks) {
				try {
					FileModel file = null;
					FileModel newFile = new FileModel();
					String fileNm = StringUtils.joinWith(".", workbook.getTableauWorkbookId(),"png");
					int length = tableauApiUtils.invokeDownloadWorkbookPreViewsImage(credential, credential.getSite().getId(), workbook.getTableauWorkbookId(), newFile);
					//length = -1 인데 실제 파일은 있음
					if (newFile.getInputStream() != null) {
						if (StringUtils.isBlank(workbook.getFileId())) {
							// 프리뷰 파일 신규 등록
							file = newFile;
							file.setRefId(workbook.getTableauWorkbookId());
							file.setFileCl(Constant.File.PREVIEW);
							file.setFileId(idUtil.getFileId());
							file.setFileUrl(workbook.getTableauWorkbookId());
							file.setFileNm(fileNm);
							file.setFileExtsn("png");
							file.setSaveFileNm(fileNm);
							file.setFileSize(0);
						} else {
							file = mapper.selectFile(workbook.getFileId());
							file.setInputStream(newFile.getInputStream());
						}
						if (length != file.getFileSize()) {
							file.setFileSize(length);
							if (s3Util.upload(file)) {
								if (StringUtils.isBlank(workbook.getFileId())) {
									mapper.insertFile(file);
								} else {
									mapper.updateFile(file);
								}
								mapper.updateWorkbookPreview(file);
								mapper.updateReportPreview(file);
							}
						}
					}
				} catch (Exception e) {
					log.warn("WORKBOOK PREVIEW 연동 오류 - WORKBOOK ID : {}",workbook.getTableauWorkbookId());
					log.warn(e.getMessage());
				}
			}
			result.put("result", Constant.YES);
		} catch (Exception e) {
			log.warn("TABLEAU WORKBOOK PREVIEW 연동 중 오류 발생");
			log.warn(e.getMessage());
			result.put("result", Constant.NO);
		}
		msg.put("preview", result);
	}
	
	/**
	 * 태블로 워크북 이미지
	 * @param msg
	 */
	public void syncViewImage(JSONObject msg) {
		JSONObject result = new JSONObject();
		TableauCredentialsType credential = null;
		try {
			List<WorkbookModel> workbooks = mapper.selectWorkbookViewFile();
			//tableau sign in
			credential = getCredential();
			for (WorkbookModel workbook : workbooks) {
				try {
					FileModel file = null;
					FileModel newFile = new FileModel();
					String fileNm = StringUtils.joinWith(".", workbook.getTableauWorkbookId(),"png");
					int length = tableauApiUtils.invokeDownloadViewsImage(credential, credential.getSite().getId(), workbook.getTableauViewId(), newFile);
					//length = -1 인데 실제 파일은 있음
					if (newFile.getInputStream() != null) {
						if (StringUtils.isBlank(workbook.getFileId())) {
							// 프리뷰 파일 신규 등록
							file = newFile;
							file.setRefId(workbook.getTableauWorkbookId());
							file.setFileCl(Constant.File.VIEW);
							file.setFileId(idUtil.getFileId());
							file.setFileUrl(workbook.getTableauWorkbookId());
							file.setFileNm(fileNm);
							file.setFileExtsn("png");
							file.setSaveFileNm(fileNm);
							file.setFileSize(0);
							if (s3Util.upload(file)) {
								mapper.insertFile(file);
							}
						} else {
							file = mapper.selectFile(workbook.getFileId());
							file.setInputStream(newFile.getInputStream());
							if (length != file.getFileSize()) {
								file.setFileSize(length);
								if (s3Util.upload(file)) {
									mapper.updateFile(file);
								}
							}
						}
						
					}
				} catch (Exception e) {
					log.warn("WORKBOOK VIEW IMAGE 연동 오류 - WORKBOOK ID : {}",workbook.getTableauWorkbookId());
					log.warn(e.getMessage());
				}
			}
			result.put("result", Constant.YES);
		} catch (Exception e) {
			log.warn("TABLEAU WORKBOOK VIEW IMAGE 연동 중 오류 발생");
			log.warn(e.getMessage());
			result.put("result", Constant.NO);
		}
		msg.put("viewImage", result);
	}
}
