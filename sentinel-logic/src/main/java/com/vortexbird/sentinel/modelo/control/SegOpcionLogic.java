package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.dataaccess.dao.*;
import com.vortexbird.sentinel.exceptions.*;
import com.vortexbird.sentinel.modelo.*;
import com.vortexbird.sentinel.modelo.dto.ComparadorGrupoDTO;
import com.vortexbird.sentinel.modelo.dto.ComparadorOpcionDTO;
import com.vortexbird.sentinel.modelo.dto.GrupoDTO;
import com.vortexbird.sentinel.modelo.dto.OpcionDTO;
import com.vortexbird.sentinel.modelo.dto.SegOpcionDTO;
import com.vortexbird.sentinel.utilities.Constantes;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("SegOpcionLogic")
public class SegOpcionLogic implements ISegOpcionLogic {
    private static final Logger log = LoggerFactory.getLogger(SegOpcionLogic.class);

    /**
     * DAO injected by Spring that manages SegOpcion entities
     *
     */
    @Autowired
    private ISegOpcionDAO segOpcionDAO;

    /**
    * DAO injected by Spring that manages SegPermiso entities
    *
    */
    @Autowired
    private ISegPermisoDAO segPermisoDAO;

    /**
    * Logic injected by Spring that manages SegGrupoOpcion entities
    *
    */
    @Autowired
    ISegGrupoOpcionLogic logicSegGrupoOpcion1;

    /**
    * Logic injected by Spring that manages SegUsuario entities
    *
    */
    @Autowired
    ISegUsuarioLogic logicSegUsuario2;
    
    @Autowired
    private ISegGrupoOpcionLogic segGrupoOpcionLogic;
    
    @Autowired
    private ISegUsuarioLogic segUsuarioLogic;
    
    @Autowired
    private ISegPermisoLogic segPermisoLogic;

    @Transactional(readOnly = true)
    public List<SegOpcion> getSegOpcion() throws Exception {
        log.debug("finding all SegOpcion instances");

        List<SegOpcion> list = new ArrayList<SegOpcion>();

        try {
            list = segOpcionDAO.findAll();
        } catch (Exception e) {
            log.error("finding all SegOpcion failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SegOpcion");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSegOpcion(SegOpcion entity) throws Exception {
        log.debug("saving SegOpcion instance");

        try {
            if (entity.getSegGrupoOpcion() == null) {
                throw new ZMessManager().new ForeignException("segGrupoOpcion");
            }

            if (entity.getSegUsuario() == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            if (entity.getOpcCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("opcCodigo");
            }

            if ((entity.getOpcDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getOpcDescripcion(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "opcDescripcion");
            }

            if (entity.getOpcEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "opcEstadoRegistro");
            }

            if ((entity.getOpcEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getOpcEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "opcEstadoRegistro");
            }

            if ((entity.getOpcLlaveAcceso() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getOpcLlaveAcceso(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "opcLlaveAcceso");
            }

            if (entity.getOpcNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("opcNombre");
            }

            if ((entity.getOpcNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getOpcNombre(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "opcNombre");
            }

            if (entity.getSegGrupoOpcion().getGruCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "gruCodigo_SegGrupoOpcion");
            }

            if (entity.getSegUsuario().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            if (getSegOpcion(entity.getOpcCodigo()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            segOpcionDAO.save(entity);

            log.debug("save SegOpcion successful");
        } catch (Exception e) {
            log.error("save SegOpcion failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSegOpcion(SegOpcion entity) throws Exception {
        log.debug("deleting SegOpcion instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("SegOpcion");
        }

        if (entity.getOpcCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("opcCodigo");
        }

        List<SegPermiso> segPermisos = null;

        try {
            segPermisos = segPermisoDAO.findByProperty("segOpcion.opcCodigo",
                    entity.getOpcCodigo());

            if (Utilities.validationsList(segPermisos) == true) {
                throw new ZMessManager().new DeletingException("segPermisos");
            }

            segOpcionDAO.delete(entity);

            log.debug("delete SegOpcion successful");
        } catch (Exception e) {
            log.error("delete SegOpcion failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSegOpcion(SegOpcion entity) throws Exception {
        log.debug("updating SegOpcion instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("SegOpcion");
            }

            if (entity.getSegGrupoOpcion() == null) {
                throw new ZMessManager().new ForeignException("segGrupoOpcion");
            }

            if (entity.getSegUsuario() == null) {
                throw new ZMessManager().new ForeignException("segUsuario");
            }

            if (entity.getOpcCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("opcCodigo");
            }

            if ((entity.getOpcDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getOpcDescripcion(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "opcDescripcion");
            }

            if (entity.getOpcEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "opcEstadoRegistro");
            }

            if ((entity.getOpcEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getOpcEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "opcEstadoRegistro");
            }

            if ((entity.getOpcLlaveAcceso() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getOpcLlaveAcceso(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "opcLlaveAcceso");
            }

            if (entity.getOpcNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("opcNombre");
            }

            if ((entity.getOpcNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getOpcNombre(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "opcNombre");
            }

            if (entity.getSegGrupoOpcion().getGruCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "gruCodigo_SegGrupoOpcion");
            }

            if (entity.getSegUsuario().getUsuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCodigo_SegUsuario");
            }

            segOpcionDAO.update(entity);

            log.debug("update SegOpcion successful");
        } catch (Exception e) {
            log.error("update SegOpcion failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SegOpcionDTO> getDataSegOpcion() throws Exception {
        try {
            List<SegOpcion> segOpcion = segOpcionDAO.findAll();

            List<SegOpcionDTO> segOpcionDTO = new ArrayList<SegOpcionDTO>();

            for (SegOpcion segOpcionTmp : segOpcion) {
                SegOpcionDTO segOpcionDTO2 = new SegOpcionDTO();

                segOpcionDTO2.setOpcCodigo(segOpcionTmp.getOpcCodigo());
                segOpcionDTO2.setOpcDescripcion((segOpcionTmp.getOpcDescripcion() != null)
                    ? segOpcionTmp.getOpcDescripcion() : null);
                segOpcionDTO2.setOpcEstadoRegistro((segOpcionTmp.getOpcEstadoRegistro() != null)
                    ? segOpcionTmp.getOpcEstadoRegistro() : null);
                segOpcionDTO2.setOpcLlaveAcceso((segOpcionTmp.getOpcLlaveAcceso() != null)
                    ? segOpcionTmp.getOpcLlaveAcceso() : null);
                segOpcionDTO2.setOpcNombre((segOpcionTmp.getOpcNombre() != null)
                    ? segOpcionTmp.getOpcNombre() : null);
                segOpcionDTO2.setGruCodigo_SegGrupoOpcion((segOpcionTmp.getSegGrupoOpcion()
                                                                       .getGruCodigo() != null)
                    ? segOpcionTmp.getSegGrupoOpcion().getGruCodigo() : null);
                segOpcionDTO2.setUsuCodigo_SegUsuario((segOpcionTmp.getSegUsuario()
                                                                   .getUsuCodigo() != null)
                    ? segOpcionTmp.getSegUsuario().getUsuCodigo() : null);
                segOpcionDTO2.setOrden(segOpcionTmp.getOrden());
                segOpcionDTO.add(segOpcionDTO2);
                
            }

            return segOpcionDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public SegOpcion getSegOpcion(Long opcCodigo) throws Exception {
        log.debug("getting SegOpcion instance");

        SegOpcion entity = null;

        try {
            entity = segOpcionDAO.findById(opcCodigo);
        } catch (Exception e) {
            log.error("get SegOpcion failed", e);
            throw new ZMessManager().new FindingException("SegOpcion");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<SegOpcion> findPageSegOpcion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SegOpcion> entity = null;

        try {
            entity = segOpcionDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegOpcion Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSegOpcion() throws Exception {
        Long entity = null;

        try {
            entity = segOpcionDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SegOpcion Count");
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
    public List<SegOpcion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SegOpcion> list = new ArrayList<SegOpcion>();
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
            list = segOpcionDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public List<GrupoDTO> getOpcionesPorRolYSistema(String login, String dominio, String sistema) throws Exception {

		List<SegUsuario> res;

		Hashtable<Long, SegOpcion> opciones = new Hashtable<Long, SegOpcion>();

		try {
			//Se consulta el usuario
			//2016-01-26 : Se añade la capacidad para dominio	
			res = segUsuarioLogic.findByCriteria(new Object[] { "usuLogin", true, login.toUpperCase().trim(), "=",
															"usuEstadoRegistro", true,"A", "=",
															"usuCodigoInterno", true, dominio,"="}, null, null);

			if (res.size() > 0) {

				SegUsuario u = res.get(0);// el login es unico!

				//Se obtienen los roles del usuario
				Object[] roles = u.getSegRolUsuariosForSegUsuarioUsuCodigo().toArray();

				//Por cada rol
				for (Object rol : roles) {

					SegRolUsuario s = (SegRolUsuario) rol;

					//Se obtienen los permisos del rol iterado
					Object[] permisos = s.getSegRol().getSegPermisos().toArray();

					// Se recorren los permisos del rol
					for (Object opcion : permisos) {

						SegPermiso per = (SegPermiso) opcion;

						//Del permiso, se obtiene el grupo (Si aplica)
						if (per.getSegGrupoOpcion() != null) {

							//Si el grupo opcion pertenece al sistema, se prosigue con las validaciones
							if (per.getSegGrupoOpcion().getSegSistema().getSisCodigo().toString().equals(sistema)){
							
								//Si el permiso es por grupo, se adicionan todas las opciones del grupo
								for (SegOpcion opcionGrupo : per.getSegGrupoOpcion().getSegOpcions()) {
									opciones.put(opcionGrupo.getOpcCodigo(),opcionGrupo);
								}
							}
						}
						
						//09-02-2016 Se añade la capacidad la sistema de seguridad de soportar permisos por opcion, además de por grupo
						//Si el permiso es por opcion, se anade la opcion 
						if (per.getSegOpcion() != null) {

							//Si la opcion es de un grupo que pertenece al sistema, se prosigue con las validaciones
							if (per.getSegOpcion().getSegGrupoOpcion().getSegSistema().getSisCodigo().toString().equals(sistema)){
								opciones.put(per.getSegOpcion().getOpcCodigo(),per.getSegOpcion());
							}

						}

						//Si el permiso es revocado, se elimina de las opciones adicionadas
						if (per.getPerEstadoRegistro().equals("I")) {

							if (per.getSegGrupoOpcion() != null) {

								for (SegOpcion opcionGrupo : per.getSegGrupoOpcion().getSegOpcions()) {
									opciones.remove(opcionGrupo.getOpcCodigo());
								}
							}
						}
						
						if (per.getPerEstadoRegistro().equals("I")) {

							if (per.getSegOpcion() != null) {

									opciones.remove(per.getSegOpcion().getOpcCodigo());
							}
						}
					}
				}

				List<GrupoDTO> arrayGrupos = new ArrayList<GrupoDTO>();
				List<GrupoDTO> arrayGruposFinal = new ArrayList<GrupoDTO>();
				
				//Por cada opcion sobre la que se otorga permiso...
				while (opciones.size() > 0) {

					Object[] opcionesFinales = (Object[]) (opciones.values().toArray());

					SegOpcion opc = (SegOpcion) opcionesFinales[0];

					GrupoDTO grupo = new GrupoDTO();

					grupo.setGru_codigo(opc.getSegGrupoOpcion().getGruCodigo().longValue()+ "");

					if (opc.getSegGrupoOpcion().getSegGrupoOpcion() != null){
						grupo.setGru_codigo_padre(opc.getSegGrupoOpcion().getSegGrupoOpcion().getGruCodigo().longValue()+ "");
					}else{
						grupo.setGru_codigo_padre("");
					}

					if (opc.getSegGrupoOpcion().getGruDescripcion() != null){
						grupo.setGru_descripcion(opc.getSegGrupoOpcion().getGruDescripcion().toString());
					}else{
						grupo.setGru_descripcion("");
					}

					if (opc.getSegGrupoOpcion().getGruLlaveAcceso() != null){
						grupo.setGru_llave_acceso(opc.getSegGrupoOpcion().getGruLlaveAcceso().toString());
					}else{
						grupo.setGru_llave_acceso("");
					}

					grupo.setGru_nombre(opc.getSegGrupoOpcion().getGruNombre().toString());
					grupo.setSis_codigo(opc.getSegGrupoOpcion().getSegSistema().getSisCodigo().longValue()+ "");
					grupo.setGru_activo(opc.getSegGrupoOpcion().getGruEstadoRegistro().toString());
					grupo.setGru_icono(opc.getSegGrupoOpcion().getGruIcono());

					List<OpcionDTO> arrayOpciones = new ArrayList<OpcionDTO>();

					for (int j = 0; j < opcionesFinales.length; j++) {

						SegOpcion opc2 = (SegOpcion) opcionesFinales[j];

						if ((opc2.getSegGrupoOpcion().getGruCodigo().longValue() + "").equals(grupo.getGru_codigo())) {

							OpcionDTO opcionDTO = new OpcionDTO();

							opcionDTO.setOpc_activo(opc2.getOpcEstadoRegistro().toString());
							opcionDTO.setOpc_codigo(opc2.getOpcCodigo().longValue() + "");

							if (opc2.getOpcDescripcion() != null){
								opcionDTO.setOpc_descripcion(opc2.getOpcDescripcion().toString() + "");
							}else{
								opcionDTO.setOpc_descripcion("");
							}

							if (opc2.getOpcLlaveAcceso() != null){
								opcionDTO.setOpc_llave_acceso(opc2.getOpcLlaveAcceso().toString());
							}else{
								opcionDTO.setOpc_llave_acceso("");
							}

							opcionDTO.setOpc_nombre(opc2.getOpcNombre().toString());
							opcionDTO.setSeg_grupo_opcion_gru_codigo(opc2.getSegGrupoOpcion().getGruCodigo().longValue() + "");
							opciones.remove(opc2.getOpcCodigo());
							
							// 01-06-2016 Se valida que solo devuelva las opciones con estado activo(1)
							if(opcionDTO.getOpc_activo().trim().equals("I") == false){
								arrayOpciones.add(opcionDTO);
							}
						}
					}
					/* - Se ordena la lista por el codigo de la opcion, puesto que los INSERTS se ejecutan ordenados en funcion de éste.
					   - Para que la funcion sort() sea exitosa, la clase OpcionDTO implementa Comparable<OpcionDTO> y se sobreescribe el metodo
					     compareTo() con el atributo que se requiere que se ordene.*/
					Collections.sort(arrayOpciones);
					/*Se setean las opciones al grupo, ordenadas.*/
					grupo.setOpciones(arrayOpciones);
					
					// 01-06-2016 Se valida que solo devuelva los grupos con estado activo(1)
					if(grupo.getGru_activo().trim().equals("I") == false){
						arrayGrupos.add(grupo);
						arrayGruposFinal.add(grupo);
					}
				}
				
				for (GrupoDTO grupoDTO : arrayGrupos) {
					// AFVL 2018-05-16 Si hay un subgrupo huerfano en la lista final, y tiene un padre que no fue añadido, se añade el grupo padre a la lista.
					if(grupoDTO.getGru_codigo_padre() != null && grupoDTO.getGru_codigo_padre().trim().equals("") == false) {
						Long gruCodigoPadre = Long.parseLong(grupoDTO.getGru_codigo_padre());
						
						boolean estaEnLaLista = false;
						
						for (GrupoDTO buscarGrupo : arrayGrupos) {
							if(buscarGrupo.getGru_codigo().trim().equalsIgnoreCase(gruCodigoPadre.toString())) {
								estaEnLaLista = true;
							}
						}
						
						if (estaEnLaLista == false) {
							
							SegGrupoOpcion grupoPadre = logicSegGrupoOpcion1.getSegGrupoOpcion(gruCodigoPadre);
							
							if (grupoPadre.getGruEstadoRegistro().trim().equals("I") == false) {

								GrupoDTO grupoPadreDTO = new GrupoDTO();
								grupoPadreDTO.setGru_codigo(grupoPadre.getGruCodigo().longValue() + "");
								if (grupoPadre.getSegGrupoOpcion() != null) {
									grupoPadreDTO.setGru_codigo_padre(
											grupoPadre.getSegGrupoOpcion().getGruCodigo().longValue() + "");
								} else {
									grupoPadreDTO.setGru_codigo_padre("");
								}
								if (grupoPadre.getGruDescripcion() != null) {
									grupoPadreDTO.setGru_descripcion(grupoPadre.getGruDescripcion().toString());
								} else {
									grupoPadreDTO.setGru_descripcion("");
								}
								if (grupoPadre.getGruLlaveAcceso() != null) {
									grupoPadreDTO.setGru_llave_acceso(grupoPadre.getGruLlaveAcceso().toString());
								} else {
									grupoPadreDTO.setGru_llave_acceso("");
								}
								grupoPadreDTO.setGru_nombre(grupoPadre.getGruNombre().toString());
								grupoPadreDTO.setSis_codigo(grupoPadre.getSegSistema().getSisCodigo().longValue() + "");
								grupoPadreDTO.setGru_activo(grupoPadre.getGruEstadoRegistro().toString());
								grupoPadreDTO.setGru_icono(grupoPadre.getGruIcono());
								List<OpcionDTO> arrayOpcionesPadreDTO = new ArrayList<OpcionDTO>();

								grupoPadreDTO.setOpciones(arrayOpcionesPadreDTO);

								arrayGruposFinal.add(grupoPadreDTO);
							} 
						}
					}
				}
				
				if (arrayGruposFinal.size() > 0){
					return arrayGruposFinal;
				}else{
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
    
    
    @Override
    @Transactional(readOnly=true)
    public List<GrupoDTO> consultarOpcionesDeUsuarioPorSistemaSucursalYCompania(String login,String sistema, String sucursal, String cia) {

		List<SegUsuario> res;
		Hashtable<Long, SegOpcion> opciones = new Hashtable<Long, SegOpcion>();

		try {

			//Se consulta el usuario por login
			res = segUsuarioLogic.findByCriteria(
					new Object[]{
							"usuLogin",true,login.toUpperCase().trim(),"=",
							"usuEstadoRegistro",true,"A","="},null, null);

			if(res.size()>0) {
				
				SegUsuario u = res.get(0);//el login es unico!

				//Se consultan los roles del usuario
				Object[] roles = u.getSegRolUsuariosForSegUsuarioUsuCodigo().toArray();

				for(Object rol:roles){

					SegRolUsuario s = (SegRolUsuario)rol;

					if (!s.getSegRol().getRolEstadoRegistro().equals("A")){
						continue;
					}

					//Se consultan los permisos de ese rol
					Object[] permisos = s.getSegRol().getSegPermisos().toArray();

					//Se recorren los permisos del rol
					for(Object opcion:permisos){

						SegPermiso per = (SegPermiso)opcion;

						boolean incluirPermiso = true;

						//Se valida si el permiso se parametriz� por sucursal / cia /
						if (per.getSegSucursal()!=null){
							if (sucursal.equals(per.getSegSucursal().getSucCodigo().toString())){
								incluirPermiso = true;
							}
							else {
								incluirPermiso = false;
							}

						}else if (per.getSegSistemaCia() !=null){
							if (cia.equals(per.getSegSistemaCia().getSegCompania().getCiaCodigo().toString())){
								incluirPermiso = true;
							}
							else {
								incluirPermiso = false;
							}
						}

						//Se calcula el sistema asignado al permiso iterado
						String sisCodigo = null;

						if (per.getSegOpcion()!=null){
							//Permiso por opcion
							sisCodigo = per.getSegOpcion().getSegGrupoOpcion().getSegSistema().getSisCodigo().toString();
						}else if (per.getSegGrupoOpcion()!=null){
							//Permiso por grupo
							sisCodigo = per.getSegGrupoOpcion().getSegSistema().getSisCodigo().toString();
						}else if (per.getSegRol()!=null){
							//Permiso por rol
							sisCodigo = per.getSegRol().getSegSistema().getSisCodigo().toString();
						}

						//Si el permiso iterado es para el sistema requerido
						incluirPermiso = incluirPermiso && sistema.equals(sisCodigo);

						if (incluirPermiso){
							if(per.getSegGrupoOpcion()!=null){

								for(SegOpcion opcionGrupo:per.getSegGrupoOpcion().getSegOpcions()){
									opciones.put(opcionGrupo.getOpcCodigo(), opcionGrupo);
								}

							}
							else if (per.getSegOpcion()!=null){
								opciones.put(per.getSegOpcion().getOpcCodigo(), per.getSegOpcion());
							}

						}

						if(!per.getPerEstadoRegistro().equals("A")) {

							if(per.getSegGrupoOpcion()!=null){

								for(SegOpcion opcionGrupo:per.getSegGrupoOpcion().getSegOpcions()){
									opciones.remove(opcionGrupo.getOpcCodigo());
								}

							}
							else if (per.getSegOpcion()!=null){
								if(per.getSegSistemaCia().getSegCompania()==null||cia.equals(per.getSegSistemaCia().getSegCompania().getCiaCodigo().toString()))
									opciones.remove(per.getSegOpcion().getOpcCodigo());
							}
						}

					}
				}
				
				//Se consultan los permisos individuales de la persona
				Object[] permisosUsuario = u.getSegPermisosForUsuCodigo().toArray();

				//Se recorren los permisos del usuario
				for(Object opcion:permisosUsuario){

					SegPermiso per = (SegPermiso)opcion;

					boolean incluirPermiso = true;

					//Se valida si el permiso se parametriz� por sucursal / cia
					if (per.getSegSucursal()!=null){
						if (sucursal.equals(per.getSegSucursal().getSucCodigo().toString())){
							incluirPermiso = true;
						}
						else {
							incluirPermiso = false;
						}
					}else if (per.getSegSistemaCia()!=null){
						if (cia.equals(per.getSegSistemaCia().getSegCompania().getCiaCodigo().toString())){
							incluirPermiso = true;
						}
						else {
							incluirPermiso = false;
						}
					}

					//Se calcula el sistema asignado al permiso iterado
					String sisCodigo = null;

					if (per.getSegOpcion()!=null){
						//Permiso por opcion
						sisCodigo = per.getSegOpcion().getSegGrupoOpcion().getSegSistema().getSisCodigo().toString();
					}else if (per.getSegGrupoOpcion()!=null){
						//Permiso por grupo
						sisCodigo = per.getSegGrupoOpcion().getSegSistema().getSisCodigo().toString();
					}else if (per.getSegRol()!=null){
						//Permiso por rol
						sisCodigo = per.getSegRol().getSegSistema().getSisCodigo().toString();
					}

					//Si el permiso iterado es para el sistema requerido
					incluirPermiso = incluirPermiso && sistema.equals(sisCodigo);

					if (incluirPermiso){
						if(per.getSegGrupoOpcion()!=null){

							for(SegOpcion opcionGrupo:per.getSegGrupoOpcion().getSegOpcions()){
								opciones.put(opcionGrupo.getOpcCodigo(), opcionGrupo);
							}

						}
						else if (per.getSegOpcion()!=null){
							opciones.put(per.getSegOpcion().getOpcCodigo(), per.getSegOpcion());
						}

					}

					if(!per.getPerEstadoRegistro().equals("A")) {
						//System.out.println("opcion desactivada "+per.getSegOpcion().getOpcNombre());

						if(per.getSegGrupoOpcion()!=null){

							for(SegOpcion opcionGrupo:per.getSegGrupoOpcion().getSegOpcions()){
								opciones.remove(opcionGrupo.getOpcCodigo());
							}

						}
						else if (per.getSegOpcion()!=null){
							opciones.remove(per.getSegOpcion().getOpcCodigo());
						}
					}

				}

				List<GrupoDTO> arrayGrupos = new ArrayList<GrupoDTO>();

				while(opciones.size()>0){

					Object[] opcionesFinales = (Object[])(opciones.values().toArray());
					SegOpcion opc = (SegOpcion)opcionesFinales[0];

					GrupoDTO grupo = new GrupoDTO();
					grupo.setGru_codigo(opc.getSegGrupoOpcion().getGruCodigo().longValue()+"");

					if(opc.getSegGrupoOpcion().getSegGrupoOpcion()!=null)
						grupo.setGru_codigo_padre(opc.getSegGrupoOpcion().getSegGrupoOpcion().getGruCodigo().longValue()+"");
					else
						grupo.setGru_codigo_padre("");

					if(opc.getSegGrupoOpcion().getGruDescripcion()!=null)
						grupo.setGru_descripcion(opc.getSegGrupoOpcion().getGruDescripcion().toString());
					else
						grupo.setGru_descripcion("");

					if(opc.getSegGrupoOpcion().getGruLlaveAcceso()!=null)
						grupo.setGru_llave_acceso(opc.getSegGrupoOpcion().getGruLlaveAcceso().toString());
					else
						grupo.setGru_llave_acceso("");

					grupo.setGru_nombre(opc.getSegGrupoOpcion().getGruNombre().toString());
					grupo.setSis_codigo(opc.getSegGrupoOpcion().getSegSistema().getSisCodigo().longValue()+"");
					grupo.setGru_activo(opc.getSegGrupoOpcion().getGruEstadoRegistro().toString());
					grupo.setOrden(opc.getSegGrupoOpcion().getOrden());

					List<OpcionDTO> arrayOpciones = new ArrayList<OpcionDTO>();

					for(int j = 0;j<opcionesFinales.length;j++) {
						SegOpcion opc2 = (SegOpcion)opcionesFinales[j];
						if((opc2.getSegGrupoOpcion().getGruCodigo().longValue()+"").equals(grupo.getGru_codigo())){

							OpcionDTO opcionDTO = new OpcionDTO();
							opcionDTO.setOpc_activo(opc2.getOpcEstadoRegistro().toString());
							opcionDTO.setOpc_codigo(opc2.getOpcCodigo().longValue()+"");

							if(opc2.getOpcDescripcion()!=null)
								opcionDTO.setOpc_descripcion(opc2.getOpcDescripcion().toString()+"");
							else
								opcionDTO.setOpc_descripcion("");

							if(opc2.getOpcLlaveAcceso()!=null)
								opcionDTO.setOpc_llave_acceso(opc2.getOpcLlaveAcceso().toString());
							else
								opcionDTO.setOpc_llave_acceso("");

							opcionDTO.setOpc_nombre(opc2.getOpcNombre().toString());
							opcionDTO.setSeg_grupo_opcion_gru_codigo(opc2.getSegGrupoOpcion().getGruCodigo().longValue()+"");
							opcionDTO.setOrden(opc2.getOrden());

							opciones.remove(opc2.getOpcCodigo());
							
							// 01-06-2016 Se valida que solo devuelva las opciones con estado activo(1)
							if(opcionDTO.getOpc_activo().trim().equals("A")){
								arrayOpciones.add(opcionDTO);
							}
						}
					}

					grupo.setOpciones(arrayOpciones);
					
					// 01-06-2016 Se valida que solo devuelva los grupos con estado activo(1)
					if(grupo.getGru_activo().trim().equals("A")){
						arrayGrupos.add(grupo);
					}
				}

				if(arrayGrupos.size()>0){
					
					//Se ordenan los grupos
					Collections.sort(arrayGrupos, new ComparadorGrupoDTO());
					
					//Se ordenan las opciones de cada uno de los grupos
					for (GrupoDTO grupoDTO : arrayGrupos) {
						Collections.sort(grupoDTO.getOpciones(), new ComparadorOpcionDTO());
					}
					
					return arrayGrupos;
				}else{
					return null;
				}

			}else {
				throw new Exception("El usuario esta desactivado");

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}

	}
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void guardarOpcion(String nombre, String descripcion, String llaveAcceso, Long gruCodigo, Long usuCreador, String estadoRegistro, Integer orden) throws Exception{
    	try {
			

            if (nombre == null){
            	throw new Exception("Debe ingresar el nombre de la opción");
            }
            
            if (descripcion == null){
            	throw new Exception("Debe ingresar la descripcion de la opción");
            }
            
            if (llaveAcceso == null){
            	throw new Exception("Debe ingresar la llave de acceso de la opción");
            }
            
            if (gruCodigo == null){
            	throw new Exception("Debe ingresar el grupo al que pertenece la opción");
            }
            
            if (usuCreador == null){
            	throw new Exception("Debe ingresar el usuario que crea la opción");
            }
            
            //Se consulta el grupo
            SegGrupoOpcion segGrupoOpcion = segGrupoOpcionLogic.getSegGrupoOpcion(gruCodigo);
            if (segGrupoOpcion == null){
            	throw new Exception("No existe el grupo con código " + gruCodigo); 
            }
            
            //se consulta el usuario que crea la opción
            SegUsuario segUsuario = segUsuarioLogic.getSegUsuario(usuCreador);
            if (segUsuario == null){
            	throw new Exception("No existe el usuario con código " + usuCreador); 
            }
            
            SegOpcion segOpcion = new SegOpcion(null, segUsuario, segGrupoOpcion, nombre, descripcion, llaveAcceso, estadoRegistro, orden, null);

            segOpcionDAO.save(segOpcion);

            log.debug("save SegOpcion successful");
    		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void modificarOpcion(Long opcCodigo, String nombre, String descripcion, String llaveAcceso, Long gruCodigo, Long usuCreador, String estadoRegistro, Integer orden) throws Exception{
    	try {
			

            if (nombre == null){
            	throw new Exception("Debe ingresar el nombre de la opción");
            }
            
            if (descripcion == null){
            	throw new Exception("Debe ingresar la descripcion de la opción");
            }
            
            if (llaveAcceso == null){
            	throw new Exception("Debe ingresar la llave de acceso de la opción");
            }
            
            if (gruCodigo == null){
            	throw new Exception("Debe ingresar el grupo al que pertenece la opción");
            }
            
            if (usuCreador == null){
            	throw new Exception("Debe ingresar el usuario que crea la opción");
            }
            
            //Se consulta la opcion
            SegOpcion segOpcion = getSegOpcion(opcCodigo);
            if (segOpcion == null){
            	throw new Exception ("No existe la opción con código: " + opcCodigo);
            }
            
            //Se consulta el grupo
            SegGrupoOpcion segGrupoOpcion = segGrupoOpcionLogic.getSegGrupoOpcion(gruCodigo);
            if (segGrupoOpcion == null){
            	throw new Exception("No existe el grupo con código " + gruCodigo); 
            }
            
            //se consulta el usuario que crea la opción
            SegUsuario segUsuario = segUsuarioLogic.getSegUsuario(usuCreador);
            if (segUsuario == null){
            	throw new Exception("No existe el usuario con código " + usuCreador); 
            }

            segOpcion.setOpcCodigo(opcCodigo);
            segOpcion.setOpcDescripcion(descripcion);
            segOpcion.setOpcEstadoRegistro(estadoRegistro);
            segOpcion.setOpcLlaveAcceso(llaveAcceso);
            segOpcion.setOpcNombre(nombre);
            segOpcion.setSegGrupoOpcion(segGrupoOpcion);
            segOpcion.setSegUsuario(segUsuario);
            segOpcion.setOrden(orden);

            segOpcionDAO.update(segOpcion);

            log.debug("update SegOpcion successful");
    		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
    }
    
    
    @Override
    @Transactional(readOnly=true)
	public List<SegOpcionDTO> consultarOpcionesPorSistema(Long sisCodigo) throws Exception {

		try {

			List<SegOpcionDTO> segOpcionDTO = new ArrayList<SegOpcionDTO>();

			if (sisCodigo == null) {
				throw new Exception(
						"El usuario ser administrador de algun sistema");
			}

			List<SegOpcion> opcionModel = segOpcionDAO.consultarOpcionesPorSistema(sisCodigo);

			for (SegOpcion segOpcion : opcionModel) {

				SegOpcionDTO segOpcionDTO2 = new SegOpcionDTO();
				
				segOpcionDTO2.setOpcCodigo(segOpcion.getOpcCodigo());
				segOpcionDTO2.setOpcNombre(segOpcion.getOpcNombre());
				segOpcionDTO2.setOpcEstadoRegistro(segOpcion.getOpcEstadoRegistro().equals("A") ? "Activo"
								: "Inactivo");
				segOpcionDTO2.setOpcLlaveAcceso(segOpcion.getOpcLlaveAcceso());
				segOpcionDTO2.setOpcDescripcion(segOpcion.getOpcDescripcion());
				segOpcionDTO2
						.setGruCodigo_SegGrupoOpcion(segOpcion.getSegGrupoOpcion().getGruCodigo());
				segOpcionDTO2
						.setGruNombre_SegGrupoOpcion(segOpcion.getSegGrupoOpcion().getGruNombre());
				//segOpcionDTO2.setUsuCodigo_SegUsuario(segOpcion.getSegUsuario().getUsuCodigo());
				segOpcionDTO2.setOrden(segOpcion.getOrden());
				segOpcionDTO.add(segOpcionDTO2);

			}
			return segOpcionDTO;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
    
    @Override
    @Transactional(readOnly=true)
	public List<SegOpcion> consultarOpcionesDeGrupoOpcion(Long gruCodigo) throws Exception {
    	try {
			
    		//Se consulta el grupo
    		SegGrupoOpcion segGrupoOpcion = segGrupoOpcionLogic.getSegGrupoOpcion(gruCodigo);
    		
    		if (segGrupoOpcion == null || !segGrupoOpcion.getGruEstadoRegistro().equals("A")){
    			throw new Exception("no existe el grupo " + gruCodigo);
    		}
    		
    		return new ArrayList<SegOpcion>(segGrupoOpcion.getSegOpcions());
    		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
    }

	/** 
	 * (non-Javadoc)
	 * @see com.vortexbird.sentinel.modelo.control.ISegOpcionLogic#consultarOpcionesPorRol(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<SegOpcionDTO> consultarOpcionesPorRol(Long rol) throws Exception{
		List<SegOpcionDTO> segOpcionDTO = new ArrayList<SegOpcionDTO>();
		try {
			if (rol == null) {
				throw new Exception("El rol es requerido");
			}
			
			Object[] variables = {"segRol.rolCodigo", false, rol, "=",
					"segOpcion.opcCodigo", false, "NULL", "IS NOT",
					"perEstadoRegistro", true, Constantes.ESTADO_ACTIVO, "="};

			List<SegPermiso> permisos = segPermisoLogic.findByCriteria(variables, null, null);
			
			if(permisos == null || permisos.isEmpty()){
				throw new Exception("El rol " + rol + " no tiene opciones");
			}
			
			for (SegPermiso permiso : permisos) {

				SegOpcion segOpcion = permiso.getSegOpcion();
				
				SegOpcionDTO segOpcionDTO2 = new SegOpcionDTO();
				
				segOpcionDTO2.setOpcCodigo(segOpcion.getOpcCodigo());
				segOpcionDTO2.setOpcNombre(segOpcion.getOpcNombre());
				segOpcionDTO2.setOpcEstadoRegistro(segOpcion.getOpcEstadoRegistro().equals("A") ? "Activo"
								: "Inactivo");
				segOpcionDTO2.setOpcLlaveAcceso(segOpcion.getOpcLlaveAcceso());
				segOpcionDTO2.setOpcDescripcion(segOpcion.getOpcDescripcion());
				segOpcionDTO2
						.setGruCodigo_SegGrupoOpcion(segOpcion.getSegGrupoOpcion().getGruCodigo());
				segOpcionDTO2
						.setGruNombre_SegGrupoOpcion(segOpcion.getSegGrupoOpcion().getGruNombre());
				//segOpcionDTO2.setUsuCodigo_SegUsuario(segOpcion.getSegUsuario().getUsuCodigo());
				segOpcionDTO2.setOrden(segOpcion.getOrden());
				segOpcionDTO.add(segOpcionDTO2);

			}
			return segOpcionDTO;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
}
