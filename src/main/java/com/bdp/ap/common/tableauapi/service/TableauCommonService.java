package com.bdp.ap.common.tableauapi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.tableauapi.model.GranteeCapabilitiesType;
import com.bdp.ap.common.tableauapi.model.GroupListType;
import com.bdp.ap.common.tableauapi.model.GroupType;
import com.bdp.ap.common.tableauapi.model.PermissionsType;
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Service("tableauCommonService")
public class TableauCommonService {
	
	@Resource(name="commonUtil")
	private CommonUtil commonUtil;	
	
    @Resource(name="tableauApiUtils")
    private TableauApiUtils apiUtils;
    
    //태블로 사용자 싱크
	public Map<String, Object> syncTableauUsers() throws Exception {
		Map<String, Object> result = new HashMap<>();
		String resultCode = "fail";
		
		try {
			//tableau sign in
			TableauCredentialsType credential = apiUtils.simpleSignIn();
			String siteId = credential.getSite().getId();
			
			//tableau search users
			UserListType users = apiUtils.invokeQueryUsersAll(credential, siteId);
			for(UserType user : users.getUser()) {
				log.info("user id : {}", 		user.getId());
				log.info("user name : {}", 		user.getName());
				log.info("user fullName : {}", 	user.getFullName());
				log.info("user siteRoll : {}", 	user.getSiteRole());
				
				//우리쪽 인터페이스 테이블에 저장하는 로직 넣어야 함
				//syncTableauUserToPortal()... 전상현
			}
			resultCode = "success";
		} catch (Exception e) {
			log.warn("{} 오류 발생", Thread.currentThread().getStackTrace()[1].getMethodName());
			log.warn(commonUtil.getExceptionTrace(e));
			result.put(Constant.ERROR_MESSAGE, commonUtil.getExceptionTrace(e));
		}
		
		result.put(Constant.RESULT_CODE, resultCode);
		return result;
	}
	
    //태블로 그룹 싱크
	public Map<String, Object> syncTableauGroups() throws Exception {
		Map<String, Object> result = new HashMap<>();
		String resultCode = "fail";
		
		try {
			//tableau sign in
			TableauCredentialsType credential = apiUtils.simpleSignIn();
			String siteId = credential.getSite().getId();
			
			//tableau search groups
			GroupListType groups = apiUtils.invokeQueryGroupsAll(credential, siteId);
			for(GroupType group : groups.getGroup()) {
				log.info("group id : {}", 		group.getId());
				log.info("group name : {}", 	group.getName());
				
				//우리쪽 인터페이스 테이블에 저장하는 로직 넣어야 함
				//syncTableauGroupToPortal()... 전상현
			}
			resultCode = "success";
		} catch (Exception e) {
			log.warn("{} 오류 발생", Thread.currentThread().getStackTrace()[1].getMethodName());
			log.warn(commonUtil.getExceptionTrace(e));
			result.put(Constant.ERROR_MESSAGE, commonUtil.getExceptionTrace(e));
		}
		
		result.put(Constant.RESULT_CODE, resultCode);
		return result;
	}
	
    //태블로 프로젝트 싱크
	public Map<String, Object> syncTableauProjects() throws Exception {
		Map<String, Object> result = new HashMap<>();
		String resultCode = "fail";
		
		try {
			//tableau sign in
			TableauCredentialsType credential = apiUtils.simpleSignIn();
			String siteId = credential.getSite().getId();
			//tableau search groups
			ProjectListType projects = apiUtils.invokeQueryProjectsAll(credential, siteId);
			for(ProjectType project : projects.getProject()) {
				log.info("project id : {}", 				project.getId());
				log.info("project parentProjectId : {}", 	project.getParentProjectId());
				log.info("project name : {}", 				project.getName());
				log.info("project description : {}", 		project.getDescription());
				log.info("project contentPermissions : {}", project.getContentPermissions());
				
				log.info("project owner.id : {}", 			project.getOwner().getId());
				
				//우리쪽 인터페이스 테이블에 저장하는 로직 넣어야 함
				//syncTableauProjectToPortal()... 전상현
			}
			resultCode = "success";
		} catch (Exception e) {
			log.warn("{} 오류 발생", Thread.currentThread().getStackTrace()[1].getMethodName());
			log.warn(commonUtil.getExceptionTrace(e));
			result.put(Constant.ERROR_MESSAGE, commonUtil.getExceptionTrace(e));
		}
		
		result.put(Constant.RESULT_CODE, resultCode);
		return result;
	}
	
    //태블로 워크북 싱크
	public Map<String, Object> syncTableauWorkbooks() throws Exception {
		Map<String, Object> result = new HashMap<>();
		String resultCode = "fail";
		
		try {
			//tableau sign in
			TableauCredentialsType credential = apiUtils.simpleSignIn();
			String siteId = credential.getSite().getId();
			//tableau search groups
			WorkbookListType workbooks = apiUtils.invokeQuerySitesWorkbooksAll(credential, siteId);
			for(WorkbookType workbook : workbooks.getWorkbook()) {
				log.info("workbook getId : {}", 			workbook.getId());
				log.info("workbook getName : {}", 			workbook.getName());
				log.info("workbook getDescription : {}", 	workbook.getDescription());
				log.info("workbook getWebpageUrl : {}", 	workbook.getWebpageUrl());
				log.info("workbook getContentUrl : {}", 	workbook.getContentUrl());
				log.info("workbook getCreatedAt : {}", 		workbook.getCreatedAt().toString());
				log.info("workbook getUpdatedAt : {}", 		workbook.getUpdatedAt().toString());
				log.info("workbook getDefaultViewId : {}", 	workbook.getDefaultViewId());
				
				log.info("\tworkbook getProject.getId : {}", 	workbook.getProject().getId());
				log.info("\tworkbook getProject.getName : {}", 	workbook.getProject().getName());
				
				log.info("\tworkbook getOwner.getId : {}", 		workbook.getOwner().getId());
				
				//우리쪽 인터페이스 테이블에 저장하는 로직 넣어야 함
				//syncTableauWorkbookToPortal()... 전상현
			}
			resultCode = "success";
		} catch (Exception e) {
			log.warn("{} 오류 발생", Thread.currentThread().getStackTrace()[1].getMethodName());
			log.warn(commonUtil.getExceptionTrace(e));
			result.put(Constant.ERROR_MESSAGE, commonUtil.getExceptionTrace(e));
		}
		
		result.put(Constant.RESULT_CODE, resultCode);
		return result;
	}	

	//태블로 워크북별 뷰
	public Map<String, Object> syncTableauWorkbookView() throws Exception {
		Map<String, Object> result = new HashMap<>();
		String resultCode = "fail";
		
		try {
			//tableau sign in
			TableauCredentialsType credential = apiUtils.simpleSignIn();
			String siteId = credential.getSite().getId();
			//tableau search groups
			//String workbookId = "509d71c9-122a-4858-a392-0636123bb67f";	//01.전사종합수익현황
			//String workbookId = "42660280-cb1f-4b7c-b4b0-86bab09ad9d5";	//03.경영Weekly-수익영업 현황
			//String workbookId = "93890ce1-9b0b-4add-b2f6-53e937c33e33";		//05.WM그룹_DailyFocus
			String workbookId = "e193e365-8a9e-4492-94b9-9f635321d15b";		//05.WM그룹_DailyFocus(부서장)
			
			String path = "C:/downimage";
			
			ViewListType views = apiUtils.invokeQueryviews(credential, siteId, workbookId);
			int i = 0;
			for(ViewType view : views.getView()) {
				String viewId = view.getId();
				if(i == 0) {
					apiUtils.invokeDownloadPreViewsImage(credential, siteId, workbookId, viewId, workbookId, path);
				}
				i++;
			}
			
			
			resultCode = "success";
		} catch (Exception e) {
			log.warn("{} 오류 발생", Thread.currentThread().getStackTrace()[1].getMethodName());
			log.warn(commonUtil.getExceptionTrace(e));
			result.put(Constant.ERROR_MESSAGE, commonUtil.getExceptionTrace(e));
		}
		
		result.put(Constant.RESULT_CODE, resultCode);
		return result;
	}	

    //태블로 사용자 생성
	public Map<String, Object> addTableauUser(String userName, String siteRole) throws Exception {
		Map<String, Object> result = new HashMap<>();
		String resultCode = "fail";
		
		try {
			//tableau sign in
			TableauCredentialsType credential = apiUtils.simpleSignIn();
			String siteId = credential.getSite().getId();
			
			//기존 사용자 존재여부 체크후 없을때만 add 
			//※단건만 추출하는 API는 userName이 아니라 '77df3c2a-a759-437d-ae05-2b039d0c784c' 형식의 userId가 필요함 => 나중에 배치에서 땡겨올 태블로 사용자 테이블에서 조회해서 체크하는 방법도 있으나, 실시간으로 체크해야하므로, 아래 방법을 사용함
			UserListType users = apiUtils.invokeQueryUsersWithUserName(credential, siteId, userName);
			if (users.getUser().isEmpty()==true && users.getUser().size()==0) {
				UserType addedUser = apiUtils.invokeAddUser(credential, siteId, userName, siteRole);
				log.info("#addedUser.getId()" + addedUser.getId());
				resultCode = "success";
			} else {
				log.info("#existUser getName : {}", 	users.getUser().get(0).getName());
				log.info("#getSiteRole : {}", 			users.getUser().get(0).getSiteRole());				
				resultCode = "duplicate";
			}
		} catch (Exception e) {
			log.warn("{} 오류 발생", Thread.currentThread().getStackTrace()[1].getMethodName());
			log.warn(commonUtil.getExceptionTrace(e));
			result.put(Constant.ERROR_MESSAGE, commonUtil.getExceptionTrace(e));
		}
		
		result.put(Constant.RESULT_CODE, resultCode);
		return result;
	}
	
    //태블로 사용자 수정
	public Map<String, Object> updateTableauUser(String userName, String newFullName, String newPassword, String newSiteRole) throws Exception {
		Map<String, Object> result = new HashMap<>();
		String resultCode = "fail";
		
		try {
			//tableau sign in
			TableauCredentialsType credential = apiUtils.simpleSignIn();
			String siteId = credential.getSite().getId();
			
			//기존 사용자 존재여부 체크후 없을때만 add 
			//※단건만 추출하는 API는 userName이 아니라 '77df3c2a-a759-437d-ae05-2b039d0c784c' 형식의 userId가 필요함 => 나중에 배치에서 땡겨올 태블로 사용자 테이블에서 조회해서 체크하는 방법도 있으나, 실시간으로 체크해야하므로, 아래 방법을 사용함
			UserListType users = apiUtils.invokeQueryUsersWithUserName(credential, siteId, userName);
			if (users.getUser().isEmpty()==false && users.getUser().size()==1) {
				String userId = users.getUser().get(0).getId(); 
				UserType updatedUser = apiUtils.invokeUpdateUser(credential, siteId, userId, newFullName, newPassword, newSiteRole);
				if (updatedUser != null) {
					log.info("#updatedUser.getName() : {}", 		updatedUser.getName());
					log.info("#updatedUser.getFullName() : {}", 	updatedUser.getFullName());
					resultCode = "success";
				}
			} else if (users.getUser().size()>1) {
				resultCode = "toManyUsers";
			} else {
				resultCode = "noUser";
			}
		} catch (Exception e) {
			log.warn("{} 오류 발생", Thread.currentThread().getStackTrace()[1].getMethodName());
			log.warn(commonUtil.getExceptionTrace(e));
			result.put(Constant.ERROR_MESSAGE, commonUtil.getExceptionTrace(e));
		}
		
		result.put(Constant.RESULT_CODE, resultCode);
		return result;
	}
	
    //태블로 그룹 생성
	public Map<String, Object> addTableauGroup(String groupName) throws Exception {
		Map<String, Object> result = new HashMap<>();
		String resultCode = "fail";
		
		try {
			//tableau sign in
			TableauCredentialsType credential = apiUtils.simpleSignIn();
			String siteId = credential.getSite().getId();
			
			//기존 그룹 존재여부 체크후 없을때만 add 
			GroupListType groups = apiUtils.invokeQueryGroupsWithGroupName(credential, siteId, groupName);
			if (groups.getGroup().isEmpty()==true && groups.getGroup().size()==0) {
				GroupType addedGroup = apiUtils.invokeCreateGroup(credential, siteId, groupName, false);	//asJob은 Active디렉토리는 사용하지 않으면 false.(※디펄트 false)
				log.info("#addedGroup.getId() : {}", 	addedGroup.getId());
				resultCode = "success";
			} else {
				log.info("#existGroup getName : {}", 	groups.getGroup().get(0).getName());
				log.info("#existGroup getId : {}", 		groups.getGroup().get(0).getId());				
				resultCode = "duplicate";				
			}
		} catch (Exception e) {
			log.warn("{} 오류 발생", Thread.currentThread().getStackTrace()[1].getMethodName());
			log.warn(commonUtil.getExceptionTrace(e));
			result.put(Constant.ERROR_MESSAGE, commonUtil.getExceptionTrace(e));
		}
		
		result.put(Constant.RESULT_CODE, resultCode);
		return result;
	}	
	
    //태블로 그룹 수정
	public Map<String, Object> updateTableauGroup(String groupName, String newGroupName) throws Exception {
		Map<String, Object> result = new HashMap<>();
		String resultCode = "fail";
		
		try {
			//tableau sign in
			TableauCredentialsType credential = apiUtils.simpleSignIn();
			String siteId = credential.getSite().getId();
			
			//기존 그룹 존재여부 체크후 없을때만 add 
			GroupListType groups = apiUtils.invokeQueryGroupsWithGroupName(credential, siteId, groupName);
			if (groups.getGroup().isEmpty()==false && groups.getGroup().size()==1) {
				String groupId = groups.getGroup().get(0).getId();
				GroupType updatedGroup = apiUtils.invokeUpdateGroup(credential, siteId, groupId, newGroupName, false);	//asJob은 Active디렉토리는 사용하지 않으면 false.(※디펄트 false)
				log.info("#updatedGroup.getName() : {}", 	updatedGroup.getName());
				resultCode = "success";
			} else if (groups.getGroup().size()>1) {
				resultCode = "toManyGroups";
			} else {
				resultCode = "noGroup";
			}		
		} catch (Exception e) {
			log.warn("{} 오류 발생", Thread.currentThread().getStackTrace()[1].getMethodName());
			log.warn(commonUtil.getExceptionTrace(e));
			result.put(Constant.ERROR_MESSAGE, commonUtil.getExceptionTrace(e));
		}
		
		result.put(Constant.RESULT_CODE, resultCode);
		return result;
	}	
	
    //태블로 프로젝트 생성
	public Map<String, Object> addTableauProject(String parentProjectId, String projectName, String projectDescription) throws Exception {
		Map<String, Object> result = new HashMap<>();
		String resultCode = "fail";
		
		try {
			//tableau sign in
			TableauCredentialsType credential = apiUtils.simpleSignIn();
			String siteId = credential.getSite().getId();
			
			//기존 프로젝트 존재여부 체크후 없을때만 add 
			ProjectListType projects = apiUtils.invokeQueryProjectsWithProjectName(credential, siteId, projectName);
			if (projects.getProject().isEmpty()==true && projects.getProject().size()==0) {
				ProjectType addedProject = apiUtils.invokeCreateProject(credential, siteId, parentProjectId, projectName, projectDescription);
				log.info("#addedProject.getId() : {}", 	addedProject.getId());
				resultCode = "success";
			} else {
				log.info("#existProject getName : {}", 	projects.getProject().get(0).getName());
				log.info("#existProject getId : {}", 	projects.getProject().get(0).getId());				
				resultCode = "duplicate";				
			}			
		} catch (Exception e) {
			log.warn("{} 오류 발생", Thread.currentThread().getStackTrace()[1].getMethodName());
			log.warn(commonUtil.getExceptionTrace(e));
			result.put(Constant.ERROR_MESSAGE, commonUtil.getExceptionTrace(e));
		}
		
		result.put(Constant.RESULT_CODE, resultCode);
		return result;
	}		
	
    //태블로 프로젝트 수정
	public Map<String, Object> updateTableauProject(String projectName, String newParentProjectId, String newProjectName, String newProjectDescription) throws Exception {
		Map<String, Object> result = new HashMap<>();
		String resultCode = "fail";
		
		try {
			//tableau sign in
			TableauCredentialsType credential = apiUtils.simpleSignIn();
			String siteId = credential.getSite().getId();
			
			//기존 프로젝트 존재여부 체크후 없을때만 update 
			ProjectListType projects = apiUtils.invokeQueryProjectsWithProjectName(credential, siteId, projectName);
			if (projects.getProject().isEmpty()==false && projects.getProject().size()==1) {
				String projectId = projects.getProject().get(0).getId();
				ProjectType updatedProject = apiUtils.invokeUpdateProject(credential, siteId, projectId, newParentProjectId, newProjectName, newProjectDescription);
				log.info("#updatedProject.getName() : {}", 	updatedProject.getName());
				resultCode = "success";
			} else if (projects.getProject().size()>1) {
				resultCode = "toManyProjects";
			} else {
				resultCode = "noProject";
			}				
		} catch (Exception e) {
			log.warn("{} 오류 발생", Thread.currentThread().getStackTrace()[1].getMethodName());
			log.warn(commonUtil.getExceptionTrace(e));
			result.put(Constant.ERROR_MESSAGE, commonUtil.getExceptionTrace(e));
		}
		
		result.put(Constant.RESULT_CODE, resultCode);
		return result;
	}		

    //태블로 워크북 권한 부여
	public Map<String, Object> addPermissionToWorkbook(String userId, String workbookId, String auth) throws Exception {
		Map<String, Object> result = new HashMap<>();
		String resultCode = "fail";
		
		try {
			//tableau sign in
			TableauCredentialsType credential = apiUtils.simpleSignIn();
			String siteId = credential.getSite().getId();
			//권한부여
			PermissionsType addPermissionWorkbook = apiUtils.invokeAddPermissionsToWorkbookToUser(credential, siteId, userId, workbookId, auth);
			log.info("#addPermissionWorkbook.getWorkbook().getId()" + addPermissionWorkbook.getWorkbook().getId());
			resultCode = "success";
		} catch (Exception e) {
			log.warn("{} 오류 발생", Thread.currentThread().getStackTrace()[1].getMethodName());
			log.warn(commonUtil.getExceptionTrace(e));
			result.put(Constant.ERROR_MESSAGE, commonUtil.getExceptionTrace(e));
		}
		
		result.put(Constant.RESULT_CODE, resultCode);
		return result;
	}

	//태블로 워크북 권한 삭제
	public Map<String, Object> deletePermissionToWorkbook(String userId, String workbookId, String auth) throws Exception {
		Map<String, Object> result = new HashMap<>();
		String resultCode = "fail";
		String capabilityName = "";
		String capabilityMode = "";
		boolean deletePermissionWorkbook = false;
		try {
			//tableau sign in
			TableauCredentialsType credential = apiUtils.simpleSignIn();
			String siteId = credential.getSite().getId();
			//권한부여
			
			capabilityName = "Read";
			capabilityMode = "Allow";
			deletePermissionWorkbook = apiUtils.invokeDeletePermissionsToWorkbookToUser(credential, siteId, userId, workbookId, auth, capabilityName, capabilityMode);
			
			if("1".equals(auth)) {
				capabilityName = "WebAuthoring";
				capabilityMode = "Allow";
				deletePermissionWorkbook = apiUtils.invokeDeletePermissionsToWorkbookToUser(credential, siteId, userId, workbookId, auth, capabilityName, capabilityMode);
				
				capabilityName = "ExportXml";
				capabilityMode = "Allow";
				deletePermissionWorkbook = apiUtils.invokeDeletePermissionsToWorkbookToUser(credential, siteId, userId, workbookId, auth, capabilityName, capabilityMode);
				
			}
			
			if(deletePermissionWorkbook) {
				resultCode = "success";
			}
		} catch (Exception e) {
			log.warn("{} 오류 발생", Thread.currentThread().getStackTrace()[1].getMethodName());
			log.warn(commonUtil.getExceptionTrace(e));
			result.put(Constant.ERROR_MESSAGE, commonUtil.getExceptionTrace(e));
		}
		
		result.put(Constant.RESULT_CODE, resultCode);
		return result;
	}
	
	 //태블로 프로젝트 수정
	public Map<String, Object> downloadPreimage(String workbookId, String viewId, String workbookName, String path) throws Exception {
		Map<String, Object> result = new HashMap<>();
		String resultCode = "fail";
		
		try {
			//tableau sign in
			TableauCredentialsType credential = apiUtils.simpleSignIn();
			String siteId = credential.getSite().getId();
			
			apiUtils.invokeDownloadPreViewsImage(credential, siteId, workbookId, viewId, workbookName, path);
				
				
			
		} catch (Exception e) {
			log.warn("{} 오류 발생", Thread.currentThread().getStackTrace()[1].getMethodName());
			log.warn(commonUtil.getExceptionTrace(e));
			result.put(Constant.ERROR_MESSAGE, commonUtil.getExceptionTrace(e));
		}
		
		result.put(Constant.RESULT_CODE, resultCode);
		return result;
	}		
}
