<%@page import="com.bank.models.BankModel"%>
<%@page import="com.bank.database.BankDAO"%>
<%@page import="com.bank.models.AccountModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="MainPage.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="index.js"></script>
</head>
<body>
<div class="main">
<%!
	BankDAO bankdb = new BankDAO();
	BankModel bank;
%>
<%	bank = bankdb.getBankDetails(account.getBranchCode()); %>
<table border="1">
<tr style="line-height: 18px; height: 18px;">
<th>Account no.</th>
<th>Bank name</th>
<th>Balance</th>
<th>Show Transactions</th>
</tr>
<tr style="line-height: 28px; height: 28px;">
<td> <%= account.getAccountNumber() %> </td>
<td> <%= bank.getName() %> </td>
<td id="balance"> <a href="#" id="balancelink" onclick="showBalance(<%= account.getAccountNumber()%>)">Click here for balance</a> </td>
<td > <a href="#" id="balancelink" onclick="loadTransactions('views','<%= account.getAccountNumber() %>','<%= account.getAccountType() %>')">Click for transactions</a> </td>
</tr>
</table>
<div id="views">
</div>
</div>
</body>
</html>