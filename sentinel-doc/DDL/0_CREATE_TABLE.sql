CREATE TABLE seg_auditoria (
    aut_codigo bigint NOT NULL,
    usu_codigo bigint,
    tabla character varying(200),
    cod_registro numeric,
    campo character varying(200),
    tipo character varying(100),
    fecha date,
    valor_anterior character varying(200),
    valor_nuevo character varying(200)
);

CREATE TABLE seg_cambio_pass (
    cap_codigo bigint NOT NULL,
    seg_usuario_usu_codigo bigint,
    cap_fecha_fin timestamp(6) without time zone,
    cap_token character varying(200),
    cap_estado character varying(50),
    cap_fecha_ini timestamp(6) without time zone
);

CREATE TABLE seg_compania (
    cia_codigo bigint NOT NULL,
    cia_codigo_interno character varying(15) NOT NULL,
    cia_nombre character varying(50) NOT NULL,
    cia_estado_registro character varying(1) NOT NULL,
    mod_usu_codigo bigint
);

CREATE TABLE seg_grupo_opcion (
    gru_codigo bigint NOT NULL,
    gru_nombre character varying(50) NOT NULL,
    gru_descripcion character varying(200),
    gru_llave_acceso character varying(200),
    gru_estado_registro character varying(1) NOT NULL,
    gru_icono character varying(200),
    gru_codigo_padre bigint,
    sis_codigo bigint NOT NULL,
    mod_usu_codigo bigint,
    orden integer
);

CREATE TABLE seg_historial_constrasena (
    hco_codigo bigint NOT NULL,
    hco_constrasena character varying(50) NOT NULL,
    hco_fecha_cambio date NOT NULL,
    hco_detalle_cambio character varying(100),
    usu_codigo bigint NOT NULL
);

CREATE TABLE seg_opcion (
    opc_codigo bigint NOT NULL,
    opc_nombre character varying(100) NOT NULL,
    opc_descripcion character varying(200),
    opc_llave_acceso character varying(200),
    seg_grupo_opcion_gru_codigo bigint NOT NULL,
    opc_estado_registro character varying(1) NOT NULL,
    mod_usu_codigo bigint,
    orden integer
);

CREATE TABLE seg_parametro (
    par_codigo bigint NOT NULL,
    par_nombre character varying(100) NOT NULL,
    par_valor_numerico numeric(10,4),
    par_valor_alfanumerico character varying(100),
    par_valor_fecha date,
    par_estado_registro character varying(1) NOT NULL,
    mod_usu_codigo bigint
);

CREATE TABLE seg_permiso (
    per_codigo bigint NOT NULL,
    per_estado_registro character varying(1) NOT NULL,
    rol_codigo bigint,
    opc_codigo bigint,
    usu_codigo bigint,
    gru_codigo bigint,
    sic_codigo bigint,
    suc_codigo bigint,
    mod_usu_codigo bigint
);

CREATE TABLE seg_rol (
    rol_codigo bigint NOT NULL,
    rol_nombre character varying(50) NOT NULL,
    rol_descripcion character varying(200),
    rol_dias_caducidad_pwd numeric(3,0),
    seg_sistema_sis_codigo bigint NOT NULL,
    rol_estado_registro character varying(1) NOT NULL,
    mod_usu_codigo bigint,
    es_admon_de_aplicacion text NOT NULL
);

CREATE TABLE seg_rol_usuario (
    rlu_codigo bigint NOT NULL,
    seg_usuario_usu_codigo bigint NOT NULL,
    seg_rol_rol_codigo bigint NOT NULL,
    rlu_estado_registro character varying(1) NOT NULL,
    mod_usu_codigo bigint
);

CREATE TABLE seg_sistema (
    sis_codigo bigint NOT NULL,
    sis_nombre character varying(50) NOT NULL,
    sis_descripcion character varying(200),
    sis_estado_registro character varying(1) NOT NULL,
    mod_usu_codigo bigint,
    sis_url text,
    sis_icono bytea
);

CREATE TABLE seg_sistema_cia (
    sic_codigo bigint NOT NULL,
    seg_sistema_sis_codigo bigint NOT NULL,
    seg_compania_cia_codigo bigint NOT NULL,
    sic_estado_registro character varying(1),
    mod_usu_codigo bigint
);

CREATE TABLE seg_sucursal (
    suc_codigo bigint NOT NULL,
    suc_codigo_interno character varying(15) NOT NULL,
    cia_codigo bigint NOT NULL,
    suc_nombre character varying(100) NOT NULL,
    suc_estado_registro character varying(1) NOT NULL,
    mod_usu_codigo bigint
);

CREATE TABLE seg_usuario (
    usu_codigo bigint NOT NULL,
    usu_nombres character varying(50) NOT NULL,
    usu_apellidos character varying(50) NOT NULL,
    usu_login character varying(30) NOT NULL,
    usu_constrasena character varying(50) NOT NULL,
    usu_estado_registro character varying(1) NOT NULL,
    usu_codigo_interno character varying(50) NOT NULL,
    mod_usu_codigo bigint,
    usu_ultmima_modificacion_pass date,
    usu_correo character varying(200),
    usu_intentos_fallidos numeric(18,0),
    usu_compania_cia_codigo numeric(12,0)
);



ALTER TABLE ONLY seg_compania
    ADD CONSTRAINT cia_pk PRIMARY KEY (cia_codigo);

ALTER TABLE ONLY seg_grupo_opcion
    ADD CONSTRAINT gru_pk PRIMARY KEY (gru_codigo);

ALTER TABLE ONLY seg_historial_constrasena
    ADD CONSTRAINT hco_pk PRIMARY KEY (hco_codigo);

ALTER TABLE ONLY seg_auditoria
    ADD CONSTRAINT log_pk PRIMARY KEY (aut_codigo);

ALTER TABLE ONLY seg_opcion
    ADD CONSTRAINT opc_pk PRIMARY KEY (opc_codigo);

ALTER TABLE ONLY seg_parametro
    ADD CONSTRAINT par_pk PRIMARY KEY (par_codigo);

ALTER TABLE ONLY seg_permiso
    ADD CONSTRAINT per_pk PRIMARY KEY (per_codigo);

ALTER TABLE ONLY seg_rol_usuario
    ADD CONSTRAINT rlu_pk PRIMARY KEY (rlu_codigo);

ALTER TABLE ONLY seg_rol
    ADD CONSTRAINT rol_pk PRIMARY KEY (rol_codigo);

ALTER TABLE ONLY seg_cambio_pass
    ADD CONSTRAINT seg_cambio_pass_pk PRIMARY KEY (cap_codigo);

ALTER TABLE ONLY seg_sistema_cia
    ADD CONSTRAINT sic_pk PRIMARY KEY (sic_codigo);

ALTER TABLE ONLY seg_sistema
    ADD CONSTRAINT sis_pk PRIMARY KEY (sis_codigo);

ALTER TABLE ONLY seg_sucursal
    ADD CONSTRAINT suc_pk PRIMARY KEY (suc_codigo);

ALTER TABLE ONLY seg_usuario
    ADD CONSTRAINT usu_pk PRIMARY KEY (usu_codigo);

ALTER TABLE ONLY seg_cambio_pass
    ADD CONSTRAINT fk_usuario FOREIGN KEY (seg_usuario_usu_codigo) REFERENCES seg_usuario(usu_codigo);

ALTER TABLE ONLY seg_grupo_opcion
    ADD CONSTRAINT gru_gru_fk FOREIGN KEY (gru_codigo_padre) REFERENCES seg_grupo_opcion(gru_codigo);

ALTER TABLE ONLY seg_opcion
    ADD CONSTRAINT gru_opc_fk FOREIGN KEY (seg_grupo_opcion_gru_codigo) REFERENCES seg_grupo_opcion(gru_codigo);

ALTER TABLE ONLY seg_permiso
    ADD CONSTRAINT gru_per_fk FOREIGN KEY (gru_codigo) REFERENCES seg_grupo_opcion(gru_codigo);

ALTER TABLE ONLY seg_historial_constrasena
    ADD CONSTRAINT hco_usu_fk FOREIGN KEY (usu_codigo) REFERENCES seg_usuario(usu_codigo);

ALTER TABLE ONLY seg_auditoria
    ADD CONSTRAINT log_usu_fk FOREIGN KEY (usu_codigo) REFERENCES seg_usuario(usu_codigo);

ALTER TABLE ONLY seg_compania
    ADD CONSTRAINT mod_usu_cia_fk FOREIGN KEY (mod_usu_codigo) REFERENCES seg_usuario(usu_codigo);

ALTER TABLE ONLY seg_grupo_opcion
    ADD CONSTRAINT mod_usu_gru_fk FOREIGN KEY (mod_usu_codigo) REFERENCES seg_usuario(usu_codigo);

ALTER TABLE ONLY seg_opcion
    ADD CONSTRAINT mod_usu_opc_fk FOREIGN KEY (mod_usu_codigo) REFERENCES seg_usuario(usu_codigo);

ALTER TABLE ONLY seg_parametro
    ADD CONSTRAINT mod_usu_par_fk FOREIGN KEY (mod_usu_codigo) REFERENCES seg_usuario(usu_codigo);

ALTER TABLE ONLY seg_permiso
    ADD CONSTRAINT mod_usu_per_fk FOREIGN KEY (mod_usu_codigo) REFERENCES seg_usuario(usu_codigo);

ALTER TABLE ONLY seg_rol_usuario
    ADD CONSTRAINT mod_usu_rlu_fk FOREIGN KEY (mod_usu_codigo) REFERENCES seg_usuario(usu_codigo);

ALTER TABLE ONLY seg_rol
    ADD CONSTRAINT mod_usu_rol_fk FOREIGN KEY (mod_usu_codigo) REFERENCES seg_usuario(usu_codigo);

ALTER TABLE ONLY seg_sistema_cia
    ADD CONSTRAINT mod_usu_sic_fk FOREIGN KEY (mod_usu_codigo) REFERENCES seg_usuario(usu_codigo);

ALTER TABLE ONLY seg_sistema
    ADD CONSTRAINT mod_usu_sis_fk FOREIGN KEY (mod_usu_codigo) REFERENCES seg_usuario(usu_codigo);

ALTER TABLE ONLY seg_sucursal
    ADD CONSTRAINT mod_usu_suc_fk FOREIGN KEY (mod_usu_codigo) REFERENCES seg_usuario(usu_codigo);

ALTER TABLE ONLY seg_permiso
    ADD CONSTRAINT opc_per_fk FOREIGN KEY (opc_codigo) REFERENCES seg_opcion(opc_codigo);

ALTER TABLE ONLY seg_permiso
    ADD CONSTRAINT per_sic_fk FOREIGN KEY (sic_codigo) REFERENCES seg_sistema_cia(sic_codigo);

ALTER TABLE ONLY seg_permiso
    ADD CONSTRAINT per_suc_fk FOREIGN KEY (suc_codigo) REFERENCES seg_sucursal(suc_codigo);

ALTER TABLE ONLY seg_permiso
    ADD CONSTRAINT rol_per_fk FOREIGN KEY (rol_codigo) REFERENCES seg_rol(rol_codigo);

ALTER TABLE ONLY seg_rol_usuario
    ADD CONSTRAINT rol_rlu_fk FOREIGN KEY (seg_rol_rol_codigo) REFERENCES seg_rol(rol_codigo);

ALTER TABLE ONLY seg_sistema_cia
    ADD CONSTRAINT sci_sis_fk FOREIGN KEY (seg_sistema_sis_codigo) REFERENCES seg_sistema(sis_codigo);

ALTER TABLE ONLY seg_usuario
    ADD CONSTRAINT seg_usuario_seg_usuario_fk1 FOREIGN KEY (mod_usu_codigo) REFERENCES seg_usuario(usu_codigo);

ALTER TABLE ONLY seg_sistema_cia
    ADD CONSTRAINT sic_cia_fk FOREIGN KEY (seg_compania_cia_codigo) REFERENCES seg_compania(cia_codigo);

ALTER TABLE ONLY seg_grupo_opcion
    ADD CONSTRAINT sis_gru_fk FOREIGN KEY (sis_codigo) REFERENCES seg_sistema(sis_codigo);

ALTER TABLE ONLY seg_rol
    ADD CONSTRAINT sis_rol_fk FOREIGN KEY (seg_sistema_sis_codigo) REFERENCES seg_sistema(sis_codigo);

ALTER TABLE ONLY seg_sucursal
    ADD CONSTRAINT suc_cia_fk FOREIGN KEY (cia_codigo) REFERENCES seg_compania(cia_codigo);

ALTER TABLE ONLY seg_permiso
    ADD CONSTRAINT usu_per_fk FOREIGN KEY (usu_codigo) REFERENCES seg_usuario(usu_codigo);

ALTER TABLE ONLY seg_rol_usuario
    ADD CONSTRAINT usu_rlu_fk FOREIGN KEY (seg_usuario_usu_codigo) REFERENCES seg_usuario(usu_codigo);
