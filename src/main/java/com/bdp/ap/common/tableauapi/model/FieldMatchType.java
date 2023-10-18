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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>fieldMatchType complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="fieldMatchType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fieldConceptURI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="fieldConcept" type="{http://tableau.com/api}fieldConceptType" minOccurs="0"/>
 *         &lt;element name="valueMatches" type="{http://tableau.com/api}valueMatchType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fieldMatchType", propOrder = {
    "fieldConceptURI",
    "weight",
    "fieldConcept",
    "valueMatches"
})
public class FieldMatchType {

    @XmlElement(required = true)
    protected String fieldConceptURI;
    protected double weight;
    protected FieldConceptType fieldConcept;
    protected List<ValueMatchType> valueMatches;

    /**
     * fieldConceptURI �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldConceptURI() {
        return fieldConceptURI;
    }

    /**
     * fieldConceptURI �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldConceptURI(String value) {
        this.fieldConceptURI = value;
    }

    /**
     * weight �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public double getWeight() {
        return weight;
    }

    /**
     * weight �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setWeight(double value) {
        this.weight = value;
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

}
