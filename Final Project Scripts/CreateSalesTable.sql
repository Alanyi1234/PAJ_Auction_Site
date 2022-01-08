CREATE TABLE `paj_auction_house`.`sales` (
  `SaleID` INT NOT NULL AUTO_INCREMENT,
  `AuctionID`	INT NOT NULL,
  `SoldTo` INT NOT NULL, 
  `SellerID` INT NOT NULL, 
  PRIMARY KEY (`SaleID`),
  FOREIGN KEY (`AuctionID`) REFERENCES Auction (AuctionID)
    ON UPDATE CASCADE,
    FOREIGN KEY (`SoldTo`) REFERENCES Customer (CustomerID)
    ON UPDATE CASCADE,
     FOREIGN KEY (`SellerID`) REFERENCES Customer (CustomerID)
    ON UPDATE CASCADE
  );
