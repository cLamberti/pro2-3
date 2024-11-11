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
                         nombre_foto varchar(30) DEFAULT NULL,
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

-- CREACION TABLA USUARIOS
CREATE TABLE users (
                       userName varchar(20) NOT NULL,
                       password varchar(12) NOT NULL,
                       admin BOOLEAN NOT NULL,
                       PRIMARY KEY (userName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- CREACION TABLA REMESAS
CREATE TABLE remesas (
                         id varchar(20) NOT NULL,
                         idAgente varchar(11) NOT NULL,
                         idCamion varchar(16) NOT NULL UNIQUE,
                         idEmpresa varchar(30) NOT NULL UNIQUE,
                         idUser varchar(20) NOT NULL UNIQUE,
                         fecha varchar(12) NOT NULL,
                         hora varchar(10) NOT NULL,
                         estado varchar(10) NOT NULL,
                         PRIMARY KEY (id),
                         FOREIGN KEY (idAgente) REFERENCES agentes(id) ON DELETE CASCADE ON UPDATE CASCADE,
                         FOREIGN KEY (idCamion) REFERENCES camiones(licensePlate) ON DELETE CASCADE ON UPDATE CASCADE,
                         FOREIGN KEY (idEmpresa) REFERENCES empresas(companyName) ON DELETE CASCADE ON UPDATE CASCADE,
                         FOREIGN KEY (idUser) REFERENCES users(userName) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

insert into users(userName, password, admin) values("admin" , "admin" , "1");
insert into users(userName, password, admin) values("Santiago" , "1234" , "0")


