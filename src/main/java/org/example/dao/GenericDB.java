package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDB {
	
	protected static Connection con = null;
	
	public static void createConnection() throws ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		String username = "root";
		String pw = "admin_123";
		String url = "jdbc:mysql://localhost:3306/messenger";
		
		try {
			con = DriverManager.getConnection(url, username, pw);
			System.out.println("DB-Verbindung aufgebaut ...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
