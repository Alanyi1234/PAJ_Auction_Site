CREATE VIEW Sold(AuctionId, SoldPrice)
AS SELECT B1.AuctionID, B1.BidPrice AS SoldPrice
FROM Bid B1
WHERE B1.BidPrice >= ALL(SELECT B2.BidPrice FROM Bid B2 WHERE B1.AuctionId = B2.AuctionId);

CREATE VIEW CustomerRepRevenue(EmployeeId, Revenue)
AS
SELECT A.Monitor, SUM(S.SoldPrice)
FROM Sold S, Auction A
WHERE S.AuctionId = A.AuctionId
GROUP BY A.Monitor