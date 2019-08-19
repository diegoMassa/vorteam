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
import com.vortexbird.sentinel.modelo.SegOpcion;


/**
 * A data access object (DAO) providing persistence and search support for
 * SegOpcion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.SegOpcion
 */
@Scope("singleton")
@Repository("SegOpcionDAO")
public class SegOpcionDAO extends HibernateDaoImpl<SegOpcion, Long>
    implements ISegOpcionDAO {
    private static final Logger log = LoggerFactory.getLogger(SegOpcionDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static ISegOpcionDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (ISegOpcionDAO) ctx.getBean("SegOpcionDAO");
    }
    
    @Override
	public List<SegOpcion> consultarOpcionesPorSistema(Long sisCodigo) throws Exception{

		try {
			
			String queryString = "SELECT op " +
					"FROM SegOpcion op " +
					"join op.segGrupoOpcion grupoOpc " +
					"join grupoOpc.segSistema sist " +
					"WHERE sist.sisCodigo = :sisCodigo " +
					"order by op.orden";
			
			Query query = createQuery(queryString);
            query.setParameter("sisCodigo", sisCodigo);
            
            List<SegOpcion> opc =  query.list();
            
			return opc;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
    }
    
    
}
