<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bank</title>
</head>
<body>
	<%! String action; %>
	<%action= request.getAttribute("action").toString();
	if(action.equals("account")){%>
	<%="Your Account Number is : "+request.getAttribute("accountNumber") %>
	<%="Your User Name is : "+request.getAttribute("userId") %>
	<a href="login.html">Login</a>
	<%} else if(action.equals("FD")) {%>
		<%="Your Fixed Deposit Account Number is : "+request.getAttribute("accountNumber") %>
		<a href="choice.jsp">Home</a>
	<%} else if(action.equals("RD")) { %>
		<%="Your Recurring Deposit Account Number is : "+request.getAttribute("accountNumber") %>
		<a href="choice.jsp">Home</a>
	<%}%>
</body>
</html>