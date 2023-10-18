//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.8-b130911.1802 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2020.02.03 �ð� 05:34:04 PM KST 
//


package com.bdp.ap.common.tableauapi.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;group ref="{http://tableau.com/api}paginatedResponseGroup"/>
 *         &lt;element name="backgroundJob" type="{http://tableau.com/api}backgroundJobType"/>
 *         &lt;element name="backgroundJobs" type="{http://tableau.com/api}backgroundJobListType"/>
 *         &lt;element name="column" type="{http://tableau.com/api}columnType"/>
 *         &lt;element name="connection" type="{http://tableau.com/api}connectionType"/>
 *         &lt;element name="connections" type="{http://tableau.com/api}connectionListType"/>
 *         &lt;element name="credentials" type="{http://tableau.com/api}tableauCredentialsType"/>
 *         &lt;element name="dataAlert" type="{http://tableau.com/api}dataAlertType"/>
 *         &lt;element name="dataAlerts" type="{http://tableau.com/api}dataAlertListType"/>
 *         &lt;element name="dataQualityWarning" type="{http://tableau.com/api}dataQualityWarningType"/>
 *         &lt;element name="dataQualityWarningList" type="{http://tableau.com/api}dataQualityWarningListType"/>
 *         &lt;element name="dataRole" type="{http://tableau.com/api}dataRoleType"/>
 *         &lt;element name="database" type="{http://tableau.com/api}databaseType"/>
 *         &lt;element name="datasource" type="{http://tableau.com/api}dataSourceType"/>
 *         &lt;element name="error" type="{http://tableau.com/api}errorType"/>
 *         &lt;element name="favorites" type="{http://tableau.com/api}favoriteListType"/>
 *         &lt;element name="fileUpload" type="{http://tableau.com/api}fileUploadType"/>
 *         &lt;element name="flow" type="{http://tableau.com/api}flowType"/>
 *         &lt;element name="group" type="{http://tableau.com/api}groupType"/>
 *         &lt;element name="job" type="{http://tableau.com/api}jobType"/>
 *         &lt;element name="metric" type="{http://tableau.com/api}metricType"/>
 *         &lt;element name="permissions" type="{http://tableau.com/api}permissionsType"/>
 *         &lt;element name="project" type="{http://tableau.com/api}projectType"/>
 *         &lt;element name="dataAlertsRecipient" type="{http://tableau.com/api}dataAlertsRecipientType"/>
 *         &lt;element name="dataAlertsRecipientList" type="{http://tableau.com/api}dataAlertsRecipientListType"/>
 *         &lt;element name="schedule" type="{http://tableau.com/api}scheduleType"/>
 *         &lt;element name="serverInfo" type="{http://tableau.com/api}serverInfo"/>
 *         &lt;element name="serverSettings" type="{http://tableau.com/api}serverSettings"/>
 *         &lt;element name="site" type="{http://tableau.com/api}siteType"/>
 *         &lt;element name="table" type="{http://tableau.com/api}tableType"/>
 *         &lt;element name="tags" type="{http://tableau.com/api}tagListType"/>
 *         &lt;element name="user" type="{http://tableau.com/api}userType"/>
 *         &lt;element name="view" type="{http://tableau.com/api}viewType"/>
 *         &lt;element name="views" type="{http://tableau.com/api}viewListType"/>
 *         &lt;element name="workbook" type="{http://tableau.com/api}workbookType"/>
 *         &lt;element name="subscription" type="{http://tableau.com/api}subscriptionType"/>
 *         &lt;element name="task" type="{http://tableau.com/api}taskType"/>
 *         &lt;element name="tasks" type="{http://tableau.com/api}taskListType"/>
 *         &lt;element name="warnings" type="{http://tableau.com/api}warningListType"/>
 *         &lt;element name="degradations" type="{http://tableau.com/api}degradationListType"/>
 *         &lt;element name="listFieldConcepts" type="{http://tableau.com/api}listFieldConceptsType"/>
 *         &lt;element name="fieldMatches" type="{http://tableau.com/api}fieldMatchListType"/>
 *         &lt;element name="valueMatches" type="{http://tableau.com/api}matchValuesResultType"/>
 *         &lt;element name="valueConceptCount" type="{http://tableau.com/api}valueConceptCountType"/>
 *         &lt;element name="listValueConcepts" type="{http://tableau.com/api}listValueConceptsType"/>
 *         &lt;element name="fieldConcept" type="{http://tableau.com/api}fieldConceptType"/>
 *         &lt;element name="getIndexingStatus" type="{http://tableau.com/api}indexingStatusType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pagination",
    "columns",
    "databases",
    "datasources",
    "extracts",
    "flows",
    "flowOutputSteps",
    "groups",
    "metrics",
    "projects",
    "revisions",
    "schedules",
    "sites",
    "tables",
    "users",
    "workbooks",
    "subscriptions",
    "backgroundJob",
    "backgroundJobs",
    "column",
    "connection",
    "connections",
    "credentials",
    "dataAlert",
    "dataAlerts",
    "dataQualityWarning",
    "dataQualityWarningList",
    "dataRole",
    "database",
    "datasource",
    "error",
    "favorites",
    "fileUpload",
    "flow",
    "group",
    "job",
    "metric",
    "permissions",
    "project",
    "dataAlertsRecipient",
    "dataAlertsRecipientList",
    "schedule",
    "serverInfo",
    "serverSettings",
    "site",
    "table",
    "tags",
    "user",
    "view",
    "views",
    "workbook",
    "subscription",
    "task",
    "tasks",
    "warnings",
    "degradations",
    "listFieldConcepts",
    "fieldMatches",
    "valueMatches",
    "valueConceptCount",
    "listValueConcepts",
    "fieldConcept",
    "getIndexingStatus"
})
@XmlRootElement(name = "tsResponse")
public class TsResponse {

    protected PaginationType pagination;
    protected ColumnListType columns;
    protected DatabaseListType databases;
    protected DataSourceListType datasources;
    protected ExtractListType extracts;
    protected FlowListType flows;
    protected FlowOutputStepListType flowOutputSteps;
    protected GroupListType groups;
    protected MetricListType metrics;
    protected ProjectListType projects;
    protected RevisionListType revisions;
    protected ScheduleListType schedules;
    protected SiteListType sites;
    protected TableListType tables;
    protected UserListType users;
    protected WorkbookListType workbooks;
    protected SubscriptionListType subscriptions;
    protected BackgroundJobType backgroundJob;
    protected BackgroundJobListType backgroundJobs;
    protected ColumnType column;
    protected ConnectionType connection;
    protected ConnectionListType connections;
    protected TableauCredentialsType credentials;
    protected DataAlertType dataAlert;
    protected DataAlertListType dataAlerts;
    protected DataQualityWarningType dataQualityWarning;
    protected DataQualityWarningListType dataQualityWarningList;
    protected DataRoleType dataRole;
    protected DatabaseType database;
    protected DataSourceType datasource;
    protected ErrorType error;
    protected FavoriteListType favorites;
    protected FileUploadType fileUpload;
    protected FlowType flow;
    protected GroupType group;
    protected JobType job;
    protected MetricType metric;
    protected PermissionsType permissions;
    protected ProjectType project;
    protected DataAlertsRecipientType dataAlertsRecipient;
    protected DataAlertsRecipientListType dataAlertsRecipientList;
    protected ScheduleType schedule;
    protected ServerInfo serverInfo;
    protected ServerSettings serverSettings;
    protected SiteType site;
    protected TableType table;
    protected TagListType tags;
    protected UserType user;
    protected ViewType view;
    protected ViewListType views;
    protected WorkbookType workbook;
    protected SubscriptionType subscription;
    protected TaskType task;
    protected TaskListType tasks;
    protected WarningListType warnings;
    protected DegradationListType degradations;
    protected ListFieldConceptsType listFieldConcepts;
    protected FieldMatchListType fieldMatches;
    protected MatchValuesResultType valueMatches;
    protected ValueConceptCountType valueConceptCount;
    protected ListValueConceptsType listValueConcepts;
    protected FieldConceptType fieldConcept;
    protected IndexingStatusType getIndexingStatus;

    /**
     * pagination �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link PaginationType }
     *     
     */
    public PaginationType getPagination() {
        return pagination;
    }

    /**
     * pagination �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link PaginationType }
     *     
     */
    public void setPagination(PaginationType value) {
        this.pagination = value;
    }

    /**
     * columns �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ColumnListType }
     *     
     */
    public ColumnListType getColumns() {
        return columns;
    }

    /**
     * columns �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ColumnListType }
     *     
     */
    public void setColumns(ColumnListType value) {
        this.columns = value;
    }

    /**
     * databases �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DatabaseListType }
     *     
     */
    public DatabaseListType getDatabases() {
        return databases;
    }

    /**
     * databases �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DatabaseListType }
     *     
     */
    public void setDatabases(DatabaseListType value) {
        this.databases = value;
    }

    /**
     * datasources �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DataSourceListType }
     *     
     */
    public DataSourceListType getDatasources() {
        return datasources;
    }

    /**
     * datasources �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSourceListType }
     *     
     */
    public void setDatasources(DataSourceListType value) {
        this.datasources = value;
    }

    /**
     * extracts �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ExtractListType }
     *     
     */
    public ExtractListType getExtracts() {
        return extracts;
    }

    /**
     * extracts �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtractListType }
     *     
     */
    public void setExtracts(ExtractListType value) {
        this.extracts = value;
    }

    /**
     * flows �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link FlowListType }
     *     
     */
    public FlowListType getFlows() {
        return flows;
    }

    /**
     * flows �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link FlowListType }
     *     
     */
    public void setFlows(FlowListType value) {
        this.flows = value;
    }

    /**
     * flowOutputSteps �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link FlowOutputStepListType }
     *     
     */
    public FlowOutputStepListType getFlowOutputSteps() {
        return flowOutputSteps;
    }

    /**
     * flowOutputSteps �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link FlowOutputStepListType }
     *     
     */
    public void setFlowOutputSteps(FlowOutputStepListType value) {
        this.flowOutputSteps = value;
    }

    /**
     * groups �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link GroupListType }
     *     
     */
    public GroupListType getGroups() {
        return groups;
    }

    /**
     * groups �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupListType }
     *     
     */
    public void setGroups(GroupListType value) {
        this.groups = value;
    }

    /**
     * metrics �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link MetricListType }
     *     
     */
    public MetricListType getMetrics() {
        return metrics;
    }

    /**
     * metrics �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link MetricListType }
     *     
     */
    public void setMetrics(MetricListType value) {
        this.metrics = value;
    }

    /**
     * projects �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ProjectListType }
     *     
     */
    public ProjectListType getProjects() {
        return projects;
    }

    /**
     * projects �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ProjectListType }
     *     
     */
    public void setProjects(ProjectListType value) {
        this.projects = value;
    }

    /**
     * revisions �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link RevisionListType }
     *     
     */
    public RevisionListType getRevisions() {
        return revisions;
    }

    /**
     * revisions �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link RevisionListType }
     *     
     */
    public void setRevisions(RevisionListType value) {
        this.revisions = value;
    }

    /**
     * schedules �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ScheduleListType }
     *     
     */
    public ScheduleListType getSchedules() {
        return schedules;
    }

    /**
     * schedules �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduleListType }
     *     
     */
    public void setSchedules(ScheduleListType value) {
        this.schedules = value;
    }

    /**
     * sites �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link SiteListType }
     *     
     */
    public SiteListType getSites() {
        return sites;
    }

    /**
     * sites �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link SiteListType }
     *     
     */
    public void setSites(SiteListType value) {
        this.sites = value;
    }

    /**
     * tables �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link TableListType }
     *     
     */
    public TableListType getTables() {
        return tables;
    }

    /**
     * tables �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link TableListType }
     *     
     */
    public void setTables(TableListType value) {
        this.tables = value;
    }

    /**
     * users �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link UserListType }
     *     
     */
    public UserListType getUsers() {
        return users;
    }

    /**
     * users �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link UserListType }
     *     
     */
    public void setUsers(UserListType value) {
        this.users = value;
    }

    /**
     * workbooks �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link WorkbookListType }
     *     
     */
    public WorkbookListType getWorkbooks() {
        return workbooks;
    }

    /**
     * workbooks �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkbookListType }
     *     
     */
    public void setWorkbooks(WorkbookListType value) {
        this.workbooks = value;
    }

    /**
     * subscriptions �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link SubscriptionListType }
     *     
     */
    public SubscriptionListType getSubscriptions() {
        return subscriptions;
    }

    /**
     * subscriptions �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionListType }
     *     
     */
    public void setSubscriptions(SubscriptionListType value) {
        this.subscriptions = value;
    }

    /**
     * backgroundJob �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BackgroundJobType }
     *     
     */
    public BackgroundJobType getBackgroundJob() {
        return backgroundJob;
    }

    /**
     * backgroundJob �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BackgroundJobType }
     *     
     */
    public void setBackgroundJob(BackgroundJobType value) {
        this.backgroundJob = value;
    }

    /**
     * backgroundJobs �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BackgroundJobListType }
     *     
     */
    public BackgroundJobListType getBackgroundJobs() {
        return backgroundJobs;
    }

    /**
     * backgroundJobs �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BackgroundJobListType }
     *     
     */
    public void setBackgroundJobs(BackgroundJobListType value) {
        this.backgroundJobs = value;
    }

    /**
     * column �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ColumnType }
     *     
     */
    public ColumnType getColumn() {
        return column;
    }

    /**
     * column �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ColumnType }
     *     
     */
    public void setColumn(ColumnType value) {
        this.column = value;
    }

    /**
     * connection �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionType }
     *     
     */
    public ConnectionType getConnection() {
        return connection;
    }

    /**
     * connection �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionType }
     *     
     */
    public void setConnection(ConnectionType value) {
        this.connection = value;
    }

    /**
     * connections �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionListType }
     *     
     */
    public ConnectionListType getConnections() {
        return connections;
    }

    /**
     * connections �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionListType }
     *     
     */
    public void setConnections(ConnectionListType value) {
        this.connections = value;
    }

    /**
     * credentials �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link TableauCredentialsType }
     *     
     */
    public TableauCredentialsType getCredentials() {
        return credentials;
    }

    /**
     * credentials �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link TableauCredentialsType }
     *     
     */
    public void setCredentials(TableauCredentialsType value) {
        this.credentials = value;
    }

    /**
     * dataAlert �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DataAlertType }
     *     
     */
    public DataAlertType getDataAlert() {
        return dataAlert;
    }

    /**
     * dataAlert �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DataAlertType }
     *     
     */
    public void setDataAlert(DataAlertType value) {
        this.dataAlert = value;
    }

    /**
     * dataAlerts �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DataAlertListType }
     *     
     */
    public DataAlertListType getDataAlerts() {
        return dataAlerts;
    }

    /**
     * dataAlerts �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DataAlertListType }
     *     
     */
    public void setDataAlerts(DataAlertListType value) {
        this.dataAlerts = value;
    }

    /**
     * dataQualityWarning �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DataQualityWarningType }
     *     
     */
    public DataQualityWarningType getDataQualityWarning() {
        return dataQualityWarning;
    }

    /**
     * dataQualityWarning �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DataQualityWarningType }
     *     
     */
    public void setDataQualityWarning(DataQualityWarningType value) {
        this.dataQualityWarning = value;
    }

    /**
     * dataQualityWarningList �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DataQualityWarningListType }
     *     
     */
    public DataQualityWarningListType getDataQualityWarningList() {
        return dataQualityWarningList;
    }

    /**
     * dataQualityWarningList �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DataQualityWarningListType }
     *     
     */
    public void setDataQualityWarningList(DataQualityWarningListType value) {
        this.dataQualityWarningList = value;
    }

    /**
     * dataRole �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DataRoleType }
     *     
     */
    public DataRoleType getDataRole() {
        return dataRole;
    }

    /**
     * dataRole �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DataRoleType }
     *     
     */
    public void setDataRole(DataRoleType value) {
        this.dataRole = value;
    }

    /**
     * database �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DatabaseType }
     *     
     */
    public DatabaseType getDatabase() {
        return database;
    }

    /**
     * database �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DatabaseType }
     *     
     */
    public void setDatabase(DatabaseType value) {
        this.database = value;
    }

    /**
     * datasource �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DataSourceType }
     *     
     */
    public DataSourceType getDatasource() {
        return datasource;
    }

    /**
     * datasource �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSourceType }
     *     
     */
    public void setDatasource(DataSourceType value) {
        this.datasource = value;
    }

    /**
     * error �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ErrorType }
     *     
     */
    public ErrorType getError() {
        return error;
    }

    /**
     * error �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorType }
     *     
     */
    public void setError(ErrorType value) {
        this.error = value;
    }

    /**
     * favorites �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link FavoriteListType }
     *     
     */
    public FavoriteListType getFavorites() {
        return favorites;
    }

    /**
     * favorites �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link FavoriteListType }
     *     
     */
    public void setFavorites(FavoriteListType value) {
        this.favorites = value;
    }

    /**
     * fileUpload �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link FileUploadType }
     *     
     */
    public FileUploadType getFileUpload() {
        return fileUpload;
    }

    /**
     * fileUpload �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link FileUploadType }
     *     
     */
    public void setFileUpload(FileUploadType value) {
        this.fileUpload = value;
    }

    /**
     * flow �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link FlowType }
     *     
     */
    public FlowType getFlow() {
        return flow;
    }

    /**
     * flow �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link FlowType }
     *     
     */
    public void setFlow(FlowType value) {
        this.flow = value;
    }

    /**
     * group �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link GroupType }
     *     
     */
    public GroupType getGroup() {
        return group;
    }

    /**
     * group �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupType }
     *     
     */
    public void setGroup(GroupType value) {
        this.group = value;
    }

    /**
     * job �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link JobType }
     *     
     */
    public JobType getJob() {
        return job;
    }

    /**
     * job �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link JobType }
     *     
     */
    public void setJob(JobType value) {
        this.job = value;
    }

    /**
     * metric �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link MetricType }
     *     
     */
    public MetricType getMetric() {
        return metric;
    }

    /**
     * metric �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link MetricType }
     *     
     */
    public void setMetric(MetricType value) {
        this.metric = value;
    }

    /**
     * permissions �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link PermissionsType }
     *     
     */
    public PermissionsType getPermissions() {
        return permissions;
    }

    /**
     * permissions �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link PermissionsType }
     *     
     */
    public void setPermissions(PermissionsType value) {
        this.permissions = value;
    }

    /**
     * project �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ProjectType }
     *     
     */
    public ProjectType getProject() {
        return project;
    }

    /**
     * project �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ProjectType }
     *     
     */
    public void setProject(ProjectType value) {
        this.project = value;
    }

    /**
     * dataAlertsRecipient �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DataAlertsRecipientType }
     *     
     */
    public DataAlertsRecipientType getDataAlertsRecipient() {
        return dataAlertsRecipient;
    }

    /**
     * dataAlertsRecipient �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DataAlertsRecipientType }
     *     
     */
    public void setDataAlertsRecipient(DataAlertsRecipientType value) {
        this.dataAlertsRecipient = value;
    }

    /**
     * dataAlertsRecipientList �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DataAlertsRecipientListType }
     *     
     */
    public DataAlertsRecipientListType getDataAlertsRecipientList() {
        return dataAlertsRecipientList;
    }

    /**
     * dataAlertsRecipientList �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DataAlertsRecipientListType }
     *     
     */
    public void setDataAlertsRecipientList(DataAlertsRecipientListType value) {
        this.dataAlertsRecipientList = value;
    }

    /**
     * schedule �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ScheduleType }
     *     
     */
    public ScheduleType getSchedule() {
        return schedule;
    }

    /**
     * schedule �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduleType }
     *     
     */
    public void setSchedule(ScheduleType value) {
        this.schedule = value;
    }

    /**
     * serverInfo �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ServerInfo }
     *     
     */
    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    /**
     * serverInfo �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ServerInfo }
     *     
     */
    public void setServerInfo(ServerInfo value) {
        this.serverInfo = value;
    }

    /**
     * serverSettings �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ServerSettings }
     *     
     */
    public ServerSettings getServerSettings() {
        return serverSettings;
    }

    /**
     * serverSettings �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ServerSettings }
     *     
     */
    public void setServerSettings(ServerSettings value) {
        this.serverSettings = value;
    }

    /**
     * site �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link SiteType }
     *     
     */
    public SiteType getSite() {
        return site;
    }

    /**
     * site �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link SiteType }
     *     
     */
    public void setSite(SiteType value) {
        this.site = value;
    }

    /**
     * table �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link TableType }
     *     
     */
    public TableType getTable() {
        return table;
    }

    /**
     * table �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link TableType }
     *     
     */
    public void setTable(TableType value) {
        this.table = value;
    }

    /**
     * tags �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link TagListType }
     *     
     */
    public TagListType getTags() {
        return tags;
    }

    /**
     * tags �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link TagListType }
     *     
     */
    public void setTags(TagListType value) {
        this.tags = value;
    }

    /**
     * user �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link UserType }
     *     
     */
    public UserType getUser() {
        return user;
    }

    /**
     * user �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link UserType }
     *     
     */
    public void setUser(UserType value) {
        this.user = value;
    }

    /**
     * view �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ViewType }
     *     
     */
    public ViewType getView() {
        return view;
    }

    /**
     * view �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ViewType }
     *     
     */
    public void setView(ViewType value) {
        this.view = value;
    }

    /**
     * views �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ViewListType }
     *     
     */
    public ViewListType getViews() {
        return views;
    }

    /**
     * views �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ViewListType }
     *     
     */
    public void setViews(ViewListType value) {
        this.views = value;
    }

    /**
     * workbook �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link WorkbookType }
     *     
     */
    public WorkbookType getWorkbook() {
        return workbook;
    }

    /**
     * workbook �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkbookType }
     *     
     */
    public void setWorkbook(WorkbookType value) {
        this.workbook = value;
    }

    /**
     * subscription �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link SubscriptionType }
     *     
     */
    public SubscriptionType getSubscription() {
        return subscription;
    }

    /**
     * subscription �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionType }
     *     
     */
    public void setSubscription(SubscriptionType value) {
        this.subscription = value;
    }

    /**
     * task �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link TaskType }
     *     
     */
    public TaskType getTask() {
        return task;
    }

    /**
     * task �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link TaskType }
     *     
     */
    public void setTask(TaskType value) {
        this.task = value;
    }

    /**
     * tasks �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link TaskListType }
     *     
     */
    public TaskListType getTasks() {
        return tasks;
    }

    /**
     * tasks �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link TaskListType }
     *     
     */
    public void setTasks(TaskListType value) {
        this.tasks = value;
    }

    /**
     * warnings �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link WarningListType }
     *     
     */
    public WarningListType getWarnings() {
        return warnings;
    }

    /**
     * warnings �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link WarningListType }
     *     
     */
    public void setWarnings(WarningListType value) {
        this.warnings = value;
    }

    /**
     * degradations �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DegradationListType }
     *     
     */
    public DegradationListType getDegradations() {
        return degradations;
    }

    /**
     * degradations �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DegradationListType }
     *     
     */
    public void setDegradations(DegradationListType value) {
        this.degradations = value;
    }

    /**
     * listFieldConcepts �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ListFieldConceptsType }
     *     
     */
    public ListFieldConceptsType getListFieldConcepts() {
        return listFieldConcepts;
    }

    /**
     * listFieldConcepts �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ListFieldConceptsType }
     *     
     */
    public void setListFieldConcepts(ListFieldConceptsType value) {
        this.listFieldConcepts = value;
    }

    /**
     * fieldMatches �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link FieldMatchListType }
     *     
     */
    public FieldMatchListType getFieldMatches() {
        return fieldMatches;
    }

    /**
     * fieldMatches �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldMatchListType }
     *     
     */
    public void setFieldMatches(FieldMatchListType value) {
        this.fieldMatches = value;
    }

    /**
     * valueMatches �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link MatchValuesResultType }
     *     
     */
    public MatchValuesResultType getValueMatches() {
        return valueMatches;
    }

    /**
     * valueMatches �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link MatchValuesResultType }
     *     
     */
    public void setValueMatches(MatchValuesResultType value) {
        this.valueMatches = value;
    }

    /**
     * valueConceptCount �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ValueConceptCountType }
     *     
     */
    public ValueConceptCountType getValueConceptCount() {
        return valueConceptCount;
    }

    /**
     * valueConceptCount �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueConceptCountType }
     *     
     */
    public void setValueConceptCount(ValueConceptCountType value) {
        this.valueConceptCount = value;
    }

    /**
     * listValueConcepts �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ListValueConceptsType }
     *     
     */
    public ListValueConceptsType getListValueConcepts() {
        return listValueConcepts;
    }

    /**
     * listValueConcepts �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ListValueConceptsType }
     *     
     */
    public void setListValueConcepts(ListValueConceptsType value) {
        this.listValueConcepts = value;
    }

    /**
     * fieldConcept �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link FieldConceptType }
     *     
     */
    public FieldConceptType getFieldConcept() {
        return fieldConcept;
    }

    /**
     * fieldConcept �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldConceptType }
     *     
     */
    public void setFieldConcept(FieldConceptType value) {
        this.fieldConcept = value;
    }

    /**
     * getIndexingStatus �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link IndexingStatusType }
     *     
     */
    public IndexingStatusType getGetIndexingStatus() {
        return getIndexingStatus;
    }

    /**
     * getIndexingStatus �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link IndexingStatusType }
     *     
     */
    public void setGetIndexingStatus(IndexingStatusType value) {
        this.getIndexingStatus = value;
    }

}
