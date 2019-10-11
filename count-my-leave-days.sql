CREATE DATABASE  IF NOT EXISTS `leave_days`;
USE `leave_days`;

--
-- Delete all tables if exist
--
DROP TABLE IF EXISTS `leave_days`;

DROP TABLE IF EXISTS `employee`;

DROP TABLE IF EXISTS `authorities`;

DROP TABLE IF EXISTS `users`;


--
-- Table structure for table `employee`
--
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `tel` int(9) DEFAULT NULL,
  `date_of_employment` date DEFAULT NULL,
  `date_of_expire` date DEFAULT NULL,
  `leave_days_limit` int(2) DEFAULT NULL,
  `your_leave_days` int(2) DEFAULT NULL,
  `current_year` int DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
   UNIQUE KEY `employee_idx_1` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `leave_days`
--
CREATE TABLE `leave_days` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `date_from` date DEFAULT NULL,
  `date_to` date DEFAULT NULL,
  `leave_days` int(2) DEFAULT NULL,
  `year` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `leave_days`
--
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`),
   UNIQUE KEY `users_idx_1` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `authorities`
--
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Data for table `employee` & `leave_days`
--
INSERT INTO `employee` VALUES 
	(1, 'jan@tlen.pl', 'password','Jan','Nowak','123456789','2019-01-20', null, 26, 20, '2019','ROLE_EMPLOYEE');

INSERT INTO `leave_days` VALUES 
	(1, 1, '2019-01-25', '2019-01-25', 1, 2019);
    
--
-- Inserting data for table `users`
--
INSERT INTO `users` 
VALUES 
('john','{noop}test123',1),
('mary','{noop}test123',1),
('susan','{noop}test123',1),
('jan@tlen.pl','{noop}password',1);

--
-- Inserting data for table `authorities`
--
INSERT INTO `authorities` 
VALUES 
('john','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_EMPLOYEE'),
('jan@tlen.pl','ROLE_ADMIN');


-- //set font encoding to uth8mb4 for special characters like: ę, ą, Ł, ó etc. 
alter table `employee`
modify column `first_name` longtext
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL ;
alter table `employee`
modify column `last_name` longtext
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL ;



