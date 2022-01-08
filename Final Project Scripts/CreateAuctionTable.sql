CREATE TABLE `auction` (
  `AuctionID` int(11) NOT NULL,
  `BidIncrement` decimal(20,2) NOT NULL,
  `MinimumBid` decimal(20,2) NOT NULL,
  `CopiesSold` int(11) NOT NULL,
  `Monitor` int(11) NOT NULL,
  `ItemID` int(11) NOT NULL,
  `HighBid` int(11) NOT NULL,
  PRIMARY KEY (`AuctionID`),
  KEY `Monitor` (`Monitor`),
  KEY `ItemID` (`ItemID`),
  CONSTRAINT `auction_ibfk_1` FOREIGN KEY (`Monitor`) REFERENCES `employee` (`EmployeeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `auction_ibfk_2` FOREIGN KEY (`ItemID`) REFERENCES `item` (`ItemID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
