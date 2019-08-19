
package com.proto.server.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import entidad.ws.server.proto.com.resultado.Resultado;


/**
 * <p>Java class for consulta-usuarioResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consulta-usuarioResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="xml-resultado" type="{http://com.proto.server.ws.entidad/Resultado}Resultado" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consulta-usuarioResponse", propOrder = {
    "xmlResultado"
})
public class ConsultaUsuarioResponse {

    @XmlElement(name = "xml-resultado")
    protected Resultado xmlResultado;

    /**
     * Gets the value of the xmlResultado property.
     * 
     * @return
     *     possible object is
     *     {@link Resultado }
     *     
     */
    public Resultado getXmlResultado() {
        return xmlResultado;
    }

    /**
     * Sets the value of the xmlResultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Resultado }
     *     
     */
    public void setXmlResultado(Resultado value) {
        this.xmlResultado = value;
    }

}
