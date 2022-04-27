<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Profile</title>
</head>
<body>
<div class="main" align ="center">
	<p class="sign" align="center">Profile</p>
	<table>
	<tr>
		<td><label class="title">Name</label></td>
		<td><label class="value"><s:property value="customer.name"/></label></td>
	</tr>
	<tr>
		<td><label class="title">Email</label></td>
		<td><label class="value"><s:property value="customer.email"/></label></td>
	</tr>
	<tr>
		<td><label class="title">Mobile</label></td>
		<td><label class="value"><s:property value="customer.mobile"/></label></td>
	</tr>
	<tr>
		<td><label class="title">Address</label></td>
		<td><label class="value"><s:property value="customer.address"/></label></td>
	</tr>
	</table>
	<br><br>
</div>
</body>
</html>