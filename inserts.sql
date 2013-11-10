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


CREATE TABLE  IF NOT EXISTS`stduents` (
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