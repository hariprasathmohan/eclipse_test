<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="MainPage.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="index.js"></script>
<title></title>
</head>
<body>
<div class="main">
    <p class="sign" align="center">Loan Application</p>
    <form class="form1" action="applyLoan" method="post">
    	<select name="type" class="un" id="type">
    		<option value ='none'>Select type</option>
    		<option value="agriculture">Agriculture Loan</option>
    		<option value="Education">Educational Loan</option>
    		<option value="Gold">Gold Loan</option>
    	</select>
    	<input class="un" type="number" name="amount" size="15" placeholder="Amount" required="required" />
    
    	<input class="un" type="text" name="purpose" size="15" placeholder="Purpose" required="required" />
    	<input class="submit" type="submit" value="Apply"><br><br>
    </form>
</div>
</body>
</html>