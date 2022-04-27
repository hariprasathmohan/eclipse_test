<%@page import="java.util.List"%>
<%@page import="com.bank.database.BankDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Register</title>
</head>
<body>
<div class="main" align="center">
<p class="sign" align="center">Login</p>
<s:form class="form1" action="register">
<s:textfield class="un" name="name" placeholder="Name"></s:textfield>
<s:textfield class="un" name="email" placeholder="Email"></s:textfield>
<s:textfield class="un" name="mobile" placeholder="Mobile"></s:textfield>
<s:textfield class="un" name="address1" placeholder="Address 1"></s:textfield>
<s:textfield class="un" name="address2" placeholder="Address 2"></s:textfield>
<s:textfield class="un" name="city" placeholder="City"></s:textfield>
<s:textfield class="un" name="state" placeholder="State"></s:textfield>
<div align="center">
<s:radio name="type" title="label1" list="#{'1':'Savings account','2':'Current account'}"/>
</div>
<s:select class="un" list="#{'none':'select Bank','SBI':'SBI','HDFC':'HDFC','TMB':'TMB'}" name="bankname"></s:select>
<s:select class="un" list="#{'none':'select Branch','MDU':'MDU','CBE':'CBE','TSI':'TSI'}" name="branchName"></s:select>
<s:submit cssClass="submit" name="submit" value="Register"></s:submit>
</s:form>
<a href="login.jsp">     Already have account? Login here</a> <br><br>
</div>
</body>
</html>