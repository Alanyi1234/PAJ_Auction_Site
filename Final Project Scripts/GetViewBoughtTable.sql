CREATE VIEW Bought(CustomerID, ItemID, ItemType)
AS
SELECT B1.CustomerID, I.ItemID, I.Type AS ItemType
FROM Bid B1, Item I, Auction A
WHERE B1.AuctionID = A.AuctionID AND A.ItemID = I.ItemID AND
B1.BidPrice >= ALL (SELECT B2.BidPrice FROM Bid B2 WHERE B1.AuctionID = B2.AuctionID);

SELECT I.ItemID, I.Name, I.Description
FROM Item I
WHERE I.Type IN (SELECT B.ItemType FROM Bought B WHERE B.CustomerID = '1') AND
I.ItemID NOT IN (Select B.ItemID FROM Bought B WHERE B.CustomerID = '1');