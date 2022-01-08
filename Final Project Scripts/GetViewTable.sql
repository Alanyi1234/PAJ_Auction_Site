SELECT I.ItemID, Count(I.ItemID) AS CountItem
FROM Sold S, Auction A, Item I
WHERE S.AuctionID = A.AuctionID AND A.ItemID = I.ItemID
GROUP BY I.ItemID
ORDER BY CountItem