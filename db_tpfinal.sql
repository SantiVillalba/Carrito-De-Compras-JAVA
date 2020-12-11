-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-12-2020 a las 20:39:10
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_tpfinal_grupo10`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `BUSCAR_PRODUCTO_CODIGO` (IN `CCodigo_P` VARCHAR(10))  BEGIN
	SELECT P.Codigo_P, CP.Nombre_CP, MP.Nombre_MP, P.Descripcion_P, P.Precio_P,P.Stock_P, P.Estado_P FROM PRODUCTO P
	INNER JOIN clase_producto CP ON CP.Codigo_CP = P.Codigo_CP
	INNER JOIN marca_producto MP ON MP.Codigo_MP = P.Codigo_MP
	WHERE P.Codigo_P = CCodigo_P and P.Estado_P = 'HAB' ORDER BY cp.Nombre_CP ASC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ELIMINAR_CLASE_PRODUCTO` (IN `CCodigo_CP` VARCHAR(10))  begin
	update CLASE_PRODUCTO set Estado_CP='INH' where Codigo_CP=UPPER(CCodigo_CP);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ELIMINAR_DETALLE_VENTA` (IN `CCodigo_V` VARCHAR(10))  BEGIN
	DELETE FROM DETALLE_VENTA WHERE Codigo_V = CCodigo_V;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ELIMINAR_MARCA_PRODUCTO` (IN `CCodigo_MP` VARCHAR(10))  begin
	update MARCA_PRODUCTO set Estado_MP='INH' where Codigo_MP=CCodigo_MP;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ELIMINAR_PRODUCTO` (IN `CCodigo_P` VARCHAR(10))  begin
	update PRODUCTO set Estado_P='INH' where Codigo_P=CCodigo_P;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ELIMINAR_USUARIO` (IN `CCodigo_U` VARCHAR(10))  begin
	update USUARIO set Estado_U='INH' where Codigo_U=CCodigo_U;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ELIMINAR_VENTA` (IN `CCodigo_V` VARCHAR(10))  BEGIN
	DELETE FROM DETALLE_VENTA WHERE DETALLE_VENTA.Codigo_V=CCodigo_V;
    DELETE FROM VENTA WHERE VENTA.Codigo_V=CCodigo_V;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `LOGEAR_USUARIO` (IN `IId_U` VARCHAR(30))  begin
	select Codigo_U,Id_U,Clave_U,Estado_U from USUARIO
    where Id_U = IId_U;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MODIFICAR_CLASE_PRODUCTO` (IN `CCodigo_CP` VARCHAR(10), IN `NNombre_CP` VARCHAR(30))  begin
	update CLASE_PRODUCTO set Nombre_CP=UPPER(NNombre_CP) where Codigo_CP=UPPER(CCodigo_CP);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MODIFICAR_CLAVE_USUARIO` (IN `CCodigo_U` VARCHAR(10), IN `CClave_U` VARCHAR(20))  begin
	update USUARIO set Clave_U=CClave_U where Codigo_U=CCodigo_U;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MODIFICAR_EMAIL_USUARIO` (IN `CCodigo_U` VARCHAR(10), IN `EEmail_U` VARCHAR(50))  begin
	update USUARIO set Email_U=EEmail_U where Codigo_U=CCodigo_U;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MODIFICAR_MARCA_PRODUCTO` (IN `CCodigo_MP` VARCHAR(10), IN `NNombre_MP` VARCHAR(30))  begin
	update MARCA_PRODUCTO set Nombre_MP=UPPER(NNombre_MP) where Codigo_MP=CCodigo_MP;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MODIFICAR_PRODUCTO` (IN `CCodigo_P` VARCHAR(10), IN `NNombre_CP` VARCHAR(30), IN `NNombre_MP` VARCHAR(30), IN `DDescripcion_P` VARCHAR(50), IN `PPrecio_P` DOUBLE, IN `SStock_P` INT)  begin
	declare CCodigo_CP varchar(10);
    declare CCodigo_MP varchar(10);
    
    set CCodigo_CP = (SELECT Codigo_CP FROM clase_producto where Nombre_CP = NNombre_CP);
    set CCodigo_MP = (SELECT Codigo_MP FROM marca_producto where Nombre_MP = NNombre_MP);

	update PRODUCTO set Codigo_CP=UPPER(CCodigo_CP), Codigo_MP=UPPER(CCodigo_MP), Descripcion_P=UPPER(DDescripcion_P), Precio_P=UPPER(PPrecio_P), Stock_P=UPPER(SStock_P) where Codigo_P=UPPER(CCodigo_P);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MODIFICAR_USUARIO` (IN `CCodigo_U` VARCHAR(10), IN `NNombres_U` VARCHAR(50), IN `AApellidos_U` VARCHAR(50))  begin
	update USUARIO set Nombres_U=UPPER(NNombres_U), Apellidos_U=UPPER(AApellidos_U) where Codigo_U=UPPER(CCodigo_U);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MOSTRAR_CLASE_PRODUCTO_HABILITADOS` ()  BEGIN
	SELECT * FROM CLASE_PRODUCTO
    WHERE Estado_CP = 'HAB'
    ORDER BY Nombre_CP ASC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MOSTRAR_CLASE_PRODUCTO_INHABILITADOS` ()  BEGIN
	SELECT * FROM CLASE_PRODUCTO
    WHERE Estado_CP = 'INH'
    ORDER BY Nombre_CP ASC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MOSTRAR_CODIGO_CLASE_PRODUCTO` ()  BEGIN
	declare max varchar(10);
	declare num int;
	declare CCodigo varchar(10);
	
    set max = (select MAX(Codigo_CP) from CLASE_PRODUCTO);
	set num = (SELECT LTRIM(RIGHT(max,4)));
	if num>=1 and num <=8 then
		set num = num + 1;
		set CCodigo = (select concat('CP000'  ,  CAST(num as CHAR)));
	elseif num>=9 and num <=98 then
		set num = num + 1;
		set CCodigo = (select concat('CP00'  ,  CAST(num as CHAR)));
	elseif num>=99 and num <=998 then
		set num = num + 1;
		set CCodigo = (select concat('CP0'  ,  CAST(num as CHAR)));
	elseif num>=999 and num <=9998 then
		set num = num + 1;
		set CCodigo = (select concat('CP'  ,  CAST(num as CHAR)));
	else 
		set CCodigo=(select 'CP0001');
	end if;
    
    SELECT MAX(CCodigo) AS Codigo_CP FROM CLASE_PRODUCTO;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MOSTRAR_CODIGO_MARCA_PRODUCTO` ()  BEGIN
	declare max varchar(10);
	declare num int;
	declare CCodigo varchar(10);
	
    set max = (select MAX(Codigo_MP) from MARCA_PRODUCTO);
	set num = (SELECT LTRIM(RIGHT(max,4)));
	if num>=1 and num <=8 then
		set num = num + 1;
		set CCodigo = (select concat('MP000'  ,  CAST(num as CHAR)));
	elseif num>=9 and num <=98 then
		set num = num + 1;
		set CCodigo = (select concat('MP00'  ,  CAST(num as CHAR)));
	elseif num>=99 and num <=998 then
		set num = num + 1;
		set CCodigo = (select concat('MP0'  ,  CAST(num as CHAR)));
	elseif num>=999 and num <=9998 then
		set num = num + 1;
		set CCodigo = (select concat('MP'  ,  CAST(num as CHAR)));
	else 
		set CCodigo=(select 'MP0001');
	end if;
    
    SELECT MAX(CCodigo) AS Codigo_MP FROM MARCA_PRODUCTO;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MOSTRAR_CODIGO_PRODUCTO` ()  BEGIN
	declare CCodigo int(11);
	
    set CCodigo = (select MAX(Codigo_P) from PRODUCTO);
	
    SET CCodigo = CCodigo + 1;
    
    SELECT CCodigo AS Codigo_P FROM PRODUCTO;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MOSTRAR_CODIGO_VENTA` ()  BEGIN
	declare max varchar(10);
	declare num int;
	declare CCodigo varchar(10);
	
    set max = (select MAX(Codigo_V) from VENTA);
	set num = (SELECT LTRIM(RIGHT(max,4)));
	if num>=1 and num <=8 then
		set num = num + 1;
		set CCodigo = (select concat('V000'  ,  CAST(num as CHAR)));
	elseif num>=9 and num <=98 then
		set num = num + 1;
		set CCodigo = (select concat('V00'  ,  CAST(num as CHAR)));
	elseif num>=99 and num <=998 then
		set num = num + 1;
		set CCodigo = (select concat('V0'  ,  CAST(num as CHAR)));
	elseif num>=999 and num <=9998 then
		set num = num + 1;
		set CCodigo = (select concat('V'  ,  CAST(num as CHAR)));
	else 
		set CCodigo=(select 'V0001');
	end if;
    
    SELECT MAX(CCodigo) AS Codigo_V FROM VENTA;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MOSTRAR_DETALLE_VENTA_POR_CODIGO` (IN `CCodigo_V` VARCHAR(10))  BEGIN
	SELECT dv.Codigo_V, CONCAT(c.Nombre_CP,' ',m.Nombre_MP,' - ',p.Descripcion_P) as Producto, dv.Precio, dv.Cantidad, dv.SubTotal
    FROM DETALLE_VENTA dv
    INNER JOIN PRODUCTO p ON p.Codigo_P = dv.Codigo_P
    INNER JOIN CLASE_PRODUCTO c ON c.Codigo_CP = p.Codigo_CP
    INNER JOIN MARCA_PRODUCTO m ON m.Codigo_MP = p.Codigo_MP
    WHERE dv.Codigo_V = CCodigo_V;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MOSTRAR_MARCA_PRODUCTO_HABILITADOS` ()  begin
	SELECT * FROM MARCA_PRODUCTO
    where Estado_MP = 'HAB'
    ORDER BY Nombre_MP ASC;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MOSTRAR_MARCA_PRODUCTO_INHABILITADOS` ()  begin
	SELECT * FROM MARCA_PRODUCTO
    where Estado_MP = 'INH'
    ORDER BY Nombre_MP ASC;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MOSTRAR_PRODUCTOS_ELIMINADOS` ()  BEGIN
	SELECT P.Codigo_P, CP.Nombre_CP as Clase_P, MP.Nombre_MP as Marca_P, P.Descripcion_P, P.Precio_P,P.Stock_P, P.Estado_P FROM PRODUCTO P
	INNER JOIN clase_producto CP ON CP.Codigo_CP = P.Codigo_CP
	INNER JOIN marca_producto MP ON MP.Codigo_MP = P.Codigo_MP
	WHERE P.Estado_P = 'INH' ORDER BY cp.Nombre_CP ASC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MOSTRAR_PRODUCTOS_HABILITADOS` ()  BEGIN
	SELECT P.Codigo_P, CP.Nombre_CP as Clase_P, MP.Nombre_MP as Marca_P, P.Descripcion_P, P.Precio_P,P.Stock_P, P.Estado_P FROM PRODUCTO P
	INNER JOIN clase_producto CP ON CP.Codigo_CP = P.Codigo_CP
	INNER JOIN marca_producto MP ON MP.Codigo_MP = P.Codigo_MP
	WHERE P.Estado_P = 'HAB' and CP.Estado_CP = 'HAB' and MP.Estado_MP = 'HAB' ORDER BY cp.Nombre_CP ASC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MOSTRAR_USUARIOS_CLIENTES_HABILITADOS` ()  begin
	select Codigo_U,Nombres_U,Apellidos_U,Email_U,Id_U,Tipo_U,Estado_U from USUARIO
    where Tipo_U LIKE 'CLIENTE' and Estado_U = 'HAB'
    order by Nombres_U asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MOSTRAR_USUARIOS_CLIENTES_INHABILITADOS` ()  begin
	select Codigo_U,Nombres_U,Apellidos_U,Email_U,Id_U,Tipo_U,Estado_U from USUARIO
    where Tipo_U LIKE 'CLIENTE' and Estado_U = 'INH'
    order by Nombres_U asc;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MOSTRAR_USUARIO_POR_CODIGO` (IN `CCodigo_U` VARCHAR(10))  begin
	select * from USUARIO
    where Codigo_U = CCodigo_U;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `MOSTRAR_VENTA` ()  BEGIN
	SELECT Codigo_V, USUARIO.Codigo_U, concat(USUARIO.Nombres_U,', ', USUARIO.Apellidos_U) AS Cliente, Total, DATE_FORMAT(Fecha,'%d/%m/%Y a las %H:%i:%s horas') AS Fecha  FROM VENTA
    INNER JOIN USUARIO ON USUARIO.Codigo_U = VENTA.Codigo_U 
    ORDER BY Fecha desc;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `RECUPERAR_CLASE_PRODUCTO` (IN `CCodigo_CP` VARCHAR(10))  begin
	update CLASE_PRODUCTO set Estado_CP='HAB' where Codigo_CP=UPPER(CCodigo_CP);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `RECUPERAR_MARCA_PRODUCTO` (IN `CCodigo_MP` VARCHAR(10))  begin
	update MARCA_PRODUCTO set Estado_MP='HAB' where Codigo_MP=CCodigo_MP;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `RECUPERAR_PRODUCTO` (IN `CCodigo_P` VARCHAR(10))  begin
	update PRODUCTO set Estado_P='HAB' where Codigo_P=CCodigo_P;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `RECUPERAR_USUARIO` (IN `CCodigo_U` VARCHAR(10))  begin
	update USUARIO set Estado_U='HAB' where Codigo_U=CCodigo_U;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `REGISTRAR_CLASE_PRODUCTO` (IN `NNombre_CP` VARCHAR(30))  BEGIN
	declare max varchar(10);
	declare num int;
	declare CCodigo varchar(10);
	
    set max = (select MAX(Codigo_CP) from CLASE_PRODUCTO);
	set num = (SELECT LTRIM(RIGHT(max,4)));
	if num>=1 and num <=8 then
		set num = num + 1;
		set CCodigo = (select concat('CP000'  ,  CAST(num as CHAR)));
	elseif num>=9 and num <=98 then
		set num = num + 1;
		set CCodigo = (select concat('CP00'  ,  CAST(num as CHAR)));
	elseif num>=99 and num <=998 then
		set num = num + 1;
		set CCodigo = (select concat('CP0'  ,  CAST(num as CHAR)));
	elseif num>=999 and num <=9998 then
		set num = num + 1;
		set CCodigo = (select concat('CP'  ,  CAST(num as CHAR)));
	else 
		set CCodigo=(select 'CP0001');
	end if;
    
    if not exists (select Codigo_CP,Nombre_CP,Estado_CP from CLASE_PRODUCTO where Codigo_CP=CCodigo or Nombre_CP=NNombre_CP and (Estado_CP='HAB' or Estado_CP='INH')) then
		insert into CLASE_PRODUCTO(Codigo_CP,Nombre_CP) values (UPPER(CCodigo) ,UPPER(NNombre_CP));
	end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `REGISTRAR_DETALLE_VENTA` (IN `CCodigo_V` VARCHAR(10), IN `NNombre_P` VARCHAR(100), IN `PPrecio` DOUBLE, IN `CCantidad` INT, IN `SSubTotal` DOUBLE)  BEGIN
	declare CCodigo_P varchar(10);
    
    set CCodigo_P = (SELECT Codigo_P FROM PRODUCTO P
    INNER JOIN clase_producto CP ON CP.Codigo_CP = P.Codigo_CP
    INNER JOIN marca_producto MP ON MP.Codigo_MP = P.Codigo_MP
    WHERE CONCAT(CP.Nombre_CP,' ',MP.Nombre_MP,' - ',P.Descripcion_P) = NNombre_P);
    
    INSERT INTO DETALLE_VENTA(Codigo_V, Codigo_P, Precio, Cantidad, SubTotal) VALUES(UPPER(CCodigo_V),UPPER(CCodigo_P),PPrecio,CCantidad,SSubTotal);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `REGISTRAR_MARCA_PRODUCTO` (IN `NNombre_MP` VARCHAR(30))  BEGIN
	declare max varchar(10);
	declare num int;
	declare CCodigo varchar(10);
	
    set max = (select MAX(Codigo_MP) from MARCA_PRODUCTO);
	set num = (SELECT LTRIM(RIGHT(max,4)));
	if num>=1 and num <=8 then
		set num = num + 1;
		set CCodigo = (select concat('MP000'  ,  CAST(num as CHAR)));
	elseif num>=9 and num <=98 then
		set num = num + 1;
		set CCodigo = (select concat('MP00'  ,  CAST(num as CHAR)));
	elseif num>=99 and num <=998 then
		set num = num + 1;
		set CCodigo = (select concat('MP0'  ,  CAST(num as CHAR)));
	elseif num>=999 and num <=9998 then
		set num = num + 1;
		set CCodigo = (select concat('MP'  ,  CAST(num as CHAR)));
	else 
		set CCodigo=(select 'MP0001');
	end if;
    
    if not exists (select Codigo_MP,Nombre_MP,Estado_MP from MARCA_PRODUCTO where Codigo_MP=CCodigo or Nombre_MP=NNombre_MP and (Estado_MP='HAB' or Estado_MP='INH')) then
		insert into MARCA_PRODUCTO(Codigo_MP,Nombre_MP) values (UPPER(CCodigo) ,UPPER(NNombre_MP));
	end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `REGISTRAR_PRODUCTO` (IN `NNombre_CP` VARCHAR(30), IN `NNombre_MP` VARCHAR(30), IN `DDescripcion_P` VARCHAR(50), IN `PPrecio_P` DOUBLE, IN `SStock_P` INT(11))  BEGIN
    
    declare CCodigo_CP varchar(30);
    declare CCodigo_MP varchar(30);
    
    set CCodigo_CP = (SELECT Codigo_CP FROM clase_producto where Nombre_CP = NNombre_CP);
    set CCodigo_MP = (SELECT Codigo_MP FROM marca_producto where Nombre_MP = NNombre_MP);
    
    
    if not exists (select Codigo_P,Codigo_CP,Codigo_MP,Descripcion_P,Precio_P,Stock_P,Estado_P from PRODUCTO
    where Codigo_CP=CCodigo_CP and Codigo_MP = CCodigo_MP and Descripcion_P = DDescripcion_P and Precio_P=PPrecio_P and Stock_P=SStock_P and (Estado_P='HAB' or Estado_P='INH')) then
		insert into PRODUCTO(Codigo_CP, Codigo_MP, Descripcion_P, Precio_P, Stock_P) values (UPPER(CCodigo_CP), UPPER(CCodigo_MP), UPPER(DDescripcion_P), UPPER(PPrecio_P), UPPER(SStock_P));
	end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `REGISTRAR_USUARIO_CLIENTE` (IN `NNombres_U` VARCHAR(50), IN `AApellidos_U` VARCHAR(50), IN `EEmail_U` VARCHAR(50), IN `IId_U` VARCHAR(30), IN `CClave_U` VARCHAR(20))  BEGIN
	declare max varchar(10);
	declare num int;
	declare CCodigo varchar(10);
	
    set max = (select MAX(Codigo_U) from USUARIO);
	set num = (SELECT LTRIM(RIGHT(max,4)));
	if num>=1 and num <=8 then
		set num = num + 1;
		set CCodigo = (select concat('U000'  ,  CAST(num as CHAR)));
	elseif num>=9 and num <=98 then
		set num = num + 1;
		set CCodigo = (select concat('U00'  ,  CAST(num as CHAR)));
	elseif num>=99 and num <=998 then
		set num = num + 1;
		set CCodigo = (select concat('U0'  ,  CAST(num as CHAR)));
	elseif num>=999 and num <=9998 then
		set num = num + 1;
		set CCodigo = (select concat('U'  ,  CAST(num as CHAR)));
	else 
		set CCodigo=(select 'U0001');
	end if;
    
    if not exists (select Id_U,Email_U from USUARIO where Id_U = IId_U and Email_U=EEmail_U) then
		insert into USUARIO(Codigo_U,Nombres_U,Apellidos_U,Email_U,Id_U,Clave_U,Tipo_U,Estado_U) 
        values (UPPER(CCodigo),UPPER(NNombres_U),UPPER(AApellidos_U),EEmail_U,IId_U,CClave_U,'CLIENTE','HAB');
	end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `REGISTRAR_VENTA` (IN `NNombre_U` VARCHAR(100), IN `TTotal` DOUBLE)  BEGIN
	declare max varchar(10);
	declare num int;
	declare CCodigo varchar(10);
    
    declare CCodigo_U varchar(10);
	
    set max = (select MAX(Codigo_V) from VENTA);
	set num = (SELECT LTRIM(RIGHT(max,4)));
	if num>=1 and num <=8 then
		set num = num + 1;
		set CCodigo = (select concat('V000'  ,  CAST(num as CHAR)));
	elseif num>=9 and num <=98 then
		set num = num + 1;
		set CCodigo = (select concat('V00'  ,  CAST(num as CHAR)));
	elseif num>=99 and num <=998 then
		set num = num + 1;
		set CCodigo = (select concat('V0'  ,  CAST(num as CHAR)));
	elseif num>=999 and num <=9998 then
		set num = num + 1;
		set CCodigo = (select concat('V'  ,  CAST(num as CHAR)));
	else 
		set CCodigo=(select 'V0001');
	end if;
    
    set CCodigo_U = (SELECT Codigo_U FROM USUARIO WHERE concat(Nombres_U,', ',Apellidos_U)=NNombre_U);
    
    if not exists (select Codigo_V from VENTA where Codigo_V = CCodigo) then
		insert into VENTA(Codigo_V, Codigo_U, Total) values (UPPER(CCodigo), UPPER(CCodigo_U), TTotal);
	end if;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clase_producto`
--

CREATE TABLE `clase_producto` (
  `Codigo_CP` varchar(10) NOT NULL,
  `Nombre_CP` varchar(30) NOT NULL,
  `Estado_CP` char(3) DEFAULT 'HAB'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clase_producto`
--

INSERT INTO `clase_producto` (`Codigo_CP`, `Nombre_CP`, `Estado_CP`) VALUES
('CP0001', 'MONITOR', 'HAB'),
('CP0002', 'CELULAR', 'HAB'),
('CP0003', 'MOUSE', 'HAB'),
('CP0004', 'TECLADO', 'HAB'),
('CP0005', 'IMPRESORA', 'HAB'),
('CP0006', 'PENDRIVE', 'HAB'),
('CP0007', 'NOTEBOOK', 'HAB'),
('CP0008', 'TABLET', 'HAB'),
('CP0009', 'MEMORIA RAM', 'HAB'),
('CP0010', 'DISCO RIGIDO', 'HAB');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_venta`
--

CREATE TABLE `detalle_venta` (
  `Codigo_V` varchar(20) NOT NULL,
  `Codigo_P` int(10) NOT NULL,
  `Precio` double NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `SubTotal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `detalle_venta`
--

INSERT INTO `detalle_venta` (`Codigo_V`, `Codigo_P`, `Precio`, `Cantidad`, `SubTotal`) VALUES
('V0002', 6, 549, 1, 549),
('V0003', 10, 6401, 8, 51208),
('V0003', 6, 549, 3, 1647),
('V0004', 10, 6401, 1, 6401),
('V0007', 11, 2937, 10, 29370),
('V0008', 10, 6401, 1, 6401),
('V0009', 4, 2359, 1, 2359),
('V0010', 4, 2359, 1, 2359),
('V0011', 12, 19999, 1, 19999),
('V0011', 1, 38999, 1, 38999),
('V0011', 11, 2937, 1, 2937),
('V0011', 10, 6401, 1, 6401),
('V0011', 3, 22289, 1, 22289),
('V0011', 4, 2359, 1, 2359),
('V0011', 2, 683, 1, 683),
('V0011', 6, 549, 1, 549),
('V0011', 9, 9599, 1, 9599),
('V0011', 5, 2679, 1, 2679),
('V0012', 6, 549, 1, 549),
('V0013', 13, 2499, 1, 2499),
('V0014', 13, 2499, 9, 22491),
('V0015', 5, 2679, 1, 2679),
('V0016', 4, 2359, 1, 2359);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca_producto`
--

CREATE TABLE `marca_producto` (
  `Codigo_MP` varchar(10) NOT NULL,
  `Nombre_MP` varchar(30) NOT NULL,
  `Estado_MP` char(3) DEFAULT 'HAB'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `marca_producto`
--

INSERT INTO `marca_producto` (`Codigo_MP`, `Nombre_MP`, `Estado_MP`) VALUES
('MP0001', 'DELL', 'HAB'),
('MP0002', 'XIAOMI', 'HAB'),
('MP0003', 'GENIUS', 'HAB'),
('MP0004', 'LOGITECH', 'HAB'),
('MP0005', 'HP', 'HAB'),
('MP0006', 'KINGSTON', 'HAB'),
('MP0007', 'LENOVO', 'HAB'),
('MP0008', 'X-VIEW', 'HAB'),
('MP0009', 'SAMSUNG', 'HAB');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `Codigo_P` int(11) NOT NULL,
  `Codigo_CP` varchar(10) NOT NULL,
  `Codigo_MP` varchar(10) NOT NULL,
  `Descripcion_P` varchar(100) NOT NULL,
  `Precio_P` double NOT NULL,
  `Stock_P` int(11) NOT NULL,
  `Estado_P` char(3) DEFAULT 'HAB'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`Codigo_P`, `Codigo_CP`, `Codigo_MP`, `Descripcion_P`, `Precio_P`, `Stock_P`, `Estado_P`) VALUES
(1, 'CP0002', 'MP0002', 'XIAOMI REDMI 9 64 GB', 38999, 10, 'HAB'),
(2, 'CP0003', 'MP0003', 'MOUSE GENIUS DX-110', 683, 14, 'HAB'),
(3, 'CP0001', 'MP0001', 'Monitor Dell 24 E2420H', 22289, 19, 'HAB'),
(4, 'CP0003', 'MP0004', 'LOGITECH G102 PRODIGY', 2359, 22, 'HAB'),
(5, 'CP0004', 'MP0004', 'Teclado inalámbrico Logitech K400', 2679, 28, 'HAB'),
(6, 'CP0006', 'MP0006', 'PENDRIVE 32GB USB ', 549, 28, 'HAB'),
(7, 'CP0007', 'MP0007', 'Lenovo IdeaPad 3 14\"', 61999, 44, 'HAB'),
(8, 'CP0005', 'MP0005', 'Impresora Multifuncional HP', 31999, 50, 'INH'),
(9, 'CP0008', 'MP0008', 'MERCURY HELIUM GT 10', 9599, 54, 'HAB'),
(10, 'CP0009', 'MP0006', 'DDR4 8GB 3600 MHZ HYPERX', 6401, 155, 'HAB'),
(11, 'CP0010', 'MP0006', 'DISCO 120GB SSD A400 SATA3 2.5', 2937, 189, 'HAB'),
(12, 'CP0002', 'MP0009', 'GALAXY A10S', 19999, 85, 'HAB'),
(13, 'CP0009', 'MP0009', 'OEM 4GB DDR4 SODIMM', 2499, 240, 'HAB'),
(14, 'CP0001', 'MP0009', 'F390 27\" CURVO GAMER FULL HD 60HZ', 31589, 66, 'HAB'),
(15, 'CP0004', 'MP0003', 'SMART KB-102 USB', 970, 7, 'HAB');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `Codigo_U` varchar(10) NOT NULL,
  `Nombres_U` varchar(50) NOT NULL,
  `Apellidos_U` varchar(50) NOT NULL,
  `Email_U` varchar(50) NOT NULL,
  `Id_U` varchar(30) NOT NULL,
  `Clave_U` varchar(20) NOT NULL,
  `Tipo_U` varchar(15) NOT NULL,
  `Estado_U` char(3) DEFAULT 'INH'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`Codigo_U`, `Nombres_U`, `Apellidos_U`, `Email_U`, `Id_U`, `Clave_U`, `Tipo_U`, `Estado_U`) VALUES
('U0001', 'USUARIO', 'ADMINISTRADOR', 'administrador@gmail.com', 'admin', 'admin123', 'ADMINISTRADOR', 'HAB'),
('U0002', 'SANTIAGO', 'VILLALBA', 'santi@gmail.com', 'santi', 'santi123', 'CLIENTE', 'HAB'),
('U0003', 'USUARIO', 'EJEMPLO', 'usuarioejemplo@gmail.com', 'usuario', 'usu123', 'CLIENTE', 'INH');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `Codigo_V` varchar(20) NOT NULL,
  `Codigo_U` varchar(10) NOT NULL,
  `Total` double NOT NULL,
  `Fecha` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`Codigo_V`, `Codigo_U`, `Total`, `Fecha`) VALUES
('V0002', 'U0002', 549, '2020-11-29 16:01:34'),
('V0003', 'U0003', 52855, '2020-11-29 17:05:44'),
('V0004', 'U0002', 6401, '2020-11-29 23:24:26'),
('V0005', 'U0002', 590337, '2020-11-30 18:46:27'),
('V0006', 'U0002', 590337, '2020-11-30 18:51:18'),
('V0007', 'U0001', 29370, '2020-11-30 19:32:30'),
('V0008', 'U0003', 6401, '2020-11-30 20:20:13'),
('V0009', 'U0001', 2359, '2020-11-30 22:05:09'),
('V0010', 'U0001', 2359, '2020-11-30 22:12:00'),
('V0011', 'U0002', 168493, '2020-12-01 22:10:24'),
('V0012', 'U0003', 549, '2020-12-01 22:23:57'),
('V0013', 'U0001', 2499, '2020-12-02 11:32:34'),
('V0014', 'U0003', 22491, '2020-12-02 13:13:58'),
('V0015', 'U0002', 2679, '2020-12-02 14:45:31'),
('V0016', 'U0002', 2359, '2020-12-02 16:45:03');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clase_producto`
--
ALTER TABLE `clase_producto`
  ADD PRIMARY KEY (`Codigo_CP`);

--
-- Indices de la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD KEY `FK_Codigo_V` (`Codigo_V`),
  ADD KEY `FK_Codigo_P` (`Codigo_P`);

--
-- Indices de la tabla `marca_producto`
--
ALTER TABLE `marca_producto`
  ADD PRIMARY KEY (`Codigo_MP`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`Codigo_P`),
  ADD KEY `FK_Codigo_CP` (`Codigo_CP`),
  ADD KEY `FK_Codigo_MP` (`Codigo_MP`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`Codigo_U`),
  ADD UNIQUE KEY `U_Id_U` (`Id_U`),
  ADD UNIQUE KEY `U_Email_U` (`Email_U`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`Codigo_V`),
  ADD KEY `FK_Codigo_U` (`Codigo_U`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `Codigo_P` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD CONSTRAINT `FK_Codigo_P` FOREIGN KEY (`Codigo_P`) REFERENCES `producto` (`Codigo_P`),
  ADD CONSTRAINT `FK_Codigo_V` FOREIGN KEY (`Codigo_V`) REFERENCES `venta` (`Codigo_V`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `FK_Codigo_CP` FOREIGN KEY (`Codigo_CP`) REFERENCES `clase_producto` (`Codigo_CP`),
  ADD CONSTRAINT `FK_Codigo_MP` FOREIGN KEY (`Codigo_MP`) REFERENCES `marca_producto` (`Codigo_MP`);

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `FK_Codigo_U` FOREIGN KEY (`Codigo_U`) REFERENCES `usuario` (`Codigo_U`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
