<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transfer</title>
</head>
<body>
<form method="post" action="accountTransfer">
		<label> Name </label>         
		<input type="text" name="name" size="15"/> <br> <br>
		<label> Account Number </label>         
		<input type="text" name="accountNumber" size="15" required="required"/> <br> <br>
		<label> IFSC code </label>         
		<input type="text" name="ifsc" size="15" required="required"/> <br> <br>
		<label> Amount </label>         
		<input type="text" name="amount" size="15" required="required"/> <br> <br>
		<input type="submit" value="Send" onclick="" > 
</form>
</body>
</html>