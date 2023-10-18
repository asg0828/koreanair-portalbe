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
 * <p>valueMatchType complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="valueMatchType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="value" type="{http://tableau.com/api}semanticsValueType" minOccurs="0"/>
 *         &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="valueConcept" type="{http://tableau.com/api}valueConceptType" minOccurs="0"/>
 *         &lt;element name="valueConceptSignature" type="{http://tableau.com/api}valueConceptSignature" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "valueMatchType", propOrder = {
    "value",
    "weight",
    "valueConcept",
    "valueConceptSignature"
})
public class ValueMatchType {

    protected SemanticsValueType value;
    protected Double weight;
    protected ValueConceptType valueConcept;
    protected ValueConceptSignature valueConceptSignature;

    /**
     * value �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link SemanticsValueType }
     *     
     */
    public SemanticsValueType getValue() {
        return value;
    }

    /**
     * value �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link SemanticsValueType }
     *     
     */
    public void setValue(SemanticsValueType value) {
        this.value = value;
    }

    /**
     * weight �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * weight �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setWeight(Double value) {
        this.weight = value;
    }

    /**
     * valueConcept �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ValueConceptType }
     *     
     */
    public ValueConceptType getValueConcept() {
        return valueConcept;
    }

    /**
     * valueConcept �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueConceptType }
     *     
     */
    public void setValueConcept(ValueConceptType value) {
        this.valueConcept = value;
    }

    /**
     * valueConceptSignature �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ValueConceptSignature }
     *     
     */
    public ValueConceptSignature getValueConceptSignature() {
        return valueConceptSignature;
    }

    /**
     * valueConceptSignature �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueConceptSignature }
     *     
     */
    public void setValueConceptSignature(ValueConceptSignature value) {
        this.valueConceptSignature = value;
    }

}
