SELECT I.ItemID, SUM(S.SoldPrice)
FROM Sold S, Customer C, Post P, Person P2, Item I
WHERE P2.FirstName = 'cust' AND C.CustomerID = P.CustomerID AND
P.AuctionID = S.AuctionID AND P2.PersonID = C.CustomerID