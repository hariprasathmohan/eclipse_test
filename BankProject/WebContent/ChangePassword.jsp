<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Change password</title>
</head>
<body>
<div class="main">
    <p class="sign" align="center">Change Password</p>
    <form class="form1" action="changePass" method="post">
      <input class="un " type="text" name="oldPassword" placeholder="Old Password" required="required">
      <input class="un" type="password" name="newPassword" placeholder="Password" required="required">
      <input class="un" type="text" name="password" placeholder="Confirm Password" required="required">
      <input class="submit" type="submit" value="change" /> <br> <br>
      <input class="submit" type="reset" value="Reset" /> <br>           
     </form>  
     <br><br>
</div>
</body>
</html>