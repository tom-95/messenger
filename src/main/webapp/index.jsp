<%@ page import="org.example.entity.User" %>
<%@ include file="resources/header.jspf" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<h1>Willkommen</h1>
	<%
		HttpSession ses = request.getSession();
		User user = (User) ses.getAttribute("loggedUser");
		if (user != null) {
			out.println("Ich begrüße Dich: " + user.getUsername());
		}
		
	%>
	<br>
	<br>
	<div class="search-container">
	    <form method="post" action="index">
	      <input type="text" placeholder="Add contact" name="contact">
	      <button type="submit">Submit</button>
	    </form>
  	</div>
  	<br>
  	
  	<h3>Deine Kontakte:</h3>
  	<c:forEach
 		items="${contacts}"
 		var="contact" 
 		varStatus="status" 
 	>	 
 		${contact} <br> 
 	</c:forEach>
 	<br>
	
<%@ include file="resources/footer.jspf" %>