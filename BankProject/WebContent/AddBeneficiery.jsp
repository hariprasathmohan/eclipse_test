<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="MainPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Add Beneficiery</title>
</head>
<body>
<div class="main" align="justify">
<p class="sign" align="center">Pay</p>
    <form class="form1" action="addBene" method="post">
     <input class="un " type="text" name="name" placeholder="Name" required="required">
      <input class="un " type="text" name="accountNumber" placeholder="Account Number" required="required">
      <input class="un " type="text" name="ifsc" placeholder="IFSC code" required="required">
      <input class="submit" type="submit" value="Add" /> <br><br>     
    </form>
    <br>     
</div>
</body>
</html>