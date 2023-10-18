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
 * <p>serverInfo complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="serverInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productVersion" type="{http://tableau.com/api}productVersion"/>
 *         &lt;element name="prepConductorVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="restApiVersion" type="{http://tableau.com/api}restApiVersion"/>
 *         &lt;element name="platform" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serverSettings" type="{http://tableau.com/api}serverSettings"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serverInfo", propOrder = {
    "productVersion",
    "prepConductorVersion",
    "restApiVersion",
    "platform",
    "serverSettings"
})
public class ServerInfo {

    @XmlElement(required = true)
    protected ProductVersion productVersion;
    @XmlElement(required = true)
    protected String prepConductorVersion;
    @XmlElement(required = true)
    protected String restApiVersion;
    @XmlElement(required = true)
    protected String platform;
    @XmlElement(required = true)
    protected ServerSettings serverSettings;

    /**
     * productVersion �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ProductVersion }
     *     
     */
    public ProductVersion getProductVersion() {
        return productVersion;
    }

    /**
     * productVersion �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductVersion }
     *     
     */
    public void setProductVersion(ProductVersion value) {
        this.productVersion = value;
    }

    /**
     * prepConductorVersion �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrepConductorVersion() {
        return prepConductorVersion;
    }

    /**
     * prepConductorVersion �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrepConductorVersion(String value) {
        this.prepConductorVersion = value;
    }

    /**
     * restApiVersion �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestApiVersion() {
        return restApiVersion;
    }

    /**
     * restApiVersion �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestApiVersion(String value) {
        this.restApiVersion = value;
    }

    /**
     * platform �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * platform �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlatform(String value) {
        this.platform = value;
    }

    /**
     * serverSettings �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link ServerSettings }
     *     
     */
    public ServerSettings getServerSettings() {
        return serverSettings;
    }

    /**
     * serverSettings �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link ServerSettings }
     *     
     */
    public void setServerSettings(ServerSettings value) {
        this.serverSettings = value;
    }

}
