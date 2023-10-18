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
 *         &lt;element name="column" type="{http://tableau.com/api}columnType"/>
 *         &lt;element name="connection" type="{http://tableau.com/api}connectionType"/>
 *         &lt;element name="connections" type="{http://tableau.com/api}connectionListType"/>
 *         &lt;element name="credentials" type="{http://tableau.com/api}tableauCredentialsType"/>
 *         &lt;element name="dataAlert" type="{http://tableau.com/api}dataAlertType"/>
 *         &lt;element name="dataQualityWarning" type="{http://tableau.com/api}dataQualityWarningType"/>
 *         &lt;element name="dataRole" type="{http://tableau.com/api}dataRoleType"/>
 *         &lt;element name="database" type="{http://tableau.com/api}databaseType"/>
 *         &lt;element name="datasource" type="{http://tableau.com/api}dataSourceType"/>
 *         &lt;element name="distinctValues" type="{http://tableau.com/api}distinctValueListType"/>
 *         &lt;element name="favorite" type="{http://tableau.com/api}favoriteType"/>
 *         &lt;element name="field" type="{http://tableau.com/api}fieldType"/>
 *         &lt;element name="fieldConcept" type="{http://tableau.com/api}fieldConceptType"/>
 *         &lt;element name="flow" type="{http://tableau.com/api}flowType"/>
 *         &lt;element name="flowWarnings" type="{http://tableau.com/api}flowWarningsListContainerType"/>
 *         &lt;element name="group" type="{http://tableau.com/api}groupType"/>
 *         &lt;element name="metric" type="{http://tableau.com/api}metricType"/>
 *         &lt;element name="permissions" type="{http://tableau.com/api}permissionsType"/>
 *         &lt;element name="project" type="{http://tableau.com/api}projectType"/>
 *         &lt;element name="schedule" type="{http://tableau.com/api}scheduleType"/>
 *         &lt;element name="site" type="{http://tableau.com/api}siteType"/>
 *         &lt;element name="table" type="{http://tableau.com/api}tableType"/>
 *         &lt;element name="tags" type="{http://tableau.com/api}tagListType"/>
 *         &lt;element name="user" type="{http://tableau.com/api}userType"/>
 *         &lt;element name="workbook" type="{http://tableau.com/api}workbookType"/>
 *         &lt;element name="subscription" type="{http://tableau.com/api}subscriptionType"/>
 *         &lt;element name="task" type="{http://tableau.com/api}taskType"/>
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
    "column",
    "connection",
    "connections",
    "credentials",
    "dataAlert",
    "dataQualityWarning",
    "dataRole",
    "database",
    "datasource",
    "distinctValues",
    "favorite",
    "field",
    "fieldConcept",
    "flow",
    "flowWarnings",
    "group",
    "metric",
    "permissions",
    "project",
    "schedule",
    "site",
    "table",
    "tags",
    "user",
    "workbook",
    "subscription",
    "task"
})
@XmlRootElement(name = "tsRequest")
public class TsRequest {

    protected ColumnType column;
    protected ConnectionType connection;
    protected ConnectionListType connections;
    protected TableauCredentialsType credentials;
    protected DataAlertType dataAlert;
    protected DataQualityWarningType dataQualityWarning;
    protected DataRoleType dataRole;
    protected DatabaseType database;
    protected DataSourceType datasource;
    protected DistinctValueListType distinctValues;
    protected FavoriteType favorite;
    protected FieldType field;
    protected FieldConceptType fieldConcept;
    protected FlowType flow;
    protected FlowWarningsListContainerType flowWarnings;
    protected GroupType group;
    protected MetricType metric;
    protected PermissionsType permissions;
    protected ProjectType project;
    protected ScheduleType schedule;
    protected SiteType site;
    protected TableType table;
    protected TagListType tags;
    protected UserType user;
    protected WorkbookType workbook;
    protected SubscriptionType subscription;
    protected TaskType task;

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
     * distinctValues �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link DistinctValueListType }
     *     
     */
    public DistinctValueListType getDistinctValues() {
        return distinctValues;
    }

    /**
     * distinctValues �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link DistinctValueListType }
     *     
     */
    public void setDistinctValues(DistinctValueListType value) {
        this.distinctValues = value;
    }

    /**
     * favorite �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link FavoriteType }
     *     
     */
    public FavoriteType getFavorite() {
        return favorite;
    }

    /**
     * favorite �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link FavoriteType }
     *     
     */
    public void setFavorite(FavoriteType value) {
        this.favorite = value;
    }

    /**
     * field �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link FieldType }
     *     
     */
    public FieldType getField() {
        return field;
    }

    /**
     * field �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldType }
     *     
     */
    public void setField(FieldType value) {
        this.field = value;
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
     * flowWarnings �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link FlowWarningsListContainerType }
     *     
     */
    public FlowWarningsListContainerType getFlowWarnings() {
        return flowWarnings;
    }

    /**
     * flowWarnings �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link FlowWarningsListContainerType }
     *     
     */
    public void setFlowWarnings(FlowWarningsListContainerType value) {
        this.flowWarnings = value;
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

}
