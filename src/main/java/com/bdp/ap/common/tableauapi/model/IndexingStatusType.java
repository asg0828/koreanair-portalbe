//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.8-b130911.1802 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2020.02.03 �ð� 05:34:04 PM KST 
//


package com.bdp.ap.common.tableauapi.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>indexingStatusType complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="indexingStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="indexingStatusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="indexingErrorCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="indexedValueConceptVersion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "indexingStatusType", propOrder = {
    "indexingStatusCode",
    "indexingErrorCode",
    "indexedValueConceptVersion"
})
public class IndexingStatusType {

    @XmlElement(required = true)
    protected String indexingStatusCode;
    @XmlElement(required = true)
    protected String indexingErrorCode;
    protected Long indexedValueConceptVersion;

    /**
     * indexingStatusCode �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndexingStatusCode() {
        return indexingStatusCode;
    }

    /**
     * indexingStatusCode �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndexingStatusCode(String value) {
        this.indexingStatusCode = value;
    }

    /**
     * indexingErrorCode �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndexingErrorCode() {
        return indexingErrorCode;
    }

    /**
     * indexingErrorCode �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndexingErrorCode(String value) {
        this.indexingErrorCode = value;
    }

    /**
     * indexedValueConceptVersion �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIndexedValueConceptVersion() {
        return indexedValueConceptVersion;
    }

    /**
     * indexedValueConceptVersion �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIndexedValueConceptVersion(Long value) {
        this.indexedValueConceptVersion = value;
    }

}
