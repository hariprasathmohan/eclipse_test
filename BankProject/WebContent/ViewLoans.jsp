<%@page import="com.bank.models.LoanModel"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.database.LoanDAO"%>
<%@page import="com.bank.models.AccountModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="MainPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="index.js"></script>
</head>
<body>
<div class="main">
<p class="sign" align="left">Loan List</p>
<%
	LoanDAO loandb = new LoanDAO();
	List<LoanModel> loanList = loandb.getAccounts(account.getAccountNumber());
	if(loanList == null  || loanList.size() <= 0){%>
	<p class="value"> <%= "No Records Found" %></p>
	<%} else {%>
<table border="1">
<tr align="center">
<th>Loan Account Number</th>
<th>Balance</th>
<th>Loan Type</th>
<th> view Statement</th>
</tr>
<% for(LoanModel loan : loanList){ %>
<tr align="center">
<td> <%= loan.getLoanAccountNumber() %></td>
<td> <%= loan.getBalance() %></td>
<td> <%= loan.getLoanType() %></td>
<td> <input type="button" value="view" onclick="loadTransactions('view',<%=loan.getLoanAccountNumber() %>,'loan')"/> </td>
</tr>	
<%}}%>
</table>
<br>
<div id="view">
</div>
</div>
</body>
</html>