package com.vortexbird.sentinel.dataaccess.dao;

import com.vortexbird.sentinel.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.sentinel.modelo.SegSistema;

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

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


/**
 * A data access object (DAO) providing persistence and search support for
 * SegSistema entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.SegSistema
 */
@Scope("singleton")
@Repository("SegSistemaDAO")
public class SegSistemaDAO extends HibernateDaoImpl<SegSistema, Long>
    implements ISegSistemaDAO {
    private static final Logger log = LoggerFactory.getLogger(SegSistemaDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static ISegSistemaDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (ISegSistemaDAO) ctx.getBean("SegSistemaDAO");
    }
    
    @Override
	public List<SegSistema> consultarSistemasDeUsuarioAdministrador(Long usuCodigo) {
		try {
			String queryString = "SELECT rol.segSistema " +
					"FROM SegRolUsuario rolUsu " +
					"join rolUsu.segRol rol " +
					"WHERE rolUsu.segUsuarioBySegUsuarioUsuCodigo.usuCodigo =:usuCodigo AND " +
					"rol.esAdmonDeAplicacion = :pEsAdmonDeAplicacion";

			Query query = createQuery(queryString);
			query.setParameter("usuCodigo", usuCodigo);
			query.setParameter("pEsAdmonDeAplicacion", "S");

			List<SegSistema> codigoSistemasByUsuarioRol =  query.list();

			return codigoSistemasByUsuarioRol;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
    
}
