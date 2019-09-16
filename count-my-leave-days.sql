CREATE DATABASE  IF NOT EXISTS `leave_days`;
USE `leave_days`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `leave_days`;

DROP TABLE IF EXISTS `employee`;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `leave_days` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `date_from` date DEFAULT NULL,
  `date_to` date DEFAULT NULL,
  `leave_days` int(2) DEFAULT NULL,
  `year` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`employee_id`) REFERENCES employee(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee` & `leave_days`
--

INSERT INTO `employee` VALUES 
	(1, 'login', 'password','Jan','Nowak','123456789','2019-01-20', null, 26, 20);

INSERT INTO `leave_days` VALUES 
	(1, 1, '2019-01-25', '2019-01-25', 1, 2019);
