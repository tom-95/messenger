<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Java EE Tutorial</title>
</head>
<body>
	<h1>Login Form</h1>
	<form method="post" action="login">
		<label for="username">Username:</label><br>
		<input type="text" id="username" name="username"><br>
		<label for="password">Password:</label><br>
		<input type="password" id="password" name="password"><br>
		<input type="submit" value="Submit">
	</form>
	<a href="register">Registrieren</a>
</body>
</html>