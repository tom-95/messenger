package org.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
	public static List<String> getContacts(String username) throws SQLException, ClassNotFoundException {
		createConnection();
		
		Statement statement = con.createStatement();
		String query = "SELECT * FROM contacts WHERE Username = '" + username + "'";
		ResultSet set = statement.executeQuery(query);
		
		List<String> contacts = new ArrayList<>();
		
		while (set.next()) {
			String name = set.getString("Contact_name");
			contacts.add(name);
		}
		con.close();
		System.out.println(contacts);
		return contacts;
		
	}
	
	public static void addContact(String username, String contact) throws ClassNotFoundException, SQLException {
		createConnection();
		
		Statement statement = con.createStatement();
		String query = "INSERT INTO contacts (Username, Contact_name) VALUES ('" + username + "', '" + contact + "')";
		statement.execute(query);
		
		if (con != null)
			con.close();
		
	}
	
}
