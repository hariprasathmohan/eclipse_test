<%@page import="com.bank.models.AccountModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Bank</title>
</head>
<body>
<%
AccountModel account = (AccountModel) request.getSession(false).getAttribute("myAccount");
 %>
 <nav class="navbar">
 <div class="logo"><a href="AccountSummary.jsp">BANK</a></div>
 <ul class="nav-links">
 <div class="menu">
 <li class="services">
 <a href="AccountSummary.jsp">Accounts and profile</a>
 <ul class="dropdown">
 <li><a href="AccountSummary.jsp">Account Summary</a></li>
 <li><a href="AccountStatement.jsp?accountNumber=<%=account.getAccountNumber()%>&type=<%= account.getAccountType()%>">Account Satement</a></li>
 <li><a href="ChangePassword.jsp">Change Password</a></li>
 <li><a href="Profile.jsp">Profile</a></li>
 </ul>
 </li>
 <li class="services">
 <a href="#">Payments and transfers</a>
  <ul class="dropdown">
 <li><a href="Quickpay.jsp">Quick pay </a></li>
 <li><a href="BeneficieryList.jsp">Beneficiary pay</a></li>
  <li><a href="AddBeneficiery.jsp">Add Beneficiary</a></li>
 <!-- <li><a href="#">Self pay</a></li> -->
 </ul>
 </li>
 <li class="services">
 <a href="#">Loan Accounts</a>
 <!-- DROPDOWN MENU -->
 <ul class="dropdown">
<!--  <li><a href="#">View loans</a></li> -->
 <li><a href="LoanApplication.jsp">Apply Loan </a></li>
 <li><a href="PayLoan.jsp">Pay loan</a></li>
 <li><a href="ViewLoans.jsp">View Statement</a></li>
 </ul>
 </li>
 <li class="services">
 <a href="#" onclick="load('homes','Register.jsp')">Deposit Accounts</a>
 <ul class="dropdown">
 <li><a href="DepositApplication.jsp">Create account</a></li>
 <li><a href="DepositWithdraw.jsp">Withdraw</a></li>
 <li><a href="ViewDepositAccounts.jsp">View Accounts</a></li>
 <!-- <li><a href="ViewFDAccounts.jsp">Close FD account</a></li>-->
 </ul>
 </li>
 <li><a href="logout">Logout</a></li>
 </div>
 </ul>
 </nav>
</body>
</html>