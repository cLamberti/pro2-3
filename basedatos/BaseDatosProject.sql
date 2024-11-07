-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-11-2024 a las 08:12:09
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `unadb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agentes`
--

CREATE TABLE `agentes` (
                           `id` varchar(11) NOT NULL,
                           `nombre` varchar(40) NOT NULL,
                           `apellidos` varchar(60) NOT NULL,
                           `tipo_agente` varchar(60) DEFAULT NULL,
                           `foto` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agentes_remesas`
--

CREATE TABLE `agentes_remesas` (
                                   `AGRM` varchar(12) NOT NULL,
                                   `idAgente` varchar(11) NOT NULL,
                                   `idRemesa` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camiones`
--

CREATE TABLE `camiones` (
                            `licensePlate` varchar(16) NOT NULL,
                            `numero_unidad` varchar(16) NOT NULL,
                            `marca` varchar(12) NOT NULL,
                            `color` varchar(16) NOT NULL,
                            `modelo` varchar(20) NOT NULL,
                            `tipo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresas`
--

CREATE TABLE `empresas` (
                            `companyName` varchar(30) NOT NULL,
                            `direccion` varchar(60) NOT NULL,
                            `contacto` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `remesas`
--

CREATE TABLE `remesas` (
                           `id` varchar(20) NOT NULL,
                           `agente_id` varchar(11) NOT NULL,
                           `camion_licensePlate` varchar(16) NOT NULL,
                           `tipo` varchar(20) NOT NULL,
                           `fecha` varchar(12) NOT NULL,
                           `hora` varchar(10) NOT NULL,
                           `estado` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
                         `username` varchar(20) NOT NULL,
                         `password` varchar(12) NOT NULL,
                         `admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `agentes`
--
ALTER TABLE `agentes`
    ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `agentes_remesas`
--
ALTER TABLE `agentes_remesas`
    ADD PRIMARY KEY (`AGRM`),
  ADD KEY `idAgente` (`idAgente`),
  ADD KEY `idRemesa` (`idRemesa`);

--
-- Indices de la tabla `camiones`
--
ALTER TABLE `camiones`
    ADD PRIMARY KEY (`licensePlate`);

--
-- Indices de la tabla `empresas`
--
ALTER TABLE `empresas`
    ADD PRIMARY KEY (`companyName`);

--
-- Indices de la tabla `remesas`
--
ALTER TABLE `remesas`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Remesas_Agentes` (`agente_id`),
  ADD KEY `FK_Remesas_Camiones` (`camion_licensePlate`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`username`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `agentes_remesas`
--
ALTER TABLE `agentes_remesas`
    ADD CONSTRAINT `agentes_remesas_ibfk_1` FOREIGN KEY (`idAgente`) REFERENCES `agentes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `agentes_remesas_ibfk_2` FOREIGN KEY (`idRemesa`) REFERENCES `remesas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `remesas`
--
ALTER TABLE `remesas`
    ADD CONSTRAINT `FK_Remesas_Agentes` FOREIGN KEY (`agente_id`) REFERENCES `agentes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Remesas_Camiones` FOREIGN KEY (`camion_licensePlate`) REFERENCES `camiones` (`licensePlate`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-11-2024 a las 08:12:09
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `unadb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agentes`
--

CREATE TABLE `agentes` (
                           `id` varchar(11) NOT NULL,
                           `nombre` varchar(40) NOT NULL,
                           `apellidos` varchar(60) NOT NULL,
                           `tipo_agente` varchar(60) DEFAULT NULL,
                           `foto` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agentes_remesas`
--

CREATE TABLE `agentes_remesas` (
                                   `AGRM` varchar(12) NOT NULL,
                                   `idAgente` varchar(11) NOT NULL,
                                   `idRemesa` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camiones`
--

CREATE TABLE `camiones` (
                            `licensePlate` varchar(16) NOT NULL,
                            `numero_unidad` varchar(16) NOT NULL,
                            `marca` varchar(12) NOT NULL,
                            `color` varchar(16) NOT NULL,
                            `modelo` varchar(20) NOT NULL,
                            `tipo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresas`
--

CREATE TABLE `empresas` (
                            `companyName` varchar(30) NOT NULL,
                            `direccion` varchar(60) NOT NULL,
                            `contacto` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `remesas`
--

CREATE TABLE `remesas` (
                           `id` varchar(20) NOT NULL,
                           `agente_id` varchar(11) NOT NULL,
                           `camion_licensePlate` varchar(16) NOT NULL,
                           `tipo` varchar(20) NOT NULL,
                           `fecha` varchar(12) NOT NULL,
                           `hora` varchar(10) NOT NULL,
                           `estado` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
                         `username` varchar(20) NOT NULL,
                         `password` varchar(12) NOT NULL,
                         `admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `agentes`
--
ALTER TABLE `agentes`
    ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `agentes_remesas`
--
ALTER TABLE `agentes_remesas`
    ADD PRIMARY KEY (`AGRM`),
  ADD KEY `idAgente` (`idAgente`),
  ADD KEY `idRemesa` (`idRemesa`);

--
-- Indices de la tabla `camiones`
--
ALTER TABLE `camiones`
    ADD PRIMARY KEY (`licensePlate`);

--
-- Indices de la tabla `empresas`
--
ALTER TABLE `empresas`
    ADD PRIMARY KEY (`companyName`);

--
-- Indices de la tabla `remesas`
--
ALTER TABLE `remesas`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Remesas_Agentes` (`agente_id`),
  ADD KEY `FK_Remesas_Camiones` (`camion_licensePlate`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`username`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `agentes_remesas`
--
ALTER TABLE `agentes_remesas`
    ADD CONSTRAINT `agentes_remesas_ibfk_1` FOREIGN KEY (`idAgente`) REFERENCES `agentes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `agentes_remesas_ibfk_2` FOREIGN KEY (`idRemesa`) REFERENCES `remesas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `remesas`
--
ALTER TABLE `remesas`
    ADD CONSTRAINT `FK_Remesas_Agentes` FOREIGN KEY (`agente_id`) REFERENCES `agentes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Remesas_Camiones` FOREIGN KEY (`camion_licensePlate`) REFERENCES `camiones` (`licensePlate`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
