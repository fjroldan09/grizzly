//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-661 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.03.03 at 02:35:00 PM EST 
//


package com.sun.grizzly.http.webxml.schema.version_2_3;

import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "authMethod",
    "realmName",
    "formLoginConfig"
})
@XmlRootElement(name = "login-config")
public class LoginConfig
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "auth-method")
    protected AuthMethod authMethod;
    @XmlElement(name = "realm-name")
    protected RealmName realmName;
    @XmlElement(name = "form-login-config")
    protected FormLoginConfig formLoginConfig;
    @XmlTransient
    private VetoableChangeSupport support = (new VetoableChangeSupport(this));

    /**
     * Gets the value of the id property.
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
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        try {
            support.fireVetoableChange("Id",id, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.id = value;
    }

    /**
     * Gets the value of the authMethod property.
     * 
     * @return
     *     possible object is
     *     {@link AuthMethod }
     *     
     */
    public AuthMethod getAuthMethod() {
        return authMethod;
    }

    /**
     * Sets the value of the authMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthMethod }
     *     
     */
    public void setAuthMethod(AuthMethod value) {
        try {
            support.fireVetoableChange("AuthMethod",authMethod, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.authMethod = value;
    }

    /**
     * Gets the value of the realmName property.
     * 
     * @return
     *     possible object is
     *     {@link RealmName }
     *     
     */
    public RealmName getRealmName() {
        return realmName;
    }

    /**
     * Sets the value of the realmName property.
     * 
     * @param value
     *     allowed object is
     *     {@link RealmName }
     *     
     */
    public void setRealmName(RealmName value) {
        try {
            support.fireVetoableChange("RealmName",realmName, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.realmName = value;
    }

    /**
     * Gets the value of the formLoginConfig property.
     * 
     * @return
     *     possible object is
     *     {@link FormLoginConfig }
     *     
     */
    public FormLoginConfig getFormLoginConfig() {
        return formLoginConfig;
    }

    /**
     * Sets the value of the formLoginConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormLoginConfig }
     *     
     */
    public void setFormLoginConfig(FormLoginConfig value) {
        try {
            support.fireVetoableChange("FormLoginConfig",formLoginConfig, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.formLoginConfig = value;
    }

    public void addVetoableChangeListener(VetoableChangeListener param0) {
        support.addVetoableChangeListener(param0);
    }

    public void addVetoableChangeListener(String param0, VetoableChangeListener param1) {
        support.addVetoableChangeListener(param0, param1);
    }

    public void removeVetoableChangeListener(String param0, VetoableChangeListener param1) {
        support.removeVetoableChangeListener(param0, param1);
    }

    public void removeVetoableChangeListener(VetoableChangeListener param0) {
        support.removeVetoableChangeListener(param0);
    }

}
