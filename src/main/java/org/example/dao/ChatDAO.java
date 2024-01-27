package org.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.example.entity.User;

public class ChatDAO extends GenericDB {
	
	public static void createChat(String user1, String user2, String chat) throws ClassNotFoundException, SQLException {
		createConnection();
		
		Statement statement = con.createStatement();
		String query = "INSERT INTO chats (user1, user2, chat) VALUES ('" + user1 + "', '" + user2 + "', '" + chat + "')";
		statement.execute(query);
		
		if (con != null)
			con.close();
		
	}
	
	public static String getChat(String user1, String user2) throws SQLException, ClassNotFoundException {
		createConnection();
		Statement statement = con.createStatement();
		String query = "SELECT chat FROM chats WHERE user1 = '" + user1 + "' and user2 = '" + user2 + "'";
		ResultSet set = statement.executeQuery(query);
		
		if (set.next()) {
			String chat = set.getString("chat");
			con.close();
			return chat;
		}
		con.close();
		return null;
		
	}
	
	public static void updateChat(String user1, String user2, String chat) throws ClassNotFoundException, SQLException {
		createConnection();
		
		Statement statement = con.createStatement();
		String query = "UPDATE chats SET chat = '" + chat + "' WHERE user1 = '" + user1 + "' and user2 = '" + user2 + "'";
		statement.execute(query);
		
		if (con != null)
			con.close();
		
	}
	
}
