package com.vortexbird.vorteam.view;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.sentinel.dto.UsuarioDTO;
import com.vortexbird.vorteam.dto.VtReporteTiempoDTO;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.FacesUtils;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ReporteTiempoEntreFechasView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(ReporteTiempoEntreFechasView.class);

	@ManagedProperty(value = "#{businessDelegator}")
	private BusinessDelegator businessDelegatorView;
	private UsuarioDTO usuario_session;

	private Calendar calFechaInicial, calFechaFinal;

	private List<VtReporteTiempoDTO> reporte;
	
	private StreamedContent fileReporteGenerado;
	private boolean deshabilitarBotonesDescarga;

	public ReporteTiempoEntreFechasView() {
		super();
		this.usuario_session = (UsuarioDTO) FacesUtils.getfromSession("usuarioDTOLogin");
		deshabilitarBotonesDescarga = true;
	}

	public Boolean validarGenerarReporte() {
		Boolean isValidate = false;
		if (FacesUtils.checkDate(calFechaInicial) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("calFechaInicial", "msgCampoNoNulo");
		}
		if (FacesUtils.checkDate(calFechaFinal) == null) {
			isValidate = true;
			FacesUtils.addErrorMessage("calFechaFinal", "msgCampoNoNulo");
		}
		if (FacesUtils.checkDate(calFechaFinal).before(FacesUtils.checkDate(calFechaInicial))) {
			isValidate = true;
			FacesUtils.addErrorMessage("calFechaFinal", "msgFechaInicioMayor");
			FacesUtils.addErrorMessage("calFechaInicial", "msgFechaInicioMayor");
		}
		return isValidate;
	}
	
	public String generarReporte() {
		try {
			deshabilitarBotonesDescarga = true;
			if (validarGenerarReporte()) {
				return "";
			}
			reporte = businessDelegatorView.reporteEntreFechas(FacesUtils.checkDate(calFechaInicial),
					FacesUtils.checkDate(calFechaFinal));
			if(reporte!= null && !reporte.isEmpty()) {
				deshabilitarBotonesDescarga = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtils.addErrorOnlyMessage(e.getMessage());
		}
		return "";
	}
	
	public void limpiarPantalla() {
		this.calFechaInicial.setValue(null);
		this.calFechaFinal.setValue(null);
		this.reporte = null;
		setDeshabilitarBotonesDescarga(true);
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 * @return La entidad businessDelegatorView
	 *
	 */
	public BusinessDelegator getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	/**
	 *
	 * @param businessDelegatorView
	 *            El/La businessDelegatorView a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 *
	 */
	public void setBusinessDelegatorView(BusinessDelegator businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 * @return La entidad usuario_session
	 *
	 */
	public UsuarioDTO getUsuario_session() {
		return usuario_session;
	}

	/**
	 *
	 * @param usuario_session
	 *            El/La usuario_session a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 *
	 */
	public void setUsuario_session(UsuarioDTO usuario_session) {
		this.usuario_session = usuario_session;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 * @return La entidad calFechaInicial
	 *
	 */
	public Calendar getCalFechaInicial() {
		return calFechaInicial;
	}

	/**
	 *
	 * @param calFechaInicial
	 *            El/La calFechaInicial a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 *
	 */
	public void setCalFechaInicial(Calendar calFechaInicial) {
		this.calFechaInicial = calFechaInicial;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 * @return La entidad calFechaFinal
	 *
	 */
	public Calendar getCalFechaFinal() {
		return calFechaFinal;
	}

	/**
	 *
	 * @param calFechaFinal
	 *            El/La calFechaFinal a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 *
	 */
	public void setCalFechaFinal(Calendar calFechaFinal) {
		this.calFechaFinal = calFechaFinal;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 * @return La entidad reporte
	 *
	 */
	public List<VtReporteTiempoDTO> getReporte() {
		return reporte;
	}

	/**
	 *
	 * @param reporte
	 *            El/La reporte a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 *
	 */
	public void setReporte(List<VtReporteTiempoDTO> reporte) {
		this.reporte = reporte;
	}
	
	public StreamedContent getReporteXlsx() {
		try {
			Date fechaInicio = FacesUtils.checkDate(calFechaInicial);
			Date fechaFin = FacesUtils.checkDate(calFechaFinal);
			ByteArrayInputStream bais = businessDelegatorView.generarReporteTiemposEntreFechas(fechaInicio, fechaFin, Constantes.REPORT_OUTPUT_TYPE.XLSX);
			fileReporteGenerado = new DefaultStreamedContent(bais, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "ReporteTiemposEntreFechas.xlsx");
			return fileReporteGenerado;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			return null;
		}
	}
	
	public StreamedContent getReporteXls() {
		try {
			Date fechaInicio = FacesUtils.checkDate(calFechaInicial);
			Date fechaFin = FacesUtils.checkDate(calFechaFinal);
			ByteArrayInputStream bais = businessDelegatorView.generarReporteTiemposEntreFechas(fechaInicio, fechaFin, Constantes.REPORT_OUTPUT_TYPE.XLS);
			fileReporteGenerado = new DefaultStreamedContent(bais, "application/vnd.ms-excel",
					"ReporteTiemposEntreFechas.xls");
			return fileReporteGenerado;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			return null;
		}
	}
	
	public StreamedContent getReportePdf() {
		try {
			Date fechaInicio = FacesUtils.checkDate(calFechaInicial);
			Date fechaFin = FacesUtils.checkDate(calFechaFinal);
			ByteArrayInputStream bais = businessDelegatorView.generarReporteTiemposEntreFechas(fechaInicio, fechaFin, Constantes.REPORT_OUTPUT_TYPE.PDF);
			fileReporteGenerado = new DefaultStreamedContent(bais, "application/pdf",
					"ReporteTiemposEntreFechas.pdf");
			return fileReporteGenerado;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			return null;
		}
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 * @return La entidad deshabilitarBotonesDescarga
	 *
	 */
	public boolean isDeshabilitarBotonesDescarga() {
		return deshabilitarBotonesDescarga;
	}

	/**
	 *
	 * @param deshabilitarBotonesDescarga El/La deshabilitarBotonesDescarga a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 *
	 */
	public void setDeshabilitarBotonesDescarga(boolean deshabilitarBotonesDescarga) {
		this.deshabilitarBotonesDescarga = deshabilitarBotonesDescarga;
	}
}
