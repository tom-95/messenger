package org.example.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		//session.invalidate();
		Cookie cookie = new Cookie("username", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		request.getSession().removeAttribute("loggedUser");
		
		//RequestDispatcher dispatcher =request.getRequestDispatcher("login.jsp");
		//dispatcher.forward(request, response);
		response.sendRedirect("login");
	}

}
