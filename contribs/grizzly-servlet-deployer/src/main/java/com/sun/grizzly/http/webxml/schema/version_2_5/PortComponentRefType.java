//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-661 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.03.05 at 03:21:43 PM EST 
//


package com.sun.grizzly.http.webxml.schema.version_2_5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 
 * 	The port-component-ref element declares a client dependency
 * 	on the container for resolving a Service Endpoint Interface
 * 	to a WSDL port. It optionally associates the Service Endpoint
 * 	Interface with a particular port-component. This is only used
 * 	by the container for a Service.getPort(Class) method call.
 * 
 *       
 * 
 * <p>Java class for port-component-refType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="port-component-refType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="service-endpoint-interface" type="{http://java.sun.com/xml/ns/javaee}fully-qualified-classType"/>
 *         &lt;element name="enable-mtom" type="{http://java.sun.com/xml/ns/javaee}true-falseType" minOccurs="0"/>
 *         &lt;element name="port-component-link" type="{http://java.sun.com/xml/ns/javaee}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "port-component-refType", propOrder = {
    "serviceEndpointInterface",
    "enableMtom",
    "portComponentLink"
})
public class PortComponentRefType {

    @XmlElement(name = "service-endpoint-interface", required = true)
    protected FullyQualifiedClassType serviceEndpointInterface;
    @XmlElement(name = "enable-mtom")
    protected TrueFalseType enableMtom;
    @XmlElement(name = "port-component-link")
    protected com.sun.grizzly.http.webxml.schema.version_2_5.String portComponentLink;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected java.lang.String id;

    /**
     * Gets the value of the serviceEndpointInterface property.
     * 
     * @return
     *     possible object is
     *     {@link FullyQualifiedClassType }
     *     
     */
    public FullyQualifiedClassType getServiceEndpointInterface() {
        return serviceEndpointInterface;
    }

    /**
     * Sets the value of the serviceEndpointInterface property.
     * 
     * @param value
     *     allowed object is
     *     {@link FullyQualifiedClassType }
     *     
     */
    public void setServiceEndpointInterface(FullyQualifiedClassType value) {
        this.serviceEndpointInterface = value;
    }

    /**
     * Gets the value of the enableMtom property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getEnableMtom() {
        return enableMtom;
    }

    /**
     * Sets the value of the enableMtom property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setEnableMtom(TrueFalseType value) {
        this.enableMtom = value;
    }

    /**
     * Gets the value of the portComponentLink property.
     * 
     * @return
     *     possible object is
     *     {@link com.sun.grizzly.http.webxml.schema.version_2_5.String }
     *     
     */
    public com.sun.grizzly.http.webxml.schema.version_2_5.String getPortComponentLink() {
        return portComponentLink;
    }

    /**
     * Sets the value of the portComponentLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.sun.grizzly.http.webxml.schema.version_2_5.String }
     *     
     */
    public void setPortComponentLink(com.sun.grizzly.http.webxml.schema.version_2_5.String value) {
        this.portComponentLink = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setId(java.lang.String value) {
        this.id = value;
    }

}
