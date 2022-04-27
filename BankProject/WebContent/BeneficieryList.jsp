<%@page import="com.bank.models.AccountModel"%>
<%@page import="com.bank.models.BeneficieryModel"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.database.BeneficieryDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="MainPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="index.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="main" align="center">
<p class="sign" align="center">Beneficiery List</p>
<%
	BeneficieryDAO beneficierydb = new BeneficieryDAO();
	List<BeneficieryModel> beneficieryList = beneficierydb.getBeneficiery(account.getAccountNumber());
	if(beneficieryList == null || beneficieryList.size() <= 0){%>
	<p class="value"><%="No Records Found" %></p>
	<%} else {%>
<table id="tbl" border="1">
<tr>
<th>Beneficiery name</th>
<th>Account number</th>
<th>IFSC</th>
<th>Action</th>
</tr>
<% for(BeneficieryModel bene : beneficieryList){ %>
<tr align="center">
<td><%= bene.getNickName() %></td>
<td><%= bene.getBeneAccNo() %></td>
<td><%= bene.getIfsc() %></td>
<td> <input type="submit" value="Pay" onclick="readData(this)" /></td>
</tr>
<%}}%>
</table>
<br>
<br>
<form class="form1" action="AddBeneficiery.jsp" method="post">
<input class="submit" type="submit" value="Add Beneficiery" /> <br>
</form>
</div>
<script type="text/javascript">
function readData(id) {
	var tbl = document.getElementById("tbl");
	console.log(tbl.rows.length)
   tr = tbl.getElementsByTagName("tr");
	var name = tr[id.parentNode.parentNode.rowIndex].getElementsByTagName("td")[0].innerHTML;
	var accNum = tr[id.parentNode.parentNode.rowIndex].getElementsByTagName("td")[1].innerHTML;
	var ifsc = tr[id.parentNode.parentNode.rowIndex].getElementsByTagName("td")[2].innerHTML;
	var path = "Benepay.jsp?name="+name+"&accountNum="+accNum+"&ifsc="+ifsc;
	window.location=path;
}
</script>
</body>
</html>