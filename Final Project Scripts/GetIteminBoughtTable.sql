SELECT I.ItemID, I.Name, I.Description
FROM Item I
WHERE -- I.Type IN (SELECT B.ItemType FROM Bought B WHERE B.CustomerID = '2') AND
I.ItemID NOT IN (Select B.ItemID FROM Bought B WHERE B.CustomerID = '2');