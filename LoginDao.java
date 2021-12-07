package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import model.Login;

public class LoginDao {
	/*
	 * This class handles all the database operations related to login functionality
	 */
	
	
	public Login login(String username, String password) {
		
		/*
		 * Return a Login object with role as "manager", "customerRepresentative" or "customer" if successful login
		 * Else, return null
		 * The role depends on the type of the user, which has to be handled in the database
		 * username, which is the email address of the user, is given as method parameter
		 * password, which is the password of the user, is given as method parameter
		 * Query to verify the username and password and fetch the role of the user, must be implemented
		 */

		/*Sample data begins*/
//		Login login = new Login();
//		login.setRole("customerRepresentative");
//		System.out.println();
//		System.out.println("Logs in here");
//		return login;
		/*Sample data ends*/
		System.out.println("here");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String pass = "Alanyi44";
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/paj_auction_house",user, pass);
			System.out.print("connected!");
			if (checkUser(connect, username, password) == false) {
//				Login login = new Login();
//				login.setUsername(username);
//				login.setPassword(password);
//				if (addUser(login,connect).equals("success")) {
//					System.out.println("New login, Created");
//					return login;
//				}
//				else {
//					System.out.println("New login, Failed to create");
//					return null;
//				}
				return null;
			}
			else {
				Login login = new Login();
				login.setUsername(username);
				login.setPassword(password);
				Statement st = connect.createStatement();
				String query = String.format("SELECT Role FROM Login WHERE (UserName = '%s' and Password = '%s')", username, password);
				System.out.println("This is the query " + query);
				
				ResultSet rs = st.executeQuery(query);
				rs.next();
				login.setRole(rs.getString(1));
				return login;
			}
		}
		catch(Exception e) {
			System.out.println("unable to connect, the error is: ");
			System.out.println(e);
		}
		return null;
	}
	
	public static String addUser(Login login) {
		/*
		 * Query to insert a new record for user login must be implemented
		 * login, which is the "Login" Class object containing username and password for the new user, is given as method parameter
		 * The username and password from login can get accessed using getter methods in the "Login" model
		 * e.g. getUsername() method will return the username encapsulated in login object
		 * Return "success" on successful insertion of a new user
		 * Return "failure" for an unsuccessful database operation
		 */
		
//		System.out.println("here22222");
		login.setRole("customer");
//		INSERT INTO `paj_auction_house`.`login` (`Username`, `Password`, `Role`) VALUES ('admin@gmail.com', 'admin', 'manager');
		String query = String.format("INSERT INTO `paj_auction_house`.`login` (`Username`, `Password`, `Role`) VALUES ('%s', '%s', '%s') ", login.getUsername(), login.getPassword(), login.getRole());
		Statement st;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String pass = "Alanyi44";
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/paj_auction_house",user, pass);
			st = connect.createStatement();
			int result = st.executeUpdate(query);
			return "success";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("failed to insert, error is : ");
			System.out.println(e);
		}
		
		/*Sample data begins*/
		return "failure";
		/*Sample data ends*/
	}
	
	public static boolean checkUser(Connection connect, String user, String pass) {
		try {
			Statement st = connect.createStatement();
			String query = String.format("SELECT * FROM Login WHERE (UserName = '%s' and Password = '%s')", user, pass);
			System.out.println("This is the query " + query);
			ResultSet rs = st.executeQuery(query);


			if (rs.next() == false) {
				System.out.println("rs is empty");
				return false;
			}
			else {
				System.out.println("rs is not empty");
				return true;
			}
			
		}catch(Exception e){
			System.out.println();
			System.out.println("Nothing retruend from query");

		}
		return false;
	}

}
