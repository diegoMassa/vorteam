
package com.proto.server.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import entidad.ws.server.proto.com.resultado.Resultado;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.proto.server.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CambiarContrasena_QNAME = new QName("http://ws.server.proto.com/", "cambiar-contrasena");
    private final static QName _CambiarContrasenaResponse_QNAME = new QName("http://ws.server.proto.com/", "cambiar-contrasenaResponse");
    private final static QName _ConsultaUsuario_QNAME = new QName("http://ws.server.proto.com/", "consulta-usuario");
    private final static QName _ConsultaUsuarioResponse_QNAME = new QName("http://ws.server.proto.com/", "consulta-usuarioResponse");
    private final static QName _CrearUsuario_QNAME = new QName("http://ws.server.proto.com/", "crear-usuario");
    private final static QName _CrearUsuarioResponse_QNAME = new QName("http://ws.server.proto.com/", "crear-usuarioResponse");
    private final static QName _InactivarActivarUsuario_QNAME = new QName("http://ws.server.proto.com/", "inactivar-activar-usuario");
    private final static QName _InactivarActivarUsuarioResponse_QNAME = new QName("http://ws.server.proto.com/", "inactivar-activar-usuarioResponse");
    private final static QName _ModificarUsuario_QNAME = new QName("http://ws.server.proto.com/", "modificar-usuario");
    private final static QName _ModificarUsuarioResponse_QNAME = new QName("http://ws.server.proto.com/", "modificar-usuarioResponse");
    private final static QName _ObtenerRoles_QNAME = new QName("http://ws.server.proto.com/", "obtener-roles");
    private final static QName _ObtenerRolesResponse_QNAME = new QName("http://ws.server.proto.com/", "obtener-rolesResponse");
    private final static QName _Resultado_QNAME = new QName("http://ws.server.proto.com/", "resultado");
    private final static QName _Username_QNAME = new QName("http://ws.server.proto.com/", "username");
    private final static QName _Password_QNAME = new QName("http://ws.server.proto.com/", "password");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.proto.server.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CambiarContrasena }
     * 
     */
    public CambiarContrasena createCambiarContrasena() {
        return new CambiarContrasena();
    }

    /**
     * Create an instance of {@link CambiarContrasenaResponse }
     * 
     */
    public CambiarContrasenaResponse createCambiarContrasenaResponse() {
        return new CambiarContrasenaResponse();
    }

    /**
     * Create an instance of {@link ConsultaUsuario }
     * 
     */
    public ConsultaUsuario createConsultaUsuario() {
        return new ConsultaUsuario();
    }

    /**
     * Create an instance of {@link ConsultaUsuarioResponse }
     * 
     */
    public ConsultaUsuarioResponse createConsultaUsuarioResponse() {
        return new ConsultaUsuarioResponse();
    }

    /**
     * Create an instance of {@link CrearUsuario }
     * 
     */
    public CrearUsuario createCrearUsuario() {
        return new CrearUsuario();
    }

    /**
     * Create an instance of {@link CrearUsuarioResponse }
     * 
     */
    public CrearUsuarioResponse createCrearUsuarioResponse() {
        return new CrearUsuarioResponse();
    }

    /**
     * Create an instance of {@link InactivarActivarUsuario }
     * 
     */
    public InactivarActivarUsuario createInactivarActivarUsuario() {
        return new InactivarActivarUsuario();
    }

    /**
     * Create an instance of {@link InactivarActivarUsuarioResponse }
     * 
     */
    public InactivarActivarUsuarioResponse createInactivarActivarUsuarioResponse() {
        return new InactivarActivarUsuarioResponse();
    }

    /**
     * Create an instance of {@link ModificarUsuario }
     * 
     */
    public ModificarUsuario createModificarUsuario() {
        return new ModificarUsuario();
    }

    /**
     * Create an instance of {@link ModificarUsuarioResponse }
     * 
     */
    public ModificarUsuarioResponse createModificarUsuarioResponse() {
        return new ModificarUsuarioResponse();
    }

    /**
     * Create an instance of {@link ObtenerRoles }
     * 
     */
    public ObtenerRoles createObtenerRoles() {
        return new ObtenerRoles();
    }

    /**
     * Create an instance of {@link ObtenerRolesResponse }
     * 
     */
    public ObtenerRolesResponse createObtenerRolesResponse() {
        return new ObtenerRolesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CambiarContrasena }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.proto.com/", name = "cambiar-contrasena")
    public JAXBElement<CambiarContrasena> createCambiarContrasena(CambiarContrasena value) {
        return new JAXBElement<CambiarContrasena>(_CambiarContrasena_QNAME, CambiarContrasena.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CambiarContrasenaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.proto.com/", name = "cambiar-contrasenaResponse")
    public JAXBElement<CambiarContrasenaResponse> createCambiarContrasenaResponse(CambiarContrasenaResponse value) {
        return new JAXBElement<CambiarContrasenaResponse>(_CambiarContrasenaResponse_QNAME, CambiarContrasenaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaUsuario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.proto.com/", name = "consulta-usuario")
    public JAXBElement<ConsultaUsuario> createConsultaUsuario(ConsultaUsuario value) {
        return new JAXBElement<ConsultaUsuario>(_ConsultaUsuario_QNAME, ConsultaUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaUsuarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.proto.com/", name = "consulta-usuarioResponse")
    public JAXBElement<ConsultaUsuarioResponse> createConsultaUsuarioResponse(ConsultaUsuarioResponse value) {
        return new JAXBElement<ConsultaUsuarioResponse>(_ConsultaUsuarioResponse_QNAME, ConsultaUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrearUsuario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.proto.com/", name = "crear-usuario")
    public JAXBElement<CrearUsuario> createCrearUsuario(CrearUsuario value) {
        return new JAXBElement<CrearUsuario>(_CrearUsuario_QNAME, CrearUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrearUsuarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.proto.com/", name = "crear-usuarioResponse")
    public JAXBElement<CrearUsuarioResponse> createCrearUsuarioResponse(CrearUsuarioResponse value) {
        return new JAXBElement<CrearUsuarioResponse>(_CrearUsuarioResponse_QNAME, CrearUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InactivarActivarUsuario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.proto.com/", name = "inactivar-activar-usuario")
    public JAXBElement<InactivarActivarUsuario> createInactivarActivarUsuario(InactivarActivarUsuario value) {
        return new JAXBElement<InactivarActivarUsuario>(_InactivarActivarUsuario_QNAME, InactivarActivarUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InactivarActivarUsuarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.proto.com/", name = "inactivar-activar-usuarioResponse")
    public JAXBElement<InactivarActivarUsuarioResponse> createInactivarActivarUsuarioResponse(InactivarActivarUsuarioResponse value) {
        return new JAXBElement<InactivarActivarUsuarioResponse>(_InactivarActivarUsuarioResponse_QNAME, InactivarActivarUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModificarUsuario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.proto.com/", name = "modificar-usuario")
    public JAXBElement<ModificarUsuario> createModificarUsuario(ModificarUsuario value) {
        return new JAXBElement<ModificarUsuario>(_ModificarUsuario_QNAME, ModificarUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModificarUsuarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.proto.com/", name = "modificar-usuarioResponse")
    public JAXBElement<ModificarUsuarioResponse> createModificarUsuarioResponse(ModificarUsuarioResponse value) {
        return new JAXBElement<ModificarUsuarioResponse>(_ModificarUsuarioResponse_QNAME, ModificarUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerRoles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.proto.com/", name = "obtener-roles")
    public JAXBElement<ObtenerRoles> createObtenerRoles(ObtenerRoles value) {
        return new JAXBElement<ObtenerRoles>(_ObtenerRoles_QNAME, ObtenerRoles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerRolesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.proto.com/", name = "obtener-rolesResponse")
    public JAXBElement<ObtenerRolesResponse> createObtenerRolesResponse(ObtenerRolesResponse value) {
        return new JAXBElement<ObtenerRolesResponse>(_ObtenerRolesResponse_QNAME, ObtenerRolesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Resultado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.proto.com/", name = "resultado")
    public JAXBElement<Resultado> createResultado(Resultado value) {
        return new JAXBElement<Resultado>(_Resultado_QNAME, Resultado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.proto.com/", name = "username")
    public JAXBElement<String> createUsername(String value) {
        return new JAXBElement<String>(_Username_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.server.proto.com/", name = "password")
    public JAXBElement<String> createPassword(String value) {
        return new JAXBElement<String>(_Password_QNAME, String.class, null, value);
    }

}
