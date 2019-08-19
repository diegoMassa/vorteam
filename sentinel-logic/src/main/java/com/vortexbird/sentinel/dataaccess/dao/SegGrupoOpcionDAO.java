package com.vortexbird.sentinel.dataaccess.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.vortexbird.sentinel.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.sentinel.modelo.SegGrupoOpcion;


/**
 * A data access object (DAO) providing persistence and search support for
 * SegGrupoOpcion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.SegGrupoOpcion
 */
@Scope("singleton")
@Repository("SegGrupoOpcionDAO")
public class SegGrupoOpcionDAO extends HibernateDaoImpl<SegGrupoOpcion, Long>
    implements ISegGrupoOpcionDAO {
    private static final Logger log = LoggerFactory.getLogger(SegGrupoOpcionDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static ISegGrupoOpcionDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (ISegGrupoOpcionDAO) ctx.getBean("SegGrupoOpcionDAO");
    }
    
    
    @Override
	public List<SegGrupoOpcion> consultarGrupoOpcionesPorSistema(Long sisCodigo) throws Exception{
		try {
			
			String queryString = "SELECT grupos " +
					"FROM SegGrupoOpcion grupos " +
					"join grupos.segSistema sist " +
					"WHERE sist.sisCodigo = :pSiscodigo " +
					"order by grupos.orden";
			
			Query query = createQuery(queryString);
            query.setParameter("pSiscodigo", sisCodigo);
            
            List<SegGrupoOpcion> gruposOpc =  query.list();
            
			return gruposOpc;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
}
