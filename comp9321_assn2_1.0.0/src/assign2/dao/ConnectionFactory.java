package assign2.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	static public Connection Connection() {
		Connection connection = null;
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assign2","root","");
		} catch (Exception e) {
			System.out.println("Connect Exception");
		    e.printStackTrace();
		}
		return connection;
	}
}
