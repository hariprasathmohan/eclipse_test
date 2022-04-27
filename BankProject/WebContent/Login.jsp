<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
     <%@ include file="style.css"%>
</style>
<title>Login</title>
</head>
<body>
<div class="main">
    <p class="sign" align="center">Login</p>
    <form class="form1" action="login" method="post">
      <input class="un" type="text" name="userId" placeholder="Username" required="required">
      <input class="un" type="password" name="password" placeholder="Password" required="required">
      <input class="submit" type="submit" value="Login" /> <br>           
     </form>  
     <br>
     <a href="Register.jsp">     Dont have account? Register here</a> <br><br>     
</div>
</body>
</html>