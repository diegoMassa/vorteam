package com.vortexbird.sentinel.dataaccess.dao;

import com.vortexbird.sentinel.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.sentinel.modelo.SegPermiso;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


/**
 * A data access object (DAO) providing persistence and search support for
 * SegPermiso entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.SegPermiso
 */
@Scope("singleton")
@Repository("SegPermisoDAO")
public class SegPermisoDAO extends HibernateDaoImpl<SegPermiso, Long>
    implements ISegPermisoDAO {
    private static final Logger log = LoggerFactory.getLogger(SegPermisoDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static ISegPermisoDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (ISegPermisoDAO) ctx.getBean("SegPermisoDAO");
    }
    
    @Override
	public List<SegPermiso> consultarPermisosDeOpcionesAsignadosARolesYUsuarios(Long codigoRol,Long sicCodigo, Long codigoUsuario) throws Exception{
		try {

			Query query = getSession().getNamedQuery("consultaPermisosDeOpcionesAsignadosARolesYUsuarios");
			query.setParameter("sicCodigo", sicCodigo);
			query.setParameter("codigoRol", codigoRol);
			query.setParameter("usuCodigo", codigoUsuario);

			List<SegPermiso> perm = query.list();

			return perm;

		} catch (Exception e) {
			throw e;
		}
	}
    
    @Override
	public List<SegPermiso> consultarPermisosDeOpcionesAsignadosARolesYUsuarios(Long rolCodigo, Long sisCiaUnico, String codigoUsuario, Long codigoOpcion) 
		throws Exception{
		
		try {
			
			List<SegPermiso> perm = new ArrayList<SegPermiso>();

			if(codigoUsuario.trim().equals("-1") == false){
				
				Query query = getSession().getNamedQuery("consultaPermisosDeOpcionesAsignadosARolesYUsuariosPorSistemaCompaniaYOpcion");
				query.setParameter("sicCodigo", sisCiaUnico);
				query.setParameter("codigoRol", rolCodigo);
				query.setParameter("usuCodigo", Long.parseLong(codigoUsuario.toString()));
				query.setParameter("codigoOpcion", codigoOpcion);
				
				perm = query.list();
			}else{
				Query query = getSession().getNamedQuery("consultaPermisosDeOpcionesAsignadosARolesPorSistemaCompaniaYOpcion");
				query.setParameter("sicCodigo", sisCiaUnico);
				query.setParameter("codigoRol", rolCodigo);
				query.setParameter("codigoOpcion", codigoOpcion);
				perm = query.list();
			}

			return perm;

		} catch (Exception e) {
			throw e;
		}
		
	}
    
    @Override
	public List<SegPermiso> consultarPermisosDeGruposAsignadosARolesYUsuarios(Long rolCodigo, Long sisCiaUnico, String codigoUsuario, Long codigoGrupo) 
		throws Exception{
		
		try {
			
			List<SegPermiso> perm = new ArrayList<SegPermiso>();

			if(codigoUsuario.trim().equals("-1") == false){
				Query query = getSession().getNamedQuery("consultaPermisosDeOpcionesAsignadosARolesYUsuariosPorSistemaCompaniaYGrupo");
				query.setParameter("sicCodigo", sisCiaUnico);
				query.setParameter("codigoRol", rolCodigo);
				query.setParameter("usuCodigo", Long.parseLong(codigoUsuario.toString()));
				query.setParameter("codigoGrupo", codigoGrupo);
	
				perm = query.list();
			}else{
				Query query = getSession().getNamedQuery("consultaPermisosDeOpcionesAsignadosARolesPorSistemaCompaniaYGrupo");query.setParameter("sicCodigo", sisCiaUnico);
				query.setParameter("codigoRol", rolCodigo);
				query.setParameter("codigoGrupo", codigoGrupo);
	
				perm = query.list();
			}

			return perm;

		} catch (Exception e) {
			throw e;
		}
		
	}
}
