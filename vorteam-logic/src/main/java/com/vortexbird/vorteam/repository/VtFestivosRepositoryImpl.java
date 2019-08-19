/**
 *
 * @author Daniel Pareja Londoño
 * @version ago. 02, 2018
 * @since 1.8
 *
 */
package com.vortexbird.vorteam.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.vorteam.dto.VtFestivosDTO;
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
@Repository("VtFestivosRepositoryImpl")
@SuppressWarnings("unchecked")
public class VtFestivosRepositoryImpl implements IVtFestivosRepository {

	private static final Logger log = LoggerFactory.getLogger(VtFestivosRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 *
	 *          (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtFestivosRepository#buscarEnFestivosPorFecha(java.util.Date)
	 *
	 */
	@Override
	public List<VtFestivosDTO> buscarEnFestivosPorFecha(Date fecha) throws Exception {
		List<VtFestivosDTO> festivos = null;
		try {
			Query query = this.entityManager.createNamedQuery("esFestivo");
			query.setParameter("pFecha", Fechas.dateToStr(fecha, Constantes.PATTERN_ANHO_MES_DIA_JUNTOS));
			query.setParameter("pPattern", Constantes.PATTERN_ANHO_MES_DIA_JUNTOS);
			query.setParameter("pActivo", Constantes.ESTADO_ACTIVO);
			festivos = query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return festivos;
	}
}
