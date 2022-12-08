package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class FetchDataFromDatabase {

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
		ResultSet result = state.executeQuery("select * from students;");
		
		//Step 6: Print data in console
		while(result.next()) {
			System.out.println(result.getInt("id")+"\t"+result.getString("name"));
		}
		
		//Step 7: Close Database connection
		connect.close();
	}

}
