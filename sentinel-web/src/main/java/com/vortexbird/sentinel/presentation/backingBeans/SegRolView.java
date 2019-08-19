package com.vortexbird.sentinel.presentation.backingBeans;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.SegRol;
import com.vortexbird.sentinel.modelo.SegSistema;
import com.vortexbird.sentinel.modelo.dto.SegRolDTO;
import com.vortexbird.sentinel.presentation.businessDelegate.BusinessDelegatorView;
import com.vortexbird.sentinel.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.sentinel.utilities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
@ManagedBean
@ViewScoped
public class SegRolView {
	private InputText txtRolDescripcion;
	private InputText txtRolDiasCaducidadPwd;
	private SelectOneMenu txtRolEstadoRegistro;
	private InputText txtRolNombre;
	private SelectOneMenu txtSisCodigo_SegSistema;
	private SelectOneMenu cmbEsAdmon;
	private InputText txtUsuCodigo_SegUsuario;
	private InputText txtRolCodigo;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<SegRolDTO> data;
	private SegRolDTO selectedSegRol;
	private List<SelectItem> selectSistemas;
	
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public SegRolView() {
		super();
	}

	public String action_clear() {
		txtRolDescripcion.setValue(null);
		txtRolDiasCaducidadPwd.setValue(null);
		txtRolEstadoRegistro.setValue("-1");
		txtRolNombre.setValue(null);
		txtSisCodigo_SegSistema.setValue("-1");
		txtRolCodigo.setValue(null);
		btnSave.setDisabled(false);
		btnModify.setDisabled(true);
		btnClear.setDisabled(false);
		cmbEsAdmon.setDisabled(false);
		return "";
	}

	public String action_save() {

		Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

		try {

			if (txtRolNombre.getValue()==null||txtRolNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre del rol no puede estar vacio");
			}


			if (txtRolEstadoRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtRolDiasCaducidadPwd.getValue()==null||txtRolDiasCaducidadPwd.getValue().toString().equals("")==true ) {
				throw new Exception("El numero de dias de caducidad del password no puede estar vacio");
			}

			if (txtRolDescripcion.getValue()==null||txtRolDescripcion.getValue().toString().equals("")==true ) {
				throw new Exception("La Descripcion del rol no puede estar vacia");
			}

			if (txtSisCodigo_SegSistema.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un Sistema valido");
			}

			String descripcion =  txtRolDescripcion.getValue().toString();
			Long diasCaducidad = Long.parseLong(txtRolDiasCaducidadPwd.getValue().toString());
			String estadoRegistro = txtRolEstadoRegistro.getValue().toString();
			String nombre = txtRolNombre.getValue().toString();
			Long codigoSistema = Long.parseLong(txtSisCodigo_SegSistema.getValue().toString());
			String esAdmon = cmbEsAdmon.getValue().toString();

			businessDelegatorView.guardarRol(
					descripcion,
					diasCaducidad,
					estadoRegistro,
					nombre ,
					codigoSistema,
					usuSession,
					esAdmon
					);

//			SegRolDTO rolDTO = new SegRolDTO();
//			rolDTO.setRolCodigo(null);
//			rolDTO.setRolDescripcion(descripcion);
//			rolDTO.setRolDiasCaducidadPwd(diasCaducidad.toString());
//			rolDTO.setRolEstadoRegistro(estadoRegistro);
//			rolDTO.setRolEstadoRegistroNombre(estadoRegistro.equals("A")?"Activo":"Activo");
//			rolDTO.setRolNombre(nombre);
//
//			SegSistema sistema = BusinessDelegatorView.getSegSistema(codigoSistema);
//			rolDTO.setSisCodigo_SegSistema(sistema.getSisNombre());
//
//			data.add(rolDTO);
			
			if (usuSession == 0) {
				data = businessDelegatorView.getDataSegRol();
			}else {
				String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();
				data = businessDelegatorView.consultarRolesPorSistemaDTO(Long.parseLong(sistema));
			}
			
			
			FacesUtils.addInfoMessage("Rol creado existósamente");
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String action_selected(){

		action_clear();

		FacesContext context = FacesContext.getCurrentInstance();  
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();

		String rolCodigo = (String)requestMap.get("rolCodigo");
		try {
			SegRol entity = businessDelegatorView.getSegRol(Long.parseLong(rolCodigo));
			txtRolDescripcion.setValue(entity.getRolDescripcion());
			txtRolDiasCaducidadPwd.setValue(entity.getRolDiasCaducidadPwd());
			txtRolEstadoRegistro.setValue(entity.getRolEstadoRegistro());
			txtRolNombre.setValue(entity.getRolNombre());
			txtSisCodigo_SegSistema.setValue(entity.getSegSistema().getSisCodigo());
			txtRolCodigo.setValue(entity.getRolCodigo());
			cmbEsAdmon.setValue(entity.getEsAdmonDeAplicacion()!=null && entity.getEsAdmonDeAplicacion().equals("S") ? "S" : "N");
			btnSave.setDisabled(true);
			btnModify.setDisabled(false);

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String action_modify() {

		Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
		List<SegRol> losRoles = new ArrayList<SegRol>();

		try {

			if (txtRolNombre.getValue()==null||txtRolNombre.getValue().toString().equals("")==true ) {
				throw new Exception("El Nombre del rol no puede estar vacio");
			}

			if (txtRolEstadoRegistro.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un estado de registro valido");
			}

			if (txtRolDiasCaducidadPwd.getValue()==null||txtRolDiasCaducidadPwd.getValue().toString().equals("")==true ) {
				throw new Exception("El numero de dias de caducidad del password no puede estar vacio");
			}

			if (txtRolDescripcion.getValue()==null||txtRolDescripcion.getValue().toString().equals("")==true ) {
				throw new Exception("La Descripcion del rol no puede estar vacia");
			}

			if (txtSisCodigo_SegSistema.getValue().toString().equals("-1")==true) {
				throw new Exception("Debe seleccionar un Sistema valido");
			}

			Long codigoRol = Long.parseLong(txtRolCodigo.getValue().toString());
			String descripcion =  txtRolDescripcion.getValue().toString();
			Long diasCaducidad = Long.parseLong(txtRolDiasCaducidadPwd.getValue().toString());
			String estadoRegistro = txtRolEstadoRegistro.getValue().toString();
			String nombre = txtRolNombre.getValue().toString();
			Long codigoSistema = Long.parseLong(txtSisCodigo_SegSistema.getValue().toString());
			String esAdmon = cmbEsAdmon.getValue().toString();

			businessDelegatorView.modificarRol(
					codigoRol,
					descripcion,
					diasCaducidad,
					estadoRegistro,
					nombre,
					codigoSistema,
					usuSession,
					esAdmon
					);

//			losRoles = BusinessDelegatorView.getSegRol();
//			data = BusinessDelegatorView.getSegRolDTO(losRoles);
			
			if (usuSession == 0) {
				data = businessDelegatorView.getDataSegRol();
			}else {
				String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();
				List<SegRol> rolesDeSistema = businessDelegatorView.consultarRolesPorSistema(Long.parseLong(sistema));
				data = new ArrayList<SegRolDTO>();
				if (rolesDeSistema != null){
					for (SegRol segRol : rolesDeSistema) {
						SegRolDTO segRolDTO = new SegRolDTO();
						
						segRolDTO.setEsAdmonDeSistema(segRol.getEsAdmonDeAplicacion());
						segRolDTO.setRolCodigo(segRol.getRolCodigo());
						segRolDTO.setRolDescripcion(segRol.getRolDescripcion());
						segRolDTO.setRolDiasCaducidadPwd(segRol.getRolDiasCaducidadPwd());
						segRolDTO.setRolEstadoRegistro(segRol.getRolEstadoRegistro());
						segRolDTO.setRolNombre(segRol.getRolNombre());
						segRolDTO.setSisCodigo_SegSistema(sistema);
						
						data.add(segRolDTO);
						
					}
				}
			}
			FacesUtils.addInfoMessage("Rol modificado exitósamente");
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}


//	public String action_modifyWitDTO(Long rolCodigo, String rolDescripcion,
//			Long rolDiasCaducidadPwd, String rolEstadoRegistro, String rolNombre,
//			Long sisCodigo_SegSistema, Long usuCodigo_SegUsuario)
//					throws Exception {
//		try {
//			businessDelegatorView.updateSegRol(rolCodigo, rolDescripcion,
//					rolDiasCaducidadPwd, rolEstadoRegistro, rolNombre,
//					sisCodigo_SegSistema, usuCodigo_SegUsuario);
//			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
//		} catch (Exception e) {
//			FacesUtils.addErrorMessage(e.getMessage());
//			throw e;
//		}
//
//		return "";
//	}

	public InputText getTxtRolDescripcion() {
		return txtRolDescripcion;
	}

	public void setTxtRolDescripcion(InputText txtRolDescripcion) {
		this.txtRolDescripcion = txtRolDescripcion;
	}

	public InputText getTxtRolDiasCaducidadPwd() {
		return txtRolDiasCaducidadPwd;
	}

	public void setTxtRolDiasCaducidadPwd(InputText txtRolDiasCaducidadPwd) {
		this.txtRolDiasCaducidadPwd = txtRolDiasCaducidadPwd;
	}

	public SelectOneMenu getTxtRolEstadoRegistro() {
		return txtRolEstadoRegistro;
	}

	public void setTxtRolEstadoRegistro(SelectOneMenu txtRolEstadoRegistro) {
		this.txtRolEstadoRegistro = txtRolEstadoRegistro;
	}

	public InputText getTxtRolNombre() {
		return txtRolNombre;
	}

	public void setTxtRolNombre(InputText txtRolNombre) {
		this.txtRolNombre = txtRolNombre;
	}


	public SelectOneMenu getTxtSisCodigo_SegSistema() {
		return txtSisCodigo_SegSistema;
	}


	public void setTxtSisCodigo_SegSistema(SelectOneMenu txtSisCodigo_SegSistema) {
		this.txtSisCodigo_SegSistema = txtSisCodigo_SegSistema;
	}


	public InputText getTxtUsuCodigo_SegUsuario() {
		return txtUsuCodigo_SegUsuario;
	}

	public void setTxtUsuCodigo_SegUsuario(InputText txtUsuCodigo_SegUsuario) {
		this.txtUsuCodigo_SegUsuario = txtUsuCodigo_SegUsuario;
	}

	public InputText getTxtRolCodigo() {
		return txtRolCodigo;
	}

	public void setTxtRolCodigo(InputText txtRolCodigo) {
		this.txtRolCodigo = txtRolCodigo;
	}

	public List<SegRolDTO> getData() {
		try {
			if (data == null) {

				Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());
				if (usuSession == 0) {
					data = businessDelegatorView.getDataSegRol();
				}else {
					String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();
					
					List<SegRol> rolesDeSistema = businessDelegatorView.consultarRolesPorSistema(Long.parseLong(sistema));
					data = new ArrayList<SegRolDTO>();
					if (rolesDeSistema != null){
						for (SegRol segRol : rolesDeSistema) {
							SegRolDTO segRolDTO = new SegRolDTO();
							
							segRolDTO.setEsAdmonDeSistema(segRol.getEsAdmonDeAplicacion());
							segRolDTO.setRolCodigo(segRol.getRolCodigo());
							segRolDTO.setRolDescripcion(segRol.getRolDescripcion());
							segRolDTO.setRolDiasCaducidadPwd(segRol.getRolDiasCaducidadPwd());
							segRolDTO.setRolEstadoRegistro(segRol.getRolEstadoRegistro());
							segRolDTO.setRolNombre(segRol.getRolNombre());
							segRolDTO.setSisCodigo_SegSistema(sistema);
							
							data.add(segRolDTO);
							
						}
					}
					
				}
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return data;
	}

	public void setData(List<SegRolDTO> segRolDTO) {
		this.data = segRolDTO;
	}

	public SegRolDTO getSelectedSegRol() {
		return selectedSegRol;
	}

	public void setSelectedSegRol(SegRolDTO segRol) {
		this.selectedSegRol = segRol;
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


	public List<SelectItem> getSelectSistemas() {

		selectSistemas = new ArrayList<SelectItem>();
		List<SegSistema> sistemas=new ArrayList<SegSistema>();
		Long usuSession = Long.parseLong(FacesUtils.getManagedBean("codigoLogin").toString());

		try {

			if (usuSession==0) {
				sistemas=businessDelegatorView.findByCriteriaInSegSistema(new Object[]{"sisEstadoRegistro",true,"A","="},null, null);
			}else {
				//String sistema = FacesUtils.getManagedBeanFromSession("sistema").toString();
				sistemas = businessDelegatorView.consultarSistemasDeUsuarioAdministrador(usuSession);
			}

			for (SegSistema sistema: sistemas) {				
				selectSistemas.add(new SelectItem(sistema.getSisCodigo(),sistema.getSisNombre()));
			}			
		} catch (Exception e) {
		}

		return selectSistemas;		
	}

	public void setSelectSistemas(List<SelectItem> selectSistemas) {
		this.selectSistemas = selectSistemas;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public SelectOneMenu getCmbEsAdmon() {
		return cmbEsAdmon;
	}

	public void setCmbEsAdmon(SelectOneMenu cmbEsAdmon) {
		this.cmbEsAdmon = cmbEsAdmon;
	}
	
}
