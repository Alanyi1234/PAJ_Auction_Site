CREATE TABLE `paj_auction_house`.`Bid` (
  `BidTime` DATETIME NOT NULL,
  `BidPrice` DECIMAL(20,2) NOT NULL,
  `CustomerID` INT NOT NULL,
  `AuctionID` INT NOT NULL,
  PRIMARY KEY (`CustomerID`,`AuctionID`,`BidTime`),
  FOREIGN KEY (`CustomerID`) REFERENCES customer (CustomerID)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (`AuctionID`) REFERENCES auction (AuctionID)
  ON DELETE CASCADE
  ON UPDATE CASCADE
  );
