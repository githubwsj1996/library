-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: library
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `publish` varchar(20) DEFAULT NULL,
  `pages` int DEFAULT NULL,
  `price` float(10,2) DEFAULT NULL,
  `bookcaseid` int DEFAULT NULL,
  `abled` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ieh6qsxp6q7oydadktc9oc8t2` (`bookcaseid`),
  CONSTRAINT `bookcase_id` FOREIGN KEY (`bookcaseid`) REFERENCES `bookcase` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'解忧杂货店','东野圭吾','南海出版公司',102,27.30,2,1),(2,'追风筝的人','卡勒德·胡赛尼','上海人民出版社',230,33.50,3,1),(3,'人间失格','太宰治','作家出版社',150,17.30,1,0),(4,'这就是二十四节气','高春香','海豚出版社',220,59.00,3,1),(5,'白夜行','东野圭吾','南海出版公司',300,27.30,4,1),(6,'摆渡人','克莱儿·麦克福尔','百花洲文艺出版社',225,22.80,1,1),(7,'暖暖心绘本','米拦弗特毕','湖南少儿出版社',168,131.60,5,1),(8,'天才在左疯子在右','高铭','北京联合出版公司',330,27.50,6,1),(9,'我们仨','杨绛','生活.读书.新知三联书店',89,17.20,7,1),(10,'活着','余华','作家出版社',100,13.00,1,1),(11,'水浒传','施耐庵','三联出版社',300,50.00,1,1),(12,'三国演义','罗贯中','三联出版社',300,50.00,2,1),(13,'红楼梦','曹雪芹','三联出版社',300,50.00,5,1),(14,'西游记','吴承恩','三联出版社',300,60.00,3,1),(50,'222','222','2222',22,222.00,7,1),(52,'111','111','1111',11,1111.00,1,1),(53,'111','111','1111',1111,111.00,NULL,1),(54,'1111','11111','11111',111,111.00,NULL,1),(57,'11111','111','1111',1111,12.00,NULL,1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookadmin`
--

DROP TABLE IF EXISTS `bookadmin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookadmin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(15) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookadmin`
--

LOCK TABLES `bookadmin` WRITE;
/*!40000 ALTER TABLE `bookadmin` DISABLE KEYS */;
INSERT INTO `bookadmin` VALUES (1,'admin1','123123'),(2,'admin2','222222'),(3,'zhangsan','000');
/*!40000 ALTER TABLE `bookadmin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookcase`
--

DROP TABLE IF EXISTS `bookcase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookcase` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookcase`
--

LOCK TABLES `bookcase` WRITE;
/*!40000 ALTER TABLE `bookcase` DISABLE KEYS */;
INSERT INTO `bookcase` VALUES (1,'社会'),(2,'情感'),(3,'国学'),(4,'推理'),(5,'绘画'),(6,'心理学'),(7,'传记');
/*!40000 ALTER TABLE `bookcase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookid` int DEFAULT NULL,
  `readerid` int DEFAULT NULL,
  `borrowtime` varchar(20) DEFAULT NULL,
  `returntime` varchar(20) DEFAULT NULL,
  `adminid` int DEFAULT NULL,
  `state` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `borrow_book_id_fk` (`bookid`),
  KEY `borrow_reader_id_fk` (`readerid`),
  KEY `borrow_bookadmin_id_fk` (`adminid`),
  CONSTRAINT `borrow_book_id_fk` FOREIGN KEY (`bookid`) REFERENCES `book` (`id`),
  CONSTRAINT `borrow_bookadmin_id_fk` FOREIGN KEY (`adminid`) REFERENCES `bookadmin` (`id`),
  CONSTRAINT `borrow_reader_id_fk` FOREIGN KEY (`readerid`) REFERENCES `reader` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` VALUES (96,1,3,'2020-05-10','2020-05-24',1,3),(100,14,4,'2020-05-11','2020-05-25',1,2),(101,2,3,'2020-05-12','2020-05-26',1,2),(102,2,3,'2020-05-12','2020-05-26',1,3),(103,3,3,'2020-05-12','2020-05-26',1,3),(104,4,3,'2020-05-12','2020-05-26',1,3),(105,10,3,'2020-05-12','2020-05-26',1,3),(106,1,4,'2020-05-12','2020-05-26',1,3),(107,10,4,'2020-05-12','2020-05-26',1,3),(108,1,4,'2020-05-13','2020-05-27',1,3),(109,1,3,'2020-05-13','2020-05-27',1,3),(110,1,3,'2020-05-15','2020-05-15',1,3),(111,11,3,'2020-05-15','2020-05-29',1,2),(112,14,3,'2020-05-15','2020-05-16',1,3),(113,6,3,'2020-05-16','2020-05-16',1,3),(114,1,3,'2020-05-16','2020-05-16',1,3),(115,9,4,'2020-05-18','2020-05-18',2,3);
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reader`
--

DROP TABLE IF EXISTS `reader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reader` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `cardid` varchar(20) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reader`
--

LOCK TABLES `reader` WRITE;
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
INSERT INTO `reader` VALUES (3,'zhangsan','123123','张三','131','001','男'),(4,'lisi','000000','李四','987655123','002','男'),(6,'haha2','1231232','哈哈','13567876541','546578987654345','女'),(13,'aaa','aaa','aa','123123','123123','女');
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `returnbook`
--

DROP TABLE IF EXISTS `returnbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `returnbook` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookid` int DEFAULT NULL,
  `readerid` int DEFAULT NULL,
  `returntime` varchar(20) DEFAULT NULL,
  `adminid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `returnbook_bookadmin_id_fk` (`adminid`),
  KEY `returnbook_book_id_fk` (`bookid`),
  KEY `returnbook_reader_id_fk` (`readerid`),
  CONSTRAINT `returnbook_book_id_fk` FOREIGN KEY (`bookid`) REFERENCES `book` (`id`),
  CONSTRAINT `returnbook_bookadmin_id_fk` FOREIGN KEY (`adminid`) REFERENCES `bookadmin` (`id`),
  CONSTRAINT `returnbook_reader_id_fk` FOREIGN KEY (`readerid`) REFERENCES `reader` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `returnbook`
--

LOCK TABLES `returnbook` WRITE;
/*!40000 ALTER TABLE `returnbook` DISABLE KEYS */;
INSERT INTO `returnbook` VALUES (22,1,3,'2020-5-12',1),(23,2,3,'2020-5-12',1),(24,4,3,'2020-5-12',1),(25,3,3,'2020-5-12',1),(26,10,3,'2020-05-12',1),(27,1,4,'2020-05-12',1),(28,10,4,'2020-05-12',1),(29,1,4,'2020-05-13',1),(30,1,3,'2020-05-13',1),(31,14,3,'2020-05-16',1),(32,6,3,'2020-05-16',1),(33,1,3,'2020-05-16',1),(34,9,4,'2020-05-18',2);
/*!40000 ALTER TABLE `returnbook` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-06 10:55:44
