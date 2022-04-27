<%@page import="java.util.List"%>
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

<s:form action="test">
<s:textfield cssClass="un" name="name" placeholder="Name"></s:textfield>

<s:textfield cssClass="un" name="email" placeholder="Email"></s:textfield>

<s:textfield cssClass="un" name="mobile" placeholder="Mobile"></s:textfield>

<s:textfield cssClass="un" name="address1" placeholder="Address 1"></s:textfield>

<s:textfield cssClass="un" name="address2" placeholder="Address 2"></s:textfield>

<s:textfield cssClass="un" name="city" placeholder="City"></s:textfield>

<s:textfield cssClass="un" name="state" placeholder="State"></s:textfield>

<div align="center">
<s:radio id="type" name="type" title="label1" list="#{'1' : 'Savings account','2' : 'Current account'}"/>

</div>

<s:select list="#{'1' : 'some','2' : 'some2','3' : 'some3' }" name="bank"></s:select>

<s:select name="branch" list="{'jkkj'}">
<option value="1">ffcfg</option>
<option value="2">ffcfg</option>
<option value="3">ffcfg</option>
</s:select>

<s:submit cssClass="submit" name="submit" value="Login"></s:submit>
</s:form>

</div>
</body>
</html>