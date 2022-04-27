<%@page import="com.bank.models.AccountModel"%>
<%@page import="com.bank.database.LoanDAO"%>
<%@page import="com.bank.models.LoanModel"%>
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
	LoanDAO loandb = new LoanDAO();
	String accountNumber = request.getParameter("accountNumber");
	LoanModel loan = loandb.getLoanAccountDetails(accountNumber);
	if(loan != null && loan.getAccountNumber().equals(account.getAccountNumber())){
%>
<p class="title" align="left">Loan Balance</p>
<p class="value" > <%= loan.getBalance() %></p>
	<input class="un" type="number" name="amount" size="15" placeholder="Amount" required="required" > 
	<input class="submit" type="submit" value="Pay"><br><br>

<%} else{ %>
<label class="title">No DataFound</label><br><br>
<%} %>
</body>
</html>