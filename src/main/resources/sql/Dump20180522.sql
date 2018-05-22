-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: tattooparlor
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `appointment_id` int(11) NOT NULL AUTO_INCREMENT,
  `appointment_type` int(11) NOT NULL DEFAULT '0',
  `master_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `begining_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ending_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ordering_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`appointment_id`),
  KEY `fk_appointment_master_idx` (`master_id`),
  KEY `fk_appointment_client_idx` (`client_id`),
  KEY `fk_appointment_type_idx` (`appointment_type`),
  KEY `fk_appointment_status_idx` (`status`),
  CONSTRAINT `fk_appointment_client` FOREIGN KEY (`client_id`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointment_master` FOREIGN KEY (`master_id`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointment_status` FOREIGN KEY (`status`) REFERENCES `appointment_status` (`status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointment_type` FOREIGN KEY (`appointment_type`) REFERENCES `appointment_type` (`type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,0,7,10,'2018-04-20 06:00:00','2018-04-20 06:29:00','2018-05-16 17:04:55',0),(3,0,8,1,'2018-05-27 06:00:00','2018-05-27 06:29:00','2018-05-16 21:21:05',0),(5,0,7,1,'2018-05-22 07:00:00','2018-05-22 07:29:00','2018-05-17 10:43:43',0),(6,0,7,1,'2018-05-24 08:00:00','2018-05-24 08:29:00','2018-05-18 09:43:44',0),(7,0,8,1,'2018-05-29 06:30:00','2018-05-29 07:00:00','2018-05-18 09:47:24',0),(8,0,7,1,'2018-05-21 09:00:00','2018-05-21 09:30:00','2018-05-18 12:40:24',0),(9,0,7,1,'2018-05-31 06:00:00','2018-05-31 06:30:00','2018-05-18 12:41:53',0);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment_status`
--

DROP TABLE IF EXISTS `appointment_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment_status` (
  `status_id` int(11) NOT NULL,
  `status_name` varchar(45) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment_status`
--

LOCK TABLES `appointment_status` WRITE;
/*!40000 ALTER TABLE `appointment_status` DISABLE KEYS */;
INSERT INTO `appointment_status` VALUES (0,'appointed'),(1,'canceled'),(2,'failure_to_attend');
/*!40000 ALTER TABLE `appointment_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment_type`
--

DROP TABLE IF EXISTS `appointment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment_type` (
  `type_id` int(11) NOT NULL,
  `type_name` varchar(45) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment_type`
--

LOCK TABLES `appointment_type` WRITE;
/*!40000 ALTER TABLE `appointment_type` DISABLE KEYS */;
INSERT INTO `appointment_type` VALUES (0,'consultation'),(1,'tattooing'),(2,'piercing'),(3,'permanent_makeup');
/*!40000 ALTER TABLE `appointment_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id_comment` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(45) NOT NULL,
  `publication_id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  `publish_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_comment`),
  KEY `fk_comment_pub_idx` (`publication_id`),
  KEY `fk_comment_author_idx` (`author_id`),
  CONSTRAINT `fk_comment_author` FOREIGN KEY (`author_id`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_publ` FOREIGN KEY (`publication_id`) REFERENCES `publication` (`id_publication`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gender` (
  `gender_id` bit(1) NOT NULL,
  `gender_name` varchar(6) NOT NULL,
  PRIMARY KEY (`gender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES ('\0','female'),('','male');
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publication`
--

DROP TABLE IF EXISTS `publication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publication` (
  `id_publication` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` longtext NOT NULL,
  `content_type` bit(1) NOT NULL DEFAULT b'0' COMMENT '0 - means article\n1 - means photo',
  `author_id` int(11) NOT NULL,
  `publish_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `blocked` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id_publication`),
  UNIQUE KEY `id_article_UNIQUE` (`id_publication`),
  KEY `fk_author_id_idx` (`author_id`),
  CONSTRAINT `fk_author_id` FOREIGN KEY (`author_id`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publication`
--

LOCK TABLES `publication` WRITE;
/*!40000 ALTER TABLE `publication` DISABLE KEYS */;
INSERT INTO `publication` VALUES (6,'Eminem tattoos','image/gallery/eminem_tattoo_by_grenge.jpg','\0',7,'2018-04-18 14:31:05','\0'),(7,'McGregor tattoos','image/gallery/conor-mcgregors-tattoos.jpg','\0',1,'2018-04-18 14:31:44','\0'),(8,'Женские тату','Красивая женская татуировка на теле, отражает богатый внутренний мир и тонкую чувственную натуру. Мода на женские тату существовала во все времена, но особенно популярна стала в последние годы. Особенно эффектно смотрится рисунок на теле, который гармонирует с женским телом и подчеркивает её формы и прелесть, деланная профессиональной рукой мастера тату. Неудивительно, что фото девушек с тату – наиболее популярные и привлекательны в соцсетях. Каждому человеку присуще желание меняться. Особенно остро чувствуется такая потребность в переломные периоды жизни. Обычно это случается либо в моменты ощущения безумной радости, любви и благополучия, когда хочется на весь мир кричать о своем счастье, либо наоборот, в состоянии угнетающей стабильности и постоянства. В такой период жизни возникает просто дикая необходимость конкретных действий. Одно из них – сделать тату на теле, которое внесет яркие краски жизни и несомненно добавит положительных эмоций в зеркальном отражении. Самый простой способ преобразиться, не меняясь кардинально – это сделать тату. Многие представительницы прекрасного пола делают женские тату на предплечье, спине, руках и ногах. Как правило, человек интуитивно подчеркивает свои лучшие достоинства. Один из вариантов взволновать и удивить партнера – сделать интимную татуировку. Но решиться на такой шаг под силу не каждой леди. Только особо смелые дамы выбирают такой способ самовыражения. И никогда не жалеют об этом. Наоборот, многие решившиеся на этот храбрый поступок замечают, что красивая тату не только украшает, но и придает уверенности в себе, добавляет шарм и неповторимый шик ее собственнице. Нередко из рисунка на теле, можно легко сделать вывод о характере ее обладательницы. Какой рисунок выбрать для тату? Опытный мастер тату без проблем определит Ваш внутренний типаж и посоветует, какой рисунок лучше нанести, предложив предварительный эскиз. Тату, которые изображают цветы, ангелов, линии и стрелы – можно назвать женские, фото таких татуировок притягивают внимание на просторах интернета. Значение рисунка известно наверняка только самому автору, ведь каждый символ для каждого человека имеет свой смысл. Желание украсить свое тело оригинальным изображением возникает у девушек разного возраста. Представительницы молодого поколения, естественно, наиболее лояльны и изобретательны на эту тему. Но нередко встречаются девушки с tattoo разного возраста. Не важно какой у вас цвет глаз и какие интересы, главное для любой женщины, - быть яркой и неповторимой. Нас окружает яркий и многогранный мир, полный красок и эмоций, поэтому тату для девушек – необычный и в то же время исключительный способ выделиться и запомниться.Тату как тонкий намек даст понять кто ее обладательница: романтическая натура или неприступная принцесса, страстная роза или снежная королева… Важно правильно расшифровать ее посыл.','',8,'2018-05-04 08:52:52','\0'),(9,'Плечо. Белорусский герб','image/gallery/i6kFPQ8iSXI.jpg','\0',7,'2018-05-14 16:22:11','\0'),(10,'Car on the hand','image/gallery/UZuWxGgN5cM.jpg','\0',7,'2018-05-14 16:23:03','\0'),(11,'Испания','image/gallery/IMG_20170926_174134.jpg','\0',1,'2018-05-20 12:58:31','\0');
/*!40000 ALTER TABLE `publication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(45) NOT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (0,'admin'),(1,'master'),(2,'user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userblocked`
--

DROP TABLE IF EXISTS `userblocked`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userblocked` (
  `blocked_id` bit(1) NOT NULL,
  `blocked_name` varchar(7) NOT NULL,
  PRIMARY KEY (`blocked_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userblocked`
--

LOCK TABLES `userblocked` WRITE;
/*!40000 ALTER TABLE `userblocked` DISABLE KEYS */;
INSERT INTO `userblocked` VALUES ('\0','active'),('','blocked');
/*!40000 ALTER TABLE `userblocked` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(25) NOT NULL,
  `password` varchar(64) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `gender` bit(1) NOT NULL DEFAULT b'1',
  `userrole` int(11) NOT NULL DEFAULT '2',
  `register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `birth` date DEFAULT NULL,
  `blocked` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`userid`),
  UNIQUE KEY `userId_UNIQUE` (`login`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_gender_idx` (`gender`),
  KEY `fk_blocked_idx` (`blocked`),
  KEY `fk_role_idx` (`userrole`),
  CONSTRAINT `fk_blocked` FOREIGN KEY (`blocked`) REFERENCES `userblocked` (`blocked_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_gender` FOREIGN KEY (`gender`) REFERENCES `gender` (`gender_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role` FOREIGN KEY (`userrole`) REFERENCES `role` (`roleid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','36EE6BE24E5A4AD70F6E176DD1DA66B1810AFEF7A3A6588778066FB75F383E2C','Oleg','Rekuts','zimmers@rambler.ru','\0',0,'2018-04-05 15:53:08','2000-04-01','\0'),(7,'user','9F9CC29ABD84FC63241F1665F9CF992EE323156DD2A725C4FE98E994E842488C','Oleg','R','superolegash@yandex.ru','',1,'2018-04-05 16:01:27','1991-01-01','\0'),(8,'user1','65807EF872DD2E272E736705512E5B0DE2005A6B167336055B8B68CBAA950FB9','Барак','Обама','superolegash@ya.ru','',1,'2018-05-03 21:12:16','2018-04-29',''),(9,'user2','642E4F3EEF103D77E52C543E0F03790A0D1DC5394DAE633E8681354EE0ECA645','Просто','Пиночет','email@mail.com','',1,'2018-05-03 21:17:50','2018-02-05',''),(10,'user3','51E67A5D7D8635C8B5015419AFB78F04AAD59D5B1D9556500A652850DBD1F1BE','Ясер','Арафат','aha@blabla.by','',2,'2018-05-09 18:08:14','1945-05-09',''),(11,'user4','941AAD141556110122646DBBBB9A1A133E9AAB5F2DCB287D94EB4F9A7E6CB088','Ганс','Перельман','someuser@gmail.com','',2,'2018-05-20 22:25:41','2002-05-08','\0');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-22 13:55:47
