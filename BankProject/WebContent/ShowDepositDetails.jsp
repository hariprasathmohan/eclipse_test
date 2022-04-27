<%@page import="com.bank.database.RecurringDepositDAO"%>
<%@page import="com.bank.models.AccountModel"%>
<%@page import="com.bank.models.OtherDepositModel"%>
<%@page import="com.bank.database.FixedDepositDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Insert title here</title>
</head>
<body>
<%
	AccountModel account = (AccountModel) request.getSession(false).getAttribute("myAccount");
	FixedDepositDAO fixeddb = new FixedDepositDAO();
	RecurringDepositDAO rddb = new RecurringDepositDAO();
	String accountNumber = request.getParameter("accountNumber");
	String type = accountNumber.substring(0, 2);
	OtherDepositModel fdModel = fixeddb.getAccountDetails(accountNumber);
	OtherDepositModel rdModel = rddb.getAccountDetails(accountNumber);
	if(fdModel != null && fdModel.getAccountNumber().equals(account.getAccountNumber())){
%>
<p class="title" align="left">Fixed Account Balance</p>
<p class="value" > <%= fdModel.getBalance() %></p>
	<input class="un" type="number" name="amount" size="15" placeholder="Amount" required="required" > 
	<input class="submit" type="submit" value="Withdraw"><br><br>
<%} else if(rdModel != null && rdModel.getAccountNumber().equals(account.getAccountNumber())){
	%>
	<p class="title" align="left">Recurring Account Balance</p>
	<p class="value" > <%= rdModel.getBalance() %></p>
		<input class="un" type="number" name="amount" size="15" placeholder="Amount" required="required" > 
		<input class="submit" type="submit" value="Withdraw"><br><br>
	<%} else{ %>
<label class="title">No DataFound</label><br><br>
<%} %>
</body>
</html>