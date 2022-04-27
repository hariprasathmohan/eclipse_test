<%@page import="java.util.ArrayList"%>
<%@page import="com.bank.database.RecurringDepositDAO"%>
<%@page import="com.bank.models.OtherDepositModel"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.database.FixedDepositDAO"%>
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
<title>Insert title here</title>
</head>
<body>
<div class="main">
<p class="sign" align="left">Other Deposit Account List</p>
<%
	FixedDepositDAO fixeddb = new FixedDepositDAO();
	RecurringDepositDAO recurringdb = new RecurringDepositDAO();
	
	List<OtherDepositModel> depositList = new ArrayList<>();
	depositList.addAll(fixeddb.getAccounts(account.getAccountNumber()));
	depositList.addAll(recurringdb.getAccounts(account.getAccountNumber()));
	if(depositList.size() <= 0 ){
%>
<p class="value"><%="No Records Found"%></p>
<%} else {%>
<table border="1">
<tr align="center">
<th>Deposit Account Number</th>
<th>Balance</th>
<th>Number of months</th>
<th>View Statement</th>
</tr>
<%for(OtherDepositModel list: depositList){%>
<tr align="center">
<td> <%= list.getDepositAccountNumber() %></td>
<td> <%= list.getBalance()%></td>
<td> <%= list.getNoOfMonths() %></td> <%-- "AccountStatement.jsp?accountNumber=<%= fixed.getFdAccountNumber() %>&type=FD" --%>
<td> <input type="button" value="view" onclick="loadTransactions('views','<%= list.getDepositAccountNumber() %>','<%= list.getType() %>')"/> </td>
</tr>
<%}}%>
</table>
<br>
<div id="views">
</div>
</div>
</body>
</html>