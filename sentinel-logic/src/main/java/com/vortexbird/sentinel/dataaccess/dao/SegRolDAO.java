package com.vortexbird.sentinel.dataaccess.dao;

import com.vortexbird.sentinel.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.sentinel.modelo.SegRol;
import com.vortexbird.sentinel.modelo.dto.SegRolDTO;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Example;
import org.hibernate.transform.Transformers;
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
 * SegRol entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.SegRol
 */
@Scope("singleton")
@Repository("SegRolDAO")
public class SegRolDAO extends HibernateDaoImpl<SegRol, Long>
    implements ISegRolDAO {
    private static final Logger log = LoggerFactory.getLogger(SegRolDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static ISegRolDAO getFromApplicationContext(ApplicationContext ctx) {
        return (ISegRolDAO) ctx.getBean("SegRolDAO");
    }
    
    @Override
	public List<SegRol> consultarRolesPorSistema(Long sisCodigo) throws Exception{
		try {
			
			String queryString = "SELECT roles " +
					"FROM SegRol roles " +
					"join roles.segSistema sist " +
					"WHERE sist.sisCodigo = :pSisCodigo";

			Query query = createQuery(queryString);
			query.setParameter("pSisCodigo", sisCodigo);

			List<SegRol> roles =  query.list();

			return roles;

		} catch (Exception e) {
			throw e;
		}
	}
    
    /**
     * 
     * @author Andrés Felipe Vargas López
     * @version Jun 18, 2018
     *
     * @see com.vortexbird.sentinel.dataaccess.dao.ISegRolDAO#consultarRolPorSistemaCompania(java.lang.Long, java.lang.Long)
     *
     */
    @Override
    public List<SegRolDTO> consultarRolPorSistemaCompania(Long sistema, Long compania) throws Exception {

    	List<SegRolDTO> listDto = null;

    	try {
    		Query query = getSession().getNamedQuery("consultarRolPorSistemaCompania")
    				.setParameter("pSisCodigo", sistema)
    				.setParameter("pCiaCodigo", compania);

    	    query.setResultTransformer(Transformers.aliasToBean(SegRolDTO.class));
    		listDto = query.list();

    	} catch (Exception e) {
    		log.error(e.getMessage());
    		throw e;
    	}

    	return (listDto == null || listDto.isEmpty()) ? new ArrayList<SegRolDTO>() : listDto;

    }
    
    /**
     * 
     * @author Andrés Felipe Vargas López
     * @version Jun 18, 2018
     *
     * @see com.vortexbird.sentinel.dataaccess.dao.ISegRolDAO#consultarRolPorUsuarioSistemaCompania(java.lang.Long, java.lang.Long, java.lang.String)
     *
     */
    @Override
    public List<SegRolDTO> consultarRolPorUsuarioSistemaCompania(Long sistema, Long compania, String login) throws Exception {

    	List<SegRolDTO> listDto = null;

    	try {
    		Query query = getSession().getNamedQuery("consultarRolPorUsuarioSistemaCompania")
    				.setParameter("pLogin", login)
    				.setParameter("pSisCodigo", sistema)
    				.setParameter("pCiaCodigo", compania);

    	    query.setResultTransformer(Transformers.aliasToBean(SegRolDTO.class));
    		listDto = query.list();

    	} catch (Exception e) {
    		log.error(e.getMessage());
    		throw e;
    	}

    	return (listDto == null || listDto.isEmpty()) ? new ArrayList<SegRolDTO>() : listDto;

    }
}
