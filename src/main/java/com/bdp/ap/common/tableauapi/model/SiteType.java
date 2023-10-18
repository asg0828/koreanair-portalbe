//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.8-b130911.1802 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2020.02.03 �ð� 05:34:04 PM KST 
//


package com.bdp.ap.common.tableauapi.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>siteType complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
 * 
 * <pre>
 * &lt;complexType name="siteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usage" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="numUsers" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="storage" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://tableau.com/api}resourceIdType" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="contentUrl" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="adminMode">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="ContentOnly"/>
 *             &lt;enumeration value="ContentAndUsers"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="userQuota" type="{http://tableau.com/api}siteQuotaType" />
 *       &lt;attribute name="storageQuota" type="{http://tableau.com/api}siteQuotaType" />
 *       &lt;attribute name="tierCreatorCapacity" type="{http://tableau.com/api}siteCapacityType" />
 *       &lt;attribute name="tierExplorerCapacity" type="{http://tableau.com/api}siteCapacityType" />
 *       &lt;attribute name="tierViewerCapacity" type="{http://tableau.com/api}siteCapacityType" />
 *       &lt;attribute name="disableSubscriptions" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="state" type="{http://tableau.com/api}stateType" />
 *       &lt;attribute name="revisionHistoryEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="revisionLimit" type="{http://tableau.com/api}revisionLimitType" />
 *       &lt;attribute name="subscribeOthersEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="allowSubscriptionAttachments" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="guestAccessEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="cacheWarmupEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="commentingEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="cacheeWarmupThreshold" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="flowsEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="extractEncryptionMode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mobileBiometricsEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="sheetImageEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="catalogingEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="derivedPermissionsEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "siteType", propOrder = {
    "usage"
})
public class SiteType {

    protected SiteType.Usage usage;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "contentUrl")
    protected String contentUrl;
    @XmlAttribute(name = "adminMode")
    protected String adminMode;
    @XmlAttribute(name = "userQuota")
    protected BigInteger userQuota;
    @XmlAttribute(name = "storageQuota")
    protected BigInteger storageQuota;
    @XmlAttribute(name = "tierCreatorCapacity")
    protected BigInteger tierCreatorCapacity;
    @XmlAttribute(name = "tierExplorerCapacity")
    protected BigInteger tierExplorerCapacity;
    @XmlAttribute(name = "tierViewerCapacity")
    protected BigInteger tierViewerCapacity;
    @XmlAttribute(name = "disableSubscriptions")
    protected Boolean disableSubscriptions;
    @XmlAttribute(name = "state")
    protected StateType state;
    @XmlAttribute(name = "revisionHistoryEnabled")
    protected Boolean revisionHistoryEnabled;
    @XmlAttribute(name = "revisionLimit")
    protected String revisionLimit;
    @XmlAttribute(name = "subscribeOthersEnabled")
    protected Boolean subscribeOthersEnabled;
    @XmlAttribute(name = "allowSubscriptionAttachments")
    protected Boolean allowSubscriptionAttachments;
    @XmlAttribute(name = "guestAccessEnabled")
    protected Boolean guestAccessEnabled;
    @XmlAttribute(name = "cacheWarmupEnabled")
    protected Boolean cacheWarmupEnabled;
    @XmlAttribute(name = "commentingEnabled")
    protected Boolean commentingEnabled;
    @XmlAttribute(name = "cacheeWarmupThreshold")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger cacheeWarmupThreshold;
    @XmlAttribute(name = "flowsEnabled")
    protected Boolean flowsEnabled;
    @XmlAttribute(name = "extractEncryptionMode")
    protected String extractEncryptionMode;
    @XmlAttribute(name = "mobileBiometricsEnabled")
    protected Boolean mobileBiometricsEnabled;
    @XmlAttribute(name = "sheetImageEnabled")
    protected Boolean sheetImageEnabled;
    @XmlAttribute(name = "catalogingEnabled")
    protected Boolean catalogingEnabled;
    @XmlAttribute(name = "derivedPermissionsEnabled")
    protected Boolean derivedPermissionsEnabled;

    /**
     * usage �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link SiteType.Usage }
     *     
     */
    public SiteType.Usage getUsage() {
        return usage;
    }

    /**
     * usage �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link SiteType.Usage }
     *     
     */
    public void setUsage(SiteType.Usage value) {
        this.usage = value;
    }

    /**
     * id �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * id �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * name �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * name �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * contentUrl �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentUrl() {
        return contentUrl;
    }

    /**
     * contentUrl �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentUrl(String value) {
        this.contentUrl = value;
    }

    /**
     * adminMode �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminMode() {
        return adminMode;
    }

    /**
     * adminMode �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminMode(String value) {
        this.adminMode = value;
    }

    /**
     * userQuota �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUserQuota() {
        return userQuota;
    }

    /**
     * userQuota �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUserQuota(BigInteger value) {
        this.userQuota = value;
    }

    /**
     * storageQuota �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStorageQuota() {
        return storageQuota;
    }

    /**
     * storageQuota �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStorageQuota(BigInteger value) {
        this.storageQuota = value;
    }

    /**
     * tierCreatorCapacity �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTierCreatorCapacity() {
        return tierCreatorCapacity;
    }

    /**
     * tierCreatorCapacity �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTierCreatorCapacity(BigInteger value) {
        this.tierCreatorCapacity = value;
    }

    /**
     * tierExplorerCapacity �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTierExplorerCapacity() {
        return tierExplorerCapacity;
    }

    /**
     * tierExplorerCapacity �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTierExplorerCapacity(BigInteger value) {
        this.tierExplorerCapacity = value;
    }

    /**
     * tierViewerCapacity �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTierViewerCapacity() {
        return tierViewerCapacity;
    }

    /**
     * tierViewerCapacity �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTierViewerCapacity(BigInteger value) {
        this.tierViewerCapacity = value;
    }

    /**
     * disableSubscriptions �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDisableSubscriptions() {
        return disableSubscriptions;
    }

    /**
     * disableSubscriptions �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisableSubscriptions(Boolean value) {
        this.disableSubscriptions = value;
    }

    /**
     * state �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link StateType }
     *     
     */
    public StateType getState() {
        return state;
    }

    /**
     * state �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link StateType }
     *     
     */
    public void setState(StateType value) {
        this.state = value;
    }

    /**
     * revisionHistoryEnabled �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRevisionHistoryEnabled() {
        return revisionHistoryEnabled;
    }

    /**
     * revisionHistoryEnabled �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRevisionHistoryEnabled(Boolean value) {
        this.revisionHistoryEnabled = value;
    }

    /**
     * revisionLimit �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevisionLimit() {
        return revisionLimit;
    }

    /**
     * revisionLimit �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevisionLimit(String value) {
        this.revisionLimit = value;
    }

    /**
     * subscribeOthersEnabled �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSubscribeOthersEnabled() {
        return subscribeOthersEnabled;
    }

    /**
     * subscribeOthersEnabled �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSubscribeOthersEnabled(Boolean value) {
        this.subscribeOthersEnabled = value;
    }

    /**
     * allowSubscriptionAttachments �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowSubscriptionAttachments() {
        return allowSubscriptionAttachments;
    }

    /**
     * allowSubscriptionAttachments �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowSubscriptionAttachments(Boolean value) {
        this.allowSubscriptionAttachments = value;
    }

    /**
     * guestAccessEnabled �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGuestAccessEnabled() {
        return guestAccessEnabled;
    }

    /**
     * guestAccessEnabled �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGuestAccessEnabled(Boolean value) {
        this.guestAccessEnabled = value;
    }

    /**
     * cacheWarmupEnabled �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCacheWarmupEnabled() {
        return cacheWarmupEnabled;
    }

    /**
     * cacheWarmupEnabled �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCacheWarmupEnabled(Boolean value) {
        this.cacheWarmupEnabled = value;
    }

    /**
     * commentingEnabled �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCommentingEnabled() {
        return commentingEnabled;
    }

    /**
     * commentingEnabled �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCommentingEnabled(Boolean value) {
        this.commentingEnabled = value;
    }

    /**
     * cacheeWarmupThreshold �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCacheeWarmupThreshold() {
        return cacheeWarmupThreshold;
    }

    /**
     * cacheeWarmupThreshold �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCacheeWarmupThreshold(BigInteger value) {
        this.cacheeWarmupThreshold = value;
    }

    /**
     * flowsEnabled �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlowsEnabled() {
        return flowsEnabled;
    }

    /**
     * flowsEnabled �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlowsEnabled(Boolean value) {
        this.flowsEnabled = value;
    }

    /**
     * extractEncryptionMode �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtractEncryptionMode() {
        return extractEncryptionMode;
    }

    /**
     * extractEncryptionMode �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtractEncryptionMode(String value) {
        this.extractEncryptionMode = value;
    }

    /**
     * mobileBiometricsEnabled �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMobileBiometricsEnabled() {
        return mobileBiometricsEnabled;
    }

    /**
     * mobileBiometricsEnabled �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMobileBiometricsEnabled(Boolean value) {
        this.mobileBiometricsEnabled = value;
    }

    /**
     * sheetImageEnabled �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSheetImageEnabled() {
        return sheetImageEnabled;
    }

    /**
     * sheetImageEnabled �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSheetImageEnabled(Boolean value) {
        this.sheetImageEnabled = value;
    }

    /**
     * catalogingEnabled �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCatalogingEnabled() {
        return catalogingEnabled;
    }

    /**
     * catalogingEnabled �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCatalogingEnabled(Boolean value) {
        this.catalogingEnabled = value;
    }

    /**
     * derivedPermissionsEnabled �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDerivedPermissionsEnabled() {
        return derivedPermissionsEnabled;
    }

    /**
     * derivedPermissionsEnabled �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDerivedPermissionsEnabled(Boolean value) {
        this.derivedPermissionsEnabled = value;
    }


    /**
     * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
     * 
     * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="numUsers" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
     *       &lt;attribute name="storage" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Usage {

        @XmlAttribute(name = "numUsers", required = true)
        @XmlSchemaType(name = "nonNegativeInteger")
        protected BigInteger numUsers;
        @XmlAttribute(name = "storage", required = true)
        @XmlSchemaType(name = "nonNegativeInteger")
        protected BigInteger storage;

        /**
         * numUsers �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getNumUsers() {
            return numUsers;
        }

        /**
         * numUsers �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setNumUsers(BigInteger value) {
            this.numUsers = value;
        }

        /**
         * storage �Ӽ��� ���� �����ɴϴ�.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getStorage() {
            return storage;
        }

        /**
         * storage �Ӽ��� ���� �����մϴ�.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setStorage(BigInteger value) {
            this.storage = value;
        }

    }

}
