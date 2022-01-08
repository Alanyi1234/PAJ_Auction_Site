CREATE TABLE `paj_auction_house`.`item` (
  `ItemID` INT NOT NULL,
  `Name` VARCHAR(200) NOT NULL,
  `Type` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(200) NOT NULL,
  `NumCopies` INT NOT NULL, #Maybe add check to see if greater than 0?
  PRIMARY KEY (`ItemID`));
