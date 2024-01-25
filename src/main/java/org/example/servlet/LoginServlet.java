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

import org.example.dao.UserDAO;
import org.example.entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User loggedUser = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					try {
						loggedUser = UserDAO.getUser(cookie.getValue());
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		if(loggedUser == null) {
			RequestDispatcher dispatcher =request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		} else {
			if (loggedUser != null) {
				session.setAttribute("loggedUser", loggedUser);
			}
			//RequestDispatcher dispatcher =request.getRequestDispatcher("/index");
			//dispatcher.forward(request, response);
			response.sendRedirect("index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User loggedUser = null;
		User userFromDB = null;
		
		try {
			userFromDB = UserDAO.getUser(username);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (userFromDB == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("exception.jsp");
			dispatcher.forward(request, response);
		}
		
		if(userFromDB.getUsername().equals(username) && userFromDB.getPassword().equals(password)) {
			loggedUser = userFromDB;
		}
		
		if(loggedUser != null) {
			
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(20);
			
			response.addCookie(cookie);
			request.getSession().setAttribute("loggedUser", loggedUser);
			
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/index");
			//dispatcher.forward(request, response);
			response.sendRedirect("index?username=" + username);
		} else
			response.sendRedirect("login");
	
	}

}
