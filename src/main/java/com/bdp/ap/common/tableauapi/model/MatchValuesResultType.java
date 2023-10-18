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
 * <p>matchValuesResultType complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="matchValuesResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="averageMatchWeight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="valueMatches" type="{http://tableau.com/api}valueMatchType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="matchResponseSet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "matchValuesResultType", propOrder = {
    "averageMatchWeight",
    "valueMatches",
    "matchResponseSet"
})
public class MatchValuesResultType {

    protected Double averageMatchWeight;
    protected List<ValueMatchType> valueMatches;
    protected String matchResponseSet;

    /**
     * averageMatchWeight �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAverageMatchWeight() {
        return averageMatchWeight;
    }

    /**
     * averageMatchWeight �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAverageMatchWeight(Double value) {
        this.averageMatchWeight = value;
    }

    /**
     * Gets the value of the valueMatches property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueMatches property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueMatches().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValueMatchType }
     * 
     * 
     */
    public List<ValueMatchType> getValueMatches() {
        if (valueMatches == null) {
            valueMatches = new ArrayList<ValueMatchType>();
        }
        return this.valueMatches;
    }

    /**
     * matchResponseSet �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchResponseSet() {
        return matchResponseSet;
    }

    /**
     * matchResponseSet �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchResponseSet(String value) {
        this.matchResponseSet = value;
    }

}
