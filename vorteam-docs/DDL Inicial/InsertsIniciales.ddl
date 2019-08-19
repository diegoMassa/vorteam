--Inserts Tipo Identificación
INSERT INTO public.vt_tipo_identificacion
(descripcion, activo, usua_creador, fecha_creacion)
VALUES('Cédula de Ciudadanía', 'A', '1', 'now');

INSERT INTO public.vt_tipo_identificacion
(descripcion, activo, usua_creador, fecha_creacion)
VALUES('NIT', 'A', '1', 'now');

--Inserts Tipo Actividad
INSERT INTO public.vt_tipo_actividad
(descripcion, activo, usua_creador, fecha_creacion)
VALUES('Desarrollo', 'A', '1', 'now');

INSERT INTO public.vt_tipo_actividad
(descripcion, activo, usua_creador, fecha_creacion)
VALUES('Soporte', 'A', '1', 'now');

INSERT INTO public.vt_tipo_actividad
(descripcion, activo, usua_creador, fecha_creacion)
VALUES('Control de Cambios', 'A', '1', 'now');

INSERT INTO public.vt_tipo_actividad
(descripcion, activo, usua_creador, fecha_creacion)
VALUES('Reunión', 'A', '1', 'now');

INSERT INTO public.vt_tipo_actividad
(descripcion, activo, usua_creador, fecha_creacion)
VALUES('Estimación', 'A', '1', 'now');

--Inserts Estado Actividad
INSERT INTO public.vt_estado
(descripcion, activo, usua_creador, fecha_creacion)
VALUES('Creada', 'A', '1', 'now');

INSERT INTO public.vt_estado
(descripcion, activo, usua_creador, fecha_creacion)
VALUES('Realizada', 'A', '1', 'now');

--Inserts Linea de Negocio
INSERT INTO public.vt_linea_negocio
(descripcion, activo, usua_creador, fecha_creacion)
VALUES('Desarrollo a la Medida', 'A', '1', 'now');

INSERT INTO public.vt_linea_negocio
(descripcion, activo, usua_creador, fecha_creacion)
VALUES('Soporte de Aplicaciones', 'A', '1', 'now');

INSERT INTO public.vt_linea_negocio
(descripcion, activo, usua_creador, fecha_creacion)
VALUES('Soporte de Servidores', 'A', '1', 'now');

INSERT INTO public.vt_linea_negocio
(descripcion, activo, usua_creador, fecha_creacion)
VALUES('Integración de Servicios', 'A', '1', 'now');

INSERT INTO public.vt_linea_negocio
(descripcion, activo, usua_creador, fecha_creacion)
VALUES('Outsourcing', 'A', '1', 'now');

INSERT INTO public.vt_linea_negocio
(descripcion, activo, usua_creador, fecha_creacion)
VALUES('Producto', 'A', '1', 'now');

--Inserts Sistema Seguridad
INSERT INTO seguridad.seg_grupo_opcion(gru_codigo, gru_nombre, gru_descripcion, gru_llave_acceso, gru_estado_registro, gru_icono, sis_codigo)VALUES(1, 'Gestión Administrador', 'Gestión', '', 'A', '', 1);
INSERT INTO seguridad.seg_grupo_opcion(gru_codigo, gru_nombre, gru_descripcion, gru_llave_acceso, gru_estado_registro, gru_icono, sis_codigo)VALUES(2, 'Tareas Usuario', 'Tareas', '', 'A', '', 1);

INSERT INTO seguridad.seg_opcion(opc_codigo, opc_nombre, opc_descripcion, 
opc_llave_acceso, seg_grupo_opcion_gru_codigo, opc_estado_registro, 
mod_usu_codigo, orden)
VALUES(1, 'Clientes', 'Clientes', 
'/XHTML/gestionClientes.xhtml', 1, 'A', 
null, null);

INSERT INTO seguridad.seg_opcion(opc_codigo, opc_nombre, opc_descripcion, 
opc_llave_acceso, seg_grupo_opcion_gru_codigo, opc_estado_registro, 
mod_usu_codigo, orden)
VALUES(2, 'Proyectos', 'Clientes', 
'/XHTML/gestionProyectos.xhtml', 1, 'A', 
null, null);

INSERT INTO seguridad.seg_opcion(opc_codigo, opc_nombre, opc_descripcion, 
opc_llave_acceso, seg_grupo_opcion_gru_codigo, opc_estado_registro, 
mod_usu_codigo, orden)
VALUES(3, 'Crear Tareas', 'Crear Tareas', 
'/XHTML/crearTareasAdministrador.xhtml', 1, 'A', 
null, null);

INSERT INTO seguridad.seg_opcion(opc_codigo, opc_nombre, opc_descripcion, 
opc_llave_acceso, seg_grupo_opcion_gru_codigo, opc_estado_registro, 
mod_usu_codigo, orden)
VALUES(4, 'Usuarios', 'Usuarios', 
'/XHTML/gestionPersonas.xhtml', 1, 'A', 
null, null);

INSERT INTO seguridad.seg_opcion(opc_codigo, opc_nombre, opc_descripcion, 
opc_llave_acceso, seg_grupo_opcion_gru_codigo, opc_estado_registro, 
mod_usu_codigo, orden)
VALUES(5, 'Mis Actividades', 'Mis Actividades', 
'/XHTML/misActividades.xhtml', 1, 'A', 
null, null);

INSERT INTO seguridad.seg_opcion(opc_codigo, opc_nombre, opc_descripcion, 
opc_llave_acceso, seg_grupo_opcion_gru_codigo, opc_estado_registro, 
mod_usu_codigo, orden)
VALUES(6, 'Reportar Actividades', 'Reportar Actividades', 
'/XHTML/crearTareasUsuario.xhtml', 1, 'A', 
null, null);

INSERT INTO seguridad.seg_opcion(opc_codigo, opc_nombre, opc_descripcion, 
opc_llave_acceso, seg_grupo_opcion_gru_codigo, opc_estado_registro, 
mod_usu_codigo, orden)
VALUES(9, 'Reporte de Tiempos entre fechas', 'Reporte de Tiempos entre fechas', 
'/XHTML/reporteTiempoEntreFechas.xhtml', 1, 'A', 
null, null);

INSERT INTO seguridad.seg_opcion(opc_codigo, opc_nombre, opc_descripcion, 
opc_llave_acceso, seg_grupo_opcion_gru_codigo, opc_estado_registro, 
mod_usu_codigo, orden)
VALUES(7, 'Mis Actividades', 'Mis Actividades', 
'/XHTML/misActividades.xhtml', 2, 'A', 
null, null);

INSERT INTO seguridad.seg_opcion(opc_codigo, opc_nombre, opc_descripcion, 
opc_llave_acceso, seg_grupo_opcion_gru_codigo, opc_estado_registro, 
mod_usu_codigo, orden)
VALUES(8, 'Reportar Actividades', 'Reportar Actividades', 
'/XHTML/crearTareasUsuario.xhtml', 2, 'A', 
null, null);

--Admin
INSERT INTO seguridad.seg_permiso (per_codigo, per_estado_registro, rol_codigo,  
usu_codigo, gru_codigo, sic_codigo)
VALUES(1,'A',1,
1,1,1);

--Desarrollador
INSERT INTO seguridad.seg_permiso (per_codigo, per_estado_registro, rol_codigo,  
usu_codigo, gru_codigo, sic_codigo)
VALUES(2,'A',2,
null,2,1);


--Cambio 14 Agosto 2018
--Insert Parametro
insert into vt_parametro(nombre_corto, descripcion, valor, activo, usua_creador, fecha_creacion)
values('URL_SENTINEL','Ruta del Sistema de Seguridad Sentinel', 'http://127.0.0.1:8080/sentinel-vorteam/controller/securityServices','A', '1', 'now');

alter table vt_reporte_tiempo 
alter column HORAS_EJECUTADAS type numeric(5, 2);

alter table vt_actividad 
alter column horas_presupuestadas type numeric(5, 2);

--Cambios 16 Agosto 2018
alter table vt_reporte_tiempo drop column fecha_inicio;

alter table vt_reporte_tiempo rename column fecha_fin to fecha;

--Cambios 31 Agosto 2018
alter table vt_actividad add column control_cambios character varying;

--Inserts para porcentaje horas mínimas tiempo laboral
insert into vt_parametro(nombre_corto, descripcion, valor, activo, usua_creador, fecha_creacion)
values('PORCENTAJE_HORAS_MINIMAS_REPORTAR','Porcentaje de Horas mínimas que debe reportar el usuario diariamente para que no llegue la notificación','70','A','1','now');

--Festivos 2018 Colombia
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('1/01/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('8/01/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('19/03/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('25/03/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('29/03/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('30/03/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('1/04/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('1/05/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('14/05/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('4/06/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('11/06/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('2/07/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('20/07/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('7/08/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('20/08/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('15/10/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('5/11/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('12/11/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('8/12/18','dd/MM/yy'),'A','1','now');
insert into vt_festivos(fecha, activo, usua_creador, fecha_creacion) values(to_date('25/12/18','dd/MM/yy'),'A','1','now');

insert into vt_parametro(nombre_corto, descripcion, valor, activo, usua_creador, fecha_creacion)
values('US_NOTIFICACION','Usuario que hace la notificacion','algo@vortexbird.com','A','1','now');
insert into vt_parametro(nombre_corto, descripcion, valor, activo, usua_creador, fecha_creacion)
values('PS_NOTIFICACION','Notificacion','n0t1f1c4c10n3s','A','1','now');

--Inserts para Sistema de Seguridad Septiembre 24 de 2018
INSERT INTO seguridad.seg_opcion(opc_codigo, opc_nombre, opc_descripcion, 
opc_llave_acceso, seg_grupo_opcion_gru_codigo, opc_estado_registro, 
mod_usu_codigo, orden)
VALUES(10, 'Cambiar Clave', 'Cambiar Clave', 
'/XHTML/cambiarClave.xhtml', 1, 'A', 
null, null);
INSERT INTO seguridad.seg_opcion(opc_codigo, opc_nombre, opc_descripcion, 
opc_llave_acceso, seg_grupo_opcion_gru_codigo, opc_estado_registro, 
mod_usu_codigo, orden)
VALUES(11, 'Cambiar Clave', 'Cambiar Clave', 
'/XHTML/cambiarClave.xhtml', 2, 'A', 
null, null);

-- Insert para gestión de Tipo de Actividad en Sentinel 24 Septiembre 2018
INSERT INTO seguridad.seg_opcion(opc_codigo, opc_nombre, opc_descripcion, 
opc_llave_acceso, seg_grupo_opcion_gru_codigo, opc_estado_registro, 
mod_usu_codigo, orden)
VALUES(12, 'Tipos de Actividad', 'Tipos de Actividad', 
'/XHTML/gestionTipoActividad.xhtml', 1, 'A', 
null, null);

-- Cambio para agregar Clasificación Financiera de un tipo de Actividad
CREATE TABLE VT_CLASIFICACION_FINANCIERA (
  CLFI_ID            BIGSERIAL NOT NULL,
  DESCRIPCION        character varying NOT NULL,
  ACTIVO             character NOT NULL,
  USUA_CREADOR       character varying NOT NULL,
  FECHA_CREACION     timestamp NOT NULL,
  USUA_MODIFICADOR   character varying,
  FECHA_MODIFICACION timestamp,
  PRIMARY KEY (CLFI_ID));

CREATE UNIQUE INDEX VT_CLASIFICACION_FINANCIERA_DESCRIPCION
  ON VT_CLASIFICACION_FINANCIERA (DESCRIPCION);

INSERT INTO VT_CLASIFICACION_FINANCIERA(DESCRIPCION, ACTIVO, USUA_CREADOR, FECHA_CREACION)
VALUES('Costo directo','A','1','now');
INSERT INTO VT_CLASIFICACION_FINANCIERA(DESCRIPCION, ACTIVO, USUA_CREADOR, FECHA_CREACION)
VALUES('Costo indirecto','A','1','now');
INSERT INTO VT_CLASIFICACION_FINANCIERA(DESCRIPCION, ACTIVO, USUA_CREADOR, FECHA_CREACION)
VALUES('Gasto','A','1','now');
INSERT INTO VT_CLASIFICACION_FINANCIERA(DESCRIPCION, ACTIVO, USUA_CREADOR, FECHA_CREACION)
VALUES('Indefinido','A','1','now');

ALTER TABLE VT_TIPO_ACTIVIDAD ADD COLUMN CLFI_ID BIGINT NULL;
UPDATE VT_TIPO_ACTIVIDAD SET CLFI_ID = 4;
CREATE INDEX VT_TIPO_ACTIVIDAD_CLFI_ID ON VT_TIPO_ACTIVIDAD (CLFI_ID);
ALTER TABLE VT_TIPO_ACTIVIDAD ADD CONSTRAINT FK_CLFI_TIAC FOREIGN KEY (CLFI_ID) REFERENCES VT_CLASIFICACION_FINANCIERA (CLFI_ID);
ALTER TABLE VT_TIPO_ACTIVIDAD ALTER COLUMN CLFI_ID SET NOT NULL;

ALTER TABLE VT_REPORTE_TIEMPO ADD COLUMN PORCENTAJE_AVANCE NUMERIC(5,2) NULL;
ALTER TABLE VT_ACTIVIDAD ADD COLUMN PORCENTAJE_AVANCE NUMERIC(5,2) NULL;

update vt_actividad set porcentaje_Avance = 100 where esta_id = 2;
update vt_reporte_tiempo set porcentaje_avance = 100 where esta_id = 2;


-- Inserts para envío de correo para el reporte de ocupación de planta
INSERT INTO public.vt_parametro
(nombre_corto, descripcion, valor, activo, usua_creador, fecha_creacion)
VALUES('P_DESTINATARIOS_INFORME_OCUPACION_PLANTA', 'Lista de correos a los que llegará el informe de ocupación planta del día anterior', 'rcollazos@vortexbird.com;dgomez@vortexbird.com;', 'A', '1', 'now');

INSERT INTO public.vt_parametro
(nombre_corto, descripcion, valor, activo, usua_creador, fecha_creacion)
VALUES('P_CARACTER_SEPARACION_CORREOS', 'Caracter de separación de la lista de correos para el parámetro P_DESTINATARIOS_INFORME_OCUPACION_PLANTA', ';', 'A', '1', 'now');
