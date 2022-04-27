<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="MainPage.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<script type="text/javascript">
function getDetails() {
	var accountNumber = document.getElementById("loanAccNo").value;
	var url = "showLoanDetails.jsp?accountNumber="+accountNumber;
	var ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status == 200){
			console.log(ajax.responseText);
			var opDiv = document.getElementById("viewLoan");
			opDiv.innerHTML = ajax.responseText;
			opDiv.style.display='inline';
		}
	}
	ajax.open('GET', url,true);
	ajax.send();
}
</script>
<title>Insert title here</title>
</head>
<body>
<div class="main" align="justify">
<p class="sign" align="center">Loan Repayment</p>
<form class="form1" method="post" action="payLoan">
<input class="un" type="text" name="loanAccNo" id="loanAccNo" size="15" placeholder="Loan Number" required="required" />
<input class="submit"  type="button" value="View Loan account" onclick="getDetails()">
<br><br>
<div id="viewLoan">
</div>
</form>
</div>
</body>
</html>