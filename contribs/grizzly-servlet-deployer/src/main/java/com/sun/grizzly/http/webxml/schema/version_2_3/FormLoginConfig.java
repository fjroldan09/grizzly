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
    "formLoginPage",
    "formErrorPage"
})
@XmlRootElement(name = "form-login-config")
public class FormLoginConfig
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "form-login-page", required = true)
    protected FormLoginPage formLoginPage;
    @XmlElement(name = "form-error-page", required = true)
    protected FormErrorPage formErrorPage;
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
     * Gets the value of the formLoginPage property.
     * 
     * @return
     *     possible object is
     *     {@link FormLoginPage }
     *     
     */
    public FormLoginPage getFormLoginPage() {
        return formLoginPage;
    }

    /**
     * Sets the value of the formLoginPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormLoginPage }
     *     
     */
    public void setFormLoginPage(FormLoginPage value) {
        try {
            support.fireVetoableChange("FormLoginPage",formLoginPage, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.formLoginPage = value;
    }

    /**
     * Gets the value of the formErrorPage property.
     * 
     * @return
     *     possible object is
     *     {@link FormErrorPage }
     *     
     */
    public FormErrorPage getFormErrorPage() {
        return formErrorPage;
    }

    /**
     * Sets the value of the formErrorPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormErrorPage }
     *     
     */
    public void setFormErrorPage(FormErrorPage value) {
        try {
            support.fireVetoableChange("FormErrorPage",formErrorPage, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.formErrorPage = value;
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
