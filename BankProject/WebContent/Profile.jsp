<%@page import="com.bank.models.CustomerModel"%>
<%@page import="com.bank.models.AccountModel"%>
<%@page import="com.bank.database.CustomerDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Profile</title>
</head>
<body>
<% 
	AccountModel account = (AccountModel) request.getSession().getAttribute("myAccount");
	CustomerDAO customerdb = new CustomerDAO();
	CustomerModel customer = customerdb.getCustomerDetails(account.getCustomerId());
	//System.out.println(account.getCustomerId());
%>
<div class="main" align ="center">
	<p class="sign" align="center">Profile</p>
	<table>
	<tr>
		<td><label class="title">Name</label></td>
		<td><label class="value"><%= customer.getName() %></label></td>
	</tr>
	<tr>
		<td><label class="title">Email</label></td>
		<td><label class="value"><%= customer.getEmail() %></label></td>
	</tr>
	<tr>
		<td><label class="title">Mobile</label></td>
		<td><label class="value"><%= customer.getMobile() %></label></td>
	</tr>
	<tr>
		<td><label class="title">Address</label></td>
		<td><label class="value"><%= customer.getAddress() %></label></td>
	</tr>
	</table>
	<br><br>
</div>
</body>
</html>