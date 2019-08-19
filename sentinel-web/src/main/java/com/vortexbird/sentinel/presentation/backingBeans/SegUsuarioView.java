package com.vortexbird.sentinel.presentation.backingBeans;

import org.apache.log4j.Logger;
import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegUsuarioDTO;
import com.vortexbird.sentinel.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.sentinel.utilities.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean
@ViewScoped
public class SegUsuarioView {
	private InputText txtUsuApellidos;
	private InputText txtUsuCodigoInterno;
	private Password txtUsuConstrasena;
	private InputText txtUsuCorreo;
	private SelectOneMenu txtUsuEstadoRegistro;
	private InputText txtUsuIntentosFallidos;
	private InputText txtUsuLogin;
	private InputText txtUsuNombres;
	private InputText txtUsuCodigo_SegUsuario;
	private InputText txtUsuCodigo;
	private Calendar txtUsuUltmimaModificacionPass;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<SegUsuarioDTO> data;
	private SegUsuarioDTO selectedSegUsuario;
	private SegUsuarioDTO usuarioSeleccionado;
	private List<String> rolesAsignados;
	private Logger logger = Logger.getLogger(SegUsuarioView.class);
	private SelectCheckboxMenu scmRoles;
	private Map<String, String> selectRoles;
	private String usuCod;


	private SelectOneMenu somCompanias;
	private SelectItem[] lasCompanias;


	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public SegUsuarioView() {
		super();
	}

	@PostConstruct
	public void SegUsuarioViewPostConstruct() {
		try {
			
				selectRoles = new HashMap<String, String>();
				List<SegRol> roles=new ArrayList<SegRol>();  
				List<SegRol> rolesConOpciones=new ArrayList<SegRol>();

				Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

				//Si el es super administrador, se cargan todos los roles
				if (usuSession==0) {
					roles = businessDelegatorView.getSegRol();
					for (int i = 0; i < roles.size(); i++) {
						rolesConOpciones.add(roles.get(i));
					}

				}else {
					//Si no es el usuario administrador, se cargan los roles del sistema seleccionado
					String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();
					rolesConOpciones = businessDelegatorView.consultarRolesPorSistema(Long.parseLong(sistema));
					
//					roles=businessDelegatorView.consultarRolesPorSistema(Long.parseLong(sistema));

//					//Se consultan adicionan a la lista, solo los roles que tienen opciones asignadas (Permisos) 
//					Long codigoCompania = Long.parseLong(FacesUtils.getManagedBeanFromSession("compania").toString());
//					for (int i = 0; i < roles.size(); i++) {
//						List<SegOpcion> opcionesDeUsuario = businessDelegatorView.consultarOpcionesPorUsuarioInPermisosSisCia(roles.get(i).getRolCodigo(),codigoCompania, usuSession);
//						if (opcionesDeUsuario.size()>0) {
//							rolesConOpciones.add(roles.get(i));
//						}
//					}
				}

				for (SegRol rol: rolesConOpciones) {		
					//					selectRoles.put(rol.getRolCodigo().toString() + " - " + rol.getRolNombre() , rol.getRolCodigo().toString());
					selectRoles.put(rol.getRolNombre() , rol.getRolCodigo().toString());
				}		
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public String action_clear() {
		txtUsuApellidos.setValue(null);
		txtUsuCodigoInterno.setValue(null);
		txtUsuConstrasena.setValue(null);
		txtUsuEstadoRegistro.setValue("-1");
		somCompanias.setValue("-1");
		txtUsuLogin.setValue(null);
		txtUsuNombres.setValue(null);
		txtUsuCodigo.setValue(null);
		btnSave.setDisabled(false);
		btnModify.setDisabled(true);
		btnClear.setDisabled(false);
		txtUsuCorreo.setValue("");
		scmRoles.setValue(null);
		scmRoles.setSelectedValues(null);
		rolesAsignados = new ArrayList<String>();
		return "";
	}


	public void listener_login(){

		String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();
		SegUsuario entity= null;


		try {
			String usuLogin = txtUsuLogin.getValue().toString();
			List<SegUsuario> usuarios = businessDelegatorView.findByCriteriaInSegUsuario(new Object[]{"usuLogin",true,usuLogin,"="},null, null);

			if (usuarios.size()==1) {

				entity = usuarios.get(0);

				if (entity!=null) {

					txtUsuCodigoInterno.setValue(entity.getUsuCodigoInterno());
					txtUsuNombres.setValue(entity.getUsuNombres());
					txtUsuApellidos.setValue(entity.getUsuApellidos());
					txtUsuConstrasena.setValue("");
					txtUsuEstadoRegistro.setValue(entity.getUsuEstadoRegistro());
					txtUsuCorreo.setValue(entity.getUsuCorreo());
					somCompanias.setValue(entity.getUsuCompaniaCiaCodigo());

					List<SegRol> rolesYaAsignados = businessDelegatorView.consultarRolesPorUsuario(entity.getUsuCodigo());
					rolesAsignados = new ArrayList<String>();

					for (int i = 0; i < rolesYaAsignados.size(); i++) {
						rolesAsignados.add(rolesYaAsignados.get(i).getRolCodigo().toString());
					}

					btnSave.setDisabled(true);
					btnModify.setDisabled(false);
				}
			}
		}
		catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}


	public void listener_codigoInterno(){

		String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();
		SegUsuario entity= null;

		try {
			String codigoInterno = txtUsuCodigoInterno.getValue().toString();
			List<SegUsuario> usuarios = businessDelegatorView.findByCriteriaInSegUsuario(new Object[]{"usuCodigoInterno",true,codigoInterno,"="},null, null);

			if (usuarios.size()==1) {

				entity = usuarios.get(0);

				if (entity!=null) {

					txtUsuLogin.setValue(entity.getUsuLogin());
					txtUsuNombres.setValue(entity.getUsuNombres());
					txtUsuApellidos.setValue(entity.getUsuApellidos());
					txtUsuConstrasena.setValue("");
					txtUsuEstadoRegistro.setValue(entity.getUsuEstadoRegistro());
					txtUsuCorreo.setValue(entity.getUsuCorreo());
					somCompanias.setValue(entity.getUsuCompaniaCiaCodigo());
					//Agregado para poder modificar
					txtUsuCodigo.setValue(entity.getUsuCodigo());

					List<SegRol> rolesYaAsignados = businessDelegatorView.consultarRolesPorUsuario(entity.getUsuCodigo());
					rolesAsignados = new ArrayList<String>();

					for (int i = 0; i < rolesYaAsignados.size(); i++) {
						rolesAsignados.add(rolesYaAsignados.get(i).getRolCodigo().toString());
					}

					btnSave.setDisabled(true);
					btnModify.setDisabled(false);
				}
			}
		}
		catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public String action_selected(){

		action_clear();

		FacesContext context = FacesContext.getCurrentInstance();  
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();

		String usuCodigo = (String)requestMap.get("usuCodigo");
		FacesUtils.setManagedBeanInSession("usu", usuCodigo);
		String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();

		try {

			List<SegRol> rolesYaAsignados = businessDelegatorView.consultarRolesPorUsuario(Long.parseLong(usuCodigo));
			rolesAsignados = new ArrayList<String>();

			for (int i = 0; i < rolesYaAsignados.size(); i++) {
				rolesAsignados.add(rolesYaAsignados.get(i).getRolCodigo().toString());
			}

			scmRoles.setValue(rolesAsignados);

			SegUsuario entity = businessDelegatorView.getSegUsuario(Long.parseLong(usuCodigo));
			txtUsuApellidos.setValue(entity.getUsuApellidos());
			txtUsuCodigoInterno.setValue(entity.getUsuCodigoInterno());
			txtUsuConstrasena.setValue("");
			txtUsuEstadoRegistro.setValue(entity.getUsuEstadoRegistro());
			txtUsuLogin.setValue(entity.getUsuLogin());
			txtUsuNombres.setValue(entity.getUsuNombres());
			txtUsuCodigo.setValue(entity.getUsuCodigo());
			txtUsuCorreo.setValue(entity.getUsuCorreo());
			
			somCompanias.setValue(entity.getUsuCompaniaCiaCodigo());
			
			
			btnSave.setDisabled(true);
			btnModify.setDisabled(false);

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String action_save() {

		try {

			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
			String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();
			String compania = FacesUtils.getManagedBeanFromSession("compania").toString();


			if (txtUsuCodigoInterno.getValue()==null||txtUsuCodigoInterno.getValue().toString().equals("")==true ) {
				throw new Exception("El Codigo Interno no puede estar vacio");
			}

			if (txtUsuLogin.getValue()==null||txtUsuLogin.getValue().toString().equals("")==true ) {
				throw new Exception("El Login no puede estar vacio");
			}

			if (txtUsuNombres.getValue()==null||txtUsuNombres.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre no puede estar vacio");
			}

			if (txtUsuApellidos.getValue()==null||txtUsuApellidos.getValue().toString().equals("")==true ) {
				throw new Exception("El Apellido no puede estar vacio");
			}

			if (txtUsuConstrasena.getValue()==null||txtUsuConstrasena.getValue().toString().equals("")==true ) {
				throw new Exception("El Password no puede estar vacio");
			}

			if (txtUsuEstadoRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtUsuCorreo.getValue()==null||txtUsuCorreo.getValue().toString().equals("")==true ) {
				throw new Exception("El correo no puede estar vacio");
			}

			if (Utilities.esCorreElectronico(txtUsuCorreo.getValue().toString())==false){
				throw new Exception("El correo debe tener el formato correcto");
			}

			if (rolesAsignados==null || rolesAsignados.size()==0) {
				throw new Exception("Debe asignarle roles al usuario creado");
			}

			if (somCompanias.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar una compania valida");
			}

			String usuLogin = txtUsuLogin.getValue().toString();
			Long intentosFallidos = 0L;
			Date ultimaFechaModificacion = new Date();
			String usuApellidos = txtUsuApellidos.getValue().toString();
			String usuCodigoInterno = txtUsuCodigoInterno.getValue().toString();
			String usuContrasena = txtUsuConstrasena.getValue().toString();
			String usuCorreo = txtUsuCorreo.getValue().toString();
			String usuEstadoRegistro = txtUsuEstadoRegistro.getValue().toString();
			String usuNombres = txtUsuNombres.getValue().toString();
			Long usuCompania = Long.parseLong(somCompanias.getValue().toString());

			//Se guarda el usuario con sus roles asignados
			businessDelegatorView.guardarUsuarioConRoles(usuApellidos,
					usuCodigoInterno,
					convertirMD5(usuContrasena),
					usuCorreo,
					usuEstadoRegistro,
					intentosFallidos,
					usuCompania,
					usuLogin.toUpperCase(),
					usuNombres,
					ultimaFechaModificacion,
					usuSession, rolesAsignados, 
					compania, sistema);


			if (usuSession == 0) {
				//Si es el usuario administrador, se consultan todos los usuarios registrados en el sistema
				data = businessDelegatorView.getDataSegUsuario();
			}else {
				//Si no es el administrador, se consultan los usuario del sistema cia con el que se encuentra trabajando
				String sistemas = FacesUtils.getManagedBeanFromSession("sistema").toString();
				String companias = FacesUtils.getManagedBeanFromSession("compania").toString();
				
				List<SegUsuario> usuarios = businessDelegatorView.consultarUsuariosPorSistemaCompania(Long.parseLong(sistemas), Long.parseLong(companias));
				data = new ArrayList<SegUsuarioDTO>();
				
				if (usuarios != null){
					for (SegUsuario segUsuarioTmp : usuarios) {
		                SegUsuarioDTO segUsuarioDTO2 = new SegUsuarioDTO();

		                segUsuarioDTO2.setUsuCodigo(segUsuarioTmp.getUsuCodigo());
		                segUsuarioDTO2.setUsuApellidos((segUsuarioTmp.getUsuApellidos() != null)
		                    ? segUsuarioTmp.getUsuApellidos() : null);
		                segUsuarioDTO2.setUsuCodigoInterno((segUsuarioTmp.getUsuCodigoInterno() != null)
		                    ? segUsuarioTmp.getUsuCodigoInterno() : null);
		                segUsuarioDTO2.setUsuCompaniaCiaCodigo((segUsuarioTmp.getUsuCompaniaCiaCodigo() != null)
		                    ? segUsuarioTmp.getUsuCompaniaCiaCodigo() : null);
		                segUsuarioDTO2.setUsuConstrasena((segUsuarioTmp.getUsuConstrasena() != null)
		                    ? segUsuarioTmp.getUsuConstrasena() : null);
		                segUsuarioDTO2.setUsuCorreo((segUsuarioTmp.getUsuCorreo() != null)
		                    ? segUsuarioTmp.getUsuCorreo() : null);
		                segUsuarioDTO2.setUsuEstadoRegistro((segUsuarioTmp.getUsuEstadoRegistro() != null)
		                    ? segUsuarioTmp.getUsuEstadoRegistro() : null);
		                segUsuarioDTO2.setUsuIntentosFallidos((segUsuarioTmp.getUsuIntentosFallidos() != null)
		                    ? segUsuarioTmp.getUsuIntentosFallidos() : null);
		                segUsuarioDTO2.setUsuLogin((segUsuarioTmp.getUsuLogin() != null)
		                    ? segUsuarioTmp.getUsuLogin() : null);
		                segUsuarioDTO2.setUsuNombres((segUsuarioTmp.getUsuNombres() != null)
		                    ? segUsuarioTmp.getUsuNombres() : null);
		                segUsuarioDTO2.setUsuUltmimaModificacionPass(segUsuarioTmp.getUsuUltmimaModificacionPass());
		                segUsuarioDTO2.setUsuCodigo_SegUsuario((segUsuarioTmp.getSegUsuario() != null)
		                    ? segUsuarioTmp.getSegUsuario().getUsuCodigo() : null);
		                
		                segUsuarioDTO2.setUsuEstadoRegistroNombre(segUsuarioTmp.getUsuEstadoRegistro().equals("A")?"Activo":"Inactivo");
		                
		                data.add(segUsuarioDTO2);
		            }
				}
				
			}
			FacesUtils.addInfoMessage("Usuario creado existósamente");
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {

		try {

			Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
			String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();
			String compania = FacesUtils.getManagedBeanFromSession("compania").toString();


			if (txtUsuCodigoInterno.getValue()==null||txtUsuCodigoInterno.getValue().toString().equals("")==true ) {
				throw new Exception("El Codigo Interno no puede estar vacio");
			}

			if (txtUsuLogin.getValue()==null||txtUsuLogin.getValue().toString().equals("")==true ) {
				throw new Exception("El Login no puede estar vacio");
			}

			if (txtUsuNombres.getValue()==null||txtUsuNombres.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre no puede estar vacio");
			}

			if (txtUsuApellidos.getValue()==null||txtUsuApellidos.getValue().toString().equals("")==true ) {
				throw new Exception("El Apellido no puede estar vacio");
			}

			if (txtUsuEstadoRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtUsuCorreo.getValue()==null||txtUsuCorreo.getValue().toString().equals("")==true ) {
				throw new Exception("El correo no puede estar vacio");
			}

			if (Utilities.validateEmailAddress(txtUsuCorreo.getValue().toString())==false){
				throw new Exception("El correo debe tener el formato correcto");
			}

			if (rolesAsignados==null || rolesAsignados.size()==0) {
				throw new Exception("Debe asignarle roles al usuario creado");
			}

			if (somCompanias.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar una compania valida");
			}

			String usuLogin = txtUsuLogin.getValue().toString();
			Long intentosFallidos = 0L;
			Date ultimaFechaModificacion = new Date();
			String usuApellidos = txtUsuApellidos.getValue().toString();
			String usuCodigoInterno = txtUsuCodigoInterno.getValue().toString();
			String usuContrasena = txtUsuConstrasena.getValue().toString();
			String usuCorreo = txtUsuCorreo.getValue().toString();
			String usuEstadoRegistro = txtUsuEstadoRegistro.getValue().toString();
			String usuNombres = txtUsuNombres.getValue().toString();
			Long usuCompania = Long.parseLong(somCompanias.getValue().toString());

			businessDelegatorView.modificarUsuarioConRoles(usuApellidos,
					new Long(txtUsuCodigo.getValue().toString()),
					usuCodigoInterno,
					(usuContrasena.equals("") ? "" : convertirMD5(usuContrasena.toString())),
					usuCorreo,
					usuEstadoRegistro,
					intentosFallidos,
					usuCompania,
					usuLogin.toUpperCase(),
					usuNombres,
					ultimaFechaModificacion,
					usuSession, rolesAsignados,compania, sistema);

			
			if (usuSession == 0) {
				//Si es el usuario administrador, se consultan todos los usuarios registrados en el sistema
				data = businessDelegatorView.getDataSegUsuario();
			}else {
				//Si no es el administrador, se consultan los usuario del sistema cia con el que se encuentra trabajando
				String sistemas = FacesUtils.getManagedBeanFromSession("sistema").toString();
				String companias = FacesUtils.getManagedBeanFromSession("compania").toString();
				
				List<SegUsuario> usuarios = businessDelegatorView.consultarUsuariosPorSistemaCompania(Long.parseLong(sistemas), Long.parseLong(companias));
				data = new ArrayList<SegUsuarioDTO>();
				
				if (usuarios != null){
					for (SegUsuario segUsuarioTmp : usuarios) {
		                SegUsuarioDTO segUsuarioDTO2 = new SegUsuarioDTO();

		                segUsuarioDTO2.setUsuCodigo(segUsuarioTmp.getUsuCodigo());
		                segUsuarioDTO2.setUsuApellidos((segUsuarioTmp.getUsuApellidos() != null)
		                    ? segUsuarioTmp.getUsuApellidos() : null);
		                segUsuarioDTO2.setUsuCodigoInterno((segUsuarioTmp.getUsuCodigoInterno() != null)
		                    ? segUsuarioTmp.getUsuCodigoInterno() : null);
		                segUsuarioDTO2.setUsuCompaniaCiaCodigo((segUsuarioTmp.getUsuCompaniaCiaCodigo() != null)
		                    ? segUsuarioTmp.getUsuCompaniaCiaCodigo() : null);
		                segUsuarioDTO2.setUsuConstrasena((segUsuarioTmp.getUsuConstrasena() != null)
		                    ? segUsuarioTmp.getUsuConstrasena() : null);
		                segUsuarioDTO2.setUsuCorreo((segUsuarioTmp.getUsuCorreo() != null)
		                    ? segUsuarioTmp.getUsuCorreo() : null);
		                segUsuarioDTO2.setUsuEstadoRegistro((segUsuarioTmp.getUsuEstadoRegistro() != null)
		                    ? segUsuarioTmp.getUsuEstadoRegistro() : null);
		                segUsuarioDTO2.setUsuIntentosFallidos((segUsuarioTmp.getUsuIntentosFallidos() != null)
		                    ? segUsuarioTmp.getUsuIntentosFallidos() : null);
		                segUsuarioDTO2.setUsuLogin((segUsuarioTmp.getUsuLogin() != null)
		                    ? segUsuarioTmp.getUsuLogin() : null);
		                segUsuarioDTO2.setUsuNombres((segUsuarioTmp.getUsuNombres() != null)
		                    ? segUsuarioTmp.getUsuNombres() : null);
		                segUsuarioDTO2.setUsuUltmimaModificacionPass(segUsuarioTmp.getUsuUltmimaModificacionPass());
		                segUsuarioDTO2.setUsuCodigo_SegUsuario((segUsuarioTmp.getSegUsuario() != null)
		                    ? segUsuarioTmp.getSegUsuario().getUsuCodigo() : null);
		                
		                segUsuarioDTO2.setUsuEstadoRegistroNombre(segUsuarioTmp.getUsuEstadoRegistro().equals("A")?"Activo":"Inactivo");
		                
		                data.add(segUsuarioDTO2);
		            }
				}
				
			}
			
			FacesUtils.addInfoMessage("Usuario modificado exitósamente");
			action_clear();
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}


	public String convertirMD5(String password) throws NoSuchAlgorithmException {

		String hash=password;
		byte[] defaultBytes = password.getBytes();	        	
		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		algorithm.reset();
		algorithm.update(defaultBytes);
		byte messageDigest[] = algorithm.digest();	        		            
		StringBuffer hexString = new StringBuffer();
		for (int i=0;i<messageDigest.length;i++) {
			int val = 0xff &  messageDigest[i];
			if (val < 16)
				hexString.append("0");
			hexString.append(Integer.toHexString(val));

			//hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
		}	        			        			
		hash=hexString+"";

		return hash;

	}


	public String action_modifyWitDTO(String usuApellidos, Long usuCodigo,
			String usuCodigoInterno, String usuConstrasena, String usuCorreo,
			String usuEstadoRegistro, Long usuIntentosFallidos, String usuLogin,
			String usuNombres, Date usuUltmimaModificacionPass,
			Long usuCodigo_SegUsuario) throws Exception {
		return "";
	}

	public InputText getTxtUsuApellidos() {
		return txtUsuApellidos;
	}

	public void setTxtUsuApellidos(InputText txtUsuApellidos) {
		this.txtUsuApellidos = txtUsuApellidos;
	}

	public InputText getTxtUsuCodigoInterno() {
		return txtUsuCodigoInterno;
	}

	public void setTxtUsuCodigoInterno(InputText txtUsuCodigoInterno) {
		this.txtUsuCodigoInterno = txtUsuCodigoInterno;
	}


	public Password getTxtUsuConstrasena() {
		return txtUsuConstrasena;
	}

	public void setTxtUsuConstrasena(Password txtUsuConstrasena) {
		this.txtUsuConstrasena = txtUsuConstrasena;
	}

	public InputText getTxtUsuCorreo() {
		return txtUsuCorreo;
	}

	public void setTxtUsuCorreo(InputText txtUsuCorreo) {
		this.txtUsuCorreo = txtUsuCorreo;
	}


	public InputText getTxtUsuIntentosFallidos() {
		return txtUsuIntentosFallidos;
	}

	public void setTxtUsuIntentosFallidos(InputText txtUsuIntentosFallidos) {
		this.txtUsuIntentosFallidos = txtUsuIntentosFallidos;
	}

	public InputText getTxtUsuLogin() {
		return txtUsuLogin;
	}

	public void setTxtUsuLogin(InputText txtUsuLogin) {
		this.txtUsuLogin = txtUsuLogin;
	}

	public InputText getTxtUsuNombres() {
		return txtUsuNombres;
	}

	public void setTxtUsuNombres(InputText txtUsuNombres) {
		this.txtUsuNombres = txtUsuNombres;
	}

	public InputText getTxtUsuCodigo_SegUsuario() {
		return txtUsuCodigo_SegUsuario;
	}

	public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
		this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
	}

	public Calendar getTxtUsuUltmimaModificacionPass() {
		return txtUsuUltmimaModificacionPass;
	}

	public void setTxtUsuUltmimaModificacionPass(
			Calendar txtUsuUltmimaModificacionPass) {
		this.txtUsuUltmimaModificacionPass = txtUsuUltmimaModificacionPass;
	}

	public InputText getTxtUsuCodigo() {
		return txtUsuCodigo;
	}

	public void setTxtUsuCodigo(InputText txtUsuCodigo) {
		this.txtUsuCodigo = txtUsuCodigo;
	}

	public List<SegUsuarioDTO> getData() {
		try {
			if (data == null) {

				Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

				if (usuSession == 0) {
					//Si es el usuario administrador, se consultan todos los usuarios registrados en el sistema
					data = businessDelegatorView.getDataSegUsuario();
				}else {
					//Si no es el administrador, se consultan los usuario del sistema cia con el que se encuentra trabajando
					String sistemas = FacesUtils.getManagedBeanFromSession("sistema").toString();
					String companias = FacesUtils.getManagedBeanFromSession("compania").toString();
					
					List<SegUsuario> usuarios = businessDelegatorView.consultarUsuariosPorSistemaCompania(Long.parseLong(sistemas), Long.parseLong(companias));
					data = new ArrayList<SegUsuarioDTO>();
					
					if (usuarios != null){
						for (SegUsuario segUsuarioTmp : usuarios) {
			                SegUsuarioDTO segUsuarioDTO2 = new SegUsuarioDTO();

			                segUsuarioDTO2.setUsuCodigo(segUsuarioTmp.getUsuCodigo());
			                segUsuarioDTO2.setUsuApellidos((segUsuarioTmp.getUsuApellidos() != null)
			                    ? segUsuarioTmp.getUsuApellidos() : null);
			                segUsuarioDTO2.setUsuCodigoInterno((segUsuarioTmp.getUsuCodigoInterno() != null)
			                    ? segUsuarioTmp.getUsuCodigoInterno() : null);
			                segUsuarioDTO2.setUsuCompaniaCiaCodigo((segUsuarioTmp.getUsuCompaniaCiaCodigo() != null)
			                    ? segUsuarioTmp.getUsuCompaniaCiaCodigo() : null);
			                segUsuarioDTO2.setUsuConstrasena((segUsuarioTmp.getUsuConstrasena() != null)
			                    ? segUsuarioTmp.getUsuConstrasena() : null);
			                segUsuarioDTO2.setUsuCorreo((segUsuarioTmp.getUsuCorreo() != null)
			                    ? segUsuarioTmp.getUsuCorreo() : null);
			                segUsuarioDTO2.setUsuEstadoRegistro((segUsuarioTmp.getUsuEstadoRegistro() != null)
			                    ? segUsuarioTmp.getUsuEstadoRegistro() : null);
			                segUsuarioDTO2.setUsuIntentosFallidos((segUsuarioTmp.getUsuIntentosFallidos() != null)
			                    ? segUsuarioTmp.getUsuIntentosFallidos() : null);
			                segUsuarioDTO2.setUsuLogin((segUsuarioTmp.getUsuLogin() != null)
			                    ? segUsuarioTmp.getUsuLogin() : null);
			                segUsuarioDTO2.setUsuNombres((segUsuarioTmp.getUsuNombres() != null)
			                    ? segUsuarioTmp.getUsuNombres() : null);
			                segUsuarioDTO2.setUsuUltmimaModificacionPass(segUsuarioTmp.getUsuUltmimaModificacionPass());
			                segUsuarioDTO2.setUsuCodigo_SegUsuario((segUsuarioTmp.getSegUsuario() != null)
			                    ? segUsuarioTmp.getSegUsuario().getUsuCodigo() : null);
			                
			                segUsuarioDTO2.setUsuEstadoRegistroNombre(segUsuarioTmp.getUsuEstadoRegistro().equals("A")?"Activo":"Inactivo");
			                
			                data.add(segUsuarioDTO2);
			            }
					}
					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public void setData(List<SegUsuarioDTO> segUsuarioDTO) {
		this.data = segUsuarioDTO;
	}

	public SegUsuarioDTO getSelectedSegUsuario() {
		return selectedSegUsuario;
	}

	public void setSelectedSegUsuario(SegUsuarioDTO segUsuario) {
		this.selectedSegUsuario = segUsuario;
	}

	public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public CommandButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(CommandButton btnModify) {
		this.btnModify = btnModify;
	}

	public CommandButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(CommandButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public CommandButton getBtnClear() {
		return btnClear;
	}

	public void setBtnClear(CommandButton btnClear) {
		this.btnClear = btnClear;
	}

	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}

	public SelectOneMenu getTxtUsuEstadoRegistro() {
		return txtUsuEstadoRegistro;
	}

	public void setTxtUsuEstadoRegistro(SelectOneMenu txtUsuEstadoRegistro) {
		this.txtUsuEstadoRegistro = txtUsuEstadoRegistro;
	}

	public SegUsuarioDTO getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(SegUsuarioDTO usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public List<String> getRolesAsignados() {
		return rolesAsignados;
	}

	public void setRolesAsignados(List<String> rolesAsignados) {
		this.rolesAsignados = rolesAsignados;
	}


	public Map<String, String> getSelectRoles() {
		return selectRoles;	
	}


	public void setSelectRoles(Map<String, String> selectRoles) {
		this.selectRoles = selectRoles;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public SelectCheckboxMenu getScmRoles() {
		return scmRoles;
	}

	public void setScmRoles(SelectCheckboxMenu scmRoles) {
		this.scmRoles = scmRoles;
	}

	public String getUsuCod() {
		return usuCod;
	}

	public void setUsuCod(String usuCod) {
		this.usuCod = usuCod;
	}

	public SelectItem[] getLasCompanias() {
		try {

			List<SegCompania> listCompania = new ArrayList<SegCompania>();
			listCompania=businessDelegatorView.getSegCompania();
			int size = 0;
			for (SegCompania segCompania : listCompania) {
				if(segCompania.getCiaEstadoRegistro().trim().equals("A")){
					size++;
				}
			}
			
			lasCompanias = new SelectItem[size];
			int i=0;
			for (SegCompania segCompania : listCompania) {
				if(segCompania.getCiaEstadoRegistro().trim().equals("A")){	
					lasCompanias[i] = new SelectItem(segCompania.getCiaCodigo(), segCompania.getCiaNombre());
					i++;
				}

			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return lasCompanias;
	}

	public void setLasCompanias(SelectItem[] lasCompanias) {
		this.lasCompanias = lasCompanias;
	}

	public SelectOneMenu getSomCompanias() {
		return somCompanias;
	}

	public void setSomCompanias(SelectOneMenu somCompanias) {
		this.somCompanias = somCompanias;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	
}
