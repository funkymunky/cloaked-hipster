CREATE DATABASE IF NOT EXISTS helloworld;
 
USE helloworld;
 

CREATE TABLE IF NOT EXISTS USERS (
  USERNAME varchar(45) NOT NULL,
  PASSWORD varchar(45) DEFAULT NULL,
  ENABLED enum('true','false') DEFAULT 'true',
  PRIMARY KEY (USERNAME)
) ENGINE=InnoDB;
 
CREATE TABLE IF NOT EXISTS AUTHORITIES(
  USERNAME varchar(45) NOT NULL,
  AUTHORITY varchar(45) DEFAULT NULL,
  PRIMARY KEY (USERNAME),
  FOREIGN KEY (USERNAME) REFERENCES USERS(USERNAME) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;
 
INSERT INTO USERS (USERNAME, PASSWORD, ENABLED) VALUES ('marco', '123', 'true');
INSERT INTO USERS (USERNAME, PASSWORD, ENABLED) VALUES ('pino', '123', 'true');
 
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY ) VALUES ('pino', 'ROLE_ADMIN');
INSERT INTO AUTHORITIES (USERNAME, AUTHORITY ) VALUES ('marco', 'ROLE_MEMBER');


CREATE TABLE IF NOT EXISTS `address` (  
  `id` int(6) NOT NULL AUTO_INCREMENT,  
  `addressLine1` varchar(100) DEFAULT NULL,  
  `addressLine2` varchar(100) DEFAULT NULL,  
  `suburb` varchar(100) DEFAULT NULL,  
  `postcode` varchar(100) DEFAULT NULL,  
  `state` varchar(100) DEFAULT NULL,  
  `country` varchar(100) DEFAULT NULL,  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `education` (
    `id` int(50) not null auto_increment,
    `institutionType` varchar(255) default null,
    `institutionName` varchar(255) default null,
    `degreeName` varchar(255) default null,
    `yearOfStudy` int(10) default null,
    `applicationDate` date default null,
    `startDate` date default null,
    `endDate` date default null,
    `monthlyAllowance` decimal(65,2) default null,
    `agent` varchar(255) default null,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


/* latest students create table script */
CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `address_id` int(6) DEFAULT NULL,
  `education_id` int(6) DEFAULT NULL,
  `sponsorship_id` int(11) DEFAULT NULL,
  `profilePic` varchar(255) DEFAULT NULL,
  `bank_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_student_address` (`address_id`),
  KEY `FK_student_education` (`education_id`),
  KEY `FK_student_sponsorship` (`sponsorship_id`),
  KEY `FK_student_bank` (`bank_id`),
  CONSTRAINT `FK_student_bank` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`id`),
  CONSTRAINT `FK_student_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK_student_education` FOREIGN KEY (`education_id`) REFERENCES `education` (`id`),
  CONSTRAINT `FK_student_sponsorship` FOREIGN KEY (`sponsorship_id`) REFERENCES `sponsorship` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `sponsors` (
  `id` int(11) not null auto_increment,
  `firstName` varchar(255) default null,
  `lastName` varchar(255) default null,
  `phone1` varchar(255) default null,
  `phone2` varchar(255) default null,
  `address_id` int(6) default null,
  `email` varchar(255) default null,
  PRIMARY KEY (`id`),
  KEY `FK_sponsor_address` (`address_id`),
  CONSTRAINT `FK_sponsor_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `sponsorship` (
  `id` int(11) not null auto_increment,
  `sponsor_id` int(11) default null,
  `student_id` int(11) default null,
  `sponsorshipType` varchar(255) default null,
  `electedCurrency` varchar(255) default null,
  `paymentFrom` date default null,
  `paymentTill` date default null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `bank` (
  `id` int(11) not null auto_increment,
  `accountName` varchar(255) default null,
  `accountNumber` varchar(255) default null,
  `bank` varchar(255) default null,
  `branch` varchar(255) default null,
  `standingOrder` varchar(255) default null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `fees` (
  `id` int(11) not null auto_increment,
  `issueDate` date default null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `sponsorshipFees` (
  `id` int(11) not null auto_increment,
  `feeIssueDate` date default null,
  `sponsor_id` int(11) default null,
  `student_id` int(11) default null,
  `amountOutstanding` decimal(65,2) default null,
  `paidInFull` tinyint(1) default 0,
  PRIMARY KEY (`id`),
  KEY `FK_fees_sponsor` (`sponsor_id`),
  KEY `FK_fees_student` (`student_id`),
  CONSTRAINT `FK_fees_sponsor` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsors` (`id`),
  CONSTRAINT `FK_fees_student` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table `sponsorship` modify column `startDate` date default null, modify column `endDate` date default null;

CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(11) not null auto_increment,
  `comments` mediumtext default null,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

alter table `students` add column comments_id int(11) default null;
alter table `students` add constraint FK_comments_id FOREIGN KEY  (comments_id) references comments (id);
alter table `students` add column email varchar(255) default null;
alter table `students` add column telephone varchar(255) default null;

alter table `sponsorship` change paymentFrom sponsorshipStartDate date;
alter table `sponsorship` change paymentTill sponsorshipEndDate date;
alter table `sponsorship` change startDate paymentFrom date;
alter table `sponsorship` change endDate paymentTill date;
alter table `sponsorship` change sponsorshipStartDate  startDate date;
alter table `sponsorship` change sponsorshipEndDate endDate date;

create table if not exists `studentsponsorfees` (
  `id` int(11) not null auto_increment,
  `student_id` int(11) default null,
  `sponsor_id` int(11) default null,
  `bankfee` decimal(10,2) default null,
  `exchangeRate` decimal (10,2) default null,
  `amountToPay` decimal(20,2) default null,
  PRIMARY KEY (`id`),
  KEY `FK_student` (`student_id`),
  KEY `FK_sponsor` (`sponsor_id`),
  CONSTRAINT `FK_student` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `FK_sponsor` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT =1 DEFAULT CHAR SET =utf8