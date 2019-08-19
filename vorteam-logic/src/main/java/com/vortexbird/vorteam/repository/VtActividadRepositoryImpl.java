/**
 *
 * @author Daniel Pareja Londoño
 * @version ago. 02, 2018
 * @since 1.8
 *
 */
package com.vortexbird.vorteam.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.vorteam.dto.VtActividadDTO;
import com.vortexbird.vorteam.dto.VtProyectoDTO;

/**
 *
 * @author Daniel Pareja Londoño
 * @version ago. 02, 2018
 * @since 1.8
 *
 */
@Scope("singleton")
@Repository("VtActividadRepositoryImpl")
@SuppressWarnings("unchecked")
public class VtActividadRepositoryImpl implements IVtActividadRepository {

	private static final Logger log = LoggerFactory.getLogger(VtActividadRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtActividadRepository#consultaActividadesOrdenadaFechaDescendiente(java.lang.String)
	 *
	 */
	@Override
	public List<VtActividadDTO> consultaActividadesOrdenadaFechaDescendiente(String activo) throws Exception {
		List<VtActividadDTO> listaActividadesDescendientes = null;
		try {
			Query query = this.entityManager.createNamedQuery("consultaActividadesOrdenadaFechaDescendiente");
			query.setParameter("pActivo", activo);
			listaActividadesDescendientes = query.getResultList();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return listaActividadesDescendientes;
	}
	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtActividadRepository#consultaMisActividades(java.lang.Long, java.lang.String)
	 *
	 */
	@Override
	public List<VtActividadDTO> consultaMisActividades(Long persId, String activo) throws Exception {
		List<VtActividadDTO> misActividades = null;
		try {
			Query query = this.entityManager.createNamedQuery("consultaMisActividades");
			query.setParameter("pActivo", activo);
			query.setParameter("pPersId", persId);
			misActividades = query.getResultList();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return misActividades;
	}
	/**
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtActividadRepository#consultaMisActividadesLazy(java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, int, int)
	 *
	 */
	@Override
	public List<VtActividadDTO> consultaMisActividadesLazy(Long persId, String activo, Long proyId, Long estaId,
			Long tiacId, String sprint, String casoSoporte, String controlCambios, int first, int pageSize)
			throws Exception {
		List<VtActividadDTO> misActividadesLazy = null;
		try {
			Query query = this.entityManager.createNamedQuery("consultaMisActividadesLazy");
			query.setParameter("pActivo", activo);
			query.setParameter("pPersId", persId);
			query.setParameter("pProyId", proyId);
			query.setParameter("pEstaId", estaId);
			query.setParameter("pTiacId", tiacId);
			query.setParameter("pSprint", sprint);
			query.setParameter("pCasoSoporte", casoSoporte);
			query.setParameter("pControlCambios", controlCambios);
			query.setFirstResult(first);
			query.setMaxResults(pageSize);
			misActividadesLazy = query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return misActividadesLazy;
	}
	/**
	 * @author Daniel Pareja Londoño
	 * @version oct. 04, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtActividadRepository#consultaTotalMisActividadesLazy(java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 *
	 */
	@Override
	public Long consultaTotalMisActividadesLazy(Long persId, String activo, Long proyId, Long estaId, Long tiacId,
			String sprint, String casoSoporte, String controlCambios) throws Exception {
		try {
			Query query = this.entityManager.createNamedQuery("consultaTotalMisActividadesLazy");
			query.setParameter("pActivo", activo);
			query.setParameter("pPersId", persId);
			query.setParameter("pProyId", proyId);
			query.setParameter("pEstaId", estaId);
			query.setParameter("pTiacId", tiacId);
			query.setParameter("pSprint", sprint);
			query.setParameter("pCasoSoporte", casoSoporte);
			query.setParameter("pControlCambios", controlCambios);
			Long total = Long.parseLong(query.getResultList().get(0).toString());
			return total;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	
}
