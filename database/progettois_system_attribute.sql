CREATE DATABASE  IF NOT EXISTS `progettois` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `progettois`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: progettois
-- ------------------------------------------------------
-- Server version	5.7.27-log

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
-- Table structure for table `system_attribute`
--

DROP TABLE IF EXISTS `system_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_attribute` (
  `slug` varchar(150) NOT NULL,
  `value` varchar(50) NOT NULL,
  `fk_user` varchar(50) NOT NULL,
  PRIMARY KEY (`slug`),
  KEY `Fk_User_idx` (`fk_user`),
  CONSTRAINT `Fk_User` FOREIGN KEY (`fk_user`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_attribute`
--

LOCK TABLES `system_attribute` WRITE;
/*!40000 ALTER TABLE `system_attribute` DISABLE KEYS */;
INSERT INTO `system_attribute` VALUES ('request-accepted','6','segreteria@unisa.it'),('request-matriculation-year-range','5','segreteria@unisa.it'),('request-max-cfu','12','segreteria@unisa.it'),('request-min-cfu','1','segreteria@unisa.it'),('request-partially-completed','1','segreteria@unisa.it'),('request-refused','7','segreteria@unisa.it'),('request-upload-path','C:UsersufalDesktopRAT_Files','segreteria@unisa.it'),('request-working-admin','3','segreteria@unisa.it'),('request-working-educational-advice-1','4','segreteria@unisa.it'),('request-working-educational-advice-2','5','segreteria@unisa.it'),('request-working-secretary','2','segreteria@unisa.it');
/*!40000 ALTER TABLE `system_attribute` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-23 12:15:59
