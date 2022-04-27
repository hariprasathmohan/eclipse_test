<%@page import="com.bank.database.RecurringDepositAccountDAO"%>
<%@page import="com.bank.database.FixedDepositAccountDAO"%>
<%@page import="com.bank.database.AccountDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.bank.database.TransactionsDAO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.bank.models.AccountModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Account</title>
</head>
<body>
<%!String accountNumber; 
TransactionsDAO transactions = new TransactionsDAO();
AccountDAO account=new AccountDAO();
FixedDepositAccountDAO fd=new FixedDepositAccountDAO();
RecurringDepositAccountDAO rd=new RecurringDepositAccountDAO();
double balance;
String action;
%>
<% 
		accountNumber = request.getParameter("accountNumber");
		action = request.getParameter("action");
	if(action.equals("savings"))
	{
		balance = account.getBalance(accountNumber);
		out.print("Savings Account");
	} else if(action.equals("FD"))
	{
		balance = fd.getBalance(accountNumber);
		out.print("Fixed Deposit Account");
	} else if(action.equals("RD"))
	{
		balance = rd.getBalance(accountNumber);
		out.print("Recurring Deposit Account");
	}
%><br><br>
	Account number : <%= accountNumber%><br><br>
	Account Balance : <%= balance %><br><br>
	<input type="submit" id="show" value="Show Transactions" onclick="showTransactions(<%= accountNumber%>)"><br><br><br>
	<div id="showTrans">
		  <br> <br> <br>
	</div>
	<script type="text/javascript">
		function showTransactions(accountNumber) {
			console.log('Im IN' + accountNumber)
			var show = document.getElementById("show");
			var ajax = new XMLHttpRequest();
			var url = 'ViewTransactions.jsp?accountNumber=' + accountNumber;
			ajax.open('GET', url , true);
			ajax.onreadystatechange = function(){
				console.log(ajax.readyState+'     '+ajax.status);
				if(ajax.readyState == 4 && ajax.status == 200){
					var showTrans = document.getElementById("showTrans");
					showTrans.innerHTML = ajax.responseText;
					showTrans.style.display = 'inline';
				}
			}
			ajax.send();
		}
	</script>
</body>
</html>