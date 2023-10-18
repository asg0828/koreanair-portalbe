//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.8-b130911.1802 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2020.02.03 �ð� 05:34:04 PM KST 
//


package com.bdp.ap.common.tableauapi.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>permissionsType complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="permissionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parent" type="{http://tableau.com/api}parentType" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="flow" type="{http://tableau.com/api}flowType"/>
 *           &lt;element name="database" type="{http://tableau.com/api}databaseType"/>
 *           &lt;element name="datasource" type="{http://tableau.com/api}dataSourceType"/>
 *           &lt;element name="project" type="{http://tableau.com/api}projectType"/>
 *           &lt;element name="table" type="{http://tableau.com/api}tableType"/>
 *           &lt;element name="view" type="{http://tableau.com/api}viewType"/>
 *           &lt;element name="workbook" type="{http://tableau.com/api}workbookType"/>
 *         &lt;/choice>
 *         &lt;element name="granteeCapabilities" type="{http://tableau.com/api}granteeCapabilitiesType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "permissionsType", propOrder = {
    "parent",
    "flow",
    "database",
    "datasource",
    "project",
    "table",
    "view",
    "workbook",
    "granteeCapabilities"
})
public class PermissionsType {

    protected ParentType parent;
    protected FlowType flow;
    protected DatabaseType database;
    protected DataSourceType datasource;
    protected ProjectType project;
    protected TableType table;
    protected ViewType view;
    protected WorkbookType workbook;
    protected List<GranteeCapabilitiesType> granteeCapabilities;

    /**
     * parent �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ParentType }
     *     
     */
    public ParentType getParent() {
        return parent;
    }

    /**
     * parent �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ParentType }
     *     
     */
    public void setParent(ParentType value) {
        this.parent = value;
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
     * Gets the value of the granteeCapabilities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the granteeCapabilities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGranteeCapabilities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GranteeCapabilitiesType }
     * 
     * 
     */
    public List<GranteeCapabilitiesType> getGranteeCapabilities() {
        if (granteeCapabilities == null) {
            granteeCapabilities = new ArrayList<GranteeCapabilitiesType>();
        }
        return this.granteeCapabilities;
    }

}
