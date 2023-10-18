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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>fieldConceptType complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="fieldConceptType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uri" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="objectConceptURI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="names" type="{http://tableau.com/api}nameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nameCharacteristics" type="{http://tableau.com/api}nameCharacteristicsType" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentFieldConceptURI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataTypes" type="{http://tableau.com/api}dataTypeType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="fieldRoles" type="{http://tableau.com/api}fieldRoleType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="defaultFormats" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="valueCharacteristics" type="{http://tableau.com/api}valueCharacteristicsType" minOccurs="0"/>
 *         &lt;element name="ownerID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdAt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="updatedAt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="valueSource" type="{http://tableau.com/api}valueSourceType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fieldConceptType", propOrder = {
    "uri",
    "objectConceptURI",
    "names",
    "nameCharacteristics",
    "description",
    "parentFieldConceptURI",
    "dataTypes",
    "fieldRoles",
    "defaultFormats",
    "valueCharacteristics",
    "ownerID",
    "createdAt",
    "updatedAt",
    "valueSource"
})
public class FieldConceptType {

    @XmlElement(required = true)
    protected String uri;
    @XmlElement(required = true)
    protected String objectConceptURI;
    protected List<NameType> names;
    protected NameCharacteristicsType nameCharacteristics;
    protected String description;
    protected String parentFieldConceptURI;
    @XmlSchemaType(name = "string")
    protected List<DataTypeType> dataTypes;
    @XmlSchemaType(name = "string")
    protected List<FieldRoleType> fieldRoles;
    protected List<String> defaultFormats;
    protected ValueCharacteristicsType valueCharacteristics;
    protected String ownerID;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdAt;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedAt;
    protected ValueSourceType valueSource;

    /**
     * uri �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUri() {
        return uri;
    }

    /**
     * uri �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUri(String value) {
        this.uri = value;
    }

    /**
     * objectConceptURI �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectConceptURI() {
        return objectConceptURI;
    }

    /**
     * objectConceptURI �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectConceptURI(String value) {
        this.objectConceptURI = value;
    }

    /**
     * Gets the value of the names property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the names property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameType }
     * 
     * 
     */
    public List<NameType> getNames() {
        if (names == null) {
            names = new ArrayList<NameType>();
        }
        return this.names;
    }

    /**
     * nameCharacteristics �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link NameCharacteristicsType }
     *     
     */
    public NameCharacteristicsType getNameCharacteristics() {
        return nameCharacteristics;
    }

    /**
     * nameCharacteristics �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link NameCharacteristicsType }
     *     
     */
    public void setNameCharacteristics(NameCharacteristicsType value) {
        this.nameCharacteristics = value;
    }

    /**
     * description �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * description �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * parentFieldConceptURI �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentFieldConceptURI() {
        return parentFieldConceptURI;
    }

    /**
     * parentFieldConceptURI �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentFieldConceptURI(String value) {
        this.parentFieldConceptURI = value;
    }

    /**
     * Gets the value of the dataTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataTypeType }
     * 
     * 
     */
    public List<DataTypeType> getDataTypes() {
        if (dataTypes == null) {
            dataTypes = new ArrayList<DataTypeType>();
        }
        return this.dataTypes;
    }

    /**
     * Gets the value of the fieldRoles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fieldRoles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFieldRoles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FieldRoleType }
     * 
     * 
     */
    public List<FieldRoleType> getFieldRoles() {
        if (fieldRoles == null) {
            fieldRoles = new ArrayList<FieldRoleType>();
        }
        return this.fieldRoles;
    }

    /**
     * Gets the value of the defaultFormats property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the defaultFormats property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDefaultFormats().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDefaultFormats() {
        if (defaultFormats == null) {
            defaultFormats = new ArrayList<String>();
        }
        return this.defaultFormats;
    }

    /**
     * valueCharacteristics �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ValueCharacteristicsType }
     *     
     */
    public ValueCharacteristicsType getValueCharacteristics() {
        return valueCharacteristics;
    }

    /**
     * valueCharacteristics �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueCharacteristicsType }
     *     
     */
    public void setValueCharacteristics(ValueCharacteristicsType value) {
        this.valueCharacteristics = value;
    }

    /**
     * ownerID �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerID() {
        return ownerID;
    }

    /**
     * ownerID �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerID(String value) {
        this.ownerID = value;
    }

    /**
     * createdAt �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedAt() {
        return createdAt;
    }

    /**
     * createdAt �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedAt(XMLGregorianCalendar value) {
        this.createdAt = value;
    }

    /**
     * updatedAt �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedAt() {
        return updatedAt;
    }

    /**
     * updatedAt �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedAt(XMLGregorianCalendar value) {
        this.updatedAt = value;
    }

    /**
     * valueSource �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ValueSourceType }
     *     
     */
    public ValueSourceType getValueSource() {
        return valueSource;
    }

    /**
     * valueSource �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueSourceType }
     *     
     */
    public void setValueSource(ValueSourceType value) {
        this.valueSource = value;
    }

}
