package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Employee;
import model.Login;

public class EmployeeDao {
	/*
	 * This class handles all the database operations related to the employee table
	 */
	
	public String addEmployee(Employee employee) {

		/*
		 * All the values of the add employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the employee details and return "success" or "failure" based on result of the database insertion.
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String pass = "Alanyi44";
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/paj_auction_house",user, pass);
			System.out.print("connected!");
			String queryEmployee = buildQueryEmployeeTable(employee);
			String queryPerson = buildQueryPersonTable(employee);
			return storeEmployee(employee, connect, queryEmployee, queryPerson );
		}
		catch(Exception e) {
			System.out.println("unable to connect, the error is: ");
			System.out.println(e);
		}
		return "failure";
	}
	public static String buildQueryEmployeeTable(Employee employee) {
		employee.setRevenue("200");
		String query = String.format("INSERT INTO `paj_auction_house`.`employee` (`EmployeeID`, `StartDate`, `HourlyRate`, `Position`, `Revenue`) VALUES ('%s', '%s', '%s', '%s', '%s') ", employee.getEmployeeID(), employee.getStartDate(),employee.getHourlyRate(), employee.getLevel(),employee.getRevenue());
		return query;
	}
	public static String buildQueryPersonTable(Employee employee) {
		String query = String.format("INSERT INTO `paj_auction_house`.`person` (`PersonID`, `LastName`, `FirstName`, `Address`, `City`, `State`, `ZipCode`, `Telephone`, `Email`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s') ", employee.getEmployeeID(), employee.getLastName(),employee.getFirstName(), employee.getAddress(),employee.getCity(),employee.getState() ,employee.getZipCode(), employee.getTelephone(),employee.getEmail());
		return query;
	}
	public static String storeEmployee(Employee employee, Connection connect, String QEmployee, String QPerson) {
		Statement st;
		try {
			st = connect.createStatement();
			st.executeUpdate(QPerson);
			st.executeUpdate(QEmployee);
			return "success";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to update Employee ");
			System.out.println(e);
		}
		return "failure";
	}

	public String editEmployee(Employee employee) {
		/*
		 * All the values of the edit employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	public String deleteEmployee(String employeeID) {
		/*
		 * employeeID, which is the Employee's ID which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	
	public List<Employee> getEmployees() {

		/*
		 * The students code to fetch data from the database will be written here
		 * Query to return details about all the employees must be implemented
		 * Each record is required to be encapsulated as a "Employee" class object and added to the "employees" List
		 */
		/*Sample data begins*/
//		for (int i = 0; i < 10; i++) {
//			Employee employee = new Employee();
//			employee.setEmail("shiyong@cs.sunysb.edu");
//			employee.setFirstName("Shiyong");
//			employee.setLastName("Lu");
//			employee.setAddress("123 Success Street");
//			employee.setCity("Stony Brook");
//			employee.setStartDate("2006-10-17");
//			employee.setState("NY");
//			employee.setZipCode(11790);
//			employee.setTelephone("5166328959");
//			employee.setEmployeeID("631-413-5555");
//			employee.setHourlyRate(100);
//			employees.add(employee);
//		}
		/*Sample data ends*/
		
//		return employees;

		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String pass = "Alanyi44";
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/paj_auction_house",user, pass);
			System.out.print("connected!");
			Statement st = connect.createStatement();
			String employeeQuery = buildQueryGetEmployees();
			String personQuery = buildQueryGetPerson();
			ResultSet rsE = st.executeQuery(employeeQuery);
//			ResultSet rsP = st.executeQuery(personQuery);
			
			storeEmployees(employees,rsE,connect);
			

		}
		catch(Exception e) {
			System.out.println("the error is: ");
			System.out.println(e);
		}
		return employees;
		
		
	}
	public static String buildQueryGetEmployees() {
		String query = String.format("SELECT * FROM employee;");
		return query;
	}
	public static String buildQueryGetPerson() {
		String query = String.format("SELECT * FROM person;");
		return query;
	}
	public static List<Employee> storeEmployees(List<Employee> employees, ResultSet rsE,Connection connect){
		System.out.println("inside store Employees");
		try {
			while(rsE.next()) {
				System.out.println(rsE.getString(1));
				System.out.println(rsE.getString(2));
				System.out.println("THE PERSON ID IS " + rsE.getString("EmployeeID"));
				Employee employee = new Employee();
				String employeeP = String.format("SELECT * FROM paj_auction_house.person WHERE PersonID = %s", rsE.getString("EmployeeID"));
				System.out.println("the employeeQ String is : " + employeeP);
				Statement st = connect.createStatement();
				ResultSet rsP = st.executeQuery(employeeP);
				rsP.next();
				employee.setEmployeeID(rsP.getString(1));
				employee.setEmail(rsP.getString("Email"));
				employee.setFirstName(rsP.getString("FirstName"));
				employee.setLastName(rsP.getString("LastName"));
				employee.setAddress(rsP.getString("Address"));
				employee.setCity(rsP.getString("City"));
				employee.setState(rsP.getString("State"));
				employee.setZipCode(Integer.parseInt(rsP.getString("ZipCode")));
				employee.setTelephone(rsP.getString("Telephone"));
				employee.setStartDate(rsE.getString("StartDate"));
				employee.setHourlyRate(Float.parseFloat(rsE.getString("HourlyRate")));
				employees.add(employee);
			}
			return employees;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
		
	}
	public Employee getEmployee(String employeeID) {

		/*
		 * The students code to fetch data from the database based on "employeeID" will be written here
		 * employeeID, which is the Employee's ID who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Employee" class object
		 */

		Employee employee = new Employee();
		
		/*Sample data begins*/
		employee.setEmail("shiyong@cs.sunysb.edu");
		employee.setFirstName("Shiyong");
		employee.setLastName("Lu");
		employee.setAddress("123 Success Street");
		employee.setCity("Stony Brook");
		employee.setStartDate("2006-10-17");
		employee.setState("NY");
		employee.setZipCode(11790);
		employee.setTelephone("5166328959");
		employee.setEmployeeID("631-413-5555");
		employee.setHourlyRate(100);
		/*Sample data ends*/
		
		return employee;
	}
	
	public Employee getHighestRevenueEmployee() {
		
		/*
		 * The students code to fetch employee data who generated the highest revenue will be written here
		 * The record is required to be encapsulated as a "Employee" class object
		 */
		
		Employee employee = new Employee();
		
		/*Sample data begins*/
		employee.setEmail("shiyong@cs.sunysb.edu");
		employee.setFirstName("Shiyong");
		employee.setLastName("Lu");
		employee.setEmployeeID("631-413-5555");
		/*Sample data ends*/
		
		return employee;
	}

	public String getEmployeeID(String username) {
		/*
		 * The students code to fetch data from the database based on "username" will be written here
		 * username, which is the Employee's email address who's Employee ID has to be fetched, is given as method parameter
		 * The Employee ID is required to be returned as a String
		 */

		return "111-11-1111";
	}

}
