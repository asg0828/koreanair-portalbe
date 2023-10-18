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
 * <p>serverSettings complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="serverSettings">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="oAuthEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="sheetImageMaxAgeFloor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sheetImageMaxAgeCeiling" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="offlineInteractionSupportedPhase" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serverSettings", propOrder = {
    "oAuthEnabled",
    "sheetImageMaxAgeFloor",
    "sheetImageMaxAgeCeiling",
    "offlineInteractionSupportedPhase"
})
public class ServerSettings {

    protected boolean oAuthEnabled;
    protected int sheetImageMaxAgeFloor;
    protected int sheetImageMaxAgeCeiling;
    protected int offlineInteractionSupportedPhase;

    /**
     * oAuthEnabled �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public boolean isOAuthEnabled() {
        return oAuthEnabled;
    }

    /**
     * oAuthEnabled �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setOAuthEnabled(boolean value) {
        this.oAuthEnabled = value;
    }

    /**
     * sheetImageMaxAgeFloor �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getSheetImageMaxAgeFloor() {
        return sheetImageMaxAgeFloor;
    }

    /**
     * sheetImageMaxAgeFloor �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setSheetImageMaxAgeFloor(int value) {
        this.sheetImageMaxAgeFloor = value;
    }

    /**
     * sheetImageMaxAgeCeiling �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getSheetImageMaxAgeCeiling() {
        return sheetImageMaxAgeCeiling;
    }

    /**
     * sheetImageMaxAgeCeiling �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setSheetImageMaxAgeCeiling(int value) {
        this.sheetImageMaxAgeCeiling = value;
    }

    /**
     * offlineInteractionSupportedPhase �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getOfflineInteractionSupportedPhase() {
        return offlineInteractionSupportedPhase;
    }

    /**
     * offlineInteractionSupportedPhase �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setOfflineInteractionSupportedPhase(int value) {
        this.offlineInteractionSupportedPhase = value;
    }

}
