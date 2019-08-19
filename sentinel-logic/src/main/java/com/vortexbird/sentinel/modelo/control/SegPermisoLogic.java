package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.dataaccess.dao.*;
import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.SegPermisoDTO;
import com.vortexbird.sentinel.modelo.dto.SellPersonaDTO;
import com.vortexbird.sentinel.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("SegPermisoLogic")
public class SegPermisoLogic implements ISegPermisoLogic {
    private static final Logger log = LoggerFactory.getLogger(SegPermisoLogic.class);

    /**
     * DAO injected by Spring that manages SegPermiso entities
     *
     */
    @Autowired
    private ISegPermisoDAO segPermisoDAO;

    /**
    * Logic injected by Spring that manages SegSucursal entities
    *
    */
    @Autowired
    ISegSucursalLogic logicSegSucursal5;

    /**
    * Logic injected by Spring that manages SegUsuario entities
    *
    */
    @Autowired
    ISegUsuarioLogic logicSegUsuario6;

    @Autowired
    ISegCompaniaLogic segCompaniaLogic;
    
    @Autowired
    ISegSistemaCiaLogic segSistemaCiaLogic;

	@Autowired
	ISegGrupoOpcionLogic segGrupoOpcionLogic;

	@Autowired
	ISegOpcionLogic segOpcionLogic;

	@Autowired
	ISegRolLogic segRolLogic;
	
	@Autowired
	ISegUsuarioLogic segUsuarioLogic;

    @Transactional(readOnly = true)
    public List<SegPermiso> getSegPermiso() throws Exception {
        log.debug("finding all SegPermiso instances");

        List<SegPermiso> list = new ArrayList<SegPermiso>();

        try {
            list = segPermisoDAO.findAll();
        } catch (Exception e) {
            log.error("finding all SegPermiso failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SegPermiso");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSegPermiso(SegPermiso entity) throws Exception {
        log.debug("saving SegPermiso instance");

        try {
//            if (entity.getSegGrupoOpcion() == null) {
//                throw new ZMessManager().new ForeignException("segGrupoOpcion");
//            }
//
//            if (entity.getSegOpcion() == null) {
//                throw new ZMessManager().new ForeignException("segOpcion");
//            }
//
//            if (entity.getSegRol() == null) {
//                throw new ZMessManager().new ForeignException("segRol");
//            }
//
//            if (entity.getSegSistemaCia() == null) {
//                throw new ZMessManager().new ForeignException("segSistemaCia");
//            }
//
//            if (entity.getSegSucursal() == null) {
//                throw new ZMessManager().new ForeignException("segSucursal");
//            }
//
//            if (entity.getSegUsuarioByModUsuCodigo() == null) {
//                throw new ZMessManager().new ForeignException(
//                    "segUsuarioByModUsuCodigo");
//            }
//
//            if (entity.getSegUsuarioByUsuCodigo() == null) {
//                throw new ZMessManager().new ForeignException(
//                    "segUsuarioByUsuCodigo");
//            }
//
//            if (entity.getPerCodigo() == null) {
//                throw new ZMessManager().new EmptyFieldException("perCodigo");
//            }
//
//            if (entity.getPerEstadoRegistro() == null) {
//                throw new ZMessManager().new EmptyFieldException(
//                    "perEstadoRegistro");
//            }
//
//            if ((entity.getPerEstadoRegistro() != null) &&
//                    (Utilities.checkWordAndCheckWithlength(
//                        entity.getPerEstadoRegistro(), 1) == false)) {
//                throw new ZMessManager().new NotValidFormatException(
//                    "perEstadoRegistro");
//            }
//
//            if (entity.getSegGrupoOpcion().getGruCodigo() == null) {
//                throw new ZMessManager().new EmptyFieldException(
//                    "gruCodigo_SegGrupoOpcion");
//            }
//
//            if (entity.getSegOpcion().getOpcCodigo() == null) {
//                throw new ZMessManager().new EmptyFieldException(
//                    "opcCodigo_SegOpcion");
//            }
//
//            if (entity.getSegRol().getRolCodigo() == null) {
//                throw new ZMessManager().new EmptyFieldException(
//                    "rolCodigo_SegRol");
//            }
//
//            if (entity.getSegSistemaCia().getSicCodigo() == null) {
//                throw new ZMessManager().new EmptyFieldException(
//                    "sicCodigo_SegSistemaCia");
//            }
//
//            if (entity.getSegSucursal().getSucCodigo() == null) {
//                throw new ZMessManager().new EmptyFieldException(
//                    "sucCodigo_SegSucursal");
//            }
//
//            if (entity.getSegUsuarioByModUsuCodigo().getUsuCodigo() == null) {
//                throw new ZMessManager().new EmptyFieldException(
//                    "usuCodigo_SegUsuario");
//            }
//
//            if (entity.getSegUsuarioByUsuCodigo().getUsuCodigo() == null) {
//                throw new ZMessManager().new EmptyFieldException(
//                    "usuCodigo_SegUsuario");
//            }
//
//            if (getSegPermiso(entity.getPerCodigo()) != null) {
//                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//            }

            segPermisoDAO.save(entity);

            log.debug("save SegPermiso successful");
        } catch (Exception e) {
            log.error("save SegPermiso failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSegPermiso(SegPermiso entity) throws Exception {
        log.debug("deleting SegPermiso instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("SegPermiso");
        }

        if (entity.getPerCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("perCodigo");
        }

        try {
            segPermisoDAO.delete(entity);

            log.debug("delete SegPermiso successful");
        } catch (Exception e) {
            log.error("delete SegPermiso failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSegPermiso(SegPermiso entity) throws Exception {
        log.debug("updating SegPermiso instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("SegPermiso");
            }

            if (entity.getSegGrupoOpcion() == null) {
                throw new ZMessManager().new ForeignException("segGrupoOpcion");
            }

            if (entity.getSegOpcion() == null) {
                throw new ZMessManager().new ForeignException("segOpcion");
            }

            if (entity.getSegRol() == null) {
                throw new ZMessManager().new ForeignException("segRol");
            }

            if (entity.getSegSistemaCia() == null) {
                throw new ZMessManager().new ForeignException("segSistemaCia");
            }

            if (entity.getSegSucursal() == null) {
                throw new ZMessManager().new ForeignException("segSucursal");
            }

            if (entity.getSegUsuarioByModUsuCodigo() == null) {
                throw new ZMessManager().new ForeignException(
                    "segUsuarioByModUsuCodigo");
            }

            if (entity.getSegUsuarioByUsuCodigo() == null) {
                throw new ZMessManager().new ForeignException(
                    "segUsuarioByUsuCodigo");
            }

            if (entity.getPerCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("perCodigo");
            }

            if (entity.getPerEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "perEstadoRegistro");
            }

            if ((entity.getPerEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getPerEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "perEstadoRegistro");
            }

            if (entity.getSegGrupoOpcion().getGruCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "gruCodigo_SegGrupoOpcion");
            }

            if (entity.getSegOpcion().getOpcCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "opcCodigo_SegOpcion");
            }

            if (entity.getSegRol().getRolCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "rolCodigo_SegRol");
            }

            if (entity.getSegSistemaCia().getSicCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "sicCodigo_SegSistemaCia");
            }

            if (entity.getSegSucursal().getSucCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "sucCodigo_SegSucursal");
            }

            if (entity.getSegUsuarioByModUsuCodigo().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            if (entity.getSegUsuarioByUsuCodigo().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            segPermisoDAO.update(entity);

            log.debug("update SegPermiso successful");
        } catch (Exception e) {
            log.error("update SegPermiso failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SegPermisoDTO> getDataSegPermiso() throws Exception {
        try {
            List<SegPermiso> segPermiso = segPermisoDAO.findAll();

            List<SegPermisoDTO> segPermisoDTO = new ArrayList<SegPermisoDTO>();

            for (SegPermiso segPermisoTmp : segPermiso) {
                SegPermisoDTO segPermisoDTO2 = new SegPermisoDTO();

                segPermisoDTO2.setPerCodigo(segPermisoTmp.getPerCodigo());
                segPermisoDTO2.setPerEstadoRegistro((segPermisoTmp.getPerEstadoRegistro() != null)
                    ? segPermisoTmp.getPerEstadoRegistro() : null);
                segPermisoDTO2.setGruCodigo_SegGrupoOpcion((segPermisoTmp.getSegGrupoOpcion()
                                                                         .getGruCodigo() != null)
                    ? segPermisoTmp.getSegGrupoOpcion().getGruCodigo() : null);
                segPermisoDTO2.setOpcCodigo_SegOpcion((segPermisoTmp.getSegOpcion()
                                                                    .getOpcCodigo() != null)
                    ? segPermisoTmp.getSegOpcion().getOpcCodigo() : null);
                segPermisoDTO2.setRolCodigo_SegRol((segPermisoTmp.getSegRol()
                                                                 .getRolCodigo() != null)
                    ? segPermisoTmp.getSegRol().getRolCodigo() : null);
                segPermisoDTO2.setSicCodigo_SegSistemaCia((segPermisoTmp.getSegSistemaCia()
                                                                        .getSicCodigo() != null)
                    ? segPermisoTmp.getSegSistemaCia().getSicCodigo() : null);
                segPermisoDTO2.setSucCodigo_SegSucursal((segPermisoTmp.getSegSucursal()
                                                                      .getSucCodigo() != null)
                    ? segPermisoTmp.getSegSucursal().getSucCodigo() : null);
                segPermisoDTO2.setUsuCodigo_SegUsuarioModifica((segPermisoTmp.getSegUsuarioByModUsuCodigo()
                                                                     .getUsuCodigo() != null)
                    ? segPermisoTmp.getSegUsuarioByUsuCodigo().getUsuCodigo() : null);
                segPermisoDTO.add(segPermisoDTO2);
            }

            return segPermisoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public SegPermiso getSegPermiso(Long perCodigo) throws Exception {
        log.debug("getting SegPermiso instance");

        SegPermiso entity = null;

        try {
            entity = segPermisoDAO.findById(perCodigo);
        } catch (Exception e) {
            log.error("get SegPermiso failed", e);
            throw new ZMessManager().new FindingException("SegPermiso");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<SegPermiso> findPageSegPermiso(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SegPermiso> entity = null;

        try {
            entity = segPermisoDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegPermiso Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSegPermiso() throws Exception {
        Long entity = null;

        try {
            entity = segPermisoDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegPermiso Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<SegPermiso> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SegPermiso> list = new ArrayList<SegPermiso>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = segPermisoDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public List<SegPermiso> consultarPermisosDeUsuariosPorSistemaCia(Long siscCodigo) throws Exception{
    	try {
			
    		//Se consultan los permisos del usuario para ese sistema cia
    		Object[] variables = {"segSistemaCia.sicCodigo", false, siscCodigo, "=",
    				"segUsuarioByUsuCodigo", false, "", "is not null", 
    				"segRol", false, "", "is null",
    				"segOpcion", false, "", "is null", 
    				"segGrupoOpcion", false, "", "is null",
    				"perEstadoRegistro", true, "A", "="};
    		
    		List<SegPermiso> permisos = findByCriteria(variables, null, null);
    		
    		return permisos;
    		
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void guardarPermiso (String perEstadoRegistro,
			Long sicCodigo_SegSistemaCia, Long gruCodigo_SegGrupoOpcion,
			Long opcCodigo_SegOpcion, Long rolCodigo_SegRol,
			Long sucCodigo_SegSucursal, Long usuCodigo_SegUsuario,
			Long usuCodigo_SegUsuario0) throws Exception {
		SegPermiso entity = null;

		try {

			if (perEstadoRegistro == null) {
				throw new ZMessManager().new EmptyFieldException(
						"perEstadoRegistro");
			}

			SegSistemaCia segSistemaCiaClass = null;
			SegGrupoOpcion segGrupoOpcionClass = null;
			SegOpcion segOpcionClass = null;
			SegRol segRolClass = null;
			SegSucursal segSucursalClass = null;
			SegUsuario segUsuarioByModUsuCodigoClass = null;
			SegUsuario segUsuarioByUsuCodigoClass = null;

			if(sicCodigo_SegSistemaCia!=null)
				segSistemaCiaClass = segSistemaCiaLogic.getSegSistemaCia(sicCodigo_SegSistemaCia);

			if(gruCodigo_SegGrupoOpcion != null)
				segGrupoOpcionClass = segGrupoOpcionLogic.getSegGrupoOpcion(gruCodigo_SegGrupoOpcion);

			if(opcCodigo_SegOpcion!=null)
				segOpcionClass = segOpcionLogic.getSegOpcion(opcCodigo_SegOpcion);

			if(rolCodigo_SegRol!=null)
				segRolClass = segRolLogic.getSegRol(rolCodigo_SegRol);

			if(sucCodigo_SegSucursal != null)
				segSucursalClass = logicSegSucursal5.getSegSucursal(sucCodigo_SegSucursal);

			if(usuCodigo_SegUsuario != null)
				segUsuarioByUsuCodigoClass = logicSegUsuario6.getSegUsuario(usuCodigo_SegUsuario);

			if(usuCodigo_SegUsuario0 != null)
				segUsuarioByModUsuCodigoClass = logicSegUsuario6.getSegUsuario(usuCodigo_SegUsuario0);


			entity = new SegPermiso();
			entity.setPerEstadoRegistro(perEstadoRegistro);
			entity.setSegSistemaCia(segSistemaCiaClass);//(segCompaniaClass);
			entity.setSegGrupoOpcion(segGrupoOpcionClass);
			entity.setSegOpcion(segOpcionClass);
			entity.setSegRol(segRolClass);
			entity.setSegSucursal(segSucursalClass);
			entity.setSegUsuarioByModUsuCodigo(segUsuarioByModUsuCodigoClass);
			entity.setSegUsuarioByUsuCodigo(segUsuarioByUsuCodigoClass);
			
			
			segPermisoDAO.save(entity);
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
    
    @Override
    @Transactional(readOnly = true)
	public List<SegOpcion> consultarPermisosDeOpcionesAsignadosARolesYUsuarios(Long codigoRol, Long codigoCompania, String codigoUsuario) throws Exception {

		List<SegOpcion> opcionesSeleccionadasPreviamente = new ArrayList<SegOpcion>();
		SegOpcion opcion =null;
		SegCompania comp = null;

		try{

			if (codigoRol==null) {
				throw new Exception("El codigo del rol es un valor necesario para la consulta");
			}

			SegRol rolConSistema = segRolLogic.getSegRol(codigoRol);
			Long codigoSistema = rolConSistema.getSegSistema().getSisCodigo();

			List<SegSistemaCia> siscias = segSistemaCiaLogic.findByCriteria(new Object[]{
					"segCompania",false,codigoCompania.toString(),"=",
					"segSistema",false,codigoSistema.toString(),"="
			},null, null);

			if (siscias.size()==0) {
				comp = segCompaniaLogic.getSegCompania(codigoCompania);
				throw new Exception("La compañía: " + comp.getCiaNombre() + " no esta parametrizada para el sistema: " + rolConSistema.getSegSistema().getSisNombre());
			}
			
			

			List<SegPermiso> permisosPorRol = segPermisoDAO.consultarPermisosDeOpcionesAsignadosARolesYUsuarios(codigoRol, 
					siscias.get(0).getSicCodigo(), Long.parseLong(codigoUsuario));

			if (permisosPorRol.size()>0) {
				for (int i = 0; i < permisosPorRol.size(); i++) {
					if(permisosPorRol.get(i).getSegOpcion()!=null){
						Long codOpcion = permisosPorRol.get(i).getSegOpcion()!=null?permisosPorRol.get(i).getSegOpcion().getOpcCodigo():null;
						if (codOpcion!=null) {
							opcion = segOpcionLogic.getSegOpcion(codOpcion);
							opcionesSeleccionadasPreviamente.add(opcion);
						}
					} else if(permisosPorRol.get(i).getSegOpcion()==null && permisosPorRol.get(i).getSegGrupoOpcion()!=null) {
						Set<SegOpcion> opcionesPorGrupo;
						opcionesPorGrupo = permisosPorRol.get(i).getSegGrupoOpcion().getSegOpcions();
						for (SegOpcion segOpcion : opcionesPorGrupo) {
							Long codOpcion = segOpcion!=null?segOpcion.getOpcCodigo():null;
							if (codOpcion!=null) {
								opcion = segOpcionLogic.getSegOpcion(codOpcion);
								opcionesSeleccionadasPreviamente.add(opcion);
							}
						}
					}
				}
			}
			return opcionesSeleccionadasPreviamente;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

    @Override
    @Transactional(readOnly = true)
    public List<SegPermiso> consultarPermisosDeOpcionesAsignadosARolesYUsuarios(Long rolCodigo,Long codigoCompania, Long codigoOpcion, String codigoUsuario) 
    		throws Exception{
    	try {
    		
    		List<SegPermiso> permisosCambiarEstado = new ArrayList<SegPermiso>();
    		
    		if (rolCodigo==null) {
				throw new Exception("El codigo del rol es un valor necesario para la consulta");
			}

			SegRol rolConSistema = segRolLogic.getSegRol(rolCodigo);
			Long codigoSistema = rolConSistema.getSegSistema().getSisCodigo();


			List<SegSistemaCia> siscias = segSistemaCiaLogic.findByCriteria(new Object[]{
					"segCompania",false,codigoCompania.toString(),"=",
					"segSistema",false,codigoSistema.toString(),"="
			},null, null);

			Long sisCiaUnico = siscias.get(0).getSicCodigo();
    		
			permisosCambiarEstado = segPermisoDAO.consultarPermisosDeOpcionesAsignadosARolesYUsuarios(rolCodigo, sisCiaUnico, codigoUsuario, codigoOpcion);
			
			if(permisosCambiarEstado == null || permisosCambiarEstado.size() == 0){
				permisosCambiarEstado = segPermisoDAO.consultarPermisosDeGruposAsignadosARolesYUsuarios(rolCodigo, sisCiaUnico, codigoUsuario, codigoOpcion);
			}
			
			return permisosCambiarEstado;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void guardarPermisosParaRolOUsuarioANivelDeGruposUOpciones(Long rolCodigo, List<SegOpcion> listOpciones, Long codigoCompania, Long codigoSistema, String codigoUsuario) throws Exception {


		SegSistemaCia segSistemaCiaClass = null;
		SegRol segRolClass = null;
		SegOpcion segOpcionClass = null;
		SegUsuario usuarioClass= null;
		SegPermiso entity = null;
		String estadoRegistro = "A";

		try {

			//Se consulta el sistema cia
			List<SegSistemaCia> siscias = segSistemaCiaLogic.findByCriteria(new Object[]{
					"segCompania",false,codigoCompania.toString(),"=",
					"segSistema",false,codigoSistema.toString(),"="
			},null, null);
			
			if (siscias == null || siscias.size() == 0){
				throw new Exception("No se econtró el par sistema cia " + codigoSistema + " - " + codigoCompania);
			}

			Long sisCiaUnico = siscias.get(0).getSicCodigo();

			if(sisCiaUnico!=null)
				segSistemaCiaClass = segSistemaCiaLogic.getSegSistemaCia(sisCiaUnico);

			if(rolCodigo!=null)
				segRolClass = segRolLogic.getSegRol(rolCodigo);

			//Si escogio usuarios es porque es un administrador ---> Se debe guardar en permisos por usuario
			if (!codigoUsuario.equals("-1")) {
				usuarioClass= segUsuarioLogic.getSegUsuario(Long.parseLong(codigoUsuario));

				for (int i = 0; i < listOpciones.size(); i++) {

					if(listOpciones.get(i).getOpcCodigo()!=null){
						segOpcionClass = segOpcionLogic.getSegOpcion(listOpciones.get(i).getOpcCodigo());

						entity = new SegPermiso();
						entity.setPerEstadoRegistro(estadoRegistro);
						entity.setSegSistemaCia(segSistemaCiaClass);
						entity.setSegGrupoOpcion(null);
						entity.setSegOpcion(segOpcionClass);
						entity.setSegRol(null);
						entity.setSegSucursal(null);
						entity.setSegUsuarioByModUsuCodigo(null);
						entity.setSegUsuarioByUsuCodigo(usuarioClass);
						
						segPermisoDAO.save(entity);
						
					}
				}
				//Es un usuario normal (EJ: Ejecutivo de ventas) --->Se debe guardar en permisos por su Rol
			}else {
				for (int i = 0; i < listOpciones.size(); i++) {

					if(listOpciones.get(i).getOpcCodigo()!=null){
						segOpcionClass = segOpcionLogic.getSegOpcion(listOpciones.get(i).getOpcCodigo());

						entity = new SegPermiso();
						entity.setPerEstadoRegistro(estadoRegistro);
						entity.setSegSistemaCia(segSistemaCiaClass);
						entity.setSegGrupoOpcion(null);
						entity.setSegOpcion(segOpcionClass);
						entity.setSegRol(segRolClass);
						entity.setSegSucursal(null);
						entity.setSegUsuarioByModUsuCodigo(null);
						entity.setSegUsuarioByUsuCodigo(null);
						
						segPermisoDAO.save(entity);
					}
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
    
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveSegPermisoAllUser(boolean enCrm, boolean enVcloud, boolean enSellout, SellPersonaDTO personaDTO) {
		try {
			SegUsuario usuario = segUsuarioLogic.consultarUsuarioPorLogin(personaDTO.getNumeroDocumento());
		
			
			if (enCrm == true) {
				SegUsuario usuModCodigo = new SegUsuario();
				usuModCodigo.setUsuCodigo(0L);
				SegPermiso permiso1 = new SegPermiso();
				permiso1.setPerCodigo(null);
				permiso1.setPerEstadoRegistro("A");
				permiso1.setSegUsuarioByUsuCodigo(usuario);
				permiso1.setSegUsuarioByModUsuCodigo(usuModCodigo);
				SegSistemaCia  grupo1 = new SegSistemaCia();
				grupo1.setSicCodigo(4L);
				permiso1.setSegSistemaCia(grupo1);
				segPermisoDAO.save(permiso1);
				log.info("se creo el permiso para el usuario "+personaDTO.getNumeroDocumento()+"en CRM");
			}
			if (enVcloud == true) {
				SegUsuario usuModCodigo1 = new SegUsuario();
				usuModCodigo1.setUsuCodigo(0L);
				SegPermiso permiso2 = new SegPermiso();
				permiso2.setPerCodigo(null);
				permiso2.setPerEstadoRegistro("A");
				permiso2.setSegUsuarioByUsuCodigo(usuario);
				permiso2.setSegUsuarioByModUsuCodigo(usuModCodigo1);
				SegSistemaCia  grupo2 = new SegSistemaCia();
				grupo2.setSicCodigo(5L);
				permiso2.setSegSistemaCia(grupo2);
				segPermisoDAO.save(permiso2);
				log.info("se creo el permiso para el usuario "+personaDTO.getNumeroDocumento()+"en Vcloud");
			}
			if (enSellout == true) {
				SegUsuario usuModCodigo3 = new SegUsuario();
				usuModCodigo3.setUsuCodigo(0L);
				SegPermiso permiso3 = new SegPermiso();
				permiso3.setPerCodigo(null);
				permiso3.setPerEstadoRegistro("A");
				permiso3.setSegUsuarioByUsuCodigo(usuario);
				permiso3.setSegUsuarioByModUsuCodigo(usuModCodigo3);
				SegSistemaCia  grupo3 = new SegSistemaCia();
				grupo3.setSicCodigo(2L);
				permiso3.setSegSistemaCia(grupo3);
				segPermisoDAO.save(permiso3);
				log.info("se creo el permiso para el usuario "+personaDTO.getNumeroDocumento()+"en Sellout");
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		
	}
}
