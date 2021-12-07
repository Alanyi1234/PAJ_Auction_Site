package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String userName = "root";
			String password = "Alanyi44";
//			String userName = "Alan";
//			String pass = "Alan";
//			Connection connect = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-4VKO6N1:1433;databaseName=PAJ Auction House",userName, pass);
//			Statement state = connect.createStatement();
//			ResultSet rs = state.executeQuery(searchKeyword);
			// rs is a row in the DataBase with all the info. create customer object and store into customers array
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/paj_auction_house",userName, password);

			System.out.print("connected!");
			
			test(connect);
			

		}
		catch(Exception e) {
			System.out.println("unable to connect, the error is: ");
			System.out.println(e);
		}

	}
	
	public static void test(Connection connect) {
		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Login;");
			rs.next();
			System.out.print("The result set contains : ");
	
			System.out.print(rs.getString(1));
			System.out.print(rs.getString(2));
			rs.next();
			System.out.print("The result set contains : ");
	
			System.out.print(rs.getString(1));
			System.out.print(rs.getString(2));
		}catch(Exception e){
			System.out.println();
			System.out.println("Nothing retruend from query");
		}
	}

}
