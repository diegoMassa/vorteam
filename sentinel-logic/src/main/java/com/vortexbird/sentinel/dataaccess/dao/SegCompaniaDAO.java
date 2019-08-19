package com.vortexbird.sentinel.dataaccess.dao;

import com.vortexbird.sentinel.dataaccess.api.HibernateDaoImpl;
import com.vortexbird.sentinel.modelo.SegCompania;

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
 * SegCompania entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.SegCompania
 */
@Scope("singleton")
@Repository("SegCompaniaDAO")
public class SegCompaniaDAO extends HibernateDaoImpl<SegCompania, Long>
    implements ISegCompaniaDAO {
    private static final Logger log = LoggerFactory.getLogger(SegCompaniaDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static ISegCompaniaDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (ISegCompaniaDAO) ctx.getBean("SegCompaniaDAO");
    }
    
    @Override
	public List<SegCompania> consultarCompaniasDeDeusuarioAdministrador(Long ucuCodigo, long sisCodigo) throws Exception{
		try {
			String queryString = "SELECT DISTINCT compania " +
					"FROM SegSistemaCia sistemaCia, SegCompania compania, SegSistema sistema, " +
					"SegPermiso permiso, SegUsuario usuario, SegRol rol " +
					"WHERE sistemaCia.segCompania.ciaCodigo = compania.ciaCodigo " +
					"AND sistemaCia.segSistema.sisCodigo = sistema.sisCodigo " +
					"AND sistemaCia.sicCodigo = permiso.segSistemaCia.sicCodigo " +
					"AND permiso.segUsuarioByUsuCodigo.usuCodigo = usuario.usuCodigo " +
					"AND rol.segSistema.sisCodigo = sistema.sisCodigo " +
					"AND rol.esAdmonDeAplicacion = 'S' " +
					"AND sistema.sisCodigo =:pSisCodigo " +
					"AND usuario.usuCodigo =:pUcuCodigo";

			Query query = createQuery(queryString);
			query.setParameter("pUcuCodigo", ucuCodigo);
			query.setParameter("pSisCodigo", sisCodigo);

			List<SegCompania> codigoCiasByUsuarioRol =  query.list();

			return codigoCiasByUsuarioRol;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		
	}
    
}
