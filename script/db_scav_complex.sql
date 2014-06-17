/*
Navicat PGSQL Data Transfer

Source Server         : PostgreSQL
Source Server Version : 90015
Source Host           : localhost:5432
Source Database       : db_scav
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90000
File Encoding         : 65001

Date: 2014-06-16 15:37:38
*/


-- ----------------------------
-- Sequence structure for bitacora_guardia_id_seq
-- ----------------------------
CREATE SEQUENCE "bitacora_guardia_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for entorno_id_seq
-- ----------------------------
CREATE SEQUENCE "entorno_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for sincronizador_id_seq
-- ----------------------------
CREATE SEQUENCE "sincronizador_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 26
 CACHE 1;
SELECT setval('"public"."sincronizador_id_seq"', 26, true);

-- ----------------------------
-- Sequence structure for tranca_id_seq
-- ----------------------------
CREATE SEQUENCE "tranca_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;
SELECT setval('"public"."tranca_id_seq"', 2, true);

-- ----------------------------
-- Table structure for administrador_entorno
-- ----------------------------
CREATE TABLE "administrador_entorno" (
"ci" varchar(10) NOT NULL,
"nombres" varchar(70) NOT NULL,
"apellidos" varchar(200) NOT NULL,
"email" varchar(50) NOT NULL,
"password" varchar(100) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of administrador_entorno
-- ----------------------------
BEGIN;
INSERT INTO "administrador_entorno" VALUES ('0000000', 'ADMIN', 'ADMIN', 'admin', 'a');
INSERT INTO "administrador_entorno" VALUES ('1234567', 'Juan Milton', 'Chambi Mendoza', 'jmcm@gmail.com', 'jmcm');
INSERT INTO "administrador_entorno" VALUES ('7800725', 'Bismarck', 'Villca Soliz', 'bismarck.villca@firstonesoft.com', 'a');
COMMIT;

-- ----------------------------
-- Table structure for bitacora_guardia
-- ----------------------------
CREATE TABLE "bitacora_guardia" (
"id" int8 DEFAULT nextval(('bitacora_guardia_id_seq'::text)::regclass) NOT NULL,
"fecha_hora" timestamp(6),
"accion" varchar(100),
"detalle" varchar(250),
"ci_guardia" varchar(10)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of bitacora_guardia
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for entorno
-- ----------------------------
CREATE TABLE "entorno" (
"id" int4 DEFAULT nextval(('entorno_id_seq'::text)::regclass) NOT NULL,
"nombre" varchar(100) NOT NULL,
"ubicacion" varchar(100),
"foto" bytea,
"licencia_activa" bool NOT NULL,
"ci" varchar(10)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of entorno
-- ----------------------------
BEGIN;
INSERT INTO "entorno" VALUES ('1', 'Condominio Valeria', '7mo Anillo', null, 't', '1234567');
INSERT INTO "entorno" VALUES ('2', 'Condominio Curpao', '4to Anillop', null, 't', '7800725');
COMMIT;

-- ----------------------------
-- Table structure for guardia
-- ----------------------------
CREATE TABLE "guardia" (
"ci" varchar(10) NOT NULL,
"nombre" varchar(100) NOT NULL,
"apellido" varchar(100) NOT NULL,
"password" varchar(50) NOT NULL,
"estado" bool NOT NULL,
"id_entorno" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for ingreso_salida
-- ----------------------------
CREATE TABLE "ingreso_salida" (
"tipo" varchar(10) NOT NULL,
"fecha_hora" timestamp(6) NOT NULL,
"placa" varchar(10) NOT NULL,
"id_tranca" int4 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for ingreso_salida_visita
-- ----------------------------
CREATE TABLE "ingreso_salida_visita" (
"tipo" varchar(10) NOT NULL,
"fecha_hora" timestamp(6) NOT NULL,
"id_tranca" int4 NOT NULL,
"placa" varchar(10) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for mu_bitacora
-- ----------------------------
CREATE TABLE "mu_bitacora" (
"fecha" timestamp(6) NOT NULL,
"usuario" varchar(50),
"formulario" varchar(50),
"accion" varchar(200),
"direccion_ip" varchar(50)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of mu_bitacora
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for mu_menu
-- ----------------------------
CREATE TABLE "mu_menu" (
"menu_id" int8 NOT NULL,
"menu_id_padre" int8,
"nombre" varchar(50) NOT NULL,
"url" varchar(50),
"estado" bool,
"depende" int4,
"nivel" float4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of mu_menu
-- ----------------------------
BEGIN;
INSERT INTO "mu_menu" VALUES ('10', null, 'Adminitracion', null, 'f', '0', '1');
INSERT INTO "mu_menu" VALUES ('11', '10', 'Gestionar Entornos', 'scav_entorno.xhtml', 'f', '0', '1.2');
INSERT INTO "mu_menu" VALUES ('20', null, 'Adminitracion Entorno', null, 't', '0', '2');
INSERT INTO "mu_menu" VALUES ('21', '20', 'Gestionar Conductores', 'scav_propietario.xhtml', 't', '0', '2.1');
INSERT INTO "mu_menu" VALUES ('22', '20', 'Gestionar Vehiculos', 'scav_vehiculo.xhtml', 't', '0', '2.2');
INSERT INTO "mu_menu" VALUES ('23', '20', 'Gestionar Guardias', 'scav_guardia.xhtml', 't', '0', '2.3');
INSERT INTO "mu_menu" VALUES ('24', '20', 'Gestionar Trancas', 'scav_tranca.xhtml', 't', '0', '2.4');
INSERT INTO "mu_menu" VALUES ('30', null, 'Comunicacion', null, 't', '0', '3');
INSERT INTO "mu_menu" VALUES ('31', '30', 'Avisos y Alarmas', 'scav_aviso_alarma.xhtml', 't', '0', '3.1');
INSERT INTO "mu_menu" VALUES ('40', null, 'Reportes', null, 't', '0', '4');
INSERT INTO "mu_menu" VALUES ('41', '40', 'Generacion de Reportes', 'scav_reporte.xhtml', 't', '0', '4.1');
COMMIT;

-- ----------------------------
-- Table structure for propietario
-- ----------------------------
CREATE TABLE "propietario" (
"ci" varchar(10) NOT NULL,
"nombres" varchar(70) NOT NULL,
"apellidos" varchar(200) NOT NULL,
"foto" bytea,
"nro_licencia" varchar(10) NOT NULL,
"estado" bool NOT NULL,
"id_entorno" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for propietario_vehiculo
-- ----------------------------
CREATE TABLE "propietario_vehiculo" (
"ci" varchar(10) NOT NULL,
"placa" varchar(10) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for sincronizador
-- ----------------------------
CREATE TABLE "sincronizador" (
"id" int8 DEFAULT nextval(('sincronizador_id_seq'::text)::regclass) NOT NULL,
"transaccion" char(1) NOT NULL,
"tabla" varchar(50) NOT NULL,
"id_entorno" int4 NOT NULL,
"ref_id" varchar(50) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for telefono_adminitrador
-- ----------------------------
CREATE TABLE "telefono_adminitrador" (
"telefono" int4 NOT NULL,
"ci" varchar(10) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of telefono_adminitrador
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for telefono_propietario
-- ----------------------------
CREATE TABLE "telefono_propietario" (
"telefono" int4 NOT NULL,
"ci" varchar(10) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for tranca
-- ----------------------------
CREATE TABLE "tranca" (
"id" int4 DEFAULT nextval(('tranca_id_seq'::text)::regclass) NOT NULL,
"descripcion" varchar(100),
"tipo" varchar(10) NOT NULL,
"id_entorno" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for vehiculo
-- ----------------------------
CREATE TABLE "vehiculo" (
"placa" varchar(10) NOT NULL,
"marca" varchar(100),
"modelo" varchar(50),
"foto" bytea,
"rfid" int4 NOT NULL,
"estado" bool NOT NULL,
"id_entorno" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for vehiculo_visita
-- ----------------------------
CREATE TABLE "vehiculo_visita" (
"placa" varchar(10) NOT NULL,
"marca" varchar(100)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for visita
-- ----------------------------
CREATE TABLE "visita" (
"ci" varchar(10) NOT NULL,
"nombres" varchar(70) NOT NULL,
"apellidos" varchar(200) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for visita_vehiculo
-- ----------------------------
CREATE TABLE "visita_vehiculo" (
"placa" varchar(10) NOT NULL,
"ci" varchar(10) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Uniques structure for table administrador_entorno
-- ----------------------------
ALTER TABLE "administrador_entorno" ADD UNIQUE ("email");

-- ----------------------------
-- Primary Key structure for table administrador_entorno
-- ----------------------------
ALTER TABLE "administrador_entorno" ADD PRIMARY KEY ("ci");

-- ----------------------------
-- Primary Key structure for table bitacora_guardia
-- ----------------------------
ALTER TABLE "bitacora_guardia" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table entorno
-- ----------------------------
ALTER TABLE "entorno" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table guardia
-- ----------------------------
ALTER TABLE "guardia" ADD PRIMARY KEY ("ci");

-- ----------------------------
-- Primary Key structure for table ingreso_salida
-- ----------------------------
ALTER TABLE "ingreso_salida" ADD PRIMARY KEY ("fecha_hora", "placa", "id_tranca");

-- ----------------------------
-- Primary Key structure for table ingreso_salida_visita
-- ----------------------------
ALTER TABLE "ingreso_salida_visita" ADD PRIMARY KEY ("fecha_hora", "id_tranca", "placa");

-- ----------------------------
-- Primary Key structure for table mu_bitacora
-- ----------------------------
ALTER TABLE "mu_bitacora" ADD PRIMARY KEY ("fecha");

-- ----------------------------
-- Primary Key structure for table mu_menu
-- ----------------------------
ALTER TABLE "mu_menu" ADD PRIMARY KEY ("menu_id");

-- ----------------------------
-- Primary Key structure for table propietario
-- ----------------------------
ALTER TABLE "propietario" ADD PRIMARY KEY ("ci");

-- ----------------------------
-- Primary Key structure for table propietario_vehiculo
-- ----------------------------
ALTER TABLE "propietario_vehiculo" ADD PRIMARY KEY ("ci", "placa");

-- ----------------------------
-- Primary Key structure for table sincronizador
-- ----------------------------
ALTER TABLE "sincronizador" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table telefono_adminitrador
-- ----------------------------
ALTER TABLE "telefono_adminitrador" ADD PRIMARY KEY ("telefono");

-- ----------------------------
-- Primary Key structure for table telefono_propietario
-- ----------------------------
ALTER TABLE "telefono_propietario" ADD PRIMARY KEY ("telefono");

-- ----------------------------
-- Primary Key structure for table tranca
-- ----------------------------
ALTER TABLE "tranca" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table vehiculo
-- ----------------------------
ALTER TABLE "vehiculo" ADD PRIMARY KEY ("placa");

-- ----------------------------
-- Primary Key structure for table vehiculo_visita
-- ----------------------------
ALTER TABLE "vehiculo_visita" ADD PRIMARY KEY ("placa");

-- ----------------------------
-- Primary Key structure for table visita
-- ----------------------------
ALTER TABLE "visita" ADD PRIMARY KEY ("ci");

-- ----------------------------
-- Primary Key structure for table visita_vehiculo
-- ----------------------------
ALTER TABLE "visita_vehiculo" ADD PRIMARY KEY ("placa", "ci");

-- ----------------------------
-- Foreign Key structure for table "bitacora_guardia"
-- ----------------------------
ALTER TABLE "bitacora_guardia" ADD FOREIGN KEY ("ci_guardia") REFERENCES "guardia" ("ci") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "entorno"
-- ----------------------------
ALTER TABLE "entorno" ADD FOREIGN KEY ("ci") REFERENCES "administrador_entorno" ("ci") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "guardia"
-- ----------------------------
ALTER TABLE "guardia" ADD FOREIGN KEY ("id_entorno") REFERENCES "entorno" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "ingreso_salida"
-- ----------------------------
ALTER TABLE "ingreso_salida" ADD FOREIGN KEY ("id_tranca") REFERENCES "tranca" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "ingreso_salida" ADD FOREIGN KEY ("placa") REFERENCES "vehiculo" ("placa") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "ingreso_salida_visita"
-- ----------------------------
ALTER TABLE "ingreso_salida_visita" ADD FOREIGN KEY ("placa") REFERENCES "vehiculo_visita" ("placa") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "ingreso_salida_visita" ADD FOREIGN KEY ("id_tranca") REFERENCES "tranca" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "mu_menu"
-- ----------------------------
ALTER TABLE "mu_menu" ADD FOREIGN KEY ("menu_id_padre") REFERENCES "mu_menu" ("menu_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "propietario"
-- ----------------------------
ALTER TABLE "propietario" ADD FOREIGN KEY ("id_entorno") REFERENCES "entorno" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "propietario_vehiculo"
-- ----------------------------
ALTER TABLE "propietario_vehiculo" ADD FOREIGN KEY ("ci") REFERENCES "propietario" ("ci") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "propietario_vehiculo" ADD FOREIGN KEY ("placa") REFERENCES "vehiculo" ("placa") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "telefono_adminitrador"
-- ----------------------------
ALTER TABLE "telefono_adminitrador" ADD FOREIGN KEY ("ci") REFERENCES "administrador_entorno" ("ci") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "telefono_propietario"
-- ----------------------------
ALTER TABLE "telefono_propietario" ADD FOREIGN KEY ("ci") REFERENCES "propietario" ("ci") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "tranca"
-- ----------------------------
ALTER TABLE "tranca" ADD FOREIGN KEY ("id_entorno") REFERENCES "entorno" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "vehiculo"
-- ----------------------------
ALTER TABLE "vehiculo" ADD FOREIGN KEY ("id_entorno") REFERENCES "entorno" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "visita_vehiculo"
-- ----------------------------
ALTER TABLE "visita_vehiculo" ADD FOREIGN KEY ("placa") REFERENCES "vehiculo_visita" ("placa") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "visita_vehiculo" ADD FOREIGN KEY ("ci") REFERENCES "visita" ("ci") ON DELETE NO ACTION ON UPDATE NO ACTION;
