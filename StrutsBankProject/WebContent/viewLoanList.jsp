<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
    <%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Loan List</title>
</head>
<body>
<div class="main" align="center">
<s:if test="loanList.size() <= 0">
<p class="value"><s:label value="No Records Found"></s:label></p>
</s:if>
<s:else>
<table id="tbl" border="1">
<tr align="center">
<th>Loan Account Number</th>
<th>Balance</th>
<th>Loan Type</th>
<th> view Statement</th>
</tr>
<s:iterator value="loanList">
<tr align="center">
<td><s:property value="loanAccountNumber"/></td>
<td><s:property value="balance"/></td>
<td><s:property value="loanType"/></td>
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
	var path = "viewStatement?accountNumber="+accNum+"&type=loan";
	window.location=path;
}
</script>
</body>
</html>