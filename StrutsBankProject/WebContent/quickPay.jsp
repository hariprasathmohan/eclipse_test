<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
    <%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="main" align="center">
    <p class="sign" align="center">Quick pay</p>
    <s:form class="form1" action="quickPay">
    <s:textfield class="un" type="text" name="name" placeholder="Name"></s:textfield>
    <s:textfield class="un" type="text" name="accountNumber" placeholder="Account Number"></s:textfield>
    <s:textfield class="un" type="text" name="ifsc" placeholder="IFSC code"></s:textfield>
    <s:textfield class="un" type="text" name="purpose" placeholder="Purpose"></s:textfield>
    <s:textfield class="un" type="number" name="amount" placeholder="Amount"></s:textfield>
    <s:submit class="submit" value="Pay"></s:submit>
    </s:form>  
</div>
</body>
</html>