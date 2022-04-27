<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript">
function hideAll() {
	document.getElementById("action").style.display = "none";
	document.getElementById("create").style.display = "none";
	document.getElementById("withdraw").style.display = "none";
	document.getElementById("view").style.display = "none";
}
	function hideOtherDep() {
		document.getElementById("create").style.display = "none";
		document.getElementById("withdraw").style.display = "none";
		document.getElementById("view").style.display = "none";
	}
	function showThis(id) {
		hideOtherDep();
		document.getElementById(id).style.display = "block";
	}
</script>
<title>Other Deposit</title>
</head>
<body onload="hideAll()">
<form action="otherDeposit" method="post">
<select id="type" name="type" onchange="showThis('action')">
	<option value="none">--select--</option>
	<option value="fixeddeposit">Fixed Deposit</option>
	<option value="recurringdeposit">Recurring Deposit</option>
	</select><br><br>
	<div id="action">
		<select id="action" name="action" onchange="showThis(this.options[this.selectedIndex].value)">
		<option value="none">--select--</option>
		<option value="create">Create account</option>
		<option value="withdraw">Withdraw</option>
		<option value="view">View account details</option>
		</select><br><br>
	</div>
	<div id="create">
		<label id="num"> Number Of Months </label>   
		<input value="0" id="numtxt" type="text" name="numOfMonth" size="15"/> <br> <br>
		<label id="amoun"> Amount </label>
		<input value="0" id="amountxt" type="text" name="amount" size="15"/> <br> <br>
		<input type="submit" value="submit">
	</div>
	<div id="withdraw">
		<label id="amoun"> Amount </label>    
		<input id="amountxt" type="text" name="amountt" size="15"/> <br> <br>
		<input type="submit" value="submit">
	</div>
	<div id="view"> 
		<input type="submit" value="Show Account">
	</div>
</form>
</body>
</html>