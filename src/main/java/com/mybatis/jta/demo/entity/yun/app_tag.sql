-- MySQL dump 10.13  Distrib 5.6.23, for Linux (x86_64)
--
-- Host: localhost    Database: ljyun_120_merchant
-- ------------------------------------------------------
-- Server version	5.6.23-log


--
-- Table structure for table `app_tag`
--

DROP TABLE IF EXISTS `app_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_tag` (
  `tag_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(50) NOT NULL DEFAULT '',
  `tag_type` tinyint(1) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
