package com.bdp.ap.common.tableauapi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.common.aws.AwsSecretsUtil;
import com.bdp.ap.common.tableauapi.model.BackgroundJobListType;
import com.bdp.ap.common.tableauapi.model.BackgroundJobType;
import com.bdp.ap.common.tableauapi.model.CapabilityType;
import com.bdp.ap.common.tableauapi.model.ConnectionCredentialsType;
import com.bdp.ap.common.tableauapi.model.ConnectionListType;
import com.bdp.ap.common.tableauapi.model.ConnectionType;
import com.bdp.ap.common.tableauapi.model.DataSourceListType;
import com.bdp.ap.common.tableauapi.model.DataSourceType;
import com.bdp.ap.common.tableauapi.model.ErrorType;
import com.bdp.ap.common.tableauapi.model.FileUploadType;
import com.bdp.ap.common.tableauapi.model.GranteeCapabilitiesType;
import com.bdp.ap.common.tableauapi.model.GroupListType;
import com.bdp.ap.common.tableauapi.model.GroupType;
import com.bdp.ap.common.tableauapi.model.ObjectFactory;
import com.bdp.ap.common.tableauapi.model.PermissionsType;
import com.bdp.ap.common.tableauapi.model.ProjectListType;
import com.bdp.ap.common.tableauapi.model.ProjectType;
import com.bdp.ap.common.tableauapi.model.ScheduleListType;
import com.bdp.ap.common.tableauapi.model.ScheduleType;
import com.bdp.ap.common.tableauapi.model.SiteListType;
import com.bdp.ap.common.tableauapi.model.SiteRoleType;
import com.bdp.ap.common.tableauapi.model.SiteType;
import com.bdp.ap.common.tableauapi.model.TableauCredentialsType;
import com.bdp.ap.common.tableauapi.model.TaskExtractRefreshType;
import com.bdp.ap.common.tableauapi.model.TaskType;
import com.bdp.ap.common.tableauapi.model.TsRequest;
import com.bdp.ap.common.tableauapi.model.TsResponse;
import com.bdp.ap.common.tableauapi.model.UserListType;
import com.bdp.ap.common.tableauapi.model.UserType;
import com.bdp.ap.common.tableauapi.model.ViewListType;
import com.bdp.ap.common.tableauapi.model.WorkbookListType;
import com.bdp.ap.common.tableauapi.model.WorkbookType;
import com.bdp.ap.config.props.AwsProps;
import com.bdp.ap.config.props.TableauProps;
import com.google.common.io.Files;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.MultiPart;
import com.sun.jersey.multipart.MultiPartMediaTypes;
import com.sun.jersey.multipart.file.FileDataBodyPart;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.utils.StringUtils;

/**
 * This class encapsulates the logic used to make requests to the Tableau Server
 * REST API. This class is implemented as a singleton.
 */
@Slf4j
@Component
public class TableauApiUtils {
	
	@Resource(name="awsSecretsUtil")
	private AwsSecretsUtil awsSecretsUtil;
	
	@Resource(name="awsProps")
	private AwsProps awsProps;

	@Resource(name="tableauProps")
	private TableauProps props;

	private static String tableauApiUrl;
	private static String tableauTokenUrl;
	private static String tableauSchemaLocation;
	private static String tableauUsername;
	private static String tableauUserpw;
	private static String tableauDefaultContentUrl;
	private static String tableauTargetSite;	
	
	private enum Operation {
		ADD_WORKBOOK_PERMISSIONS(getApiUriBuilder().path("sites/{siteId}/workbooks/{workbookId}/permissions")),
		APPEND_FILE_UPLOAD(getApiUriBuilder().path("sites/{siteId}/fileUploads/{uploadSessionId}")),
		CREATE_GROUP(getApiUriBuilder().path("sites/{siteId}/groups")),
		INITIATE_FILE_UPLOAD(getApiUriBuilder().path("sites/{siteId}/fileUploads")),
		PUBLISH_WORKBOOK(getApiUriBuilder().path("sites/{siteId}/workbooks")),
		QUERY_PROJECTS(getApiUriBuilder().path("sites/{siteId}/projects")),
		QUERY_SITES(getApiUriBuilder().path("sites")),
		QUERY_WORKBOOKS(getApiUriBuilder().path("sites/{siteId}/users/{userId}/workbooks")),
		SIGN_IN(getApiUriBuilder().path("auth/signin")),
		SIGN_OUT(getApiUriBuilder().path("auth/signout"));

		private final UriBuilder m_builder;

		Operation(UriBuilder builder) {
			m_builder = builder;
		}

		UriBuilder getUriBuilder() {
			return m_builder;
		}

		String getUrl(Object... values) {
			return m_builder.build(values).toString();
		}
	}
	
	// The only instance of the RestApiUtils
	//private static RestApiUtils INSTANCE = null;
	private static Marshaller s_jaxbMarshaller;
	private static Unmarshaller s_jaxbUnmarshaller;

	//private static String protocol = "http://";
	//private static String version = "3.0";
	
	/**
	 * Initializes the TableauApiUtils if it has not already been done so.
	 *
	 * @return the single instance of the TableauApiUtils
	 */
//	public RestApiUtils getInstance() {
//		if (INSTANCE == null) {
//			INSTANCE = new RestApiUtils();
//			initialize();
//		}
//
//		return INSTANCE;
//	}

	/**
	 * Creates an instance of UriBuilder, using the URL of the server specified
	 * in the configuration file.
	 *
	 * @return the URI builder
	 */
	private static UriBuilder getApiUriBuilder() {
		return UriBuilder.fromPath(tableauApiUrl);
	}


	/**
	 * Initializes the TableauApiUtils. The initialize code loads values from the configuration
	 * file and initializes the JAXB marshaller and unmarshaller.
	 */
	@PostConstruct
	private void initialize() {
		try {
			tableauApiUrl = props.getApiUrl();
			tableauTokenUrl = props.getTokenUrl();
			tableauSchemaLocation = props.getSchemaLocation();
			tableauUsername = props.getUsername();
			
			// AWS Secret 을 사용할 경우 주석 해제, props.getUserpw(); 주석 처리
			//tableauUserpw = awsSecretsUtil.getValue(awsProps.getTableauKey());
			tableauUserpw = props.getUserpw();
					
			tableauDefaultContentUrl = props.getDefaultContentUrl();
			tableauTargetSite = props.getTargetSite();

			JAXBContext jaxbContext = JAXBContext.newInstance(TsRequest.class, TsResponse.class);
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new ClassPathResource(tableauSchemaLocation).getFile());
			s_jaxbMarshaller = jaxbContext.createMarshaller();
			s_jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			s_jaxbUnmarshaller.setSchema(schema);
			s_jaxbMarshaller.setSchema(schema);
		} catch (JAXBException | 
				SAXException | 
				IOException ex) {
			log.info("IOException : " + ex.getMessage().toString());
			throw new IllegalStateException("Failed to initialize the REST API");
		}
	}

	private final String TABLEAU_AUTH_HEADER = "X-Tableau-Auth";
	private final String TABLEAU_PAYLOAD_NAME = "request_payload";
	
	private final String TABLEAU_CONTENT_TYPE = "application/octet-stream"; 

	private ObjectFactory m_objectFactory = new ObjectFactory();

	//타블로 API
	private final String ADD_WORKBOOK_PERMISSIONS 	= "sites/{siteId}/workbooks/{workbookId}/permissions";
	private final String APPEND_FILE_UPLOAD 		= "sites/{siteId}/fileUploads/{uploadSessionId}";
	private final String CREATE_GROUP 				= "sites/{siteId}/groups";
	private final String INITIATE_FILE_UPLOAD 		= "sites/{siteId}/fileUploads";
	private final String PUBLISH_WORKBOOK 			= "sites/{siteId}/workbooks";
	private final String QUERY_PROJECTS 			= "sites/{siteId}/projects";
	private final String QUERY_SITES 				= "sites";
	private final String QUERY_WORKBOOKS 			= "sites/{siteId}/users/{userId}/workbooks";
	private final String SIGN_IN 					= "auth/signin";
	private final String SIGN_OUT 					= "auth/signout";
	
	/**
	 * 타블로 API URL 생성
	 * @param apiPath
	 * @param values
	 * @return
	 */
	private String getUrl(String apiPath, Object... values) {
		return getUriBuilder().path(apiPath).build(values).toString();
	}
	
	/**
	 * 타블로 API URL 파라메터 추가를 위한 UriBuilder 생성
	 * @param apiPath
	 * @return
	 */
	private UriBuilder getUriBuilder(String apiPath) {
		return getUriBuilder().path(apiPath);
	}
	
	/**
	 * 타블로 기본 UriBuilder 생성
	 * @return
	 */
	private UriBuilder getUriBuilder() {
		return UriBuilder.fromPath(props.getApiUrl());
	}

	// This class is implemented as a singleton, so it cannot be constructed externally
	//private TableauApiUtils() {}

	/**
	 * Creates a grantee capability object used to modify permissions on Tableau
	 * Server. A grantee capability contains the group ID and a map of
	 * capabilities and their permission mode.
	 *
	 * @param group
	 *			the group the permissions apply to
	 * @param capabilitiesMap
	 *			the map of capability name to permission mode
	 * @return the grantee capability for the group
	 */
	public GranteeCapabilitiesType createGroupGranteeCapability(GroupType group, Map<String, String> capabilitiesMap) {
		GranteeCapabilitiesType granteeCapabilities = m_objectFactory.createGranteeCapabilitiesType();

		// Sets the grantee to the specified group
		granteeCapabilities.setGroup(group);
		GranteeCapabilitiesType.Capabilities capabilities = m_objectFactory.createGranteeCapabilitiesTypeCapabilities();

		// Iterates over the list of capabilities and creates a capability element
		for (String key : capabilitiesMap.keySet()) {
			CapabilityType capabilityType = m_objectFactory.createCapabilityType();

			// Sets the capability name and permission mode
			capabilityType.setName(key);
			capabilityType.setMode(capabilitiesMap.get(key));

			// Adds the capability to the list of capabilities for the grantee
			capabilities.getCapability().add(capabilityType);
		}

		// Sets the list of capabilities for the grantee element
		granteeCapabilities.setCapabilities(capabilities);

		return granteeCapabilities;
	}

	public GranteeCapabilitiesType createUserGranteeCapability(UserType user, Map<String, String> capabilitiesMap) {
		GranteeCapabilitiesType granteeCapabilities = m_objectFactory.createGranteeCapabilitiesType();

		// Sets the grantee to the specified user
		granteeCapabilities.setUser(user);
		GranteeCapabilitiesType.Capabilities capabilities = m_objectFactory.createGranteeCapabilitiesTypeCapabilities();

		// Iterates over the list of capabilities and creates a capability element
		for (String key : capabilitiesMap.keySet()) {
			CapabilityType capabilityType = m_objectFactory.createCapabilityType();

			// Sets the capability name and permission mode
			capabilityType.setName(key);
			capabilityType.setMode(capabilitiesMap.get(key));

			// Adds the capability to the list of capabilities for the grantee
			capabilities.getCapability().add(capabilityType);
		}

		// Sets the list of capabilities for the grantee element
		granteeCapabilities.setCapabilities(capabilities);

		return granteeCapabilities;
	}		

	//사용자=>워크북 권한부여
	public PermissionsType invokeAddPermissionsToWorkbookToUser(TableauCredentialsType credential, String siteId,
			String userId, String workbookId , String auth) {
		
		//UserType 생성
		UserType user = new UserType();
		user.setId(userId);
		//capabilitiesMap 생성
		Map<String, String> capabilitiesMap = new HashMap<String, String>();
		capabilitiesMap.put("Read", "Allow");
		//capabilitiesMap.put("ExportImage", "Allow");
		if("1".equals(auth)) {
			capabilitiesMap.put("WebAuthoring", "Allow");
			capabilitiesMap.put("ExportXml", "Allow");
		}
		
		//GranteeCapabilitiesType에 맵핑할 유저와 권한들map 생성
		GranteeCapabilitiesType granteeCapabilitiesType = createUserGranteeCapability(user, capabilitiesMap);
		
		//<유저+권한> 묶음을 리스트에 추가
		List<GranteeCapabilitiesType> granteeCapabilities = new ArrayList<GranteeCapabilitiesType>();
		granteeCapabilities.add(granteeCapabilitiesType);
		
		//권한 생성
		PermissionsType permission = invokeAddPermissionsToWorkbook(credential, siteId, workbookId, granteeCapabilities);
		return permission;
	}

	//사용자=>워크북 권한삭제
	public boolean invokeDeletePermissionsToWorkbookToUser(TableauCredentialsType credential, String siteId,
			String userId, String workbookId , String auth , String capabilityName, String capabilityMode) {
		
		boolean result = false;
		String url = tableauApiUrl + "/sites/" + siteId + "/workbooks/" + workbookId + "/permissions/users/" + userId + "/" + capabilityName +"/" + capabilityMode;
		
		// Makes a DELETE request with the authenticity token
		TsResponse response = delete(url, credential.getToken());
		
		// Verifies that the response has a workbooks element
		if (response.getError().getCode() == BigInteger.valueOf(204)) {
			result = true;
		}
		
		// No workbooks were found
		return result;
	}
	
	/**
	 * Invokes an HTTP request to add permissions to the target workbook.
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param workbookId
	 *			the ID of the target workbook
	 * @param granteeCapabilities
	 *			the list of grantees, including the permissions to add for this
	 *			workbook
	 * @return the permissions added to the workbook, otherwise
	 *		 <code>null</code>
	 */
	public PermissionsType invokeAddPermissionsToWorkbook(TableauCredentialsType credential, String siteId,
			String workbookId, List<GranteeCapabilitiesType> granteeCapabilities) {

		String url = Operation.ADD_WORKBOOK_PERMISSIONS.getUrl(siteId, workbookId);
		log.info("Adding permissions to workbook '{}'.", workbookId);

		// Creates the payload used to add permissions
		TsRequest payload = createPayloadForAddingWorkbookPermissions(workbookId, granteeCapabilities);

		// Makes a PUT request using the credential's authenticity token
		TsResponse response = put(url, credential.getToken(), payload);

		// Verifies that the response has a permissions element
		if (response.getPermissions() != null) {

			return response.getPermissions();
		}

		// No permissions were added
		return null;
	}

	public GroupListType invokeQueryGroupsAll(TableauCredentialsType credential, String siteId) {
		String url = tableauApiUrl + "/sites/"+siteId + "/groups";
		TsResponse response = get(url, credential.getToken());
		if (response.getGroups() != null) {
			return response.getGroups();
		}
		return null;
	}
	
	public GroupListType invokeQueryGroupsWithGroupName(TableauCredentialsType credential, String siteId, String groupName) throws UnsupportedEncodingException {
		//검색은 다 get으로 던지는거라, 한글일경우 에러가 난다. 꼭 인코딩 해서 던질것.
		groupName = java.net.URLEncoder.encode(groupName,"UTF-8");
		
		String url = tableauApiUrl + "/sites/"+siteId + "/groups?filter=name:eq:" + groupName;
		TsResponse response = get(url, credential.getToken());
		if (response.getGroups() != null) {
			return response.getGroups();
		}
		return null;
	}	
	
	
	/**
	 * Invokes an HTTP request to create a group on target site.
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param groupName
	 *			the name to assign to the new group
	 * @param requestPayload
	 *			the payload used to create the group
	 * @return the group if it was successfully created, otherwise
	 *		 <code>null</code>
	 */
	public GroupType invokeCreateGroup(TableauCredentialsType credential, String siteId, String groupName, boolean asJob) {
		String url = tableauApiUrl + "/sites/" + siteId + "/groups?asJob="+ asJob;		//==> opertaion으로 뺄것
		log.info("Creating group '{}' on site '{}'.", groupName, siteId);

		TsRequest payload = createPayloadForCreatingGroup(groupName);

		TsResponse response = post(url, credential.getToken(), payload);

		if (response.getGroup() != null) {
			return response.getGroup();
		}

		// No group was created
		return null;
	}
	
	public GroupType invokeUpdateGroup(TableauCredentialsType credential, String siteId, String groupId, String newGroupName, boolean asJob) {
		String url = tableauApiUrl + "/sites/" + siteId + "/groups/" + groupId + "?asJob="+ asJob;		//==> opertaion으로 뺄것

		TsRequest payload = createPayloadForCreatingGroup(newGroupName);

		TsResponse response = put(url, credential.getToken(), payload);

		if (response.getGroup() != null) {
			return response.getGroup();
		}

		return null;
	}	

	/**
	 * Invokes an HTTP request to publish a workbook to target site.
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param requestPayload
	 *			the XML payload containing the workbook attributes used to
	 *			publish the workbook
	 * @param workbookFile
	 *			the workbook file to publish
	 * @param chunkedPublish
	 *			whether to publish the workbook in chunks or not
	 * @return the workbook if it was published successfully, otherwise
	 *		 <code>null</code>
	 */
	public WorkbookType invokePublishWorkbook(TableauCredentialsType credential, String siteId, String projectId,
			String workbookName, File workbookFile, boolean chunkedPublish, String ext, boolean overwrite) {

		if (chunkedPublish) {
			return invokePublishWorkbookChunked(credential, siteId, projectId, workbookName, workbookFile, ext, overwrite);
		} else {
			return invokePublishWorkbookSimple(credential, siteId, projectId, workbookName, workbookFile, ext, overwrite);
		}
	}
	
	/**
	 * Invokes an HTTP request to publish a datasorce to target site.
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param requestPayload
	 *			the XML payload containing the datasorce attributes used to
	 *			publish the datasorce
	 * @param workbookFile
	 *			the datasorce file to publish
	 * @param chunkedPublish
	 *			whether to publish the datasorce in chunks or not
	 * @return the datasorce if it was published successfully, otherwise
	 *		 <code>null</code>
	 */
	public DataSourceType invokePublishDatasource(TableauCredentialsType credential, String siteId, String projectId,
			String datasourceName, File datasourceFile, boolean chunkedPublish, String ext, boolean overwrite) {
		if (chunkedPublish) {
			return invokePublishDatasourceChunked(credential, siteId, projectId, datasourceName, datasourceFile, ext, overwrite);
		} else {
			return invokePublishDatasourceSimple(credential, siteId, projectId, datasourceName, datasourceFile, ext, overwrite);
		}
	}

	/**
	 * Invokes an HTTP request to query the projects on the target site.
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @throws UnsupportedEncodingException 
	 */
	public ProjectListType invokeQueryProjectsWithProjectName(TableauCredentialsType credential, String siteId, String projectName) throws UnsupportedEncodingException {
		//검색은 다 get으로 던지는거라, 한글일경우 에러가 난다. 꼭 인코딩 해서 던질것.
		projectName = java.net.URLEncoder.encode(projectName,"UTF-8");
		
		String url = tableauApiUrl + "/sites/" + siteId + "/projects?filter=name:eq:" + projectName;	

		TsResponse response = get(url, credential.getToken());

		if (response.getProjects() != null) {
			return response.getProjects();
		}
		return null;
	}
	public ProjectListType invokeQueryProjectsAll(TableauCredentialsType credential, String siteId) {
		String url = tableauApiUrl + "/sites/" + siteId + "/projects";

		TsResponse response = get(url, credential.getToken());

		if (response.getProjects() != null) {
			return response.getProjects();
		}
		return null;
	}	
	public ProjectListType invokeQueryProjectsPaging(TableauCredentialsType credential, String siteId, int pageSize, int pageNumber) {
		String url = tableauApiUrl + "/sites/" + siteId + "/projects?paeSize="+ pageSize + "&pageNumber" + pageNumber;	//==> opertaion으로 뺄것
		
		TsResponse response = get(url, credential.getToken());

		if (response.getProjects() != null) {
			return response.getProjects();
		}
		return null;
	}
	
	public ProjectType invokeCreateProject(TableauCredentialsType credential, String siteId, String parentProjectId, String projectName, String projectDescription) {
		String url = tableauApiUrl + "/sites/" + siteId + "/projects";

		TsRequest payload = createPayloadForCreatingProject(parentProjectId, projectName, projectDescription);

		TsResponse response = post(url, credential.getToken(), payload);

		if (response.getProject() != null) {
			return response.getProject();
		}
		// No group was created
		return null;
	}
	
	public ProjectType invokeUpdateProject(TableauCredentialsType credential, String siteId, String projectId, String newParentProjectId, String newProjectName, String newProjectDescription) {
		String url = tableauApiUrl + "/sites/" + siteId + "/projects/" + projectId;

		TsRequest payload = createPayloadForUpdateProject(newParentProjectId, newProjectName, newProjectDescription);

		TsResponse response = put(url, credential.getToken(), payload);

		if (response.getProject() != null) {
			return response.getProject();
		}
		// No group was created
		return null;
	}		

	/**
	 * Invokes an HTTP request to query the sites on the server.
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @return a list of sites if the query succeeded, otherwise <code>null</code>
	 */
	public SiteListType invokeQuerySites(TableauCredentialsType credential, int pageSize, int pageNumber) {


		String url = tableauApiUrl + "/sites?paeSize="+ pageSize + "&pageNumber" + pageNumber;	//==> opertaion으로 뺄것

		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());

		// Verifies that the response has a sites element
		if (response.getSites() != null) {

			return response.getSites();
		}

		// No sites were found
		return null;
	}

	/**
	 * Invokes an HTTP request to query for the list of workbooks for which the
	 * user has read capability.
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param userId
	 *			the ID of the target user
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public WorkbookListType invokeQueryUsersWorkbooks(TableauCredentialsType credential, String siteId, String userId, int pageSize, int pageNumber) {

		//String url = Operation.QUERY_WORKBOOKS.getUrl(siteId, userId);
		String url = tableauApiUrl + "/sites/"+siteId + "/users/" +userId+"/workbooks?paeSize="+ pageSize + "&pageNumber" + pageNumber;	//==> opertaion으로 뺄것
		
		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());
		log.info("Querying workbooks on site '{}'.", siteId);

		// Verifies that the response has a workbooks element
		if (response.getWorkbooks() != null) {

			return response.getWorkbooks();
		}

		// No workbooks were found
		return null;
	}
	
	/**
	 * Invokes an HTTP request to query for the list of workbooks for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 * @throws UnsupportedEncodingException 
	 */
	public WorkbookListType invokeQuerySitesWorkbooksWithWorkbookName(TableauCredentialsType credential, String siteId, String workbookName) throws UnsupportedEncodingException {
		//검색은 다 get으로 던지는거라, 한글일경우 에러가 난다. 꼭 인코딩 해서 던질것.
		workbookName = java.net.URLEncoder.encode(workbookName,"UTF-8");		
		
		String url = tableauApiUrl + "/sites/"+siteId + "/workbooks?filter=name:eq:" + workbookName;
		
		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());

		// Verifies that the response has a workbooks element
		if (response.getWorkbooks() != null) {
			return response.getWorkbooks();
		}

		// No workbooks were found
		return null;
	}
	public WorkbookListType invokeQuerySitesWorkbooksAll(TableauCredentialsType credential, String siteId) {
		//String url = Operation.QUERY_WORKBOOKS.getUrl(siteId, userId);
		String url = tableauApiUrl + "/sites/"+siteId + "/workbooks";
		
		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());

		// Verifies that the response has a workbooks element
		if (response.getWorkbooks() != null) {
			return response.getWorkbooks();
		}

		// No workbooks were found
		return null;
	}	
	public WorkbookListType invokeQuerySitesWorkbooksPaging(TableauCredentialsType credential, String siteId, int pageSize, int pageNumber) {
		//String url = Operation.QUERY_WORKBOOKS.getUrl(siteId, userId);
		String url = tableauApiUrl + "/sites/"+siteId + "/workbooks?paeSize="+ pageSize + "&pageNumber" + pageNumber;
		
		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());

		// Verifies that the response has a workbooks element
		if (response.getWorkbooks() != null) {
			return response.getWorkbooks();
		}

		// No workbooks were found
		return null;
	}
	
	/**
	 * Invokes an HTTP request to query for the list of workbooks for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param workbookId
	 *			the ID of the target workbookId
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public ErrorType invokeQuerySitesEmptyWorkbooks(TableauCredentialsType credential, String siteId, String workbookId) {

		//log.info(String.format("Querying Empty workbook on site '%s'.", siteId));

		String url = tableauApiUrl + "/sites/"+siteId + "/workbooks/" + workbookId;
		
		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());

		// Verifies that the response has a workbooks element
		if (response.getError() != null) {
			//log.info("Query Empty workbook is successful!");

			return response.getError();
		}

		// No workbooks were found
		return null;
	}

	/**
	 * Invokes an HTTP request to query for the list of workbookConnections for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param workbookId
	 *			the ID of the target workbookId
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public ConnectionListType invokeQueryWorkbookConnections(TableauCredentialsType credential, String siteId, String workbookId) {
		
		//log.info(String.format("Querying workbook Connections on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/"+siteId + "/workbooks/" + workbookId + "/connections";
		
		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());
		
		// Verifies that the response has a workbooks element
		if (response.getConnections() != null) {
			//log.info("Query Empty workbook is successful!");
			
			return response.getConnections();
		}
		
		// No workbooks were found
		return null;
	}
	
	/**
	 * Invokes an HTTP request to query for the Delete Workbook for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param workbookId
	 *			the ID of the target workbookId
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public boolean invokeQueryDeleteWorkbook(TableauCredentialsType credential, String siteId, String workbookId) {
		boolean result = false;
		
		//log.info(String.format("delete workbook on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/" + siteId + "/workbooks/" + workbookId;
		
		// Makes a DELETE request with the authenticity token
//		int response = delete(url, credential.getToken());
//		
//		// Verifies that the response has a workbooks element
//		if (response == 204) {
//			result = true;
//		}
		
		// No workbooks were found
		return result;
	}
	
	/**
	 * Invokes an HTTP request to query for the list of datasources for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public DataSourceListType invokeQueryDatasources(TableauCredentialsType credential, String siteId, int pageSize, int pageNumber) {
		
		//log.info(String.format("Querying datasours on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/"+siteId + "/datasources?pageSize=" + pageSize + "&pageNumber=" + pageNumber;
		
		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());
		
		// Verifies that the response has a workbooks element
		if (response.getDatasources() != null) {
			//log.info("Query Datasources is successful!");
			
			return response.getDatasources();
		}
		
		// No workbooks were found
		return null;
	}
	
	/**
	 * Invokes an HTTP request to query for the datasource for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public DataSourceType invokeQueryDatasource(TableauCredentialsType credential, String siteId, String datasourceId) {
		
		//log.info(String.format("Querying datasour on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/"+siteId + "/datasources" + datasourceId;
		
		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());
		
		// Verifies that the response has a datasource element
		if (response.getDatasource() != null) {
			//log.info("Query Datasource is successful!");
			
			return response.getDatasource();
		}
		
		// No workbooks were found
		return null;
	}
	
	/**
	 * Invokes an HTTP request to query for the list of datasources for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param workbookId
	 *			the ID of the target workbookId
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public ErrorType invokeQuerySitesEmptyDatasources(TableauCredentialsType credential, String siteId, String datasourceId) {

		//log.info(String.format("Querying Empty workbook on site '%s'.", siteId));

		String url = tableauApiUrl + "/sites/"+siteId + "/datasources/" + datasourceId;
		
		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());

		// Verifies that the response has a workbooks element
		if (response.getError() != null) {
			//log.info("Query Empty datasource is successful!");

			return response.getError();
		}

		// No workbooks were found
		return null;
	}
	
	/**
	 * Invokes an HTTP request to query for the Delete Workbook for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param workbookId
	 *			the ID of the target workbookId
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public boolean invokeQueryDeleteDatasource(TableauCredentialsType credential, String siteId, String datasourceId) {
		boolean result = false;
		
		//log.info(String.format("delete workbook on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/" + siteId + "/datasources/" + datasourceId;
		
//		// Makes a DELETE request with the authenticity token
//		int response = delete(url, credential.getToken());
//		
//		// Verifies that the response has a workbooks element
//		if (response == 204) {
//			result = true;
//		}
		
		// No workbooks were found
		return result;
	}
	
	/**
	 * Invokes an HTTP request to query for the list of datasourceConnections for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param datasourceId
	 *			the ID of the target datasourceId
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public ConnectionListType invokeQueryDatasourceConnections(TableauCredentialsType credential, String siteId, String datasourceId) {
		
		//log.info(String.format("Querying datasource Connections on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/"+siteId + "/datasources/" + datasourceId + "/connections";
		
		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());
		
		// Verifies that the response has a workbooks element
		if (response.getConnections() != null) {
			//log.info("Query datasource Connections is successful!");
			
			return response.getConnections();
		}
		
		// No workbooks were found
		return null;
	}
	
	/**
	 * Invokes an HTTP request to query for the list of views for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param workbookId
	 *			the ID of the target workbookId
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public ViewListType invokeQueryviews(TableauCredentialsType credential, String siteId, String workbookId) {
		
		//log.info(String.format("Querying views on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/"+siteId + "/workbooks/" + workbookId + "/views";
		
		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());
		
		// Verifies that the response has a workbooks element
		if (response.getViews() != null) {
			//log.info("Query views is successful!");
			
			return response.getViews();
		}
		
		// No workbooks were found
		return null;
	}

	/**
	 * Invokes an HTTP request to query for the list of users for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param workbookId
	 *			the ID of the target workbookId
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public UserListType invokeQueryUsersAll(TableauCredentialsType credential, String siteId) {
		String url = tableauApiUrl + "/sites/"+siteId + "/users";
		TsResponse response = get(url, credential.getToken());
		if (response.getUsers() != null) {
			return response.getUsers();
		}
		return null;
	}	 
	public UserListType invokeQueryUsersPaging(TableauCredentialsType credential, String siteId, int pageSize, int pageNumber) {
		String url = tableauApiUrl + "/sites/"+siteId + "/users?pageSize=" + pageSize + "&pageNumber=" + pageNumber;
		TsResponse response = get(url, credential.getToken());
		if (response.getUsers() != null) {
			return response.getUsers();
		}
		return null;
	}	
	public UserListType invokeQueryUsersWithUserName(TableauCredentialsType credential, String siteId, String userName) throws UnsupportedEncodingException {
		//검색은 다 get으로 던지는거라, 한글일경우 에러가 난다. 꼭 인코딩 해서 던질것.
		userName = java.net.URLEncoder.encode(userName,"UTF-8");
		
		String url = tableauApiUrl + "/sites/"+siteId + "/users?filter=name:eq:" + userName;
		
		log.info("#invokeQueryUsersWithFilter url = " + url);
		
		TsResponse response = get(url, credential.getToken());
		if (response.getUsers() != null) {
			return response.getUsers();
		}
		return null;
	}

	/**
	 * Invokes an HTTP request to query for the list of users for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param workbookId
	 *			the ID of the target workbookId
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public UserType invokeQueryUser(TableauCredentialsType credential, String siteId, String userId) {
		
		//log.info(String.format("Querying users on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/"+siteId + "/users/"+userId;
		
		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());
		
		// Verifies that the response has a workbooks element
		if (response.getUser() != null) {
			//log.info("Query users is successful!");
			
			return response.getUser();
		}
		
		// No workbooks were found
		return null;
	}
	
	public UserType invokeAddUser(TableauCredentialsType credential, String siteId, String userName, String newSiteRole) {
		String url = tableauApiUrl + "/sites/"+siteId + "/users";
		
		TsRequest payload = createPayloadForUser(userName, newSiteRole);
		
		// Makes a GET request with the authenticity token
		TsResponse response = post(url, credential.getToken(), payload);
		
		// Verifies that the response has a workbooks element
		if (response.getUser() != null) {
			return response.getUser();
		}
		
		// No workbooks were found
		return null;
	}
	private TsRequest createPayloadForUser(String username, String newSiteRole) {
		// Creates the parent tsRequest element
		TsRequest requestPayload = m_objectFactory.createTsRequest();
		// Creates the User element
		UserType user = m_objectFactory.createUserType();
		user.setName(username);
		user.setSiteRole(getTableauSiteRoleType(newSiteRole));
		//user.setAuthSetting(SiteUserAuthSettingType.SERVER_DEFAULT);	아무 권한도 안준다.
		// Adds the credential element to the request payload
		requestPayload.setUser(user);
		return requestPayload;
	}
	
	/**\
	 * 
	 * @param credential
	 * @param siteId
	 * @param userId 포털 시스템 user_id - 태블로 name
	 * @param userName 포털 시스템 user_nm - 태블로 fullName
	 * @param siteRole
	 * @return
	 */
	public UserType invokeAddUser(TableauCredentialsType credential, String siteId, String userId, String userName, String siteRole) {
		String url = tableauApiUrl + "/sites/"+siteId + "/users";
		
		TsRequest payload = createPayloadForUser(userId, userName, siteRole);
		
		// Makes a GET request with the authenticity token
		TsResponse response = post(url, credential.getToken(), payload);
		
		// Verifies that the response has a workbooks element
		if (response.getUser() != null) {
			return response.getUser();
		}
		
		// No workbooks were found
		return null;
	}
	private TsRequest createPayloadForUser(String userId, String username, String siteRole) {
		// Creates the parent tsRequest element
		TsRequest requestPayload = m_objectFactory.createTsRequest();
		// Creates the User element
		UserType user = m_objectFactory.createUserType();
		user.setName(userId);
		user.setFullName(username);
		user.setSiteRole(getTableauSiteRoleType(siteRole));
		//user.setAuthSetting(SiteUserAuthSettingType.SERVER_DEFAULT);	아무 권한도 안준다.
		// Adds the credential element to the request payload
		requestPayload.setUser(user);
		return requestPayload;
	}	
	
	public UserType invokeUpdateUser(TableauCredentialsType credential, String siteId, String userId, String newFullName, String newPassword, String newSiteRole) {
		String url = tableauApiUrl + "/sites/"+siteId + "/users/" + userId;
		
		log.info("#invokeUpdateUser url = " + url);
		
		TsRequest payload = createPayloadForUpdateUser(newFullName, newPassword, newSiteRole);
		
		// Makes a GET request with the authenticity token
		TsResponse response = put(url, credential.getToken(), payload);
		
		// Verifies that the response has a workbooks element
		if (response.getUser() != null) {
			return response.getUser();
		}
		
		// No workbooks were found
		return null;
	}
	private TsRequest createPayloadForUpdateUser(String newFullName, String newPassword, String newSiteRole) {
		// Creates the parent tsRequest element
		TsRequest requestPayload = m_objectFactory.createTsRequest();
		
		// Creates the User element
		UserType user = m_objectFactory.createUserType();
		user.setFullName(newFullName);
		if (StringUtils.isNotBlank(newPassword)) {
			user.setPassword(newPassword);
		}
		if (StringUtils.isNotBlank(newSiteRole)) {
			user.setSiteRole(getTableauSiteRoleType(newSiteRole));
		}
		
		// Adds the credential element to the request payload
		requestPayload.setUser(user);
		
		return requestPayload;
	}	
	
	private SiteRoleType getTableauSiteRoleType(String gubunNum) {
		SiteRoleType type = null;
		switch (gubunNum) {
		case "1":
			type = SiteRoleType.SITE_ADMINISTRATOR;
			break;
		case "2":
			type = SiteRoleType.CREATOR;
			break;
		case "3":
			type = SiteRoleType.EXPLORER;
			break;
		case "4":
			type = SiteRoleType.VIEWER;
			break;
		case "8":
			type = SiteRoleType.EXPLORER_CAN_PUBLISH;
			break;
		case "9":
			type = SiteRoleType.UNLICENSED;
			break;
		default:
			type = SiteRoleType.UNLICENSED;
			break;
		}
		return type;
	}
	
	
	/**
	 * Invokes an HTTP request to Workbook Download for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param workbookId
	 *			the ID of the target workbookId
	 * @param workbookName
	 *			the ID of the target workbookName
	 * @param path
	 *			the ID of the target path
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public Map<String, Object> invokeDownloadWorkbook(TableauCredentialsType credential, String siteId, String workbookId, String workbookName, String path) {
		
		//log.info(String.format("download Workbook on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/"+siteId + "/workbooks/" + workbookId + "/content";
		
		// scpecified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		
		//sets the header and makes a GET request
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, credential.getToken()).get(ClientResponse.class);
		
		MultivaluedMap<String, String> headers = clientResponse.getHeaders();
		List<String> maps = headers.get("Content-Disposition");
		
		String fileName = "";
		String ext = "";
		String workname = "";
		if(maps != null){
			for(String name : maps){
				log.info("values : " + name);
				int idx = name.indexOf("filename=");
				fileName = name.substring(idx + 9).replaceAll("\"", "");
				log.info("fileName : " + fileName);
			}
			String files[] = fileName.split("\\.");
			ext = files[1];
			workname = files[0];
			path += "/" + workbookName + "." + ext;
			try{
				File downlocalfile = new File(path);
				FileOutputStream fos = new FileOutputStream(downlocalfile);
				byte[] data = IOUtils.toByteArray(clientResponse.getEntityInputStream());
				fos.write(data);
				fos.flush();
				fos.close();
			}catch (IOException e) {
				// TODO: handle exception
				log.info("invokeDownloadWorkbook IOException : " + e.toString());
			}
		}
		
		log.info("clientResponse : " + clientResponse.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("path", path);
		map.put("fileName", fileName);
		ext = (ext == null ? "" : ext);
		workname = (workname == null ? "" : workname);
		map.put("ext", ext);
		map.put("workname", workname);
		
		return map;
	}
	
	/**
	 * Invokes an HTTP request to Workbook Download for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param datasourceId
	 *			the ID of the target datasourceId
	 * @param datasourceName
	 *			the ID of the target datasourceName
	 * @param path
	 *			the ID of the target path
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public Map<String, Object> invokeDownloadDatasource(TableauCredentialsType credential, String siteId, String datasourceId, String datasourceName, String path) {
		
		//log.info(String.format("download Workbook on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/"+siteId + "/datasources/" + datasourceId + "/content";
		
		// scpecified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		
		//sets the header and makes a GET request
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, credential.getToken()).get(ClientResponse.class);
		
		MultivaluedMap<String, String> headers = clientResponse.getHeaders();
		List<String> maps = headers.get("Content-Disposition");
		
		String fileName = "";
		String ext = "";
		String dataname = "";
		if(maps != null){
			for(String name : maps){
				log.info("values : " + name);
				int idx = name.indexOf("filename=");
				fileName = name.substring(idx + 9).replaceAll("\"", "");
				log.info("fileName : " + fileName);
			}
			String files[] = fileName.split("\\.");
			ext = files[1];
			dataname = files[0];
			path += "/" + datasourceName + "." + ext;
			try{
				File downlocalfile = new File(path);
				FileOutputStream fos = new FileOutputStream(downlocalfile);
				byte[] data = IOUtils.toByteArray(clientResponse.getEntityInputStream());
				fos.write(data);
				fos.flush();
				fos.close();
			}catch (IOException e) {
				// TODO: handle exception
				log.info("invokeDownloadDatasource IOException : " + e.toString());
			}
		}
		
		log.info("clientResponse : " + clientResponse.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("path", path);
		map.put("fileName", fileName);
		ext = (ext == null ? "" : ext);
		dataname = (dataname == null ? "" : dataname);
		map.put("ext", ext);
		map.put("dataname", dataname);
		
		return map;
	}
	
	/**
	 * Invokes an HTTP request to view image Download for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param viewId
	 *			the ID of the target viewId
	 * @param workbookName
	 *			the ID of the target workbookName
	 * @param path
	 *			the ID of the target path
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public Map<String, Object> invokeDownloadViewsImage(TableauCredentialsType credential, String siteId, String viewId, String workbookName, String path) {
		
		//log.info(String.format("download view image on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/"+siteId + "/views/" + viewId + "/image";
		
		// scpecified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		
		//sets the header and makes a GET request
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, credential.getToken()).get(ClientResponse.class);
		
		MultivaluedMap<String, String> headers = clientResponse.getHeaders();
		
		String fileName = workbookName + ".png";
		path += "/" + fileName;
		try{
			File downlocalfile = new File(path);
			FileOutputStream fos = new FileOutputStream(downlocalfile);
			byte[] data = IOUtils.toByteArray(clientResponse.getEntityInputStream());
			fos.write(data);
			fos.flush();
			fos.close();
		}catch (IOException e) {
			// TODO: handle exception
			log.info("invokeDownloadViewsImage IOException : " + e.toString());
		}
		
		log.info("clientResponse : " + clientResponse.toString());
		Map<String, Object> image = new HashMap<String, Object>();
		image.put("path", path);
		image.put("fileName", fileName);
		image.put("ext", "png");
		image.put("workname", workbookName);
		
		return image;
	}

	/**
	 * Invokes an HTTP request to view image Download for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param viewId
	 *			the ID of the target viewId
	 * @param workbookName
	 *			the ID of the target workbookName
	 * @param path
	 *			the ID of the target path
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public int invokeDownloadViewsImage(TableauCredentialsType credential, String siteId, String viewId, FileModel file) {
		
		//log.info(String.format("download view image on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/"+siteId + "/views/" + viewId + "/image";
		
		// scpecified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		
		//sets the header and makes a GET request
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, credential.getToken()).get(ClientResponse.class);
		
		file.setInputStream(clientResponse.getEntityInputStream());
		
		return clientResponse.getLength();
	}
	
	/**
	 * Invokes an HTTP request to preview image Download for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param workbookId
	 *			the ID of the target workbookId
	 * @param viewId
	 *			the ID of the target viewId
	 * @param workbookName
	 *			the ID of the target workbookName
	 * @param path
	 *			the ID of the target path
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public Map<String, Object> invokeDownloadPreViewsImage(TableauCredentialsType credential, String siteId, String workbookId, String viewId, String workbookName, String path) {
		
		//log.info(String.format("download preview image on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/"+siteId + "/workbooks/" + workbookId + "/views/" + viewId + "/previewImage";
		
		// scpecified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		
		//sets the header and makes a GET request
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, credential.getToken()).get(ClientResponse.class);
		
		MultivaluedMap<String, String> headers = clientResponse.getHeaders();
		
		//String fileName = workbookName + ".png";
		String fileName = workbookId +"_" + viewId + ".png";
		path += "/" + fileName;
		log.info("invokeDownloadPreViewsImage : " + path);
		try{
			File downlocalfile = new File(path);
			FileOutputStream fos = new FileOutputStream(downlocalfile);
			byte[] data = IOUtils.toByteArray(clientResponse.getEntityInputStream());
			fos.write(data);
			fos.flush();
			fos.close();
		}catch (IOException e) {
			// TODO: handle exception
			log.info("invokeDownloadPreViewsImage IOException : " + e.toString());
		}
		
		log.info("clientResponse : " + clientResponse.toString());
		Map<String, Object> image = new HashMap<String, Object>();
		image.put("path", path);
		image.put("fileName", fileName);
		image.put("ext", "png");
		image.put("workname", workbookName);
		
		return image;
	}

	/**
	 * Invokes an HTTP request to preview image Download for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param workbookId
	 *			the ID of the target workbookId
	 * @param viewId
	 *			the ID of the target viewId
	 * @param workbookName
	 *			the ID of the target workbookName
	 * @param path
	 *			the ID of the target path
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public InputStream invokeDownloadPreViewsImage(TableauCredentialsType credential, String siteId, String workbookId, String viewId) {
		
		//log.info(String.format("download preview image on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/"+siteId + "/workbooks/" + workbookId + "/views/" + viewId + "/previewImage";
		
		// scpecified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		
		//sets the header and makes a GET request
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, credential.getToken()).get(ClientResponse.class);
		
		return clientResponse.getEntityInputStream();
	}
	
	/**
	 * Invokes an HTTP request to preview image Download for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param workbookId
	 *			the ID of the target workbookId
	 * @param workbookName
	 *			the ID of the target workbookName
	 * @param path
	 *			the ID of the target path
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public Map<String, Object> invokeDownloadWorkbookPreViewsImage(TableauCredentialsType credential, String siteId, String workbookId, String workbookName, String path) {
		
		//log.info(String.format("download preview image on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/"+siteId + "/workbooks/" + workbookId + "/previewImage";
		
		// scpecified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		
		//sets the header and makes a GET request
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, credential.getToken()).get(ClientResponse.class);
		
		MultivaluedMap<String, String> headers = clientResponse.getHeaders();
		
		String fileName = workbookName + ".png";
		path += "/" + fileName;
		log.info("invokeDownloadWorkbookPreViewsImage : " + path);
		try{
			File downlocalfile = new File(path);
			FileOutputStream fos = new FileOutputStream(downlocalfile);
			byte[] data = IOUtils.toByteArray(clientResponse.getEntityInputStream());
			fos.write(data);
			fos.flush();
			fos.close();
		}catch (IOException e) {
			// TODO: handle exception
			log.info("invokeDownloadWorkbookPreViewsImage IOException : " + e.toString());
		}
		
		log.info("clientResponse : " + clientResponse.toString());
		Map<String, Object> image = new HashMap<String, Object>();
		image.put("path", path);
		image.put("fileName", fileName);
		image.put("ext", "png");
		image.put("workname", workbookName);
		
		return image;
	}
	
	/**
	 * Invokes an HTTP request to preview image Download for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param workbookId
	 *			the ID of the target workbookId
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public int invokeDownloadWorkbookPreViewsImage(TableauCredentialsType credential, String siteId, String workbookId, FileModel file) {
		
		//log.info(String.format("download preview image on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/"+siteId + "/workbooks/" + workbookId + "/previewImage";
		
		// scpecified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		
		//sets the header and makes a GET request
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, credential.getToken()).get(ClientResponse.class);
		
		file.setInputStream(clientResponse.getEntityInputStream());
		//file.setFileSize(clientResponse.getLength());
		
		return clientResponse.getLength();
	}
	
	/**
	 * Invokes an HTTP request to query for the updatedatasourceConnection for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param datasourceId
	 *			the ID of the target datasourceId
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public ConnectionType invokeUpdateDatasourceConnection(TableauCredentialsType credential, String siteId, String datasourceId,
			String connectionId, String serverAddr, String port, String userName, String password, String type, boolean embedPassword ) {
		
		//log.info(String.format("update datasource Connection on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/" + siteId + "/datasources/" + datasourceId + "/connections/" + connectionId;
		
		//creates the payload reqired to authenticity to server
		TsRequest payload = createPayloadForUpdateDatasourceConnection(serverAddr, port, userName, password, type, embedPassword);
		
		// Makes a POST request with the authenticity token
		TsResponse response = put(url, credential.getToken(), payload);
		
		// Verifies that the response has a workbooks element
		if (response.getConnection() != null) {
			//log.info("update datasource Connections is successful!");
			
			return response.getConnection();
		}
		
		// No workbooks were found
		return null;
	}
	
	/**
	 * Invokes an HTTP request to query for the list of jobs for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public BackgroundJobListType invokeQueryJobs(TableauCredentialsType credential, String siteId) {
		
		//log.info(String.format("Querying users on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/" + siteId + "/jobs";
		
		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());
		
		// Verifies that the response has a workbooks element
		if (response.getBackgroundJobs() != null) {
			//log.info("Query backgroundJobs is successful!");
			
			return response.getBackgroundJobs();
		}
		
		// No jobs were found
		return null;
	}

	/**
	 * Invokes an HTTP request to query for the list of jobs for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param jobId
	 *			the ID of the target jobId
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public BackgroundJobType invokeQueryJob(TableauCredentialsType credential, String siteId, String jobId) {
		
		//log.info(String.format("Querying users on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/" + siteId + "/jobs/" + jobId;
		
		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());
		
		// Verifies that the response has a job element
		if (response.getBackgroundJob() != null) {
			//log.info("Query backgroundJob is successful!");
			
			return response.getBackgroundJob();
		}
		
		// No job were found
		return null;
	}
	
	/**
	 * Invokes an HTTP request to create Schedule for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param datasourceId
	 *			the ID of the target datasourceId
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public ScheduleType invokeCreateSchedule(TableauCredentialsType credential, String siteId, String datasourceId, 
			String connectionId, String serverAddr, String port, String userName, String password, String type, boolean embedPassword) {
		
		//log.info(String.format("Create Schedule on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/schedules";
		
		//creates the payload reqired to authenticity to server
		TsRequest payload = createPayloadForCreateSchedule(serverAddr, port, userName, password, type, embedPassword);
		
		// Makes a POST request with the authenticity token
		TsResponse response = put(url, credential.getToken(), payload);
		
		// Verifies that the response has a Schedule element
		if (response.getSchedule() != null) {
			//log.info("create Schedule is successful!");
			
			return response.getSchedule();
		}
		
		// No Schedule were found
		return null;
	}
	
	/**
	 * Invokes an HTTP request to query for the list of Schedules for which the
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param jobId
	 *			the ID of the target jobId
	 * @return a list of workbooks if the query succeeded, otherwise <code>null</code>
	 */
	public ScheduleListType invokeQuerySchedules(TableauCredentialsType credential, String siteId, int pageSize, int pageNumber) {
		
		//log.info(String.format("Querying Schedules on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/schedules?pageSize=" + pageSize + "&pageNumber=" + pageNumber;
	
//		전상현 - 위에꺼 아래처럼 바꿀것
//		String url = getUriBuilder(PUBLISH_WORKBOOK)
//				.queryParam("pageSize", pageSize)
//				.queryParam("pageNumber", pageNumber)
//				.build(siteId, fileUpload.getUploadSessionId()).toString();
		
		// Makes a GET request with the authenticity token
		TsResponse response = get(url, credential.getToken());
		
		// Verifies that the response has a Schedules element
		if (response.getSchedules() != null) {
			//log.info("Query Schedules is successful!");
			
			return response.getSchedules();
		}
		
		// No Schedules were found
		return null;
	}
	
	public TaskType invokeAddWorkbookToSchedule(TableauCredentialsType credential, String siteId, String workbookId, String scheduleId) {
		
		//log.info(String.format("invokeAddWorkbookToSchedule on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/" + siteId + "/schdules/" + scheduleId + "/workbooks";
		
		//creates the payload reqired to authenticity to server
		TsRequest payload = createPayloadForAddWorkbookToSchedule(workbookId);
		
		// Makes a POST request with the authenticity token
		TsResponse response = put(url, credential.getToken(), payload);
		
		// Verifies that the response has a Task element
		if (response.getTask() != null) {
			//log.info("invokeAddWorkbookToSchedule is successful!");
			
			return response.getTask();
		}
		
		// No Task were found
		return null;
	}
	
	public TaskType invokeAddDatasourceToSchedule(TableauCredentialsType credential, String siteId, String datasourceId, String scheduleId) {
		
		//log.info(String.format("invokeAddDatasourceToSchedule on site '%s'.", siteId));
		
		String url = tableauApiUrl + "/sites/" + siteId + "/schdules/" + scheduleId + "/datasources";
							
		//creates the payload reqired to authenticity to server
		TsRequest payload = createPayloadForAddDatasourceToSchedule(datasourceId);
		
		// Makes a POST request with the authenticity token
		TsResponse response = put(url, credential.getToken(), payload);
		
		// Verifies that the response has a Task element
		if (response.getTask() != null) {
			//log.info("invokeAddDatasourceToSchedule is successful!");
			
			return response.getTask();
		}
		
		// No Task were found
		return null;
	}
	
	/**
	 * Invokes an HTTP request to sign in to the server.
	 *
	 * @param requestPayload
	 *			the payload containing the username and password to authenticate
	 * @return the credential if authentication was successful, otherwise
	 *		 <code>null</code>
	 */
	public TableauCredentialsType simpleSignIn() {
		return invokeSignIn(tableauUsername, tableauUserpw, tableauDefaultContentUrl);
	}
	public TableauCredentialsType invokeSignIn(String username, String password, String contentUrl) {
		String url = getUrl(SIGN_IN);
		
		TsRequest payload = createPayloadForSignin(username, password, contentUrl);

		// Makes a POST request with no credential
		TsResponse response = post(url, null, payload);

		// Verifies that the response has a credentials element
		if (response.getCredentials() != null) {
			log.info("#SignIn is successful!");
			return response.getCredentials();
		} else {
			log.info("#SignIn in is failed!");
		}

		// No credential were received
		return null;
	}

	/**
	 * Invokes an HTTP request to sign out of the Server.
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 */
	public void invokeSignOut(TableauCredentialsType credential) {

		//log.info("Signing out of Tableau Server");

		String url = getUrl(SIGN_OUT);

		// Creates the HTTP client object and makes the HTTP request to the
		// specified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);

		// Makes a POST request with the payload and credential
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, credential.getToken()).post(
				ClientResponse.class);

		if (clientResponse.getStatus() == Status.NO_CONTENT.getStatusCode()) {
			//log.info("Successfully signed out of Tableau Server");
		} else {
			//log.error("Failed to sign out of Tableau Server");
		}
	}

	private TsRequest createPayloadForCreatingProject(String parentProjectId, String projectName, String projectDescription) {
		TsRequest requestPayload = m_objectFactory.createTsRequest();
		ProjectType project = m_objectFactory.createProjectType();
		//parentProjectId는 optional이다. 입력값이 없으면 디펄트로 최상위프로젝트로 생성된다.
		if (parentProjectId != null) {
			project.setParentProjectId(parentProjectId);
		}
		project.setName(projectName);
		project.setDescription(projectDescription);

		requestPayload.setProject(project);
		return requestPayload;
	}
	
	private TsRequest createPayloadForUpdateProject(String newParentProjectId, String newProjectName, String newProjectDescription) {
		TsRequest requestPayload = m_objectFactory.createTsRequest();
		ProjectType project = m_objectFactory.createProjectType();
		if (newParentProjectId != null) {
			project.setParentProjectId(newParentProjectId);
		}
		project.setName(newProjectName);
		project.setDescription(newProjectDescription);

		requestPayload.setProject(project);
		return requestPayload;
	}		
	
	/**
	 * Creates the request payload used to add permissions for a workbook.
	 *
	 * @param workbookId
	 *			the ID of the workbook the permissions payload applies to
	 * @param granteeCapabilities
	 *			the list of capabilities for the payload
	 * @return the request payload
	 */
	private TsRequest createPayloadForAddingWorkbookPermissions(String workbookId,
			List<GranteeCapabilitiesType> granteeCapabilities) {
		// Creates the parent tsRequest element
		TsRequest requestPayload = m_objectFactory.createTsRequest();

		// Creates the permissions element
		PermissionsType permissions = m_objectFactory.createPermissionsType();

		// Creates the workbook and set the workbook ID
		WorkbookType workbook = m_objectFactory.createWorkbookType();
		workbook.setId(workbookId);

		// Sets the workbook element and capabilities element
		permissions.setWorkbook(workbook);
		permissions.getGranteeCapabilities().addAll(granteeCapabilities);

		// Adds the permissions element to the request payload
		requestPayload.setPermissions(permissions);

		return requestPayload;
	}

	/**
	 * Creates the request payload used to create a group.
	 *
	 * @param groupName
	 *			the name for the new group
	 * @return the request payload
	 */
	private TsRequest createPayloadForCreatingGroup(String groupName) {
		// Creates the parent tsRequest element
		TsRequest requestPayload = m_objectFactory.createTsRequest();

		// Creates group element
		GroupType group = m_objectFactory.createGroupType();

		// Sets the group name
		group.setName(groupName);

		// Adds the group element to the request payload
		requestPayload.setGroup(group);

		return requestPayload;
	}

	/**
	 * Creates the request payload used to sign in to the server.
	 *
	 * @param username
	 *			the username of the user to authenticate
	 * @param password
	 *			the password of the user to authenticate
	 * @param contentUrl
	 *			the content URL for the site to authenticate to
	 * @return the request payload
	 */
	private TsRequest createPayloadForSignin(String username, String password, String contentUrl) {
		// Creates the parent tsRequest element
		TsRequest requestPayload = m_objectFactory.createTsRequest();

		// Creates the credentials element and site element
		TableauCredentialsType signInCredentials = m_objectFactory.createTableauCredentialsType();
		SiteType site = m_objectFactory.createSiteType();

		// Sets the content URL of the site to sign in to
		site.setContentUrl(contentUrl);
		signInCredentials.setSite(site);

		// Sets the name and password of the user to authenticate
		signInCredentials.setName(username);
		signInCredentials.setPassword(password);

		// Adds the credential element to the request payload
		requestPayload.setCredentials(signInCredentials);

		return requestPayload;
	}



	/**
	 * Creates the request payload used to publish a workbook.
	 *
	 * @param workbookName
	 *			the name for the new workbook
	 * @param projectId
	 *			the ID of the project to publish to
	 * @return the request payload
	 */
	private TsRequest createPayloadToPublishWorkbook(String workbookName, String projectId) {
		// Creates the parent tsRequest element
		TsRequest requestPayload = m_objectFactory.createTsRequest();

		// Creates the workbook element
		WorkbookType workbook = m_objectFactory.createWorkbookType();

		// Creates the project element
		ProjectType project = m_objectFactory.createProjectType();

		// Sets the target project ID
		project.setId(projectId);

		// Sets the workbook name
		workbook.setName(workbookName);

		// Sets the project
		workbook.setProject(project);

		// Adds the workbook element to the request payload
		requestPayload.setWorkbook(workbook);

		return requestPayload;
	}

	/**
	 * Creates the request payload used to publish a datasource.
	 *
	 * @param datasourceName
	 *			the name for the new datasource
	 * @param projectId
	 *			the ID of the project to publish to
	 * @return the request payload
	 */
	private TsRequest createPayloadToPublishDatasource(String datasourceName, String projectId) {
		// Creates the parent tsRequest element
		TsRequest requestPayload = m_objectFactory.createTsRequest();

		// Creates the datasource element
		DataSourceType datasource = m_objectFactory.createDataSourceType();

		// Creates the project element
		ProjectType project = m_objectFactory.createProjectType();

		//creates the connectionCredentials element
		ConnectionCredentialsType ConnectionCredentials = m_objectFactory.createConnectionCredentialsType(); 
		
		// Sets the target project ID
		project.setId(projectId);

		// Sets the datasource name
		datasource.setName(datasourceName);

		// Sets the project
		datasource.setProject(project);

		// Sets the target user name
		ConnectionCredentials.setName("");

		// Sets the target user pw
		ConnectionCredentials.setPassword("");
		ConnectionCredentials.setEmbed(true);
		ConnectionCredentials.setOAuth(true);
		
		// Sets the ConnectionCredentials
		// datasource.setConnectionCredentials(ConnectionCredentials);
		
		// Adds the datasource element to the request payload
		requestPayload.setDatasource(datasource);

		return requestPayload;
	}
	
	/**
	 * Creates the request payload used to update a datasourceConnection.
	 *
	 * @param datasourceName
	 *			the name for the new datasource
	 * @param projectId
	 *			the ID of the project to publish to
	 * @return the request payload
	 */				  
	private TsRequest createPayloadForUpdateDatasourceConnection(String serverAddr, String port, String userName, String password, String type, boolean embedPassword) {
		// Creates the parent tsRequest element
		TsRequest requestPayload = m_objectFactory.createTsRequest();

		// Creates the connection element
		ConnectionType connection = m_objectFactory.createConnectionType();

		connection.setServerAddress(serverAddr);
		
		connection.setServerPort(new BigInteger(port));
		
		connection.setUserName(userName);
		
		connection.setPassword(password);
		
		connection.setType(type);
		
		// Adds the connection element to the request payload
		requestPayload.setConnection(connection);

		return requestPayload;
	}
	
	/**
	 * Creates a GET request using the specified URL.
	 *
	 * @param url
	 *			the URL to send the request to
	 * @param authToken
	 *			the authentication token to use for this request
	 * @return the response from the request
	 */
	private TsResponse get(String url, String authToken) {
		// Creates the HTTP client object and makes the HTTP request to the
		// specified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);

		// Sets the header and makes a GET request
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, authToken).get(ClientResponse.class);

		// Parses the response from the server into an XML string
		String responseXML = clientResponse.getEntity(String.class);

		log.info("#get Response: {}", responseXML);

		// Returns the unmarshalled XML response
		return unmarshalResponse(responseXML);
	}

	/**
	 * Invokes an HTTP request to append to target file upload on target site.
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param uploadSessionId
	 *			the session ID of the target file upload
	 * @param chunk
	 *			the chunk of data to append to target file upload
	 * @param numChunkBytes
	 *			the number of bytes in the chunk of data
	 */
	private void invokeAppendFileUpload(TableauCredentialsType credential, String siteId, String uploadSessionId,
			byte[] chunk, int numChunkBytes) {

		log.info("Appending to file upload '{}'.", uploadSessionId);
		String url = getUrl(APPEND_FILE_UPLOAD,siteId, uploadSessionId);

		// Writes the chunk of data to a temporary file
		try (FileOutputStream outputStream = new FileOutputStream("appendFileUpload.temp")) {
			outputStream.write(chunk, 0, numChunkBytes);
		} catch (IOException e) {
			throw new IllegalStateException("Failed to create temporary file to append to file upload");
		}

		// Makes a multipart PUT request with specified credential's
		// authenticity token and payload
		BodyPart filePart = new FileDataBodyPart("tableau_file", new File("appendFileUpload.temp"),
				MediaType.APPLICATION_OCTET_STREAM_TYPE);
		putMultipart(url, credential.getToken(), null, filePart);
	}

	/**
	 * Invokes an HTTP request to create a new file upload on target site.
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @return the file upload if created successfully, otherwise
	 *		 <code>null</code>
	 */
	private FileUploadType invokeInitiateFileUpload(TableauCredentialsType credential, String siteId) {

		//log.info(String.format("Initia projects on site '%s'.", siteId));

		String url = getUrl(INITIATE_FILE_UPLOAD,siteId);

		// Make a POST request with the authenticity token
		TsResponse response = post(url, credential.getToken());

		// Verifies that the response has a file upload element
		if (response.getFileUpload() != null) {
			//log.info("Initiate file upload is successful!");

			return response.getFileUpload();
		}

		// No file upload is found
		return null;
	}

	/**
	 * Initiates a file upload session to get an upload session id. This upload
	 * session id is used to upload the workbook in chunks. After the workbook
	 * has been uploaded, publish the workbook using the upload session id.
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param requestPayload
	 *			the XML payload containing the workbook attributes used to
	 *			publish the workbook
	 * @param workbookFile
	 *			the workbook file to publish
	 * @return the workbook if it was published successfully, otherwise
	 *		 <code>null</code>
	 */
	private WorkbookType invokePublishWorkbookChunked(TableauCredentialsType credential, String siteId,
			String projectId, String workbookName, File workbookFile, String ext, boolean overwrite) {

		// Initiates a new file upload to get an upload session id
		FileUploadType fileUpload = invokeInitiateFileUpload(credential, siteId);

		// Builds the URL with the upload session id and workbook type
		String url = getUriBuilder(PUBLISH_WORKBOOK)
				.queryParam("uploadSessionId", fileUpload.getUploadSessionId())
				.queryParam("workbookType", Files.getFileExtension(workbookFile.getName()))
				.build(siteId, fileUpload.getUploadSessionId()).toString();

		// Creates a buffer to read 100KB at a time
		byte[] buffer = new byte[100000];
		int numReadBytes = 0;

		// Reads the specified workbook and appends each chunk to the file upload
		try (FileInputStream inputStream = new FileInputStream(workbookFile.getAbsolutePath())) {
			while ((numReadBytes = inputStream.read(buffer)) != -1) {
				invokeAppendFileUpload(credential, siteId, fileUpload.getUploadSessionId(), buffer, numReadBytes);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Failed to read the workbook file.");
		}

		// Creates the payload to publish the workbook
		TsRequest payload = createPayloadToPublishWorkbook(workbookName, projectId);

		// Makes a multipart POST request with specified credential's
		// authenticity token and payload
		TsResponse response = postMultipart(url, credential.getToken(), payload, null);

		// Verifies that the response has a workbook element
		if (response.getWorkbook() != null) {
			//log.info("Publish workbook is successful!");

			return response.getWorkbook();
		} else {
			log.info("publish workook error code 		: {}",  response.getError().getCode().toString());
			log.info("publish workook error detail 		: {}",  response.getError().getDetail().toString());
			log.info("publish workook error summary 	: {}",  response.getError().getSummary().toString());			
		}

		// No workbook was published
		return null;
	}

	/**
	 * Invokes an HTTP request to publish a workbook to target site including
	 * the workbook in the request.
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param requestPayload
	 *			the XML payload containing the workbook attributes used to
	 *			publish the workbook
	 * @param workbookFile
	 *			the workbook file to publish
	 * @return the workbook if it was published successfully, otherwise
	 *		 <code>null</code>
	 */
	private WorkbookType invokePublishWorkbookSimple(TableauCredentialsType credential, String siteId,
			String projectId, String workbookName, File workbookFile,String ext, boolean overwrite) {
		
		String url = getUrl(PUBLISH_WORKBOOK,siteId);

		// Creates the payload to publish the workbook
		TsRequest payload = createPayloadToPublishWorkbook(workbookName, projectId);

		// Makes a multipart POST request with specified credential's
		// authenticity token and payload
		BodyPart filePart = new FileDataBodyPart("tableau_workbook", workbookFile,
				MediaType.APPLICATION_OCTET_STREAM_TYPE);
		TsResponse response = postMultipart(url, credential.getToken(), payload, filePart);

		// Verifies that the response has a workbook element
		if (response.getWorkbook() != null) {
			//log.info("Publish workbook is successful!");

			return response.getWorkbook();
		}

		// No workbook was published
		return null;
	}

	/**
	 * Initiates a file upload session to get an upload session id. This upload
	 * session id is used to upload the datasource in chunks. After the datasource
	 * has been uploaded, publish the datasource using the upload session id.
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param requestPayload
	 *			the XML payload containing the workbook attributes used to
	 *			publish the datasource
	 * @param datasourceFile
	 *			the datasource file to publish
	 * @return the datasource if it was published successfully, otherwise
	 *		 <code>null</code>
	 */
	private DataSourceType invokePublishDatasourceChunked(TableauCredentialsType credential, String siteId,
			String projectId, String datasourceName, File datasourceFile, String ext, boolean overwrite) {
		
		// Initiates a new file upload to get an upload session id
		FileUploadType fileUpload = invokeInitiateFileUpload(credential, siteId);
		
		// Builds the URL with the upload session id and workbook type
		UriBuilder builder = Operation.PUBLISH_WORKBOOK.getUriBuilder()
				.queryParam("uploadSessionId", fileUpload.getUploadSessionId());
		//.queryParam("workbookType", Files.getFileExtension(workbookFile.getName()));
		//String url = builder.build(siteId, fileUpload.getUploadSessionId()).toString();
		String url = tableauApiUrl + "/sites/" + siteId + "/datasources?uploadSessionId="
				+ fileUpload.getUploadSessionId() + "&datasourceType=" + ext + "&overwrite=" + overwrite;
		
		// Creates a buffer to read 100KB at a time
		byte[] buffer = new byte[100000];
		int numReadBytes = 0;
		
		// Reads the specified workbook and appends each chunk to the file upload
		try (FileInputStream inputStream = new FileInputStream(datasourceFile.getAbsolutePath())) {
			while ((numReadBytes = inputStream.read(buffer)) != -1) {
				invokeAppendFileUpload(credential, siteId, fileUpload.getUploadSessionId(), buffer, numReadBytes);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Failed to read the datasource file.");
		}
		
		// Creates the payload to publish the datasource
		TsRequest payload = createPayloadToPublishDatasource(datasourceName, projectId);
		
		// Makes a multipart POST request with specified credential's
		// authenticity token and payload
		TsResponse response = postMultipart(url, credential.getToken(), payload, null);
		
		// Verifies that the response has a datasource element
		if (response.getDatasource() != null) {
			//log.info("Publish datasource is successful!");
			
			return response.getDatasource();
		} else {
			log.info("publish datasource error code 	: {}", response.getError().getCode().toString());
			log.info("publish datasource error detail 	: {}", response.getError().getDetail().toString());
			log.info("publish datasource error summary 	: {}", response.getError().getSummary().toString());
		}
		
		// No datasource was published
		return null;
	}
	
	/**
	 * Invokes an HTTP request to publish a datasource to target site including
	 * the workbook in the request.
	 *
	 * @param credential
	 *			the credential containing the authentication token to use for
	 *			this request
	 * @param siteId
	 *			the ID of the target site
	 * @param requestPayload
	 *			the XML payload containing the datasource attributes used to
	 *			publish the workbook
	 * @param datasourceFile
	 *			the datasource file to publish
	 * @return the datasource if it was published successfully, otherwise
	 *		 <code>null</code>
	 */
	private DataSourceType invokePublishDatasourceSimple(TableauCredentialsType credential, String siteId,
			String projectId, String datasourceName, File datasourceFile, String ext, boolean overwrite) {
		
		String url = Operation.PUBLISH_WORKBOOK.getUrl(siteId);
		
		// Creates the payload to publish the workbook
		TsRequest payload = createPayloadToPublishDatasource(datasourceName, projectId);
		
		// Makes a multipart POST request with specified credential's
		// authenticity token and payload
		BodyPart filePart = new FileDataBodyPart("tableau_datasource", datasourceFile,
				MediaType.APPLICATION_OCTET_STREAM_TYPE);
		TsResponse response = postMultipart(url, credential.getToken(), payload, filePart);
		
		// Verifies that the response has a datasource element
		if (response.getDatasource() != null) {
			//log.info("Publish datasource is successful!");
			
			return response.getDatasource();
		}
		
		// No datasource was published
		return null;
	}
	
	private TsRequest createPayloadForCreateSchedule(String serverAddr, String port, String scheduleName,
			String priority, String type, boolean embedPassword) {
		// Creates the parent tsRequest element
		TsRequest requestPayload = m_objectFactory.createTsRequest();

		// Creates the schedule element
		ScheduleType schedule = m_objectFactory.createScheduleType();

		//필요시 추가
		
		// Adds the schedule element to the request payload
		requestPayload.setSchedule(schedule);

		return requestPayload;
	}
	
	private TsRequest createPayloadForAddWorkbookToSchedule(String workbookId) {
		// Creates the parent tsRequest element
		TsRequest requestPayload = m_objectFactory.createTsRequest();

		// Creates the schedule element
		TaskType task = m_objectFactory.createTaskType();

		TaskExtractRefreshType extractRefresh = m_objectFactory.createTaskExtractRefreshType();
		
		//create Workbook element
		WorkbookType workbook = m_objectFactory.createWorkbookType();
		
		workbook.setId(workbookId);
		
		extractRefresh.setWorkbook(workbook);
		
		task.setExtractRefresh(extractRefresh);
		
		// Adds the schedule element to the request payload
		requestPayload.setTask(task);

		return requestPayload;
	}
	
	private TsRequest createPayloadForAddDatasourceToSchedule(String datasourceId) {
		// Creates the parent tsRequest element
		TsRequest requestPayload = m_objectFactory.createTsRequest();

		// Creates the schedule element
		TaskType task = m_objectFactory.createTaskType();

		TaskExtractRefreshType extractRefresh = m_objectFactory.createTaskExtractRefreshType();
		
		//create datasource element
		DataSourceType datasource = m_objectFactory.createDataSourceType();
		
		datasource.setId(datasourceId);
		
		extractRefresh.setDatasource(datasource);
		
		task.setExtractRefresh(extractRefresh);
		
		// Adds the schedule element to the request payload
		requestPayload.setTask(task);

		return requestPayload;
	}

	/**
	 * Creates a POST request using the specified URL without a payload.
	 *
	 * @param url
	 *			the URL to send the request to
	 * @param authToken
	 *			the authentication token to use for this request
	 * @return the response from the request
	 */
	private TsResponse post(String url, String authToken) {

		// Creates the HTTP client object and makes the HTTP request to the
		// specified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);

		// Makes a POST request with the payload and credential
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, authToken).post(ClientResponse.class);

		// Parses the response from the server into an XML string
		String responseXML = clientResponse.getEntity(String.class);

		//log.debug("Response: \n" + responseXML);

		// Returns the unmarshalled XML response
		return unmarshalResponse(responseXML);
	}

	/**
	 * Creates a POST request using the specified URL with the specified payload.
	 *
	 * @param url
	 *			the URL to send the request to
	 * @param authToken
	 *			the authentication token to use for this request
	 * @param requestPayload
	 *			the payload to send with the request
	 * @return the response from the request
	 */
	private TsResponse post(String url, String authToken, TsRequest requestPayload) {
		// Creates an instance of StringWriter to hold the XML for the request
		StringWriter writer = new StringWriter();

		// Marshals the TsRequest object into XML format if it is not null
		if (requestPayload != null) {
			try {
				s_jaxbMarshaller.marshal(requestPayload, writer);
			} catch (JAXBException ex) {
				//log.error("There was a problem marshalling the payload");
			}
		}

		// Converts the XML into a string
		String payload = writer.toString();

		//log.debug("Input payload: \n" + payload);

		// Creates the HTTP client object and makes the HTTP request to the
		// specified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);

		// Makes a POST request with the payload and credential
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, authToken).type(MediaType.TEXT_XML_TYPE).post(ClientResponse.class, payload);

		// Parses the response from the server into an XML string
		String responseXML = clientResponse.getEntity(String.class);

		log.debug("#post Response: {}", responseXML);

		// Returns the unmarshalled XML response
		return unmarshalResponse(responseXML);
	}

	/**
	 * Creates a multipart POST request using the specified URL with the specified payload.
	 *
	 * @param url
	 *			the URL to send the request to
	 * @param authToken
	 *			the authentication token to use for this request
	 * @param requestPayload
	 *			the payload to send with the request
	 * @param file
	 *			the file to send with the request
	 * @return the response from the request
	 */
	private TsResponse postMultipart(String url, String authToken, TsRequest requestPayload, BodyPart filePart) {
		// Creates an instance of StringWriter to hold the XML for the request
		StringWriter writer = new StringWriter();

		// Marshals the TsRequest object into XML format if it is not null
		if (requestPayload != null) {
			try {
				s_jaxbMarshaller.marshal(requestPayload, writer);
			} catch (JAXBException ex) {
				//log.error("There was a problem marshalling the payload");
			}
		}

		// Converts the XML into a string
		String payload = writer.toString();

		log.debug("Input payload: {}", payload);

		// Creates the XML request payload portion of the multipart request
		BodyPart payloadPart = new FormDataBodyPart(TABLEAU_PAYLOAD_NAME, payload);

		// Creates the multipart object and adds the file portion of the
		// multipart request to it
		MultiPart multipart = new MultiPart();
		multipart.bodyPart(payloadPart);

		if(filePart != null) {
			multipart.bodyPart(filePart);
		}

		// Creates the HTTP client object and makes the HTTP request to the
		// specified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);

		// Makes a multipart POST request with the multipart payload and
		// authenticity token
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, authToken)
				.type(MultiPartMediaTypes.createMixed()).post(ClientResponse.class, multipart);

		// Parses the response from the server into an XML string
		String responseXML = clientResponse.getEntity(String.class);

		log.debug("Response: {}", responseXML);

		// Returns the unmarshalled XML response
		return unmarshalResponse(responseXML);
	}

	/**
	 * Creates a PUT request using the specified URL with the specified payload.
	 *
	 * @param url
	 *			the URL to send the request to
	 * @param authToken
	 *			the authentication token to use for this request
	 * @param requestPayload
	 *			the payload to send with the request
	 * @return the response from the request
	 */
	private TsResponse put(String url, String authToken, TsRequest requestPayload) {
		// Creates an instance of StringWriter to hold the XML for the request
		StringWriter writer = new StringWriter();

		// Marshals the TsRequest object into XML format if it is not null
		if (requestPayload != null) {
			try {
				s_jaxbMarshaller.marshal(requestPayload, writer);
			} catch (JAXBException ex) {
				//log.error("There was a problem marshalling the payload");
			}
		}

		// Converts the XML into a string
		String payload = writer.toString();

		//log.debug("Input payload: \n" + payload);

		// Creates the HTTP client object and makes the HTTP request to the
		// specified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);

		// Makes a PUT request with the payload and credential
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, authToken)
				.type(MediaType.TEXT_XML_TYPE).put(ClientResponse.class, payload);

		// Parses the response from the server into an XML string
		String responseXML = clientResponse.getEntity(String.class);

		log.debug("#put Response: {}", responseXML);

		// Returns the unmarshalled XML response
		return unmarshalResponse(responseXML);
	}

	/**
	 * Creates a multipart PUT request using the specified URL with the
	 * specified payload.
	 *
	 * @param url
	 *			the URL to send the request to
	 * @param authToken
	 *			the authentication token to use for this request
	 * @param requestPayload
	 *			the payload to send with the request
	 * @param file
	 *			the file to send with the request
	 * @return the response from the request
	 */
	private TsResponse putMultipart(String url, String authToken, TsRequest requestPayload, BodyPart filePart) {
		// Creates an instance of StringWriter to hold the XML for the request
		StringWriter writer = new StringWriter();

		// Marshals the TsRequest object into XML format if it is not null
		if (requestPayload != null) {
			try {
				s_jaxbMarshaller.marshal(requestPayload, writer);
			} catch (JAXBException ex) {
				//log.error("There was a problem marshalling the payload");
			}
		}

		// Converts the XML into a string
		String payload = writer.toString();

		//log.debug("Input payload: \n" + payload);

		// Creates the XML request payload portion of the multipart request
		BodyPart payloadPart = new FormDataBodyPart(TABLEAU_PAYLOAD_NAME, payload);

		// Creates the multipart object and adds the file portion of the
		// multipart request to it
		MultiPart multipart = new MultiPart();
		multipart.bodyPart(payloadPart);

		if(filePart != null) {
			multipart.bodyPart(filePart);
		}

		// Creates the HTTP client object and makes the HTTP request to the
		// specified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);

		// Makes a multipart POST request with the multipart payload and
		// authenticity token
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, authToken)
				.type(MultiPartMediaTypes.createMixed()).put(ClientResponse.class, multipart);

		// Parses the response from the server into an XML string
		String responseXML = clientResponse.getEntity(String.class);

		// Returns the unmarshalled XML response
		return unmarshalResponse(responseXML);
	}
	
	/**
	 * Creates a DELETE request using the specified URL without a payload.
	 *
	 * @param url
	 *			the URL to send the request to
	 * @param authToken
	 *			the authentication token to use for this request
	 * @return the response from the request
	 */
	private TsResponse delete(String url, String authToken) {

		// Creates the HTTP client object and makes the HTTP request to the
		// specified URL
		Client client = Client.create();
		WebResource webResource = client.resource(url);

		// Makes a POST request with the payload and credential
		ClientResponse clientResponse = webResource.header(TABLEAU_AUTH_HEADER, authToken).delete(ClientResponse.class);

		if(clientResponse.getResponseStatus().getStatusCode() == 204) {
			TsResponse tsResponse = new TsResponse();
			ErrorType error = new ErrorType();
			error.setCode(BigInteger.valueOf(204));
			error.setDetail("success");
			tsResponse.setError(error);
			return tsResponse;
		}else {
			// Parses the response from the server into an XML string
			String responseXML = clientResponse.getEntity(String.class);
			log.debug("Response: {}", responseXML);
			
			TsResponse response = unmarshalResponse(responseXML);
			// Returns the unmarshalled XML response
			return unmarshalResponse(responseXML);
			
		}
	}

	/**
	 * Return the unmarshalled XML result, or an empty TsResponse if it can't be
	 * unmarshalled.
	 *
	 * @param responseXML
	 *			the XML string from the response
	 * @return the TsResponse of unmarshalled input
	 */
	private TsResponse unmarshalResponse(String responseXML) {
		TsResponse tsResponse = m_objectFactory.createTsResponse();
		try {
			// Creates a StringReader instance to store the response and then
			// unmarshals the response into a TsResponse object
			StringReader reader = new StringReader(responseXML);
			tsResponse = s_jaxbUnmarshaller.unmarshal(new StreamSource(reader), TsResponse.class).getValue();
		} catch (JAXBException e) {
			log.error("Failed to parse response from server due to:");
			log.error(e.getMessage());
		}

		return tsResponse;
	}
}
