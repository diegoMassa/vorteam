package com.vortexbird.sentinel.presentation.backingBeans;

import java.io.Serializable;
import java.io.StringReader;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.primefaces.component.inputmask.InputMask;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.proto.server.ws.AdminUsuarios;
import com.proto.server.ws.AdminUsuarios_Service;
import com.proto.server.ws.ObtenerRoles;
import com.proto.server.ws.ObtenerRolesResponse;
import com.vortexbird.sentinel.modelo.VinCamposParametrizables;
import com.vortexbird.sentinel.modelo.dto.RollCrmDTO;
import com.vortexbird.sentinel.modelo.dto.RollVcloudDTO;
import com.vortexbird.sentinel.modelo.dto.RollesVcloudDTO;
import com.vortexbird.sentinel.modelo.dto.SegUsuarioDTO;
import com.vortexbird.sentinel.modelo.dto.SellCiudadDTO;
import com.vortexbird.sentinel.modelo.dto.SellPersonaDTO;
import com.vortexbird.sentinel.modelo.dto.SellSucursalDTO;
import com.vortexbird.sentinel.modelo.dto.SellTipoDocumentoDTO;
import com.vortexbird.sentinel.modelo.dto.UsuarioCRMDTO;
import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;
import com.vortexbird.sentinel.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.sentinel.rest.cliente.ISentinelRestServiceClient;
import com.vortexbird.sentinel.utilities.FacesUtils;
import com.vortexbird.sentinel.utilities.Utilities;

@ManagedBean
@ViewScoped
public class CreateUserAllSystem implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CreateUserAllSystem.class);

	// Variables Para tabla sell_persona
	private InputMask txtCelular;
	private InputText txtDireccion;
	private InputText txtEmail;
	private InputText txtNumeroDocumento;
	private InputText txtPrimerApellido;
	private InputText txtPrimerNombre;
	private InputText txtRazonSocial;
	private InputText txtSegundoApellido;
	private InputText txtSegundoNombre;
	private Date txtFechaNacimiento;

	private SellPersonaDTO selectedSellPersona;
	private List<SellPersonaDTO> listAsesores;

	private SelectOneMenu somTipoDocumento;
	private List<SellTipoDocumentoDTO> listTiposDocumento;
	private SelectItem[] losTipoDocumento;

	private Date txtFechaIngreso;
	private SelectOneMenu somGenero;
	private SelectOneRadio sorTieneWhatsapp;

	private SelectOneMenu somSucursales;
	private SelectItem[] lasSucursales;
	private SelectItem[] losRollesCrm;
	private SelectItem[] losRollesVcloud;
	private String[] somRolesSellou;
	private String somRolesVCloud;
	private String somRolesCRM;
	private List<SellSucursalDTO> listSucursales;
	private List<RollCrmDTO> listRollesCRM;
	private List<RollesVcloudDTO> listRollesVCloud;

	private SellPersonaDTO usuario;

	private SelectOneMenu somCiudadResidencia;
	private SelectItem[] lasCiudadesResidencia;
	private List<SellCiudadDTO> listCiudades;

	private SelectOneRadio sorEstadoRegistro;

	private static final QName SERVICE_NAME = new QName("http://ws.server.proto.com/", "AdminUsuarios");

	// Datos SegUsuario
	private List<SegUsuarioDTO> data;

	private boolean enSellout;
	private boolean enCrm;
	private boolean enVcloud;

	private boolean desactivarDatosSellout = false;
	private boolean desactivarDatosVcloud = false;
	private boolean desactivarDatosCRM = false;

	private String[] sucursalesSeleccionadas;

	private UsuarioDTO usuarioDTO;

	private String parametroUrlVin;
	private String userVcloud;
	private String passVcloud;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	@ManagedProperty(value = "#{SentinelRestServiceClient}")
	private ISentinelRestServiceClient sentinelRestServiceClient;

	@PostConstruct
	public void init() {
		usuarioDTO = (UsuarioDTO) FacesUtils.getManagedBeanFromSession("usuarioDTO");
		
		try {
			// Variables para consultar por estado registro activo
			Object[] variableSERVICIO_WEB_ADMINUSUARIOS = { "estadoRegistro", true,
					com.vortexbird.sentinel.utilities.Constantes.ESTADO_ACTIVO, "=", "llave", true,
					com.vortexbird.sentinel.utilities.Constantes.SERVICIO_WEB_ADMINUSUARIOS, "=" };

			List <VinCamposParametrizables> parametroUrlVin = businessDelegatorView.findByCriteriaInVinCamposParametrizables(variableSERVICIO_WEB_ADMINUSUARIOS, null, null);

			if (parametroUrlVin == null || parametroUrlVin.isEmpty() == true) {
				log.error("No se encontro el parametro 22");
				throw new Exception("No se encontro el parametro 22");
			}
			
			this.parametroUrlVin = parametroUrlVin.get(0).getValor();
			
			Object[] variableSERVICIO_WEB_USER_PASS = { "estadoRegistro", true,
					com.vortexbird.sentinel.utilities.Constantes.ESTADO_ACTIVO, "=", "llave", true,
					com.vortexbird.sentinel.utilities.Constantes.SERVICIO_WEB_USER_PASS, "=" };

			List <VinCamposParametrizables> passVcloud = businessDelegatorView.findByCriteriaInVinCamposParametrizables(variableSERVICIO_WEB_USER_PASS, null, null);
			
			if (passVcloud == null || passVcloud.isEmpty()) {
				log.error("No existe el parametro 23 - llave ws");
				throw new Exception("No existe el parametro 23 - llave ws");
			}
			
			this.userVcloud = passVcloud.get(0).getValor().split("-")[0];
			this.passVcloud = passVcloud.get(0).getValor().split("-")[1];
			
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			log.error(e.getMessage());
		}
	
	}

	public CreateUserAllSystem() {
	}

	public String procesoCreateAllSystem(FlowEvent event) {
		return event.getNewStep();
	}

	public void descactivarPanel() {
		if (enSellout == true) {
			desactivarDatosSellout = true;
		} else {
			desactivarDatosSellout = false;
		}
	}

	public void desactivarVcloud() {
		if (enVcloud == true) {
			desactivarDatosVcloud = true;
		} else {
			desactivarDatosVcloud = false;
		}
	}

	public void desactivarCRM() {
		if (enCrm == true) {
			desactivarDatosCRM = true;
		} else {
			desactivarDatosCRM = false;
		}
	}

	public void listener_numero_documento(){
		SellPersonaDTO persona = null;
		try {
			//validamos el que no sea vacio
			if (txtNumeroDocumento.getValue() != null || !txtNumeroDocumento.getValue().toString().equals("")) {
				
				String cedula = txtNumeroDocumento.getValue().toString();
				// Consulto En sellout
				persona = sentinelRestServiceClient.consultarPersonaByCedula(cedula);
		
				
				if (persona != null ) {
					txtPrimerNombre.setValue(persona.getPrimerNombre());
					txtSegundoNombre.setValue(persona.getSegundoNombre());
					txtPrimerApellido.setValue(persona.getPrimerApellido());
					txtSegundoApellido.setValue(persona.getSegundoApellido());
					txtCelular.setValue(persona.getCelular());
					txtEmail.setValue(persona.getEmail());
					somGenero.setValue(persona.getGenero());
					sorTieneWhatsapp.setValue(persona.getTieneWhatsapp());
					txtDireccion.setValue(persona.getDireccion());
					txtFechaNacimiento = persona.getFechaNacimiento();
					txtFechaIngreso = persona.getFechaIngreso();
					somCiudadResidencia.setValue(persona.getCodigoCiudad());
				} else {
					limpiar();
				}
			}else {
				limpiar();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			limpiar();
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public AdminUsuarios integracionVcloud() {
		try {
			
			URL url = new URL(parametroUrlVin);
			AdminUsuarios_Service ss = new AdminUsuarios_Service(url, SERVICE_NAME);
			AdminUsuarios port = ss.getAdminUsuariosPort();
			BindingProvider bp = (BindingProvider) port;
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, parametroUrlVin);
			return port;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(""+e.getMessage());
			log.error(e.getMessage());
			return null;
		}
	}

	public void abrirPu() {
		limpiar();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('dialogDatos').show()");
	}

	public void limpiar() {
		txtPrimerNombre.setValue("");
		txtSegundoNombre.setValue("");
		txtPrimerApellido.setValue("");
		txtSegundoApellido.setValue("");
		txtCelular.setValue("");
		txtEmail.setValue("");
		somGenero.setValue("");
		sorTieneWhatsapp.setValue("");
		txtDireccion.setValue("");
		txtFechaNacimiento = null;
		txtFechaIngreso = null;
		//txtNumeroDocumento.setValue("");
		somCiudadResidencia.setValue("");
		enSellout = false;
		enVcloud = false;
		enCrm = false;
		desactivarDatosVcloud = false;
		desactivarDatosSellout = false;
		somRolesSellou=null;
		somRolesVCloud=null;
		somRolesCRM=null;
		desactivarDatosCRM = false;

	}

	public void createUserSystemSelectec() {

		String primerNombre = "";
		String primerApellido = "";
		String cadenaRespuesta = "";
		long sucuXPuntoVenta=0;
		List<RollCrmDTO> lstRolesCRM = null;
		
		try {
			if (txtNumeroDocumento.getValue() == null || txtNumeroDocumento.getValue().toString().equals("")) {
				throw new Exception("Debe indicar el numero de documento");
			}

			// Validaciones Generales del formulario
			if (sucursalesSeleccionadas == null || sucursalesSeleccionadas.length == 0) {
				throw new Exception("Debe seleccionar una sucursal valida");
			}
			
			if (sucursalesSeleccionadas.length==listSucursales.size()) {
				log.info("se creara como administrador");
			}else {
				if (sucursalesSeleccionadas.length > 1) {
					 sucuXPuntoVenta = sentinelRestServiceClient.getSucuXPuntoVenta(sucursalesSeleccionadas);
					if (sucuXPuntoVenta==0) {
						throw new Exception("No puede seleccionar sucursales de diferentes puntos de venta");
					}
				}
			}
			
			
			if (txtPrimerNombre == null || txtPrimerNombre.equals("")) {
				throw new Exception("Debe indicar el primer nombre de manera obligatoria");
			}

			if (txtPrimerApellido.getValue() == null || txtPrimerApellido.getValue().toString().equals("")) {
				throw new Exception("Debe indicar el primer apellido de manera obligatoria");
			}
			primerApellido = txtPrimerApellido.getValue().toString();

			if (txtCelular.getValue() == null || txtCelular.getValue().toString().equals("")) {
				throw new Exception("Debe indicar el numero de celular");
			}

			if (txtEmail.getValue() == null || txtEmail.getValue().toString().equals("")) {
				throw new Exception("El correo electronico es obligatorio");
			}

			if (Utilities.validateEmailAddress(txtEmail.getValue().toString()) == false) {
				throw new Exception("Debe ingresar un correo electronico valido");
			}

			Integer persId = null;
			Integer asesId = null;
			String numeroDocumento = txtNumeroDocumento.getValue().toString();
			primerNombre = txtPrimerNombre.getValue().toString();
			String segundoNombre = txtSegundoNombre.getValue() != null ? txtSegundoNombre.getValue().toString() : null;
			primerApellido = txtPrimerApellido.getValue().toString();
			String segundoApellido = txtSegundoApellido.getValue() != null ? txtSegundoApellido.getValue().toString()
					: null;
			String celular = txtCelular.getValue().toString();
			String email = txtEmail.getValue().toString();

			SellPersonaDTO personaDTO = new SellPersonaDTO();

			personaDTO.setPersId(persId);
			personaDTO.setAsesorId(asesId);
			personaDTO.setSucursalesSeleccionadas(sucursalesSeleccionadas);
			personaDTO.setNumeroDocumento(numeroDocumento);
			personaDTO.setPrimerNombre(primerNombre);
			personaDTO.setSegundoNombre(segundoNombre);
			personaDTO.setPrimerApellido(primerApellido);
			personaDTO.setSegundoApellido(segundoApellido);
			personaDTO.setCelular(celular);
			personaDTO.setEmail(email);

			if (isEnSellout() == true) {

				if (somRolesSellou == null || somRolesSellou.length == 0) {
					throw new Exception("Debe seleccionar un roll en sellout");
				}

				if (sorTieneWhatsapp.getValue() == null || (!sorTieneWhatsapp.getValue().toString().equals("N")
						&& !sorTieneWhatsapp.getValue().toString().equals("S"))) {
					throw new Exception("Se debe indicar si tiene o no whatsapp");
				}

				if (txtDireccion.getValue() == null || txtDireccion.getValue().toString().equals("")) {
					throw new Exception("Debe indicar una direccion valida");
				}

				if (txtFechaNacimiento == null) {
					throw new Exception("Debe ingresar la fecha de Nacimiento");
				}

				if (txtFechaNacimiento.getTime() >= new Date().getTime()) {
					throw new Exception("La fecha de nacimiento debe ser menor a la fecha actual");
				}

				if (txtFechaIngreso == null) {
					throw new Exception("Debe ingresar la fecha de ingreso");
				}

				if (txtFechaIngreso.getTime() >= new Date().getTime()) {
					throw new Exception("La fecha de ingreso debe ser menor a la fecha actual");
				}

				if (somCiudadResidencia.getValue() == null || somCiudadResidencia.getValue().toString().equals("-1")) {
					throw new Exception("Debe indicar una ciudad de residencia valida");
				}

				if (somGenero.getValue() == null || somGenero.getValue().toString().equals("-1")) {
					throw new Exception("Debe indicar el genero");
				}

				String tieneWhatsApp = sorTieneWhatsapp.getValue().toString();
				String direccion = txtDireccion.getValue().toString();
				String genero = somGenero.getValue().toString();
				if (somCiudadResidencia.getValue().toString().equals("-1")) {
					personaDTO.setCiudId_SellCiudad(null);
				} else {
					Integer idCiudadResidencia = Integer.parseInt(somCiudadResidencia.getValue().toString());
					personaDTO.setCiudId_SellCiudad(idCiudadResidencia);
				}

				personaDTO.setDireccion(direccion);
				personaDTO.setEmail(email);
				personaDTO.setEstadoRegistro("A");
				personaDTO.setFechaCreacion(new Date());
				personaDTO.setFechaIngreso(txtFechaIngreso);
				personaDTO.setFechaNacimiento(txtFechaNacimiento);
				personaDTO.setGenero(genero);
				personaDTO.setOperCreador(0L);
				personaDTO.setTidoId_SellTipoDocumento(2);
				personaDTO.setTieneWhatsapp(tieneWhatsApp);
				// 1---> administrador
				// 2---> adminitrador sellout vin "pantalla para motorred"
				// 3---> asesor

				personaDTO.setSomRolesSellou(somRolesSellou);
				sentinelRestServiceClient.guardarAsesor(personaDTO);
				cadenaRespuesta = cadenaRespuesta + "Sellout ";
			}

			if (isEnCrm() == true) {
//				String rollesCrmString = "";
//				for (int i = 0; i < somRolesCRM.length; i++) {
//					rollesCrmString = rollesCrmString + somRolesCRM[i];
//				}

				if (somRolesCRM == null || somRolesCRM.equals("")) {
					throw new Exception("Debe Selleccionar un roll");
				}

				UsuarioCRMDTO crmUser = new UsuarioCRMDTO();
				crmUser.setNombres(primerNombre + " " + segundoNombre);
				crmUser.setApellidos(primerApellido + " " + segundoApellido);
				crmUser.setLogin(numeroDocumento);
				crmUser.setPassword(Utilities.crearContraseña(primerNombre, primerApellido, numeroDocumento));
				crmUser.setCedula(numeroDocumento);
				crmUser.setCorreo(email);
				String sucursalesParaCrm = "";

				for (int i = 0; i < sucursalesSeleccionadas.length; i++) {
					sucursalesParaCrm = sucursalesParaCrm + sucursalesSeleccionadas[i];
					if (i + 1 != sucursalesSeleccionadas.length) {
						sucursalesParaCrm = sucursalesParaCrm + "|";
					}
				}
				crmUser.setSellout("1");// No se cual es el criterio para
										// decidir si puede o no puede
				crmUser.setSucursales(sucursalesParaCrm);
				
				//Se consulta los roles CRM por cedula
				lstRolesCRM = sentinelRestServiceClient.consultarRolesCRM(numeroDocumento);
				
				//SI trae roles significa que existe y se llama al metodo de modificar, en caso de no traer roles se debe crear el usuario
				if(lstRolesCRM == null  || lstRolesCRM.isEmpty()){
					sentinelRestServiceClient.crearUsuarioCRM(crmUser, somRolesCRM);
				}else{
					sentinelRestServiceClient.modificarUsuarioCRM(crmUser, somRolesCRM);
				}
				
				cadenaRespuesta = cadenaRespuesta + "CRM ";
				
			
			}
			if (isEnVcloud() == true) {

		
				
				if (somRolesVCloud.equals("")) {
					throw new Exception("Debe Selleccionar un roll");
				}

				UsuarioCRMDTO vcloudUser = new UsuarioCRMDTO();
				vcloudUser.setNombres(primerNombre + " " + segundoNombre);
				vcloudUser.setApellidos(primerApellido + " " + segundoApellido);
				vcloudUser.setLogin(numeroDocumento);
				vcloudUser.setPassword(Utilities.crearContraseña(primerNombre, primerApellido, numeroDocumento));
				vcloudUser.setCedula(numeroDocumento);
				vcloudUser.setCorreo(email);
				vcloudUser.setCelular(celular);
				vcloudUser.setCodigoConcesionario(sucuXPuntoVenta+"");

			    
			    String respuesta = sentinelRestServiceClient.consultarUsuarioVCloud(vcloudUser, somRolesVCloud, SERVICE_NAME,
						sucursalesSeleccionadas, parametroUrlVin,sucuXPuntoVenta,userVcloud,passVcloud);
			    
			    if (respuesta.equals("1")) {
			    	sentinelRestServiceClient.actualizarUsuarioVCloud(vcloudUser, somRolesVCloud, SERVICE_NAME,
							sucursalesSeleccionadas, parametroUrlVin,sucuXPuntoVenta,userVcloud,passVcloud);

				}else {
					sentinelRestServiceClient.crearUsuarioVCloud(vcloudUser, somRolesVCloud, SERVICE_NAME,
							sucursalesSeleccionadas, parametroUrlVin,sucuXPuntoVenta,userVcloud,passVcloud);

				}
			    
			
				cadenaRespuesta = cadenaRespuesta + "VCloud ";
			}

			// Si almenos uno es correcto Guardo el usuario
			if (isEnSellout() == true || isEnCrm() == true || isEnVcloud() == true) {
				 
					businessDelegatorView.crearUsuario(personaDTO);
					//TODO aqui se deben agregar los permisos
					businessDelegatorView.saveSegPermisoAllUser(isEnCrm(),isEnVcloud(),isEnSellout(),personaDTO);	
					
					if (isEnSellout() == true ) {
						businessDelegatorView.agregarRolles(personaDTO);
					}
			
			} else {
				throw new Exception("Debe seleccionar al menos un sistema donde Necesite crear el usaurio");
			}
			
			txtNumeroDocumento.setValue("");
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.execute("PF('dialogDatos').hide()");
			FacesUtils.addInfoMessage("Usuario Creado en : " + cadenaRespuesta);
			limpiar();
			data = null;

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public InputMask getTxtCelular() {
		return txtCelular;
	}

	public void setTxtCelular(InputMask txtCelular) {
		this.txtCelular = txtCelular;
	}

	public InputText getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(InputText txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public InputText getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(InputText txtEmail) {
		this.txtEmail = txtEmail;
	}

	public InputText getTxtNumeroDocumento() {
		return txtNumeroDocumento;
	}

	public void setTxtNumeroDocumento(InputText txtNumeroDocumento) {
		this.txtNumeroDocumento = txtNumeroDocumento;
	}

	public InputText getTxtPrimerApellido() {
		return txtPrimerApellido;
	}

	public void setTxtPrimerApellido(InputText txtPrimerApellido) {
		this.txtPrimerApellido = txtPrimerApellido;
	}

	public InputText getTxtPrimerNombre() {
		return txtPrimerNombre;
	}

	public void setTxtPrimerNombre(InputText txtPrimerNombre) {
		this.txtPrimerNombre = txtPrimerNombre;
	}

	public InputText getTxtRazonSocial() {
		return txtRazonSocial;
	}

	public void setTxtRazonSocial(InputText txtRazonSocial) {
		this.txtRazonSocial = txtRazonSocial;
	}

	public InputText getTxtSegundoApellido() {
		return txtSegundoApellido;
	}

	public void setTxtSegundoApellido(InputText txtSegundoApellido) {
		this.txtSegundoApellido = txtSegundoApellido;
	}

	public InputText getTxtSegundoNombre() {
		return txtSegundoNombre;
	}

	public void setTxtSegundoNombre(InputText txtSegundoNombre) {
		this.txtSegundoNombre = txtSegundoNombre;
	}

	public Date getTxtFechaNacimiento() {
		return txtFechaNacimiento;
	}

	public void setTxtFechaNacimiento(Date txtFechaNacimiento) {
		this.txtFechaNacimiento = txtFechaNacimiento;
	}

	public SellPersonaDTO getSelectedSellPersona() {
		return selectedSellPersona;
	}

	public void setSelectedSellPersona(SellPersonaDTO selectedSellPersona) {
		this.selectedSellPersona = selectedSellPersona;
	}

	public List<SellPersonaDTO> getListAsesores() {
		return listAsesores;
	}

	public void setListAsesores(List<SellPersonaDTO> listAsesores) {
		this.listAsesores = listAsesores;
	}

	public SelectOneMenu getSomTipoDocumento() {
		return somTipoDocumento;
	}

	public void setSomTipoDocumento(SelectOneMenu somTipoDocumento) {
		this.somTipoDocumento = somTipoDocumento;
	}

	public List<SellTipoDocumentoDTO> getListTiposDocumento() {
		return listTiposDocumento;
	}

	public void setListTiposDocumento(List<SellTipoDocumentoDTO> listTiposDocumento) {
		this.listTiposDocumento = listTiposDocumento;
	}

	public SelectItem[] getLosTipoDocumento() {
		return losTipoDocumento;
	}

	public void setLosTipoDocumento(SelectItem[] losTipoDocumento) {
		this.losTipoDocumento = losTipoDocumento;
	}

	public SelectOneMenu getSomGenero() {
		return somGenero;
	}

	public void setSomGenero(SelectOneMenu somGenero) {
		this.somGenero = somGenero;
	}

	public SelectOneRadio getSorTieneWhatsapp() {
		return sorTieneWhatsapp;
	}

	public void setSorTieneWhatsapp(SelectOneRadio sorTieneWhatsapp) {
		this.sorTieneWhatsapp = sorTieneWhatsapp;
	}

	public SelectOneMenu getSomSucursales() {
		return somSucursales;
	}

	public void setSomSucursales(SelectOneMenu somSucursales) {
		this.somSucursales = somSucursales;
	}

	public SelectItem[] getLasSucursales() {
		try {
			if (listSucursales == null) {
				listSucursales = sentinelRestServiceClient.ConsultarTodasLasSucursales().getLstSucursal();
				lasSucursales = new SelectItem[listSucursales.size()];
				int i = 0;
				for (SellSucursalDTO sucursal : listSucursales) {
					lasSucursales[i] = new SelectItem(sucursal.getSucu_codigo(), sucursal.getSucu_descripcion());
					i++;
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return lasSucursales;
	}

	public SelectItem[] getLosRollesCrm() {
		if (listRollesCRM == null) {
			try {
				listRollesCRM = sentinelRestServiceClient.consultarRollesCRM();
				losRollesCrm = new SelectItem[listRollesCRM.size()];
				int i = 0;
				for (RollCrmDTO rollCrm : listRollesCRM) {
					losRollesCrm[i] = new SelectItem(rollCrm.getName(), rollCrm.getName());
					i++;
				}
			} catch (Exception e) {
				FacesUtils.addErrorMessage(e.getMessage());
				log.error(e.getMessage(), e);
				
			}
		}

		return losRollesCrm;
	}

	public SelectItem[] getLosRollesVcloud() {
		if (losRollesVcloud == null || losRollesVcloud.length == 0) {
			try {
				
				log.info("Consulto Rolles Vcloud");
				AdminUsuarios ws = integracionVcloud();
				ObtenerRolesResponse obtenerRoles = ws.obtenerRoles(new ObtenerRoles(), userVcloud, passVcloud);
				JAXBContext jaxbContext = JAXBContext.newInstance(RollesVcloudDTO.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				RollesVcloudDTO roles = (RollesVcloudDTO) jaxbUnmarshaller
						.unmarshal(new StringReader(obtenerRoles.getXmlRegistros()));
				if (losRollesVcloud==null) {
					throw new Exception("Error con credenciales en Vcloud o el servicio fallo");
				}
				losRollesVcloud = new SelectItem[roles.getRegistros().size()];
				int i = 0;
				for (RollVcloudDTO rol : roles.getRegistros()) {
					losRollesVcloud[i] = new SelectItem(rol.getId(), rol.getPerfil());
					i++;
				}
				log.info("Sale del metodo Rolles de Vcloud");
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				FacesUtils.addErrorMessage(e.getMessage());
			}

		}

		return losRollesVcloud;
	}

	public void setLosRollesVcloud(SelectItem[] losRollesVcloud) {
		this.losRollesVcloud = losRollesVcloud;
	}

	public List<RollesVcloudDTO> getListRollesVCloud() {
		return listRollesVCloud;
	}

	public void setListRollesVCloud(List<RollesVcloudDTO> listRollesVCloud) {
		this.listRollesVCloud = listRollesVCloud;
	}

	public void setLosRollesCrm(SelectItem[] losRollesCrm) {
		this.losRollesCrm = losRollesCrm;
	}

	public void setLasSucursales(SelectItem[] lasSucursales) {
		this.lasSucursales = lasSucursales;
	}

	public String[] getSucursalesSeleccionadas() {
		return sucursalesSeleccionadas;
	}

	public void setSucursalesSeleccionadas(String[] sucursalesSeleccionadas) {
		this.sucursalesSeleccionadas = sucursalesSeleccionadas;
	}

	public List<SellSucursalDTO> getListSucursales() {
		return listSucursales;
	}

	public void setListSucursales(List<SellSucursalDTO> listSucursales) {
		this.listSucursales = listSucursales;
	}

	public SellPersonaDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(SellPersonaDTO usuario) {
		this.usuario = usuario;
	}

	public SelectOneMenu getSomCiudadResidencia() {
		return somCiudadResidencia;
	}

	public void setSomCiudadResidencia(SelectOneMenu somCiudadResidencia) {
		this.somCiudadResidencia = somCiudadResidencia;
	}

	public SelectItem[] getLasCiudadesResidencia() {
		try {
			if (listCiudades == null) {
				listCiudades = sentinelRestServiceClient.consultarCiudades().getLstCiudad();
				lasCiudadesResidencia = new SelectItem[listCiudades.size()];
				int i = 0;
				for (SellCiudadDTO ciudad : listCiudades) {
					lasCiudadesResidencia[i] = new SelectItem(ciudad.getCiudId(), ciudad.getDescripcion());
					i++;
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return lasCiudadesResidencia;
	}

	public List<RollCrmDTO> getListRollesCRM() {
		return listRollesCRM;
	}

	public void setListRollesCRM(List<RollCrmDTO> listRollesCRM) {
		this.listRollesCRM = listRollesCRM;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public void setLasCiudadesResidencia(SelectItem[] lasCiudadesResidencia) {
		this.lasCiudadesResidencia = lasCiudadesResidencia;
	}

	public List<SellCiudadDTO> getListCiudades() {
		return listCiudades;
	}

	public void setListCiudades(List<SellCiudadDTO> listCiudades) {
		this.listCiudades = listCiudades;
	}

	public SelectOneRadio getSorEstadoRegistro() {
		return sorEstadoRegistro;
	}

	public void setSorEstadoRegistro(SelectOneRadio sorEstadoRegistro) {
		this.sorEstadoRegistro = sorEstadoRegistro;
	}

	public List<SegUsuarioDTO> getData() {
		if (data == null) {
			try {
				data = businessDelegatorView.getDataSegUsuario();
			} catch (Exception e) {
				log.error(e.getMessage(), e.getCause());
			}
		}
		return data;
	}

	public void setData(List<SegUsuarioDTO> data) {
		this.data = data;
	}

	public Date getTxtFechaIngreso() {
		return txtFechaIngreso;
	}

	public void setTxtFechaIngreso(Date txtFechaIngreso) {
		this.txtFechaIngreso = txtFechaIngreso;
	}

	public boolean isEnSellout() {
		return enSellout;
	}

	public void setEnSellout(boolean enSellout) {
		this.enSellout = enSellout;
	}

	public boolean isEnCrm() {
		return enCrm;
	}

	public void setEnCrm(boolean enCrm) {
		this.enCrm = enCrm;
	}

	public boolean isEnVcloud() {
		return enVcloud;
	}

	public void setEnVcloud(boolean enVcloud) {
		this.enVcloud = enVcloud;
	}

	public String[] getSomRolesSellou() {
		return somRolesSellou;
	}

	public void setSomRolesSellou(String[] somRolesSellou) {
		this.somRolesSellou = somRolesSellou;
	}

	public boolean isDesactivarDatosSellout() {
		return desactivarDatosSellout;
	}

	public void setDesactivarDatosSellout(boolean desactivarDatosSellout) {
		this.desactivarDatosSellout = desactivarDatosSellout;
	}

	public boolean isDesactivarDatosVcloud() {
		return desactivarDatosVcloud;
	}

	public void setDesactivarDatosVcloud(boolean desactivarDatosVcloud) {
		this.desactivarDatosVcloud = desactivarDatosVcloud;
	}

	public boolean isDesactivarDatosCRM() {
		return desactivarDatosCRM;
	}

	public void setDesactivarDatosCRM(boolean desactivarDatosCRM) {
		this.desactivarDatosCRM = desactivarDatosCRM;
	}

	public String getSomRolesVCloud() {
		return somRolesVCloud;
	}

	public void setSomRolesVCloud(String somRolesVCloud) {
		this.somRolesVCloud = somRolesVCloud;
	}


	public String getSomRolesCRM() {
		return somRolesCRM;
	}

	public void setSomRolesCRM(String somRolesCRM) {
		this.somRolesCRM = somRolesCRM;
	}

	public ISentinelRestServiceClient getSentinelRestServiceClient() {
		return sentinelRestServiceClient;
	}

	public void setSentinelRestServiceClient(ISentinelRestServiceClient sentinelRestServiceClient) {
		this.sentinelRestServiceClient = sentinelRestServiceClient;
	}

}
