CREATE TABLE `person` (
  `PersonID` int(11) NOT NULL AUTO_INCREMENT,
  `LastName` varchar(45) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `City` varchar(45) NOT NULL,
  `State` varchar(45) NOT NULL,
  `ZipCode` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Telephone` varchar(45) NOT NULL,
  PRIMARY KEY (`PersonID`),
  UNIQUE KEY `PersonID_UNIQUE` (`PersonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
