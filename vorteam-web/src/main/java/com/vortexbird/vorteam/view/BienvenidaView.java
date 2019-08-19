package com.vortexbird.vorteam.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.sentinel.dto.UsuarioDTO;
import com.vortexbird.vorteam.domain.VtPersonas;
import com.vortexbird.vorteam.utility.FacesUtils;


/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class BienvenidaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(BienvenidaView.class);
    
    @ManagedProperty(value = "#{businessDelegator}")
    private BusinessDelegator businessDelegatorView;

    private String nombreCompleto;
    private UsuarioDTO usuario_session;
    private BigDecimal horasReportadas;
    
    public BienvenidaView() {
        super();
        this.usuario_session = (UsuarioDTO) FacesUtils.getfromSession("usuarioDTOLogin");
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
	 * @param businessDelegatorView El/La businessDelegatorView a setear
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
	 * @return La entidad nombreCompleto
	 *
	 */
	public String getNombreCompleto() {
		try {
			if(nombreCompleto == null || nombreCompleto.trim().equals("")) {
				VtPersonas persona = businessDelegatorView.consultarPersonaPorCorreo(usuario_session.getUsu_login());
				nombreCompleto = persona==null?"Vortexbird":persona.getNombre();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nombreCompleto;
	}

	/**
	 *
	 * @param nombreCompleto El/La nombreCompleto a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 *
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 *
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 * @return La entidad horasReportadas
	 *
	 */
	public BigDecimal getHorasReportadas() {
		try {
			if(horasReportadas == null) {
				horasReportadas = businessDelegatorView.horasReportadasHoy(new Date(), usuario_session.getUsu_login());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return horasReportadas;
	}

	/**
	 *
	 * @param horasReportadas El/La horasReportadas a setear
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 * @since 1.8
	 *
	 */
	public void setHorasReportadas(BigDecimal horasReportadas) {
		this.horasReportadas = horasReportadas;
	}

    
}
