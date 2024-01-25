package org.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.entity.User;

public class UserDAO extends GenericDB {
	
	public static void registerUser(String username, String pw) throws ClassNotFoundException, SQLException {
		createConnection();
		
		Statement statement = con.createStatement();
		String query = "INSERT INTO user (Username, Password) VALUES ('" + username + "', '" + pw + "')";
		statement.execute(query);
		
		if (con != null)
			con.close();
		
	}
	
	public static User getUser(String username) throws SQLException, ClassNotFoundException {
		createConnection();
		
		Statement statement = con.createStatement();
		String query = "SELECT * FROM user WHERE Username = '" + username + "'";
		ResultSet set = statement.executeQuery(query);
		
		if (set.next()) {
			String name = set.getString("Username");
			String pw = set.getString("Password");
			if (name != null && pw != null) {
				User user = new User(name, pw);
				con.close();
				return user;
			}
		}
		con.close();
		return null;
		
	}
	
}
