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
<s:if test="depositList.size() <= 0">
<p class="value"><s:label value="No Records Found"></s:label></p>
</s:if>
<s:else>
<table id="tbl" border="1">
<tr align="center">
<th>Deposit Account Number</th>
<th>Balance</th>
<th>Number of months</th>
<th>View Statement</th>
</tr>
<s:iterator value="depositList">
<tr align="center">
<td><s:property value="depositAccountNumber"/></td>
<td><s:property value="balance"/></td>
<td><s:property value="noOfMonths"/></td>
<td><input type="button" value="View" onclick="readData(this)"/></td>
</tr>
</s:iterator>
</table>
</s:else>
</div>
<script type="text/javascript">
function readData(id) {
	var tbl = document.getElementById("tbl");
	console.log(tbl.rows.length)
   tr = tbl.getElementsByTagName("tr");
	var accNum = tr[id.parentNode.parentNode.rowIndex].getElementsByTagName("td")[0].innerHTML;
	var type = accNum.substring(0,2);
	console.log(type);
	var path = "viewStatement?accountNumber="+accNum+"&type="+type;
	window.location=path;
}
</script>
</body>
</html>