CREATE TABLE `employee` (
  `EmployeeID` int(11) NOT NULL AUTO_INCREMENT,
  `StartDate` varchar(45) NOT NULL,
  `HourlyRate` varchar(45) NOT NULL,
  `Position` varchar(45) NOT NULL,
  `Revenue` varchar(45) NOT NULL,
  PRIMARY KEY (`EmployeeID`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `person` (`PersonID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
