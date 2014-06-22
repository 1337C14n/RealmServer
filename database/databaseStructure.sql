/*
SQLyog Community v11.27 (64 bit)
MySQL - 5.5.37-0ubuntu0.14.04.1 : Database - chat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`chat` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `chat`;

/*Table structure for table `bans` */

DROP TABLE IF EXISTS `bans`;

CREATE TABLE `bans` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `reason` text,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `banee` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=latin1;

/*Data for the table `bans` */

insert  into `bans`(`id`,`name`,`reason`,`time`,`banee`) values (1,'x4n4th','test','2013-08-12 12:54:44',NULL),(2,'x4n4th','ZOMG','2013-08-12 12:54:44',NULL),(3,'x4n4th','becuase','2013-08-12 12:54:44',NULL),(4,'Konig_Wolf53','test','2013-08-12 12:54:44',NULL),(6,'x4n4th','Shenanigans','2013-08-12 13:06:31','x4n4th'),(7,'x4n4th','Auto Unban','2013-08-12 13:08:32','Server'),(8,'NoFaceSpirit','ZOMG YOUR BANNED','2013-08-12 14:21:43','x4n4th'),(9,'NoFaceSpirit','Auto Unban','2013-08-12 14:26:48','Server'),(10,'NoFaceSpirit','fuck off','2013-08-12 17:17:43','devore07'),(11,'nofacespirit','done','2013-08-12 17:18:09','devore07'),(12,'DEVORE07','YOU AINT FROM NEWCASLT U GYM PUSSY KUNT','2013-08-12 17:25:17','NoFaceSpirit'),(13,'devore07','test','2013-08-12 17:27:04','x4n4th'),(14,'devore07','Auto Unban','2013-08-12 17:27:15','Server'),(15,'devore07','YOUR A NEWCASTLE GYM PUSSY U KUNT','2013-08-12 17:27:31','NoFaceSpirit'),(16,'devore07','Auto Unban','2013-08-12 17:27:38','Server'),(17,'x4n4th','Cabbage','2013-08-12 21:13:13','astrozombieX'),(18,'x4n4th','Auto Unban','2013-08-12 21:13:37','Server'),(19,'fluffy4eva','Cabbage','2013-08-12 21:13:46','astrozombieX'),(20,'x4n4th','no mr plugin,','2013-08-12 21:13:58','Fluffy4eva'),(21,'fluffy4eva','Auto Unban','2013-08-12 21:14:34','Server'),(22,'fluffy4eva','Rawr','2013-08-12 21:14:34','astrozombieX'),(23,'x4n4th','i want to','2013-08-12 21:14:45','Fluffy4eva'),(24,'x4n4th','Test','2013-08-12 21:14:59','astrozombieX'),(25,'x4n4th','Auto Unban','2013-08-12 21:15:11','Server'),(26,'astrozombieX','hdks','2013-08-12 21:15:38','Fluffy4eva'),(27,'astrozombieX','hi','2013-08-12 21:16:11','Fluffy4eva'),(28,'Konig_Wolf53','hi','2013-08-12 21:16:36','Fluffy4eva'),(29,'konig_wolf53','this is getting old','2013-08-12 21:17:29','Fluffy4eva'),(30,'Konig_Wolf53','.','2013-08-12 21:21:03','Fluffy4eva'),(31,'konig_wolf53','im sorry','2013-08-12 21:21:36','Fluffy4eva'),(32,'Fluffy4eva','test','2013-08-12 21:24:20','x4n4th'),(33,'Fluffy4eva','Auto Unban','2013-08-12 21:24:42','Server'),(34,'x4n4th','testing','2013-09-05 14:24:03','x4n4th'),(35,'x4n4th','test','2013-09-05 15:05:30','Konig_Wolf53'),(36,'nate_Crafter',':D','2013-09-05 15:55:13','x4n4th'),(37,'nate_Crafter','Auto Unban','2013-09-05 15:55:19','Server'),(38,'nate_Crafter','asshole','2013-09-05 18:32:22','Konig_Wolf53'),(39,'nate_crafter','not an ass','2013-09-05 18:32:49','Konig_Wolf53'),(40,'devore07',':D','2013-09-06 15:11:13','x4n4th'),(41,'devore07','Auto Unban','2013-09-06 15:11:19','Server'),(42,'husky1996','relog','2013-09-06 16:17:21','x4n4th'),(43,'husky1996','Auto Unban','2013-09-06 16:17:25','Server'),(44,'Fluffy4eva','RELOG!','2013-09-06 17:34:26','x4n4th'),(45,'Fluffy4eva','Auto Unban','2013-09-06 17:34:27','Server'),(46,'x4n4th','test','2013-09-07 19:32:09','Konig_Wolf53'),(47,'x4n4th','test','2013-09-07 19:32:26','Konig_Wolf53'),(48,'iowahorsegirl','why can i not find, nor kick this this IowaHorseGirl','2013-09-14 19:58:04','Konig_Wolf53'),(49,'IowaHorseGirl','non existant','2013-09-14 19:58:58','Konig_Wolf53'),(50,'IowaHorseGirl','get out','2013-09-14 20:11:31','Fluffy4eva'),(51,'IowaHorseGirl','out','2013-09-14 20:12:19','Fluffy4eva'),(52,'x4n4th','test','2013-09-14 22:46:16','Konig_Wolf53'),(53,'x4n4th','test','2013-09-14 22:46:33','Konig_Wolf53'),(54,'seemlycraft','Unbanning seemlycraft','2013-09-17 20:50:13','x4n4th'),(55,'daniel_mcc','Ban appeal accepted and is being set to convict','2013-10-05 18:23:47','Konig_Wolf53'),(56,'djdevilmonkey','still a convict and causing problems. see Kenny\'s post','2013-10-14 13:30:16','devore07'),(57,'djdevilmonkey','overruled','2013-10-14 13:40:37','devore07'),(58,'XobiddyoX','language after numerous warnings. please appeal on the forums','2013-10-15 22:58:52','devore07'),(59,'djdevilmonkey','lack of convict work','2013-10-18 07:45:36','Konig_Wolf53'),(60,'djdevilmonkey','test','2013-10-18 07:46:09','Konig_Wolf53'),(61,'djdevilmonkey','lack of convict work','2013-10-18 07:46:15','Konig_Wolf53'),(62,'14evil','test','2013-10-27 14:41:07','Bugsbunnie'),(63,'25_to_life','spamming','2013-10-27 17:17:10','devore07'),(64,'25_to_life','test','2013-10-27 17:17:16','Konig_Wolf53'),(65,'25_to_life','test','2013-10-27 17:17:23','Konig_Wolf53'),(66,'jackman102','Griefing Appeal at 1337clan.com/forums','2013-10-31 00:08:49','ikilledkenny'),(67,'Triedstream251','Griefing. Appeal at 1337clan.com','2013-10-31 00:10:06','ikilledkenny'),(68,'awesomekid2013','Griefing. Appeal at 1337clan.com','2013-10-31 00:10:58','ikilledkenny'),(69,'awesomekid2013','Admin','2013-11-05 15:50:29','ikilledkenny'),(70,'Triedstream251','Admin','2013-11-05 15:50:48','ikilledkenny'),(71,'jackman102','Admin','2013-11-05 15:51:04','ikilledkenny'),(72,'Triedstream251','unbanned','2013-11-05 16:09:45','Konig_Wolf53'),(73,'Triedstream251','unbanned','2013-11-05 16:09:49','Konig_Wolf53'),(74,'awesomekid2013','unban','2013-11-05 16:11:15','Konig_Wolf53'),(75,'awesomekid2013','unban','2013-11-05 16:11:21','Konig_Wolf53'),(76,'poolson','kick','2013-11-17 21:41:21','x4n4th'),(77,'ImGoingInDry','change shit','2013-11-17 21:57:55','x4n4th'),(78,'jbisch15','Dont swear on our server asshat','2013-11-18 14:55:51','devore07'),(79,'Jbisch15','foul language','2013-11-18 15:05:25','Konig_Wolf53'),(80,'Jbisch15','foul language','2013-11-18 15:05:29','Konig_Wolf53'),(81,'Jbisch15','.','2013-11-18 15:05:45','Konig_Wolf53'),(82,'Jbisch15','.','2013-11-18 15:05:48','Konig_Wolf53'),(83,'xobiddyox','convict period','2013-11-19 22:36:18','Konig_Wolf53'),(84,'jbisch15','unban','2013-11-19 22:42:11','Konig_Wolf53'),(85,'dirtbikes95','Starting with new account','2013-11-20 17:57:43','Konig_Wolf53'),(86,'squirrelboy64','relog','2013-11-21 21:34:11','x4n4th'),(87,'poolson','Auto Unban','2013-11-26 15:23:51','Server'),(88,'poolson','.','2013-11-26 15:23:51','Konig_Wolf53'),(89,'poolson','.','2013-11-26 15:23:57','Konig_Wolf53'),(90,'poolson','.','2013-11-28 08:41:13','Konig_Wolf53'),(91,'poolson','.','2013-11-28 08:41:19','Konig_Wolf53'),(92,'dirtbikes95','.','2013-11-28 16:18:17','Konig_Wolf53'),(93,'ikilledkenny','no','2013-12-05 21:18:59','x4n4th'),(94,'ikilledkenny','bye','2013-12-05 21:19:46','x4n4th'),(95,'micky075','you said it','2013-12-06 16:10:26','x4n4th'),(96,'ikilledkenny','dammit dan','2013-12-06 17:37:31','ikilledkenny'),(97,'ikilledkenny','.','2013-12-06 17:45:07','Konig_Wolf53'),(98,'ikilledkenny','.','2013-12-06 17:45:31','Konig_Wolf53'),(99,'micky075','Auto Unban','2013-12-08 18:26:26','Server'),(100,'ShiiFT_AquaaaH','relog pls','2013-12-08 18:57:04','x4n4th'),(101,'ShiiFT_AquaaaH','Auto Unban','2013-12-10 18:10:18','Server'),(102,'XxToYxJeTTxX','unban','2013-12-11 20:56:00','x4n4th'),(103,'djdevilmonkey','You know why.','2013-12-25 20:29:48','ikilledkenny'),(104,'djdevilmonkey','You know why.','2013-12-25 20:29:59','ikilledkenny'),(105,'djdevilmonkey','Test','2013-12-25 20:30:51','ikilledkenny'),(106,'djdevilmonkey','You know why.','2013-12-25 20:30:56','ikilledkenny'),(107,'djdevilmonkey','No.','2013-12-25 20:33:45','ikilledkenny'),(108,'djdevilmonkey','unabn','2013-12-25 20:35:14','x4n4th'),(109,'djdevilmonkey','banning this guy','2013-12-25 20:36:19','x4n4th'),(110,'Midnightgoddess1','alt account of dj','2013-12-25 20:40:36','x4n4th'),(111,'x4n4th','lawls','2013-12-25 20:45:07','x4n4th'),(112,'x4n4th','unban','2013-12-25 20:45:35','x4n4th'),(113,'husky1996','THAT\'S MY LINE','2014-01-16 22:42:20','ikilledkenny'),(114,'devore07','REASONS','2014-01-21 13:47:47','devore07'),(115,'devore07','unban','2014-01-21 13:57:10','x4n4th'),(116,'squirrelboy64','Auto Unban','2014-01-21 20:50:11','Server'),(117,'djdevilmonkey','griefing','2014-01-22 22:31:31','devore07'),(118,'ikilledkenny','ASSHAT','2014-01-22 23:57:09','devore07'),(119,'ikilledkenny','Auto Unban','2014-01-22 23:57:14','Server'),(120,'ImGoingInDry','Auto Unban','2014-01-23 01:16:03','Server'),(121,'ImGoingInDry','unban','2014-01-23 01:16:03','ikilledkenny'),(122,'ImGoingInDry','test','2014-01-23 01:16:09','ikilledkenny'),(123,'MidnightGoddess1','unban','2014-01-24 01:24:43','ikilledkenny'),(124,'dirtbikes95','Bypassing mute','2014-01-24 20:42:40','Konig_Wolf53'),(125,'dirtbikes95','unban','2014-01-24 21:03:40','Konig_Wolf53'),(126,'dirtbikes95','disrespecting staff','2014-01-24 21:04:00','Konig_Wolf53'),(127,'dirtbikes95','good','2014-01-25 13:40:07','Konig_Wolf53'),(128,'Stock85','unban','2014-02-02 12:31:44','x4n4th'),(129,'Stock85','lawl','2014-02-04 16:44:05','x4n4th'),(130,'Stock85','lawl','2014-02-04 16:44:09','x4n4th'),(131,'Stock85','yeah he is banned','2014-02-04 16:45:34','x4n4th'),(132,'dabuilderguy','posting a gore link in chat','2014-02-09 15:58:53','devore07'),(133,'dirtbikes95','caps raging after warning. talked to fluffy about it','2014-02-09 17:38:28','devore07'),(134,'a_biohazard29','Check dirtbikes95','2014-02-09 18:05:50','devore07'),(135,'A_Biohazard29','Check dirtbikes95','2014-02-09 18:06:15','devore07'),(136,'dirtbikes95','Auto Unban','2014-02-16 19:13:09','Server'),(137,'maxkeepssinging','Vulgar build. Appeal at www.1337clan.com/forums','2014-02-17 00:54:56','ikilledkenny'),(138,'jksmith25','spamming inappropriate stuff in chat','2014-02-20 16:45:51','devore07'),(139,'jksmith25','spamming obscenitites in chat','2014-02-20 16:46:37','devore07'),(140,'jksmith25','spamming obscenitites in chat','2014-02-20 16:46:42','devore07'),(141,'jksmith25','spamming obscenitites in chat','2014-02-20 16:47:09','devore07'),(142,'jksmith25','spamming obscenitites in chat','2014-02-20 16:47:14','devore07'),(143,'x4n4th','testing','2014-02-20 16:54:34','x4n4th'),(144,'x4n4th','Auto Unban','2014-02-20 16:54:45','Server'),(145,'Jksmith25','Auto Unban','2014-02-21 16:50:52','Server'),(146,'A_Biohazard29','Auto Unban','2014-02-22 16:36:29','Server'),(147,'icebear242','Stealing town livestock from staffmembers town.','2014-02-24 00:30:16','Konig_Wolf53'),(148,'NotoriousLlama','We don\'t want your anger here.','2014-02-24 16:18:16','ikilledkenny'),(149,'NotoriousLlama','We don\'t want your anger here.','2014-02-24 16:18:30','ikilledkenny'),(150,'notoriousllama','unban','2014-02-24 16:30:22','devore07'),(151,'NotoriousLlama','Unban because Konig and Dev want to make him rage more.','2014-02-24 16:35:20','ikilledkenny'),(152,'notoriousllama','unban','2014-02-24 16:47:10','devore07'),(153,'ikilledkenny','pink','2014-02-25 01:37:20','husky1996'),(154,'ikilledkenny','Auto Unban','2014-02-25 01:37:26','Server'),(155,'ikilledkenny','Relog and you\'ll have your commands!','2014-02-25 02:25:50','husky1996'),(156,'ikilledkenny','as of about an hour ago','2014-02-25 02:26:42','husky1996'),(157,'Philip69999','advertising','2014-02-25 17:09:15','husky1996'),(158,'A_Biohazard29','using alt to bypass ban and mute','2014-02-26 13:44:57','devore07'),(159,'A_Biohazard29','unban','2014-02-26 13:59:33','devore07'),(160,'dirtbikes95','unban','2014-02-26 14:00:50','devore07'),(161,'dirtbikes95','unban','2014-02-26 14:00:58','devore07'),(162,'dirtbikes95','permabann','2014-02-26 14:01:03','devore07'),(163,'dirtbikes95','correcting reason','2014-02-26 14:04:01','Konig_Wolf53'),(164,'dirtbikes95','Bypassing mute for 2nd time','2014-02-26 14:04:11','Konig_Wolf53'),(165,'KSU_kid','malicious griefing of Peace. Please appeal on the forums','2014-02-26 19:15:37','devore07'),(166,'prince_hockey','we dont want your language here anyways asshat','2014-03-02 17:33:17','devore07'),(167,'ImGoingInDry','writing bad words','2014-03-03 00:26:33','devore07'),(168,'imgoingindry','Auto Unban','2014-03-03 00:29:17','Server'),(169,'imgoingindry','unban','2014-03-03 00:29:17','devore07'),(170,'imgoingindry','unban','2014-03-03 00:29:21','devore07'),(171,'ImGoingInDry','unban because alex and ryan are morons','2014-03-03 00:29:23','ikilledkenny'),(172,'ImGoingInDry','unban because alex and ryan are morons','2014-03-03 00:29:38','ikilledkenny'),(173,'ImGoingInDry','unban from factions','2014-03-03 00:30:16','ikilledkenny'),(174,'ImGoingInDry','DAN YOUR SYSTEM IS BUGGY AS FUCK','2014-03-03 00:30:37','ikilledkenny'),(175,'stock85','Unban: Appeal approved','2014-03-05 10:06:06','Fluffy4eva'),(176,'A_Biohazard29','Staff consensus for a permanent ban based on PVP logging and past bahavior','2014-03-06 18:59:20','Fluffy4eva'),(177,'icebear242','Auto Unban','2014-03-07 15:54:15','Server'),(178,'icebear242','UNBAN, previous ban was supposed to be a tempban','2014-03-07 15:54:15','Fluffy4eva'),(179,'icebear242','unban','2014-03-07 16:27:46','x4n4th'),(180,'jackman102','Auto Unban','2014-03-07 18:02:53','Server'),(181,'midnightgoddess1','killing staff during official duty','2014-03-10 19:09:12','Konig_Wolf53'),(182,'midnightgoddess1','killing staff during official duty','2014-03-10 19:09:47','Konig_Wolf53'),(183,'Midnightgoddess1','killing staff on official business','2014-03-10 19:10:37','Konig_Wolf53'),(184,'MidnightGoddess1','unban','2014-03-10 19:56:31','Konig_Wolf53'),(185,'MidnightGoddess1','killing staff on duty','2014-03-10 19:56:51','Konig_Wolf53'),(186,'Midnightgoddess1','Auto Unban','2014-03-12 18:02:31','Server'),(187,'mikeyolom','Rule 4, heavy water griefing','2014-03-30 20:22:40','devore07'),(188,'tom1448','spam','2014-04-09 11:13:57','devore07'),(189,'Midnightgoddess1','bullying','2014-04-18 19:08:55','devore07');

/*Table structure for table `changedPerms` */

DROP TABLE IF EXISTS `changedPerms`;

CREATE TABLE `changedPerms` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `group_id` int(32) DEFAULT NULL,
  `player_id` int(32) DEFAULT NULL,
  `server_id` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `changedPerms` */

/*Table structure for table `channels` */

DROP TABLE IF EXISTS `channels`;

CREATE TABLE `channels` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `owners` text NOT NULL,
  `prefixenabled` tinyint(1) NOT NULL,
  `unfilteredname` varchar(32) DEFAULT NULL,
  `prefix` varchar(32) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

/*Data for the table `channels` */

insert  into `channels`(`id`,`name`,`owners`,`prefixenabled`,`unfilteredname`,`prefix`,`password`) values (1,'alathar','Morphrana',1,'alathar','alathar',NULL),(2,'animals','ikilledkenny',1,'animals','animals',NULL),(3,'asecret','Midnightgoddess1',1,'asecret','asecret',NULL),(4,'berg','Konig_Wolf53',1,'berg','berg',NULL),(5,'campgrid','Midnightgoddess1',1,'campgrid','campgrid',NULL),(6,'cuss','dirtbikes95',1,'cuss','cuss',NULL),(7,'dirt','micky075',1,'dirt','dirt',NULL),(8,'dirtidiot','micky075',1,'dirtidiot','dirtidiot',NULL),(9,'dirtrage','ShiiFT_AquaaaH',1,'dirtrage','dirtrage',NULL),(10,'dirtrage95','Midnightgoddess1',1,'dirtrage95','dirtrage95',NULL),(11,'djsucks','Morphrana',1,'djsucks','djsucks',NULL),(12,'dog','micky075',1,'dog','dog',NULL),(13,'dsfalk','x4n4th',1,'dsfalk','dsfalk',NULL),(14,'faction','Midnightgoddess1',1,'faction','faction',NULL),(15,'ffs','husky1996',1,'ffs','ffs',NULL),(16,'fuckall','Morphrana',1,'fuckall','fuckall',NULL),(17,'g','x4n4th',0,'g','G',NULL),(18,'general','Midnightgoddess1',1,'general','general',NULL),(19,'global','Midnightgoddess1',1,'global','global',NULL),(20,'hi123','devore07',1,'hi123','hi123',NULL),(21,'howsautumn','mrbossman56',1,'howsautumn','howsautumn',NULL),(22,'jr.mod','dirtbikes95',1,'jr.mod','jr.mod',NULL),(23,'kenny','ikilledkenny',1,'kenny','kenny',NULL),(24,'lawl','x4n4th',1,'lawl','lawl',NULL),(25,'lel','squirrelboy64',1,'lel','lel',NULL),(26,'maidenway','dirtbikes95',1,'maidenway','maidenway',NULL),(27,'math','Morphrana',1,'math','math',NULL),(28,'micky','micky075',1,'micky','micky',NULL),(29,'micky075','micky075',1,'micky075','micky075',NULL),(30,'military','dirtbikes95',1,'military','military',NULL),(31,'new','x4n4th',1,'new','new',NULL),(32,'newchannel','x4n4th',1,'newchannel','nc',NULL),(33,'nomid','william6299',1,'nomid','nomid',NULL),(34,'pripyat','Midnightgoddess1',1,'pripyat','pripyat',NULL),(35,'pvp','Midnightgoddess1',1,'pvp','pvp',NULL),(36,'rage','ShiiFT_AquaaaH',1,'rage','rage',NULL),(37,'scream','ShiiFT_AquaaaH',1,'scream','scream',NULL),(38,'secretsg','djdevilmonkey',1,'secretsg','secretsg',NULL),(39,'staff','Konig_Wolf53',1,'staff','staff',NULL),(40,'stafftalk','devore07',1,'stafftalk','stafftalk',NULL),(41,'t','djdevilmonkey',1,'t','t',NULL),(42,'test','x4n4th',1,'test','test',NULL),(43,'thetalk','Midnightgoddess1',1,'thetalk','thetalk',NULL),(44,'tt','Midnightgoddess1',1,'tt','tt',NULL);

/*Table structure for table `chatlog` */

DROP TABLE IF EXISTS `chatlog`;

CREATE TABLE `chatlog` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `channel` varchar(32) NOT NULL,
  `player` varchar(32) NOT NULL,
  `text` varchar(256) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=latin1;

/*Data for the table `chatlog` */

insert  into `chatlog`(`id`,`channel`,`player`,`text`,`time`) values (1,'g','x4n4th','This is a test',NULL),(2,'g','x4n4th','This is a test of the new logging','2014-03-06 00:00:00'),(3,'g','x4n4th','how does it work?','2014-03-06 00:00:00'),(4,'g','x4n4th','i wonder if this is being logged','2014-03-06 00:00:00'),(5,'g','x4n4th','it is not thow exceptions yet','2014-03-06 00:00:00'),(6,'g','x4n4th','interesting','2014-03-06 00:00:00'),(7,'g','x4n4th',':D','2014-03-06 00:00:00'),(8,'g','x4n4th','hAKJSDHJKhajsdhasT','2014-03-06 00:00:00'),(9,'g','x4n4th','haskjdhKJAHDKJHajkd','2014-03-06 00:00:00'),(10,'g','x4n4th','KJASHDKJhaskdhKJASHD','2014-03-06 00:00:00'),(11,'g','x4n4th','jahdskjHASKLDHlkjahsd','2014-03-06 00:00:00'),(12,'g','x4n4th','kjADKJHkajshdeAT','2014-03-06 00:00:00'),(13,'g','x4n4th','this a second test','2014-03-06 21:09:03'),(14,'g','x4n4th',':D','2014-03-06 21:09:04'),(15,'g','x4n4th','does it work now?','2014-03-06 21:09:07'),(16,'g','x4n4th','....','2014-03-06 21:09:09'),(17,'g','x4n4th','LSADLKASLKDALJSD','2014-03-06 21:09:10'),(18,'g','x4n4th','SALSDLAKSJDL;JALSKD','2014-03-06 21:09:12'),(19,'g','x4n4th','ASLKDFLAKSJDFLJASD','2014-03-06 21:09:13'),(20,'g','x4n4th','ALSKFLKASDJFKLJAKSDF','2014-03-06 21:09:14'),(21,'g','x4n4th','LKSDFLKAJSDLFKJASDF','2014-03-06 21:09:15'),(22,'test1','x4n4th','asd','2014-03-06 22:01:27'),(23,'test1','x4n4th',':D','2014-03-06 22:01:29'),(24,'test1','x4n4th','i am in channel test1!','2014-03-06 22:01:40'),(25,'test1','x4n4th','YAY!','2014-03-06 22:01:42'),(26,'g','x4n4th','.','2014-03-06 22:05:43'),(27,'g','x4n4th','.asdasd','2014-03-06 23:06:09'),(28,'g','x4n4th',':D','2014-03-06 23:06:10'),(29,'g','x4n4th','dsf','2014-03-06 23:06:55'),(30,'g','x4n4th','sdfasdf','2014-03-06 23:11:34'),(31,'g','devore07','hi','2014-03-06 23:28:51'),(32,'hi123','devore07','hi','2014-03-06 23:28:47'),(33,'g','x4n4th','hi','2014-03-06 23:34:14'),(34,'g','devore07','HI!','2014-03-06 23:34:17'),(35,'g','x4n4th','Hi','2014-03-07 14:56:10'),(36,'lawl','x4n4th','.','2014-03-07 14:56:44'),(37,'g','x4n4th','sdf','2014-03-07 14:56:48'),(38,'lawl','x4n4th','asd','2014-03-07 14:56:46'),(39,'g','x4n4th','..','2014-06-01 19:59:36'),(40,'g','x4n4th','l,','2014-06-01 20:01:51'),(41,'g','x4n4th','.','2014-06-13 19:19:39'),(42,'g','x4n4th','.','2014-06-13 19:22:53'),(43,'g','x4n4th','.','2014-06-13 19:27:59'),(44,'g','x4n4th','asd','2014-06-13 19:28:54'),(45,'g','x4n4th','.','2014-06-13 19:29:50'),(46,'g','x4n4th','.','2014-06-13 21:04:14'),(47,'g','devore07','hi','2014-06-13 21:04:18'),(48,'g','husky1996','.','2014-06-13 21:04:20'),(49,'g','devore07','it\'s broken','2014-06-13 21:04:46'),(50,'g','devore07','talk','2014-06-13 21:05:52'),(51,'g','husky1996','<3','2014-06-13 21:06:07'),(52,'g','husky1996','\"I dont have permissions.\" \"Well that\'s because you don\'t have permissions.\"','2014-06-13 21:07:14'),(53,'g','devore07','permission?','2014-06-13 21:07:39'),(54,'g','x4n4th','.','2014-06-13 21:08:46'),(55,'g','devore07','hi','2014-06-13 21:08:52'),(56,'g','husky1996',':(','2014-06-13 21:10:20'),(57,'g','husky1996','rekt','2014-06-13 21:10:41'),(58,'g','x4n4th','asd','2014-06-14 13:04:59'),(59,'lawl','x4n4th','hellow lawl','2014-06-14 13:10:44'),(60,'g','x4n4th','.','2014-06-14 15:07:31'),(61,'g','Concurrency','.','2014-06-14 15:12:49'),(62,'g','Concurrency','d','2014-06-14 15:13:06'),(63,'g','Concurrency','.','2014-06-14 15:13:16'),(64,'g','Concurrency','.','2014-06-14 15:13:22'),(65,'g','Concurrency','.','2014-06-14 15:13:29'),(66,'g','Concurrency','.','2014-06-14 15:13:40'),(67,'g','Concurrency','.','2014-06-14 15:13:46'),(68,'g','Concurrency','.','2014-06-14 15:13:53'),(69,'g','Concurrency','.','2014-06-14 15:17:47'),(70,'g','Concurrency',':D','2014-06-14 15:17:52'),(71,'g','x4n4th','.','2014-06-15 19:23:52'),(72,'g','x4n4th','dsdf','2014-06-15 19:23:58'),(73,'g','x4n4th','asdflsaf','2014-06-15 19:24:00'),(74,'g','x4n4th','.','2014-06-15 19:24:27'),(75,'g','x4n4th','.','2014-06-15 19:30:57'),(76,'g','x4n4th','sad','2014-06-15 19:31:08'),(77,'g','x4n4th','.','2014-06-15 19:31:21'),(78,'g','x4n4th','.','2014-06-15 19:36:05'),(79,'g','x4n4th','.','2014-06-15 20:37:18'),(80,'g','x4n4th','.','2014-06-15 20:37:47'),(81,'g','x4n4th','.','2014-06-15 20:37:51'),(82,'g','x4n4th','.','2014-06-15 20:38:31'),(83,'g','x4n4th','.','2014-06-15 20:38:37'),(84,'g','x4n4th','.','2014-06-15 20:38:40'),(85,'g','x4n4th','.','2014-06-15 20:38:41'),(86,'g','x4n4th','asdsa','2014-06-18 17:14:28'),(87,'g','x4n4th','alskdasd','2014-06-18 17:14:31'),(88,'g','x4n4th','?','2014-06-18 17:14:35'),(89,'g','x4n4th','.','2014-06-18 17:14:58'),(90,'g','x4n4th','.','2014-06-18 17:18:50'),(91,'g','x4n4th','asd','2014-06-18 17:19:01'),(92,'g','x4n4th','asd','2014-06-18 17:19:02'),(93,'g','x4n4th','ah my prefix didnt set :(','2014-06-18 17:19:10'),(94,'g','x4n4th','./cry','2014-06-18 17:19:14'),(95,'g','x4n4th','why noy?','2014-06-18 17:19:25'),(96,'g','x4n4th','.','2014-06-18 17:20:46'),(97,'g','x4n4th','there we go?','2014-06-18 17:20:53'),(98,'g','x4n4th','.','2014-06-18 17:21:00'),(99,'g','x4n4th','this server doesnt have it','2014-06-18 17:21:06'),(100,'g','x4n4th','i think i need to set all','2014-06-18 17:21:12'),(101,'g','x4n4th','.','2014-06-18 17:25:25'),(102,'g','x4n4th','.','2014-06-18 17:25:38');

/*Table structure for table `groups` */

DROP TABLE IF EXISTS `groups`;

CREATE TABLE `groups` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `rank` int(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `groups` */

insert  into `groups`(`id`,`name`,`rank`) values (1,'Admin',0),(2,'*',0),(3,'SuperAdmin',0),(4,'New',1),(5,'Miner',2),(6,'Builder',3),(7,'Designer',4),(8,'Architect',5),(9,'Engineer',6),(10,'Veteran',7),(11,'JrMod',8),(12,'Convict',0),(13,'Altruist',9),(14,'TempMod',0),(15,'Mod',0),(16,'GlobalMod',0),(17,'Iron',0),(18,'Gold',0),(19,'Diamond',0),(20,'Emerald',0);

/*Table structure for table `kicks` */

DROP TABLE IF EXISTS `kicks`;

CREATE TABLE `kicks` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `reason` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `kicks` */

/*Table structure for table `log` */

DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `level` varchar(32) DEFAULT NULL,
  `text` text,
  `date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `log` */

/*Table structure for table `mutes` */

DROP TABLE IF EXISTS `mutes`;

CREATE TABLE `mutes` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `reason` text,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `mutee` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `mutes` */

insert  into `mutes`(`id`,`name`,`reason`,`time`,`mutee`) values (8,'x4n4th','hes an ass','2013-08-12 14:15:49','x4n4th'),(9,'x4n4th','rsdf','2013-08-12 14:16:01','x4n4th'),(10,'devore07','testing','2013-08-12 17:19:55','x4n4th'),(11,'morphrana','spamming \"meow\" in chat','2014-01-22 18:16:04','devore07'),(12,'dirtbikes95','asshole','2014-01-24 20:32:40','Konig_Wolf53'),(13,'kastlekrusher','caps','2014-02-08 00:59:55','thezerbler'),(14,'jksmith25','foul language in chat','2014-02-19 17:27:17','devore07'),(15,'jksmith25','i am not a part of this yet.. kenny just told me someone was trying to buy a town','2014-02-19 17:44:33','Fluffy4eva'),(16,'midnightgoddess1','my name is husky','2014-03-06 18:35:35','husky1996');

/*Table structure for table `permissionnodes` */

DROP TABLE IF EXISTS `permissionnodes`;

CREATE TABLE `permissionnodes` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `node` varchar(64) DEFAULT NULL,
  `groupid` int(3) DEFAULT NULL,
  `serverid` int(3) DEFAULT NULL,
  `playerid` int(9) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `groupidcon` (`groupid`),
  KEY `playeridcon` (`playerid`),
  KEY `serveridcon` (`serverid`),
  CONSTRAINT `groupidcon` FOREIGN KEY (`groupid`) REFERENCES `groups` (`id`),
  CONSTRAINT `playeridcon` FOREIGN KEY (`playerid`) REFERENCES `players` (`Id`),
  CONSTRAINT `serveridcon` FOREIGN KEY (`serverid`) REFERENCES `servers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=444 DEFAULT CHARSET=latin1;

/*Data for the table `permissionnodes` */

insert  into `permissionnodes`(`id`,`node`,`groupid`,`serverid`,`playerid`) values (15,'multiverse.access.Survival',4,1,7),(16,'multiverse.access.Survival_nether',4,1,7),(17,'multiverse.access.museum',4,1,7),(18,'multiverse.access.mining',4,1,7),(19,'multiverse.access.Creative',4,1,7),(20,'multiverse.access.Anarchy',4,1,7),(21,'essentials.kick.notify',4,1,7),(22,'essentials.ban.notify',4,1,7),(23,'towny.wild.*',4,1,7),(24,'essentials.teleport.cooldown.bypass',4,1,7),(25,'essentials.spawn',4,1,7),(26,'essentials.list',4,1,7),(27,'herochat.speak.*',4,1,7),(28,'herochat.join.Global',4,1,7),(29,'herochat.join.Local',4,1,7),(30,'essentails.who',4,1,7),(31,'essentials.motd',4,1,7),(32,'essentials.help',4,1,7),(33,'essentials.sethome',4,1,7),(34,'essentials.home',4,1,7),(35,'essentials.kit',4,1,7),(36,'essentials.kits.starter',4,1,7),(37,'essentials.kits.done',4,1,7),(38,'essentials.ping',4,1,7),(39,'essentials.warp',4,1,7),(40,'essentials.warps.shop',4,1,7),(41,'modifyworld.*',4,1,7),(42,'mbr.collect',4,1,7),(43,'mbr.user.collect.normal',4,1,7),(44,'mbr.user.collect.blaze',4,1,7),(45,'mbr.user.collect.chicken',4,1,7),(46,'mbr.user.collect.creeper',4,1,7),(47,'mbr.user.collect.enderdragon',4,1,7),(48,'mbr.user.collect.enderman',4,1,7),(49,'mbr.user.collect.ghast',4,1,7),(50,'mbr.user.collect.giant',4,1,7),(51,'mbr.user.collect.ironGolem',4,1,7),(52,'mbr.user.collect.monster',4,1,7),(53,'mbr.user.collect.ocelot',4,1,7),(54,'mbr.user.collect.pigZombie',4,1,7),(55,'mbr.user.collect.player',4,1,7),(56,'mbr.user.collect.silverfish',4,1,7),(57,'mbr.user.collect.skeleton',4,1,7),(58,'mbr.user.collect.spider',4,1,7),(59,'mbr.user.collect.tamedCat',4,1,7),(60,'mbr.user.collect.tamedWolf',4,1,7),(61,'mbr.user.collect.wither',4,1,7),(62,'mbr.user.collect.witherSkeleton',4,1,7),(63,'mbr.user.collect.wolf',4,1,7),(64,'mbr.user.collect.zombie',4,1,7),(65,'multiverse.teleport.self.*',4,1,7),(66,'multiverse.portal.access.*',4,1,7),(67,'sg.arena.join',4,1,7),(68,'sg.arena.vote',4,1,7),(69,'sg.lobby.join',4,1,7),(70,'lwc.protect',5,1,7),(71,'cenotaph.use',5,1,7),(72,'cenotaph.freechest',5,1,7),(73,'cenotaph.large',5,1,7),(74,'cenotaph.lockette',5,1,7),(75,'cenotaph.sign',5,1,7),(76,'cenotaph.freesign',5,1,7),(77,'cenotaph.freelockettesign',5,1,7),(78,'cenotaph.quickloot',5,1,7),(79,'cenotaph.cmd.cenotaphfind',5,1,7),(80,'towny.wild.*',5,1,7),(81,'towny.spawntp',5,1,7),(82,'towny.nation.new',5,1,7),(83,'towny.town.*',5,1,7),(84,'controller.hub',5,1,7),(85,'herochat.join.*',5,1,7),(86,'herochat.leave.*',5,1,7),(87,'herochat.pm',5,1,7),(88,'craftbook.mech.gate',5,1,7),(89,'craftbook.mech.gate.use',5,1,7),(90,'craftbook.mech.elevator',5,1,7),(91,'craftbook.mech.elevator.use',5,1,7),(92,'falsebook.ic.detection',5,1,7),(93,'falsebook.destroy.blocks',5,1,7),(94,'essentials.mail',5,1,7),(95,'essentials.mail.send',5,1,7),(96,'essentials.help',5,1,7),(97,'essentials.pay',5,1,7),(98,'essentials.tpa',5,1,7),(99,'essentials.tpaccept',5,1,7),(100,'essentials.tpdeny',5,1,7),(101,'essentials.tptoggle',5,1,7),(102,'essentials.back',5,1,7),(103,'essentials.tpahere',5,1,7),(104,'essentials.warps.shop',5,1,7),(105,'essentials.warps.dock',5,1,7),(106,'essentials.warps.netherarena',5,1,7),(107,'essentials.warps.mall',5,1,7),(108,'essentials.warps.arena',5,1,7),(109,'mcmmo.chat.partychat',5,1,7),(110,'mcmmo.skills.*',5,1,7),(111,'mcmmo.commands.ability',5,1,7),(112,'mcmmo.ability.*',5,1,7),(113,'mbr.collect',5,1,7),(114,'frameProtect.protect.*',5,1,7),(115,'frameProtect.place.*',5,1,7),(116,'frameProtect.info.*',5,1,7),(117,'permissions.user.promote.default',11,1,7),(118,'permissions.user.demote.default',11,1,7),(119,'coreprotect.*',11,1,7),(120,'essentials.mute',11,1,7),(121,'essentials.kick',11,1,7),(122,'essentials.kick.notify',11,1,7),(123,'essentials.signs.color',11,1,7),(124,'-towny.command.townyadmin.reset',11,1,7),(125,'towny.admin',11,1,7),(126,'towny.admin.town',11,1,7),(127,'towny.admin.nation',11,1,7),(128,'towny.admin.set',11,1,7),(129,'towny.admin.toggle',11,1,7),(130,'towny.admin.backup',11,1,7),(131,'towny.admin.unclaim',11,1,7),(132,'multiverse.teleport.self.*',11,1,7),(133,'multiverse.portal.access.*',11,1,7),(134,'herochat.*',11,1,7),(135,'essentials.tp',11,1,7),(136,'essentials.tpo',11,1,7),(137,'essentials.tphere',11,1,7),(138,'essentials.tpohere',11,1,7),(139,'essentials.invsee',11,1,7),(140,'towny.claimed.*',13,1,7),(141,'essentials.fly',13,1,7),(142,'lwc.admin',14,1,7),(143,'coreprotect.*',14,1,7),(144,'essentials.ban',14,1,7),(145,'essentials.ban.notify',14,1,7),(146,'essentials.ban.offline',14,1,7),(147,'essentials.unban',14,1,7),(148,'essentials.tempban',14,1,7),(149,'essentials.tempban.offline',14,1,7),(150,'redstoneclockdetector.*',14,1,7),(151,'-cenotaph.use',1,1,7),(152,'-cenotaph.freechest',1,1,7),(153,'-cenotaph.large',1,1,7),(154,'-cenotaph.lockette',1,1,7),(155,'-cenotaph.sign',1,1,7),(156,'-cenotaph.freesign',1,1,7),(157,'-cenotaph.freelockettesign',1,1,7),(158,'-cenotaph.quickloot',1,1,7),(159,'-cenotaph.cmd.cenotaphfind',1,1,7),(160,'-playerheads.canloosehead',1,1,7),(161,'*',1,6,7),(162,'multiverse.access.Donor',17,1,7),(163,'noair.air',17,1,7),(164,'essentials.warp',17,1,7),(165,'essentials.delhome',17,1,7),(166,'essentials.sethome.multiple',17,1,7),(167,'essentials.sethome.multiple.iron',17,1,7),(168,'essentials.warps.dshop',17,1,7),(169,'essentials.kits.iron',17,1,7),(170,'essentials.kits.food',17,1,7),(171,'scavenger.free',17,1,7),(172,'scavenger.exp',17,1,7),(173,'scavenger.level',17,1,7),(174,'alphachest.workbench',17,1,7),(175,'use.bucket.lava',17,1,7),(176,'use.bucket.water',17,1,7),(177,'multiverse.access.Donor',18,1,7),(178,'noair.air',18,1,7),(179,'essentials.warp',18,1,7),(180,'essentials.delhome',18,1,7),(181,'essentials.sethome.multiple',18,1,7),(182,'essentials.sethome.multiple.gold',18,1,7),(183,'essentials.warps.dshop',18,1,7),(184,'essentials.kits.gold',18,1,7),(185,'essentials.kits.food',18,1,7),(186,'scavenger.free',18,1,7),(187,'scavenger.level',18,1,7),(188,'scavenger.exp',18,1,7),(189,'scavenger.scavenge',18,1,7),(190,'alphachest.workbench',18,1,7),(191,'parachute.useByClicking',18,1,7),(192,'use.bucket.lava',18,1,7),(193,'use.bucket.water',18,1,7),(194,'-cenotaph.use',18,1,7),(195,'-cenotaph.freechest',18,1,7),(196,'-cenotaph.large',18,1,7),(197,'-cenotaph.lockette',18,1,7),(198,'-cenotaph.sign',18,1,7),(199,'-cenotaph.freesign',18,1,7),(200,'-cenotaph.freelockettesign',18,1,7),(201,'-cenotaph.quickloot',18,1,7),(202,'-cenotaph.cmd.cenotaphfind',18,1,7),(203,'multiverse.access.Donor',19,1,7),(204,'noair.air',19,1,7),(205,'essentials.warp',19,1,7),(206,'essentials.delhome',19,1,7),(207,'essentials.sethome.multiple',19,1,7),(208,'essentials.sethome.multiple.diamond',19,1,7),(209,'essentials.warps.dshop',19,1,7),(210,'essentials.kits.diamond',19,1,7),(211,'essentials.kits.food',19,1,7),(212,'scavenger.scavenge',19,1,7),(213,'scavenger.level',19,1,7),(214,'scavenger.free',19,1,7),(215,'scavenger.exp',19,1,7),(216,'alphachest.chest',19,1,7),(217,'alphachest.workbench',19,1,7),(218,'parachute.useByClicking',19,1,7),(219,'use.bucket.lava',19,1,7),(220,'use.bucket.water',19,1,7),(221,'-cenotaph.use',19,1,7),(222,'-cenotaph.freechest',19,1,7),(223,'-cenotaph.large',19,1,7),(224,'-cenotaph.lockette',19,1,7),(225,'-cenotaph.sign',19,1,7),(226,'-cenotaph.freesign',19,1,7),(227,'-cenotaph.freelockettesign',19,1,7),(228,'-cenotaph.quickloot',19,1,7),(229,'-cenotaph.cmd.cenotaphfind',19,1,7),(230,'multiverse.access.Donor',20,1,7),(231,'noair.air',20,1,7),(232,'herochat.*',20,1,7),(233,'essentials.home',20,1,7),(234,'essentials.spawn',20,1,7),(235,'essentials.warp',20,1,7),(236,'essentials.delhome',20,1,7),(237,'essentials.sethome.multiple',20,1,7),(238,'essentials.sethome.multiple.emerald',20,1,7),(239,'essentials.warps.dshop',20,1,7),(240,'essentials.kits.emerald',20,1,7),(241,'essentials.kits.food',20,1,7),(242,'scavenger.scavenge',20,1,7),(243,'scavenger.level',20,1,7),(244,'scavenger.free',20,1,7),(245,'scavenger.exp',20,1,7),(246,'alphachest.chest',20,1,7),(247,'alphachest.workbench',20,1,7),(248,'parachute.useByClicking',20,1,7),(249,'use.explosives.tnt',20,1,7),(250,'use.bucket.lava',20,1,7),(251,'use.bucket.water',20,1,7),(252,'essentials.fly',20,1,7),(253,'-cenotaph.use',20,1,7),(254,'-cenotaph.freechest',20,1,7),(255,'-cenotaph.large',20,1,7),(256,'-cenotaph.lockette',20,1,7),(257,'-cenotaph.sign',20,1,7),(258,'-cenotaph.freesign',20,1,7),(259,'-cenotaph.freelockettesign',20,1,7),(260,'-cenotaph.quickloot',20,1,7),(261,'-cenotaph.cmd.cenotaphfind',20,1,7),(262,'multiverse.access.Survival',4,2,7),(263,'multiverse.access.Survival_nether',4,2,7),(264,'multiverse.access.museum',4,2,7),(265,'multiverse.access.mining',4,2,7),(266,'multiverse.access.Creative',4,2,7),(267,'multiverse.access.Anarchy',4,2,7),(268,'towny.wild.*',4,2,7),(269,'essentials.teleport.cooldown.bypass',4,2,7),(270,'essentials.spawn',4,2,7),(271,'essentials.list',4,2,7),(272,'herochat.speak.*',4,2,7),(273,'herochat.join.Global',4,2,7),(274,'herochat.join.Local',4,2,7),(275,'essentails.who',4,2,7),(276,'essentials.motd',4,2,7),(277,'essentials.help',4,2,7),(278,'essentials.sethome',4,2,7),(279,'essentials.home',4,2,7),(280,'essentials.kit',4,2,7),(281,'essentials.kits.starter',4,2,7),(282,'essentials.kits.done',4,2,7),(283,'essentials.ping',4,2,7),(284,'nocheatplus.checks.blockplace.fastplace',4,2,7),(285,'multiverse.teleport.self.*',4,2,7),(286,'multiverse.portal.access.*',4,2,7),(287,'sg.arena.join',4,2,7),(288,'sg.arena.vote',4,2,7),(289,'sg.lobby.join',4,2,7),(290,'plotme.use',5,2,7),(291,'plotme.limit.10',5,2,7),(292,'towny.wild.*',5,2,7),(293,'herochat.create',5,2,7),(294,'herochat.speak.*',5,2,7),(295,'herochat.join.*',5,2,7),(296,'herochat.leave.*',5,2,7),(297,'herochat.pm',5,2,7),(298,'essentials.mail',5,2,7),(299,'essentials.mail.send',5,2,7),(300,'essentials.pay',5,2,7),(301,'essentials.tpa',5,2,7),(302,'essentials.tpaccept',5,2,7),(303,'essentials.tpdeny',5,2,7),(304,'essentials.tptoggle',5,2,7),(305,'essentials.back',5,2,7),(306,'essentials.tpahere',5,2,7),(307,'worldguard.region.define',5,2,7),(308,'worldguard.region.select.own.*',5,2,7),(309,'worldguard.region.flag.regions.own.*',5,2,7),(310,'worldguard.region.addmember.own.*',5,2,7),(311,'worldguard.region.removemember.own.*',5,2,7),(312,'worldedit.wand',5,2,7),(313,'worldedit.selection.pos',5,2,7),(314,'permissions.user.promote.default',11,2,7),(315,'permissions.user.demote.default',11,2,7),(316,'coreprotect.*',11,2,7),(317,'essentials.mute',11,2,7),(318,'essentials.kick',11,2,7),(319,'essentials.kick.notify',11,2,7),(320,'essentials.signs.color',11,2,7),(321,'towny.admin',11,2,7),(322,'towny.admin.town',11,2,7),(323,'towny.admin.nation',11,2,7),(324,'towny.admin.set',11,2,7),(325,'towny.admin.toggle',11,2,7),(326,'towny.admin.backup',11,2,7),(327,'towny.admin.unclaim',11,2,7),(328,'multiverse.teleport.self.*',11,2,7),(329,'multiverse.portal.access.*',11,2,7),(330,'herochat.*',11,2,7),(331,'essentials.tp',11,2,7),(332,'essentials.tpo',11,2,7),(333,'essentials.tphere',11,2,7),(334,'essentials.tpohere',11,2,7),(335,'essentials.invsee',11,2,7),(336,'lwc.admin',14,2,7),(337,'coreprotect.*',14,2,7),(338,'essentials.ban',14,2,7),(339,'essentials.ban.notify',14,2,7),(340,'essentials.ban.offline',14,2,7),(341,'essentials.unban',14,2,7),(342,'essentials.tempban',14,2,7),(343,'essentials.tempban.offline',14,2,7),(344,'redstoneclockdetector.*',14,2,7),(345,'multiverse.access.Donor',17,2,7),(346,'noair.air',17,2,7),(347,'essentials.warp',17,2,7),(348,'essentials.delhome',17,2,7),(349,'essentials.sethome.multiple',17,2,7),(350,'essentials.sethome.multiple.iron',17,2,7),(351,'essentials.warps.dshop',17,2,7),(352,'essentials.kits.iron',17,2,7),(353,'essentials.kits.food',17,2,7),(354,'scavenger.free',17,2,7),(355,'scavenger.exp',17,2,7),(356,'scavenger.level',17,2,7),(357,'alphachest.workbench',17,2,7),(358,'use.bucket.lava',17,2,7),(359,'use.bucket.water',17,2,7),(360,'multiverse.access.Donor',18,2,7),(361,'noair.air',18,2,7),(362,'essentials.warp',18,2,7),(363,'essentials.delhome',18,2,7),(364,'essentials.sethome.multiple',18,2,7),(365,'essentials.sethome.multiple.gold',18,2,7),(366,'essentials.warps.dshop',18,2,7),(367,'essentials.kits.gold',18,2,7),(368,'essentials.kits.food',18,2,7),(369,'scavenger.free',18,2,7),(370,'scavenger.level',18,2,7),(371,'scavenger.exp',18,2,7),(372,'scavenger.scavenge',18,2,7),(373,'alphachest.workbench',18,2,7),(374,'parachute.useByClicking',18,2,7),(375,'use.bucket.lava',18,2,7),(376,'use.bucket.water',18,2,7),(377,'-cenotaph.use',18,2,7),(378,'-cenotaph.freechest',18,2,7),(379,'-cenotaph.large',18,2,7),(380,'-cenotaph.lockette',18,2,7),(381,'-cenotaph.sign',18,2,7),(382,'-cenotaph.freesign',18,2,7),(383,'-cenotaph.freelockettesign',18,2,7),(384,'-cenotaph.quickloot',18,2,7),(385,'-cenotaph.cmd.cenotaphfind',18,2,7),(386,'multiverse.access.Donor',19,2,7),(387,'noair.air',19,2,7),(388,'essentials.warp',19,2,7),(389,'essentials.delhome',19,2,7),(390,'essentials.sethome.multiple',19,2,7),(391,'essentials.sethome.multiple.diamond',19,2,7),(392,'essentials.warps.dshop',19,2,7),(393,'essentials.kits.diamond',19,2,7),(394,'essentials.kits.food',19,2,7),(395,'scavenger.scavenge',19,2,7),(396,'scavenger.level',19,2,7),(397,'scavenger.free',19,2,7),(398,'scavenger.exp',19,2,7),(399,'alphachest.workbench',19,2,7),(400,'parachute.useByClicking',19,2,7),(401,'use.bucket.lava',19,2,7),(402,'use.bucket.water',19,2,7),(403,'-cenotaph.use',19,2,7),(404,'-cenotaph.freechest',19,2,7),(405,'-cenotaph.large',19,2,7),(406,'-cenotaph.lockette',19,2,7),(407,'-cenotaph.sign',19,2,7),(408,'-cenotaph.freesign',19,2,7),(409,'-cenotaph.freelockettesign',19,2,7),(410,'-cenotaph.quickloot',19,2,7),(411,'-cenotaph.cmd.cenotaphfind',19,2,7),(412,'multiverse.access.Donor',20,2,7),(413,'herochat.*',20,2,7),(414,'essentials.home',20,2,7),(415,'essentials.spawn',20,2,7),(416,'essentials.warp',20,2,7),(417,'essentials.delhome',20,2,7),(418,'essentials.sethome.multiple',20,2,7),(419,'essentials.sethome.multiple.emerald',20,2,7),(420,'essentials.warps.dshop',20,2,7),(421,'essentials.kits.emerald',20,2,7),(422,'essentials.kits.food',20,2,7),(423,'scavenger.scavenge',20,2,7),(424,'scavenger.level',20,2,7),(425,'scavenger.free',20,2,7),(426,'scavenger.exp',20,2,7),(427,'alphachest.workbench',20,2,7),(428,'parachute.useByClicking',20,2,7),(429,'use.explosives.tnt',20,2,7),(430,'use.bucket.lava',20,2,7),(431,'use.bucket.water',20,2,7),(432,'essentials.fly',20,2,7),(433,'-cenotaph.use',20,2,7),(434,'-cenotaph.freechest',20,2,7),(435,'-cenotaph.large',20,2,7),(436,'-cenotaph.lockette',20,2,7),(437,'-cenotaph.sign',20,2,7),(438,'-cenotaph.freesign',20,2,7),(439,'-cenotaph.freelockettesign',20,2,7),(440,'-cenotaph.quickloot',20,2,7),(441,'-cenotaph.cmd.cenotaphfind',20,2,7),(442,'essentials.spawn',2,0,7);

/*Table structure for table `playergroups` */

DROP TABLE IF EXISTS `playergroups`;

CREATE TABLE `playergroups` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `playerid` int(9) DEFAULT NULL,
  `groupid` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `PlayerConstraint` (`playerid`),
  KEY `GroupConstraint` (`groupid`),
  CONSTRAINT `GroupConstraint` FOREIGN KEY (`groupid`) REFERENCES `groups` (`id`),
  CONSTRAINT `PlayerConstraint` FOREIGN KEY (`playerid`) REFERENCES `players` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=latin1;

/*Data for the table `playergroups` */

insert  into `playergroups`(`id`,`playerid`,`groupid`) values (2,5,20),(5,9,5),(6,8,1),(7,8,20),(8,10,1),(9,12,5),(10,11,5),(21,8,2),(22,13,4),(23,13,5),(24,13,6),(25,13,7),(26,13,8),(27,13,9),(28,13,10),(29,13,11),(48,8,4),(66,8,5),(67,8,6),(68,8,7),(69,8,9),(70,8,10),(71,8,11),(72,8,13);

/*Table structure for table `players` */

DROP TABLE IF EXISTS `players`;

CREATE TABLE `players` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `playername` varchar(128) NOT NULL,
  `activechannel` varchar(128) NOT NULL,
  `channels` text NOT NULL,
  `ignored` text,
  `muted` tinyint(1) NOT NULL DEFAULT '0',
  `muteUntil` timestamp NULL DEFAULT NULL,
  `banned` tinyint(1) DEFAULT '0',
  `bannedUntil` timestamp NULL DEFAULT NULL,
  `serverid` int(32) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `serverid` (`serverid`),
  CONSTRAINT `serverid` FOREIGN KEY (`serverid`) REFERENCES `servers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `players` */

insert  into `players`(`id`,`playername`,`activechannel`,`channels`,`ignored`,`muted`,`muteUntil`,`banned`,`bannedUntil`,`serverid`) values (7,'*','','',NULL,0,NULL,0,NULL,1),(8,'x4n4th','g','g,lawl','',0,NULL,0,NULL,8),(9,'SaltyWalrus','g','g','',0,NULL,0,NULL,1),(10,'Fluffy4eva','g','g',NULL,0,NULL,0,NULL,1),(11,'husky1996','g','g','',0,NULL,0,NULL,1),(12,'devore07','g','g','',0,NULL,0,NULL,1),(13,'Concurrency','g','g','',0,NULL,0,NULL,1);

/*Table structure for table `prefixes` */

DROP TABLE IF EXISTS `prefixes`;

CREATE TABLE `prefixes` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `groupid` int(3) DEFAULT NULL,
  `prefix` varchar(64) DEFAULT NULL,
  `priority` int(3) DEFAULT NULL,
  `donatorlevel` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `donatorgroup` (`groupid`),
  CONSTRAINT `donatorgroup` FOREIGN KEY (`groupid`) REFERENCES `groups` (`id`),
  CONSTRAINT `groupcontraint` FOREIGN KEY (`groupid`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

/*Data for the table `prefixes` */

insert  into `prefixes`(`id`,`groupid`,`prefix`,`priority`,`donatorlevel`) values (1,1,'&8/&4Admin&8/-/&7',999,1),(2,2,'*',0,2),(3,3,'SuperAdmin',0,3),(4,4,'',10,4),(5,5,'&b',20,5),(6,6,'&9',30,6),(7,7,'&3',40,7),(8,8,'&5',50,8),(9,9,'&e',60,9),(10,10,'&a',70,10),(11,11,'&8/&cHelper&8/-/&7',90,11),(12,12,'&6C&fo&6n&fv&6i&fc&6t&8',999,12),(13,13,'&bAlt&3rui&1st &7',80,13),(14,14,'&8/&6Staff&8/-/&7',100,14),(15,15,'&8/&2Staff&8/-/&7',110,15),(16,16,'&8/&cStaff&8/-/&7',120,16),(17,5,'&7«Donator»&b ',21,17),(18,5,'&6«&7Donator&6»&b ',22,18),(19,5,'&b«&7Donator&b»&b ',23,19),(20,5,'&a«&7Donator&a»&b ',24,20),(21,6,'&7«Donator»&9 ',31,17),(22,6,'&6«&7Donator&6»&9 ',32,18),(23,6,'&b«&7Donator&b»&9 ',33,19),(24,6,'&a«&7Donator&a»&9 ',34,20),(25,7,'&7«Donator»&3 ',41,17),(26,7,'&6«&7Donator&6»&3 ',42,18),(27,7,'&b«&7Donator&b»&3 ',43,19),(28,7,'&a«&7Donator&a»&3 ',44,20),(29,8,'&7«Donator»&5 ',51,17),(30,8,'&6«&7Donator&6»&5 ',52,18),(31,8,'&b«&7Donator&b»&5 ',53,19),(32,8,'&a«&7Donator&a»&5 ',54,20),(33,9,'&7«Donator»&e ',61,17),(34,9,'&6«&7Donator&6»&e ',62,18),(35,9,'&b«&7Donator&b»&e ',63,19),(36,9,'&a«&7Donator&a»&e ',64,20),(37,10,'&7«Donator»&e ',71,17),(38,10,'&6«&7Donator&6»&e ',72,18),(39,10,'&b«&7Donator&b»&e ',73,19),(40,10,'&a«&7Donator&a»&e ',74,20),(41,17,'',0,17),(42,18,'',0,18),(43,19,'',0,19),(44,20,'',0,20);

/*Table structure for table `privateMessageLog` */

DROP TABLE IF EXISTS `privateMessageLog`;

CREATE TABLE `privateMessageLog` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `player1` varchar(32) NOT NULL,
  `player2` varchar(32) NOT NULL,
  `text` varchar(256) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `privateMessageLog` */

/*Table structure for table `servers` */

DROP TABLE IF EXISTS `servers`;

CREATE TABLE `servers` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `servers` */

insert  into `servers`(`id`,`name`) values (0,'hub'),(1,'survival'),(2,'creative'),(5,'testServer'),(6,'*'),(7,'HgAlpha'),(8,'Factions');

/*Table structure for table `warns` */

DROP TABLE IF EXISTS `warns`;

CREATE TABLE `warns` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `reason` text,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `warnee` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `warns` */

insert  into `warns`(`id`,`name`,`reason`,`time`,`warnee`) values (1,'x4n4th','test','2014-01-20 16:56:52','ImGoingInDry');

/* Procedure structure for procedure `PromotePlayer` */

/*!50003 DROP PROCEDURE IF EXISTS  `PromotePlayer` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `PromotePlayer`(IN playerNameInput VARCHAR(128))
BEGIN
	SELECT @playerId := id FROM players WHERE playername = playerNameInput;	
	
	SELECT @currentRank := MAX(groups.rank)
		FROM groups
		INNER JOIN playergroups
		ON playergroups.groupid = groups.id
		WHERE playergroups.playerid = @playerId
		LIMIT 1;

	INSERT INTO playergroups (playerid, groupid)
	SELECT @playerId, MIN(groups.id)
	FROM groups
	WHERE groups.rank > @currentRank;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
