package org.example.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.example.dao.UserDAO;
import org.example.entity.User;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("test get");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		System.out.println("Username: " + username);

		List<String> contacts = null;

		try {
			contacts = UserDAO.getContacts(username);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		session.setAttribute("contacts", contacts);

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("test post");

		HttpSession session = request.getSession();
		String contact = request.getParameter("contact");
		User user = (User) session.getAttribute("loggedUser");

		User contactFromDB = null;

		try {
			contactFromDB = UserDAO.getUser(contact);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (contactFromDB == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("exception.jsp");
			dispatcher.forward(request, response);
		}

		try {
			UserDAO.addContact(user.getUsername(), contact);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);

	}

}
