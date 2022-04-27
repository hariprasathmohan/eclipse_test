<%@page import="com.bank.database.RecurringDepositDAO"%>
<%@page import="com.bank.models.TransactionModel"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.database.TransactionDAO"%>
<%@page import="com.bank.database.LoanDAO"%>
<%@page import="com.bank.database.FixedDepositDAO"%>
<%@page import="com.bank.database.AccountDao"%>
<%@page import="com.bank.models.AccountModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="index.js"></script>
<title>Bank</title>
</head>
<body>
<%
String accountNumber = request.getParameter("accountNumber");
String accountType = request.getParameter("type");
//System.out.println(request.getParameter("accountNumber"));
double balance;
TransactionDAO transaction = new TransactionDAO();
List<TransactionModel> transactions;
if(accountType.equalsIgnoreCase("FD")){
	FixedDepositDAO accountdb = new FixedDepositDAO();
	balance = accountdb.getBalance(accountNumber);
	transactions = transaction.getTransactions(accountNumber);
	accountType = "Fixed Deposit Account";
}else if(accountType.equalsIgnoreCase("RD")){
	RecurringDepositDAO accountdb = new RecurringDepositDAO();
	balance = accountdb.getBalance(accountNumber);
	transactions = transaction.getTransactions(accountNumber);
	accountType = "Recurring Deposit Account";
} else if(accountType.equalsIgnoreCase("loan")){
	LoanDAO accountdb = new LoanDAO();
	balance = accountdb.getBalance(accountNumber);
	transactions = transaction.getTransactions(accountNumber);
	accountType = "Loan Account";
} else if(accountType.equalsIgnoreCase("savings")) {
	AccountDao accountdb = new AccountDao();
	balance = accountdb.getBalance(accountNumber);
	transactions = transaction.getTransactions(accountNumber);
	accountType = "Savings Account";
} else {
	AccountDao accountdb = new AccountDao();
	balance = accountdb.getBalance(accountNumber);
	transactions = transaction.getTransactions(accountNumber);
	accountType = "Current Account";
}
%>
<div align="center">
<p class="sign" align="center"><%= accountType %></p>
<p class="sign" align="left">Account number : <%= accountNumber %></p>
<p class="sign" align="left">Available Balance : <%= balance %></p>
<br><br>
	<%if(transactions == null || transactions.size() <= 0){%>
	<p class="value"><%="No Records Found" %></p>
	
	<%} else {%>
<table border="1">
<tr>
<th>Date</th>
<th>Description</th>
<th>Credit</th>
<th>Debit</th>
</tr>
<% for(TransactionModel trans : transactions){ %>
<tr>
<td><%=trans.getDate() %></td>
<td><%=trans.getToAccount()+"\n"+ trans.getPurpose() %></td>
<td><%=trans.getCreditAmount() %></td>
<td><%=trans.getDebitAmount() %></td>
</tr>
<%}} %>
</table>
<br><br>
</div>
</body>
</html>