<%@ page import="org.example.entity.User" %>
<%@ include file="resources/header.jspf" %>
<%@ page import="java.util.*" %>

	<% 
		ArrayList<String> list = new ArrayList();
		list.add("first");
		list.add("second");
		session.setAttribute("list", list);
	%>

	<!-- Include Direktive -->
	
	<h1>Willkommen zum Java EE Tutorial.</h1>
	<%
		HttpSession ses = request.getSession();
		User user = (User) ses.getAttribute("loggedUser");
		if (user != null) {
			out.println("Ich begrüße Dich: " + user.getUsername());
		}
	%>
	<br>
	<div class="search-container">
	    <form action="/action_page.php">
	      <input type="text" placeholder="Search contact" name="search">
	      <button type="submit">Submit</button>
	    </form>
  	</div>
	
<%@ include file="resources/footer.jspf" %>