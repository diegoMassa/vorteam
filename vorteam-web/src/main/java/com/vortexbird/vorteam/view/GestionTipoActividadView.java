package com.vortexbird.vorteam.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.sentinel.dto.UsuarioDTO;
import com.vortexbird.vorteam.domain.VtClasificacionFinanciera;
import com.vortexbird.vorteam.domain.VtTipoActividad;
import com.vortexbird.vorteam.dto.VtClasificacionFinancieraDTO;
import com.vortexbird.vorteam.dto.VtTipoActividadDTO;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.FacesUtils;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class GestionTipoActividadView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(GestionTipoActividadView.class);

	@ManagedProperty(value = "#{businessDelegator}")
	private BusinessDelegator businessDelegatorView;

	private List<VtTipoActividadDTO> tiposActividades;
	private List<SelectItem> siClasificacionFinanciera, siActivo;
	private VtTipoActividad tipoActividadModificar;
	private InputText txtTipoActividad;
	private SelectOneMenu somActivo, somClasificacionFinanciera;
	private boolean esVisibleDlgTipoActividad;
	
	private UsuarioDTO usuario_session;
	
	public GestionTipoActividadView() {
		super();
		this.usuario_session = (UsuarioDTO) FacesUtils.getfromSession("usuarioDTOLogin");
	}
	
	/*
	 * Acciones Proyecto
	 */
	public void limpiarFormularioTipoActividad() {
		txtTipoActividad.setValue(null);
		somActivo.setValue(Constantes.SOM_OPCION_SELECCIONAR);
		somClasificacionFinanciera.setValue(Constantes.SOM_OPCION_SELECCIONAR);
		setTiposActividades(null);
	}
	
	public String nuevoTipoActividad() {
		limpiarFormularioTipoActividad();
		setEsVisibleDlgTipoActividad(true);
		setTipoActividadModificar(null);
		return "";
	}
	
	public String cerrarDlgTipoActividad() {
		limpiarFormularioTipoActividad();
		setEsVisibleDlgTipoActividad(false);
		return "";
	}
	
	public Boolean validarGuardarTipoActividad() {
		Boolean isValidate = false;
		if(FacesUtils.checkString(txtTipoActividad) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("txtTipoActividad", "msgCampoNoNulo");
		}
		if(FacesUtils.checkLong(somClasificacionFinanciera).equals(Constantes.SOM_OPCION_SELECCIONAR)) {
			isValidate = true;
			FacesUtils.addErrorMessage("somClasificacionFinanciera", "msgSeleccione");
		}
		if(FacesUtils.checkString(somActivo).equals(Constantes.SOM_OPCION_SELECCIONAR+"")) {
			isValidate = true;
			FacesUtils.addErrorMessage("somActivo", "msgSeleccione");
		}
		return isValidate;
	}
	
	public String guardarTipoActividad() {
		try {
			if(validarGuardarTipoActividad()) {
				return "";
			}
			VtClasificacionFinanciera clasificacionFinanciera = businessDelegatorView.getVtClasificacionFinanciera(FacesUtils.checkLong(somClasificacionFinanciera));
			if(tipoActividadModificar == null) {
				VtTipoActividad tipoActividad = new VtTipoActividad();
				tipoActividad.setActivo(Constantes.ESTADO_ACTIVO);
				tipoActividad.setDescripcion(FacesUtils.checkString(txtTipoActividad));
				tipoActividad.setVtClasificacionFinanciera(clasificacionFinanciera);
				tipoActividad.setFechaCreacion(new Date());
				tipoActividad.setUsuaCreador(usuario_session.getUsu_login());

				businessDelegatorView.saveVtTipoActividad(tipoActividad);
			}else {
				tipoActividadModificar.setDescripcion(FacesUtils.checkString(txtTipoActividad));
				tipoActividadModificar.setVtClasificacionFinanciera(clasificacionFinanciera);
				tipoActividadModificar.setActivo(FacesUtils.checkString(somActivo));
				
				tipoActividadModificar.setUsuaModificador(usuario_session.getUsu_login());
				tipoActividadModificar.setFechaModificacion(new Date());
				
				businessDelegatorView.updateVtTipoActividad(tipoActividadModificar);
			}
			
			limpiarFormularioTipoActividad();
			FacesUtils.addInfoMessage("msgGuardado");
			
			setTiposActividades(null);
			setEsVisibleDlgTipoActividad(false);
		} catch (Exception e) {
			FacesUtils.addErrorOnlyMessage(e.getMessage());
		}
		return "";
	}
	
	public String modificarTipoActividad(ActionEvent actionEvent) {
		try {
			setEsVisibleDlgTipoActividad(true);
			Long tiacId = Long.parseLong(actionEvent.getComponent().getAttributes().get("tiacId").toString());
			tipoActividadModificar = businessDelegatorView.getVtTipoActividad(tiacId);
			txtTipoActividad.setValue(tipoActividadModificar.getDescripcion());
			somActivo.setValue(tipoActividadModificar.getActivo());
			somClasificacionFinanciera.setValue(tipoActividadModificar.getVtClasificacionFinanciera().getClfiId());
		} catch (Exception e) {
			FacesUtils.addErrorOnlyMessage(e.getMessage());
		}
		return "";
	}
	
	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad businessDelegatorView
	 *
	 */
	public BusinessDelegator getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	/**
	 *
	 * @param businessDelegatorView El/La businessDelegatorView a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setBusinessDelegatorView(BusinessDelegator businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad tiposActividades
	 *
	 */
	public List<VtTipoActividadDTO> getTiposActividades() {
		try {
			if(tiposActividades == null) {
				this.tiposActividades = businessDelegatorView.tiposActividadesConClasificacionFinanciera();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tiposActividades;
	}

	/**
	 *
	 * @param tiposActividades El/La tiposActividades a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setTiposActividades(List<VtTipoActividadDTO> tiposActividades) {
		this.tiposActividades = tiposActividades;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad siClasificacionFinanciera
	 *
	 */
	public List<SelectItem> getSiClasificacionFinanciera() {
		try {
			if(siClasificacionFinanciera == null) {
				siClasificacionFinanciera = new ArrayList<>();
				List<VtClasificacionFinancieraDTO> clasificacionFinanciera = businessDelegatorView.getDataVtClasificacionFinanciera();
				for (VtClasificacionFinancieraDTO vtClasificacionFinancieraDTO : clasificacionFinanciera) {
					siClasificacionFinanciera.add(new SelectItem(vtClasificacionFinancieraDTO.getClfiId(), vtClasificacionFinancieraDTO.getDescripcion().trim().toUpperCase()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siClasificacionFinanciera;
	}

	/**
	 *
	 * @param siClasificacionFinanciera El/La siClasificacionFinanciera a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setSiClasificacionFinanciera(List<SelectItem> siClasificacionFinanciera) {
		this.siClasificacionFinanciera = siClasificacionFinanciera;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad siActivo
	 *
	 */
	public List<SelectItem> getSiActivo() {
		try {
			if(siActivo == null) {
				siActivo = new ArrayList<>();
				for(String activo : Constantes.ESTADOS_GENERALES) {
					siActivo.add(new SelectItem(activo.toUpperCase().trim().substring(0, 1), activo.toUpperCase()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siActivo;
	}

	/**
	 *
	 * @param siActivo El/La siActivo a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setSiActivo(List<SelectItem> siActivo) {
		this.siActivo = siActivo;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad tipoActividadModificar
	 *
	 */
	public VtTipoActividad getTipoActividadModificar() {
		return tipoActividadModificar;
	}

	/**
	 *
	 * @param tipoActividadModificar El/La tipoActividadModificar a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setTipoActividadModificar(VtTipoActividad tipoActividadModificar) {
		this.tipoActividadModificar = tipoActividadModificar;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad txtTipoActividad
	 *
	 */
	public InputText getTxtTipoActividad() {
		return txtTipoActividad;
	}

	/**
	 *
	 * @param txtTipoActividad El/La txtTipoActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setTxtTipoActividad(InputText txtTipoActividad) {
		this.txtTipoActividad = txtTipoActividad;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad somActivo
	 *
	 */
	public SelectOneMenu getSomActivo() {
		return somActivo;
	}

	/**
	 *
	 * @param somActivo El/La somActivo a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setSomActivo(SelectOneMenu somActivo) {
		this.somActivo = somActivo;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad somClasificacionFinanciera
	 *
	 */
	public SelectOneMenu getSomClasificacionFinanciera() {
		return somClasificacionFinanciera;
	}

	/**
	 *
	 * @param somClasificacionFinanciera El/La somClasificacionFinanciera a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setSomClasificacionFinanciera(SelectOneMenu somClasificacionFinanciera) {
		this.somClasificacionFinanciera = somClasificacionFinanciera;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 * @return La entidad esVisibleDlgTipoActividad
	 *
	 */
	public boolean isEsVisibleDlgTipoActividad() {
		return esVisibleDlgTipoActividad;
	}

	/**
	 *
	 * @param esVisibleDlgTipoActividad El/La esVisibleDlgTipoActividad a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 24, 2018
	 * @since 1.8
	 *
	 */
	public void setEsVisibleDlgTipoActividad(boolean esVisibleDlgTipoActividad) {
		this.esVisibleDlgTipoActividad = esVisibleDlgTipoActividad;
	}
	
}
