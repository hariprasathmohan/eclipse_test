<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="MainPage.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="index.js"></script>
<title>Fixed Deposit</title>
</head>
<body>
<div class="main">
    <p class="sign" align="center">Deposit Application</p>
    <form class="form1" action="depositApplication" method="post">
    <div align="center">
    	<input type="radio" name="accType" value="fd"><label class="value">Fixed Deposit</label>
    	<input type="radio" name="accType" value="rd"><label class="value">Recurring Deposit</label><br><br>
    </div>
    	<input class="un" type="text" name="accountNumber" size="15" placeholder="Your Account number" required="required" />
    	<input class="un" type="number" name="numOfMonth" size="15" placeholder="Number of Month" required="required" />
    	<input class="un" type="number" name="amount" size="15" placeholder="Amount" required="required" />
    	<input class="submit" type="submit" value="Apply"><br><br>
    </form>
</div>
</body>
</html>