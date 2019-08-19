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

import com.vortexbird.vorteam.dto.VtPersonasDTO;
import com.vortexbird.vorteam.dto.VtProyectoDTO;

/**
 *
 * @author Daniel Pareja Londoño
 * @version ago. 02, 2018
 * @since 1.8
 *
 */
@Scope("singleton")
@Repository("VtPersonasRepositoryImpl")
@SuppressWarnings("unchecked")
public class VtPersonasRepositoryImpl implements IVtPersonasRepository {

	private static final Logger log = LoggerFactory.getLogger(VtPersonasRepositoryImpl.class);
    @PersistenceContext
    private EntityManager entityManager;
	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtPersonasRepository#consultaPersonasProyectos(java.lang.String)
	 *
	 */
	@Override
	public List<VtPersonasDTO> consultaPersonasProyectos(String activo) throws Exception {
		List<VtPersonasDTO> listaPersonasProyectos = null;
		try {
			Query query = this.entityManager.createNamedQuery("consultaPersonasProyectos");
			query.setParameter("pActivo", activo);
			listaPersonasProyectos = query.getResultList();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return listaPersonasProyectos;
	}
	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 03, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtPersonasRepository#consultaPersonasProyecto(java.lang.String, java.lang.Long)
	 *
	 */
	@Override
	public List<VtPersonasDTO> consultaPersonasProyecto(String activo, Long proyId) throws Exception {
		List<VtPersonasDTO> listaPersonasProyectos = null;
		try {
			Query query = this.entityManager.createNamedQuery("consultaPersonasProyecto");
			query.setParameter("pActivo", activo);
			query.setParameter("pProyId", proyId);
			listaPersonasProyectos = query.getResultList();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return listaPersonasProyectos;
	}
	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 06, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtPersonasRepository#consultaPersonas(java.lang.String)
	 *
	 */
	@Override
	public List<VtPersonasDTO> consultaPersonas(String activo) throws Exception {
		List<VtPersonasDTO> listaPersonasProyectos = null;
		try {
			Query query = this.entityManager.createNamedQuery("consultaPersonas");
			query.setParameter("pActivo", activo);
			listaPersonasProyectos = query.getResultList();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return listaPersonasProyectos;
	}
	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 09, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtPersonasRepository#consultaPersonasActividad(java.lang.Long, java.lang.String)
	 *
	 */
	@Override
	public List<VtPersonasDTO> consultaPersonasActividad(Long actiId, String activo) throws Exception {
		List<VtPersonasDTO> listaPersonas = null;
		try {
			Query query = this.entityManager.createNamedQuery("consultaPersonasActividad");
			query.setParameter("pActivo", activo);
			query.setParameter("pActiId", actiId);
			listaPersonas = query.getResultList();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return listaPersonas;
	}
	
}
