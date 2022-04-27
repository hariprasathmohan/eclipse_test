<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="MainPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Insert title here</title>
</head>
<body>
<div class="main" align="justify">
<p class="sign" align="center">Pay</p>
    <form class="form1" action="quickpay" method="post">
     <input class="un " type="text" name="name" value="<%=request.getParameter("name") %>" placeholder="Name" readonly="readonly">
      <input class="un " type="text" name="accountNumber" value="<%=request.getParameter("accountNum") %>" placeholder="Account Number" readonly="readonly">
      <input class="un " type="text" name="ifsc" value="<%=request.getParameter("ifsc") %>" placeholder="IFSC code" readonly="readonly">
      <input class="un " type="text" name="purpose" placeholder="purpose" required="required">
      <input class="un " type="number" name="amount" placeholder="amount" required="required">
      <input class="submit" type="submit" value="Pay" /> <br><br>     
    </form>  
    <br>     
</div>
</body>
</html>