<%@page import="com.bank.database.DatabaseCreation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bank.bank.Bank"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.database.BankDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
     <%@ include file="style.css"%>
</style>
<title>Register</title>
</head>
<body>
<div class="main">
    <p class="sign" align="center">Register</p>
    <form class="form1" action="register" method="post">
    
    <input class="un" type="text" name="name" size="15" placeholder="Name" required="required" />
    
    <input class="un" type="text" name=email size="15" placeholder="Email" required="required" />
    
    <input class="un" type="text" name="mobile" size="15" placeholder="Mobile" required="required" />
    
    <input class="un" type="text" name="address1" size="15" placeholder="Address 1" required="required" />
    
    <input class="un" type="text" name="address2" size="15" placeholder="Address 2" required="required" /><br>
    
    <input class="un" type="text" name="city" size="15" placeholder="City" required="required" />
    
    <input class="un" type="text" name="state" size="15" placeholder="State" required="required" />
    <div align="center">
    <input type="radio" name="accountType" value="savings"><label class="value">Savings account</label>
    <input type="radio" name="accountType" value="current"><label class="value">Current account</label><br><br>
    </div>
    <select class="un" id="banklist">
    	<option disabled selected>Select Bank</option>
    	<%
    		BankDAO bankdb = new BankDAO();
    		List<String> bankList = bankdb.getBankList();
    		for(String bankName : bankList){%>
    			<option value=<%=bankName %>> <%=bankName %> </option>
    	<%	}
    	%>
    </select>
    
    <select class="un" id="branch" name="branch">
    	<option>Select Branch</option>
    </select>
    
    <input class="submit" type="submit" value="Register"><br><br>
    <input class="submit" type="reset" value="Reset" onclick="window.location.reload()"><br><br>
    </form>
    <a href="Login.jsp">     Already have account? Login here</a> <br><br>
    
</div>
<script type="text/javascript">
		function getBranchList(){
			var bankList = document.getElementById("banklist");
			//console.log("IM IN");
			var bank = bankList.options[bankList.selectedIndex].value;
 
			//console.log(bank);
			
			var ajax = new XMLHttpRequest();
			var url = 'BranchList.jsp?bank=' + bank;
			ajax.open('GET', url);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4 && ajax.status == 200){
					var branch = document.getElementById("branch");
					//console.log(ajax.responseText);
					branch.innerHTML = ajax.responseText;
					branch.style.display='inline';
				}
			}
			ajax.send();
		} 
		var bankList = document.getElementById("banklist");
		bankList.addEventListener("change", getBranchList);
	</script>
</body>
</html>