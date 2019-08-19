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

import com.vortexbird.vorteam.dto.VtProyectoDTO;

/**
 *
 * @author Daniel Pareja Londoño
 * @version ago. 02, 2018
 * @since 1.8
 *
 */
@Scope("singleton")
@Repository("VtProyectoRepositoryImpl")
@SuppressWarnings("unchecked")
public class VtProyectoRepositoryImpl implements IVtProyectoRepository {

	private static final Logger log = LoggerFactory.getLogger(VtProyectoRepositoryImpl.class);
    @PersistenceContext
    private EntityManager entityManager;
	
	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtProyectoRepository#consultaProyectosClientes()
	 *
	 */
	@Override
	public List<VtProyectoDTO> consultaProyectosClientes(String activo) throws Exception {
		List<VtProyectoDTO> listaProyectosClientes = null;
		try {
			Query query = this.entityManager.createNamedQuery("consultaProyectosClientes");
			query.setParameter("pActivo", activo);
			listaProyectosClientes = query.getResultList();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return listaProyectosClientes;
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 02, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtProyectoRepository#consultaProyectosClientesRecursos(java.lang.String)
	 *
	 */
	@Override
	public List<VtProyectoDTO> consultaProyectosClientesRecursos(String activo) throws Exception {
		List<VtProyectoDTO> listaProyectosClientes = null;
		try {
			Query query = this.entityManager.createNamedQuery("consultaProyectosClientesRecursos");
			query.setParameter("pActivo", activo);
			listaProyectosClientes = query.getResultList();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return listaProyectosClientes;
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 08, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtProyectoRepository#consultaProyectos(java.lang.String)
	 *
	 */
	@Override
	public List<VtProyectoDTO> consultaProyectos(String activo) throws Exception {
		List<VtProyectoDTO> listaProyectos = null;
		try {
			Query query = this.entityManager.createNamedQuery("consultaProyectos");
			query.setParameter("pActivo", activo);
			listaProyectos = query.getResultList();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return listaProyectos;
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version ago. 15, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtProyectoRepository#consultaProyectosActivosPersona(java.lang.Long, java.lang.String)
	 *
	 */
	@Override
	public List<VtProyectoDTO> consultaProyectosActivosPersona(Long persId, String activo) throws Exception {
		List<VtProyectoDTO> listaProyectos = null;
		try {
			Query query = this.entityManager.createNamedQuery("consultaProyectosActivosPersona");
			query.setParameter("pActivo", activo);
			query.setParameter("pPersId", persId);
			listaProyectos = query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return listaProyectos;
	}

	/**
	 * @author Daniel Pareja Londoño
	 * @version oct. 05, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.repository.IVtProyectoRepository#consultaProyectosTodosPersona(java.lang.Long, java.lang.String)
	 *
	 */
	@Override
	public List<VtProyectoDTO> consultaProyectosTodosPersona(Long persId, String activo) throws Exception {
		List<VtProyectoDTO> listaProyectos = null;
		try {
			Query query = this.entityManager.createNamedQuery("consultaProyectosTodosPersona");
			query.setParameter("pActivo", activo);
			query.setParameter("pPersId", persId);
			listaProyectos = query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return listaProyectos;
	}

}
