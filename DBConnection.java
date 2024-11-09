package utility;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

//establish connection for create acconuut in dao package 1st create method//once connection is opened close the connection to close it create method for close connection
public class DBConnection {
	private static final String url="jdbc:mysql://localhost:3306/bankingApp";
	private static final String username="root";
	 private static final String password="root";
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,username,password);
	}
	public static void closeConnection(Connection con) {
		if(con!=null) {//if connection is open close it by close() method
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error Closing Connection"+e.getMessage());
			}
		}
	}
}
