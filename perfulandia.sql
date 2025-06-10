-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-06-2025 a las 05:38:22
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
-- Base de datos: `perfulandia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `id_carrito` int(11) NOT NULL,
  `activo` bit(1) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `nombre_completo` varchar(255) DEFAULT NULL,
  `rut` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `direccion`, `nombre_completo`, `rut`, `telefono`, `id_usuario`) VALUES
(1, 'Av. Los Pinos 1456, Depto 301', 'Carolina Muñoz Rivas', '18.345.789-5', '987654321', 1),
(2, 'Calle Roble 999', 'Javier Javierito Javieroski', '15.555.555-5', '987654320', 2),
(3, 'calle falsa 123', 'ana soto alvarez', '15726829-2', '987625463', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_venta`
--

CREATE TABLE `detalle_venta` (
  `id_detalle` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `precio_unitario` int(11) DEFAULT NULL,
  `id_venta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_venta`
--

INSERT INTO `detalle_venta` (`id_detalle`, `cantidad`, `id_producto`, `precio_unitario`, `id_venta`) VALUES
(1, 2, 1, 12000, 1),
(2, 1, 2, 8000, 1),
(3, 2, 1, 15990, 2),
(4, 1, 2, 282500, 2),
(5, 10, 1, 15990, 3),
(6, 2, 2, 282500, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `envio`
--

CREATE TABLE `envio` (
  `id_envio` int(11) NOT NULL,
  `direccion_destino` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_entrega` datetime(6) DEFAULT NULL,
  `fecha_envio` datetime(6) DEFAULT NULL,
  `id_venta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `envio`
--

INSERT INTO `envio` (`id_envio`, `direccion_destino`, `estado`, `fecha_entrega`, `fecha_envio`, `id_venta`) VALUES
(1, 'Calle Falsa 123', 'PENDIENTE', '2025-06-13 18:30:00.000000', '2025-06-10 10:00:00.000000', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE `inventario` (
  `id_inventario` int(11) NOT NULL,
  `activo` bit(1) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`id_inventario`, `activo`, `cantidad`, `id_producto`) VALUES
(1, b'1', 480, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_producto` int(11) NOT NULL,
  `activo` bit(1) DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio_unitario` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto`, `activo`, `categoria`, `descripcion`, `nombre`, `precio_unitario`) VALUES
(1, b'1', 'Tecnología', 'Mouse con batería recargable', 'Mouse inalámbrico', 15990),
(2, b'1', 'Premium', 'Perfume Hombre Sauvage Elixir - Notas cítricas, especiadas y amaderadas', 'Perfume Sauvage ', 282500),
(3, b'1', 'Frescos', 'Aroma actualizado con notas cítricas y florales', 'Perfume Ocean Breeze', 15990);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tickets`
--

CREATE TABLE `tickets` (
  `id_ticket` int(11) NOT NULL,
  `asunto` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tickets`
--

INSERT INTO `tickets` (`id_ticket`, `asunto`, `descripcion`, `estado`, `fecha_creacion`, `id_cliente`) VALUES
(1, 'Problema con el envío', 'Mi pedido no llegó en la fecha estimada', 'Pendiente', '2025-06-10 00:46:10.761675', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `nombre_usuario` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `contrasena`, `email`, `estado`, `nombre_usuario`, `rol`) VALUES
(1, '$2b$12$NC3WIJfUABIsfrkcE9bRO.H4zzwWuoAZhg6nKCu.beoXcl9yonvkS\r\n', 'carolina.munoz@correo.com', 'activo', 'carolina.munoz', 'cliente'),
(2, '$2a$10$TCEKhnmpibA710mpe/Ecve4ZToc00ViGWKrUywbFZuBhXniyDB6PK', 'javier@correo.cl', 'activo', 'javier', 'cliente'),
(3, '$2a$10$X.dKTaSWYQa26GYS3EzCe.wejOKrOdi2MiyIQJDEgHZBh6ntX.P2q', 'admin@correo.cl', 'activo', 'administradorJavier', 'admin'),
(4, '$2a$10$3dt9YesNZLH1eYVfxMDwQuFekgeVNbIwPDJnyNhIPqAbPn49LHvZ.', 'ana.soto@correo.com', 'activo', 'ana.soto', 'cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedores`
--

CREATE TABLE `vendedores` (
  `id_vendedor` int(11) NOT NULL,
  `area_ventas` varchar(255) DEFAULT NULL,
  `nombre_completo` varchar(255) DEFAULT NULL,
  `rut` varchar(255) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vendedores`
--

INSERT INTO `vendedores` (`id_vendedor`, `area_ventas`, `nombre_completo`, `rut`, `id_usuario`) VALUES
(1, 'Perfumes', 'Vendedor Uno', '11.111.111-1', 1),
(2, 'Aromas', 'Vendedor Dos', '22.222.222-2', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id_venta` int(11) NOT NULL,
  `fecha_hora` datetime(6) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_vendedor` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`id_venta`, `fecha_hora`, `id_cliente`, `id_vendedor`, `total`) VALUES
(1, '2025-06-08 22:39:58.315518', 3, 2, 32000),
(2, '2025-06-09 00:20:11.396852', 3, 2, 314480),
(3, '2025-06-09 00:25:04.319409', 3, 2, 724900);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`id_carrito`),
  ADD KEY `fk_carrito_cliente` (`id_cliente`),
  ADD KEY `fk_carrito_producto` (`id_producto`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`),
  ADD UNIQUE KEY `UKl8nfa1qkfrk958v1rx9h8anvb` (`id_usuario`);

--
-- Indices de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `fk_detalleventa_venta` (`id_venta`),
  ADD KEY `fk_detalleventa_producto` (`id_producto`);

--
-- Indices de la tabla `envio`
--
ALTER TABLE `envio`
  ADD PRIMARY KEY (`id_envio`),
  ADD KEY `fk_envio_venta` (`id_venta`);

--
-- Indices de la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`id_inventario`),
  ADD KEY `fk_inventario_producto` (`id_producto`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id_ticket`),
  ADD KEY `fk_tickets_cliente` (`id_cliente`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Indices de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  ADD PRIMARY KEY (`id_vendedor`),
  ADD UNIQUE KEY `UKqvr0q75ub50h6330lit89f58e` (`id_usuario`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `fk_venta_cliente` (`id_cliente`),
  ADD KEY `fk_venta_vendedor` (`id_vendedor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `id_carrito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `envio`
--
ALTER TABLE `envio`
  MODIFY `id_envio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `inventario`
--
ALTER TABLE `inventario`
  MODIFY `id_inventario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id_ticket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  MODIFY `id_vendedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD CONSTRAINT `fk_carrito_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  ADD CONSTRAINT `fk_carrito_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`);

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `FKf6u564o4dprnq7uln5gjvidp3` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `fk_cliente_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD CONSTRAINT `FKoknpg31rxsqnjxrsu7iy47p1o` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`),
  ADD CONSTRAINT `fk_detalleventa_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`),
  ADD CONSTRAINT `fk_detalleventa_venta` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`);

--
-- Filtros para la tabla `envio`
--
ALTER TABLE `envio`
  ADD CONSTRAINT `fk_envio_venta` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`);

--
-- Filtros para la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD CONSTRAINT `fk_inventario_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`);

--
-- Filtros para la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `fk_tickets_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`);

--
-- Filtros para la tabla `vendedores`
--
ALTER TABLE `vendedores`
  ADD CONSTRAINT `FKjmon615u31ld039ixwo82bkfq` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `fk_vendedor_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `fk_venta_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  ADD CONSTRAINT `fk_venta_vendedor` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedores` (`id_vendedor`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
