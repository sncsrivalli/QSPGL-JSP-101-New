package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ModifyDataInDatabase {

	public static void main(String[] args) throws SQLException {

		//Step 1: Create an object for Driver class
		Driver dbDriver = new Driver();

		//Step 2: Register the dbDriver to JDBC
		DriverManager.registerDriver(dbDriver);
		
		//Step 3: Establish JDBC connection
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/qspjsp101", "root", "root");
		
		//Step 4: Create the statement
		Statement state = connect.createStatement();
		
		//Step 5: Execute the query
		int result = state.executeUpdate("insert into students(id,name) values(105,'Efg');");
		
		//Step 6: Print data in console
		System.out.println(result);
		
		
		//Step 7: Close Database connection
		connect.close();

	}

}
