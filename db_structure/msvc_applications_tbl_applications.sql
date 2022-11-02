-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: msvc_applications
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `tbl_applications`
--

DROP TABLE IF EXISTS `tbl_applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_applications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `img` varchar(200) DEFAULT NULL,
  `price` double DEFAULT '0',
  `rating` int DEFAULT '0',
  `tittle` varchar(150) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `developer` varchar(150) DEFAULT NULL,
  `instaled` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_admgcxhpi6c2oam5i6ekxvc33` (`tittle`),
  KEY `FK2ixityh6lemh88xyh8gkdka51` (`category_id`),
  CONSTRAINT `FK2ixityh6lemh88xyh8gkdka51` FOREIGN KEY (`category_id`) REFERENCES `tbl_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_applications`
--

LOCK TABLES `tbl_applications` WRITE;
/*!40000 ALTER TABLE `tbl_applications` DISABLE KEYS */;
INSERT INTO `tbl_applications` VALUES (1,'2022-10-30 18:40:17.320000','Description Aplication 1','1.webp',2.5,3,'Aplication 1',1,'Developer 1',0),(2,'2022-10-30 18:40:34.908000','Description Aplication 2','2.webp',1.95,2,'Aplication 2',1,'Developer 1',1),(3,'2022-10-30 18:40:49.470000','Description Aplication 3','3.webp',0.25,3,'Aplication 3',2,'Developer 2',0),(4,'2022-10-30 18:41:11.643000','Description Aplication 4','4.webp',2.25,2,'Aplication 4',3,'Developer 2',0),(5,'2022-10-30 18:41:27.921000','Description Aplication 5','5.webp',0.85,5,'Aplication 5',3,'Developer 2',0),(6,'2022-10-30 18:41:44.183000','Description Aplication 6','6.webp',6.85,4,'Aplication 6',2,'Developer 1',1),(7,'2022-10-30 18:42:02.702000','Description Aplication 7','7.webp',4.55,2,'Aplication 7',2,'Developer 1',0),(8,'2022-10-30 18:42:16.631000','Description Aplication 8','8.webp',2.55,2,'Aplication 8',1,'Developer 3',0),(9,'2022-10-30 18:42:28.174000','Description Aplication 9','9.webp',1.55,1,'Aplication 9',1,'Developer 3',0),(10,'2022-10-30 18:42:40.206000','Description Aplication 10','10.webp',1.55,3,'Aplication 10',4,'Developer 3',0),(11,'2022-10-30 18:42:54.547000','Description Aplication 11','11.webp',0.35,3,'Aplication 11',4,'Developer 4',0),(12,'2022-10-30 18:43:04.825000','Description Aplication 12','12.webp',0.45,3,'Aplication 12',4,'Developer 4',0),(13,'2022-10-30 18:43:17.587000','Description Aplication 13','13.webp',8.55,2,'Aplication 13',5,'Developer 5',1),(14,'2022-10-30 18:43:57.613000','Description Aplication 14','14.webp',8.15,5,'Aplication 14',5,'Developer 5',0),(15,'2022-10-30 18:44:10.831000','Description Aplication 15','15.webp',7.15,5,'Aplication 15',1,'Developer 5',0),(16,'2022-10-30 18:44:23.870000','Description Aplication 16','16.webp',4.15,2,'Aplication 16',1,'Developer 5',0),(17,'2022-10-30 18:44:37.864000','Description Aplication 17','17.webp',2.15,1,'Aplication 17',5,'Developer 5',0),(18,'2022-10-30 18:44:50.908000','Description Aplication 18','18.webp',0.25,1,'Aplication 18',5,'Developer 1',0),(19,'2022-10-30 18:45:03.052000','Description Aplication 19','19.webp',2.75,2,'Aplication 19',5,'Developer 1',0),(20,'2022-10-30 18:45:15.362000','Description Aplication 20','20.webp',0.4,3,'Aplication 20',4,'Developer 2',1),(21,'2022-11-01 21:46:28.364000','Description Aplication 21','21.webp',1.65,0,'Aplication 21',1,'Developer 1',0),(22,'2022-11-01 21:46:47.093000','Description Aplication 22','22.webp',1.25,0,'Aplication 22',2,'Developer 10',0),(23,'2022-11-01 21:47:03.980000','Description Aplication 23','23.webp',0.25,0,'Aplication 23',2,'Developer 11',0),(24,'2022-11-01 21:47:21.136000','Description Aplication 24','24.webp',1.15,0,'Aplication 24',2,'Developer 2',0),(25,'2022-11-01 21:47:39.867000','Description Aplication 25','25.webp',1.15,0,'Aplication 25',2,'Developer 3',0),(26,'2022-11-01 21:47:39.867000','Description Aplication 26','26.webp',0.55,0,'Aplication 26',3,'Developer 3',0),(27,'2022-11-01 21:49:52.655000','Description Aplication 27','27.webp',1.15,0,'Aplication 27',2,'Developer 1',0),(28,'2022-11-01 21:50:12.103000','Description Aplication 28','28.webp',7.15,0,'Aplication 28',5,'Developer 10',1),(29,'2022-11-01 21:50:33.126000','Description Aplication 29','29.webp',5.5,0,'Aplication 29',5,'Developer 5',0),(30,'2022-11-01 21:50:48.953000','Description Aplication 30','30.webp',3.5,0,'Aplication 30',5,'Developer 5',0),(31,'2022-11-01 21:51:04.679000','Description Aplication 31','31.webp',2.5,0,'Aplication 31',1,'Developer 1',0),(32,'2022-11-01 21:51:20.133000','Description Aplication 32','32.webp',1.5,0,'Aplication 32',3,'Developer 11',1),(33,'2022-11-01 21:51:32.987000','Description Aplication 33','33.webp',3.5,0,'Aplication 33',3,'Developer 11',0),(34,'2022-11-01 21:51:47.884000','Description Aplication 34','34.webp',1.5,0,'Aplication 34',2,'Developer 12',0),(35,'2022-11-01 21:52:08.235000','Description Aplication 35','35.webp',1.85,0,'Aplication 35',2,'Developer 6',0),(36,'2022-11-01 21:52:26.650000','Description Aplication 37','37.webp',1.85,0,'Aplication 37',2,'Developer 7',0),(37,'2022-11-01 21:52:48.167000','Description Aplication 38','38.webp',2.45,0,'Aplication 38',5,'Developer 4',0),(38,'2022-11-01 21:53:07.447000','Description Aplication 39','39.webp',1.45,0,'Aplication 39',1,'Developer 6',0),(39,'2022-11-01 21:53:21.717000','Description Aplication 40','40.webp',1.45,0,'Aplication 40',1,'Developer 2',1),(40,'2022-11-01 21:53:35.676000','Description Aplication 41','41.webp',2.45,0,'Aplication 41',1,'Developer 3',0),(41,'2022-11-01 21:53:54.266000','Description Aplication 42','42.webp',2.65,0,'Aplication 42',2,'Developer 5',0),(42,'2022-11-01 21:54:07.362000','Description Aplication 43','43.webp',1.65,0,'Aplication 43',2,'Developer 1',0),(43,'2022-11-01 21:54:18.599000','Description Aplication 44','44.webp',1.65,0,'Aplication 44',2,'Developer 1',0),(44,'2022-11-01 21:54:28.853000','Description Aplication 45','45.webp',1.65,0,'Aplication 45',2,'Developer 1',0),(45,'2022-11-01 21:54:42.947000','Description Aplication 46','46.webp',5.65,0,'Aplication 46',1,'Developer 1',1),(46,'2022-11-01 21:55:02.192000','Description Aplication 47','47.webp',4.5,0,'Aplication 47',5,'Developer 10',0),(47,'2022-11-01 21:55:16.026000','Description Aplication 48','48.webp',3.5,0,'Aplication 48',5,'Developer 4',0),(48,'2022-11-01 21:55:37.662000','Description Aplication 49','49.webp',4.35,0,'Aplication 49',4,'Developer 2',0),(49,'2022-11-01 21:55:51.377000','Description Aplication 50','50.webp',1.35,0,'Aplication 50',4,'Developer 1',0);
/*!40000 ALTER TABLE `tbl_applications` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-01 22:33:11
