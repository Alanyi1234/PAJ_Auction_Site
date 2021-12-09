package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Customer;

import java.util.stream.IntStream;

public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */

	/**
	 * @param String searchKeyword
	 * @return ArrayList<Customer> object
	 */
	public List<Customer> getCustomers(String searchKeyword) {

		/*
		 * 
		 * This method fetches one or more customers based on the searchKeyword and
		 * returns it as an ArrayList
		 */

		List<Customer> customers = new ArrayList<Customer>();

		/*
		 * The students code to fetch data from the database based on searchKeyword will
		 * be written here Each record is required to be encapsulated as a "Customer"
		 * class object and added to the "customers" List
		 */

		/* Sample data begins */
		System.out.println("Get customers");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paj_auction_house", "root",
					"Alanyi44");
			Statement st = con.createStatement();
			ResultSet rs;
			if (searchKeyword == null) {
				rs = st.executeQuery("SELECT * FROM Customer, Person WHERE Customer.CustomerID = Person.PersonID");
			} else {
				rs = st.executeQuery("SELECT * FROM Customer, Person WHERE Customer.CustomerID = Person.PersonID "
						+ "AND (Person.FirstName LIKE \'%" + searchKeyword + "%\' " + "OR Person.LastName like \'%"
						+ searchKeyword + "%\')");
			}
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(rs.getString("CustomerID"));
				customer.setAddress(rs.getString("Address"));
				customer.setLastName(rs.getString("LastName"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(rs.getInt("ZipCode"));
				customer.setTelephone(rs.getString("Telephone"));
				customer.setCreditCard(rs.getString("CreditCard"));
				customer.setRating(rs.getInt("Rating"));
				customers.add(customer);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		/* Sample data ends */

		return customers;
	}

	public Customer getHighestRevenueCustomer() {
        /*
         * This method fetches the customer who generated the highest total revenue and
         * returns it The students code to fetch data from the database will be written
         * here The customer record is required to be encapsulated as a "Customer" class
         * object
         */

        /* Sample data begins */
        System.out.println("highest revenue");
        Customer customer = new Customer();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paj_auction_house", "root",
                    "Alanyi44");
            Statement st = con.createStatement();
            st.executeUpdate("CREATE VIEW Sold (CustomerID, AuctionID, SoldPrice) AS SELECT B1.CustomerID, B1.AuctionID, B1.BidPrice "
                    + "AS SoldPrice FROM Bid B1 WHERE B1.BidPrice >= ALL (SELECT B2.BidPrice FROM Bid B2 "
                    + "WHERE B1.AuctionID = B2.AuctionID)");
            st.executeUpdate("CREATE VIEW CustomerRevenue (CustomerID, Revenue) AS SELECT P.CustomerID, SUM(S.SoldPrice) FROM Sold S, "
                    + "Post P, Auction A WHERE P.AuctionID = A.AuctionID AND A.AuctionID = S.AuctionID GROUP BY P.CustomerID");
            ResultSet rs = st.executeQuery("SELECT CR.CustomerID FROM CustomerRevenue CR WHERE CR.Revenue >= ALL (SELECT CR1.Revenue "
                    + "FROM CustomerRevenue CR1)");
            rs.next();
            String customerID = rs.getString("CustomerID");
            rs = st.executeQuery("SELECT * FROM Person WHERE Person.PersonID = '" + customerID + "'");
            rs.next();
            customer.setCustomerID(rs.getString("PersonID"));
            customer.setAddress(rs.getString("Address"));
            customer.setLastName(rs.getString("LastName"));
            customer.setFirstName(rs.getString("FirstName"));
            customer.setCity(rs.getString("City"));
            customer.setState(rs.getString("State"));
            customer.setEmail(rs.getString("Email"));
            customer.setZipCode(rs.getInt("ZipCode"));
            customer.setTelephone(rs.getString("Telephone"));
//            customer.setCreditCard(rs.getString("CreditCard"));
//            customer.setRating(rs.getInt("Rating"));
            st.executeUpdate("DROP VIEW IF EXISTS Sold");
            st.executeUpdate("DROP VIEW IF EXISTS CustomerRevenue");
        } catch (Exception e) {
            System.out.println(e);
        }
        /* Sample data ends */

        return customer;

    }

	public List<Customer> getCustomerMailingList() {

		/*
		 * This method fetches the all customer mailing details and returns it The
		 * students code to fetch data from the database will be written here Each
		 * customer record is required to be encapsulated as a "Customer" class object
		 * and added to the "customers" List
		 */

		List<Customer> customers = new ArrayList<Customer>();

		/* Sample data begins */
		System.out.println("mailing list");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paj_auction_house", "root",
					"Alanyi44");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Customer, Person WHERE Customer.CustomerID = Person.PersonID");
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(rs.getString("PersonID"));
				customer.setAddress(rs.getString("Address"));
				customer.setLastName(rs.getString("LastName"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(rs.getInt("ZipCode"));
				customers.add(customer);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		/* Sample data ends */

		return customers;
	}

	public Customer getCustomer(String customerID) {

		/*
		 * This method fetches the customer details and returns it customerID, which is
		 * the Customer's ID who's details have to be fetched, is given as method
		 * parameter The students code to fetch data from the database will be written
		 * here The customer record is required to be encapsulated as a "Customer" class
		 * object
		 */

		/* Sample data begins */
		System.out.println("Get customer");
		Customer customer = new Customer();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paj_auction_house", "root",
					"Alanyi44");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Customer, Person WHERE Customer.CustomerID = Person.PersonID "
					+ "AND Customer.CustomerID = " + customerID);
			rs.next();
			customer.setCustomerID(rs.getString("CustomerID"));
			customer.setAddress(rs.getString("Address"));
			customer.setLastName(rs.getString("LastName"));
			customer.setFirstName(rs.getString("FirstName"));
			customer.setCity(rs.getString("City"));
			customer.setState(rs.getString("State"));
			customer.setEmail(rs.getString("Email"));
			customer.setZipCode(rs.getInt("ZipCode"));
			customer.setTelephone(rs.getString("Telephone"));
			customer.setCreditCard(rs.getString("CreditCard"));
			customer.setRating(rs.getInt("Rating"));
		} catch (Exception e) {
			System.out.println("the error is " + e);
		}
		/* Sample data ends */
		return customer;
	}

	public String deleteCustomer(String customerID) {

		/*
		 * This method deletes a customer returns "success" string on success, else
		 * returns "failure" The students code to delete the data from the database will
		 * be written here customerID, which is the Customer's ID who's details have to
		 * be deleted, is given as method parameter
		 */

		/* Sample data begins */
		System.out.println("Delete customer");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paj_auction_house", "root",
					"Alanyi44");
			Statement st = con.createStatement();
			st.executeUpdate("DELETE FROM Customer WHERE Customer.CustomerID = " + customerID);
			st.executeUpdate("DELETE FROM Person WHERE Person.PersonID = " + customerID);
		} catch (Exception e) {
			System.out.println(e);
			return "failure";
		}
		return "success";
		/* Sample data ends */

	}

	public String getCustomerID(String username) {
		/*
		 * This method returns the Customer's ID based on the provided email address The
		 * students code to fetch data from the database will be written here username,
		 * which is the email address of the customer, who's ID has to be returned, is
		 * given as method parameter The Customer's ID is required to be returned as a
		 * String
		 */

		System.out.println("Get customerID");
		String customerID = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paj_auction_house", "root",
					"Alanyi44");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT Customer.CustomerID FROM Person, Customer WHERE Person.Email = '" + username 
					+ "' AND Person.PersonID = Customer.CustomerID");
			rs.next();
			customerID = rs.getString("CustomerID");
		} catch (Exception e) {
			System.out.println(e);
		}
		return customerID;
	}

	public List<Customer> getSellers() {

		/*
		 * This method fetches the all seller details and returns it The students code
		 * to fetch data from the database will be written here The seller (which is a
		 * customer) record is required to be encapsulated as a "Customer" class object
		 * and added to the "customers" List
		 */

		System.out.println("Get sellers");
		List<Customer> customers = new ArrayList<Customer>();

		/* Sample data begins */
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paj_auction_house", "root",
					"Alanyi44");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT Customer.CustomerID, Person.LastName, Person.FirstName, "
					+ "Person.Email, Customer.Rating FROM Customer, Person, Post WHERE "
					+ "Person.PersonID = Customer.CustomerID And Customer.CustomerID = Post.CustomerID");
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(rs.getString("CustomerID"));
				customer.setLastName(rs.getString("LastName"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setEmail(rs.getString("Email"));
				customer.setRating(rs.getInt("Rating"));
				customers.add(customer);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		/* Sample data ends */

		return customers;

	}

	public String addCustomer(Customer customer) {

		/*
		 * All the values of the add customer form are encapsulated in the customer
		 * object. These can be accessed by getter methods (see Customer class in model
		 * package). e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default. You need to handle the database
		 * insertion of the customer details and return "success" or "failure" based on
		 * result of the database insertion.
		 */

		/* Sample data begins */
		System.out.println("Add customer");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paj_auction_house", "root",
					"Alanyi44");
			Statement st = con.createStatement();
			st.executeUpdate("INSERT INTO Person VALUES (" + customer.getCustomerID() + ", '" + customer.getLastName() + "', '" 
					+ customer.getFirstName() + "', '" + customer.getAddress() + "', '" + customer.getCity() + "', '" + customer.getState() 
					+ "', '" + customer.getZipCode() + "', '" + customer.getTelephone() + "', '" + customer.getEmail() + "')");
			st.executeUpdate("INSERT INTO Customer VALUES (" + customer.getCustomerID() + ", " + customer.getCreditCard()
				+ ", " + customer.getRating() + ")");
		} catch (Exception e) {
			System.out.println(e);
			return "failure";
		}
		return "success";
		/* Sample data ends */

	}

	public String editCustomer(Customer customer) {
		/*
		 * All the values of the edit customer form are encapsulated in the customer
		 * object. These can be accessed by getter methods (see Customer class in model
		 * package). e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default. You need to handle the database
		 * update and return "success" or "failure" based on result of the database
		 * update.
		 */

		/* Sample data begins */
		System.out.println("Edit customer");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paj_auction_house", "root",
					"Alanyi44");
			Statement st = con.createStatement();
			st.executeUpdate("UPDATE Person SET LastName = '" + customer.getLastName() + "', FirstName = '" + customer.getFirstName() 
			+ "', Address = '" + customer.getAddress() + "', City = '" + customer.getCity() + "', State = '" + customer.getState() 
			+ "', ZipCode = '" + customer.getZipCode() + "', Telephone = '" + customer.getTelephone() + "', Email = '" 
			+ customer.getEmail() + "' WHERE PersonID = " + customer.getCustomerID());
			st.executeUpdate("UPDATE Customer SET CreditCard = " + customer.getCreditCard()
				+ ", Rating = " + customer.getRating() + " WHERE CustomerID = " + customer.getCustomerID());
		} catch (Exception e) {
			System.out.println(e);
			return "failure";
		}
		return "success";
		/* Sample data ends */

	}

}
