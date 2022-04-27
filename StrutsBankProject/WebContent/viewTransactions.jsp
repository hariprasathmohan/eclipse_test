<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Account Statement</title>
</head>
<body>
<div align="center">
<p class="sign" align="center"><s:property value="accountType"/></p>
<p class="sign" align="left">Account number : <s:property value="accountNumber"/></p>
<p class="sign" align="left">Available Balance : <s:property value="balance"/></p>
<br><br>
<s:if test="transactions.size() <= 0">
<p class="value"><s:label value="No Records Found"></s:label></p>
</s:if>
<s:else>
<table border="1">
<tr>
<th>Date</th>
<th>Description</th>
<th>Credit</th>
<th>Debit</th>
</tr>
<s:iterator value="transactions">
<tr align="center">
<td><s:property value="date"/></td>
<td><s:property value="toAccount"/><s:property value="purpose"/></td>
<td><s:property value="creditAmount"/></td>
<td><s:property value="debitAmount"/></td>
</tr>
</s:iterator>
</table>
</s:else>
</div>
</body>
</html>