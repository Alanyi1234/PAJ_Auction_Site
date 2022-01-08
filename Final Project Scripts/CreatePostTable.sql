CREATE TABLE `paj_auction_house`.`post` (
  `ExpireDate` DATETIME NOT NULL,
  `PostDate` DATETIME NOT NULL,
  `CustomerID` INT NOT NULL,
  `AuctionID` INT NOT NULL,
  PRIMARY KEY (`CustomerID`,`AuctionID`),
  FOREIGN KEY (`CustomerID`) REFERENCES customer (CustomerID)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (`AuctionID`) REFERENCES auction (AuctionID)
  ON DELETE CASCADE
  ON UPDATE CASCADE
  );
