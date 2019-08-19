
package com.proto.server.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obtener-rolesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obtener-rolesResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="xml-registros" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtener-rolesResponse", propOrder = {
    "xmlRegistros"
})
public class ObtenerRolesResponse {

    @XmlElement(name = "xml-registros")
    protected String xmlRegistros;

    /**
     * Gets the value of the xmlRegistros property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlRegistros() {
        return xmlRegistros;
    }

    /**
     * Sets the value of the xmlRegistros property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlRegistros(String value) {
        this.xmlRegistros = value;
    }

}
