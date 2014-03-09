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


CREATE TABLE  IF NOT EXISTS`students` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `lastName` varchar(40) NOT NULL,
  `firstName` varchar(40) NOT NULL,
  `otherNames` varchar(60),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


ALTER TABLE students add dateOfBirth date;

alter table students drop column dateOfBirth;

CREATE TABLE IF NOT EXISTS `address` (  
  `id` int(6) NOT NULL AUTO_INCREMENT,  
  `addressLine1` varchar(100) DEFAULT NULL,  
  `addressLine2` varchar(100) DEFAULT NULL,  
  `suburb` varchar(100) DEFAULT NULL,  
  `postcode` varchar(100) DEFAULT NULL,  
  `state` varchar(100) DEFAULT NULL,  
  `country` varchar(100) DEFAULT NULL,  
  `telephone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)  
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

ALTER TABLE students add column address_id int(6);

alter table students 
add constraint FK_student_address 
foreign key (address_id) references address(id);

alter table students drop foreign key FK_student_address;

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

alter table students drop column otherNames;

alter table students
add constraint FK_student_education
foreign key (education_id) references education(id);


/** Latest create table for students **/
CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `address_id` int(6) DEFAULT NULL,
  `education_id` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_student_address` (`address_id`),
  KEY `FK_student_education` (`education_id`),
  CONSTRAINT `FK_student_education` FOREIGN KEY (`education_id`) REFERENCES `education` (`id`),
  CONSTRAINT `FK_student_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1


CREATE TABLE IF NOT EXISTS `sponsors` (
  `id` int(11) not null auto_increment,
  `firstName` varchar(255) default null,
  `lastName` varchar(255) default null,
  `phone1` varchar(255) default null,
  `phone2` varchar(255) default null,
  `address_id` int(6) default null,
  PRIMARY KEY (`id`),
  KEY `FK_sponsor_address` (`address_id`),
  CONSTRAINT `FK_sponsor_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1


CREATE TABLE IF NOT EXISTS `sponsorship` (
  `id` int(11) not null auto_increment,
  `sponsor_id` int(11) default null,
  `student_id` int(11) default null,
  `sponsorshipType` varchar(255) default null,
  `electedCurrency` varchar(255) default null,
  `paymentFrom` date default null,
  `paymentTill` date default null,
  PRIMARY KEY (`id`),
  KEY `FK_sponsorship_sponsor` (`sponsor_id`),
  KEY `FK_sponsorship_student` (`student_id`),
  CONSTRAINT `FK_sponsorship_sponsor` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsors` (`id`),
  CONSTRAINT `FK_sponsorship_student` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1