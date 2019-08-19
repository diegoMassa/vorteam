/**
 *
 * @author Daniel Pareja Londoño
 * @version ago. 02, 2018
 * @since 1.8
 *
 */
package com.vortexbird.vorteam.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.vorteam.dto.VtProyectoDTO;
import com.vortexbird.vorteam.dto.VtReporteTiempoDTO;
import com.vortexbird.vorteam.utility.Constantes;
import com.vortexbird.vorteam.utility.Fechas;

/**
 *
 * @author Daniel Pareja Londoño
 * @version ago. 02, 2018
 * @since 1.8
 *
 */
@Scope("singleton")
@Repository("VtInformesRepositoryImpl")
@SuppressWarnings("unchecked")
public class VtInformesRepositoryImpl implements IVtInformesRepository {

	private static final Logger log = LoggerFactory.getLogger(VtInformesRepositoryImpl.class);
    @PersistenceContext
    private EntityManager entityManager;
	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtInformesRepository#reporteEntreFechas(java.util.Date, java.util.Date)
	 *
	 */
	@Override
	public List<VtReporteTiempoDTO> reporteEntreFechas(Date fechaInicial, Date fechaFinal) throws Exception {
		List<VtReporteTiempoDTO> reporte = null;
		try {
			Query query = this.entityManager.createNamedQuery("reporteTiemposEntreFechas");
			query.setParameter("pActivo", Constantes.ESTADO_ACTIVO);
			query.setParameter("pFechaInicial", Fechas.dateToStr(fechaInicial, Constantes.PATTERN_ANHO_MES_DIA_JUNTOS));
			query.setParameter("pFechaFinal", Fechas.dateToStr(fechaFinal, Constantes.PATTERN_ANHO_MES_DIA_JUNTOS));
			query.setParameter("pPattern", Constantes.PATTERN_ANHO_MES_DIA_JUNTOS);
			reporte = query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return reporte;
	}
	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 17, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtInformesRepository#horasReportadasHoy(java.util.Date, java.lang.String)
	 *
	 */
	@Override
	public BigDecimal horasReportadasHoy(Date fecha, String email) throws Exception {
		BigDecimal horasReportadas = null;
		try {
			Query query = this.entityManager.createNamedQuery("horasEjecutadasDia");
			query.setParameter("pFecha", Fechas.dateToStr(fecha, Constantes.PATTERN_ANHO_MES_DIA_JUNTOS));
			query.setParameter("pPattern", Constantes.PATTERN_ANHO_MES_DIA_JUNTOS);
			query.setParameter("pEmail", email.trim().toLowerCase());
			query.setParameter("pActivo", Constantes.ESTADO_ACTIVO);
			List<VtReporteTiempoDTO> reporte;
			reporte = query.getResultList();
			horasReportadas = reporte.isEmpty()?new BigDecimal(0):reporte.get(0).getHoras();
			if(horasReportadas == null) {
				return new BigDecimal(0);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return horasReportadas;
	}
	
}
