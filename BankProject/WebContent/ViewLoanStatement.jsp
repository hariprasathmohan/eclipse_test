<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="MainPage.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="index.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="main" align="justify">
<p class="sign" align="center">Loan Repayment</p>
<input class="un" type="text" id="loanAccNo" name="loanAccNo" size="15" placeholder="Loan Number" required="required" />
<input class="submit"  type="submit" value="Get Statement" onclick="loadTransactions('viewLoan',loanAccNo.value,'loan')">
<br><br>
<div id="viewLoan">
</div>
</div>
<script type="text/javascript">
function getAccountNumber() {
	var accountNumber = document.getElementById("loanAccNo").value;
	console.log(accountNumber)
	return accountNumber;
}
</script>
</body>
</html>