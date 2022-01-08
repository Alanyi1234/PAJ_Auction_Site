CREATE VIEW Sold (CustomerID, AuctionID, SoldPrice)
AS
SELECT B1.CustomerID, B1.AuctionID, B1.BidPrice AS SoldPrice
FROM Bid B1
WHERE B1.BidPrice >=
ALL (SELECT B2.BidPrice FROM Bid B2 WHERE B1.AuctionID = B2.AuctionID);

SELECT I.ItemID, Count(I.ItemID) AS CountItem
FROM Sold S, Auction A, Item I
WHERE S.AuctionID = A.AuctionID AND A.ItemID = I.ItemID
GROUP BY I.ItemID
ORDER BY CountItem