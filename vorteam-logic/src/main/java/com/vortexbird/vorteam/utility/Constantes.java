package com.vortexbird.vorteam.utility;

import java.math.BigDecimal;

/**
 * 
 *
 * @author Daniel Pareja Londoño
 * @version jul. 31, 2018
 * @since 1.8
 *
 */
public class Constantes {
	public final static String ESTADO_ACTIVO = "A", ESTADO_INACTIVO = "I", ESTADO_FINALIZADO = "F";
	public final static Long SOM_OPCION_SELECCIONAR = 0L, COSTO_TOTAL_INICIAL = 0L, ESTADO_CREADO_ID = 1L, ESTADO_REALIZADO = 2L;
	public final static String NO = "N", SI = "S";
	
	
	public final static String URL_SENTINEL = "URL_SENTINEL";
	public static final String USU_CODIGO_INTERNO = "1";
	public static final Long USU_INTENTOS = 0L;
	public static final String USU_COMPANIA = "1";
	public static final String USU_SESION = "1";
	public static final String USU_ROL_CODIGO_ADMIN = "1";
	public static final String USU_ROL_CODIGO_DESA = "2";
	
	public static final Long CONTROL_CAMBIOS_ID = 3L;
	public static final Long SOPORTE_ID = 2L;
	
	public static final String PATTERN_ANHO_MES_DIA_JUNTOS = "yyyyMMdd";
	
	public static final String HORAS_DIARIAS = "HORAS_DIARIAS", PORCENTAJE_HORAS_MINIMAS_REPORTAR="PORCENTAJE_HORAS_MINIMAS_REPORTAR";
	public static final String RUTA_BASE_REPORTES = "RUTA_BASE_REPORTES";
	
	public enum REPORT_OUTPUT_TYPE {XLS, XLSX, PDF, CSV, DOC, DOCX, HTML};
	
	public static final String[] ESTADOS_PROYECTO = {"ACTIVO","FINALIZADO"};
	public static final String[] ESTADOS_GENERALES = {"ACTIVO","INACTIVO"};
	
	public static final String US_NOTIFICACION = "US_NOTIFICACION", PS_NOTIFICACION="PS_NOTIFICACION", URL_VORTEAM="URL_VORTEAM";
	
	public static BigDecimal CIEN = new BigDecimal(100);
	
	public static String ARROBA = "@";
	
	public static String P_DESTINATARIOS_INFORME_OCUPACION_PLANTA = "P_DESTINATARIOS_INFORME_OCUPACION_PLANTA";
	public static String P_CARACTER_SEPARACION_CORREOS = "P_CARACTER_SEPARACION_CORREOS";
	
}