//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.8-b130911.1802 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2020.02.03 �ð� 05:34:04 PM KST 
//


package com.bdp.ap.common.tableauapi.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>dataTypeType�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * <p>
 * <pre>
 * &lt;simpleType name="dataTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DATA_TYPE_UNSPECIFIED"/>
 *     &lt;enumeration value="DATE"/>
 *     &lt;enumeration value="DATETIME"/>
 *     &lt;enumeration value="STRING"/>
 *     &lt;enumeration value="INT"/>
 *     &lt;enumeration value="FLOAT"/>
 *     &lt;enumeration value="BOOL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "dataTypeType")
@XmlEnum
public enum DataTypeType {

    DATA_TYPE_UNSPECIFIED,
    DATE,
    DATETIME,
    STRING,
    INT,
    FLOAT,
    BOOL;

    public String value() {
        return name();
    }

    public static DataTypeType fromValue(String v) {
        return valueOf(v);
    }

}
