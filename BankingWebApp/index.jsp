<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.bank.bank.Bank"%>
<%@page import="com.bank.utils.Utils"%>
<%@page import="com.bank.utils.MyDbConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>bank list text</title>
</head>
<body>
<div id="form">
	<h2>Select the Country & State</h2>
	<select id="country-select">
		<option disabled selected>Please Select Bank</option>
		<%
		MyDbConnection con=(MyDbConnection) getServletContext().getAttribute("con"); 
		Utils.conn=con.getConnection();
		Bank bank=Bank.getInstance();
		try {
			ResultSet rs=bank.getAllBanks();
			if(rs!=null){
				while(rs.next()){%>
					<option value=<%=rs.getString("name") %>> <%=rs.getString("name") %> </option>
			<%}
		} 
		}catch (SQLException e) {
			e.printStackTrace();
		}
		%>
	</select>
	<select id="state-select">
	</select>
	<select id="city-select">
	</select>
	<script type="text/javascript">
		function getStatesSelectList(){
			var country_select = document.getElementById("country-select");
			var city_select = document.getElementById("city-select");
			
			var country_id = country_select.options[country_select.selectedIndex].value;
			console.log('CountryId : ' + country_id);
 
			var xhr = new XMLHttpRequest();
			var url = 'branchList.jsp?bank=' + country_id;
			// open function
			xhr.open('GET', url, true);
			xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			
			// check response is ready with response states = 4
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4 && xhr.status == 200){
					var text = xhr.responseText;
					console.log('response from states.php : ' + xhr.responseText);
					var state_select = document.getElementById("state-select");
					state_select.innerHTML = text;
					state_select.style.display='inline';
				}
			}
			xhr.send();
		} 
		var country_select = document.getElementById("country-select");
		country_select.addEventListener("change", getStatesSelectList);
 
/* 		var state_select = document.getElementById("state-select");
		state_select.addEventListener("change", getCitySelectList); */
	</script>
</div>
</body>
</html>