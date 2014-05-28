CREATE TABLE mu_bitacora ( 
	fecha timestamp NOT NULL,
	usuario varchar(50),
	formulario varchar(50),
	accion varchar(200),
	direccion_ip varchar(50)
)
;

CREATE TABLE mu_rol_menu ( 
	rol_id bigint NOT NULL,
	menu_id bigint NOT NULL,
	estado boolean NOT NULL
)
;

CREATE TABLE mu_menu ( 
	menu_id bigint NOT NULL,
	menu_id_padre bigint,
	nombre varchar(50) NOT NULL,
	url varchar(50),
	estado boolean,
	depende integer,
	nivel real
)
;

CREATE SEQUENCE mu_rol_rol_id_seq INCREMENT 1 START 1
;

CREATE TABLE mu_rol ( 
	rol_id bigint DEFAULT nextval(('mu_rol_rol_id_seq'::text)::regclass) NOT NULL,
	nombre varchar(50) NOT NULL,
	descripcion bigint,
	estado boolean NOT NULL
)
;

CREATE SEQUENCE mu_usuario_usuario_id_seq INCREMENT 1 START 1
;

CREATE TABLE mu_usuario ( 
	usuario_id bigint DEFAULT nextval(('mu_usuario_usuario_id_seq'::text)::regclass) NOT NULL,
	rol_id bigint,
	username varchar(50) NOT NULL,
	nombre varchar(100) NOT NULL,
	estado boolean NOT NULL
)
;


ALTER TABLE mu_bitacora ADD CONSTRAINT PK_mu_bitacora 
	PRIMARY KEY (fecha)
;


ALTER TABLE mu_rol_menu ADD CONSTRAINT PK_mu_rol_menu 
	PRIMARY KEY (rol_id, menu_id)
;


ALTER TABLE mu_menu ADD CONSTRAINT PK_mu_menu 
	PRIMARY KEY (menu_id)
;


ALTER TABLE mu_rol ADD CONSTRAINT PK_mu_rol 
	PRIMARY KEY (rol_id)
;


ALTER TABLE mu_usuario ADD CONSTRAINT PK_mu_usuario 
	PRIMARY KEY (usuario_id)
;




ALTER TABLE mu_rol_menu ADD CONSTRAINT FK_mu_rol_menu_mu_menu 
	FOREIGN KEY (menu_id) REFERENCES mu_menu (menu_id)
;

ALTER TABLE mu_rol_menu ADD CONSTRAINT FK_mu_rol_menu_mu_rol 
	FOREIGN KEY (rol_id) REFERENCES mu_rol (rol_id)
;

ALTER TABLE mu_menu ADD CONSTRAINT FK_mu_menu_mu_menu 
	FOREIGN KEY (menu_id_padre) REFERENCES mu_menu (menu_id)
;

ALTER TABLE mu_usuario ADD CONSTRAINT FK_mu_usuario_mu_rol 
	FOREIGN KEY (rol_id) REFERENCES mu_rol (rol_id)
;
