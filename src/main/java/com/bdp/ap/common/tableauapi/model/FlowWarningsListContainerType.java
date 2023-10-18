//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.8-b130911.1802 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2020.02.03 �ð� 05:34:04 PM KST 
//


package com.bdp.ap.common.tableauapi.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>flowWarningsListContainerType complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="flowWarningsListContainerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="connectionWarnings" type="{http://tableau.com/api}warningListType" minOccurs="0"/>
 *         &lt;element name="nodeWarnings" type="{http://tableau.com/api}warningListType" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flowWarningsListContainerType", propOrder = {
    "connectionWarnings",
    "nodeWarnings"
})
public class FlowWarningsListContainerType {

    protected WarningListType connectionWarnings;
    protected WarningListType nodeWarnings;

    /**
     * connectionWarnings �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link WarningListType }
     *     
     */
    public WarningListType getConnectionWarnings() {
        return connectionWarnings;
    }

    /**
     * connectionWarnings �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link WarningListType }
     *     
     */
    public void setConnectionWarnings(WarningListType value) {
        this.connectionWarnings = value;
    }

    /**
     * nodeWarnings �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link WarningListType }
     *     
     */
    public WarningListType getNodeWarnings() {
        return nodeWarnings;
    }

    /**
     * nodeWarnings �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link WarningListType }
     *     
     */
    public void setNodeWarnings(WarningListType value) {
        this.nodeWarnings = value;
    }

}
