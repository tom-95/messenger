package org.example.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.example.dao.ChatDAO;
import org.example.dao.UserDAO;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChatServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chat = null;
		try {
			chat = ChatDAO.getChat(request.getParameter("username"), request.getParameter("contact"));
			if (chat == null) {
				chat = ChatDAO.getChat(request.getParameter("contact"), request.getParameter("username"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		if (chat != null)
			session.setAttribute("chat", chat);
		
		System.out.println("chat: " + chat);

		RequestDispatcher dispatcher = request.getRequestDispatcher("chat.jsp?username=" + request.getParameter("username") + "&contact=" + request.getParameter("contact"));
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String chat = (String) session.getAttribute("chat");
		
		if (chat != null) {
			chat += " <br> " + request.getParameter("username") + ": " + request.getParameter("message");
			System.out.println(chat);
			try {
				ChatDAO.updateChat(request.getParameter("username"), request.getParameter("contact"), chat);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			chat = request.getParameter("username") + ": " + request.getParameter("message");
			try {
				ChatDAO.createChat(request.getParameter("username"), request.getParameter("contact"), chat);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		session.setAttribute("chat", chat);

		RequestDispatcher dispatcher = request.getRequestDispatcher("chat.jsp?username=" + request.getParameter("username") + "&contact=" + request.getParameter("contact"));
		dispatcher.forward(request, response);
		 
	}

}
