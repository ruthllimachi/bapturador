-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: dbo_administrador
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

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
-- Table structure for table `adm_empresa`
--

DROP TABLE IF EXISTS `adm_empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_empresa` (
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) NOT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_empresa` int(20) NOT NULL,
  `ciudad` varchar(60) DEFAULT NULL,
  `direccion_fiscal` varchar(100) DEFAULT NULL,
  `razon_social` varchar(150) DEFAULT NULL,
  `nit_empresa` int(11) NOT NULL,
  `contacto` varchar(60) DEFAULT NULL,
  `telefono_empresa` int(11) NOT NULL,
  `inicio_periodo` date NOT NULL,
  `fin_periodo` date NOT NULL,
  `codigo_ambiente` int(11) NOT NULL,
  `tipo_modalidad_defecto` int(11) NOT NULL,
  PRIMARY KEY (`id_empresa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_empresa`
--

LOCK TABLES `adm_empresa` WRITE;
/*!40000 ALTER TABLE `adm_empresa` DISABLE KEYS */;
INSERT INTO `adm_empresa` VALUES ('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,1,'La Paz','Av. Siempre Viva #3466','BAP Srl.',1002329022,'juan perez',2245978,'2019-01-01','2020-01-01',2,1);
/*!40000 ALTER TABLE `adm_empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_permiso`
--

DROP TABLE IF EXISTS `adm_permiso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_permiso` (
  `fecha_alta` datetime NOT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) NOT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_permiso` bigint(20) NOT NULL,
  `id_proceso` int(20) NOT NULL,
  `id_rol` bigint(20) NOT NULL,
  PRIMARY KEY (`id_permiso`),
  KEY `adm_permiso_rol_ibfk_2` (`id_rol`),
  KEY `adm_permiso_proceso_ibfk_1` (`id_proceso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_permiso`
--

LOCK TABLES `adm_permiso` WRITE;
/*!40000 ALTER TABLE `adm_permiso` DISABLE KEYS */;
INSERT INTO `adm_permiso` VALUES ('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,80,79,3),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,81,80,3),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,82,81,3),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,83,82,3),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,84,83,3),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,85,84,3),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,86,85,3),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,87,86,3),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,88,87,3);
/*!40000 ALTER TABLE `adm_permiso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_persona`
--

DROP TABLE IF EXISTS `adm_persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_persona` (
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) NOT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_persona` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_empresa` bigint(20) DEFAULT NULL,
  `nombre_completo` varchar(50) NOT NULL,
  `numero_documento` varchar(30) NOT NULL,
  PRIMARY KEY (`id_persona`),
  KEY `id_empresa` (`id_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_persona`
--

LOCK TABLES `adm_persona` WRITE;
/*!40000 ALTER TABLE `adm_persona` DISABLE KEYS */;
INSERT INTO `adm_persona` VALUES ('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,2,1,'admin1','11111'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,3,1,'admin2','22222'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,4,1,'admin3','33333');
/*!40000 ALTER TABLE `adm_persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_proceso`
--

DROP TABLE IF EXISTS `adm_proceso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_proceso` (
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) NOT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_proceso` int(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`id_proceso`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_proceso`
--

LOCK TABLES `adm_proceso` WRITE;
/*!40000 ALTER TABLE `adm_proceso` DISABLE KEYS */;
INSERT INTO `adm_proceso` VALUES ('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,79,'crea sucursal'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,80,'crea punto de venta'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,81,'asigna sucursal'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,82,'asigna punto de venta'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,83,'registra dosificacion sucursal'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,84,'registra dosificacion punto vneta'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,85,'sincroniza catalogos y codigos'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,86,'sincroniza productos'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,87,'solicita CUFD'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,88,'registra productos'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,89,'registra clientes');
/*!40000 ALTER TABLE `adm_proceso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_rol`
--

DROP TABLE IF EXISTS `adm_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_rol` (
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) NOT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_rol` int(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(70) NOT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_rol`
--

LOCK TABLES `adm_rol` WRITE;
/*!40000 ALTER TABLE `adm_rol` DISABLE KEYS */;
INSERT INTO `adm_rol` VALUES ('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,3,'ADMIN'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,4,'USER');
/*!40000 ALTER TABLE `adm_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_rol_usuario`
--

DROP TABLE IF EXISTS `adm_rol_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_rol_usuario` (
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) NOT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_rol_usuario` int(20) NOT NULL AUTO_INCREMENT,
  `id_rol` int(20) NOT NULL,
  `id_usuario` int(20) NOT NULL,
  PRIMARY KEY (`id_rol_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_rol_usuario`
--

LOCK TABLES `adm_rol_usuario` WRITE;
/*!40000 ALTER TABLE `adm_rol_usuario` DISABLE KEYS */;
INSERT INTO `adm_rol_usuario` VALUES ('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,2,3,2),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,3,4,3);
/*!40000 ALTER TABLE `adm_rol_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_session`
--

DROP TABLE IF EXISTS `adm_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_session` (
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) NOT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_session` int(20) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(20) NOT NULL,
  `fecha_expiracion` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  PRIMARY KEY (`id_session`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `adm_session_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `adm_usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_session`
--

LOCK TABLES `adm_session` WRITE;
/*!40000 ALTER TABLE `adm_session` DISABLE KEYS */;
INSERT INTO `adm_session` VALUES ('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,2,2,'2019-12-31 00:00:00','eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1NTE5ODk0NzQsInN1YiI6InsndXNlck5hbWUnPSdhZG1pbicsICd0b2tlbic9J251bGwgJ30ifQ.pjq9QjJvYEVY6OYhPA21F2Mb08IptEFZWPrn0ce73N0zN3QmshexnLv9x6fHWuDw7qlxwDNl9bVhW0i4spx6Jw'),('2019-08-07 23:05:35',NULL,NULL,'adm',NULL,NULL,3,3,'2019-08-07 00:00:00','eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1NjUyMzM1MzUsInN1YiI6ImNvbS5iYXAuYWRtLmR0by5Vc2VyVG9rZW5ANjVlMDM3NjUifQ.PET1q1W7MenlSHRwFi2wY4AM6dngp__MfoY9ENgShsrKsn_4a6tVqmnwfmoCXMikii9Y4apmBG_t7KFJpXEm5A'),('2019-10-21 09:04:02',NULL,NULL,'adm',NULL,NULL,4,4,'2019-10-21 00:00:00','eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1NzE2NjMwNDIsInN1YiI6ImNvbS5iYXAuYWRtLmR0by5Vc2VyVG9rZW5ANWE1ZjBkMjQifQ.8l-1qCakEzBC1A5KOm5ijVh2eIulYxJyOIYsN6-6nLyRL3ZDOrcwMeM26BbTkrs3vh4DYJ_KyPYrlH1HxCI77w');
/*!40000 ALTER TABLE `adm_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_sistema`
--

DROP TABLE IF EXISTS `adm_sistema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_sistema` (
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) NOT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_sistema` int(20) NOT NULL AUTO_INCREMENT,
  `nombre_sistema` varchar(50) NOT NULL,
  `version` varchar(20) NOT NULL,
  `codigo_sistema` varchar(30) NOT NULL,
  PRIMARY KEY (`id_sistema`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_sistema`
--

LOCK TABLES `adm_sistema` WRITE;
/*!40000 ALTER TABLE `adm_sistema` DISABLE KEYS */;
INSERT INTO `adm_sistema` VALUES ('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,1,'BAPTURADOR','2.0.1','5C72C9918AF');
/*!40000 ALTER TABLE `adm_sistema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_usuario`
--

DROP TABLE IF EXISTS `adm_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_usuario` (
  `fecha_alta` datetime NOT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_alta` varchar(30) NOT NULL,
  `usuario_baja` varchar(30) DEFAULT NULL,
  `usuario_modificacion` varchar(30) DEFAULT NULL,
  `id_usuario` int(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `fecha_expiracion` datetime NOT NULL,
  `id_persona` int(20) NOT NULL,
  `estado_usuario` varchar(5) NOT NULL,
  `id_punto_venta` int(20) DEFAULT NULL,
  `id_sucursal` int(20) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `adm_usuario_persona_ibfk_1` (`id_persona`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_usuario`
--

LOCK TABLES `adm_usuario` WRITE;
/*!40000 ALTER TABLE `adm_usuario` DISABLE KEYS */;
INSERT INTO `adm_usuario` VALUES ('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,2,'admin1','admin1','2020-12-30 00:00:00',2,'ACTIV',NULL,10),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,3,'admin2','admin2','2020-12-30 00:00:00',3,'ACTIV',NULL,11),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,4,'admin3','admin3','2020-01-01 00:00:00',4,'ACTIV',3,12);
/*!40000 ALTER TABLE `adm_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `par_estado_usuario`
--

DROP TABLE IF EXISTS `par_estado_usuario`;
/*!50001 DROP VIEW IF EXISTS `par_estado_usuario`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `par_estado_usuario` AS SELECT 
 1 AS `codigo`,
 1 AS `descripcion`*/;
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
  `id_par_valor` int(20) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(5) NOT NULL,
  `contexto` varchar(30) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`id_par_valor`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `par_valor`
--

LOCK TABLES `par_valor` WRITE;
/*!40000 ALTER TABLE `par_valor` DISABLE KEYS */;
INSERT INTO `par_valor` VALUES ('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,1,'ACTIV','ESTADO_USUARIO','Usuario Activo'),('2019-01-01 00:00:00',NULL,NULL,'admin',NULL,NULL,2,'INACT','ESTADO_USUARIO','Usuario Inactivo');
/*!40000 ALTER TABLE `par_valor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `par_estado_usuario`
--

/*!50001 DROP VIEW IF EXISTS `par_estado_usuario`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `par_estado_usuario` AS select `par_valor`.`codigo` AS `codigo`,`par_valor`.`descripcion` AS `descripcion` from `par_valor` where ((`par_valor`.`contexto` = 'ESTADO_USUARIO') and isnull(`par_valor`.`fecha_baja`)) */;
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

-- Dump completed on 2020-04-13  0:10:07
