CREATE DATABASE  IF NOT EXISTS `dbo_facturador` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dbo_facturador`;
-- MySQL dump 10.13  Distrib 5.5.62, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: dbo_facturador
-- ------------------------------------------------------
-- Server version	5.5.62-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `api_actividad`
--

DROP TABLE IF EXISTS `api_actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_actividad` (
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) NOT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_actividad` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_empresa` int(20) NOT NULL,
  `codigo_actividad` int(20) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  PRIMARY KEY (`id_actividad`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_actividad`
--

LOCK TABLES `api_actividad` WRITE;
/*!40000 ALTER TABLE `api_actividad` DISABLE KEYS */;
INSERT INTO `api_actividad` VALUES ('2019-08-19 18:44:29',NULL,NULL,'admin',NULL,NULL,3,1,620100,'ACTIVIDADES DE PROGRAMACIÓN INFORMÁTICA'),('2019-08-19 18:44:29',NULL,NULL,'admin',NULL,NULL,4,1,620200,'ACTIVIDADES DE CONSULTORÍA DE INFORMÁTICA Y DE GESTIÓN DE INSTALACIONES INFORMÁTICAS'),('2019-08-19 18:44:29',NULL,NULL,'admin',NULL,NULL,5,1,773400,'ALQUILER DE EQUIPO Y MOBILIARIO DE OFICINA '),('2019-08-19 18:44:29',NULL,NULL,'admin',NULL,NULL,6,1,692000,'SERVICIOS DE CONTABILIDAD, TENEDURÍA DE LIBROS Y AUDITORIA; CONSULTORÍA FISCAL');
/*!40000 ALTER TABLE `api_actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `api_cliente`
--

DROP TABLE IF EXISTS `api_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_cliente` (
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) NOT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_cliente` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_empresa` int(20) NOT NULL,
  `codigo_cliente` varchar(100) NOT NULL,
  `nombre_razon_social` varchar(200) NOT NULL,
  `nit_emisor` int(20) NOT NULL,
  `tipo_documento_identidad` int(20) NOT NULL,
  `numero_documento` varchar(20) DEFAULT NULL,
  `correo_electronico` varchar(100) DEFAULT NULL,
  `tipo_cliente` varchar(5) DEFAULT NULL,
  `complemento` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 DELAY_KEY_WRITE=1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_cliente`
--

LOCK TABLES `api_cliente` WRITE;
/*!40000 ALTER TABLE `api_cliente` DISABLE KEYS */;
INSERT INTO `api_cliente` VALUES ('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,1,1,'PMamani','Pedro Mamani',456489012,1,'1548971','pmamani@gmail.com','NAT',NULL),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,2,1,'cli001','cliente prueba',15558922,1,'3545467','xx@gmail.com','NAT',NULL);
/*!40000 ALTER TABLE `api_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `api_configuracion`
--

DROP TABLE IF EXISTS `api_configuracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_configuracion` (
  `fecha_alta` datetime DEFAULT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) DEFAULT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_configuracion` bigint(20) NOT NULL AUTO_INCREMENT,
  `cufd` varchar(100) NOT NULL,
  `fecha_vigencia` datetime DEFAULT NULL,
  `fecha_hora` datetime DEFAULT NULL,
  `estado_configuracion` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id_configuracion`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_configuracion`
--

LOCK TABLES `api_configuracion` WRITE;
/*!40000 ALTER TABLE `api_configuracion` DISABLE KEYS */;
INSERT INTO `api_configuracion` VALUES ('2019-08-16 16:36:24',NULL,NULL,'admin',NULL,NULL,6,'396A4EFD88D57B1CFAB73AAE91584C8D','2019-08-16 12:29:17','2019-08-15 12:31:43','NVGTE'),('2019-08-20 18:04:29',NULL,NULL,'admin',NULL,NULL,7,'1C65664D2BD3EE171D239013267F8878','2019-08-17 16:38:20','2019-08-16 17:32:54','NVGTE'),('2019-08-22 09:58:26',NULL,NULL,'admin',NULL,NULL,8,'4A52AA2B766A04A860661338EA04730B','2019-08-22 09:45:47',NULL,'NVGTE'),('2019-08-22 10:19:18',NULL,NULL,'admin',NULL,NULL,9,'24F585B83CA7D68D78355C467FE024DB','2019-08-22 09:56:46','2019-08-21 10:05:45','NVGTE'),('2019-08-28 11:02:04',NULL,NULL,'admin',NULL,NULL,10,'2E554F5CBC1060AC3956A4296572357E','2019-08-22 10:17:39','2019-08-28 12:15:24','NVGTE'),('2019-08-28 13:02:31',NULL,NULL,'admin',NULL,NULL,11,'6E25998E035DCD30584BAE7EE2B60FC0','2019-08-29 23:51:51','2019-08-29 01:13:16','VGTE');
/*!40000 ALTER TABLE `api_configuracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `api_configuracion_punto_venta`
--

DROP TABLE IF EXISTS `api_configuracion_punto_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_configuracion_punto_venta` (
  `id_configuracion_punto_venta` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_configuracion` bigint(20) NOT NULL,
  `id_punto_venta` bigint(20) NOT NULL,
  PRIMARY KEY (`id_configuracion_punto_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_configuracion_punto_venta`
--

LOCK TABLES `api_configuracion_punto_venta` WRITE;
/*!40000 ALTER TABLE `api_configuracion_punto_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `api_configuracion_punto_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `api_configuracion_sucursal`
--

DROP TABLE IF EXISTS `api_configuracion_sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_configuracion_sucursal` (
  `id_configuracion_sucursal` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_configuracion` bigint(20) NOT NULL,
  `id_sucursal` bigint(20) NOT NULL,
  PRIMARY KEY (`id_configuracion_sucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_configuracion_sucursal`
--

LOCK TABLES `api_configuracion_sucursal` WRITE;
/*!40000 ALTER TABLE `api_configuracion_sucursal` DISABLE KEYS */;
INSERT INTO `api_configuracion_sucursal` VALUES (4,6,10),(5,7,10),(6,8,10),(7,9,10),(8,10,10),(9,11,10);
/*!40000 ALTER TABLE `api_configuracion_sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `api_dosificacion`
--

DROP TABLE IF EXISTS `api_dosificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_dosificacion` (
  `fecha_alta` datetime DEFAULT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) DEFAULT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_dosificacion` bigint(20) NOT NULL AUTO_INCREMENT,
  `cuis` varchar(200) DEFAULT NULL,
  `tipo_modalidad` int(20) NOT NULL,
  `estado_dosificacion` varchar(5) NOT NULL,
  PRIMARY KEY (`id_dosificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_dosificacion`
--

LOCK TABLES `api_dosificacion` WRITE;
/*!40000 ALTER TABLE `api_dosificacion` DISABLE KEYS */;
INSERT INTO `api_dosificacion` VALUES ('2019-08-14 17:38:45',NULL,NULL,'admin',NULL,NULL,9,'BCF77BB2',1,'VGTE'),('2019-08-14 17:52:18',NULL,NULL,'admin',NULL,NULL,10,'BCF77BB2',1,'VGTE');
/*!40000 ALTER TABLE `api_dosificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `api_dosificacion_punto_venta`
--

DROP TABLE IF EXISTS `api_dosificacion_punto_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_dosificacion_punto_venta` (
  `id_dosificacion_punto_venta` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_dosificacion` bigint(20) NOT NULL,
  `id_punto_venta` bigint(20) NOT NULL,
  PRIMARY KEY (`id_dosificacion_punto_venta`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_dosificacion_punto_venta`
--

LOCK TABLES `api_dosificacion_punto_venta` WRITE;
/*!40000 ALTER TABLE `api_dosificacion_punto_venta` DISABLE KEYS */;
INSERT INTO `api_dosificacion_punto_venta` VALUES (9,10,2);
/*!40000 ALTER TABLE `api_dosificacion_punto_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `api_dosificacion_sucursal`
--

DROP TABLE IF EXISTS `api_dosificacion_sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_dosificacion_sucursal` (
  `id_dosificacion_sucursal` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_dosificacion` bigint(20) NOT NULL,
  `id_sucursal` bigint(20) NOT NULL,
  PRIMARY KEY (`id_dosificacion_sucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_dosificacion_sucursal`
--

LOCK TABLES `api_dosificacion_sucursal` WRITE;
/*!40000 ALTER TABLE `api_dosificacion_sucursal` DISABLE KEYS */;
INSERT INTO `api_dosificacion_sucursal` VALUES (9,9,10);
/*!40000 ALTER TABLE `api_dosificacion_sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `api_empresa_documento`
--

DROP TABLE IF EXISTS `api_empresa_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_empresa_documento` (
  `id_empresa_documento` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_empresa` int(20) NOT NULL,
  `tipo_documento_sector` int(11) NOT NULL,
  PRIMARY KEY (`id_empresa_documento`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_empresa_documento`
--

LOCK TABLES `api_empresa_documento` WRITE;
/*!40000 ALTER TABLE `api_empresa_documento` DISABLE KEYS */;
INSERT INTO `api_empresa_documento` VALUES (1,1,1),(2,1,12),(3,1,18);
/*!40000 ALTER TABLE `api_empresa_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `api_factura`
--

DROP TABLE IF EXISTS `api_factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_factura` (
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `usuario_baja` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `usuario_modificacion` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_factura` bigint(20) NOT NULL AUTO_INCREMENT,
  `nit_emisor` int(20) NOT NULL,
  `numero_factura` int(20) NOT NULL,
  `cuf` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_configuracion` bigint(20) NOT NULL,
  `fecha_emision` datetime NOT NULL,
  `nombre_razon_social` varchar(200) CHARACTER SET utf8mb4 NOT NULL,
  `id_cliente` bigint(20) NOT NULL,
  `id_dosificacion` bigint(20) NOT NULL,
  `codigo_metodo_pago` int(20) NOT NULL,
  `numero_tarjeta` bigint(20) NOT NULL,
  `monto_total` decimal(20,5) NOT NULL,
  `monto_descuento` decimal(20,5) DEFAULT NULL,
  `codigo_moneda` int(20) NOT NULL,
  `tipo_cambio` decimal(20,5) NOT NULL,
  `monto_total_moneda` decimal(20,5) NOT NULL,
  `leyenda` int(20) NOT NULL,
  `usuario` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `codigo_documento_sector` int(20) NOT NULL,
  `codigo_excepcion_documento` int(20) DEFAULT NULL,
  `tipo_emision` int(20) NOT NULL,
  `tipo_documento_fiscal` int(20) NOT NULL,
  `id_sucursal` bigint(20) DEFAULT NULL,
  `id_punto_venta` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_factura`),
  KEY `id_configuracion` (`id_configuracion`),
  KEY `id_dosificacion` (`id_dosificacion`),
  KEY `id_cliente` (`id_cliente`),
  KEY `FK_api_factura_ibfk_3` (`id_sucursal`),
  KEY `FK_api_factura_ibfk_4` (`id_punto_venta`),
  CONSTRAINT `api_factura_ibfk_1` FOREIGN KEY (`id_configuracion`) REFERENCES `api_configuracion` (`id_configuracion`),
  CONSTRAINT `api_factura_ibfk_2` FOREIGN KEY (`id_dosificacion`) REFERENCES `api_dosificacion` (`id_dosificacion`),
  CONSTRAINT `FK_api_factura_ibfk_3` FOREIGN KEY (`id_sucursal`) REFERENCES `api_sucursal` (`id_sucursal`),
  CONSTRAINT `FK_api_factura_ibfk_4` FOREIGN KEY (`id_punto_venta`) REFERENCES `api_punto_venta` (`id_punto_venta`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_factura`
--

LOCK TABLES `api_factura` WRITE;
/*!40000 ALTER TABLE `api_factura` DISABLE KEYS */;
INSERT INTO `api_factura` VALUES ('2019-08-30 06:46:19',NULL,NULL,'admin',NULL,NULL,2,456489012,100,'4FF5ACDDA3B459174729A8D178D603B1ED1CB687',11,'2019-07-26 11:00:12','Pablo Mamani',1,9,2,1158000000000005,25.00000,NULL,688,1.00000,25.00000,1,'pperez',1,NULL,1,1,10,NULL),('2019-09-02 11:12:12',NULL,NULL,'admin',NULL,NULL,3,456489012,1001,'4FF5ACDDA3B459174729A8D178D603B1F27B87A3',11,'2019-07-26 11:00:12','Pablo Mamani',1,9,2,1158000000000005,25.00000,NULL,688,1.00000,25.00000,1,'pperez',1,NULL,1,1,10,NULL),('2019-09-02 11:15:36',NULL,NULL,'admin',NULL,NULL,4,456489012,1001,'4FF5ACDDA3B459174729A8D178D603B1F27B87A3',11,'2019-07-26 11:00:12','Pablo Mamani',1,9,2,1158000000000005,25.00000,NULL,688,1.00000,25.00000,1,'pperez',1,NULL,1,1,10,NULL),('2019-09-02 13:09:37',NULL,NULL,'admin',NULL,NULL,5,456489012,1001,'4FF5ACDDA3B459174729A8D178D603B1F27B87A3',11,'2019-07-26 11:00:12','Pablo Mamani',1,9,2,1158000000000005,25.00000,NULL,688,1.00000,25.00000,1,'pperez',1,NULL,1,1,10,NULL),('2019-09-02 09:14:29',NULL,NULL,'admin',NULL,NULL,6,456489012,1002,'4FF5ACDDA3B459174729A8D178D603B1F27D0E49',11,'2019-07-26 11:00:12','Pablo Mamani',1,9,2,1158000000000005,25.00000,NULL,688,1.00000,25.00000,1,'pperez',1,NULL,1,1,10,NULL),('2019-09-02 13:15:39',NULL,NULL,'admin',NULL,NULL,7,456489012,1006,'4FF5ACDDA3B459174729A8D178D603B1F28328C0',11,'2019-07-26 11:00:12','Pablo Mamani',1,9,2,1158000000000005,25.00000,NULL,688,1.00000,25.00000,1,'pperez',1,NULL,1,1,NULL,NULL);
/*!40000 ALTER TABLE `api_factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `api_factura_detalle`
--

DROP TABLE IF EXISTS `api_factura_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_factura_detalle` (
  `id_factura_detalle` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_factura` bigint(20) NOT NULL,
  `id_item_homologado` bigint(20) NOT NULL,
  `cantidad` decimal(20,5) NOT NULL,
  `precio_unitario` decimal(20,5) NOT NULL,
  `monto_descuento` decimal(20,5) DEFAULT NULL,
  `sub_total` decimal(20,5) NOT NULL,
  PRIMARY KEY (`id_factura_detalle`),
  KEY `id_factura` (`id_factura`),
  KEY `id_item_homologado` (`id_item_homologado`),
  CONSTRAINT `api_factura_detalle_ibfk_1` FOREIGN KEY (`id_factura`) REFERENCES `api_factura` (`id_factura`),
  CONSTRAINT `api_factura_detalle_ibfk_2` FOREIGN KEY (`id_item_homologado`) REFERENCES `api_item_homologado` (`id_item_homologado`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_factura_detalle`
--

LOCK TABLES `api_factura_detalle` WRITE;
/*!40000 ALTER TABLE `api_factura_detalle` DISABLE KEYS */;
INSERT INTO `api_factura_detalle` VALUES (1,2,3,10.00000,2.50000,NULL,25.00000),(2,3,3,10.00000,2.50000,NULL,25.00000),(3,4,3,10.00000,2.50000,NULL,25.00000),(4,4,4,15.00000,8.50000,NULL,25.00000),(5,5,3,10.00000,2.50000,NULL,25.00000),(6,5,4,15.00000,8.50000,NULL,25.00000),(7,6,3,10.00000,2.50000,NULL,25.00000),(8,6,4,15.00000,8.50000,NULL,25.00000),(9,7,3,10.00000,2.50000,NULL,25.00000),(10,7,4,15.00000,8.50000,NULL,25.00000);
/*!40000 ALTER TABLE `api_factura_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `api_item`
--

DROP TABLE IF EXISTS `api_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_item` (
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) NOT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_item` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_empresa` int(20) NOT NULL,
  `codigo_producto_sin` int(20) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `codigo_actividad` int(20) NOT NULL,
  PRIMARY KEY (`id_item`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_item`
--

LOCK TABLES `api_item` WRITE;
/*!40000 ALTER TABLE `api_item` DISABLE KEYS */;
INSERT INTO `api_item` VALUES ('2019-08-19 18:52:22',NULL,NULL,'admin',NULL,NULL,2,1,83141,'Servicios de diseño y desarrollo de TI para aplicaciones',620100),('2019-08-19 18:52:22',NULL,NULL,'admin',NULL,NULL,3,1,83143,'Software originales',620100),('2019-08-19 18:52:22',NULL,NULL,'admin',NULL,NULL,4,1,83131,'Servicios de consultoría en TI',620200),('2019-08-19 18:52:22',NULL,NULL,'admin',NULL,NULL,5,1,83132,'Servicios de apoyo en TI',620200),('2019-08-19 18:52:22',NULL,NULL,'admin',NULL,NULL,7,1,83142,'Servicios de diseño y de desarrollo de TI para redes y sistemas',620200),('2019-08-19 18:52:22',NULL,NULL,'admin',NULL,NULL,9,1,82210,'Servicios de auditoría financiera',692000),('2019-08-19 18:52:22',NULL,NULL,'admin',NULL,NULL,10,1,82221,'Servicios de contabilidad',692000),('2019-08-19 18:52:22',NULL,NULL,'admin',NULL,NULL,11,1,82222,'Servicios de teneduría de libros',692000),('2019-08-19 18:52:22',NULL,NULL,'admin',NULL,NULL,12,1,82223,'Servicio de nómina',692000),('2019-08-19 18:52:22',NULL,NULL,'admin',NULL,NULL,13,1,82310,'Servicios de preparación y asesoramiento tributario empresarial',692000),('2019-08-19 18:52:22',NULL,NULL,'admin',NULL,NULL,14,1,82320,'Servicios de preparación y planificación de impuestos personales',692000),('2019-08-19 18:52:22',NULL,NULL,'admin',NULL,NULL,15,1,73123,'Servicios de arrendamiento (leasing) o alquiler de maquinaria y equipo de oficina (excepto computadoras) sin operario',773400),('2019-08-19 18:52:22',NULL,NULL,'admin',NULL,NULL,16,1,73124,'Servicios de arrendamiento (leasing) o alquiler de computadoras sin operario',773400);
/*!40000 ALTER TABLE `api_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `api_item_homologado`
--

DROP TABLE IF EXISTS `api_item_homologado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_item_homologado` (
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) NOT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_item_homologado` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_item` bigint(20) NOT NULL,
  `codigo_producto` varchar(50) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `unidad_medida` int(20) NOT NULL,
  `numero_serie` varchar(50) DEFAULT NULL,
  `numero_imei` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_item_homologado`),
  KEY `id_item` (`id_item`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_item_homologado`
--

LOCK TABLES `api_item_homologado` WRITE;
/*!40000 ALTER TABLE `api_item_homologado` DISABLE KEYS */;
INSERT INTO `api_item_homologado` VALUES ('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,3,10,'10-131231','Servicio xxx',1,NULL,NULL),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,4,12,'ML-121231','Producto xxx',3,NULL,NULL);
/*!40000 ALTER TABLE `api_item_homologado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `api_punto_venta`
--

DROP TABLE IF EXISTS `api_punto_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_punto_venta` (
  `fecha_alta` datetime DEFAULT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) DEFAULT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_punto_venta` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_sucursal` bigint(20) NOT NULL,
  `codigo_punto_venta` int(20) NOT NULL,
  `nombre_punto_venta` varchar(100) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `tipo_punto_venta` int(20) NOT NULL,
  `tiene_cuis` varchar(5) NOT NULL,
  PRIMARY KEY (`id_punto_venta`),
  KEY `id_sucursal` (`id_sucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_punto_venta`
--

LOCK TABLES `api_punto_venta` WRITE;
/*!40000 ALTER TABLE `api_punto_venta` DISABLE KEYS */;
INSERT INTO `api_punto_venta` VALUES ('2019-08-14 15:13:20',NULL,NULL,'admin',NULL,NULL,2,10,1111,'Ptov01','descripcion 1111',1,'NO'),('2019-08-14 21:33:44',NULL,NULL,'admin',NULL,NULL,3,10,3,'nuevo prueba','prueba',1,'SI'),('2019-08-28 13:03:10',NULL,NULL,'admin',NULL,NULL,4,10,18,'pruebita','sdfsagagag',2,'NO');
/*!40000 ALTER TABLE `api_punto_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `api_sincronizacion`
--

DROP TABLE IF EXISTS `api_sincronizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_sincronizacion` (
  `fecha_alta` datetime NOT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) NOT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `usuario_baja` varchar(45) DEFAULT NULL,
  `id_sincronizacion` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo` int(20) DEFAULT NULL,
  `grupo` varchar(30) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_sincronizacion`)
) ENGINE=InnoDB AUTO_INCREMENT=779 DEFAULT CHARSET=latin1 DELAY_KEY_WRITE=1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_sincronizacion`
--

LOCK TABLES `api_sincronizacion` WRITE;
/*!40000 ALTER TABLE `api_sincronizacion` DISABLE KEYS */;
INSERT INTO `api_sincronizacion` VALUES ('2019-07-22 17:27:58',NULL,NULL,'admin',NULL,NULL,1,970,'EVENTO_SIGNIFICATIVO','PROBLEMAS DE COMUNICACIÓN EXTERNA CON SERVIDOR'),('2019-07-22 17:27:58',NULL,NULL,'admin',NULL,NULL,2,971,'EVENTO_SIGNIFICATIVO','PROBLEMAS DE COMUNICACIÓN INTERNA CON PUNTOS DE VENTA O CLIENTES'),('2019-07-22 17:27:58',NULL,NULL,'admin',NULL,NULL,3,975,'EVENTO_SIGNIFICATIVO','CAMBIO DE INFRAESTRUCTURA DEL SISTEMA O FALLA DE HARDWARE'),('2019-07-22 17:27:58',NULL,NULL,'admin',NULL,NULL,4,976,'EVENTO_SIGNIFICATIVO','INACCESIBILIDAD AL SERVICIO WEB DE LA ADMINISTRACIÓN TRIBUTARIA'),('2019-07-22 17:27:58',NULL,NULL,'admin',NULL,NULL,5,977,'EVENTO_SIGNIFICATIVO','INGRESO A ZONAS SIN INTERNET POR DESPLIEGUE DE PUNTO DE VENTA'),('2019-07-22 17:27:58',NULL,NULL,'admin',NULL,NULL,6,979,'EVENTO_SIGNIFICATIVO','SINCRONIZACIÓN DE HUELLAS DE SISTEMA'),('2019-07-22 17:27:58',NULL,NULL,'admin',NULL,NULL,7,972,'EVENTO_SIGNIFICATIVO','CORTE DE SUMINISTRO DE ENERGIA ELECTRICA'),('2019-07-22 17:27:58',NULL,NULL,'admin',NULL,NULL,8,968,'EVENTO_SIGNIFICATIVO','INICIO DE OPERACIONES'),('2019-07-22 17:27:58',NULL,NULL,'admin',NULL,NULL,9,969,'EVENTO_SIGNIFICATIVO','CIERRE DE OPERACIONES'),('2019-07-22 17:27:58',NULL,NULL,'admin',NULL,NULL,10,973,'EVENTO_SIGNIFICATIVO','CORTE DEL SERVICIO DE INTERNET'),('2019-07-22 17:27:59',NULL,NULL,'admin',NULL,NULL,11,974,'EVENTO_SIGNIFICATIVO','VIRUS INFORMÁTICO O FALLA DE SOFTWARE'),('2019-07-22 17:27:59',NULL,NULL,'admin',NULL,NULL,12,978,'EVENTO_SIGNIFICATIVO','ACTUALIZACIÓN DE PARAMÉTRICAS'),('2019-07-22 17:27:59',NULL,NULL,'admin',NULL,NULL,13,3054,'EVENTO_SIGNIFICATIVO','ADICIÓN DE PUNTO DE VENTA'),('2019-07-22 17:27:59',NULL,NULL,'admin',NULL,NULL,14,3055,'EVENTO_SIGNIFICATIVO','IMPRESORA NO DISPONIBLE'),('2019-07-22 17:27:59',NULL,NULL,'admin',NULL,NULL,15,3056,'EVENTO_SIGNIFICATIVO','RENOVACIÓN DE CERTIFICADO DIGITAL'),('2019-07-22 17:27:59',NULL,NULL,'admin',NULL,NULL,16,3057,'EVENTO_SIGNIFICATIVO','CORREO ELECTRÓNICO NO DISPONIBLE'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,17,2130,'PAIS_ORIGEN','PUERTO RICO'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,18,2131,'PAIS_ORIGEN','HONG KONG'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,19,2132,'PAIS_ORIGEN','FORMOSA'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,20,2133,'PAIS_ORIGEN','NO ESPECIFICOS'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,21,360,'PAIS_ORIGEN','ALEMANIA'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,22,361,'PAIS_ORIGEN','ANDORRA'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,23,362,'PAIS_ORIGEN','ANGOLA'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,24,1276,'PAIS_ORIGEN','ANTILLAS NEERLANDESAS'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,25,364,'PAIS_ORIGEN','ARABIA SAUDITA'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,26,365,'PAIS_ORIGEN','ARGELIA'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,27,366,'PAIS_ORIGEN','ARGENTINA'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,28,367,'PAIS_ORIGEN','ARMENIA'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,29,1277,'PAIS_ORIGEN','ARUBA'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,30,368,'PAIS_ORIGEN','AUSTRALIA'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,31,369,'PAIS_ORIGEN','AUSTRIA'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,32,370,'PAIS_ORIGEN','AZERBAIYÁN'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,33,371,'PAIS_ORIGEN','BAHAMAS'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,34,372,'PAIS_ORIGEN','BAHREIN'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,35,373,'PAIS_ORIGEN','BANGLADESH'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,36,374,'PAIS_ORIGEN','BARBADOS'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,37,375,'PAIS_ORIGEN','BIELORRUSIA'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,38,387,'PAIS_ORIGEN','BÉLGICA'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,39,376,'PAIS_ORIGEN','BELICE'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,40,377,'PAIS_ORIGEN','BENIN'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,41,1278,'PAIS_ORIGEN','BERMUDAS'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,42,379,'PAIS_ORIGEN','BOLIVIA (ESTADO PLURINACIONAL DE)'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,43,380,'PAIS_ORIGEN','BOSNIA Y HERZEGOVINA'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,44,382,'PAIS_ORIGEN','BRASIL'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,45,383,'PAIS_ORIGEN','BRUNEI DARUSSALAM'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,46,384,'PAIS_ORIGEN','BULGARIA'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,47,385,'PAIS_ORIGEN','BURKINA FASO'),('2019-07-22 17:28:08',NULL,NULL,'admin',NULL,NULL,48,388,'PAIS_ORIGEN','CABO VERDE'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,49,1279,'PAIS_ORIGEN','ISLAS CAIMÁN'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,50,390,'PAIS_ORIGEN','CAMERÚN'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,51,391,'PAIS_ORIGEN','CANADÁ'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,52,393,'PAIS_ORIGEN','CHEQUIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,53,394,'PAIS_ORIGEN','CHILE'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,54,395,'PAIS_ORIGEN','CHINA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,55,396,'PAIS_ORIGEN','CHIPRE'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,56,397,'PAIS_ORIGEN','COLOMBIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,57,399,'PAIS_ORIGEN','CONGO'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,58,505,'PAIS_ORIGEN','REPÚBLICA POPULAR DEMOCRÁTICA DE COREA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,59,507,'PAIS_ORIGEN','REPÚBLICA DE COREA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,60,506,'PAIS_ORIGEN','REPÚBLICA UNIDA DE TANZANÍA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,61,400,'PAIS_ORIGEN','COSTA RICA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,62,401,'PAIS_ORIGEN','CROACIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,63,402,'PAIS_ORIGEN','CUBA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,64,404,'PAIS_ORIGEN','DINAMARCA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,65,405,'PAIS_ORIGEN','DJIBOUTI'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,66,406,'PAIS_ORIGEN','DOMINICA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,67,504,'PAIS_ORIGEN','REPÚBLICA DOMINICANA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,68,407,'PAIS_ORIGEN','ECUADOR'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,69,408,'PAIS_ORIGEN','EGIPTO'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,70,409,'PAIS_ORIGEN','EL SALVADOR'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,71,410,'PAIS_ORIGEN','EMIRATOS ÁRABES UNIDOS'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,72,411,'PAIS_ORIGEN','ERITREA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,73,412,'PAIS_ORIGEN','ESLOVAQUIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,74,413,'PAIS_ORIGEN','ESLOVENIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,75,414,'PAIS_ORIGEN','ESPAÑA'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,76,1,'MENSAJE_SERVICIO','Ambiente Inválido'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,77,415,'PAIS_ORIGEN','ESTADOS UNIDOS DE AMÉRICA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,78,416,'PAIS_ORIGEN','ESTONIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,79,417,'PAIS_ORIGEN','ETIOPÍA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,80,419,'PAIS_ORIGEN','FIYI'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,81,420,'PAIS_ORIGEN','FILIPINAS'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,82,421,'PAIS_ORIGEN','FINLANDIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,83,422,'PAIS_ORIGEN','FRANCIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,84,423,'PAIS_ORIGEN','GABÓN'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,85,424,'PAIS_ORIGEN','GAMBIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,86,425,'PAIS_ORIGEN','GEORGIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,87,426,'PAIS_ORIGEN','GHANA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,88,427,'PAIS_ORIGEN','GRANADA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,89,428,'PAIS_ORIGEN','GRECIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,90,1280,'PAIS_ORIGEN','GROENLANDIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,91,429,'PAIS_ORIGEN','GUATEMALA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,92,430,'PAIS_ORIGEN','GUINEA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,93,431,'PAIS_ORIGEN','GUINEA ECUATORIAL'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,94,432,'PAIS_ORIGEN','GUINEA-BISSAU'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,95,433,'PAIS_ORIGEN','GUYANA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,96,434,'PAIS_ORIGEN','HAITÍ'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,97,495,'PAIS_ORIGEN','PAÍSES BAJOS'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,98,435,'PAIS_ORIGEN','HONDURAS'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,99,436,'PAIS_ORIGEN','HUNGRÍA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,100,437,'PAIS_ORIGEN','INDIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,101,438,'PAIS_ORIGEN','INDONESIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,102,439,'PAIS_ORIGEN','IRAQ'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,103,2,'MENSAJE_SERVICIO','Código de Sistema Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,104,3,'MENSAJE_SERVICIO','Código Único de Inicio de Sistema (CUIS) Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,105,4,'MENSAJE_SERVICIO','Código Único de Facturación Diario (CUFD) Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,106,5,'MENSAJE_SERVICIO','Documento Fiscal Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,107,6,'MENSAJE_SERVICIO','Tipo Emisión Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,108,7,'MENSAJE_SERVICIO','Modalidad Invalida'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,109,8,'MENSAJE_SERVICIO','Sucursal Invalida'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,110,9,'MENSAJE_SERVICIO','NIT Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,111,10,'MENSAJE_SERVICIO','Archivo Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,112,11,'MENSAJE_SERVICIO','Firma Incorrrecta'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,113,12,'MENSAJE_SERVICIO','Firma No corresponde al Contribuyente'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,114,13,'MENSAJE_SERVICIO','Código de Recepción Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,115,14,'MENSAJE_SERVICIO','Numero Documento Duplicado'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,116,15,'MENSAJE_SERVICIO','Numero Documento Inexistente'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,117,16,'MENSAJE_SERVICIO','Nombre de Cajero con caracteres inválidos'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,118,17,'MENSAJE_SERVICIO','Fecha Emision mayor a definido en normativa'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,119,18,'MENSAJE_SERVICIO','Fecha Emision menor a definido en normativa'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,120,19,'MENSAJE_SERVICIO','Tipo Documento Identidad Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,121,20,'MENSAJE_SERVICIO','Código Único de Factura (CUF) Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,122,21,'MENSAJE_SERVICIO','XML Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,123,22,'MENSAJE_SERVICIO','El periodo_facturado no contiene un valor valido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,124,23,'MENSAJE_SERVICIO','El campo Estudiante es requerido para este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,125,24,'MENSAJE_SERVICIO','El Tipo Cambio es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,126,24,'MENSAJE_SERVICIO','El Tipo Cambio es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,127,25,'MENSAJE_SERVICIO','Incoterm es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,128,26,'MENSAJE_SERVICIO','Puerto destino es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,129,27,'MENSAJE_SERVICIO','Tipo moneda es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,130,28,'MENSAJE_SERVICIO','Tipo de  Moneda Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,131,29,'MENSAJE_SERVICIO','Precio valor bruto es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,132,30,'MENSAJE_SERVICIO','Gastos transporte frontera es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,133,31,'MENSAJE_SERVICIO','Gastos seguro frontera es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,134,32,'MENSAJE_SERVICIO','Total fob frontera es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,135,33,'MENSAJE_SERVICIO','Monto transporte internacional es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,136,34,'MENSAJE_SERVICIO','Monto seguro internacional es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,137,35,'MENSAJE_SERVICIO','Otros montos es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,138,36,'MENSAJE_SERVICIO','Total Moneda es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,139,37,'MENSAJE_SERVICIO','Total Puerto es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,140,38,'MENSAJE_SERVICIO','Operador Turismo receptivo es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,141,39,'MENSAJE_SERVICIO','Remitente es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,142,40,'MENSAJE_SERVICIO','Consignatario es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,143,41,'MENSAJE_SERVICIO','Placa es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,144,42,'MENSAJE_SERVICIO','Código de pais es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,145,43,'MENSAJE_SERVICIO','Código de País Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,146,44,'MENSAJE_SERVICIO','Monto Ley 317 es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,147,45,'MENSAJE_SERVICIO','Monto Total Sujeto a IVA es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,148,46,'MENSAJE_SERVICIO','Monto Total Sujeto a IPJ es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,149,47,'MENSAJE_SERVICIO','Monto Total IJ es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,150,48,'MENSAJE_SERVICIO','Monto por diferencia de tipo de cambio es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,151,49,'MENSAJE_SERVICIO','Monto ICE es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,152,50,'MENSAJE_SERVICIO','Monto IDH es requerido en este tipo de factura'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,153,51,'MENSAJE_SERVICIO','Código de Producto Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,154,52,'MENSAJE_SERVICIO','Producto no coincide con la actividad'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,155,53,'MENSAJE_SERVICIO','Actividad Invalida'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,156,54,'MENSAJE_SERVICIO','Monto total no coincide con la sumatoria de los subtotales y descuentos'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,157,55,'MENSAJE_SERVICIO','Calculo del subtotal incorrecto'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,158,56,'MENSAJE_SERVICIO','Monto Ley 317 incorrecto'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,159,57,'MENSAJE_SERVICIO','Monto Total Sujeto a IVA incorrecto'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,160,58,'MENSAJE_SERVICIO','Monto Total Sujeto IPJ incorrecto'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,161,59,'MENSAJE_SERVICIO','Monto Total IJ incorrecto'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,162,60,'MENSAJE_SERVICIO','Monto ICE incorrecto'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,163,61,'MENSAJE_SERVICIO','Monto IDH incorrecto'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,164,62,'MENSAJE_SERVICIO','Motivo de Anulación Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,165,63,'MENSAJE_SERVICIO','Error de ejecución del servicio'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,166,64,'MENSAJE_SERVICIO','Servicio de verificación de recepción incorrecto'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,167,65,'MENSAJE_SERVICIO','Error de datos'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,168,66,'MENSAJE_SERVICIO','Comunicacion exitosa'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,169,67,'MENSAJE_SERVICIO','Error al recuperar datos básicos asociados al NIT'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,170,68,'MENSAJE_SERVICIO','Error durante la validación de datos de emisor'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,171,69,'MENSAJE_SERVICIO','Error durante la recepción de Nota Fiscal'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,172,70,'MENSAJE_SERVICIO','Error durante la recepción de Nota Fiscal Paquete'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,173,71,'MENSAJE_SERVICIO','Error durante la recepción del documento fiscal'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,174,72,'MENSAJE_SERVICIO','Fecha Firma Invalida'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,175,73,'MENSAJE_SERVICIO','Firma Revocada'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,176,74,'MENSAJE_SERVICIO','Certificado Firma Invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,177,75,'MENSAJE_SERVICIO','NIT no tiene asociada la modalidad'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,178,76,'MENSAJE_SERVICIO','El NIT presenta marcas de control'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,179,77,'MENSAJE_SERVICIO','NIT Inactivo'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,180,78,'MENSAJE_SERVICIO','CUIS no esta vigente'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,181,79,'MENSAJE_SERVICIO','CUIS no corresponde a la Sucursal/punto Venta'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,182,80,'MENSAJE_SERVICIO','Codigo Documento Sector invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,183,81,'MENSAJE_SERVICIO','Codigo de Sector no corresponde al servicio'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,184,82,'MENSAJE_SERVICIO','Punto de venta invalido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,185,83,'MENSAJE_SERVICIO','Anulacion Fuera de Plazo'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,186,84,'MENSAJE_SERVICIO','Archivo no corresponde a la huella'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,187,85,'MENSAJE_SERVICIO','Feha envío inválida'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,188,86,'MENSAJE_SERVICIO','Factura Inexistente'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,189,87,'MENSAJE_SERVICIO','Factura ya esta Anulada'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,190,88,'MENSAJE_SERVICIO','Error en parámetros enviados al servicio'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,191,89,'MENSAJE_SERVICIO','El Documento Fiscal no cumple con el formato del XSD especificado'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,192,90,'MENSAJE_SERVICIO','Servicio solo habilitado para entorno de producción'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,193,91,'MENSAJE_SERVICIO','Error de ejecución del servicio en validación a detalle del Documento Fiscal'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,194,92,'MENSAJE_SERVICIO','Su catalogo ya se encuentra sincronizado'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,195,93,'MENSAJE_SERVICIO','La Fecha y Hora ya se encuentra sincronizado'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,196,94,'MENSAJE_SERVICIO','Factura no consignada correctamente'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,197,95,'MENSAJE_SERVICIO','Nit sin asociación con tipo documento sector'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,198,96,'MENSAJE_SERVICIO','Código Método de Pago Inválido'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,199,103,'MENSAJE_SERVICIO','Producto no coincide con Codigo Nandina'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,200,901,'MENSAJE_SERVICIO','Recepción Pendiente'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,201,902,'MENSAJE_SERVICIO','Recepción Rechazada'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,202,903,'MENSAJE_SERVICIO','Recepción Procesada'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,203,904,'MENSAJE_SERVICIO','Recepción Observada'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,204,905,'MENSAJE_SERVICIO','Anulación Confirmada'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,205,906,'MENSAJE_SERVICIO','Anulación Rechazada'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,206,907,'MENSAJE_SERVICIO','Anulación Pendiente de Confirmación'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,207,908,'MENSAJE_SERVICIO','Recepción Validada'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,208,909,'MENSAJE_SERVICIO','Anulada Procesado por PIN'),('2019-01-01 00:00:00',NULL,NULL,'adm',NULL,NULL,209,910,'MENSAJE_SERVICIO','Ya existe una solicitud de Anulación'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,210,441,'PAIS_ORIGEN','IRÁN (REPÚBLICA ISLÁMICA DE)'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,211,440,'PAIS_ORIGEN','IRLANDA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,212,442,'PAIS_ORIGEN','ISLANDIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,213,443,'PAIS_ORIGEN','ISLAS COOK'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,214,445,'PAIS_ORIGEN','ISLAS MARSHALL'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,215,1281,'PAIS_ORIGEN','ISLAS MALVINAS'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,216,444,'PAIS_ORIGEN','ISLAS FEROE (MIEMBRO ASOCIADO)'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,217,447,'PAIS_ORIGEN','ISRAEL'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,218,448,'PAIS_ORIGEN','ITALIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,219,449,'PAIS_ORIGEN','JAMAICA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,220,450,'PAIS_ORIGEN','JAPÓN'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,221,453,'PAIS_ORIGEN','KENIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,222,456,'PAIS_ORIGEN','KUWAIT'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,223,463,'PAIS_ORIGEN','LÍBANO'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,224,461,'PAIS_ORIGEN','LITUANIA'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,225,470,'PAIS_ORIGEN','MARRUECOS'),('2019-07-22 17:28:09',NULL,NULL,'admin',NULL,NULL,226,478,'PAIS_ORIGEN','MÉXICO'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,227,490,'PAIS_ORIGEN','PAKISTÁN'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,228,491,'PAIS_ORIGEN','PALAOS'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,229,492,'PAIS_ORIGEN','PANAMÁ'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,230,493,'PAIS_ORIGEN','PAPUA NUEVA GUINEA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,231,494,'PAIS_ORIGEN','PARAGUAY'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,232,496,'PAIS_ORIGEN','PERÚ'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,233,497,'PAIS_ORIGEN','POLONIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,234,498,'PAIS_ORIGEN','PORTUGAL'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,235,499,'PAIS_ORIGEN','QATAR'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,236,500,'PAIS_ORIGEN','REINO UNIDO'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,237,501,'PAIS_ORIGEN','REPÚBLICA CENTROAFRICANA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,238,502,'PAIS_ORIGEN','REPÚBLICA DEMOCRÁTICA POPULAR LAO'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,239,508,'PAIS_ORIGEN','REPÚBLICA DE MOLDAVIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,240,418,'PAIS_ORIGEN','FEDERACIÓN DE RUSIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,241,509,'PAIS_ORIGEN','REPÚBLICA ÁRABE SIRIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,242,510,'PAIS_ORIGEN','RUMANIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,243,511,'PAIS_ORIGEN','REPÚBLICA DE RUANDA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,244,512,'PAIS_ORIGEN','SAN CRISTÓBAL Y NIEVES'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,245,513,'PAIS_ORIGEN','SAMOA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,246,514,'PAIS_ORIGEN','SAN MARINO'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,247,515,'PAIS_ORIGEN','SAN VICENTE Y LAS GRANADINAS'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,248,516,'PAIS_ORIGEN','SANTA LUCÍA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,249,517,'PAIS_ORIGEN','SANTO TOMÉ Y PRÍNCIPE'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,250,518,'PAIS_ORIGEN','SENEGAL'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,251,520,'PAIS_ORIGEN','SEYCHELLES'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,252,521,'PAIS_ORIGEN','SIERRA LEONA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,253,519,'PAIS_ORIGEN','SERBIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,254,522,'PAIS_ORIGEN','SINGAPUR'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,255,1282,'PAIS_ORIGEN','YUGOSLAVIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,256,503,'PAIS_ORIGEN','REPÚBLICA DEMOCRÁTICA DEL CONGO'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,257,363,'PAIS_ORIGEN','ANTIGUA Y BARBUDA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,258,378,'PAIS_ORIGEN','BUTÁN'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,259,381,'PAIS_ORIGEN','BOTSUANA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,260,386,'PAIS_ORIGEN','BURUNDI'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,261,389,'PAIS_ORIGEN','CAMBOYA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,262,392,'PAIS_ORIGEN','CHAD'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,263,398,'PAIS_ORIGEN','COMORAS'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,264,403,'PAIS_ORIGEN','COSTA DE MARFIL'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,265,446,'PAIS_ORIGEN','ISLAS SALOMÓN'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,266,451,'PAIS_ORIGEN','JORDANIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,267,452,'PAIS_ORIGEN','KAZAJISTÁN'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,268,454,'PAIS_ORIGEN','KIRGUISTÁN'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,269,455,'PAIS_ORIGEN','KIRIBATI'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,270,457,'PAIS_ORIGEN','LESOTO'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,271,458,'PAIS_ORIGEN','LETONIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,272,459,'PAIS_ORIGEN','LIBERIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,273,460,'PAIS_ORIGEN','LIBIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,274,462,'PAIS_ORIGEN','LUXEMBURGO'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,275,464,'PAIS_ORIGEN','MADAGASCAR'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,276,465,'PAIS_ORIGEN','MALASIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,277,466,'PAIS_ORIGEN','MALAWI'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,278,467,'PAIS_ORIGEN','MALDIVAS'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,279,468,'PAIS_ORIGEN','MALTA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,280,469,'PAIS_ORIGEN','MALÍ'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,281,471,'PAIS_ORIGEN','MAURICIO'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,282,472,'PAIS_ORIGEN','MAURITANIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,283,473,'PAIS_ORIGEN','MICRONESIA (ESTADOS FEDERADOS DE)'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,284,474,'PAIS_ORIGEN','MONGOLIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,285,475,'PAIS_ORIGEN','MONTENEGRO'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,286,476,'PAIS_ORIGEN','MOZAMBIQUE'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,287,477,'PAIS_ORIGEN','MYANMAR'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,288,547,'PAIS_ORIGEN','VANUATU'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,289,549,'PAIS_ORIGEN','VIETNAM'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,290,550,'PAIS_ORIGEN','YEMEN'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,291,551,'PAIS_ORIGEN','ZAMBIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,292,552,'PAIS_ORIGEN','ZIMBABUE'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,293,553,'PAIS_ORIGEN','REPÚBLICA DE MACEDONIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,294,918,'PAIS_ORIGEN','NO REGISTRADO'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,295,358,'PAIS_ORIGEN','AFGANISTÁN'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,296,359,'PAIS_ORIGEN','ALBANIA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,297,525,'PAIS_ORIGEN','SUDÁFRICA'),('2019-07-22 17:28:10',NULL,NULL,'admin',NULL,NULL,298,483,'PAIS_ORIGEN','NICARAGUA'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,299,484,'PAIS_ORIGEN','NIGERIA'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,300,486,'PAIS_ORIGEN','NORUEGA'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,301,524,'PAIS_ORIGEN','SRI LANKA'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,302,528,'PAIS_ORIGEN','SUECIA'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,303,529,'PAIS_ORIGEN','SUIZA'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,304,530,'PAIS_ORIGEN','SURINAM'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,305,532,'PAIS_ORIGEN','TAILANDIA'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,306,540,'PAIS_ORIGEN','TURQUÍA'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,307,543,'PAIS_ORIGEN','UCRANIA'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,308,544,'PAIS_ORIGEN','UGANDA'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,309,545,'PAIS_ORIGEN','URUGUAY'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,310,546,'PAIS_ORIGEN','UZBEKISTÁN'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,311,548,'PAIS_ORIGEN','VENEZUELA (REPÚBLICA BOLIVARIANA DE)'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,312,479,'PAIS_ORIGEN','MÓNACO'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,313,480,'PAIS_ORIGEN','NAMIBIA'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,314,481,'PAIS_ORIGEN','NAURU'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,315,482,'PAIS_ORIGEN','NEPAL'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,316,485,'PAIS_ORIGEN','NIUE'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,317,487,'PAIS_ORIGEN','NUEVA ZELANDA'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,318,488,'PAIS_ORIGEN','NÍGER'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,319,489,'PAIS_ORIGEN','OMÁN'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,320,523,'PAIS_ORIGEN','SOMALIA'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,321,526,'PAIS_ORIGEN','SUDÁN'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,322,527,'PAIS_ORIGEN','SUDÁN DEL SUR'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,323,531,'PAIS_ORIGEN','SUAZILANDIA'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,324,533,'PAIS_ORIGEN','TAYIKISTÁN'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,325,534,'PAIS_ORIGEN','TIMOR ORIENTAL'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,326,535,'PAIS_ORIGEN','TOGO'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,327,536,'PAIS_ORIGEN','TOKELAU'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,328,537,'PAIS_ORIGEN','TONGA'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,329,538,'PAIS_ORIGEN','TRINIDAD Y TOBAGO'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,330,539,'PAIS_ORIGEN','TURKMENISTÁN'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,331,541,'PAIS_ORIGEN','TUVALU'),('2019-07-22 17:28:11',NULL,NULL,'admin',NULL,NULL,332,542,'PAIS_ORIGEN','TÚNEZ'),('2019-07-23 15:09:56',NULL,NULL,'admin',NULL,NULL,333,1402,'TIPO_COMPONENTE','REGISTRO DE DOCUMENTOS FISCALES MANUALES'),('2019-07-23 15:09:56',NULL,NULL,'admin',NULL,NULL,334,1401,'TIPO_COMPONENTE','GESTOR DE DOCUMENTOS FISCALES ELECTRÓNICOS'),('2019-07-23 15:09:56',NULL,NULL,'admin',NULL,NULL,343,630,'TIPO_COMPONENTE','EMISOR DE DOCUMENTOS FISCALES ELECTRÓNICOS'),('2019-07-23 15:09:56',NULL,NULL,'admin',NULL,NULL,344,1410,'TIPO_COMPONENTE','REGISTRO DE CÓDIGOS ESPECIALES'),('2019-07-23 15:09:56',NULL,NULL,'admin',NULL,NULL,345,1409,'TIPO_COMPONENTE','ARCHIVOS DE CONFIGURACIÓN DE ACCESO A BASE DE DATOS'),('2019-07-23 15:09:56',NULL,NULL,'admin',NULL,NULL,346,1408,'TIPO_COMPONENTE','VALIDADOR DE SALUD DEL SISTEMA'),('2019-07-23 15:09:56',NULL,NULL,'admin',NULL,NULL,347,1407,'TIPO_COMPONENTE','REGISTRO DE EVENTOS SIGNIFICATIVOS'),('2019-07-23 15:09:56',NULL,NULL,'admin',NULL,NULL,349,1406,'TIPO_COMPONENTE','VALIDADOR DE COMUNICACIONES'),('2019-07-23 15:09:56',NULL,NULL,'admin',NULL,NULL,350,1405,'TIPO_COMPONENTE','REPORTE DE HUELLAS DE COMPONENTES CRÍTICOS DEL SISTEMA'),('2019-07-23 15:09:56',NULL,NULL,'admin',NULL,NULL,351,1404,'TIPO_COMPONENTE','SINCRONIZACIÓN DE FECHA Y HORA'),('2019-07-23 15:09:56',NULL,NULL,'admin',NULL,NULL,352,1403,'TIPO_COMPONENTE','SINCRONIZACIÓN DE CATÁLOGOS'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,360,97,'MENSAJE_SERVICIO','Factura no disponible para anulación'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,361,98,'MENSAJE_SERVICIO','Numero Documento de Identidad Invalido'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,362,99,'MENSAJE_SERVICIO','Nit Emisor Invalido'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,363,100,'MENSAJE_SERVICIO','Numero de Tarjeta Invalido'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,364,101,'MENSAJE_SERVICIO','Monto Total en Moneda Invalido'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,365,102,'MENSAJE_SERVICIO','Código de Producto SIN Invalido'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,366,104,'MENSAJE_SERVICIO','Sistema no asociado al contribuyente'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,367,105,'MENSAJE_SERVICIO','Codigo de Autorizacion de Sincronizacion no valido'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,368,106,'MENSAJE_SERVICIO','Campo Descripcion obligatorio'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,369,107,'MENSAJE_SERVICIO','Codigo de Solicitud de nuevo valor de producto/servicio no valido'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,370,108,'MENSAJE_SERVICIO','Solicitud de nuevo valor producto/servicio pendiente de respuesta'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,371,109,'MENSAJE_SERVICIO','Solicitud de nuevo valor producto/servicio rechazada'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,372,110,'MENSAJE_SERVICIO','Actualizar en la sincronización siguiente habilitada'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,373,111,'MENSAJE_SERVICIO','Asociar al código de producto/servicio que se indica'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,374,112,'MENSAJE_SERVICIO','Codigo de Recepcion de Evento Significativo no Habilitado'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,375,113,'MENSAJE_SERVICIO','Formato de fecha incorrecta'),('2019-07-23 16:17:52',NULL,NULL,'admin',NULL,NULL,376,114,'MENSAJE_SERVICIO','Recepción Anulación Inexistente'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,377,115,'MENSAJE_SERVICIO','Estado de Recepción Anulación Incorrecta'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,378,116,'MENSAJE_SERVICIO','Código Único de Factura (CUF) Inexistente'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,379,117,'MENSAJE_SERVICIO','Codigo Tipo Punto Venta invalido'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,380,118,'MENSAJE_SERVICIO','Nombre Punto Venta vacio'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,381,119,'MENSAJE_SERVICIO','Descripcion Punto Venta vacio'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,382,120,'MENSAJE_SERVICIO','Codigo Evento invalido'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,383,121,'MENSAJE_SERVICIO','Descripcion Evento vacio'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,384,122,'MENSAJE_SERVICIO','Código Único de Factura (CUF) duplicado'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,385,123,'MENSAJE_SERVICIO','Código Único de Facturación Diario (CUFD) fuera de tolerancia'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,386,124,'MENSAJE_SERVICIO','CI Invalido'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,387,125,'MENSAJE_SERVICIO','Unidad de Medida Invalido'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,388,126,'MENSAJE_SERVICIO','Cantidad de documentos por paquete excedido'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,389,127,'MENSAJE_SERVICIO','Envio fuera de horario'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,390,128,'MENSAJE_SERVICIO','NIT sin asociacion de actividades. Comuniquese a siat.soporte@impuestos.gob.bo'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,391,129,'MENSAJE_SERVICIO','Registro de solicitud de proceso masivo inexistente'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,392,500,'MENSAJE_SERVICIO','Tiempo espera agotado Conexion a Base de Datos'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,393,911,'MENSAJE_SERVICIO','Hash Invalido'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,394,912,'MENSAJE_SERVICIO','Gestion incorrecta'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,395,913,'MENSAJE_SERVICIO','Fecha ingreso hospedaje incorrecto'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,396,914,'MENSAJE_SERVICIO','Documento medico incorrecto'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,397,915,'MENSAJE_SERVICIO','NIT salon incorrecto'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,398,916,'MENSAJE_SERVICIO','Fecha evento incorrecto'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,399,917,'MENSAJE_SERVICIO','Monto total puerto incorrecto'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,400,918,'MENSAJE_SERVICIO','Monto total fob frontera incorrecto'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,401,919,'MENSAJE_SERVICIO','Codigo nandina incorrecto'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,402,920,'MENSAJE_SERVICIO','Monto total devuelto incorrecto'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,403,921,'MENSAJE_SERVICIO','Monto efectivo credito debito incorrecto'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,404,922,'MENSAJE_SERVICIO','Monto original incorrecto'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,405,923,'MENSAJE_SERVICIO','Monto Iehd incorrecto'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,406,924,'MENSAJE_SERVICIO','Ingreso diferencia cambio incorrecto'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,407,925,'MENSAJE_SERVICIO','Oficina Inexistente'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,408,1000,'MENSAJE_SERVICIO','Ejecución satisfactoria en base de datos.'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,409,1001,'MENSAJE_SERVICIO','Excepción en la base de datos de recaudaciones.'),('2019-07-23 16:17:53',NULL,NULL,'admin',NULL,NULL,410,1002,'MENSAJE_SERVICIO','Excepción en la base de datos de transversales.'),('2019-07-23 16:18:05',NULL,NULL,'admin',NULL,NULL,411,1401,'TIPO_COMPONENTE','GESTOR DE DOCUMENTOS FISCALES ELECTRÓNICOS'),('2019-07-23 16:18:05',NULL,NULL,'admin',NULL,NULL,412,1402,'TIPO_COMPONENTE','REGISTRO DE DOCUMENTOS FISCALES MANUALES'),('2019-07-23 16:18:05',NULL,NULL,'admin',NULL,NULL,413,1403,'TIPO_COMPONENTE','SINCRONIZACIÓN DE CATÁLOGOS'),('2019-07-23 16:18:05',NULL,NULL,'admin',NULL,NULL,414,1404,'TIPO_COMPONENTE','SINCRONIZACIÓN DE FECHA Y HORA'),('2019-07-23 16:18:05',NULL,NULL,'admin',NULL,NULL,415,1405,'TIPO_COMPONENTE','REPORTE DE HUELLAS DE COMPONENTES CRÍTICOS DEL SISTEMA'),('2019-07-23 16:18:05',NULL,NULL,'admin',NULL,NULL,416,1406,'TIPO_COMPONENTE','VALIDADOR DE COMUNICACIONES'),('2019-07-23 16:18:05',NULL,NULL,'admin',NULL,NULL,417,1407,'TIPO_COMPONENTE','REGISTRO DE EVENTOS SIGNIFICATIVOS'),('2019-07-23 16:18:05',NULL,NULL,'admin',NULL,NULL,418,1408,'TIPO_COMPONENTE','VALIDADOR DE SALUD DEL SISTEMA'),('2019-07-23 16:18:05',NULL,NULL,'admin',NULL,NULL,419,1409,'TIPO_COMPONENTE','ARCHIVOS DE CONFIGURACIÓN DE ACCESO A BASE DE DATOS'),('2019-07-23 16:18:05',NULL,NULL,'admin',NULL,NULL,420,1410,'TIPO_COMPONENTE','REGISTRO DE CÓDIGOS ESPECIALES'),('2019-07-23 16:18:05',NULL,NULL,'admin',NULL,NULL,421,630,'TIPO_COMPONENTE','EMISOR DE DOCUMENTOS FISCALES ELECTRÓNICOS'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,422,1,'UNIDAD_MEDIDA','BOBINAS'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,423,2,'UNIDAD_MEDIDA','BALDE'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,424,3,'UNIDAD_MEDIDA','BARRILES'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,425,4,'UNIDAD_MEDIDA','BOLSA'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,426,5,'UNIDAD_MEDIDA','BOTELLAS'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,427,6,'UNIDAD_MEDIDA','CAJA'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,428,7,'UNIDAD_MEDIDA','CARTONES'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,429,8,'UNIDAD_MEDIDA','CENTIMETRO CUADRADO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,430,9,'UNIDAD_MEDIDA','CENTIMETRO CUBICO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,431,10,'UNIDAD_MEDIDA','CENTIMETRO LINEAL'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,432,11,'UNIDAD_MEDIDA','CIENTO DE UNIDADES'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,433,12,'UNIDAD_MEDIDA','CILINDRO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,434,13,'UNIDAD_MEDIDA','CONOS'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,435,14,'UNIDAD_MEDIDA','DOCENA'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,436,15,'UNIDAD_MEDIDA','FARDO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,437,16,'UNIDAD_MEDIDA','GALON INGLES'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,438,17,'UNIDAD_MEDIDA','GRAMO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,439,18,'UNIDAD_MEDIDA','GRUESA'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,440,19,'UNIDAD_MEDIDA','HECTOLITRO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,441,20,'UNIDAD_MEDIDA','HOJA'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,442,21,'UNIDAD_MEDIDA','JUEGO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,443,22,'UNIDAD_MEDIDA','KILOGRAMO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,444,23,'UNIDAD_MEDIDA','KILOMETRO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,445,24,'UNIDAD_MEDIDA','KILOVATIO HORA'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,446,25,'UNIDAD_MEDIDA','KIT'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,447,26,'UNIDAD_MEDIDA','LATAS'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,448,27,'UNIDAD_MEDIDA','LIBRAS'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,449,28,'UNIDAD_MEDIDA','LITRO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,450,29,'UNIDAD_MEDIDA','MEGAWATT HORA'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,451,30,'UNIDAD_MEDIDA','METRO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,452,31,'UNIDAD_MEDIDA','METRO CUADRADO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,453,32,'UNIDAD_MEDIDA','METRO CUBICO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,454,33,'UNIDAD_MEDIDA','MILIGRAMOS'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,455,34,'UNIDAD_MEDIDA','MILILITRO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,456,35,'UNIDAD_MEDIDA','MILIMETRO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,457,36,'UNIDAD_MEDIDA','MILIMETRO CUADRADO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,458,37,'UNIDAD_MEDIDA','MILIMETRO CUBICO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,459,38,'UNIDAD_MEDIDA','MILLARES'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,460,39,'UNIDAD_MEDIDA','MILLON DE UNIDADES'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,461,40,'UNIDAD_MEDIDA','ONZAS'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,462,41,'UNIDAD_MEDIDA','PALETAS'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,463,42,'UNIDAD_MEDIDA','PAQUETE'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,464,43,'UNIDAD_MEDIDA','PAR'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,465,44,'UNIDAD_MEDIDA','PIES'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,466,45,'UNIDAD_MEDIDA','PIES CUADRADOS'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,467,46,'UNIDAD_MEDIDA','PIES CUBICOS'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,468,47,'UNIDAD_MEDIDA','PIEZAS'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,469,48,'UNIDAD_MEDIDA','PLACAS'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,470,49,'UNIDAD_MEDIDA','PLIEGO'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,471,50,'UNIDAD_MEDIDA','PULGADAS'),('2019-07-23 16:58:11',NULL,NULL,'admin',NULL,NULL,472,51,'UNIDAD_MEDIDA','RESMA'),('2019-07-23 16:58:12',NULL,NULL,'admin',NULL,NULL,473,52,'UNIDAD_MEDIDA','TAMBOR'),('2019-07-23 16:58:12',NULL,NULL,'admin',NULL,NULL,474,53,'UNIDAD_MEDIDA','TONELADA CORTA'),('2019-07-23 16:58:12',NULL,NULL,'admin',NULL,NULL,475,54,'UNIDAD_MEDIDA','TONELADA LARGA'),('2019-07-23 16:58:12',NULL,NULL,'admin',NULL,NULL,476,55,'UNIDAD_MEDIDA','TONELADAS'),('2019-07-23 16:58:12',NULL,NULL,'admin',NULL,NULL,477,56,'UNIDAD_MEDIDA','TUBOS'),('2019-07-23 16:58:12',NULL,NULL,'admin',NULL,NULL,478,57,'UNIDAD_MEDIDA','UNIDAD (BIENES)'),('2019-07-23 16:58:12',NULL,NULL,'admin',NULL,NULL,479,58,'UNIDAD_MEDIDA','UNIDAD (SERVICIOS)'),('2019-07-23 16:58:12',NULL,NULL,'admin',NULL,NULL,480,59,'UNIDAD_MEDIDA','US GALON (3,7843 L)'),('2019-07-23 16:58:12',NULL,NULL,'admin',NULL,NULL,481,60,'UNIDAD_MEDIDA','YARDA'),('2019-07-23 16:58:12',NULL,NULL,'admin',NULL,NULL,482,61,'UNIDAD_MEDIDA','YARDA CUADRADA'),('2019-07-23 16:58:12',NULL,NULL,'admin',NULL,NULL,483,62,'UNIDAD_MEDIDA','OTRO'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,484,1,'TIPO_PUNTO_VENTA','PUNTO VENTA MOVIL'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,485,2,'TIPO_PUNTO_VENTA','PUNTO VENTA FIJO'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,486,3,'TIPO_PUNTO_VENTA','PUNTO VENTA CONSOLIDADO'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,487,4,'TIPO_PUNTO_VENTA','PUNTO VENTA PROVEEDOR'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,488,5,'TIPO_PUNTO_VENTA','PUNTO VENTA WEBSERVICE'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,489,1,'TIPO_MODALIDAD','ELECTRONICA'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,490,2,'TIPO_MODALIDAD','COMPUTARIZADA'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,491,3,'TIPO_MODALIDAD','MANUAL'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,492,4,'TIPO_MODALIDAD','PREVALORADA ELECTRONICA'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,493,5,'TIPO_MODALIDAD','PREVALORADA COMPUTARIZADA'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,494,6,'TIPO_MODALIDAD','WEB'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,495,1,'TIPO_AMBIENTE','PRODUCCION'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,496,2,'TIPO_AMBIENTE','PRUEBAS'),('2019-08-19 18:08:24',NULL,NULL,'admin',NULL,NULL,497,130,'MENSAJE_SERVICIO','Cantidad de documentos fiscales superior al límite asignado'),('2019-08-19 18:08:24',NULL,NULL,'admin',NULL,NULL,498,131,'MENSAJE_SERVICIO','Código de Sistema Proveedor Invalido'),('2019-08-19 18:08:24',NULL,NULL,'admin',NULL,NULL,499,132,'MENSAJE_SERVICIO','Registro de evento significativo inexistente'),('2019-08-19 18:26:31',NULL,NULL,'admin',NULL,NULL,500,1,'LEYENDA_FACTURA','Ley N° 453: Los medios de comunicación deben promover el respeto de los derechos de los usuarios y consumidores.'),('2019-08-19 18:26:31',NULL,NULL,'admin',NULL,NULL,501,2,'LEYENDA_FACTURA','Ley N° 453: La entidad financiera debe informar por escrito los motivos por los cuales se denegó un crédito.'),('2019-08-19 18:26:31',NULL,NULL,'admin',NULL,NULL,502,3,'LEYENDA_FACTURA','Ley N° 453: La entidad financiera debe facilitar en cualquier momento y gratuitamente, toda información de los movimientos bancarios, financieros o de crédito.'),('2019-08-19 18:26:31',NULL,NULL,'admin',NULL,NULL,503,4,'LEYENDA_FACTURA','Ley N° 453: Los medios de comunicación deben evitar contenidos inapropiados que vulneren la protección de niñas, niños y adolescentes.'),('2019-08-19 18:26:31',NULL,NULL,'admin',NULL,NULL,504,5,'LEYENDA_FACTURA','Ley N° 453: Puedes acceder a la reclamación cuando tus derechos han sido vulnerados. '),('2019-08-19 18:26:31',NULL,NULL,'admin',NULL,NULL,505,6,'LEYENDA_FACTURA','Ley N° 453: El proveedor debe brindar atención sin discriminación, con respeto, calidez y cordialidad a los usuarios y consumidores.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,506,7,'LEYENDA_FACTURA','Ley N° 453: Tienes derecho a un trato equitativo sin discriminación en la oferta de servicios.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,507,8,'LEYENDA_FACTURA','Ley N° 453: El proveedor de productos debe habilitar medios e instrumentos para efectuar consultas y reclamaciones.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,508,9,'LEYENDA_FACTURA','Ley N° 453: Está prohibido importar, distribuir o comercializar productos expirados o prontos a expirar.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,509,10,'LEYENDA_FACTURA','Ley N° 453: Los contratos de adhesión deben redactarse en términos claros, comprensibles, legibles y deben informar todas las facilidades y limitaciones.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,510,11,'LEYENDA_FACTURA','Ley N° 453: Si se te ha vulnerado algún derecho puedes exigir la reposición o restauración.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,511,12,'LEYENDA_FACTURA','Ley N° 453: Las publicaciones, mensajes e imágenes no deben promover la sumisión o explotación de las mujeres.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,512,13,'LEYENDA_FACTURA','Ley N° 453: Tienes derecho a recibir información que te proteja de la publicidad engañosa.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,513,14,'LEYENDA_FACTURA','Ley N° 453: En caso de incumplimiento a lo ofertado o convenido, el proveedor debe reparar o sustituir el servicio.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,514,15,'LEYENDA_FACTURA','Ley N° 453: El proveedor debe exhibir certificaciones de habilitación o documentos que acrediten las capacidades u ofertas de servicios especializados.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,515,16,'LEYENDA_FACTURA','Ley N° 453: Las publicaciones, mensajes e imágenes no deben deshonrar y atentar contra la dignidad e imagen de la mujer'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,516,17,'LEYENDA_FACTURA','Ley N° 453: Los alimentos declarados de primera necesidad deben ser suministrados de manera adecuada, oportuna, continua y a precio justo.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,517,18,'LEYENDA_FACTURA','Ley N° 453: Se debe promover el consumo solidario, justo, en armonía con la Madre Tierra y precautelando el hábitat, en el marco del Vivir Bien.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,518,19,'LEYENDA_FACTURA','Ley N° 453: Tienes derecho a recibir información sobre las características y contenidos de los servicios que utilices.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,519,20,'LEYENDA_FACTURA','Ley N° 453: El proveedor deberá dar cumplimiento a las condiciones ofertadas.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,520,21,'LEYENDA_FACTURA','Ley N° 453: Tienes derecho a recibir información sobre las características y contenidos de los productos que consumes.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,521,22,'LEYENDA_FACTURA','Ley N° 453: Tienes derecho a un trato equitativo sin discriminación en la oferta de productos.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,522,23,'LEYENDA_FACTURA','Ley N° 453: En caso de incumplimiento a lo ofertado o convenido, el proveedor debe reparar o sustituir el servicio..'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,523,24,'LEYENDA_FACTURA','Ley N° 453: Está prohibido importar, distribuir o comercializar productos prohibidos o retirados en el país de origen por atentar a la integridad física y a la salud.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,524,25,'LEYENDA_FACTURA','Ley N° 453: Se debe brindar alternativas de pago por servicios utilizados en emergencia médica u hospitalaria, no pudiendo retenerse al usuario por deuda.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,525,26,'LEYENDA_FACTURA','Ley N° 453: Se debe otorgar el auxilio y atención necesarios en casos de urgencia o emergencia hospitalaria, sin aducir excusa alguna.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,526,27,'LEYENDA_FACTURA','Ley N° 453: Tienes derecho a denunciar la existencia de productos y servicios que pongan en riesgo tu salud o integridad física.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,527,28,'LEYENDA_FACTURA','Ley N° 453: El proveedor de servicios debe habilitar medios e instrumentos para efectuar consultas y reclamaciones.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,528,29,'LEYENDA_FACTURA','Ley N° 453: El proveedor deberá suministrar el servicio en las modalidades y términos ofertados o convenidos.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,529,30,'LEYENDA_FACTURA','Ley N° 453: Están prohibidas las prácticas comerciales abusivas, tienes derecho a denunciarlas.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,530,31,'LEYENDA_FACTURA','Ley N° 453: El proveedor deberá entregar el producto en las modalidades y términos ofertados o convenidos.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,531,32,'LEYENDA_FACTURA','Ley N° 453: La entidad financiera tiene la obligación de promover la educación financiera.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,532,33,'LEYENDA_FACTURA','Ley N° 453: Cuando lo solicite el paciente, se debe informar los resultados de exámenes, diagnósticos y estudios de laboratorio.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,533,34,'LEYENDA_FACTURA','Ley N° 453: En caso de incumplimiento a lo ofertado o convenido, el proveedor debe reparar o sustituir el producto..'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,534,35,'LEYENDA_FACTURA','Ley N° 453: La entidad financiera debe facilitar en cualquier momento y gratuitamente, toda información de los movimientos bancarios, financieros o de crédito. '),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,535,36,'LEYENDA_FACTURA','Ley N° 453: La interrupción del servicio debe comunicarse con anterioridad a las Autoridades que correspondan y a los usuarios afectados.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,536,37,'LEYENDA_FACTURA','Ley N° 453: El prestador de servicio médico debe prescribir medicamentos debidamente autorizados por el Ministerio de Salud.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,537,38,'LEYENDA_FACTURA','Ley N° 453: El prestador de servicio médico debe brindar atención de calidad al paciente.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,538,39,'LEYENDA_FACTURA','Ley N° 453: Los medios de comunicación deben difundir mensajes o programas de educación de consumo responsable y sustentable.'),('2019-08-19 18:26:32',NULL,NULL,'admin',NULL,NULL,539,40,'LEYENDA_FACTURA','Ley N° 453: Los servicios deben suministrarse en condiciones de inocuidad, calidad y seguridad.'),('2019-08-19 18:26:56',NULL,NULL,'admin',NULL,NULL,540,912,'MOTIVO_ANULACION','FACTURA MAL EMITIDA'),('2019-08-19 18:26:56',NULL,NULL,'admin',NULL,NULL,541,913,'MOTIVO_ANULACION','NOTA FISCAL EMITIDA'),('2019-08-19 18:26:56',NULL,NULL,'admin',NULL,NULL,542,914,'MOTIVO_ANULACION','DATOS DE EMISION INCORRECTOS'),('2019-08-19 18:26:56',NULL,NULL,'admin',NULL,NULL,543,915,'MOTIVO_ANULACION','FACTURA O NOTA DEVUELTA'),('2019-08-19 18:27:44',NULL,NULL,'admin',NULL,NULL,544,328,'TIPO_DEPARTAMENTO','LA PAZ'),('2019-08-19 18:27:44',NULL,NULL,'admin',NULL,NULL,545,329,'TIPO_DEPARTAMENTO','COCHABAMBA'),('2019-08-19 18:27:44',NULL,NULL,'admin',NULL,NULL,546,331,'TIPO_DEPARTAMENTO','CHUQUISACA'),('2019-08-19 18:27:44',NULL,NULL,'admin',NULL,NULL,547,332,'TIPO_DEPARTAMENTO','TARIJA'),('2019-08-19 18:27:44',NULL,NULL,'admin',NULL,NULL,548,333,'TIPO_DEPARTAMENTO','ORURO'),('2019-08-19 18:27:44',NULL,NULL,'admin',NULL,NULL,549,334,'TIPO_DEPARTAMENTO','POTOSÍ'),('2019-08-19 18:27:44',NULL,NULL,'admin',NULL,NULL,550,335,'TIPO_DEPARTAMENTO','BENI'),('2019-08-19 18:27:44',NULL,NULL,'admin',NULL,NULL,551,336,'TIPO_DEPARTAMENTO','PANDO'),('2019-08-19 18:27:44',NULL,NULL,'admin',NULL,NULL,552,330,'TIPO_DEPARTAMENTO','SANTA CRUZ'),('2019-08-19 18:27:56',NULL,NULL,'admin',NULL,NULL,553,6,'TIPO_DOCUMENTO_FISCAL','NOTA FISCAL DE CONTINGENCIA'),('2019-08-19 18:27:56',NULL,NULL,'admin',NULL,NULL,554,5,'TIPO_DOCUMENTO_FISCAL','DOCUMENTO EQUIVALENTE'),('2019-08-19 18:27:56',NULL,NULL,'admin',NULL,NULL,555,4,'TIPO_DOCUMENTO_FISCAL','FACTURA DE CONTINGENCIA'),('2019-08-19 18:27:56',NULL,NULL,'admin',NULL,NULL,556,3,'TIPO_DOCUMENTO_FISCAL','NOTA FISCAL'),('2019-08-19 18:27:56',NULL,NULL,'admin',NULL,NULL,557,2,'TIPO_DOCUMENTO_FISCAL','NOTA DE CREDITO/DEBITO'),('2019-08-19 18:27:56',NULL,NULL,'admin',NULL,NULL,558,1,'TIPO_DOCUMENTO_FISCAL','FACTURA'),('2019-08-19 18:28:24',NULL,NULL,'admin',NULL,NULL,565,30,'TIPO_DOCUMENTO_SECTOR','NOTA DE CREDITO DEBITO SFV'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,566,29,'TIPO_DOCUMENTO_SECTOR','FACTURA TELECOMUNICACIONES'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,567,28,'TIPO_DOCUMENTO_SECTOR','FACTURA PREVALORADA DIGITAL'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,568,27,'TIPO_DOCUMENTO_SECTOR','FACTURA VENTA INTERNA MINERALES'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,569,26,'TIPO_DOCUMENTO_SECTOR','FACTURA COMERCIAL DE LIBRE CONSIGNACIÓN YPFB'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,570,25,'TIPO_DOCUMENTO_SECTOR','FACTURA COMERCIAL DE EXPORTACIÓN DE MINERALES'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,571,24,'TIPO_DOCUMENTO_SECTOR','FACTURA COMERCIAL DE EXPORTACIÓN YPFB'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,572,1,'TIPO_DOCUMENTO_SECTOR','FACTURA ESTANDAR'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,573,20,'TIPO_DOCUMENTO_SECTOR','BOLETO AEREO'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,574,18,'TIPO_DOCUMENTO_SECTOR','NOTA DE CREDITO-DEBITO'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,575,16,'TIPO_DOCUMENTO_SECTOR','NOTA FISCAL DE COMERCIALIZACIÓN DE ALIMENTOS – SEGURIDAD ALIMENTARIA Y ABASTECIMIENTO'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,576,15,'TIPO_DOCUMENTO_SECTOR','NOTA FISCAL DE ARTISTAS NACIONALES'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,577,14,'TIPO_DOCUMENTO_SECTOR','NOTA FISCAL DE ZONA FRANCA'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,578,13,'TIPO_DOCUMENTO_SECTOR','FACTURA COMERCIAL DE EXPORTACIÓN EN LIBRE CONSIGNACION'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,579,12,'TIPO_DOCUMENTO_SECTOR','FACTURA COMERCIAL DE EXPORTACION'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,580,11,'TIPO_DOCUMENTO_SECTOR','FACTURA DE ARTISTAS INTERNACIONALES'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,581,10,'TIPO_DOCUMENTO_SECTOR','FACTURA DE JUEGOS DE AZAR'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,582,9,'TIPO_DOCUMENTO_SECTOR','FACTURAS DE HOSPITALES/CLINICAS'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,583,8,'TIPO_DOCUMENTO_SECTOR','FACTURA DE HOTELES'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,584,7,'TIPO_DOCUMENTO_SECTOR','FACTURA DE ENTIDADES FINANCIERAS'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,585,6,'TIPO_DOCUMENTO_SECTOR','FACTURA DE EMBOTELLADORAS'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,586,5,'TIPO_DOCUMENTO_SECTOR','FACTURA DE SERVICIOS BASICOS'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,587,4,'TIPO_DOCUMENTO_SECTOR','FACTURA DE COMERCIALIZACION DE HIDROCARBUROS'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,588,3,'TIPO_DOCUMENTO_SECTOR','FACTURA DE ALQUILER DE BIENES INMUEBLES'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,589,2,'TIPO_DOCUMENTO_SECTOR','FACTURA SECTORES EDUCATIVOS'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,590,17,'TIPO_DOCUMENTO_SECTOR','NOTA FISCAL DE COMPRA Y VENTA DE MONEDA EXTRANJERA'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,591,23,'TIPO_DOCUMENTO_SECTOR','FACTURA HIDROCARBUROS'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,592,22,'TIPO_DOCUMENTO_SECTOR','NOTA FISCAL TASA CERO'),('2019-08-19 18:28:25',NULL,NULL,'admin',NULL,NULL,593,21,'TIPO_DOCUMENTO_SECTOR','NOTA FISCAL TURISMO RECEPTIVO'),('2019-08-19 18:28:37',NULL,NULL,'admin',NULL,NULL,594,4,'TIPO_EMISION','CONTINGENCIA'),('2019-08-19 18:28:37',NULL,NULL,'admin',NULL,NULL,595,3,'TIPO_EMISION','ON LINE MASIVO'),('2019-08-19 18:28:37',NULL,NULL,'admin',NULL,NULL,596,2,'TIPO_EMISION','OFF LINE'),('2019-08-19 18:28:37',NULL,NULL,'admin',NULL,NULL,597,1,'TIPO_EMISION','ON LINE'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,602,1894,'TIPO_MONEDA','AFGANI'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,603,1895,'TIPO_MONEDA','LEK'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,604,1896,'TIPO_MONEDA','LIBRA ESTERLINA'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,605,1897,'TIPO_MONEDA','DINAR'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,606,1898,'TIPO_MONEDA','EURO'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,607,1899,'TIPO_MONEDA','KUANZA DE ANGOLA'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,608,1900,'TIPO_MONEDA','PESO ARGENTINO'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,609,1901,'TIPO_MONEDA','DRAM ARMENIO'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,610,1902,'TIPO_MONEDA','FLORÍN ARUBANO'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,611,1903,'TIPO_MONEDA','DÓLAR AUSTRALIANO'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,612,1905,'TIPO_MONEDA','MANAT AZERBAYANO'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,613,1906,'TIPO_MONEDA','DÓLAR DE LAS BAHAMAS'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,614,1907,'TIPO_MONEDA','DINAR DE BARÉIN'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,615,1908,'TIPO_MONEDA','TAKA DE BANGLADÉS'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,616,1909,'TIPO_MONEDA','DÓLAR DE LAS BARBADOS'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,617,1910,'TIPO_MONEDA','RUBLO BIELORRUSO'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,618,1912,'TIPO_MONEDA','DÓLAR BELICEÑO'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,619,1913,'TIPO_MONEDA','GULTRUM BUTANÉS'),('2019-08-19 18:29:17',NULL,NULL,'admin',NULL,NULL,620,1914,'TIPO_MONEDA','MARCO CONVERTIBLE DE BOSNIA Y HERZEGOVINA'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,621,1915,'TIPO_MONEDA','PULA DE BOTSUANA'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,622,1916,'TIPO_MONEDA','REAL BRASILEÑO'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,623,1917,'TIPO_MONEDA','DÓLAR DE BRUNEI'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,624,1918,'TIPO_MONEDA','LEVA BÚLGARO'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,625,1919,'TIPO_MONEDA','FRANCO CFA'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,626,1920,'TIPO_MONEDA','KIAT DE BIRMANIA'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,627,1921,'TIPO_MONEDA','FRANCO BURUNDÉS'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,628,1922,'TIPO_MONEDA','RIEL CAMBOYANO'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,629,1923,'TIPO_MONEDA','DÓLAR CANADIENSE'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,630,1924,'TIPO_MONEDA','ESCUDO DE CABO VERDE'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,631,1925,'TIPO_MONEDA','DÓLAR DE LAS ISLAS CAIMÁN'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,632,1928,'TIPO_MONEDA','PESO CHILENO'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,633,1929,'TIPO_MONEDA','YUAN, RENMINBI'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,634,1930,'TIPO_MONEDA','PESO COLOMBIANO'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,635,1931,'TIPO_MONEDA','FRANCO COMORANO'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,636,1932,'TIPO_MONEDA','FRANCO CONGOLEÑO'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,637,1934,'TIPO_MONEDA','COLÓN COSTARRICENSE'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,638,1936,'TIPO_MONEDA','KUNA CROATA'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,639,1937,'TIPO_MONEDA','PESO CUBANO'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,640,1938,'TIPO_MONEDA','FLORÍN DE LAS ANTILLAS NEERLANDESAS'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,641,1940,'TIPO_MONEDA','CORONA CHECA'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,642,1941,'TIPO_MONEDA','CORONA DANESA'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,643,1942,'TIPO_MONEDA','FRANCO YIBUTIANO'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,644,1943,'TIPO_MONEDA','PESO DOMINICANO'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,645,1944,'TIPO_MONEDA','DÓLAR ESTADOUNIDENSE'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,646,1945,'TIPO_MONEDA','LIBRA EGIPCIA'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,647,1946,'TIPO_MONEDA','COLÓN SALVADOREÑO'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,648,1948,'TIPO_MONEDA','NAKFA DE ERITREA'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,649,1950,'TIPO_MONEDA','BIR'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,650,1953,'TIPO_MONEDA','FRANCO CFP'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,651,1955,'TIPO_MONEDA','DALASI GAMBIANO'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,652,1956,'TIPO_MONEDA','LARI GEORGIANO'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,653,1958,'TIPO_MONEDA','CEDI DE GANA'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,654,1959,'TIPO_MONEDA','LIBRA DE GIBRALTAR'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,655,1961,'TIPO_MONEDA','QUETZAL'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,656,1962,'TIPO_MONEDA','FRANCO GUINEANO'),('2019-08-19 18:29:18',NULL,NULL,'admin',NULL,NULL,657,1964,'TIPO_MONEDA','GURDE'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,658,1965,'TIPO_MONEDA','LEMPIRA'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,659,1966,'TIPO_MONEDA','FORINTO'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,660,1967,'TIPO_MONEDA','CORONA ISLANDESA'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,661,1968,'TIPO_MONEDA','RUPIA INDIA'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,662,1969,'TIPO_MONEDA','RUPIA INDONESIA'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,663,1970,'TIPO_MONEDA','RIAL IRANÍ'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,664,1971,'TIPO_MONEDA','DINAR IRAKÍ'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,665,1973,'TIPO_MONEDA','NUEVO SÉQUEL'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,666,1975,'TIPO_MONEDA','DÓLAR JAMAICANO'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,667,1976,'TIPO_MONEDA','YEN'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,668,1977,'TIPO_MONEDA','DINAR JORDANO'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,669,1978,'TIPO_MONEDA','TENGUE KAZAJO'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,670,1979,'TIPO_MONEDA','CHELÍN KENIANO'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,671,1980,'TIPO_MONEDA','WON NORCOREANO'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,672,1981,'TIPO_MONEDA','WON SURCOREANO'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,673,1982,'TIPO_MONEDA','DINAR KUWAITÍ'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,674,1983,'TIPO_MONEDA','SOM KIRGUÍS'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,675,1984,'TIPO_MONEDA','KIP'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,676,1985,'TIPO_MONEDA','LATS'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,677,1986,'TIPO_MONEDA','LIBRA LIBANESA'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,678,1987,'TIPO_MONEDA','LOTI DE LESOTO'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,679,1988,'TIPO_MONEDA','DÓLAR LIBERIANO'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,680,1989,'TIPO_MONEDA','DINAR LIBIO'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,681,1990,'TIPO_MONEDA','LITAS'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,682,1992,'TIPO_MONEDA','PATACA DE MACAO'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,683,1993,'TIPO_MONEDA','DENAR'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,684,1994,'TIPO_MONEDA','ARIARI'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,685,1995,'TIPO_MONEDA','KUACHA DE MALAUI'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,686,1996,'TIPO_MONEDA','RINGIT'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,687,1997,'TIPO_MONEDA','RUFIYA'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,688,2000,'TIPO_MONEDA','UGUIYA'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,689,2001,'TIPO_MONEDA','RUPIA DE MAURICIO'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,690,2002,'TIPO_MONEDA','PESO MEXICANO'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,691,2003,'TIPO_MONEDA','DÓLAR MICRONESIO'),('2019-08-19 18:29:19',NULL,NULL,'admin',NULL,NULL,692,2004,'TIPO_MONEDA','LEU MOLDAVO'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,693,2006,'TIPO_MONEDA','TUGRIK MONGOL'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,694,2008,'TIPO_MONEDA','DÍRHAM'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,695,2009,'TIPO_MONEDA','METICAL'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,696,2010,'TIPO_MONEDA','DÓLAR DE NAMIBIA'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,697,2011,'TIPO_MONEDA','DÓLAR NAURUANO'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,698,2012,'TIPO_MONEDA','RUPIA NEPALÍ'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,699,2015,'TIPO_MONEDA','DÓLAR DE NUEVA ZELANDA'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,700,2016,'TIPO_MONEDA','CÓRDOBA NICARAGÜENSE'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,701,2018,'TIPO_MONEDA','NAIRA NIGERIANO'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,702,2019,'TIPO_MONEDA','CORONA NORUEGA'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,703,2020,'TIPO_MONEDA','RIAL DE OMÁN'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,704,2021,'TIPO_MONEDA','RUPIA PAKISTANÍ'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,705,2022,'TIPO_MONEDA','BALBOA PANAMEÑO'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,706,2023,'TIPO_MONEDA','KINA'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,707,2024,'TIPO_MONEDA','GUARANÍ PARAGUAYO'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,708,2025,'TIPO_MONEDA','NUEVO SOL PERUANO'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,709,2026,'TIPO_MONEDA','PESO FILIPINO'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,710,2029,'TIPO_MONEDA','RIAL CATARÍ'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,711,2030,'TIPO_MONEDA','LEU RUMANO'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,712,2031,'TIPO_MONEDA','RUBLO'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,713,2032,'TIPO_MONEDA','FRANCO RUANDÉS'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,714,2033,'TIPO_MONEDA','DÓLAR DEL CARIBE ORIENTAL'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,715,2036,'TIPO_MONEDA','TALA'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,716,2038,'TIPO_MONEDA','DOBRA'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,717,2039,'TIPO_MONEDA','RIAL SAUDÍ'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,718,2041,'TIPO_MONEDA','DINAR SERBIO'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,719,2042,'TIPO_MONEDA','RUPIA SEYCHELLENSE'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,720,2043,'TIPO_MONEDA','LEONA'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,721,2044,'TIPO_MONEDA','DÓLAR SINGAPURENSE'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,722,2047,'TIPO_MONEDA','DÓLAR SALOMONENSE'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,723,2048,'TIPO_MONEDA','CHELÍN SOMALÍ'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,724,2049,'TIPO_MONEDA','RAND'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,725,2051,'TIPO_MONEDA','LIBRA SURSUDANESA'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,726,2052,'TIPO_MONEDA','RUPIA CEILANDESA'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,727,2053,'TIPO_MONEDA','LIBRA SUDANESA'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,728,2054,'TIPO_MONEDA','DÓLAR SURINAMÉS'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,729,2055,'TIPO_MONEDA','LILANGENI'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,730,2056,'TIPO_MONEDA','CORONA SUECA'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,731,2057,'TIPO_MONEDA','FRANCO SUIZO'),('2019-08-19 18:29:20',NULL,NULL,'admin',NULL,NULL,732,2058,'TIPO_MONEDA','LIBRA SIRIA'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,733,2059,'TIPO_MONEDA','NUEVO DÓLAR DE TAIWÁN'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,734,2060,'TIPO_MONEDA','SOMONI'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,735,2061,'TIPO_MONEDA','CHELÍN TANZANIANO'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,736,2062,'TIPO_MONEDA','BAT'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,737,2064,'TIPO_MONEDA','PAANGA'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,738,2065,'TIPO_MONEDA','DÓLAR TRINITENSE'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,739,2066,'TIPO_MONEDA','DINAR TUNECINO'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,740,2067,'TIPO_MONEDA','LIRA TURCA'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,741,2068,'TIPO_MONEDA','MANAT TURCOMANO'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,742,2069,'TIPO_MONEDA','CHELÍN UGANDÉS'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,743,2070,'TIPO_MONEDA','GRIVNA'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,744,2072,'TIPO_MONEDA','PESO URUGUAYO'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,745,2073,'TIPO_MONEDA','SUM'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,746,2074,'TIPO_MONEDA','VATU DO VANUATU'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,747,2076,'TIPO_MONEDA','BOLÍVAR FUERTE'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,748,2077,'TIPO_MONEDA','DONG'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,749,2078,'TIPO_MONEDA','RIAL YEMENÍ'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,750,2079,'TIPO_MONEDA','KUACHA ZAMBIANO'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,751,2080,'TIPO_MONEDA','DÓLAR ZIMBABUENSE'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,752,2027,'TIPO_MONEDA','ZLOTI'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,753,688,'TIPO_MONEDA','BOLIVIANO'),('2019-08-19 18:29:21',NULL,NULL,'admin',NULL,NULL,754,689,'TIPO_MONEDA','DOLAR'),('2019-08-19 18:29:33',NULL,NULL,'admin',NULL,NULL,755,63,'UNIDAD_MEDIDA','ONZA TROY'),('2019-08-19 18:29:33',NULL,NULL,'admin',NULL,NULL,756,64,'UNIDAD_MEDIDA','LIBRA FINA'),('2019-08-19 18:29:33',NULL,NULL,'admin',NULL,NULL,757,65,'UNIDAD_MEDIDA','DISPLAY'),('2019-08-19 18:29:33',NULL,NULL,'admin',NULL,NULL,758,66,'UNIDAD_MEDIDA','BULTO'),('2019-08-22 17:21:56',NULL,NULL,'admin',NULL,NULL,765,5,'TIPO_DOCUMENTO_IDENTIDAD','NUMERO DE IDENTIFICACION TRIBUTARIA'),('2019-08-22 17:21:56',NULL,NULL,'admin',NULL,NULL,766,4,'TIPO_DOCUMENTO_IDENTIDAD','OTRO DOCUMENTO DE IDENTIDAD'),('2019-08-22 17:21:57',NULL,NULL,'admin',NULL,NULL,767,3,'TIPO_DOCUMENTO_IDENTIDAD','PASAPORTE'),('2019-08-22 17:21:57',NULL,NULL,'admin',NULL,NULL,768,2,'TIPO_DOCUMENTO_IDENTIDAD','CARNET DE IDENTIDAD DE EXTRANJERO'),('2019-08-22 17:21:57',NULL,NULL,'admin',NULL,NULL,769,1,'TIPO_DOCUMENTO_IDENTIDAD','CARNET DE IDENTIDAD'),('2019-08-22 18:40:15',NULL,NULL,'admin',NULL,NULL,770,9,'TIPO_METODO_PAGO','TRANSFERENCIA SWIFT'),('2019-08-22 18:40:15',NULL,NULL,'admin',NULL,NULL,771,8,'TIPO_METODO_PAGO','DEPOSITO EN CUENTA'),('2019-08-22 18:40:16',NULL,NULL,'admin',NULL,NULL,772,7,'TIPO_METODO_PAGO','TRANSFERENCIA BANCARIA'),('2019-08-22 18:40:16',NULL,NULL,'admin',NULL,NULL,773,6,'TIPO_METODO_PAGO','PAGO POSTERIOR'),('2019-08-22 18:40:16',NULL,NULL,'admin',NULL,NULL,774,5,'TIPO_METODO_PAGO','OTROS'),('2019-08-22 18:40:16',NULL,NULL,'admin',NULL,NULL,775,4,'TIPO_METODO_PAGO','VALES'),('2019-08-22 18:40:16',NULL,NULL,'admin',NULL,NULL,776,2,'TIPO_METODO_PAGO','TARJETA'),('2019-08-22 18:40:16',NULL,NULL,'admin',NULL,NULL,777,1,'TIPO_METODO_PAGO','EFECTIVO'),('2019-08-22 18:40:16',NULL,NULL,'admin',NULL,NULL,778,3,'TIPO_METODO_PAGO','CHEQUE');
/*!40000 ALTER TABLE `api_sincronizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `api_sucursal`
--

DROP TABLE IF EXISTS `api_sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_sucursal` (
  `fecha_alta` datetime DEFAULT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) DEFAULT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) NOT NULL,
  `id_sucursal` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_empresa` int(20) DEFAULT NULL,
  `codigo_sucursal` int(20) NOT NULL,
  `nombre_sucursal` varchar(50) NOT NULL,
  `direccion` varchar(300) NOT NULL,
  `tiene_cuis` varchar(5) NOT NULL,
  PRIMARY KEY (`id_sucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_sucursal`
--

LOCK TABLES `api_sucursal` WRITE;
/*!40000 ALTER TABLE `api_sucursal` DISABLE KEYS */;
INSERT INTO `api_sucursal` VALUES ('2019-08-12 17:29:16',NULL,NULL,'admin',NULL,'',10,1,0,'Sucursal Central','direccion sucursal','SI'),('2019-08-14 23:22:43',NULL,NULL,'admin',NULL,'',11,1,1,'Sucursal 1','asfafadfaf','NO'),('2019-08-15 07:20:24',NULL,NULL,'admin',NULL,'',12,1,1,'nuevo sucursal','direccion','NO');
/*!40000 ALTER TABLE `api_sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `par_actividad`
--

DROP TABLE IF EXISTS `par_actividad`;
/*!50001 DROP VIEW IF EXISTS `par_actividad`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_actividad` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_condicion`
--

DROP TABLE IF EXISTS `par_condicion`;
/*!50001 DROP VIEW IF EXISTS `par_condicion`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_condicion` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_estado`
--

DROP TABLE IF EXISTS `par_estado`;
/*!50001 DROP VIEW IF EXISTS `par_estado`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_estado` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_evento_significativo`
--

DROP TABLE IF EXISTS `par_evento_significativo`;
/*!50001 DROP VIEW IF EXISTS `par_evento_significativo`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_evento_significativo` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_leyenda_factura`
--

DROP TABLE IF EXISTS `par_leyenda_factura`;
/*!50001 DROP VIEW IF EXISTS `par_leyenda_factura`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_leyenda_factura` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_mensaje_servicio`
--

DROP TABLE IF EXISTS `par_mensaje_servicio`;
/*!50001 DROP VIEW IF EXISTS `par_mensaje_servicio`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_mensaje_servicio` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_motivo_anulacion`
--

DROP TABLE IF EXISTS `par_motivo_anulacion`;
/*!50001 DROP VIEW IF EXISTS `par_motivo_anulacion`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_motivo_anulacion` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_pais_origen`
--

DROP TABLE IF EXISTS `par_pais_origen`;
/*!50001 DROP VIEW IF EXISTS `par_pais_origen`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_pais_origen` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_sincronizacion`
--

DROP TABLE IF EXISTS `par_sincronizacion`;
/*!50001 DROP VIEW IF EXISTS `par_sincronizacion`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_sincronizacion` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL,
  `grupo` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_tipo_ambiente`
--

DROP TABLE IF EXISTS `par_tipo_ambiente`;
/*!50001 DROP VIEW IF EXISTS `par_tipo_ambiente`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_tipo_ambiente` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_tipo_cliente`
--

DROP TABLE IF EXISTS `par_tipo_cliente`;
/*!50001 DROP VIEW IF EXISTS `par_tipo_cliente`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_tipo_cliente` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_tipo_componente`
--

DROP TABLE IF EXISTS `par_tipo_componente`;
/*!50001 DROP VIEW IF EXISTS `par_tipo_componente`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_tipo_componente` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_tipo_departamento`
--

DROP TABLE IF EXISTS `par_tipo_departamento`;
/*!50001 DROP VIEW IF EXISTS `par_tipo_departamento`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_tipo_departamento` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_tipo_documento_fiscal`
--

DROP TABLE IF EXISTS `par_tipo_documento_fiscal`;
/*!50001 DROP VIEW IF EXISTS `par_tipo_documento_fiscal`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_tipo_documento_fiscal` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_tipo_documento_identidad`
--

DROP TABLE IF EXISTS `par_tipo_documento_identidad`;
/*!50001 DROP VIEW IF EXISTS `par_tipo_documento_identidad`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_tipo_documento_identidad` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_tipo_documento_sector`
--

DROP TABLE IF EXISTS `par_tipo_documento_sector`;
/*!50001 DROP VIEW IF EXISTS `par_tipo_documento_sector`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_tipo_documento_sector` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_tipo_emision`
--

DROP TABLE IF EXISTS `par_tipo_emision`;
/*!50001 DROP VIEW IF EXISTS `par_tipo_emision`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_tipo_emision` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_tipo_facturador`
--

DROP TABLE IF EXISTS `par_tipo_facturador`;
/*!50001 DROP VIEW IF EXISTS `par_tipo_facturador`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_tipo_facturador` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_tipo_metodo_pago`
--

DROP TABLE IF EXISTS `par_tipo_metodo_pago`;
/*!50001 DROP VIEW IF EXISTS `par_tipo_metodo_pago`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_tipo_metodo_pago` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_tipo_modalidad`
--

DROP TABLE IF EXISTS `par_tipo_modalidad`;
/*!50001 DROP VIEW IF EXISTS `par_tipo_modalidad`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_tipo_modalidad` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_tipo_moneda`
--

DROP TABLE IF EXISTS `par_tipo_moneda`;
/*!50001 DROP VIEW IF EXISTS `par_tipo_moneda`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_tipo_moneda` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_tipo_punto_venta`
--

DROP TABLE IF EXISTS `par_tipo_punto_venta`;
/*!50001 DROP VIEW IF EXISTS `par_tipo_punto_venta`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_tipo_punto_venta` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `par_unidad_medida`
--

DROP TABLE IF EXISTS `par_unidad_medida`;
/*!50001 DROP VIEW IF EXISTS `par_unidad_medida`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `par_unidad_medida` (
  `codigo` tinyint NOT NULL,
  `descripcion` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `par_valor`
--

DROP TABLE IF EXISTS `par_valor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `par_valor` (
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) NOT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_par_valor` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(5) NOT NULL,
  `contexto` varchar(30) NOT NULL,
  `descripcion` varchar(150) NOT NULL,
  `grupo` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`id_par_valor`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `par_valor`
--

LOCK TABLES `par_valor` WRITE;
/*!40000 ALTER TABLE `par_valor` DISABLE KEYS */;
INSERT INTO `par_valor` VALUES ('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,48,'1','SINCRONIZACION','Eventos Significativos','EVENTO_SIGNIFICATIVO'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,49,'18','SINCRONIZACION','Leyenda Factura','LEYENDA_FACTURA'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,50,'2','SINCRONIZACION','Mensaje de Servicios','MENSAJE_SERVICIO'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,51,'4','SINCRONIZACION','Motivos de Anulacion','MOTIVO_ANULACION'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,52,'5','SINCRONIZACION','Paises Origen','PAIS_ORIGEN'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,53,'6','SINCRONIZACION','Tipo de Ambientes','TIPO_AMBIENTE'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,54,'7','SINCRONIZACION','Tipos de Componentes','TIPO_COMPONENTE'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,55,'8','SINCRONIZACION','Tipos de Departamento','TIPO_DEPARTAMENTO'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,56,'9','SINCRONIZACION','Tipos de Documento Fiscal','TIPO_DOCUMENTO_FISCAL'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,57,'16','SINCRONIZACION','Tipos de Punto de Venta','TIPO_PUNTO_VENTA'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,58,'3','SINCRONIZACION','Actividades Economicas','ACTIVIDADES'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,59,'10','SINCRONIZACION','Tipos de Documento de Identidad','TIPO_DOCUMENTO_IDENTIDAD'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,60,'11','SINCRONIZACION','Tipos de Documento Sector','TIPO_DOCUMENTO_SECTOR'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,61,'12','SINCRONIZACION','Tipos de Emision','TIPO_EMISION'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,62,'13','SINCRONIZACION','Tipos de Metodo de Pago','TIPO_METODO_PAGO'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,63,'14','SINCRONIZACION','Tipos de Modalidad','TIPO_MODALIDAD'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,64,'15','SINCRONIZACION','Tipos de Moneda','TIPO_MONEDA'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,65,'17','SINCRONIZACION','Unidades de Medida','UNIDAD_MEDIDA'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,66,'SI','CONDICION','Si ',NULL),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,67,'NO','CONDICION','No',NULL),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,70,'JUR','TIPO_CLIENTE','Tipo de Cliente Jurido',NULL),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,71,'NAT','TIPO_CLIENTE','Tipo de Cliente Natural',NULL),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,72,'20','SINCRONIZACION','Productos y Servicios','PRODUCTO_SERVICIO'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,75,'VGTE','ESTADO','Vigente',NULL),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,76,'NVGTE','ESTADO','No Vigente',NULL);
/*!40000 ALTER TABLE `par_valor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `par_actividad`
--

/*!50001 DROP TABLE IF EXISTS `par_actividad`*/;
/*!50001 DROP VIEW IF EXISTS `par_actividad`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_actividad` AS select distinct `api_actividad`.`codigo_actividad` AS `codigo`,`api_actividad`.`descripcion` AS `descripcion` from `api_actividad` order by `api_actividad`.`codigo_actividad` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_condicion`
--

/*!50001 DROP TABLE IF EXISTS `par_condicion`*/;
/*!50001 DROP VIEW IF EXISTS `par_condicion`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_condicion` AS select `par_valor`.`codigo` AS `codigo`,`par_valor`.`descripcion` AS `descripcion` from `par_valor` where (`par_valor`.`contexto` = 'CONDICION') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_estado`
--

/*!50001 DROP TABLE IF EXISTS `par_estado`*/;
/*!50001 DROP VIEW IF EXISTS `par_estado`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_estado` AS select `par_valor`.`codigo` AS `codigo`,`par_valor`.`descripcion` AS `descripcion` from `par_valor` where (`par_valor`.`contexto` = 'ESTADO') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_evento_significativo`
--

/*!50001 DROP TABLE IF EXISTS `par_evento_significativo`*/;
/*!50001 DROP VIEW IF EXISTS `par_evento_significativo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_evento_significativo` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'EVENTO_SIGNIFICATIVO') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_leyenda_factura`
--

/*!50001 DROP TABLE IF EXISTS `par_leyenda_factura`*/;
/*!50001 DROP VIEW IF EXISTS `par_leyenda_factura`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_leyenda_factura` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'LEYENDA_FACTURA') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_mensaje_servicio`
--

/*!50001 DROP TABLE IF EXISTS `par_mensaje_servicio`*/;
/*!50001 DROP VIEW IF EXISTS `par_mensaje_servicio`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_mensaje_servicio` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'MENSAJE_SERVICIO') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_motivo_anulacion`
--

/*!50001 DROP TABLE IF EXISTS `par_motivo_anulacion`*/;
/*!50001 DROP VIEW IF EXISTS `par_motivo_anulacion`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_motivo_anulacion` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'MOTIVO_ANULACION') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_pais_origen`
--

/*!50001 DROP TABLE IF EXISTS `par_pais_origen`*/;
/*!50001 DROP VIEW IF EXISTS `par_pais_origen`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_pais_origen` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'PAIS_ORIGEN') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_sincronizacion`
--

/*!50001 DROP TABLE IF EXISTS `par_sincronizacion`*/;
/*!50001 DROP VIEW IF EXISTS `par_sincronizacion`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_sincronizacion` AS select `par_valor`.`codigo` AS `codigo`,`par_valor`.`descripcion` AS `descripcion`,`par_valor`.`grupo` AS `grupo` from `par_valor` where (`par_valor`.`contexto` = 'SINCRONIZACION') order by cast(`par_valor`.`codigo` as unsigned) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_tipo_ambiente`
--

/*!50001 DROP TABLE IF EXISTS `par_tipo_ambiente`*/;
/*!50001 DROP VIEW IF EXISTS `par_tipo_ambiente`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_tipo_ambiente` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'TIPO_AMBIENTE') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_tipo_cliente`
--

/*!50001 DROP TABLE IF EXISTS `par_tipo_cliente`*/;
/*!50001 DROP VIEW IF EXISTS `par_tipo_cliente`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_tipo_cliente` AS select `par_valor`.`codigo` AS `codigo`,`par_valor`.`descripcion` AS `descripcion` from `par_valor` where (`par_valor`.`contexto` = 'TIPO_CLIENTE') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_tipo_componente`
--

/*!50001 DROP TABLE IF EXISTS `par_tipo_componente`*/;
/*!50001 DROP VIEW IF EXISTS `par_tipo_componente`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_tipo_componente` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'TIPO_COMPONENTE') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_tipo_departamento`
--

/*!50001 DROP TABLE IF EXISTS `par_tipo_departamento`*/;
/*!50001 DROP VIEW IF EXISTS `par_tipo_departamento`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_tipo_departamento` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'TIPO_DEPARTAMENTO') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_tipo_documento_fiscal`
--

/*!50001 DROP TABLE IF EXISTS `par_tipo_documento_fiscal`*/;
/*!50001 DROP VIEW IF EXISTS `par_tipo_documento_fiscal`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_tipo_documento_fiscal` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'TIPO_DOCUMENTO_FISCAL') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_tipo_documento_identidad`
--

/*!50001 DROP TABLE IF EXISTS `par_tipo_documento_identidad`*/;
/*!50001 DROP VIEW IF EXISTS `par_tipo_documento_identidad`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_tipo_documento_identidad` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'TIPO_DOCUMENTO_IDENTIDAD') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_tipo_documento_sector`
--

/*!50001 DROP TABLE IF EXISTS `par_tipo_documento_sector`*/;
/*!50001 DROP VIEW IF EXISTS `par_tipo_documento_sector`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_tipo_documento_sector` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'TIPO_DOCUMENTO_SECTOR') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_tipo_emision`
--

/*!50001 DROP TABLE IF EXISTS `par_tipo_emision`*/;
/*!50001 DROP VIEW IF EXISTS `par_tipo_emision`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_tipo_emision` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'TIPO_EMISION') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_tipo_facturador`
--

/*!50001 DROP TABLE IF EXISTS `par_tipo_facturador`*/;
/*!50001 DROP VIEW IF EXISTS `par_tipo_facturador`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_tipo_facturador` AS select `par_valor`.`codigo` AS `codigo`,`par_valor`.`descripcion` AS `descripcion` from `par_valor` where (`par_valor`.`contexto` = 'TIPO_FACTURADOR') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_tipo_metodo_pago`
--

/*!50001 DROP TABLE IF EXISTS `par_tipo_metodo_pago`*/;
/*!50001 DROP VIEW IF EXISTS `par_tipo_metodo_pago`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_tipo_metodo_pago` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'TIPO_METODO_PAGO') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_tipo_modalidad`
--

/*!50001 DROP TABLE IF EXISTS `par_tipo_modalidad`*/;
/*!50001 DROP VIEW IF EXISTS `par_tipo_modalidad`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_tipo_modalidad` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'TIPO_MODALIDAD') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_tipo_moneda`
--

/*!50001 DROP TABLE IF EXISTS `par_tipo_moneda`*/;
/*!50001 DROP VIEW IF EXISTS `par_tipo_moneda`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_tipo_moneda` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'TIPO_MONEDA') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_tipo_punto_venta`
--

/*!50001 DROP TABLE IF EXISTS `par_tipo_punto_venta`*/;
/*!50001 DROP VIEW IF EXISTS `par_tipo_punto_venta`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_tipo_punto_venta` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'TIPO_PUNTO_VENTA') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `par_unidad_medida`
--

/*!50001 DROP TABLE IF EXISTS `par_unidad_medida`*/;
/*!50001 DROP VIEW IF EXISTS `par_unidad_medida`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_unidad_medida` AS select `api_sincronizacion`.`codigo` AS `codigo`,`api_sincronizacion`.`descripcion` AS `descripcion` from `api_sincronizacion` where (`api_sincronizacion`.`grupo` = 'UNIDAD_MEDIDA') order by `api_sincronizacion`.`codigo` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-02 13:23:15
