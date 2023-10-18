//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.8-b130911.1802 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2020.02.03 �ð� 05:34:04 PM KST 
//


package com.bdp.ap.common.tableauapi.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>siteRoleType�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * <p>
 * <pre>
 * &lt;simpleType name="siteRoleType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Creator"/>
 *     &lt;enumeration value="Explorer"/>
 *     &lt;enumeration value="ExplorerCanPublish"/>
 *     &lt;enumeration value="Guest"/>
 *     &lt;enumeration value="Interactor"/>
 *     &lt;enumeration value="Publisher"/>
 *     &lt;enumeration value="ReadOnly"/>
 *     &lt;enumeration value="ServerAdministrator"/>
 *     &lt;enumeration value="SiteAdministrator"/>
 *     &lt;enumeration value="SiteAdministratorCreator"/>
 *     &lt;enumeration value="SiteAdministratorExplorer"/>
 *     &lt;enumeration value="Unlicensed"/>
 *     &lt;enumeration value="UnlicensedWithPublish"/>
 *     &lt;enumeration value="Viewer"/>
 *     &lt;enumeration value="ViewerWithPublish"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "siteRoleType")
@XmlEnum
public enum SiteRoleType {

    @XmlEnumValue("Creator")
    CREATOR("Creator"),
    @XmlEnumValue("Explorer")
    EXPLORER("Explorer"),
    @XmlEnumValue("ExplorerCanPublish")
    EXPLORER_CAN_PUBLISH("ExplorerCanPublish"),
    @XmlEnumValue("Guest")
    GUEST("Guest"),
    @XmlEnumValue("Interactor")
    INTERACTOR("Interactor"),
    @XmlEnumValue("Publisher")
    PUBLISHER("Publisher"),
    @XmlEnumValue("ReadOnly")
    READ_ONLY("ReadOnly"),
    @XmlEnumValue("ServerAdministrator")
    SERVER_ADMINISTRATOR("ServerAdministrator"),
    @XmlEnumValue("SiteAdministrator")
    SITE_ADMINISTRATOR("SiteAdministrator"),
    @XmlEnumValue("SiteAdministratorCreator")
    SITE_ADMINISTRATOR_CREATOR("SiteAdministratorCreator"),
    @XmlEnumValue("SiteAdministratorExplorer")
    SITE_ADMINISTRATOR_EXPLORER("SiteAdministratorExplorer"),
    @XmlEnumValue("Unlicensed")
    UNLICENSED("Unlicensed"),
    @XmlEnumValue("UnlicensedWithPublish")
    UNLICENSED_WITH_PUBLISH("UnlicensedWithPublish"),
    @XmlEnumValue("Viewer")
    VIEWER("Viewer"),
    @XmlEnumValue("ViewerWithPublish")
    VIEWER_WITH_PUBLISH("ViewerWithPublish");
    private final String value;

    SiteRoleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SiteRoleType fromValue(String v) {
        for (SiteRoleType c: SiteRoleType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
