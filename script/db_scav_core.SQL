CREATE TABLE ingres_salida_visita ( 
	tipo integer NOT NULL,
	fecha_hora timestamp NOT NULL,
	id_tranca integer NOT NULL,
	placa varchar(10) NOT NULL
)
;

CREATE TABLE vehiculo_visita ( 
	placa varchar(10) NOT NULL,
	marca varchar(100),
	ci integer
)
;

CREATE TABLE visita ( 
	ci integer NOT NULL,
	nombres varchar(70) NOT NULL,
	apellidos varchar(200) NOT NULL
)
;

CREATE SEQUENCE advertencia_tranca_id_seq INCREMENT 1 START 1
;

CREATE TABLE advertencia_tranca ( 
	id integer DEFAULT nextval(('advertencia_tranca_id_seq'::text)::regclass) NOT NULL,
	descripcion varchar(200),
	fecha_hora timestamp,
	prioridad integer NOT NULL,
	enviado boolean NOT NULL,
	id_tranca integer
)
;

CREATE TABLE ingreso_salida ( 
	tipo integer NOT NULL,
	fecha_hora timestamp NOT NULL,
	placa varchar(10) NOT NULL,
	id_tranca integer NOT NULL
)
;

CREATE SEQUENCE tranca_id_seq INCREMENT 1 START 1
;

CREATE TABLE tranca ( 
	id integer DEFAULT nextval(('tranca_id_seq'::text)::regclass) NOT NULL,
	descripcion varchar(100)
)
;

CREATE SEQUENCE advertencia_propietario_id_seq INCREMENT 1 START 1
;

CREATE TABLE advertencia_propietario ( 
	id integer DEFAULT nextval(('advertencia_propietario_id_seq'::text)::regclass) NOT NULL,
	descripcion varchar(200),
	fecha_hora timestamp,
	prioridad integer NOT NULL,
	enviado boolean NOT NULL,
	ci integer
)
;

CREATE TABLE telefono_propietario ( 
	telefono integer NOT NULL,
	ci integer NOT NULL
)
;

CREATE TABLE propietario_vehiculo ( 
	ci integer NOT NULL,
	placa varchar(10) NOT NULL
)
;

CREATE TABLE propietario ( 
	ci integer NOT NULL,
	nombres varchar(70) NOT NULL,
	apellidos varchar(200) NOT NULL,
	foto bytea,
	nro_licencia integer NOT NULL,
	estado boolean NOT NULL
)
;

CREATE TABLE entorno_vehiculo ( 
	id_entorno integer NOT NULL,
	placa varchar(10) NOT NULL
)
;

CREATE TABLE vehiculo ( 
	placa varchar(10) NOT NULL,
	marca varchar(100),
	modelo varchar(50),
	foto bytea,
	rfid integer NOT NULL,
	estado boolean NOT NULL
)
;

CREATE SEQUENCE licencia_id_seq INCREMENT 1 START 1
;

CREATE TABLE licencia ( 
	id integer DEFAULT nextval(('licencia_id_seq'::text)::regclass) NOT NULL,
	tipo_licencia integer,
	fecha_emision timestamp,
	fecha_caducidad timestamp,
	estado boolean NOT NULL,
	id_entorno integer
)
;

CREATE TABLE telefono_adminitrador ( 
	telefono integer NOT NULL,
	ci integer NOT NULL
)
;

CREATE TABLE administrador_entorno ( 
	ci integer NOT NULL,
	nombres varchar(70) NOT NULL,
	apellidos varchar(200) NOT NULL,
	username varchar(50) NOT NULL,
	password varchar(100) NOT NULL,
	email varchar(50),
	id_entorno integer
)
;

CREATE SEQUENCE entorno_id_seq INCREMENT 1 START 1
;

CREATE TABLE entorno ( 
	id integer DEFAULT nextval(('entorno_id_seq'::text)::regclass) NOT NULL,
	nombre varchar(100) NOT NULL,
	ubicacion varchar(100)
)
;


ALTER TABLE ingres_salida_visita ADD CONSTRAINT PK_ingres_salida_visita 
	PRIMARY KEY (fecha_hora, id_tranca, placa)
;


ALTER TABLE vehiculo_visita ADD CONSTRAINT PK_vehiculo_visita 
	PRIMARY KEY (placa)
;


ALTER TABLE visita ADD CONSTRAINT PK_visita 
	PRIMARY KEY (ci)
;


ALTER TABLE advertencia_tranca ADD CONSTRAINT PK_advertencia_tranca 
	PRIMARY KEY (id)
;


ALTER TABLE ingreso_salida ADD CONSTRAINT PK_ingreso_salida 
	PRIMARY KEY (fecha_hora, placa, id_tranca)
;


ALTER TABLE tranca ADD CONSTRAINT PK_tranca 
	PRIMARY KEY (id)
;


ALTER TABLE advertencia_propietario ADD CONSTRAINT PK_advertencia_propietario 
	PRIMARY KEY (id)
;


ALTER TABLE telefono_propietario ADD CONSTRAINT PK_telefono_propietario 
	PRIMARY KEY (telefono)
;


ALTER TABLE propietario_vehiculo ADD CONSTRAINT PK_propietario_vehiculo 
	PRIMARY KEY (ci, placa)
;


ALTER TABLE propietario ADD CONSTRAINT PK_propietario 
	PRIMARY KEY (ci)
;


ALTER TABLE entorno_vehiculo ADD CONSTRAINT PK_entorno_vehiculo 
	PRIMARY KEY (id_entorno, placa)
;


ALTER TABLE vehiculo ADD CONSTRAINT PK_vehiculo 
	PRIMARY KEY (placa)
;


ALTER TABLE licencia ADD CONSTRAINT PK_licencia 
	PRIMARY KEY (id)
;


ALTER TABLE telefono_adminitrador ADD CONSTRAINT PK_telefono_adminitrador 
	PRIMARY KEY (telefono)
;


ALTER TABLE administrador_entorno ADD CONSTRAINT PK_administrador_entorno 
	PRIMARY KEY (ci)
;


ALTER TABLE entorno ADD CONSTRAINT PK_entorno 
	PRIMARY KEY (id)
;




ALTER TABLE ingres_salida_visita ADD CONSTRAINT FK_ingres_salida_visita_tranca 
	FOREIGN KEY (id_tranca) REFERENCES tranca (id)
;

ALTER TABLE ingres_salida_visita ADD CONSTRAINT FK_ingres_salida_visita_vehiculo_visita 
	FOREIGN KEY (placa) REFERENCES vehiculo_visita (placa)
;

ALTER TABLE vehiculo_visita ADD CONSTRAINT FK_vehiculo_visita_visita 
	FOREIGN KEY (ci) REFERENCES visita (ci)
;

ALTER TABLE advertencia_tranca ADD CONSTRAINT FK_advertencia_tranca 
	FOREIGN KEY (id_tranca) REFERENCES tranca (id)
;

ALTER TABLE ingreso_salida ADD CONSTRAINT FK_ingreso_salida_tranca 
	FOREIGN KEY (id_tranca) REFERENCES tranca (id)
;

ALTER TABLE ingreso_salida ADD CONSTRAINT FK_ingreso_salida_vehiculo 
	FOREIGN KEY (placa) REFERENCES vehiculo (placa)
;

ALTER TABLE advertencia_propietario ADD CONSTRAINT FK_advertencia_propietario 
	FOREIGN KEY (ci) REFERENCES propietario (ci)
;

ALTER TABLE telefono_propietario ADD CONSTRAINT FK_telefono_propietario 
	FOREIGN KEY (ci) REFERENCES propietario (ci)
;

ALTER TABLE propietario_vehiculo ADD CONSTRAINT FK_propietario_vehiculo_propietario 
	FOREIGN KEY (ci) REFERENCES propietario (ci)
;

ALTER TABLE propietario_vehiculo ADD CONSTRAINT FK_propietario_vehiculo_vehiculo 
	FOREIGN KEY (placa) REFERENCES vehiculo (placa)
;

ALTER TABLE entorno_vehiculo ADD CONSTRAINT FK_entorno_vehiculo_entorno 
	FOREIGN KEY (id_entorno) REFERENCES entorno (id)
;

ALTER TABLE entorno_vehiculo ADD CONSTRAINT FK_entorno_vehiculo_vehiculo 
	FOREIGN KEY (placa) REFERENCES vehiculo (placa)
;

ALTER TABLE licencia ADD CONSTRAINT FK_licencia_entorno 
	FOREIGN KEY (id_entorno) REFERENCES entorno (id)
;

ALTER TABLE telefono_adminitrador ADD CONSTRAINT FK_telefono_entorno 
	FOREIGN KEY (ci) REFERENCES administrador_entorno (ci)
;

ALTER TABLE administrador_entorno ADD CONSTRAINT FK_administrador_entorno 
	FOREIGN KEY (id_entorno) REFERENCES entorno (id)
;
