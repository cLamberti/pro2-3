SET SQL_MODE = NO_AUTO_VALUE_ON_ZERO;
START TRANSACTION;
set time_zone="-06:00";
create DATABASE if not EXISTS unadb default character set utf8 collate utf8_general_ci;

use unadb;
-- CREACION TABLA AGENTES
CREATE TABLE agentes (
  id varchar(11) NOT NULL,
  nombre varchar(40) NOT NULL,
  apellidos varchar(60) NOT NULL,
  tipo_agente varchar(60) DEFAULT NULL,
  foto longblob DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- CREACION TABLA CAMIONES
CREATE TABLE camiones (
  licensePlate varchar(16) NOT NULL,
  numero_unidad varchar(16) NOT NULL,
  marca varchar(12) NOT NULL,
  color varchar(16) NOT NULL,
  modelo varchar(20) NOT NULL,
  tipo varchar(20) NOT NULL,
  PRIMARY KEY (licensePlate)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- CREACION TABLA EMPRESAS
CREATE TABLE empresas (
  companyName varchar(30) NOT NULL,
  direccion varchar(60) NOT NULL,
  contacto varchar(60) NOT NULL,
  PRIMARY KEY (companyName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- CREACION TABLA REMESAS
CREATE TABLE remesas (
  id varchar(20) NOT NULL,
  tipo varchar(20) NOT NULL,
  fecha varchar(12) NOT NULL,
  hora varchar(10) NOT NULL,
  estado varchar(10) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- CREACION TABLA USUARIOS
CREATE TABLE users (
  username varchar(20) NOT NULL,
  password varchar(12) NOT NULL,
  admin BOOLEAN NOT NULL,
  PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- CREACION RELACIONES
CREATE TABLE agentes_remesas (
  AGRM varchar(12) NOT NULL,
  idAgente varchar(11) NOT NULL,
  idRemesa varchar(20) NOT NULL,
  PRIMARY KEY (AGRM),
  FOREIGN KEY (idAgente) REFERENCES agentes(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (idRemesa) REFERENCES remesas(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;