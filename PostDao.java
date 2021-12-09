package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Item;
import model.Post;

public class PostDao {

	public List<Item> getSalesReport(Post post) {

		/*
		 * The students code to fetch data from the database will be written here Each
		 * record is required to be encapsulated as a "Item" class object and added to
		 * the "items" List Query to get sales report for a particular month must be
		 * implemented post, which has details about the month and year for which the
		 * sales report is to be generated, is given as method parameter The month and
		 * year are in the format "month-year", e.g. "10-2018" and stored in the
		 * expireDate attribute of post object The month and year can be accessed by
		 * getter method, i.e., post.getExpireDate()
		 */

		List<Item> items = new ArrayList<Item>();

		/* Sample data begins */
		System.out.println("Get sales report");
		String[] date = post.getExpireDate().split("-");
		String nextMonth = Integer.toString(Integer.parseInt(date[0]) + 1);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paj_auction_house", "root",
					"Alanyi44");
			Statement st = con.createStatement();
			st.executeUpdate(
					"CREATE VIEW Sold (CustomerID, AuctionID, SoldPrice) AS SELECT B1.CustomerID, B1.AuctionID, B1.BidPrice "
							+ "AS SoldPrice FROM Bid B1 WHERE B1.BidPrice >= ALL (SELECT B2.BidPrice FROM Bid B2 "
							+ "WHERE B1.AuctionID = B2.AuctionID)");
			ResultSet rs;
			if (nextMonth.equals("13")) {
				nextMonth = "1";
				String nextYear = Integer.toString(Integer.parseInt(date[1]) + 1);
				String query = "SELECT I.Name, S.SoldPrice FROM Sold S, Item I, Auction A, Post P "
						+ "WHERE S.AuctionID = A.AuctionID AND A.ItemID = I.ItemID AND P.AuctionID = A.AuctionID "
						+ "AND P.ExpireDate > '" + date[1] + "-" + date[0] + "-01 00:00:00' AND P.ExpireDate < '"
						+ nextYear + "-" + nextMonth + "-01 00:00:00'";
				System.out.println(query);
				rs = st.executeQuery(query);
			} else {
				String query = "SELECT I.Name, S.SoldPrice FROM Sold S, Item I, Auction A, Post P "
						+ "WHERE S.AuctionID = A.AuctionID AND A.ItemID = I.ItemID AND P.AuctionID = A.AuctionID "
						+ "AND P.ExpireDate > '" + date[1] + "-" + date[0] + "-01 00:00:00' AND P.ExpireDate < '"
						+ date[1] + "-" + nextMonth + "-01 00:00:00'";
				System.out.println(query);
				rs = st.executeQuery(query);
			}
			while (rs.next()) {
				Item item = new Item();
				item.setName(rs.getString("Name"));
				item.setSoldPrice(rs.getInt("SoldPrice"));
				items.add(item);
			}
			st.executeUpdate("DROP VIEW IF EXISTS Sold");
		} catch (Exception e) {
			System.out.println(e);
		}
		/* Sample data ends */

		return items;

	}
}
