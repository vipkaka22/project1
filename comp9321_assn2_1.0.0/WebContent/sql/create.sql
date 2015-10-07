DROP TABLE IF EXISTS `assign2`.`sellitem`;
DROP TABLE IF EXISTS `assign2`.`userhistory`;
DROP TABLE IF EXISTS `assign2`.`solditem`;
DROP TABLE IF EXISTS `assign2`.`buyer`;
DROP TABLE IF EXISTS `assign2`.`seller`;
DROP TABLE IF EXISTS `assign2`.`admin`;
DROP TABLE IF EXISTS `assign2`.`item`;


CREATE TABLE `assign2`.`item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `editor` varchar(255) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `journal` varchar(255) DEFAULT NULL,
  `volume` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `isbn` varchar(45) DEFAULT NULL,
  `pages` varchar(45) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `booktitle` varchar(255) DEFAULT NULL,
  `price` float unsigned zerofill NOT NULL,
  `pause` int(1) unsigned zerofill NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
);

CREATE TABLE `assign2`.`buyer` (
  `email` varchar(255) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  `birth` int(4) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `credit` varchar(45) DEFAULT NULL,
  `ban` int(1) unsigned zerofill NOT NULL DEFAULT '0',
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
);

CREATE TABLE `assign2`.`seller` (
  `email` varchar(255) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  `birth` int(4) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `credit` varchar(45) DEFAULT NULL,
  `ban` int(1) unsigned zerofill NOT NULL DEFAULT '0',
  PRIMARY KEY (`username`)
);

CREATE TABLE `assign2`.`admin` (
  `email` varchar(255) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
);

CREATE TABLE `assign2`.`solditem` (
  `buyername` varchar(45) NOT NULL,
  `itemid` int(11) NOT NULL,
  `cart` int(10) unsigned zerofill NOT NULL DEFAULT '0',
  `sold` int(10) unsigned zerofill NOT NULL DEFAULT '0',
  PRIMARY KEY (`buyername`,`itemid`),
  CONSTRAINT `itemid1` FOREIGN KEY (`itemid`) REFERENCES `assign2`.`item` (`id`),
  CONSTRAINT `buyername1` FOREIGN KEY (`buyername`) REFERENCES `assign2`.`buyer` (`username`)
);

CREATE TABLE `assign2`.`userhistory` (
  `historyid` int(11) NOT NULL AUTO_INCREMENT,
  `buyername` varchar(45) NOT NULL,
  `itemid` int(11) NOT NULL,
  `cart` int(10) unsigned zerofill NOT NULL DEFAULT '0',
  `sold` int(10) unsigned zerofill NOT NULL DEFAULT '0',
  `soldtime` varchar(45) DEFAULT '',
  `addtime` varchar(45) DEFAULT '',
  `removetime` varchar(45) DEFAULT '',
  PRIMARY KEY (`historyid`),
  UNIQUE KEY `historyid_UNIQUE` (`historyid`),
  CONSTRAINT `buyername2` FOREIGN KEY (`buyername`) REFERENCES `assign2`.`buyer` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `itemid2` FOREIGN KEY (`itemid`) REFERENCES `assign2`.`item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `assign2`.`sellitem` (
  `sellername` varchar(45) NOT NULL,
  `itemid` int(11) NOT NULL,
  PRIMARY KEY (`sellername`,`itemid`),
  CONSTRAINT `itemid3` FOREIGN KEY (`itemid`) REFERENCES `assign2`.`item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sellname` FOREIGN KEY (`sellername`) REFERENCES `assign2`.`seller` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
);