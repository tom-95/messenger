package org.example.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@WebFilter(urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {
	
	private static final String[] protectedURL = {"index.jsp"};
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Initialisierung des Filters");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("Filterung findet statt");
		HttpServletRequest req = (HttpServletRequest) request;
		
		System.out.println("RequestURI: " + req.getRequestURI());
		System.out.println("ContextPath: " + req.getContextPath());
		
		HttpSession session = req.getSession(false);
		
		boolean auth = (session != null && session.getAttribute("loggedUser") != null);
		String login = req.getContextPath() + "/login";
		
		boolean loginReq = req.getRequestURI().equals(login);
		boolean loginPag = req.getRequestURI().endsWith("login.jsp");
		
		if (!auth && protectedURL(req)) {
			String page = "/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} else if (auth && (loginReq || loginPag)) {
			String page = "/";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} else {
			System.out.println("else");
			chain.doFilter(request, response);
		}

	}
	
	private boolean protectedURL(HttpServletRequest request) {
		String requestedURL = request.getRequestURL().toString();
		
		for (String element : protectedURL) {
			if (requestedURL.contains(element))
				return true;
		}
		return false;
	}
	
	@Override
	public void destroy() {
		System.out.println("Zerstörung des Filters");
	}
	
}
