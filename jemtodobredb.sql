CREATE DATABASE  IF NOT EXISTS `jemtodobredb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `jemtodobredb`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: jemtodobredb
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adres`
--

DROP TABLE IF EXISTS `adres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adres` (
  `id_adres` bigint NOT NULL AUTO_INCREMENT,
  `kod_pocztowy` varchar(255) DEFAULT NULL,
  `miejscowosc` varchar(255) DEFAULT NULL,
  `nr_domu` int DEFAULT NULL,
  `nr_mieszkania` int DEFAULT NULL,
  `ulica` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_adres`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adres`
--

LOCK TABLES `adres` WRITE;
/*!40000 ALTER TABLE `adres` DISABLE KEYS */;
/*!40000 ALTER TABLE `adres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alergeny`
--

DROP TABLE IF EXISTS `alergeny`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alergeny` (
  `id_alergen` int NOT NULL AUTO_INCREMENT,
  `nazwa_alergenu` varchar(255) DEFAULT NULL,
  `opis_alergenu` varchar(255) DEFAULT NULL,
  `typ_alergenu` varchar(255) DEFAULT NULL,
  `zrodlo_alergenu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_alergen`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alergeny`
--

LOCK TABLES `alergeny` WRITE;
/*!40000 ALTER TABLE `alergeny` DISABLE KEYS */;
INSERT INTO `alergeny` VALUES (1,'Gluten','Białko znajdujące się w niektórych zbóż','Pokarmowy','Pszenica, jęczmień, żyto'),(2,'Soja','Roślina z rodziny bobowatych','Pokarmowy','Produkty sojowe, sos sojowy'),(3,'Glutaminian sodu','Sól sodowa kwasu glutaminowego','Pokarmowy','Dodatek do żywności, glutaminian, E621'),(4,'Seler','Warzywo z rodziny selera','Pokarmowy','Seler naciowy, bulwa selera'),(5,'Jajo','Produkt odzwierzęcy','Pokarmowy','Białko jaja kurzego'),(6,'Wieprzowina','Mięso wieprzowe','Pokarmowy','Mięso wieprzowe, wieprzowina'),(7,'Sezam','Roślina oleista','Pokarmowy','Ziarna sezamu, olej sezamowy'),(8,'Ryba','Produkty pochodzenia morskiego','Pokarmowy','Łosoś, tuńczyk'),(9,'Nabiał','Produkty pochodzenia mlecznego','Pokarmowy','Ser Philadelphia');
/*!40000 ALTER TABLE `alergeny` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategoria_menu`
--

DROP TABLE IF EXISTS `kategoria_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kategoria_menu` (
  `id_kategoria` int NOT NULL AUTO_INCREMENT,
  `nazwa_kategorii` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_kategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategoria_menu`
--

LOCK TABLES `kategoria_menu` WRITE;
/*!40000 ALTER TABLE `kategoria_menu` DISABLE KEYS */;
INSERT INTO `kategoria_menu` VALUES (1,'Przystawki'),(2,'Zupy'),(3,'Ramen'),(4,'Sushi'),(5,'Napoje');
/*!40000 ALTER TABLE `kategoria_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pozycje_menu`
--

DROP TABLE IF EXISTS `pozycje_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pozycje_menu` (
  `id_pozycja_menu` int NOT NULL AUTO_INCREMENT,
  `cena` double DEFAULT NULL,
  `id_alergen` int DEFAULT NULL,
  `id_kategoria` int DEFAULT NULL,
  `nazwa_pozycji` varchar(255) DEFAULT NULL,
  `opis` varchar(255) DEFAULT NULL,
  `skladniki` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_pozycja_menu`),
  KEY `FKr6d1mqg92xpe210lsc0l2ja5s` (`id_alergen`),
  KEY `FK4jsuaq0agaeuqaho4f2cqxvhj` (`id_kategoria`),
  CONSTRAINT `FK4jsuaq0agaeuqaho4f2cqxvhj` FOREIGN KEY (`id_kategoria`) REFERENCES `kategoria_menu` (`id_kategoria`),
  CONSTRAINT `FKr6d1mqg92xpe210lsc0l2ja5s` FOREIGN KEY (`id_alergen`) REFERENCES `alergeny` (`id_alergen`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pozycje_menu`
--

LOCK TABLES `pozycje_menu` WRITE;
/*!40000 ALTER TABLE `pozycje_menu` DISABLE KEYS */;
INSERT INTO `pozycje_menu` VALUES (1,25.99,1,1,'Kim Chi','Tradycyjna koreańska sałatka z kiszonej kapusty','kapusta, czosnek, imbir, czerwona papryka, ryżowa mąka kleista'),(2,18.5,2,1,'Goma Wakame','Sałatka z wodorostów w sezamowym sosie','wodorosty wakame, sos sezamowy, cebula, sezam, mirin'),(3,12.99,3,2,'Miso Shiru','Japońska zupa miso z tofu i wodorostami','pasta miso, tofu, wodorosty, cebula, tofu'),(4,30.5,4,2,'Bajiru Sakana','Pieczony biała ryba z sosem bajiru','ryba biała, sos sojowy, mirin, sake, cebula, imbir'),(5,22.99,5,3,'Shifudo Ramen','Klasyczne japońskie danie z makaronem ramen','makaron ramen, bulion dashi, miso, jajko, wędzony boczek, wodorosty, cebula'),(6,20.5,6,3,'Tori Ramen','Ramen z kurczakiem w bulionie miso','makaron ramen, bulion miso, kurczak, jajko, wodorosty, cebula, szczypiorek'),(7,28.99,8,4,'Sake Philadelphia','Duży zwinięty nori z łososiem i serem Philadelphia','ryż sushi, nori, łosoś, ser Philadelphia, ogórek, awokado'),(8,32.5,8,4,'Maguro','Tradycyjne sushi z tuńczykiem','ryż sushi, nori, tuńczyk, ogórek, awokado'),(9,35.99,8,4,'Unagi','Nigiri z pieczonym węgorzem','ryż sushi, węgorz, sos teriyaki'),(10,30.5,8,4,'Sake','Nigiri z łososiem','ryż sushi, łosoś');
/*!40000 ALTER TABLE `pozycje_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pozycje_zamowienia`
--

DROP TABLE IF EXISTS `pozycje_zamowienia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pozycje_zamowienia` (
  `id_pozycja_zamowienia` bigint NOT NULL AUTO_INCREMENT,
  `cena` double DEFAULT NULL,
  `id_pozycji` int DEFAULT NULL,
  `id_zamowienia` int DEFAULT NULL,
  `ilosc` int DEFAULT NULL,
  PRIMARY KEY (`id_pozycja_zamowienia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pozycje_zamowienia`
--

LOCK TABLES `pozycje_zamowienia` WRITE;
/*!40000 ALTER TABLE `pozycje_zamowienia` DISABLE KEYS */;
/*!40000 ALTER TABLE `pozycje_zamowienia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uzytkownicy`
--

DROP TABLE IF EXISTS `uzytkownicy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uzytkownicy` (
  `id_uzytkownik` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nazwa_uzytkownika` varchar(255) DEFAULT NULL,
  `haslo` varchar(255) DEFAULT NULL,
  `telefon` varchar(255) DEFAULT NULL,
  `typ_uzytkownika` tinyint DEFAULT NULL,
  PRIMARY KEY (`id_uzytkownik`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uzytkownicy`
--

LOCK TABLES `uzytkownicy` WRITE;
/*!40000 ALTER TABLE `uzytkownicy` DISABLE KEYS */;
INSERT INTO `uzytkownicy` VALUES (1,'test1@wp.pl','test1','123','123456789',NULL);
/*!40000 ALTER TABLE `uzytkownicy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zamowienia`
--

DROP TABLE IF EXISTS `zamowienia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zamowienia` (
  `id_zamowienia` bigint NOT NULL AUTO_INCREMENT,
  `adres` int DEFAULT NULL,
  `data_realizacji` varchar(255) DEFAULT NULL,
  `data_zamowienia` varchar(255) DEFAULT NULL,
  `dodatkowe_informacje` varchar(255) DEFAULT NULL,
  `id_uzytkownik` int DEFAULT NULL,
  `nr_faktury` varchar(255) DEFAULT NULL,
  `status_zamowienia` varchar(255) DEFAULT NULL,
  `laczna_cena` double DEFAULT NULL,
  PRIMARY KEY (`id_zamowienia`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zamowienia`
--

LOCK TABLES `zamowienia` WRITE;
/*!40000 ALTER TABLE `zamowienia` DISABLE KEYS */;
INSERT INTO `zamowienia` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(13,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(14,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(15,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(16,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(17,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(19,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(20,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(21,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(22,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(23,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(24,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(25,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(26,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(27,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(28,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(29,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(30,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(31,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(32,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(33,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(34,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(35,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(36,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(37,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(38,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(39,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(40,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(41,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(42,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(43,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(44,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(45,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(46,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(47,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(48,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(49,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(50,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(51,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(52,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(53,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(54,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(55,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(56,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(57,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(58,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(59,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(60,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(61,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(62,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(63,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(64,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(65,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(66,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(67,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(68,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(69,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(70,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(71,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(72,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(73,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(74,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(75,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(76,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(77,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(78,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(79,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(80,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `zamowienia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-09 14:38:59
