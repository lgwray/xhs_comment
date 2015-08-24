-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: my_test
-- ------------------------------------------------------
-- Server version	5.6.25

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
-- Table structure for table `sh_comment_catetory`
--

DROP TABLE IF EXISTS `sh_comment_catetory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sh_comment_catetory` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `parent` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sh_comment_catetory`
--

LOCK TABLES `sh_comment_catetory` WRITE;
/*!40000 ALTER TABLE `sh_comment_catetory` DISABLE KEYS */;
INSERT INTO `sh_comment_catetory` VALUES (1,'70周年',0),(2,'抗战70周年（老兵）',1),(3,'反法西斯胜利70周年',1),(4,'抗战',1),(5,'各国首脑出席93阅兵',1),(6,'体育',0),(7,'篮球',6),(8,'足球',6),(9,'奥运会',6),(10,'田径世锦赛',6),(11,'军事',0),(12,'军事',11),(13,'南海问题',11),(14,'中国军事',11),(15,'台海',11),(16,'国际',0),(17,'南非',16),(18,'中西教育',16),(19,'中日关系',16),(20,'日本通用',16),(21,'中美关系',16),(22,'朝鲜武器',16),(23,'安倍访华',16),(24,'数码',0),(25,'手机',24),(26,'电脑',24),(27,'旅游',0),(28,'中国游客消费',27),(29,'时政新闻',0),(30,'十九大（巡视组）',29),(31,'灾害',0),(32,'天津爆炸',31),(33,'曼谷爆炸',31),(34,'山阳山体滑坡',31),(35,'马航MH370',31),(36,'火灾',31),(37,'环保',0),(38,'空气质量',37),(39,'环境污染',37),(40,'社会',0),(41,'毒品',40),(42,'医疗体系',40),(43,'农民工',40),(44,'贪腐',40),(45,'留守儿童',40),(46,'油价',40),(47,'高考问题',40),(48,'减肥整容',40),(49,'拐卖妇女儿童',40),(50,'网络谣言',40),(51,'母爱',40),(52,'传销',40),(53,'房价',40),(54,'科技',0),(55,'科技通用',54),(56,'互联网',54),(57,'创业',54),(58,'财经',0),(59,'财经(需要在梳理)',58),(60,'国企改革',58),(61,'股市',58),(62,'人民币改版',58),(63,'人民币贬值',58),(64,'新三板',58),(65,'中国经济发展',58),(66,'理财安全',58),(67,'O2O',58),(68,'通用评论',0),(69,'全通用评论',68);
/*!40000 ALTER TABLE `sh_comment_catetory` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-24 22:04:01
