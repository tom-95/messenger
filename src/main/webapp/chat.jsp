<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Say hello</title>
</head>
<body>
	<p>${sessionScope.chat}</p>
	<br>
	    <form method="post" action="chat?username=${param.username}&contact=${param.contact}">
	      <input type="text" placeholder="Send message" name="message">
	      <button type="submit">Submit</button>
	    </form>
  	<br>
</body>
</html>