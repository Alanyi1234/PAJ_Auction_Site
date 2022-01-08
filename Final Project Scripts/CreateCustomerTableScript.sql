CREATE TABLE `paj_auction_house`.`customer` (
  `CustomerID` INT NOT NULL,
  `CreditCard` VARCHAR(45) NOT NULL,
  `Rating` INT NOT NULL,
  PRIMARY KEY (`CustomerID`),
  FOREIGN KEY(`CustomerID`) REFERENCES person(`PersonID`)
  ON DELETE NO ACTION
  ON UPDATE CASCADE);
